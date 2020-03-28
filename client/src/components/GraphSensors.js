import React, {PureComponent} from 'react';
import Dropdown from 'react-dropdown'
import 'react-dropdown/style.css'
import '../style.css';
import Graph from './Graph.js'

import moment from 'moment';
import {settingsList, coloursList} from "../resources/ValuesLists";
import axios from "axios";

export default class GraphSensors extends PureComponent {
    constructor() {
        super();
        this.state = {
            setting: 's2',
            filteredData: '',
            fullData: []
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

    };


    onSelectSetting = (e) => {
        this.setState({setting: e.value})
    };

    getData = () => {
        let graphData = '';
        axios.get("http://localhost:8080/data/nasa/")
            .then(res => {
                graphData = res.data;
                this.tempData = this.state.fullData;
                this.tempData.push(graphData);
                this.setState({fullData: this.tempData}, () => console.log);
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
                    <h1>The selected setting
                        is {this.state.setting !== '' ? this.state.setting.toUpperCase() : 'S2'} </h1>
                    <Graph data={this.state.filteredData} setting={this.state.setting}/>
                    <span style={{color: '#ebebeb'}}>Time</span>
                </div>
            </div>
        );
    }
}
