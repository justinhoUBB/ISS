import React,{Component} from 'react';
import '../App.css';
import {Table, Button} from 'reactstrap';
import ApiService from '../api/file'
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
            content:null,
            isShow: false,
            isShowDeadline:false,
            isShowAttendance:false

        };
        this.handleSubmit2 = this.handleSubmit2.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleChangeFile = this.handleChangeFile.bind(this);
        this.register  = paper.addPaper.bind(this);
        this.submission  = paper.addPaperSubmission.bind(this);
        this.changeDeadlines = conference.updateConference.bind(this);
        this.attendConference = conference.attendConference.bind(this);
        this.handleAttend=this.handleAttend.bind(this);
        this.createText = this.createText.bind(this);
        this.createTextDeadline = this.createTextDeadline.bind(this);
        this.createTextAttendance = this.createTextAttendance.bind(this);
        this.logout = this.logout.bind(this);
        this.getCurrentDate =this.getCurrentDate.bind(this);
    }


    getCurrentDate(separator='-'){

        let newDate = new Date();
        let date = newDate.getDate();
        let month = newDate.getMonth() + 1;
        let year = newDate.getFullYear();

        return `${year}${separator}${month<10?`0${month}`:`${month}`}${separator}${date}`
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
        setTimeout( () => {this.submission();},2000);
    }

    handleAttend(event){
        event.preventDefault();
        this.attendConference();


    }
    handleSubmit2(event) {

        event.preventDefault();
        this.changeDeadlines();
    }

    handleChangeFile(event){
        this.setState({
            content: event.target.files[0]

        })
    }
    changeFile(e)
    {
        this.readSingleFile(e);
    }
    readSingleFile(e) {
        let file = e.target.files[0];
        if (!file) {
            return;
        }
        let reader = new FileReader();
        reader.onload = (e) =>{
            console.log("nuuu  "+e);
            let contents = e.target.result;
            this.displayContents(contents);
        };
        reader.readAsText(file);
    }

    displayContents(contents) {
        let element = document.getElementById('file-content');
        this.state.content=contents;
        element.textContent = contents;
    }



    render(){

        return (

            <div  id="conference" className ="conferenceList">
                <h1> Conference Management System</h1>

                {localStorage.isCommitteeMember === "true" && <a href="http://localhost:3000/addconference"> Add conference</a>}
                <a href="http://localhost:3000/dashboard"> Dashboard </a>
                {localStorage.isCommitteeMember === "true" && <a href="http://localhost:3000/comdashboard"> Committee Dashboard </a>}

                <br/>
                <br/>
                <h2> {(this.props.location.state.conference_topics)} Conference</h2>

                <p> {(this.props.location.state.conference_description)}</p>

                <p> Number of seats: {(this.props.location.state.conference_number_of_rooms * this.props.location.state.conference_number_of_seats_per_room)}</p>
                <p> Starting date: {(this.props.location.state.conference_starting_date)}</p>

                {(Date.parse(this.getCurrentDate()) <= Date.parse(this.props.location.state.conference_paper_deadline)) &&
                <p> Paper submission deadline: {(this.props.location.state.conference_paper_deadline)}</p>}


                {(Date.parse(this.getCurrentDate()) > Date.parse(this.props.location.state.conference_paper_deadline)) && <p> Papers can no longer be submitted at this date for this conference.</p>}
                {(Date.parse(this.getCurrentDate()) <= Date.parse(this.props.location.state.conference_paper_deadline)) && !this.state.isShow && <button onClick = {this.createText}> Submit Paper</button>}

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
                           id="file-input"
                           placeholder="content"
                           value={this.state.content}
                           onChange={(e)=>{
                               console.log("daaa   "+e.target);
                               this.handleChange(e);
                               this.changeFile(e);
                           }}
                           required/><br/>
                           <br/>
                           <h3>Content of file</h3>
                    <button type="submit"> Add Paper </button><br/>
                            <pre id="file-content"style={{textAlign: 'left'}} ></pre>



                </form>
                }
                { (Date.parse(this.getCurrentDate()) <= Date.parse(this.props.location.state.conference_starting_date)) && localStorage.isCommitteeMember === "true" && !this.state.isShowDeadline && <button onClick = {this.createTextDeadline}> Change Deadlines</button>}

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

                {(Date.parse(this.getCurrentDate()) <= Date.parse(this.props.location.state.conference_starting_date))  &&!this.state.isShowAttendance&& <form onSubmit={this.handleAttend}>
                    <br/>
                <button type="submit" onClick={this.createTextAttendance}> Attend Conference </button><br/>
                </form>}
                {
                    this.state.isShowAttendance && <div>
                        <p> Your attendance has been registered !</p>
                        <p> Thank you for choosing to attend {(this.props.location.state.conference_topics)} Conference .</p>
                    </div>
                }


                {(Date.parse(this.getCurrentDate()) > Date.parse(this.props.location.state.conference_starting_date))
                    &&
                    <div>
                        <p> This conference has already been held.</p>
                    </div>

                }


                <br/>
                <br/>
                <Button className="buttonLogOut" onClick={this.logout}> Log out </Button>

            </div>
        );

    }

};


