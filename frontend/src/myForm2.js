import React from 'react'
import { Formiz, useForm } from '@formiz/core'
import { isEmail } from '@formiz/validations' // Import some validations
import { MyField } from './myField' // Import your field

export const MyForm2 = () => {
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