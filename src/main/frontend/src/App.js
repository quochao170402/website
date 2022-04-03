import logo from './logo.svg';
import './App.css';
import React, { useState, useEffect } from 'react';
import axios from 'axios';

// Test thử cho vui thôi nha
const DemoCode = () => {

  const fecthData = () => {
    axios.get("http://localhost:8080").then(res => {
      console.log(res);
    });
  }

  useEffect(() => {
    fecthData();
  })

  return <h1>Hello</h1>
}

function App() {
  return (
    <div className="App">
      <DemoCode />
    </div>
  );
}

export default App;
