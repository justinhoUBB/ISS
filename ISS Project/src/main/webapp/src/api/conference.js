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

module.exports = {
     addConference
};
