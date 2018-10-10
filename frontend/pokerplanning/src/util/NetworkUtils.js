import axios from 'axios';
import { BASE_URL } from '../constant/constants';

let instance;

export const init = () => {
    if (instance === undefined) {
        instance = axios.create({
            baseURL: BASE_URL,
            timeout: 100000
        });
    }
   return instance;
};

export const doPost = (url, config) => {
    init();

    const {postBody} = config;

    return instance.post(url, postBody);
};

export const doGet = (url) => {
    init();

    return instance.get(url);
};