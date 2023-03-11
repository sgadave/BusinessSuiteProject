import axios from "axios";

class AnalyticsService {
    constructor() {
        // Base Url i.e. common Url
        this.baseUrl = "http://localhost:8080/admin/"
    }

    countPageVisits() {
        // method to get user name
        return axios.put(this.baseUrl+"addvisits")
    }

    getPageVisits(){
        return axios.get(this.baseUrl+"allVisits")
    }

    getApplicationRecieved(){
        return axios.get(this.baseUrl+"applicationsrecieved")
    }
}
export default new AnalyticsService();