import React,{Component} from 'react';
import '../App.css';
import {Table, Button} from 'reactstrap';
import {Link,Route,BrowserRouter,Switch,useParams} from 'react-router-dom';
import Conference  from "./Conference";
import ReactDOM from 'react-dom'
import { withRouter } from "react-router-dom";
const paper = require('../api/paper');
const conference = require('../api/conference')


export default class CommitteeDashboard extends Component{
    userId;

    constructor(props) {
        super(props);
        this.state={
            items:[],
            paper_id:0,
            conference_id:0,
            paper_check:false,
            isLoaded:false,
            isShow:false,
            showNo:false,
            hasToReview:false,
            bid_value:0,
            justification: "",
            is_user_committee_member:false,
            users:[]
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
        return (<p> {this.getBidDeadline(conference_id) }  </p>);
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
                             <p>Bid deadline: </p> {this.bidDeadline(item.conference_id) }
                            Keywords: {item.keywords}<br/>
                            Content:   <Link to={{ activeClassName:'idk', pathname: `/papers/${item.id}`, state: {
                                 id: item.id,
                                 title:item.title,
                                 content:item.content

                             }}}  >View paper</Link>

                             <br/><br/>
                             {/*} <Button onClick={()=>this.openFile(item.content)}>View the paper</Button> {*/}

                             <form onSubmit={() => this.handleSubmitBid(item.id)}>
                             <input type ="text"
                                    name = "justification"
                                    placeholder="motivate your choice"
                                    value={this.state.justification}
                                    onChange={this.handleChange}
                                    required/><br/><br/>
                                 <input type="checkbox" onChange={ this.handleAccept}  value = {this.state.bid_value} name="randi"/>  <label htmlFor="randi"> Reject </label>
                                 <input type="checkbox" onChange={ this.handleReject}  value = {this.state.bid_value} name="randi2"/>  <label htmlFor="randi2"> Accept </label>

                                 {this.state.hasToReview &&<Button onClick={()=>this.reviewPaperWithId(item.id,item.conference_id)}> Review paper</Button>}

                             <button type ="submit"> Submit </button>
                             </form>
                             <br/><br/><br/>
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

