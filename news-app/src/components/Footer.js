import React from 'react';
import './Footer.css'; // Import the CSS file for styling

const Footer = () => {
    return (
        <footer className="footer">
            <p>Developed by Kamal Chandra</p>
            <p>Student, Engineering of Galgotias University</p>
            <div className="social-links">
                <a href="https://www.linkedin.com/in/kamalchandra/" target="_blank" rel="noopener noreferrer">LinkedIn</a>
                <a href="https://www.facebook.com/kamal.chandra.5011516/" target="_blank" rel="noopener noreferrer">Facebook</a>
                <a href="https://www.instagram.com/kamal_chandra_official1/" target="_blank" rel="noopener noreferrer">Instagram</a>
            </div>
        </footer>
    );
};

export default Footer;
