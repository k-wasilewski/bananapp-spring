import React from 'react';
import {MyField} from "./myField";
import {isEmail} from "@formiz/validations";
import axios from "axios";

class NameForm extends React.Component {
    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(values, event) {
        console.log(values.email + ", " + values.password);

        axios({
            method: 'post',
            url: '/',
            data: {
                j_username: values.email,
                j_password: values.password
            }
        }).then(function (response) {
            if (response.status === 200) {
                console.log("login success");
                console.log(response.data);
            } else {
                console.log("login response: " + JSON.stringify(response));
            }
        })
            .catch(function (error) {
                console.log(error);
            });
    }

    render() {
        return (
            <form action={this.handleSubmit}>
                <label>User: </label>
                <input
                    name="email"
                />
                <br/>
                <label>Password: </label>
                <input
                    name="password"
                    type="password"
                />
                <br/>
                <button
                    type="submit"
                >
                    Submit
                </button>
            </form>
        );
    }
}

export default NameForm;