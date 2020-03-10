import React, {PureComponent} from 'react';
import {
    AreaChart, Area, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer,
} from 'recharts';
import Dropdown from 'react-dropdown'
import 'react-dropdown/style.css'
import '../style.css';
import CustomTooltip from './CustomTooltip';
import {dates} from '../resources/dates';


// import data from '../resources/mockData.json';
import moment from 'moment';
import {settingsList, coloursList} from "../resources/ValuesLists";
import {getData} from "../resources/FetchData";
import axios from "axios";

export default class Graph extends PureComponent {
    constructor() {
        super();
        this.state = {
            filteredData: '',
            fullData:''
        };

    }

    interval = 0;
    fullData = [];

    componentDidMount() {
        this.setState({fullData: this.getData()}, ()=>  console.log); // uncomment when link works
        // the state setting can be too slow initially this is why I use also a temp variable for the initial load
        // this.setState({fullData: data});// delete when the previous line works
        // const tempData = data; // delete when the axios call works
        //     this.batchData.push(tempData[this.batchData.length]);
        //     this.batchData.push(tempData[this.batchData.length]);
        // this.setData('s2');
        this.interval = setInterval(() => this.getData(), 5000);
    }


    setData = () => {
        let tempData = [];
        let filteredObject = '';
        this.fullData.forEach((i,obj) => {
            filteredObject = {
                date: dates[i],
                rul: obj.predictions,
            };
            tempData.push(filteredObject);
        });
        this.setState({filteredData: tempData});

    }


    getData = () => {
        let graphData='';
        axios.get("http://localhost:8080/predict")
            .then(res => {
                graphData = res.data;

                this.setState({fullData: this.state.fullData.append(graphData)}, ()=>  console.log);
                const tempData = this.state.fullData.append(graphData);
                console.log(graphData)

                this.batchData.push(tempData[this.batchData.length]);
                this.batchData.push(tempData[this.batchData.length]);
                this.setData();
            });

    };


    render() {
        return (
            <div>
                <h1>The RUL processed values</h1>
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
                            <Tooltip content={<CustomTooltip style={{backgroundColor: 'red'}} name="RUL"/>}/>
                            <Area type="monotone" dataKey="value" stroke={coloursList['rul']}
                                  fill={coloursList[this.state.setting]}/>
                        </AreaChart>
                    </ResponsiveContainer>
                </div>
            </div>
        );
    }
}
