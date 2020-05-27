import React from "react";
const axios = require('axios');

export function add_bid() {
    axios.post('http://localhost:8080/api/paper_bid/', {
        member_id: this.s
    })
    console.log(this.state.bidval);
    console.log(this.state.bidpaperid);
    console.log(this.state.bidconfid);
    console.log(this.state.biddate);
}