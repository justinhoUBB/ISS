import React, {Component} from "react";

const auth = require('../api/auth');


export default class Login extends  Component {
    constructor(props) {
        super(props);

        this.state = {
            email: "",
            password: "",
            submitted: false,
            loading: false,
            error: ''
        };
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.login = auth.login.bind(this);    }


    async handleSubmit(event){
        event.preventDefault();
        this.setState({ submitted: true });
        this.login();
    }


    handleChange(event){
        this.setState({
            [event.target.name]: event.target.value
        });

    }


    render()  {

        return (
                <div>
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
                </div>

        );
    }
}

