import axios from 'axios';
import NProgress from 'nprogress';

NProgress.configure({ showSpinner: false, trickleSpeed: 100 });

const instance = axios.create({
  baseURL: 'http://localhost:8080/',
});

// Add a request interceptor
axios.interceptors.request.use(
  function (config) {
    // Do something before request is sent
    let access_token = 'Todo...';
    config.headers['Authorization'] = `Bearer ${access_token}`;

    NProgress.start();
    return config;
  },
  function (error) {
    // Do something with request error
    NProgress.done();
    return Promise.reject(error);
  },
);

// Add a response interceptor
axios.interceptors.response.use(
  function (response) {
    // Any status code that lie within the range of 2xx cause this function to trigger
    // Do something with response data
    NProgress.done();
    return response;
  },
  function (error) {
    // Any status codes that falls outside the range of 2xx cause this function to trigger
    // Do something with response error
    NProgress.done();
    return Promise.reject(error);
  },
);

export default instance;
