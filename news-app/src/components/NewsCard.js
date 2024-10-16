import React from 'react';

const NewsCard = ({ article }) => {
    return (
        <div className="news-card">
            <h2>{article.title}</h2>
            <img src={article.urlToImage} alt={article.title} />
            <p>{article.description}</p>
            <a href={article.url} target="_blank" rel="noopener noreferrer">Read More</a>
        </div>
    );
};

export default NewsCard;
