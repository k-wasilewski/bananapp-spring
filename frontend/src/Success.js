import React, {Component} from 'react';
import {Link, Redirect} from "react-router-dom";
import './App.css';
import axios from "axios";
import Auth_home from './Auth_home';

class Success extends Component {

    constructor(){
        super();
        this.state = {
            username: 0,
            redir: 0
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

    logout = () => {
        axios.get('http://localhost:8081/logout')
            .then((response) => {
                console.log(response.data);
                this.setState({
                    redir: 'logout'
                });
            })
    }

    render() {
        var $this = this;

        if (this.state.username===0) {
            return (
                <div className="App">
                    <header className="App-header">
                        <h3> Login failed, try again</h3>
                        <Link to="/">
                            <button variant="outlined">
                                Back
                            </button>
                        </Link>
                    </header>
                </div>
            )
        } else if (this.state.redir===0) {
            return (
                <div className="App">
                    <header className="App-header">
                        <h3> Logged-in as { this.state.username }</h3>
                        < Auth_home username={this.state.username}/>
                        <Link to={{
                            pathname: "/auth/personalBananas",
                            state: {
                                username: $this.state.username
                            }
                        }}>
                            <button variant="outlined">
                                Personal bananas
                            </button>
                        </Link>
                        <button variant="outlined" onClick={this.logout}>
                            Logout
                        </button>
                        <Link to="/">
                            <button variant="outlined">
                                Back
                            </button>
                        </Link>
                    </header>
                </div>
            );
        } else if (this.state.redir=='logout') {
            return (
                <Redirect to={{
                    pathname: '/',
                    state: { logout: true }
                }}/>
            )
        }
    }
}
export default Success;