import React from 'react';
import { Switch, Route } from 'react-router-dom';

import Home from "../components/home";
import Success from "../components/Success";
import Results from "../components/Results";
import Auth_results from "../components/auth/Auth_results";
import Auth_personalBananas from "../components/auth/Auth_personal-bananas";
import About from "../components/About";

const Main = () => {
    return (
        <Switch>
            <Route exact path='/' component={Home}></Route>
            <Route exact path='/results' component={Results}></Route>
            <Route exact path='/success' component={Success}></Route>
            <Route exact path='/auth/results' component={Auth_results}></Route>
            <Route exact path='/auth/personalBananas' component={Auth_personalBananas}></Route>
            <Route exact path='/about' component={About}></Route>
        </Switch>
    );
}

export default Main;