import React,{Component} from 'react';
import '../App.css';
import {Table, Button} from 'reactstrap';
import {Link,Route,BrowserRouter,Switch,useParams} from 'react-router-dom';
import Conference  from "./Conference";
import ReactDOM from 'react-dom'
import { withRouter } from "react-router-dom";

export default class CommitteeDashboard extends Component{
    userId;

    constructor(props) {
        super(props);
        this.state={
            items:[],
            isLoaded:false,
            isShow:false,
            users:[]
        };
        this.logout = this.logout.bind(this);
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

    logout() {
        this.props.history.push('/logout');
    }
    reviewPaperWithId(id){


        this.userId=this.state.users.filter(item=>item.email===localStorage.loggedInUser).map(item=>item.id);
        this.props.history.push({
            pathname: '/reviewpaper',
            data: [id,this.userId]
        })

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


                <ul>
                    {items.map(item=>(
                        <li key={item.id}>


                            {item.title}  Paper  <br/>

                            List of Authors: {item.list_of_authors}<br/>
                            Keywords: {item.keywords}<br/>
                            Content: {item.content}<br/>

                            <br/>

                            <Button color="succes" onClick={()=>this.reviewPaperWithId(item.id)}> Bid</Button>
                            <br/><br/><br/>
                        </li>
                    ))}
                </ul>
                <Button className="buttonLogOut" onClick={this.logout}> Log out </Button>


            </div>


        );
    }


};

