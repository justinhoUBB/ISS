import React from "react";

class AccessDenied extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="conferenceList">
                <div>
                    <div className="row align-items-center justify-content-center" style={{minHeight: '98vh'}}>
                        <h1>Access denied</h1>
                    </div>
                    <a href="http://localhost:3000/dashboard"> Dashboard </a>
                </div>
            </div>
        );
    }
}

export default AccessDenied;