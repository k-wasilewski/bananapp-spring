import React from 'react';
import {Link, Redirect} from "react-router-dom";
import './App.css';
import axios from "axios";

class Auth_results extends React.Component {

    saveimg = (score, acc, filename, username) => {
        axios.post('http://localhost:8081/auth/saveimg',
            "filename=" + filename[1] + "&"
            + "score=" + score[1] + "&" + "acc=" +acc[1] +
            "&" + "uname=" + username
        )};


    render() {
        const img = this.props.location.state.img;
        const prediction = this.props.location.state.prediction;
        const username = this.props.location.state.username;

        const scoreRegex = /score:(.*?),/;
        const accRegex = /accuracy:(0\.\d\d)/;
        const filenameRegex = /filename:(.*?)END/

        const score = scoreRegex.exec(prediction);
        const accuracy = accRegex.exec(prediction);
        const filename = filenameRegex.exec(prediction);

        var days = '[error]';

        if (!score) {
            return (
                <div className="App">
                    <header className="App-header">
                        <h3>File already exists or file type not supported, try again</h3>
                        <Link to="/success">
                            <button variant="outlined">
                                Back
                            </button>
                        </Link>
                    </header>
                </div>
            )
        } else {
            this.saveimg(score, accuracy, filename, username);

            if (score[1]==1.0) days="1 day";
            else if (score[1]==2.0) days="2 days";
            else if (score[1]==3.0) days="3 days";
            else if (score[1]==4.0) days="4 days";
            else if (score[1]==5.0) days="5 days";
            else if (score[1]==6.0) days="6 days";
            else if (score[1]==7.0) days="7 days";

            return (
                <div className="App">
                    <header className="App-header">
                        <img src={img} style={{maxHeight: "500px"}} />
                        <h3> Your banana is { days } old, </h3>
                        <h3> with { Number((accuracy[1]*100).toFixed(2)) }% certainty</h3>
                        <Link to="/success">
                            <button variant="outlined">
                                Back
                            </button>
                        </Link>
                    </header>
                </div>
            );
        }
    }
}
export default Auth_results;