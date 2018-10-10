import React, {Component} from 'react';
import SockJsClient from 'react-stomp';
import {doGet} from "../util/NetworkUtils";
import {BASE_URL, SESSION} from "../constant/constants";
// React router, socks.js change port
export default class PokerBoard extends Component {


    constructor(props) {
        super(props);
        this.state = {
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
        console.log("session", session);
        this.setState({session});
    };

    // updateSession = () => {
    //     try {
    //         const {session} = this.state;
    //         this.clientRef.sendMessage("/board/session", JSON.stringify(session));
    //         return true;
    //     } catch(e) {
    //         return false;
    //     }
    // };

    render() {
        const {session} = this.state;
        return (
            <div>
                <SockJsClient url={BASE_URL +"/board"} topics={['/topic/session']}
                              onMessage={this.onSessionReceive}
                              ref={(client) => { this.clientRef = client }}/>
                <label ref={"session"}>Session {session.roomNumber}</label>
                <div id={"session"}>
                    <ul>
                        {
                            session.users && session.users.map((u, i) => <li key={i}>{u.name}</li>)
                        }
                    </ul>
                </div>
            </div>
        );
    }
}