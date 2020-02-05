import React, {Component} from 'react';
import logo from './logo0.jpg';
import logo_title from './logotitle.jpg';
import './App.css';
import {Redirect} from 'react-router-dom';
import {Form_register} from "./Form_register";
import {Form_login} from "./Form_login";

class Home extends Component {

    state =  {
        selectedFile: null,
        imagePreviewUrl: null,
        prediction: null,
        redirect: false,
        register: false,
        login: false
    };

    do_register = () => {
        if (!this.state.register) this.setState({register: true})
        else this.setState({register: false})
    }

    do_login = () => {
        if (!this.state.login) this.setState({login: true})
        else this.setState({login: false})
    }

    fileChangedHandler = event => {
        this.setState({
            selectedFile: event.target.files[0]
        })

        let reader = new FileReader();

        reader.onloadend = () => {
            this.setState({
                imagePreviewUrl: reader.result
            });
        }

        reader.readAsDataURL(event.target.files[0])

    }

    submit = () => {

        var fd = new FormData();

        fd.append('file', this.state.selectedFile);

        var request = new XMLHttpRequest();

        var $this = this;
        request.onload = function() {
            $this.setState({
                prediction: request.response,
                redirect: true
            });
        };

        request.open("POST", "http://localhost:8082/image", true);
        request.send(fd);
    }

    render() {
        let $imagePreview = (<div className="previewText image-container">Please select an image</div>);
        if (this.state.imagePreviewUrl) {
            $imagePreview = (<div className="image-container" ><img src={this.state.imagePreviewUrl} alt="icon" width="200" /> </div>);
        }

        let form_register;
        if ( this.state.register ) {
            form_register = (
                <div>
                    <Form_register />
                </div>
            )
        } else form_register = (<div />)

        let form_login;
        if ( this.state.login ) {
            form_login = (<Form_login/>)
        } else form_login = (<div />)

        let logout_message;
        console.log(this.state.logout);
        if ( this.props.location.state.logout ) {
            logout_message = (<div>Successfully logged out</div>)
        } else logout_message = (<div />)

        if (!this.state.redirect) return (
            <div className="App">
                <header className="App-header">
                    <div className="App-break"/>
                    <img src={logo} className="App-logo" alt="logo"/>
                    <div className="App-break"/>
                    <img src={logo_title} />
                    { logout_message }
                        <button variant="outlined" onClick={this.do_register}>
                            Register
                        </button>
                    { form_register }
                    <button variant="outlined" onClick={this.do_login}>
                        Log-in
                    </button>
                    { form_login }
                    <input type="file" name="avatar" onChange={this.fileChangedHandler} />
                    <button type="button" onClick={this.submit} > Upload </button>
                    { $imagePreview }
                </header>
            </div>
        );
        else if (this.state.redirect) {
            return (
                <Redirect to={{
                    pathname: '/results',
                    state: { prediction: this.state.prediction,
                            img: this.state.imagePreviewUrl}
                }}/>
            )
        }
    }
}

export default Home;