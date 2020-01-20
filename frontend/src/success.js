import React from 'react';
import {Link} from "react-router-dom";
import './App.css';

class Success extends React.Component {

    render() {
        return (
            <div className="App">
                <header className="App-header">
                    <h3> Logged-in, </h3>
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