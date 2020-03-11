import React, {PureComponent} from 'react';
import {
    AreaChart, Area, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer,
} from 'recharts';
import Dropdown from 'react-dropdown'
import 'react-dropdown/style.css'
import '../style.css';
import CustomTooltip from './CustomTooltip';


import data from '../resources/mockData.json';
import moment from 'moment';
import {settingsList, coloursList} from "../resources/ValuesLists";
import {getData} from "../resources/FetchData";
import axios from "axios";

export default class Graph extends PureComponent {
    constructor() {
        super();
        this.state = {
            setting: 's2',
            filteredData: '',
            fullData:[]
        };

    }

    interval = 0;
    tempData = [];

    componentDidMount() {
       this.getData();
        this.interval = setInterval(() => this.getData(), 6000);
    }





    setData = (setting) => {
        let tempData = [];
        let filteredObject = '';

        this.state.fullData.forEach(obj => {
            filteredObject = {
                date: moment(obj.timestamp * 1000).format("YYYY-MM-DD"),
                value: obj[setting],
                rul: obj.rul,
                name: setting
            };
            tempData.push(filteredObject);
        });
        this.setState({filteredData: tempData});

    }


    onSelectSetting = (e) => {
        this.setState({setting: e.value})
    };

    getData = () => {
        let graphData='';
        axios.get("http://localhost:8080/data/nasa/")
            .then(res => {
                graphData = res.data;
                this.tempData = this.state.fullData;
                this.tempData.push(graphData);
                this.setState({fullData: this.tempData}, ()=>  console.log);
                this.setData('s2');
            });

    };


    render() {
        return (
            <div className="graphContainer">
                <div className='menus'>
                    <div className='drop'>
                        <Dropdown options={settingsList} onChange={this.onSelectSetting} value={this.state.setting}
                                  placeholder="Select a setting" className='dropdown'/>
                    </div>
                </div>
                <div className='graphSensor'>
                    <h1>The selected setting is {this.state.setting !== '' ? this.state.setting.toUpperCase() : 'S2'} </h1>
                    <ResponsiveContainer>
                        <AreaChart
                            data={this.state.filteredData}
                            margin={{
                                top: 10, right: 30, left: 0, bottom: 0,
                            }}
                        >
                            <CartesianGrid strokeDasharray="3 3"/>
                            <XAxis dataKey="date" hide={true}/>
                            <YAxis/>
                            <Tooltip
                                labelStyle={{ color: "#676767" }}
                                itemStyle={{ fontWeight: "bold", color:"black" }}
                                formatter={function(value) {
                                return value;
                            }}
                                                   labelFormatter={function(value) {
                                                       return  value;
                                                   }}/>/>}
                            <Area type="monotone" dataKey="value" stroke={coloursList[this.state.setting]}
                                  fill={coloursList[this.state.setting]}/>
                        </AreaChart>
                    </ResponsiveContainer>
                </div>
            </div>
        );
    }
}
