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
            bidjust:"",
            bidval:-2,
            bidpaperid:0,
            bidconfid:0
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
        this.handleSubmit2 = this.handleSubmit2.bind(this);
        this.addBid = bid.add_bid.bind(this);
    }

    componentDidMount() {
        fetch('http://localhost:8080/api/papers')
            .then(res => res.json())
            .then(json => {
                this.setState({
                    isLoaded: true,
                    items: json,

                })
            });
        fetch('http://localhost:8080/api/users')
            .then(res => res.json())
            .then(json => {
                this.setState({
                    isLoaded: true,
                    users: json,

                })
            })
    }
    handleSubmitBid(event,paper_id){
        event.preventDefault();
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
    handleSubmit2(paper_id, conf_id) {
        if(this.state.bid !== "") {
            if(this.state.bid === "For") {
                this.state.bidval = 1;
            }
            else if(this.state.bid === "Indifferent") {
                this.state.bidval = 0;
            }
            else {
                this.state.bidval = -1;
            }
            this.state.bidpaperid = paper_id;
            this.state.bidconfid = conf_id;
            this.state.biddate = this.getCurrentDate();
            this.addBid();
        }
    }

    saveSelect(event) {
        const id = event.nativeEvent.target.selectedIndex;
        this.state.bid = event.nativeEvent.target[id].text;
        console.log(this.state.bidjust);
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
                            <p>Starting date: </p>
                             <p>Bid deadline:  { this.bidDeadline(item.conference_id) } { localStorage.getItem("bid_deadline_" + item.conference_id) } </p>
                            Keywords: {item.keywords}<br/>
                            Content:   <Link to={{ activeClassName:'idk', pathname: `/papers/${item.id}`, state: {
                                 id: item.id,
                                 title:item.title,
                                 content:item.content

                             }}}  >View paper</Link>

                             <br/><br/>
                             {/*} <Button onClick={()=>this.openFile(item.content)}>View the paper</Button> {*/}
                             { Date.parse(this.getCurrentDate()) < Date.parse(localStorage.getItem("bid_deadline_" + item.conference_id)) &&

                                 <form onSubmit={() => this.handleSubmit2(item.id, item.conference_id)}>
                                 <input type="text"
                                        name={"bidjust" + item.id}
                                        placeholder="motivate your choice"
                                        value={this.state.bidjust}
                                        onChange={this.handleChange}
                                        required/><br/>
                                 <select onChange={this.saveSelect}>
                                     <option value="blank"></option>
                                     <option value="for">For</option>
                                     <option value="against">Against</option>
                                     <option value="indifferent">Indifferent</option>
                                 </select>
                                 <button type="submit">Leave bid</button>
                                     <br/><br/><br/>
                                 </form>
                             }
                             { //Date.parse(this.getCurrentDate()) > Date.parse(localStorage.getItem("bid_deadline_" + item.conference_id)) &&
                               //Date.parse(this.getCurrentDate()) < Date.parse(localStorage.getItem("starting_date_" + item.conference_id)) &&
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

