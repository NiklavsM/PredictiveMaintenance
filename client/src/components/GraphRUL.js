import React, {PureComponent} from 'react';
import {
    AreaChart, Area, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer,
} from 'recharts';
import 'react-dropdown/style.css'
import '../style.css';
import {dates} from '../resources/dates';


import {coloursList} from "../resources/ValuesLists";
import axios from "axios";

export default class Graph extends PureComponent {
    constructor() {
        super();
        this.state = {
            filteredData: '',
            fullData:[],
        };

    }

    interval = 0;
   tempData = [];

    componentDidMount() {
        this.getData();
        this.interval = setInterval(() => this.getData(), 6000);
    }


    setData = () => {
        let tempData = [];
        let filteredObject = '';
        this.state.fullData.forEach((obj,i) => {
            filteredObject = {
                date: dates[i],
                rul: obj[0],
            };
            tempData.push(filteredObject);
        });
        this.setState({filteredData: tempData});
    }



    getData = () => {
        let graphData='';
        axios.get("http://localhost:8080/predict")
            .then(res => {
                graphData = res.data.predictions[0];
                this.tempData = this.state.fullData;
                this.tempData.push(graphData);
                this.setState({fullData: this.tempData}, ()=>  console.log());
                this.setData();
            })
            .catch(error => {
                    console.log(error)
                });

    };


    render() {
        return (
            <div className="graphContainer">
                <div className='graphRUL'>
                    <h1>The RUL processed values</h1>
                    <ResponsiveContainer>
                        <AreaChart
                            data={this.state.filteredData}
                            margin={{
                                top: 10, right: 30, left: 0, bottom: 0,
                            }}
                        >
                            <CartesianGrid strokeDasharray="3 3"/>
                            <XAxis dataKey="date" hide={true}/>
                            <YAxis />
                            <Area type="monotone" dataKey="rul" stroke={coloursList['rul']}
                                  fill={coloursList['rul']}/>
                            <Tooltip              labelStyle={{ color: "#676767" }}
                                                  itemStyle={{ fontWeight: "bold", color:"black" }}
                                                 formatter={function(value) {
                                return value;
                            }}
                                       labelFormatter={function(value) {
                                           return value;
                                       }}/>

                        </AreaChart>
                    </ResponsiveContainer>
                </div>
            </div>
        );
    }
}
