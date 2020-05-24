import React, {Component} from "react";
import axios from 'axios';
import {ButtonComponent} from "@syncfusion/ej2-react-buttons";
const auth = require('../api/auth');

export default class Register extends  Component {
    constructor(props) {
        super(props);

        this.state = {
            email: "",
            password: "",
            first_name:"",
            last_name:"",
            affiliation:"",
            is_committee_member:false,
            loginErros: ""
        };
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.redirectToHome = this.redirectToHome.bind(this);
        this.register =auth.register.bind(this);
    }

    handleChange(event){
        this.setState({
            [event.target.name]: event.target.value
        });

    }

    redirectToHome() {
        this.props.history.push('/');
    }


    handleSubmit(event) {

        event.preventDefault();
        this.register();
    }

    render()  {
        return (
            <div>
                <h1> Conference Management System</h1><br/>

                <form onSubmit={this.handleSubmit}>
                    <input type ="text"
                           name = "first_name"
                           placeholder="first name"
                           value={this.state.first_name}
                           onChange={this.handleChange}
                           required/><br/><br/>

                    <input type ="text"
                           name = "last_name"
                           placeholder="last name"
                           value={this.state.last_name}
                           onChange={this.handleChange}
                           required/><br/><br/>

                    <input type ="text"
                           name="email"
                           placeholder ="email"
                           value={this.state.email}
                           onChange ={this.handleChange}
                           required/>
                    <br/><br/>

                    <input type ="password"
                           name = "password"
                           placeholder="password"
                           value={this.state.password}
                           onChange={this.handleChange}
                           required/><br/><br/>

                    <input type ="text"
                           name = "affiliation"
                           placeholder="affiliation"
                           value={this.state.affiliation}
                           onChange={this.handleChange}
                           required/><br/><br/>

                           Are you a comitte Member?
                    <br/>
                    <input type ="boolean"
                           name = "is_committee_member"
                           placeholder="Are you a Committe Member?"
                           value={this.state.is_committee_member}
                           onChange={this.handleChange}
                           required/><br/><br/>

                    <button type="submit"> Register </button>

                </form>
                <p> You already have an account? <ButtonComponent onClick={this.redirectToHome}> Log in !</ButtonComponent> </p>
            </div>

        );
    }

}