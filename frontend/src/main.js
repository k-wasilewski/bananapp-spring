import React from 'react';
import { Switch, Route } from 'react-router-dom';

import Home from "./home";
import Success from "./Success";
import Results from "./Results";
import Auth_results from "./Auth_results";
import Auth_personalBananas from "./Auth_personal-bananas";

const Main = () => {
    return (
        <Switch>
            <Route exact path='/' component={Home}></Route>
            <Route exact path='/results' component={Results}></Route>
            <Route exact path='/success' component={Success}></Route>
            <Route exact path='/auth/results' component={Auth_results}></Route>
            <Route exact path='/auth/personalBananas' component={Auth_personalBananas}></Route>
        </Switch>
    );
}

export default Main;