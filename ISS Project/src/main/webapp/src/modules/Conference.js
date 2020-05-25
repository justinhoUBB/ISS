import React,{Component} from 'react';
import '../App.css';
import {Table, Button} from 'reactstrap';
import { Link } from 'react-router-dom';
const paper = require('../api/paper');
const auth = require('../api/auth');
const conference = require('../api/conference')

export default class Conference extends Component {

    constructor(props) {
        super(props);
        this.state= {
            title:"",
            user_id:localStorage.loggedInUserID,
            publisher_id:localStorage.loggedInUserID,
            conference_id:this.props.location.state.conference_id,
            conference_topics: this.props.location.state.conference_topics,
            conference_description: this.props.location.state.conference_description,
            conference_number_of_rooms:this.props.location.state.conference_number_of_rooms,
            conference_number_of_seats_per_room:this.props.location.state.conference_number_of_seats_per_room,
            list_of_authors:"",
            keywords:"",
            paper_deadline:"",
            bid_deadline:"",
            starting_date:"",
            content:"",
            isShow: false,
            isShowDeadline:false,
            isShowAttendance:false

        };
        this.handleSubmit2 = this.handleSubmit2.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.register  = paper.addPaper.bind(this);
        this.submission  = paper.addPaperSubmission.bind(this);
        this.changeDeadlines = conference.updateConference.bind(this);
        this.attendConference = conference.attendConference.bind(this);
        this.handleAttend=this.handleAttend.bind(this);
        this.createText = this.createText.bind(this);
        this.createTextDeadline = this.createTextDeadline.bind(this);
        this.createTextAttendance = this.createTextAttendance.bind(this);
        this.logout = this.logout.bind(this);

    }

    createTextAttendance(){
        this.setState({isShowAttendance: true
        });
    }

    createTextDeadline(){
        this.setState({isShowDeadline: true
        });
    }

    createText(){
        this.setState({isShow: true
        });
    }

    handleChange(event){
        this.setState({
            [event.target.name]: event.target.value
        });

    }

    logout() {
        this.props.history.push('/logout');
    }

    handleSubmit(event) {

        event.preventDefault();
        this.register();
        this.submission();
    }

    handleAttend(event){
        event.preventDefault();
        this.attendConference();


    }
    handleSubmit2(event) {

        event.preventDefault();
        this.changeDeadlines();
    }


    render(){
        return (

            <div  id="conference" className ="conferenceList">
                <h1> Conference Management System</h1>

                <a href="http://localhost:3000/addconference"> Add conference</a>
                <a href="http://localhost:3000/dashboard"> Dashboard </a>
                <a href="http://localhost:3000/comdashboard"> Committee Dashboard </a>

                <br/>
                <br/>
                <h2> {(this.props.location.state.conference_topics)} Conference</h2>

                <p> {(this.props.location.state.conference_description)}</p>

                <p> Number of seats: {(this.props.location.state.conference_number_of_rooms * this.props.location.state.conference_number_of_seats_per_room)}</p>
                <p> Starting date: {(this.props.location.state.conference_starting_date)}</p>


                <p> Paper submission deadline: {(this.props.location.state.conference_paper_deadline)}</p>

                {!this.state.isShow && <button onClick = {this.createText}> Submit Paper</button>}

                {this.state.isShow &&
                <form onSubmit={this.handleSubmit}>
                   Title: <br/>
                    <input type ="text"
                           name = "title"
                           placeholder="title"
                           value={this.state.title}
                           onChange={this.handleChange}
                           required/><br/><br/>



                    List of authors:<br/>
                    <input type ="text"
                           name="list_of_authors"
                           placeholder ="list_of_authors"
                           value={this.state.list_of_authors}
                           onChange ={this.handleChange}
                           required/>
                    <br/><br/>

                    Keywords:<br/>
                    <input type ="text"
                           name = "keywords"
                           placeholder="keywords"
                           value={this.state.keywords}
                           onChange={this.handleChange}
                           required/><br/><br/>

                    Content:<br/>
                    <input type ="file"
                           name = "content"
                           placeholder="content"
                           value={this.state.content}
                           onChange={this.handleChange}
                           required/><br/>
                           <br/>


                    <button type="submit"> Add Paper </button><br/>
                </form>
                }
                {localStorage.isCommitteeMember === "true" && !this.state.isShowDeadline && <button onClick = {this.createTextDeadline}> Change Deadlines</button>}

                {this.state.isShowDeadline &&
                <form onSubmit={this.handleSubmit2}>
                    <br/>
                    Bid deadline: <br/>
                    <input type ="date"
                           name = "bid_deadline"
                           placeholder="title"
                           value={this.state.bid_deadline}
                           onChange={this.handleChange}
                           required/><br/><br/>



                    Paper submission deadline:<br/>
                    <input type ="date"
                           name="paper_deadline"
                           placeholder ="paper_deadline"
                           value={this.state.paper_deadline}
                           onChange ={this.handleChange}
                           required/>
                    <br/><br/>

                    Starting date:<br/>
                    <input type ="date"
                           name = "starting_date"
                           placeholder="starting_date"
                           value={this.state.starting_date}
                           onChange={this.handleChange}
                           required/><br/><br/>



                    <button type="submit"> Change deadlines </button><br/>
                </form>
                }

                {!this.state.isShowAttendance&& <form onSubmit={this.handleAttend}>
                    <br/>
                <button type="submit" onClick={this.createTextAttendance}> Attend Conference </button><br/>
                </form>}
                {
                    this.state.isShowAttendance && <div>
                        <p> Your attendance has been registered !</p>
                        <p> Thank you for choosing to attend {(this.props.location.state.conference_topics)} Conference .</p>
                    </div>
                }

                <br/>
                <br/>
                <Button className="buttonLogOut" onClick={this.logout}> Log out </Button>

            </div>
        );
}
};


