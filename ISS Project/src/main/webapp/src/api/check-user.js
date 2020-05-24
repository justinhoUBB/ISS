import React from "react";
import {Redirect} from "react-router-dom";
import AccessDenied from "../modules/AccesDenied";


const permissions = {
    true: ['/dashboard','/addconference', '/conferences/:id','/logout'],
    false: ['/dashboard','/conferences/:id','/logout']
}

const EnsureCorrectUserLoggedIn = (Component, currentPath, props) => {

    if (!localStorage.loggedInUser) {
        return <Redirect to="/"/>
    } else {
        if ( localStorage.isCommitteeMember === "true") {
            return <Component {...props}/>;
        } else {
            return <AccessDenied/>
        }
    }
};

const EnsureLoggedIn = (Component, currentPath, props) => {
    if (localStorage.loggedInUser) {
        return <Component {...props}/>;
    } else {
        return <Redirect to="/"/>
    }
};

export {EnsureCorrectUserLoggedIn, EnsureLoggedIn};