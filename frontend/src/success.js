import React, {Component} from 'react';
import {Link, Redirect} from "react-router-dom";
import './App.css';
import axios from "axios";
import Auth_home from './auth_home';

class Success extends Component {

    constructor(){
        super();
        this.state = {
            username: 0
        }
    }

    componentDidMount() {
        axios.get('http://localhost:8081/auth/username')
            .then((response) => {
                let uname = response.data;
                this.setState({
                    username: uname
                });
            })
    }

    render() {
        if (this.state.username===0) {
            return (
                <div className="App">
                    <header className="App-header">
                        <h3> Login failed, try again</h3>
                        <Link to="/">
                            <button variant="outlined">
                                back
                            </button>
                        </Link>
                    </header>
                </div>
            )
        } else {
            return (
                <div className="App">
                    <header className="App-header">
                        <h3> Logged-in as { this.state.username }</h3>
                        < Auth_home username={this.state.username}/>
                        <Link to="/">
                            <button variant="outlined">
                                back
                            </button>
                        </Link>
                        <Link to="/auth/personalBananas">
                            <button variant="outlined">
                                personal bananas
                            </button>
                        </Link>
                    </header>
                </div>
            );
        }
    }
}
export default Success;