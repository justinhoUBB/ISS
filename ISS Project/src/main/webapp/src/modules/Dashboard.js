import React,{Component} from 'react';
import '../App.css';
import {Table, Button} from 'reactstrap';
import {Link,Route,BrowserRouter,Switch,useParams} from 'react-router-dom';
import Conference  from "./Conference";
import ReactDOM from 'react-dom'
import { withRouter } from "react-router-dom";

export default class Dashboard extends Component{

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
        fetch('http://localhost:8080/api/conferences')
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
               <br/>
               <br/>
                {/*
                 Aici am incercat sa creez si sa generez dinamic paginile de conferinte,
                 da' nu mi-a iesit sa generez contentul, nu stiu daca e realizabil.
                */}

                <ul>
                    {items.map(item=>(
                        <li key={item.id}>

                            {item.topics}  Conference  <br/>

                                {item.description}<br/>
                                Starting date: {item.starting_date}<br/>
                                Number of seats: {item.number_of_seats_per_room * item.number_of_rooms}
                        <br/>

                            <Link to={{ activeClassName:'idk', pathname: `/conferences/${item.id}`}}>See more</Link>
                            <br/><br/><br/>
                        </li>
                    ))}
                </ul>
                <Button className="buttonLogOut" onClick={this.logout}> Log out </Button>


            </div>


        );
    }


};

