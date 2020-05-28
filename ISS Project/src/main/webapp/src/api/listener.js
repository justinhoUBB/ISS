const axios = require('axios');

function  addListener() {


    axios.post('http://localhost:8080/api/listeners/', {
        conference_id: +this.state.conference_id,
        member_id: +this.state.user_id,
        section_name:this.state.OneTopic
    }).then((response) => {
        if (!response.data.isError) {

        }

    })

}
module.exports={
    addListener
};