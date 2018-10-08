import React, {Component} from 'react';
import {doPost} from "../util/NetworkUtils";
import {USER} from "../constant/constants";
import PokerBoard from "./PokerBoard";

export default class UserForm extends Component {


    constructor(props){
        super(props);
        this.state = {
            username: "",
            user: null,
            pokerSession: this.props.pokerSession
        };
    }

    trackUserName = (e) => {
        e.preventDefault();
        e.stopPropagation();
        const username = e.target.value;

        this.setState({username});
    };

    joinUserToSession = () => {
        const {username, pokerSession} = this.state;
        const request = {
            username: username,
            roomNumber: pokerSession.roomNumber
        };
        doPost(USER, {postBody: request})
            .then(res => this.setUser(res))
            .catch(() => console.log("Error"));
        console.log("user joined");
    };


    setUser = (response) => {
          const user = response.data;

          this.setState({user});
    };

    renderCreateUser = () => {
        const {roomNumber} = this.props.pokerSession;

        return (
            <div>
                <h2>Room {roomNumber}</h2>
                <label ref={"join-session"}>Enter your name</label>
                <div id={"join-session"}>
                    <input type={"text"} onChange={this.trackUserName}/>
                    <button onClick={this.joinUserToSession}>join to session</button>
                </div>

            </div>
        );
    };

    render() {
        const {pokerSession} = this.props;
        const {user} = this.state;

        return (
            <div>
                {!user && this.renderCreateUser()}
                {user && <PokerBoard session={pokerSession} user={user}/>}
            </div>
        );
    }

}