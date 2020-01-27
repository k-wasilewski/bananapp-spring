import React from 'react';
import axios from "axios";
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

        console.log("post sent from frontend8081");

        var fd = new FormData();

        fd.append('file', this.state.selectedFile);

        var request = new XMLHttpRequest();
        request.onreadystatechange = function() {
            if (this.status === 200) {
                console.log("at home: "+this.status+" "+this.statusText);
            }
        };
        var $this = this;
        request.onload = function() {
            console.log(`Response: ${request.response}`);
            $this.setState({
                prediction: request.response,
                redirect: true
            });
        };
        request.onerror = function() { // only triggers if the request couldn't be made at all
            console.log(`Network Error`);
        };
        request.onprogress = function(event) { // triggers periodically
            // event.loaded - how many bytes downloaded
            // event.lengthComputable = true if the server sent Content-Length header
            // event.total - total number of bytes (if lengthComputable)
            console.log(`Received ${event.loaded} of ${event.total}`);
        };

        //curl -d "uname=kuba" -H "Content-Type: application/x-www-form-urlencoded" -X POST http://localhost:8082/auth/user
        //var requestUname = new XMLHttpRequest();
        //requestUname.open("POST", "http://localhost:8082/auth/user", true);
        //requestUname.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded8');
        //console.log("uname: "+this.props.username);
        //requestUname.send("uname="+this.props.username);

        //axios.post('http://localhost:8082/auth/user',
        //    "uname="+this.props.username);

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