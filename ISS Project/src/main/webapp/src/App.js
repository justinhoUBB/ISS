import React,{Component} from 'react';
import './App.css';
import {BrowserRouter, Switch, Route} from "react-router-dom";
import Home from './modules/Home';
import Dashboard from './modules/Dashboard'
import Register from './modules/Register'

export default class App extends Component{
    render(){
        return (
            <div className="app">
                <BrowserRouter>
                    <Switch>
                        <Route exact path = "/" component = {Home}/>
                        <Route exact path = "/register" component = {Register}/>
                        <Route exact path ="/dashboard" component={Dashboard}/>
                    </Switch>
                </BrowserRouter>
            </div>
        );
    }
};
