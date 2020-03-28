import React, {PureComponent} from 'react';
import {
    AreaChart, Area, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer, Text,
} from 'recharts';
import 'react-dropdown/style.css'
import '../style.css';


import {coloursList} from "../resources/ValuesLists";

const Graph = props => (
    <ResponsiveContainer>
        <AreaChart
            data={props.data}
            margin={{
                top: 10, right: 50, left: 0, bottom: 10,
            }}
        >
            <CartesianGrid strokeDasharray="3 3"/>
            <XAxis dataKey="date" hide={true}/>
            <YAxis label={<Text x={0} y={0} dx={11} dy={170} offset={0} angle={-90} fill='#d82939'>
                {props.setting !== 'none' ? "Setting value" : "RUL in cycles"}</Text>}/>
            <Tooltip
                labelStyle={{color: "#676767"}}
                itemStyle={{fontWeight: "bold", color: "black"}}
                formatter={function (value) {
                    return value;
                }}
                labelFormatter={function (value) {
                    return value;
                }}/>/>}
            <Area isAnimationActive={false} type="monotone" dataKey={props.setting !== 'none' ? "value" : "rul"}
                  stroke={props.settings !== 'none' ? coloursList[props.setting] : coloursList['rul']}
                  fill={props.settings !== 'none' ? coloursList[props.setting] : coloursList['rul']}/>
        </AreaChart>
    </ResponsiveContainer>
);

export default Graph;
