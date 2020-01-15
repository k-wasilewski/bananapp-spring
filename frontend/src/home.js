import React, {Component} from 'react';
import logo from './logo.svg';
import './App.css';
import {
    BrowserRouter as Router,
    Link,
} from 'react-router-dom';

class Home extends Component {

    state =  {
        selectedFile: null,
        imagePreviewUrl: null
    };

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

        request.onreadystatechange = function() {
            if (this.readyState === 4 && this.status === 200) {
                alert('Uploaded!');
            }
        };
        request.open("POST", "https://us-central1-tutorial-e6ea7.cloudfunctions.net/fileUpload", true);
        request.send(fd);
    }

    componentDidMount() {
        setInterval(this.hello, 250);
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
        return (
            <div className="App">
                <header className="App-header">
                    <img src={logo} className="App-logo" alt="logo"/>
                    <h1 className="App-title">{this.state.message}</h1>
                    <Link to="/anotherr">
                        <button variant="outlined">
                            another by React
                        </button>
                    </Link>
                    <Router>
                        <a href={'http://localhost:8082/hello'}>
                            <button variant="outlined">
                                Google App
                            </button>
                        </a>
                    </Router>
                    <input type="file" name="avatar" onChange={this.fileChangedHandler} />
                    <button type="button" onClick={this.submit} > Upload </button>
                    { $imagePreview }
                </header>
                <p className="App-intro">
                    To get started, edit <code>src/App.js</code> and save to reload.
                </p>
            </div>
        );
    }
}

export default Home;