import React, {PureComponent} from 'react';
import {
    AreaChart, Area, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer,
} from 'recharts';
import Dropdown from 'react-dropdown'
import 'react-dropdown/style.css'
import '../style.css';
import CustomTooltip from './CustomTooltip';


// import data from '../resources/mockData.json';
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
            batchData: [],
            needsChange: false,
            fullData:''
        };

    }

    interval = 0;
    batchData = [];

    componentDidMount() {
       this.setState({fullData: this.getData()}, ()=>  console.log); // uncomment when link works
         const tempData = getData(); //the state setting can be too slow initially this is why I use also a temp variable for the initial load
        // this.setState({fullData: data});// delete when the previous line works
        // const tempData = data; // delete when the axios call works
    //     this.batchData.push(tempData[this.batchData.length]);
    //     this.batchData.push(tempData[this.batchData.length]);
    // this.setData('s2');
        this.interval = setInterval(() => this.loadPatchedData(), 5000);
    }


    componentDidUpdate(prevProps, prevState, snapshot) {
        if (this.state.setting !== prevState.setting || this.state.needsChange === true) {
            const setting = this.state.setting;
            this.setData(setting);
            this.setState({needsChange:false})
        }
    }

    stopInterval = () => {
        clearInterval(this.interval);
    };


    loadPatchedData = () => {
        if (this.batchData.length < this.state.fullData.length) {
            this.batchData.push(this.state.fullData[this.batchData.length]); //i
            this.batchData.push(this.state.fullData[this.batchData.length]); //i+1
            this.setState({batchData: this.batchData}, ()=>  console.log);
            this.setState({needsChange: true})
        } else {
             this.stopInterval();
        }
    };


    setData = (setting) => {
        let tempData = [];
        let filteredObject = '';
        this.batchData.forEach(obj => {
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
                this.setState({fullData: graphData}, ()=>  console.log);
                const tempData = graphData;
                console.log(graphData)

                this.batchData.push(tempData[this.batchData.length]);
                this.batchData.push(tempData[this.batchData.length]);
                this.setData('s2');
            });

    };


    render() {
        return (
            <div>
                <h1>The selected setting is {this.state.setting !== '' ? this.state.setting.toUpperCase() : 'S2'} </h1>
                <div className='menus'>
                    <div className='drop'>
                        <Dropdown options={settingsList} onChange={this.onSelectSetting} value={this.state.setting}
                                  placeholder="Select a setting" className='dropdown'/>
                    </div>
                </div>
                <div className='graph'>
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
                            <Tooltip content={<CustomTooltip style={{backgroundColor: 'red'}} name="Value"/>}/>
                            <Area type="monotone" dataKey="value" stroke={coloursList[this.state.setting]}
                                  fill={coloursList[this.state.setting]}/>
                        </AreaChart>
                    </ResponsiveContainer>
                </div>
            </div>
        );
    }
}
