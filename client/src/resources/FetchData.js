import axios from 'axios';

export const getData = () => {
    let graphData='';
    axios.get("http://localhost:8080/data/nasa/")
        .then(res => {
            graphData = res.data;
        });
    return graphData;
};