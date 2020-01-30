import React, {useEffect, useState} from 'react';
import logo from './logo.svg';
import './App.css';
import {Service} from "./Types/Service";
import {Prediction} from "./Types/Prediction";

export interface Predictions {
    results: Prediction[];
}

const App: React.FC = () => {
    const [result, setResult] = useState<Service<Predictions>>({
        status: 'loading'
    });
    useEffect(() => {
            fetch("http://localhost:8080/predict").then(response => response.json())
                // .then(response => setResult({ status: 'loaded', payload: response }))
                .catch(error => {
                    setResult({status: 'error', error});
                    console.log("Error ", error)
                })
        }, []
    );
    return (
        <div className="App">
            <header className="App-header">
                <img src={logo} className="App-logo" alt="logo"/>
                <p>

                </p>
                {result.status === 'loading' && <div>Sending...</div>}
                {result.status === 'loaded' && <div>Loaded</div>}
                {result.status === 'error' && <div>Error message</div>}
                <a
                    className="App-link"
                    href="https://reactjs.org"
                    target="_blank"
                    rel="noopener noreferrer"
                >
                </a>
            </header>
        </div>
    );
};

export default App;
