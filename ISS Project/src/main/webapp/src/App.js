import React,{Component} from 'react';
import './App.css';
import {BrowserRouter, Switch, Route} from "react-router-dom";
import { Link, useHistory } from "react-router-dom";
import Home from './modules/Home';
import Dashboard from './modules/Dashboard'
import Register from './modules/Register'
import AddConference from "./modules/AddConference";
import Conference from "./modules/Conference";
import Logout from "./modules/Logout";
import {EnsureCorrectUserLoggedIn, EnsureLoggedIn} from "./api/check-user";
import CommitteeDashBoard from "./modules/CommitteeDashBoard";
import ReviewPapers from "./modules/ReviewPapers";
import Paper from "./modules/Paper";


export default class App extends Component{



    render(){
        return (
            <div className="app">
                <BrowserRouter>
                    <Switch>
                        <Route exact path = "/" component = {Home}/>
                        <Route exact path = "/logout" component = {Logout}/>
                        <Route exact path = "/register" component = {Register}/>
                        <Route exact path ="/dashboard"  render={(props) => EnsureLoggedIn(Dashboard, "/dashboard", props)}/>
                        <Route exact path ="/addconference" render={(props) => EnsureCorrectUserLoggedIn(AddConference, "/addconference", props)}/>
                        <Route exact path ="/comdashboard" render={(props) => EnsureCorrectUserLoggedIn(CommitteeDashBoard, "/comdashboard", props)}/>
                        <Route exact path ="/reviewpaper" render={(props) => EnsureCorrectUserLoggedIn(ReviewPapers, "/reviewpaper", props)}/>
                        <Route exact path="/conferences/:id" render={(props) => EnsureLoggedIn(Conference, "/conferences/:id", props)}/>
                        <Route exact path="/papers/:id" render={(props) => EnsureLoggedIn(Paper, "/papers/:id", props)}/>
                    </Switch>
                </BrowserRouter>
            </div>
        );
    }

}