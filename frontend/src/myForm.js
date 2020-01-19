import React from 'react'
import { Formiz, useForm } from '@formiz/core'
import { isEmail } from '@formiz/validations' // Import some validations
import { MyField } from './myField' // Import your field

export const MyForm = () => {
    const myForm = useForm();
    const handleSubmit = (values) => {
        console.log(values)
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
                    name="firstName"
                    label="First Name"
                    required="First Name is required"
                />
                <MyField
                    name="lastName"
                    label="Last Name"
                    required="Last Name is required"
                />
                <MyField
                    name="email"
                    label="Email"
                    validations={[
                        {
                            rule: isEmail(),
                            message: 'This is not a valid email',
                        },
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