import React from 'react';
import Graph from './components/GraphSensors';
import GraphRUL from './components/GraphRUL'
import './style.css';

function App() {
    return (
        <div className="App">
            <Graph/>
            <GraphRUL/>
        </div>
    );
}

export default App;
