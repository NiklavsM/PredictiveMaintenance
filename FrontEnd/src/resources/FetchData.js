import axios from 'axios';

export const getData = () => {
    let graphData='';
    axios.get(`https://backEndLink`)
        .then(res => {
            graphData = res.data;
        })
    return graphData;
};