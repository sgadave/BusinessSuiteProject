import { useEffect } from "react";
import AnalyticsService from "../services/AnalyticsService";
import PageRouter from "./router/PageRouter"

const Index = ()=>{
    useEffect(()=>{
        AnalyticsService.countPageVisits().catch((err)=>{
            console.log("Index Page Analytics Error : ",err)
        })
    },[])
    return(
        <PageRouter></PageRouter>
    )
}

export default Index;