import React from 'react';
import {Link, Redirect} from "react-router-dom";
import './App.css';
import axios from "axios";

class Success extends React.Component {

    state = {
        username: 0,
        status: false
    }

    render() {
        var $this = this;

        axios.get('http://localhost:8081/auth/username')
            .then(function (response) {
                $this.setState({username: response.data});
                if (response.status===200) $this.setState({status: true});
            });

        if (this.state.status) {
            return (
                <div className="App">
                    <header className="App-header">
                        <h3> Logged-in as { this.state.username }</h3>
                        <Link to="/">
                            <button variant="outlined">
                                back
                            </button>
                        </Link>
                    </header>
                </div>
            );
        } else {
            return (<Redirect to='/' />);
        }
    }
}
export default Success;