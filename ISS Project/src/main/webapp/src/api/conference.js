const axios = require('axios');

function  addConference() {
    axios.post('http://localhost:8080/api/conferences/', {
        description: this.state.description,
        topics: this.state.topics,
        starting_date: this.state.starting_date,
        paper_deadline: this.state.paper_deadline,
        bid_deadline: this.state.bid_deadline,
        number_of_rooms: this.state.number_of_rooms,
        number_of_seats_per_room: this.state.number_of_seats_per_room
    }).then((response) => {
        if (!response.data.isError) {
            this.state.conference_id = response.data.id;
        }

    })

}


function  addConferenceCommitteeMember() {
    axios.post('http://localhost:8080/api/conference_committee/', {
        conference_id: +this.state.conference_id,
        user_id: this.state.member_id
    }).then((response) => {
        if (!response.data.isError) {

        }

    })

}

function  addConferenceCommittee() {
    axios.post('http://localhost:8080/api/conference_committee/', {
        conference_id: +this.state.conference_id,
        user_id: this.state.user_id
    }).then((response) => {
        if (!response.data.isError) {

        }

    })

}

function checkConferenceCommittee(){

   axios.post('http://localhost:8080/api/conference_committee/'+ this.state.conference_id, {
       user_id: localStorage.loggedInUserID,
       conference_id: this.state.conference_id
   }).then((response) =>{
       console.log(response.data.status);
       localStorage.setItem("isUserPartOfCom", response.data.status);
    });


}


function checkConferenceCommitteePaper(conference_id){

    axios.post('http://localhost:8080/api/conference_committee/'+ conference_id, {
        user_id: localStorage.loggedInUserID,
        conference_id: conference_id
    }).then((response) =>{
        localStorage.setItem("isUserPartOfCom", response.data.status);
    });


}

function getBidDeadline(conference_id){

    let deadline = "";
    axios.post('http://localhost:8080/api/conferences_bid/'+ conference_id, {
        conference_id: conference_id
    }).then((response) =>{
       setTimeout( () => {deadline = response.data.bid_deadline},2000);
        return deadline;
    });


}

function updateConference(){
    axios.put('http://localhost:8080/api/conferences/'+ this.state.conference_id, {
        description: "",
        topics: "",
        starting_date: this.state.starting_date,
        paper_deadline: this.state.paper_deadline,
        bid_deadline: this.state.bid_deadline,
        number_of_rooms: 0,
        number_of_seats_per_room: 0
    }).then((response) =>{
        this.props.history.push('/dashboard');
    });

}

function attendConference(){
    axios.post('http://localhost:8080/api/attendances/', {
        user_id: this.state.user_id,
        conference_id: this.state.conference_id

    });

}

module.exports = {
     addConference, updateConference,attendConference,
    addConferenceCommittee,addConferenceCommitteeMember,checkConferenceCommittee,
    checkConferenceCommitteePaper,getBidDeadline
};
