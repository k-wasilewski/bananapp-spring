import React from 'react';
import {MyField} from "./myField";
import {isEmail} from "@formiz/validations";
import axios from "axios";
import Gallery from 'react-grid-gallery';
import {Link} from "react-router-dom";

class PersonalBananas extends React.Component {

    constructor(){
        super();
        this.state = {
            username: 0,
            images: 0,
            pred: 0,
            IMAGES: []
        }
        this.imgList = this.imgList.bind(this)
    }

    componentDidMount() {
        let $this = this;

        axios.get('http://localhost:8081/auth/username')
            .then((response) => {
                let uname = response.data;
                this.setState({
                    username: uname
                });
            })

        axios.get('http://localhost:8081/auth/files')
            .then((response) => {
                let imgs = response.data;
                console.log("images response: "+imgs);
                this.setState({
                        images: imgs
                    },
                    function() { $this.imgList() }
                );
            })
    }

    getImgPred = (path) => {
        var username = this.state.username;
        var $this = this;

        let regex = new RegExp(username+'\/(.*?)$');
        let imgRegex= /{username}\/(.*?)$/;

        let filename = regex.exec(path);
        console.log("filename at front:"+filename[1]);
        console.log("regex1:"+imgRegex+", regex2:"+regex);

        axios.post('http://localhost:8081/auth/imgpred',
            "filename=" + filename[1]
        ).then(function (response) {
            console.log("response at front (get img prediction):"+response.data);
            if (response.status === 200) {
                const prediction = response.data;

                const scoreRegex = /score:(.*?),/;
                const accRegex = /acc:(0\.\d\d)/;

                const score = scoreRegex.exec(prediction);
                const accuracy = accRegex.exec(prediction);

                var days = '[error]';
                if (score[1]==1.0) days="1 day";
                else if (score[1]==2.0) days="2 days";
                else if (score[1]==3.0) days="3 days";
                else if (score[1]==4.0) days="4 days";
                else if (score[1]==5.0) days="5 days";
                else if (score[1]==6.0) days="6 days";
                else if (score[1]==7.0) days="7 days";

                $this.setState({
                    pred: days+" for "+Number((accuracy[1]*100).toFixed(2)) +"%"
                }, function() { $this.IMAGESpush(path) } );
            }
        });
    }

    IMAGESpush = (path) => {
        var $this = this;
        const newIMAGE = {
            src: process.env.PUBLIC_URL +`/${path}`,
            thumbnail: process.env.PUBLIC_URL +`/${path}`,
            caption: $this.state.pred,
            tags: [{value: $this.state.pred, title: $this.state.pred}]
        };
        this.setState({IMAGES: this.state.IMAGES.concat(newIMAGE)});
    }

    imgList = () => {
        var $this = this;
        const IMAGES = [];
        const imgpaths = this.state.images;
        console.log("images from imgLis():"+imgpaths);

        for (let i = 0; i < imgpaths.length; i++) {
            var path = imgpaths[i]
            this.getImgPred(path);
            console.log("pred:"+$this.state.pred);
        }
        console.log("IMAGES from imgLis():"+IMAGES);
    };

    render() {

        return (
            <div className="App">
                <Gallery images={this.state.IMAGES} />
                <header className="App-header">
                    <Link to="/success">
                        <button variant="outlined">
                            back
                        </button>
                    </Link>
                </header>
            </div>
        )
    }
}

export default PersonalBananas;