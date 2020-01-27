import React, {Component} from 'react'
import { Formiz, useForm } from '@formiz/core'
import { isEmail } from '@formiz/validations' // Import some validations
import { MyField } from './myField'
import axios from "axios";
import {Link, Redirect} from "react-router-dom";
import Home from "./home";
import { useState } from 'react'

//login
export const MyForm2 = () => {

    const myForm = useForm();

    const [redirect, setRedirect] = useState(0);

    const handleSubmit = (values, event) => {
        console.log(values.email + ", " + values.password);

        //curl -d username=user@user.com -d password=user -L http://localhost:8081

        axios.post('http://localhost:8081',
            "username=" + values.email + "&" + "password=" + values.password
        ).then(function (response) {
            if (response.status === 200) {
                console.log(response.data);
                setRedirect(response.data);
            }
        });
        axios.post('http://localhost:8082/auth/user',
            "uname=" + values.email
        ).then(function (response) {
            if (response.status === 200) {
                console.log(response.data);
                setRedirect(response.data);
            }
        });
    };

    if (redirect == 0) {
        return (
            <Formiz
                connect={myForm}
                onValidSubmit={handleSubmit}
            >
                <form
                    noValidate
                    onSubmit={myForm.submit}
                >
                    <MyField
                        name="email"
                        label="E-mail: "
                        validations={[
                            {
                                rule: isEmail(),
                                message: 'This is not a valid email',
                            },
                        ]}
                    />
                    <MyField
                        name="password"
                        label="Password: "
                        type="password"
                    />
                    <button
                        type="submit"
                        disabled={!myForm.isValid}
                    >
                        Submit
                    </button>
                </form>
            </Formiz>
        );
    } else if (redirect == "success") {
        return (<Redirect to='/success' />);
    } else {
        return (
            <Formiz
                connect={myForm}
                onValidSubmit={handleSubmit}
            >
                <form
                    noValidate
                    onSubmit={myForm.submit}
                >
                    Login failed
                    <MyField
                        name="email"
                        label="E-mail: "
                        validations={[
                            {
                                rule: isEmail(),
                                message: 'This is not a valid email',
                            },
                        ]}
                    />
                    <MyField
                        name="password"
                        label="Password: "
                        type="password"
                    />
                    <button
                        type="submit"
                        disabled={!myForm.isValid}
                    >
                        Submit
                    </button>
                </form>
            </Formiz>
        );
    }
};
