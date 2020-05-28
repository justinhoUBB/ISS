const axios = require('axios');

function  addSection() {
    console.log("a ajunss");
    axios.post('http://localhost:8080/api/sections/', {
        supervisor_id: +this.state.member_id,
        conference_id: this.state.conference_id,
        topics:this.state.Onetopic
    }).then((response) => {
        if (!response.data.isError) {

        }

    })

}


module.exports = {
    addSection
};