import React,{Component} from 'react';
import '../App.css';
import {Table, Button} from 'reactstrap';
import {Link,Route,BrowserRouter,Switch,useParams} from 'react-router-dom';
import Conference  from "./Conference";
import ReactDOM from 'react-dom'
import { withRouter } from "react-router-dom";
const paper = require('../api/paper');

export default class ReviewPapers extends Component{

    constructor(props) {
        super(props);
        this.state={
            recommendations:'',
            remark:0,
            paper_id: 0,
            member_id: 0,
            isLoaded:false,
            isShow:false
        };

        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.register  = paper.addReview.bind(this);
        this.logout = this.logout.bind(this);
        this.createText= this.createText.bind(this);
    }

    componentDidMount() {
        fetch('http://localhost:8080/api/papers')
            .then(res => res.json())
            .then(json => {
                this.setState({
                    isLoaded: true,
                    items: json,

                })
            })
    }

    createText(){
        this.setState({isShow: true
        });
    }

    handleSubmit(event) {

        event.preventDefault();
        this.register();
    }

    logout() {
        this.props.history.push('/logout');
    }

    handleChange(event){
        this.setState({
            [event.target.name]: event.target.value
        });

    }


    render(){
        const { data } = this.props.location;
        this.state.paper_id=data[0];
        this.state.member_id=data[1];

        const {items, isLoaded} = this.state;
        return (

            <div  className ="conferenceList">
                <h1> Conference Management System</h1>

                <a href="http://localhost:3000/comdashboard"> Committee Dashboard </a>
                <a href="http://localhost:3000/addconference"> Add conference</a>
                <a href="http://localhost:3000/dashboard"> Dashboard </a>
                <br/>
                <br/>


                {/*{!this.state.isShow && <button onClick = {this.createText}> Review Paper</button>}*/}

                {!this.state.isShow &&
                <form onSubmit={this.handleSubmit}>


                    Remark:<br/>
                    <input type ="integer"
                           name="remark"
                           placeholder ="remark"
                           value={this.state.remark}
                           onChange ={this.handleChange}
                           required/>
                    <br/><br/>

                    Recommendations:<br/>
                    <input type ="text"
                           name = "recommendations"
                           placeholder="recommendations"
                           value={this.state.recommendations}
                           onChange={this.handleChange}
                           required/><br/><br/>


                    <button type="submit"> Review Paper </button><br/>
                </form>}
                <br/><br/>
                <Button className="buttonLogOut" onClick={this.logout}> Log out </Button>


            </div>


        );
    }


};

