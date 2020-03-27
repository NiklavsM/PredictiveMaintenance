import React, {PureComponent} from 'react';
import 'react-dropdown/style.css'
import '../style.css';
import {dates} from '../resources/dates';
import axios from "axios";
import Graph from './Graph';

export default class GraphRUL extends PureComponent {
    constructor() {
        super();
        this.state = {
            filteredData: '',
            fullData: [],
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
        this.state.fullData.forEach((obj, i) => {
            filteredObject = {
                date: dates[i],
                rul: obj[0],
            };
            tempData.push(filteredObject);
        });
        this.setState({filteredData: tempData});
    }


    getData = () => {
        let graphData = '';
        axios.get("http://localhost:8080/predict")
            .then(res => {
                graphData = res.data.predictions[0];
                this.tempData = this.state.fullData;
                this.tempData.push(graphData);
                this.setState({fullData: this.tempData}, () => console.log());
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
                    <Graph data={this.state.filteredData} setting="none"/>
                    <span style={{color: '#ebebeb'}}>date</span>
                </div>
            </div>
        );
    }
}
