const axios = require('axios');

function  addPaper() {
    axios.post('http://localhost:8080/api/papers/', {

        content: this.state.content,
        publisher_id: this.state.publisher_id,
        conference_id: this.state.conference_id,
        list_of_authors: this.state.list_of_authors,
        title: this.state.title,
        keywords: this.state.keywords


    }).then((response) => {
        if (!response.data.isError) {
            this.state.paper_id=response.data.id;
            this.props.history.push('/dashboard');

        }

    });
}

function  getCurrentDate(separator='-'){

    let newDate = new Date();
    let date = newDate.getDate();
    let month = newDate.getMonth() + 1;
    let year = newDate.getFullYear();

    return `${year}${separator}${month<10?`0${month}`:`${month}`}${separator}${date}`
}
function  addPaperBid(paper_id) {
    axios.post('http://localhost:8080/api/paper_bids/', {
        bid_date: getCurrentDate('-'),
        bid_value: this.state.bid_value,
        justification: this.state.justification,
        member_id: localStorage.loggedInUserID,
        paper_id: paper_id
    }).then(r => console.log(r));
}

function checkPaperSubmission(id){

    axios.post('http://localhost:8080/api/submissions/'+ this.state.paper_id, {
        conference_id: this.state.conference_id,
        paper_id: id,
        user_id: localStorage.loggedInUserID

    }).then((response) =>{
        console.log(response.data.conference_id);
        localStorage.setItem("confId", response.data.conference_id);
    });


}

function addReview(){
    console.log(localStorage.loggedInUserID);
    console.log(this.state.reviewpaperid);
    console.log(this.state.reviewjust);
    console.log(this.state.reviewval);
    axios.put('http://localhost:8080/api/paper_review/', {

        member_id: localStorage.loggedInUserID,
        paper_id: this.state.reviewpaperid,
        recommendations: this.state.reviewjust,
        remark: this.state.reviewval

    }).then((response) => {
        if (!response.data.isError) {
            axios.post('http://localhost:8080/api/review_result/');
            axios.put('http://localhost:8080/api/papers/' + this.state.paper_id).then(()=>{
                this.props.history.push('/comdashboard');

            })

        }

    })

}



function addPaperSubmission(){

    axios.post('http://localhost:8080/api/submissions/', {

        user_id: this.state.publisher_id,
        paper_id: +this.state.paper_id,
        conference_id: this.state.conference_id


    }).then((response) => {
        if (!response.data.isError) {
            this.props.history.push('/dashboard');
        }

    })

}
module.exports = {
    addPaper,addReview, addPaperSubmission,checkPaperSubmission,addPaperBid
}
