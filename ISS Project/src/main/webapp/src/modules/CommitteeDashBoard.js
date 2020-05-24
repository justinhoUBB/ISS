import React,{Component} from 'react';
import '../App.css';
import {Table, Button} from 'reactstrap';
import {Link,Route,BrowserRouter,Switch,useParams} from 'react-router-dom';
import Conference  from "./Conference";
import ReactDOM from 'react-dom'
import { withRouter } from "react-router-dom";

export default class CommitteeDashboard extends Component{

    constructor(props) {
        super(props);
        this.state={
            items:[],
            isLoaded:false,
            isShow:false
        }
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
            })
    }

    logout() {
        this.props.history.push('/logout');
    }

    render(){

        const {items, isLoaded} = this.state;
        return (

            <div  className ="conferenceList">
                <h1> Conference Management System</h1>

                <a href="http://localhost:3000/addconference"> Add conference</a>
                <a href="http://localhost:3000/reviewpaper"> Review Paper </a>
                <a href="http://localhost:3000/dashboard"> Dashboard </a>
                <br/>
                <br/>


                <ul>
                    {items.map(item=>(
                        <li key={item.id}>

                            {item.paper_title}  Paper  <br/>

                            List of Authors: {item.list_of_authors}<br/>
                            Keywords: {item.keywords}<br/>
                            Content: {item.paper_content}<br/>

                            <br/>

                            <Button color="succes"> Bid</Button>
                            <br/><br/><br/>
                        </li>
                    ))}
                </ul>
                <Button className="buttonLogOut" onClick={this.logout}> Log out </Button>


            </div>


        );
    }


};

