import React, {Component} from "react";
import {Button} from 'reactstrap';
const conference = require('../api/conference');

export default class Register extends  Component {
    constructor(props) {
        super(props);

        this.state = {
            items:[],
            user_id: localStorage.loggedInUserID,
            member_id:0,
            description: "",
            topics: "",
            starting_date:new Date(2018, 11, 24),
            paper_deadline:new Date(2018, 11, 24),
            bid_deadline:new Date(2018, 11, 24),
            number_of_rooms:0,
            number_of_seats_per_room:0,
            submitted:false,
            isShow: false,
            submittedMember:false
        };
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleSubmitMember = this.handleSubmitMember.bind(this);

        this.handleChange = this.handleChange.bind(this);
        this.handleChangeMember = this.handleChangeMember.bind(this);
        this.register  = conference.addConference.bind(this);
        this.registerCommittee = conference.addConferenceCommittee.bind(this);
        this.registerCommitteeMember = conference.addConferenceCommitteeMember.bind(this);
        this.redirectToDashboard = this.redirectToDashboard.bind(this);
        this.logout = this.logout.bind(this);
        this.createText = this.createText.bind(this);
    }
    componentDidMount() {
        fetch('http://localhost:8080/api/users')
            .then(res => res.json())
            .then(json => {
                this.setState({
                    isLoaded: true,
                    items: json,

                })
            });
    }

    handleChange(event){
        this.setState({
            [event.target.name]: event.target.value
        });

    }

    handleChangeMember(id){
        this.setState({
            member_id: id,
            submittedMember:false
        });

    }
    handleSubmitMember(event){
        event.preventDefault();
        this.setState({submittedMember:true});
        this.registerCommitteeMember();


    }

    redirectToDashboard() {
        this.props.history.push('/dashboard');
    }


    handleSubmit(event) {

        event.preventDefault();
        this.register();
        this.setState({submitted:true});
        setTimeout( () => {this.registerCommittee();},2000);
    }


    logout() {
        this.props.history.push('/logout');
    }
    createText(){
        this.setState({isShow: true
        });
    }

    render()  {
        return (
            <div className="conference">
                <h1> Conference Management System</h1>
                <a href="http://localhost:3000/dashboard"> Dashboard </a>
                <a href="http://localhost:3000/comdashboard"> Committee Dashboard </a>

                <br/><br/>

                {!this.state.submitted && <form onSubmit={this.handleSubmit}>

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
                    <button type="submit"> Add Conference </button><br/> </form>}
                    {this.state.submitted &&
                    <ul className="usersUL" >
                        <h2> Please choose your conference's committee members</h2><br/>
                        {this.state.submittedMember && <p> Committee member added successfully !</p>}
                        {this.state.items.map(item=>(

                            <li key={item.id}>

                                <form onSubmit={this.handleSubmitMember}>
                                <b>User</b> {item.first_name} {item.last_name}    <br/>
                                <b>Email</b> {item.email}  <br/>
                                <b>Affiliation</b> {item.affiliation}  <br/>

                                <input type="checkbox" onChange={() => this.handleChangeMember(item.id)}  value = {this.state.member_id} name="randi"/>  <label htmlFor="randi"> Add to members </label><br/><br/>
                                <button type="submit"> Add  </button>


                                </form>

                                <br/><br/>
                            </li>

                            )) }
                    </ul>

                    }
                    <Button className="buttonLogOut"  onClick={this.logout}> Log out </Button>
            </div>

        );
    }

}