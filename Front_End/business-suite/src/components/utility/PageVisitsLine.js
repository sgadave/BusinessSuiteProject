import React, { useEffect, useRef, useState } from 'react';
import * as d3 from 'd3';
import AnalyticsService from '../../services/AnalyticsService';
import "../../styles/Analytics.css"

function PageVisitsLine() {
    let [graphData, setGraphData] = useState([])

    useEffect(() => {
        AnalyticsService.getPageVisits().then((resp) => {
            console.log("REPS------", resp.data)
            setGraphData(resp.data)
        }).catch((err) => {
            console.log(err)
        })
    }, [])
    const ref = useRef()

    const createGraph = () => {
        const node = ref.current;

        const margin = { top: 15, right: 15, bottom: 30, left: 30 };
        const width = 500 - margin.left - margin.right;
        const height = 300 - margin.top - margin.bottom;


        const data = graphData.map(d => ({
            date: d.date,
            value: d.pageVisits,
        }));

        console.log("Graph", graphData)



        let barData = graphData.map(data => data.pageVisits);
        console.log("BAR DATA", graphData.map(data => data.pageVisits));



        //   data.map(function(d) { return d.Country; })

        // Define the scales
        const xScale = d3.scaleBand()
            .range([0, width])
            .domain(graphData.map(data => data.date))
            .padding(0.2);


        const yScale = d3.scaleLinear()
            .domain([0, d3.max(barData)])
            .range([height, 0]);


        // Define the axes
        const xAxis = d3.axisBottom(xScale);
        const yAxis = d3.axisLeft(yScale);

        // Create the chart
        const svg = d3.select(node)
            .append('svg')
            .attr('width', width + margin.left + margin.right)
            .attr('height', height + margin.top + margin.bottom)
            .append('g')
            .attr('transform', `translate(${margin.left},${margin.top})`);

        // Add the x axis
        svg.append('g')
            .attr('transform', `translate(0,${height})`)
            .call(xAxis)
            .selectAll("text")
            .style("text-anchor", "end")
            .append('text')
            .attr('class', 'label')
            .attr('x', width)
            .attr('y', -6)
            .style('text-anchor', 'end')
            .text("Date");

        // Add the y axis
        svg.append('g')
            .call(yAxis)
            .append('text')
            .attr('class', 'label')
            .attr('y', 6)
            .style('text-anchor', 'end')
            .text("Visits");


        // Add the line
        svg.append("path")
            .datum(data)
            .attr("fill", "none")
            .attr("stroke", "steelblue")
            .attr("stroke-width", 1.5)
            .attr("d", d3.line()
                .x(function (d) { return xScale(d.date) })
                .y(function (d) { return yScale(d.value) })
            )
    }

    return (
        <div>
            {createGraph()}
            <div className="webVisits">
                <svg ref={node => ref.current = node} width={500} height={300}></svg>
            </div>
            Website Visited
        </div>
    );
}

export default PageVisitsLine;
