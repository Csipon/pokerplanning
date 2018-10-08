import React, {Component} from 'react';
import {doPost} from "../util/NetworkUtils";
import {CREATE_SESSION} from "../constant/constants";

export default class SessionForm extends Component{


    constructor(props) {
        super(props);
        this.state = {
            token : null
        }
    }

    createNewSession = () => {
        doPost(CREATE_SESSION, {postBody: {token: this.state.token}})
            .then(res => this.setToken(res))
            .catch(() => console.log("Error"));
    };


    setToken = (response) =>{
        const{token} = response.data;
        this.setState({token});
        console.log(response.data);
    };

    render() {
        return (
            <div>
                <label htmlFor={"join"}>Join to session</label>
                <form id={"join"}>
                    <input type={"text"}/>
                    <input type={"submit"} value={"join session"}/>
                </form>
                <label htmlFor={"start-new"}>Start new session</label>
                <button id={"start-new"} onClick={this.createNewSession}>start new</button>
            </div>
        );
    }
}