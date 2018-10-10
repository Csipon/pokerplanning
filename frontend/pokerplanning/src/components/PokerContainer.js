import {BrowserRouter as Router, Link, Route} from "react-router-dom";

import React, {Component} from 'react';

export default class SessionForm extends Component {


    constructor(props) {
        super(props);
    }

    render() {
        return (
            <Router>
                <div>
                    <ul>
                        <li>
                            <Link to="/">Join session</Link>
                        </li>
                        <li>
                            <Link to="/about">Start session</Link>
                        </li>
                        <li>
                            <Link to="/topics">Topics</Link>
                        </li>
                    </ul>

                    <hr/>

                    <Route exact path="/" component={}/>
                    <Route path="/about" component={About}/>
                    <Route path="/topics" component={Topics}/>
                </div>
            </Router>
        );
    };
}