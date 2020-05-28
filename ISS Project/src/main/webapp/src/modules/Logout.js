import React, {Component} from "react";
import {ButtonComponent} from "@syncfusion/ej2-react-buttons";
import {Button} from "reactstrap";
const authApi = require('../api/auth');



export default class Logout  extends  Component {
    state = {
        success: false,
        username: ''
    };

    componentDidMount() {
        if (localStorage.loggedInUser) {
            this.setState({username: localStorage.getItem("loggedInUser")});
            (authApi.logout.bind(this))()
                .then (() => {setTimeout(() => {
                    this.props.history.push('/');
                }, 700)});
        }
    }

    render() {
        return (
            <div className="container" style={{height: '100vh'}}>
                <div className="row align-items-center justify-content-center" style={{height: '100vh'}}>
                    <h3>Logging out {this.state.username}...</h3>
                </div>
            </div>
        );
    }
}


