import React, {Component} from 'react'
import { Formiz, useForm } from '@formiz/core'
import { isEmail } from '@formiz/validations' // Import some validations
import { MyField } from './myField'
import axios from "axios";
import {Link, Redirect} from "react-router-dom";

//login
export const MyForm2 = () => {

    const myForm = useForm();

    const handleSubmit = (values, event) => {
        console.log(values.email + ", " + values.password);

        //curl -d j_username=user@user.com -d j_password=user -L http://localhost:8081

        //try1
        /*axios.post("http://localhost:8081/j_username="+values.email+"&j_password="+values.password).then(function (response) {
            if(response.data.code === 200){
                console.log("login success");
                window.location('http://localhost:8081/success')
            } else {
                console.log("login response: "+response.data.code);
            }
        })
            .catch(function (error) {
                console.log(error);
            });*/
        //try2
        /*const credentials = {username: values.email, password: values.password};
        axios.post("http://localhost:8081/token/"+ "generate-token", credentials).then(res => {
            if(res.data.status === 200){
                //localStorage.setItem("userInfo", JSON.stringify(res.data.result));
                //this.props.history.push('/list-user');
                console.log("success login");
            }else {
                //this.setState({message: res.data.message});
                console.log("error logging");
            }
        });*/
        //try3
        /*axios('/', {
            method: 'POST',
            auth: {
                j_username: values.email,
                j_password: values.password
            }
        }).then((response => {
            console.log("response: "+response);
        })).catch((error) => {
            console.log("error: "+error);
        })*/
        //try4
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
                return (
                    JSON.stringify(response.data)
                )
            }
        })
            .catch(function (error) {
                console.log(error);
            });
        //try5
        /*axios.post('http://localhost:9090/login',{
            username: this.state.username,
            password: this.state.password})*/
    };

    //console.log(redir);   --ALWAYS false


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
    )
    };
