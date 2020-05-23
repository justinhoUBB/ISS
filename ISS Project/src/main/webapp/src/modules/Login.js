import React, {Component} from "react";
import axios from 'axios';

export default class Login extends  Component {
    constructor(props) {
        super(props);

        this.state = {
            email: "",
            password: "",
            loginErros: ""
        };
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(event){
        this.setState({
            [event.target.name]: event.target.value
        });


    }

    handleSuccessfulAuth(event){

        this.props.history.push('/dashboard');
    };

    handleSubmit(event) {
        const {email, password} = this.state;

        axios.get('http://localhost:8080/api/users/1').then(result => {
                    this.handleSuccessfulAuth(result.data);
                }, {withCredentials: false})
        event.preventDefault();
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