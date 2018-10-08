import React, {Component} from 'react';
import {doGet} from "../util/NetworkUtils";
import {SESSION} from "../constant/constants";

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
        console.log(session);
        this.setState({session});
    };

    render() {
        const {session} = this.state;
        return (
            <div>
                <label ref={"session"}>Session {session.roomNumber}</label>
                <div id={"session"}>
                    <ul>
                        {
                            session.users && session.users.map(u => <li>{u.name}</li>)
                        }
                    </ul>
                </div>
            </div>
        );
    }
}