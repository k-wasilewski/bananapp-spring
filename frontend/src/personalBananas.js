import React from 'react';
import {MyField} from "./myField";
import {isEmail} from "@formiz/validations";
import axios from "axios";

class PersonalBananas extends React.Component {

    constructor(){
        super();
        this.state = {
            username: 0,
            images: 0
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

    render() {
        var $this = this;
        //img src={people[i]} works


        const people = this.state.images;
        let peopleToReturn = [];
        const peopleLis = () => {
            for (let i = 0; i < people.length; i++) {
                var path = people[i];
                peopleToReturn.push(<li> <img src={process.env.PUBLIC_URL +`/${path}`}></img> </li>);
            }
            return peopleToReturn;
        };

        return (
            <div>
                <ul>{peopleLis()}</ul>
                {$this.state.images[0]}
            </div>
        )
    }
}

export default PersonalBananas;