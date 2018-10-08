import React, {Component} from 'react';
import './App.css';
import SessionForm from "./components/SessionForm"

export default class App extends Component {
    render() {
        return (
            <div className="App">
                <h1>Poker Planning</h1>
                <SessionForm />
            </div>
        );
    }
}
