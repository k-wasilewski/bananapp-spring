import React, {Component} from 'react';
import logo from './logo.svg';
import './App.css';
import {Redirect} from 'react-router-dom';
import {MyForm} from "./myForm";
import {MyForm2} from "./myForm2";

class Home extends Component {

    state =  {
        selectedFile: null,
        imagePreviewUrl: null,
        prediction: null,
        redirect: false,
        login2: false,
        login3: false
    };

    login2 = () => {
        if (!this.state.login2) this.setState({login2: true})
        else this.setState({login2: false})
    }

    login3 = () => {
        if (!this.state.login3) this.setState({login3: true})
        else this.setState({login3: false})
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

    hello = () => {
        fetch('/api/hello')
            .then(response => response.text())
            .then(message => {
                this.setState({message: message});
            });
    };

    render() {
        let $imagePreview = (<div className="previewText image-container">Please select an Image for Preview</div>);
        if (this.state.imagePreviewUrl) {
            $imagePreview = (<div className="image-container" ><img src={this.state.imagePreviewUrl} alt="icon" width="200" /> </div>);
        }
        let $form2;
        if ( this.state.login2 ) {
            $form2 = (
                <div>
                    <MyForm />
                </div>
            )
        } else $form2 = (<div />)
        let $form3;

        if ( this.state.login3 ) {
            $form3 = (<MyForm2/>)
        } else $form3 = (<div />)
        if (!this.state.redirect) return (
            <div className="App">
                <header className="App-header">
                    <img src={logo} className="App-logo" alt="logo"/>
                    <h1 className="App-title">{this.state.message}</h1>
                        <button variant="outlined" onClick={this.login2}>
                            Register
                        </button>
                    { $form2 }
                    <button variant="outlined" onClick={this.login3}>
                        Log-in
                    </button>
                    { $form3 }
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