import React from 'react';
import { Switch, Route } from 'react-router-dom';

import Another from './another';
import Home from "./home";

const Main = () => {
    return (
        <Switch>
            <Route exact path='/' component={Home}></Route>
            <Route exact path='/anotherr' component={Another}></Route>
        </Switch>
    );
}

export default Main;