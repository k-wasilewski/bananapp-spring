import React from 'react';
import {Redirect} from "react-router-dom";

class Auth_home extends React.Component {
    constructor(props) {
        super(props);
    }

    state =  {
        selectedFile: null,
        imagePreviewUrl: null,
        prediction: null,
        redirect: false
    };

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

        request.open("POST", "http://localhost:8082/auth/image?uname="+this.props.username, true);
        request.send(fd);
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

    render() {
        let $imagePreview = (<div className="previewText image-container">Please select an Image for Preview</div>);
        if (this.state.imagePreviewUrl) {
            $imagePreview = (<div className="image-container" ><img src={this.state.imagePreviewUrl} alt="icon" width="200" /> </div>);
        }

        if (!this.state.redirect) return (
            <div>
                <header>
                    <input type="file" name="avatar" onChange={this.fileChangedHandler} />
                    <button type="button" onClick={this.submit} > Upload </button>
                    { $imagePreview }
                </header>
            </div>
        );
        else return (
            <Redirect to={{
                pathname: '/auth/results',
                state: { prediction: this.state.prediction,
                    img: this.state.imagePreviewUrl}
            }}/>
        )
    }
}

export default Auth_home;