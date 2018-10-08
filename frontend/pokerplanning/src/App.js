import React, {Component} from 'react';
import './App.css';
import SessionForm from "./components/SessionForm"

class App extends Component {
    render() {
        return (
            <div className="App">
                <h1>Hello</h1>
                <SessionForm />
            </div>
        );
    }
}

export default App;
