import React from 'react';
import { Switch, Route } from 'react-router-dom';

import Home from "./home";
import Results from "./results";
import Login from "./login";
import NameForm from "./nameForm";
import {MyForm} from "./myForm";

const Main = () => {
    return (
        <Switch>
            <Route exact path='/' component={Home}></Route>
            <Route exact path='/login1' component={NameForm}></Route>
            <Route exact path='/login2' component={MyForm}></Route>
            <Route exact path='/results' component={Results}></Route>
        </Switch>
    );
}

export default Main;