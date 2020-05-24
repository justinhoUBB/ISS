import React, {Component} from "react";
import {Button} from 'reactstrap';
const conference = require('../api/conference');

export default class Register extends  Component {
    constructor(props) {
        super(props);

        this.state = {
            description: "",
            topics: "",
            starting_date:new Date(2018, 11, 24),
            paper_deadline:new Date(2018, 11, 24),
            bid_deadline:new Date(2018, 11, 24),
            number_of_rooms:0,
            number_of_seats_per_room:0
        };
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.register  = conference.addConference.bind(this);
        this.redirectToDashboard = this.redirectToDashboard.bind(this);
        this.logout = this.logout.bind(this);
    }

    handleChange(event){
        this.setState({
            [event.target.name]: event.target.value
        });

    }

    redirectToDashboard() {
        this.props.history.push('/dashboard');
    }


    handleSubmit(event) {

        event.preventDefault();
        this.register();
    }


    logout() {
        this.props.history.push('/logout');
    }

    render()  {
        return (
            <div className="conference">
                <h1> Conference Management System</h1>
                <a href="http://localhost:3000/dashboard"> Dashboard </a>
                <a href="http://localhost:3000/comdashboard"> Committee Dashboard </a>

                <br/><br/>

                <form onSubmit={this.handleSubmit}>

                    Conference description: <br/>
                    <input type ="text"
                           name = "description"
                           placeholder="description"
                           value={this.state.description}
                           onChange={this.handleChange}
                           required/><br/><br/>

                           Topics: <br/>
                    <input type ="text"
                           name = "topics"
                           placeholder="topics"
                           value={this.state.topics}
                           onChange={this.handleChange}
                           required/><br/><br/>


                           Starting date:<br/>
                    <input type ="date"
                           name="starting_date"
                           placeholder ="starting date"
                           value={this.state.starting_date}
                           onChange ={this.handleChange}
                           required/>
                    <br/><br/>

                            Papers deadline:<br/>
                    <input type ="date"
                           name = "paper_deadline"
                           placeholder="paper deadline"
                           value={this.state.paper_deadline}
                           onChange={this.handleChange}
                           required/><br/><br/>

                           Bid deadline:<br/>
                    <input type ="date"
                           name = "bid_deadline"
                           placeholder="bid deadline"
                           value={this.state.bid_deadline}
                           onChange={this.handleChange}
                           required/><br/>

                    <br/>
                        Number of rooms:<br/>
                    <input type ="integer"
                           name = "number_of_rooms"
                           placeholder="Nr of rooms"
                           value={this.state.is_committee_member}
                           onChange={this.handleChange}
                           required/><br/>


                    <br/>
                        Number of seats per room:<br/>
                    <input type ="integer"
                           name = "number_of_seats_per_room"
                           placeholder="Nr of seats per room"
                           value={this.state.number_of_seats_per_room}
                           onChange={this.handleChange}
                           required/><br/><br/>

                    <button type="submit"> Add Conference </button><br/>
                    <Button className="buttonLogOut"  onClick={this.logout}> Log out </Button>
                </form>
            </div>

        );
    }

}