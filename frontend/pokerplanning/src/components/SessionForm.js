import React, {Component} from 'react';
import {doGet, doPost} from "../util/NetworkUtils";
import {SESSION} from "../constant/constants";
import UserForm from "./UserForm";

export default class SessionForm extends Component {


    constructor(props) {
        super(props);
        this.state = {
            session: null,
            sessionNumber: 0
        }
    }

    createNewSession = () => {
        doPost(SESSION, {postBody: {}})
            .then(res => this.setSession(res))
            .catch(() => console.log("Error"));
    };


    joinToSession = () => {
        const {sessionNumber} = this.state;
        doGet(SESSION + "/" + sessionNumber)
            .then(res => this.setSession(res))
            .catch(() => console.log("Error"));
    };

    trackSessionNumber = (e) => {
        e.preventDefault();
        e.stopPropagation();
        const sessionNumber = e.target.value;
        console.log(sessionNumber);

        this.setState({sessionNumber});
    };

    setSession = (response) => {
        const session = response.data;
        console.log("session", session);
        this.setState({session});
    };


    renderSessionConnection = () => {
        return (
            <div>
                <label htmlFor={"join"}>Join to session</label>
                <div id={"join"}>
                    <input type={"number"} onChange={this.trackSessionNumber}/>
                    <button onClick={this.joinToSession}>join session</button>
                </div>
                <label htmlFor={"start-new"}>Start new session</label>
                <div id={"start-new"}>
                    <button onClick={this.createNewSession}>start new</button>
                </div>
            </div>
        );
    };

    render() {
        const {session} = this.state;
        return (
            <div>
                {!session && this.renderSessionConnection()}
                {session && <UserForm pokerSession={session}/>}
            </div>
        );
    }
}