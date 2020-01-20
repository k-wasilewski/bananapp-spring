import React from 'react'
import { Formiz, useForm } from '@formiz/core'
import { isEmail } from '@formiz/validations' // Import some validations
import { MyField } from './myField'
import axios from "axios";

//login
export const MyForm2 = () => {
    const myForm = useForm();
    const handleSubmit = (values) => {
        console.log(values.email+", "+values.password);
        axios.post("http://localhost:8081/username="+values[0]+"&password="+values[1]).then(function (response) {
            if(response.data.code === 200){
                console.log("login success");
                window.location('http://localhost:8081/success')
            } else {
                console.log("login response: "+response.data.code);
            }
        })
            .catch(function (error) {
                console.log(error);
            });
    };
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
};