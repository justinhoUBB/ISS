import React,{Component} from 'react';
import './App.css';
import {BrowserRouter, Switch, Route} from "react-router-dom";
import Home from './modules/Home';
import Dashboard from './modules/Dashboard'
import Register from './modules/Register'
import AddConference from "./modules/AddConference";
import Conference from "./modules/Conference";

export default class App extends Component{



    render(){
        const {Provider, Consumer} = React.createContext("");
        return (
            <div className="app">
                <BrowserRouter>
                    <Switch>
                        <Route exact path = "/" component = {Home}/>
                        <Route exact path = "/register" component = {Register}/>
                        <Route exact path ="/dashboard" component={Dashboard}/>
                        <Route exact path ="/addconference" component={AddConference}/>
                        <Route exact path="/conferences/:id" component={Conference}/>
                    </Switch>
                </BrowserRouter>
            </div>
        );
    }

}