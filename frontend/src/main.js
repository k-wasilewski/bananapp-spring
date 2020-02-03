import React from 'react';
import { Switch, Route } from 'react-router-dom';

import Home from "./home";
import Success from "./success";
import Results from "./results";
import AuthResults from "./AuthResults";
import PersonalBananas from "./personalBananas";

const Main = () => {
    return (
        <Switch>
            <Route exact path='/' component={Home}></Route>
            <Route exact path='/results' component={Results}></Route>
            <Route exact path='/success' component={Success}></Route>
            <Route exact path='/auth/results' component={AuthResults}></Route>
            <Route exact path='/auth/personalBananas' component={PersonalBananas}></Route>
        </Switch>
    );
}

export default Main;