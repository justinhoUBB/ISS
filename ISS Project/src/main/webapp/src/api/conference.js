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
            this.props.history.push('/dashboard');
        }

    })

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
     addConference, updateConference,attendConference
};
