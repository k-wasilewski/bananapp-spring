import React from 'react'
import { Formiz, useForm } from '@formiz/core'
import { isEmail } from '@formiz/validations' // Import some validations
import { MyField } from './myField' // Import your field
import axios from "axios";

//register
export const MyForm = () => {
    const myForm = useForm();
    const handleSubmit = (event) => {

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
};