import React,{Component} from 'react';
import '../App.css';
import {Table, Button} from 'reactstrap';
import ApiService from '../api/file'
import { Link } from 'react-router-dom';
const paper = require('../api/paper');
const auth = require('../api/auth');
const conference = require('../api/conference')

export default class Paper extends Component {



    constructor(props) {
        super(props);

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
                <h2> {(this.props.location.state.title)} Paper</h2>

                <p> {(this.props.location.state.content)} </p>

                <a href="http://localhost:3000/comdashboard"> Go back </a>
                <br/>
                <br/>
                <Button className="buttonLogOut" onClick={this.logout}> Log out </Button>

            </div>
        );

    }

};


