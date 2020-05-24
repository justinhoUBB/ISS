import React from "react";
const axios = require('axios');





export function  register() {
    axios.post('http://localhost:8080/api/users/', {
        affiliation: this.state.affiliation,
        email: this.state.email,
        first_name: this.state.first_name,
        is_committee_member: this.state.is_committee_member,
        last_name: this.state.last_name,
        password: this.state.password
    }).then((response) => {
                if (!response.data.isError) {
                        localStorage.setItem("loggedInUser", this.state.email);
                        localStorage.setItem("isComitteeMember", this.state.is_committee_member);
                        this.props.history.push('/dashboard');
        }

    })

}



export function login() {
    axios.post('http://localhost:8080/api/login', { email: this.state.email, password: this.state.password })
        .then((response) => {
            if (!response.data.isError) {
                axios.get('http://localhost:8080/api/users/' + this.state.email).then((result) => {
                    if (!result.data.isError) {
                        localStorage.setItem("loggedInUser", this.state.email);
                        localStorage.setItem("isComitteeMember", this.state.is_committee_member);

                    }

                    });

            }
        })
}

export function logout() {
    return axios.post('http://localhost:8080/api/logout', { email: localStorage.loggedInUser })
        .then(() => {
            localStorage.removeItem("loggedInUser");
            this.setState({ success: true });
        })
        .catch(function(err) {
            alert(err);
        });
}
