import React, { useEffect, useState } from 'react';
import axios, { AxiosResponse } from 'axios';
import './Quotes.scss';
import LoaderIcon from '../Loader/LoaderIcon';
import Quote from './Quote';
import Header from '../Header/Header';

export interface IDataItem {
    _id: string;
    content: string;
    author: string;
    authorSlug: string;
    dateAdded: string;
    dateModified: string;
    length: number;
    tags: string[];
}

const Quotes: React.FC = () => {
    const [data, setData] = useState<IDataItem[] | []>([]);
    const [tags, setTags] = useState<Set<string>>(new Set());
    const [loading, setLoading] = useState<boolean>(false);
    
    const fetchData = async (): Promise<void> => {
        setLoading(true);
        try {
            const response: AxiosResponse = await axios.get('https://api.quotable.io/quotes/random?limit=3');
            setData(response.data);
        } catch (error: unknown) {
            console.error('Failed to fetch data:', error);
        } finally {
            setLoading(false);
        }
    };
    

    useEffect(() => {
        fetchData();
    }, []);    


    const newQuote = async (): Promise<void> => {
        try {
            setLoading(true);
            const response: AxiosResponse = await axios.get('https://api.quotable.io/quotes/random?limit=1');
            setData([...response.data, ...data])
            setLoading(false);
        } catch (error: unknown) {
            console.log(error);
            setLoading(false);
        }
    }

    const addTag = (tag : string): void => {
        const newTags = new Set(tags); 
        newTags.add(tag);
        console.log(tag)
        setTags(newTags);
    }

    const deleteTag = (tag : string): void => {
        const newTags = new Set(tags); 
        newTags.delete(tag);
        console.log(tag)
        setTags(newTags);
    }

    return (
        <div className='quotes-container'>
            <Header newQuote={newQuote} tags={tags} deleteTag={deleteTag} loading={loading}/>
            <div className='main'>
                {loading ? <LoaderIcon /> : ''}
                {data != null ? <Quote data={data} addTag={addTag} deleteTag={deleteTag} tags={tags}/> : ''}
            </div>
        </div>
    );
};

export default Quotes;
