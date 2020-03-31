import React, {PureComponent} from 'react';
import 'react-dropdown/style.css'
import '../style.css';
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
                date: this.getTime(i),
                rul: obj[0],
            };
            tempData.push(filteredObject);
        });
        this.setState({filteredData: tempData});
    };


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

    getTime = (i) => {
        let date = new Date();
        date.setMilliseconds(i * 10000);
        let time = date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
        return time;
    };


    render() {
        return (
            <div className="graphContainer">
                <div className='graphRUL'>
                    <h1>Remaining Useful Lifetime (RUL)</h1>
                    <Graph className="graph" data={this.state.filteredData} setting="none"/>
                    <span style={{color: '#ffffff'}}>Time</span>
                </div>
            </div>
        );
    }
}
