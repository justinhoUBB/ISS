import React,{Component} from 'react';
import '../App.css';
import Login from '../modules/Login';
import { ButtonComponent } from '@syncfusion/ej2-react-buttons';

export default class Home extends Component{

    constructor(props) {
        super(props);
        this.redirectToRegister = this.redirectToRegister.bind(this);
    }

    redirectToRegister() {
        this.props.history.push('/register');
    }

    render(){
        return (
                <div>
                <h1> Conference Management System</h1>
                <Login/>
            </div>
        );
    }
};
