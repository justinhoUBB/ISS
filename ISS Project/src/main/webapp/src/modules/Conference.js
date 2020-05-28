import React,{Component} from 'react';
import '../App.css';
import {Table, Button} from 'reactstrap';
import $ from "jquery";
import ApiService from '../api/file'
import { Link } from 'react-router-dom';
import { useAlert } from 'react-alert'
const paper = require('../api/paper');
const auth = require('../api/auth');
const conference = require('../api/conference')
const listener = require('../api/listener');

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
            is_user_committee_member:false,
            paper_deadline:"",
            bid_deadline:"",
            starting_date:"",
            userID:0,
            content:null,
            isShow: false,
            isShowDeadline:false,
            isShowAttendance:false,
            papers: [],
            isLoaded : false,
            checkboxes: [],
            OneTopic:"",
            isShowForSpeaker:false,
        };

        this.handleSubmit2 = this.handleSubmit2.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleChangeFile = this.handleChangeFile.bind(this);
        this.register  = paper.addPaper.bind(this);
        this.submission  = paper.addPaperSubmission.bind(this);
        this.changeDeadlines = conference.updateConference.bind(this);
        this.attendConference = conference.attendConference.bind(this);
        this.isPartOfCommittee = conference.checkConferenceCommittee.bind(this);
        this.handleAttend=this.handleAttend.bind(this);
        this.createText = this.createText.bind(this);
        this.createTextDeadline = this.createTextDeadline.bind(this);
        this.createTextAttendance = this.createTextAttendance.bind(this);
        this.createTextSpeaker=this.createTextSpeaker.bind(this);
        this.logout = this.logout.bind(this);
        this.getCurrentDate =this.getCurrentDate.bind(this);
        this.checkUserCM = this.checkUserCM.bind(this);
        this.addListener=listener.addListener.bind(this);
        this.createSections=this.createSections.bind(this);
    }

    handleListenerMember() {
        let index=this.state.checkboxes.indexOf(true);
        this.state.OneTopic=this.state.sections[index];
        this.addListener();
    }
    onChangeBoxes(e, changedIndex) {
        const { checked } = e.target;

        this.setState(state => ({
            checkboxes: state.checkboxes.map((_, i) => i === changedIndex ? checked : false),
        }));
    }

    componentDidMount() {
        this.checkUserCM();
        setTimeout( () => {this.setState({ is_user_committee_member:localStorage.isUserPartOfCom});
            localStorage.removeItem("isUserPartOfCom");},100);
        fetch('http://localhost:8080/api/papersconf/' + this.state.conference_id)
            .then(res => res.json())
            .then(json => {
                this.setState({
                    isLoaded: true,
                    papers: json,
                });
                console.log(this.state.papers);
            }
            )
    }


    getCurrentDate(separator='-'){

        let newDate = new Date();
        let date = newDate.getDate();
        let month = newDate.getMonth() + 1;
        let year = newDate.getFullYear();

        return `${year}${separator}${month<10?`0${month}`:`${month}`}${separator}${date}`
    }

    checkUserCM(){
        this.isPartOfCommittee();
    }

    createTextAttendance(){
        this.setState({isShowAttendance: true
        });
    }
    createTextSpeaker(){
        this.setState({isShowForSpeaker: true
        });
    }

    createTextDeadline(){
        this.setState({isShowDeadline: true
        });
        this.checkUserCM();
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
        const {papers} = this.state
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

                <h3>Papers:</h3>
                <ul>
                    {papers.map(paper =>(
                        <li key={paper.id}>
                            <p>Paper title: {paper.title}</p>
                            <p>List of authors: {paper.list_of_authors}</p>
                            <p>Keywords: {paper.keywords}</p>
                            <br/><br/><br/>
                        </li>
                        ))}
                </ul>
                <ul>
                </ul>
                {(Date.parse(this.getCurrentDate()) <= Date.parse(this.props.location.state.conference_paper_deadline)) &&
                <p> Paper submission deadline: {(this.props.location.state.conference_paper_deadline)}</p>}

                {localStorage.isCommitteeMember === "true" && this.state.is_user_committee_member === "true"  && <p> You can't upload papers at one of the conferences you are part of the PC if you are from the steering committee.</p>}
                {(Date.parse(this.getCurrentDate()) > Date.parse(this.props.location.state.conference_paper_deadline)) && <p> Papers can no longer be submitted at this date for this conference.</p>}
                {(localStorage.isCommitteeMember === "false" || this.state.is_user_committee_member === "false") && (Date.parse(this.getCurrentDate()) <= Date.parse(this.props.location.state.conference_paper_deadline)) && !this.state.isShow && <button onClick = {this.createText}> Submit Paper</button>}

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
                { (Date.parse(this.getCurrentDate()) <= Date.parse(this.props.location.state.conference_starting_date)) && localStorage.isCommitteeMember === "true" && !this.state.isShowDeadline &&  this.state.is_user_committee_member === "true"  && <button onClick = {this.createTextDeadline}> Change Deadlines</button>}
                    <br/><br/><button onClick = {this.createTextSpeaker}>Add Presentation</button>
                {this.state.isShowForSpeaker && <h2>
                    <h5>Hello speaker</h5>
                    <h5>here you can insert your presentation</h5>
                    <input type ="file" placeholder="Prezentation"/>
                    <button onClick={this.callAlert}>Upload</button>
                </h2>}
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

                {(Date.parse(this.getCurrentDate()) <= Date.parse(this.props.location.state.conference_starting_date))  &&!this.state.isShowAttendance&&
                <form onSubmit={this.handleAttend}>
                    <br/>
                    <button type="submit" onClick={this.createTextAttendance}> Attend Conference </button><br/>
                </form>}
                {
                    this.state.isShowAttendance && this.createSections()&& <div>
                        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js">
                            <script src="https://cdnjs.cloudflare.com/ajax/libs/react/16.6.3/umd/react.production.min.js"></script>
                            <script src="https://cdnjs.cloudflare.com/ajax/libs/react-dom/16.6.3/umd/react-dom.production.min.js"></script>


                        </script>
                        <p>Choose a section from the conference</p>


                        <ul>
                            {this.state.checkboxes.map((item,i)=>(
                                <li>



                                    <input key={i}
                                           type="checkbox"
                                           checked={item}
                                           onChange={e => this.onChangeBoxes(e, i)}/>{this.state.sections[i]}

                                </li>
                            ))}
                            <button onClick={()=>this.handleListenerMember()} >Attend</button>
                        </ul>

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
    createSections() {
        this.state.sections=this.props.location.state.conference_topics.split(",");
        if(this.state.checkboxes.length===0)
        {
            this.state.checkboxes=new Array(this.state.sections.length).fill().map((_, i) => !i);
        }
        return true;
    }

    callAlert() {
        const alert = useAlert();
        alert.show("Uploaded");
    }
};


