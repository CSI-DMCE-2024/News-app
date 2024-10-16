import React, { useEffect, useState } from 'react';
import axios from 'axios';
import NewsCard from './NewsCard';

const NewsList = () => {
    const [articles, setArticles] = useState([]);

    useEffect(() => {
        const fetchNews = async () => {
            const response = await axios.get('https://newsapi.org/v2/top-headlines?country=us&apiKey=0924bb66492c4c2ca34feeaeca002850');
            setArticles(response.data.articles);
        };
        fetchNews();
    }, []);

    return (
        <div className="news-list">
            {articles.map((article, index) => (
                <NewsCard key={index} article={article} />
            ))}
        </div>
    );
};

export default NewsList;
