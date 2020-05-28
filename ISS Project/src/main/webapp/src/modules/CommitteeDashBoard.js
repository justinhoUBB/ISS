import React,{Component} from 'react';
import '../App.css';
import {Table, Button} from 'reactstrap';
import {Link,Route,BrowserRouter,Switch,useParams} from 'react-router-dom';
import Conference  from "./Conference";
import ReactDOM from 'react-dom'
import { withRouter } from "react-router-dom";
const paper = require('../api/paper');
const conference = require('../api/conference')
const bid = require('../api/bid')


export default class CommitteeDashboard extends Component{
    userId;

    constructor(props) {
        super(props);
        this.myRef = React.createRef();
        this.state={
            items:[],
            paper_id:0,
            bid:"",
            conference_id:0,
            paper_check:false,
            isLoaded:false,
            isShow:false,
            showNo:false,
            hasToReview:false,
            bid_value:0,
            justification: "",
            is_user_committee_member:false,
            users:[],
            reviewjust:"",
            reviewval:-2,
            reviewpaperid:0,
            reviewrec:"",
            review:"",
            conferences:[]
        };
        this.logout = this.logout.bind(this);
        this.checkPaper = paper.checkPaperSubmission.bind(this);
        this.getBidDeadline = conference.getBidDeadline.bind(this);
        this.bidDeadline =this.bidDeadline.bind(this);
        this.isPartOfCommittee = conference.checkConferenceCommitteePaper.bind(this);
        this.checkUserCM = this.checkUserCM.bind(this);
        this.handleAccept =this.handleAccept.bind(this);
        this.handleReject = this.handleReject.bind(this);
        this.handleSubmitBid = this.handleSubmitBid.bind(this);
        this.addPaperBid = paper.addPaperBid.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.getStartingDate = conference.getStartingDate.bind(this);
        this.saveSelect = this.saveSelect.bind(this);
        this.handleSubmitReview = this.handleSubmitReview.bind(this);
        this.settleBids = conference.settleBids.bind(this);
        this.doReviews = conference.doReviews.bind(this);
        this.saveSelectReview = this.saveSelectReview.bind(this);
        this.addReview = paper.addReview.bind(this);
        this.wereBidsSettled = conference.areBidsSettled.bind(this);
    }

    componentDidMount() {
        fetch('http://localhost:8080/api/papers')
            .then(res => res.json())
            .then(json => {
                this.setState({
                    isLoaded: true,
                    items: json
                })
            });
        fetch('http://localhost:8080/api/users')
            .then(res => res.json())
            .then(json => {
                this.setState({
                    isLoaded: true,
                    users: json
                })
            });
        fetch('http://localhost:8080/api/conferences')
            .then(res => res.json())
            .then(json => {
                this.setState({
                    isLoaded: true,
                    conferences: json
                });
                this.state.conferences.map(conference => {
                    this.wereBidsSettled(conference.id);
                    if(Date.parse(conference.bid_deadline) < Date.parse(this.getCurrentDate()) && localStorage.getItem("was_settled_" + conference.id) === "false") {
                        this.settleBids(conference.id);
                        this.doReviews(conference.id);
                    }
                });
            });
    }
    handleSubmitBid(paper_id){
        this.addPaperBid(paper_id);
    }

    getCurrentDate(separator='-'){

        let newDate = new Date();
        let date = newDate.getDate();
        let month = newDate.getMonth() + 1;
        let year = newDate.getFullYear();

        return `${year}${separator}${month<10?`0${month}`:`${month}`}${separator}${date}`
    }

    handleChange(event){
        this.setState({
            [event.target.name]: event.target.value
        });

    }

    handleReject(){
        this.setState({bid_value:-11})
    }

    handleAccept(){
        this.setState({bid_value:-1})

    }
    bidDeadline(conference_id){
        this.getBidDeadline(conference_id);
    }

    startingDate(conference_id){
        this.getStartingDate(conference_id);
    }

    checkUserCM(conference_id){
        this.isPartOfCommittee(conference_id);
    }

    logout() {
        this.props.history.push('/logout');
    }
    reviewPaperWithId(id,conf_id){
        this.setState({showNo:false});
        this.userId=this.state.users.filter(item=>item.email===localStorage.loggedInUser).map(item=>item.id);
        this.checkUserCM(conf_id);
        setTimeout( () => {
            this.setState({is_user_committee_member: localStorage.isUserPartOfCom});
            localStorage.removeItem("isUserPartOfCom");
            if (this.state.is_user_committee_member === "true") {
                this.props.history.push({
                    pathname: '/reviewpaper',
                    data: [id, this.userId]
                })
            } else
            {
                this.setState({showNo:true});

            }
        },100);

        }

