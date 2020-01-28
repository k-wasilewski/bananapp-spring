import React from 'react';
import {MyField} from "./myField";
import {isEmail} from "@formiz/validations";
import axios from "axios";

class PersonalBananas extends React.Component {

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
                        <div>personal bananas of [not known]</div>
                    </header>
                </div>
            )
        } else {
            return (
                <div className="App">
                    <header className="App-header">
                        <div>personal bananas of {this.state.username}</div>
                    </header>
                </div>
            )
        }
    }
}

export default PersonalBananas;