import React from 'react';
import Navbar from './components/Navbar';
import NewsList from './components/NewsList';
import Footer from './components/Footer'; // Import the Footer component

import './App.css';

const App = () => {
    return (
        <div>
            <Navbar />
            <NewsList />
            <Footer /> {/* Add the Footer component here */}
        </div>
    );
};

export default App;
