import logo from './logo.svg';
import './App.css';
import React, { useState, useEffect } from 'react';
import axios from 'axios';

// Test thử cho vui thôi nha
const DemoCode = () => {

  const fecthData = () => {
    axios.get("https://website-h3.herokuapp.com/").then(res => {
      console.log(res);
      console.log("Hello world");
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
