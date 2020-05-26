import React, {Component} from "react";
import {Button} from "reactstrap";
import { useHistory } from 'react-router';
import {Link} from "react-router-dom";
const auth = require('../api/auth');


export default class Login extends  Component {
    constructor(props) {

        super(props);

        this.state = {

            email: "",
            password: "",
            submitted: false,
            loading: true,
            redirect: false,
            error: ''
        };
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.login = auth.login.bind(this);
        this.logout = this.logout.bind(this);}


    async handleSubmit(event) {
        event.preventDefault();
        this.setState({submitted: true});
        this.login();
        this.setState({ loading: false,redirect: true});

    }


    redirectToRegister() {
        this.props.history.push('/register');
    }

    handleChange(event){
        this.setState({
            [event.target.name]: event.target.value

        });


    }
    logout() {
        this.props.history.push('/logout');
    }


    render()  {
        if (!localStorage.loggedInUser){




        return (
        <div>
                 {!this.state.submitted &&
                <div className="login">
                    <form onSubmit={this.handleSubmit}>
                    <input type ="text"
                           name="email"
                           placeholder ="email"
                           value={this.state.email}
                           onChange ={this.handleChange}
                           required/>
                <br/>

                       <input type ="password"
                              name = "password"
                              placeholder="password"
                              value={this.state.password}
                              onChange={this.handleChange}
                              required/><br/><br/>
                        <button type="submit"> Log in</button>

                </form>

                 <p> You don't have an account? <a href="http://localhost:3000/register"> Sign up !</a> </p>
                    </div>
                 }


                 { this.state.submitted &&  <div className="conferenceList"> <h2> Welcome, {this.state.email}!</h2>
                     <a href="http://localhost:3000/dashboard"> Dashboard </a><br/>
                     <Button className="buttonLogOut" > Log out </Button>
                 </div>

                 }

        </div>

        );

        }
        else{
            return(

                <div className="login" style={{height: '100vh'}}>
                    <div className="row align-items-center" style={{height: '100vh'}}>
                        <div className="col-11" style={{textAlign: 'center'}}>

                            <a href="http://localhost:3000/dashboard"> Dashboard </a>
                            <h2>You are already logged in!</h2>
                        </div>
                    </div>
                </div>
            );
        }


        }
}

