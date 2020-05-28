import axios from 'axios';

class ApiService {

    upload(data) {
        return axios.post("http://localhost:8080/api/upload", data);
    }
}

export default new ApiService();
