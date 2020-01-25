import React from 'react'
import { Formiz, useForm } from '@formiz/core'
import { isEmail } from '@formiz/validations' // Import some validations
import { MyField } from './myField' // Import your field
import axios from "axios";
import { useState } from 'react'

//register
export const MyForm = () => {
    const myForm = useForm();
    const [redirect, setRedirect] = useState(0);

    const handleSubmit = (values) => {
        /*axios.get('http://localhost:8081/create-user?username='
            + values.email + "&" + "password=" + values.password
        ).then(function (response) {
            if (response.status === 200) {
                console.log(response.data);
                setRedirect(response.data);
            }
        });*/

        axios.post('http://localhost:8081/create-user',
            "username=" + values.email + "&" + "password=" + values.password
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
                    <MyField
                        name="passwordConfirm"
                        label="Confirm password: "
                        type="password"
                        validations={[
                            {
                                rule: (value) => myForm.values.password === value,
                                deps: [myForm.values.password],
                                message: 'Passwords do not match',
                            }
                        ]}
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
    } else if (redirect=="success") {
        return (
            <Formiz
                connect={myForm}
                onValidSubmit={handleSubmit}
            >
                Registration success
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
                    <MyField
                        name="passwordConfirm"
                        label="Confirm password: "
                        type="password"
                        validations={[
                            {
                                rule: (value) => myForm.values.password === value,
                                deps: [myForm.values.password],
                                message: 'Passwords do not match',
                            }
                        ]}
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
                    E-mail already exists
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
                    <MyField
                        name="passwordConfirm"
                        label="Confirm password: "
                        type="password"
                        validations={[
                            {
                                rule: (value) => myForm.values.password === value,
                                deps: [myForm.values.password],
                                message: 'Passwords do not match',
                            }
                        ]}
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
