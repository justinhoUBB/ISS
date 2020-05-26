const axios = require('axios');

function  addPaper() {
    axios.post('http://localhost:8080/api/papers/', {

        content: this.state.content,
        publisher_id: this.state.publisher_id,
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


function addReview(){

    axios.post('http://localhost:8080/api/paper_review/', {

        member_id: +this.state.member_id,
        paper_id: +this.state.paper_id,
        recommendations: this.state.recommendations,
        remark: this.state.remark

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
    addPaper,addReview, addPaperSubmission
}
