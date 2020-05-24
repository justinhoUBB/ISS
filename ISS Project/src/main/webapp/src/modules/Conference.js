import React,{Component} from 'react';
import '../App.css';
import {Table, Button} from 'reactstrap';
import { Link } from 'react-router-dom';
const paper = require('../api/paper');


export default class Conference extends Component {

    constructor(props) {
        super(props);
        this.state= {
            paper_title:"",
            publisher_id:0,
            conference_id:0,
            list_of_authors:"",
            keywords:"",
            paper_content:"",
            isShow: false
        }
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.register  = paper.addPaper.bind(this);
        this.submission  = paper.addPaperSubmission.bind(this);
        this.createText = this.createText.bind(this);
        this.logout = this.logout.bind(this);
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
        this.submission();
    }

    render(){
        return (

            <div  id="conference" className ="conferenceList">
                <h1> Conference Management System</h1>

                <a href="http://localhost:3000/addconference"> Add conference</a>
                <a href="http://localhost:3000/dashboard"> Dashboard </a>
                <a href="http://localhost:3000/comdashboard"> Committee Dashboard </a>

                <br/>
                <br/>

                {!this.state.isShow && <button onClick = {this.createText}> Submit Paper</button>}

                {this.state.isShow &&
                <form onSubmit={this.handleSubmit}>
                   Title: <br/>
                    <input type ="text"
                           name = "paper_title"
                           placeholder="title"
                           value={this.state.paper_title}
                           onChange={this.handleChange}
                           required/><br/><br/>

                    Your Id: <br/>
                    <input type ="integer"
                           name = "publisher_id"
                           placeholder="Published id"
                           value={this.state.publisher_id}
                           onChange={this.handleChange}
                           required/><br/><br/>

                    Conference ID: <br/>
                    <input type ="integer"
                           name = "conference_id"
                           placeholder="conference id"
                           value={this.state.conference_id}
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
                           name = "paper_content"
                           placeholder="content"
                           value={this.state.paper_content}
                           onChange={this.handleChange}
                           required/><br/>
                           <br/>


                    <button type="submit"> Add Paper </button><br/>
                </form>}
                <br/>
                <br/>
                <Button className="buttonLogOut" onClick={this.logout}> Log out </Button>

            </div>
        );
}
};


