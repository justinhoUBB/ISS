const axios = require('axios');

function  addPaper() {
    axios.post('http://localhost:8080/api/papers/', {
        title: this.state.title,
        publisher_id: this.state.publisher_id,
        list_of_authors: this.state.list_of_authors,
        keywords: this.state.keywords,
        content: this.state.content
    }).then((response) => {
        if (!response.data.isError) {
            this.props.history.push('/dashboard');
        }

    })

}

module.exports = {
    addPaper
}
