import axios from 'axios'

const request = axios.create({
  baseURL: 'http://localhost:8082',
  timeout: 5000,
  headers: {'X-Requested-With': 'XMLHttpRequest'},
  responseType: 'json'
})

export default request