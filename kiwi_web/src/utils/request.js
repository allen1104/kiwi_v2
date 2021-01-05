import axios from "axios";
import { getToken } from "@/utils/auth.js"

const request = axios.create({
  baseURL: process.env.VUE_APP_BASE_API,
  timeout: 10000
});

// Add a request interceptor
request.interceptors.request.use(
  function (config) {
    // Do something before request is sent
    config.headers.Authorization = getToken();
    return config;
  },
  function (error) {
    // Do something with request error
    return Promise.reject(error);
  }
);

// Add a response interceptor
request.interceptors.response.use(
  function (response) {
    // Any status code that lie within the range of 2xx cause this function to trigger
    // Do something with response data
    return response;
  },
  function (error) {
    if(error.response.status === 401 || error.response.status === 403){
      window.location.href = "/#/login"
    }
    // Any status codes that falls outside the range of 2xx cause this function to trigger
    // Do something with response error
    return Promise.reject(error);
  }
);

// axios.get('db.json').then(response => {
//     console.log(response.data);
// })

export default request;
