import PageVisits from "../../utility/PageVisits";
import React from "react";
import ApplicationsRecieved from "../../utility/ApplicationsRecieved";
import "../../../styles/Analytics.css"
import PageVisitsLine from "../../utility/PageVisitsLine";

const AnalyticsPage = () => {

    return (
        <div className="analyticsPageBody">
            <PageVisitsLine></PageVisitsLine>
            <div>
                <ApplicationsRecieved></ApplicationsRecieved>
            </div>

        </div>
    )
}

export default AnalyticsPage;