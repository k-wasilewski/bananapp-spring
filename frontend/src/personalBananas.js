import React from 'react';
import {MyField} from "./myField";
import {isEmail} from "@formiz/validations";
import axios from "axios";
import Gallery from 'react-grid-gallery';

class PersonalBananas extends React.Component {

    constructor(){
        super();
        this.state = {
            username: 0,
            images: 0,
            pred: 0
        }
    }

    componentDidMount() {
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
                });
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
                $this.setState({
                    pred: response.data
                });
            }
        });
    }

    render() {
        var $this = this;
        //img src={people[i]} works
        const IMAGES = [];


        const people = this.state.images;
        let peopleToReturn = [];
        const peopleLis = () => {
            for (let i = 0; i < people.length; i++) {
                var path = people[i]
                this.getImgPred(path);
                console.log("pred:"+$this.state.pred);

                IMAGES.push({
                    src: process.env.PUBLIC_URL +`/${path}`,
                    thumbnail: process.env.PUBLIC_URL +`/${path}`,
                    thumbnailWidth: 320,
                    thumbnailHeight: 320,
                    caption: $this.state.pred
                })
            }
            return IMAGES;
        };

        return (
            <div>
                <Gallery images={peopleLis()}/>
            </div>
        )
    }
}

export default PersonalBananas;