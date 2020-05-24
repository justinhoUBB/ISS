const axios = require('axios');

function  register() {
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

{/*
   Nu vrea sa meargă săracia de login, da' cred că problema e din controller, nu face Spring mapping-urile pe
   /login, /logout, /api/users/{email} sau /api/users/{id}
 */}

function login() {
    axios.post('http://localhost:8080/api/login', { email: this.state.email, password: this.state.password })
        .then((response) => {
            if (!response.data.isError) {
                axios.get('http://localhost:8080/api/users/' + this.state.email)
                    .then((result) => {

                        localStorage.setItem("loggedInUser", this.state.email);
                        localStorage.setItem("isComitteeMember", this.state.is_committee_member);

                    });

            }
        })
}

function logout() {
    return axios.post('http://localhost:8080/api//logout', { email: localStorage.loggedInUser })
        .then(() => {
            localStorage.removeItem("loggedInUser");
            this.setState({ success: true });
        })
        .catch(function(err) {
            alert(err);
        });
}


module.exports = {
    register,login,logout
};
