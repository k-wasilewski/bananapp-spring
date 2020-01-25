import React from 'react';
import {Link} from "react-router-dom";
import './App.css';
import axios from "axios";

class Success extends React.Component {

    state = {
        username: "n/a"
    }

    render() {
        var $this = this;
        axios.get('http://localhost:8081/username')
            .then(function (response) {
                $this.setState({username: response.data})
            });

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
    }
}
export default Success;