import React from 'react';
import ReactDOM from 'react-dom';
import { MyForm } from './myForm';
import NameForm from './nameForm';

class Login extends React.Component {

    render() {
        return (
            ReactDOM.render(<NameForm />, document.getElementById('root'))
        );
    }
}
export default Login;