        //bid
    handleSubmitReview(paper_id, conf_id) {
        if(this.state.review !== "") {
            if(this.state.review === "Strong Accept") {
                this.state.reviewval = 1;
            }
            else if(this.state.review === "Accept") {
                this.state.reviewval = 2;
            }
            else if(this.state.review === "Weak Accept") {
                this.state.reviewval = 3;
            }
            else if(this.state.review === "Borderline") {
                this.state.reviewval = 4;
            }
            else if(this.state.review === "Weak Reject") {
                this.state.reviewval = 5;
            }
            else if(this.state.review === "Reject") {
                this.state.reviewval = 6;
            }
            else {
                this.state.reviewval = 7;
            }
            this.state.reviewpaperid = paper_id;
            this.state.reviewdate = this.getCurrentDate();
            this.addReview();
        }
    }

    saveSelect(event) {
        const id = event.nativeEvent.target.selectedIndex;
        this.state.bid = event.nativeEvent.target[id].text;
    }

    saveSelectReview(event) {
        const id = event.nativeEvent.target.selectedIndex;
        this.state.review = event.nativeEvent.target[id].text;
    }

    render(){

        const {items, isLoaded} = this.state;
        return (

            <div  className ="conferenceList">
                <h1> Conference Management System</h1>

                <a href="http://localhost:3000/addconference"> Add conference</a>
                <a href="http://localhost:3000/dashboard"> Dashboard </a>
                <br/>
                <br/>

                {this.state.showNo && <p> You can't leave reviews to papers that are submitted to conferences that you have no privileges on. </p>}
                <ul>
                    {items.map(item=>(

                         <li key={item.id}>

                            {item.title}  Paper  <br/>
                            <p>Starting date:  { this.startingDate(item.conference_id) } { localStorage.getItem("starting_date_" + item.conference_id) } </p>
                            <p>Bid deadline:  { this.bidDeadline(item.conference_id) } { localStorage.getItem("bid_deadline_" + item.conference_id) } </p>
                            Keywords: {item.keywords}<br/>
                            Content:   <Link to={{ activeClassName:'idk', pathname: `/papers/${item.id}`, state: {
                                 id: item.id,
                                 title:item.title,
                                 content:item.content

                             }}}  >View paper</Link>

                             <br/><br/><br/>
                             { Date.parse(this.getCurrentDate()) > Date.parse(localStorage.getItem("bid_deadline_" + item.conference_id)) &&
                               Date.parse(this.getCurrentDate()) < Date.parse(localStorage.getItem("starting_date_" + item.conference_id)) && <h3>Leave review</h3> &&

                                 <form onSubmit={() => this.handleSubmitReview(item.id, item.conference_id)}>
                                 <input type="text"
                                        name="reviewjust"
                                        placeholder="Leave recommandations"
                                        value={this.state.reviewjust}
                                        onChange={this.handleChange}
                                        required/><br/>
                                 <select onChange={this.saveSelectReview}>
                                     <option value="blank"></option>
                                     <option value="strong_accept">Strong Accept</option>
                                     <option value="accept">Accept</option>
                                     <option value="weak_accept">Weak Accept</option>
                                     <option value="borderline">Borderline</option>
                                     <option value="strong_reject">Strong Reject</option>
                                     <option value="reject">Reject</option>
                                     <option value="weak_reject">Weak Reject</option>
                                 </select>
                                 <button type="submit">Leave review</button>
                                     <br/><br/><br/>
                                 </form>
                             }
                             { Date.parse(this.getCurrentDate()) < Date.parse(localStorage.getItem("bid_deadline_" + item.conference_id)) &&
                                 <form onSubmit={() => this.handleSubmitBid(item.id)}>
                                     <input type="text"
                                            name="justification"
                                            placeholder="motivate your choice"
                                            value={this.state.justification}
                                            onChange={this.handleChange}
                                            required/><br/><br/>
                                     <input type="checkbox" onChange={this.handleAccept} value={this.state.bid_value}
                                            name="randi"/> <label htmlFor="randi"> Reject </label>
                                     <input type="checkbox" onChange={this.handleReject} value={this.state.bid_value}
                                            name="randi2"/> <label htmlFor="randi2"> Accept </label>
                                     {  }
                                     {this.state.hasToReview &&
                                     <Button onClick={() => this.reviewPaperWithId(item.id, item.conference_id)}> Review
                                         paper</Button>}

                                     <button type="submit"> Submit</button>
                                 </form>
                             }
                        </li>
                    ))}
                </ul>
                <Button className="buttonLogOut" onClick={this.logout}> Log out </Button>


            </div>


        );
    }


    openFile(str) {

        window.open(str);
    }
};

