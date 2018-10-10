import React, {Component} from 'react';
import SockJsClient from 'react-stomp';
import {doGet, doPost} from "../util/NetworkUtils";
import {BASE_URL, BUTTONS, POINT, SESSION, STORY} from "../constant/constants";
// React router, socks.js change port
export default class PokerBoard extends Component {


    constructor(props) {
        super(props);
        this.state = {
            buttonSelected: 0,
            storyName: "",
            edit: true,
            session: {},
            user: {}
        }
    }

    componentDidMount = () =>{
        const {session, user} = this.props;
        this.fetchSession(session.roomNumber);
        this.setState({user});
    };


    fetchSession = (sessionNumber) => {
        doGet(SESSION + "/" + sessionNumber)
            .then(res => this.setSession(res))
            .catch(() => console.log("Error"));
    };

    setSession = (response) => {
        const session = response.data;
        this.setState({session});
    };


    onSessionReceive = (session, topic) => {
        this.setState({session});
    };

    editEnable = (e) =>{
        const {edit} = this.state;
        if (!edit){
            this.setState({edit: !edit});
        }
    };

    confirmStory = (e) => {
        const {edit, session, storyName} = this.state;
        if (edit && storyName){
            const request = {
                roomNumber: session.roomNumber,
                storyName: storyName
            };
            doPost(STORY,{postBody: request})
                .then(res => this.setSession(res))
                .catch(() => console.log("Error"));
            this.setState({edit: !edit});
        }
    };

    trackUserStoryName = (e) => {
        e.preventDefault();
        e.stopPropagation();

        const storyName = e.target.value;
        const {storyName: oldStoryName} = this.state;

        if (storyName !== oldStoryName){
            this.setState({storyName});
        }
    };

    onPointsClick = (e) => {
        e.preventDefault();
        e.stopPropagation();
        const {session, user} = this.state;
        const point = e.target.value;
        const request = {
            roomNumber: session.roomNumber,
            token: user.token,
            point: point
        };
        doPost(POINT,{postBody: request})
            .then(res => this.setSession(res))
            .catch(() => console.log("Error"));

        this.setState({buttonSelected: point});
    };

    renderCreateStoryForm = () => {
        const {edit, storyName, user, session} = this.state;
        if (user && session.creator && user.token === session.creator.token) {
            return (
                <div id={"create-story-form"}>
                    <label ref={"create-story"}>Enter story name</label>
                    <input id={"create-story"} type={"text"} onChange={this.trackUserStoryName} disabled={!edit}/>
                    <button className={"button"} onClick={this.editEnable}>Edit</button>
                    <button className={"button"} disabled={!storyName} onClick={this.confirmStory}>Confirm</button>
                </div>
            );
        }
    };

    render() {
        const {session, buttonSelected, storyName} = this.state;
        return (
            <div className={"board"}>
                <SockJsClient url={BASE_URL + "/ws"}
                               topics={['/topic/session']}
                              onMessage={this.onSessionReceive}
                              ref={(client) => { this.clientRef = client }}/>
                <h2>Session {session.roomNumber}</h2>

                <label ref={"story-name"}>Story name</label>
                <div id={"story-name"}>
                    {session.currentStory && <span><b>{session.currentStory.name}</b></span>}
                </div>

                {this.renderCreateStoryForm()}

                <div align={"left"}>
                    <label ref={"users"}><b>Players</b></label>
                    <ul id={"users"}>
                        {
                            session.users && session.users.map((u, i) => <li key={i}>{u.name}</li>)
                        }
                    </ul>
                    {
                        storyName &&
                        session.currentStory &&
                        BUTTONS.map((point, i) =>
                            <button
                                    key={i}
                                    className={"button"}
                                    disabled={buttonSelected == point}
                                    onClick={this.onPointsClick}
                                    value={point}>{point}
                            </button>
                        )
                    }
                </div>
            </div>
        );
    }
}