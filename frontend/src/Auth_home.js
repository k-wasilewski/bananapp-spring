import React from 'react';
import {Redirect} from "react-router-dom";
import Loading from "./Loading-component";

class Auth_home extends React.Component {
    constructor(props) {
        super(props);
    }

    state =  {
        selectedFile: null,
        imagePreviewUrl: null,
        prediction: null,
        redirect: false,
        loading: false
    };

    submit_loading = () => {
        this.loading();
        setTimeout( () => {
            this.submit();
        }, 500);
    }

    loading = () => {
        this.setState({loading: true});
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
        let $imagePreview = (<div className="previewText image-container">Select a jpg image to add a banana</div>);
        if (this.state.imagePreviewUrl) {
            $imagePreview = (<div className="image-container" ><img src={this.state.imagePreviewUrl} alt="icon" width="200" /> </div>);
        }

        let loading_component;
        if (this.state.loading) {
            loading_component = (
                <div>
                    <Loading/>
                </div>
            )
        } else loading_component = (<div />);

        if (!this.state.redirect) return (
            <div>
                <header>
                    <input type="file" name="avatar" onChange={this.fileChangedHandler} />
                    <button type="button" onClick={this.submit_loading} > Upload </button>
                    { loading_component }
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