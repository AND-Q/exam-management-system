import router from "@/router";
import axios from "axios";
// 创建一个新的axios对象
const request = axios.create({
  baseURL: 'http://localhost:8081',   // 后端的接口学院 ip:port
  timeout: 30000    // 请求超时时间
})

// request 拦截器
// 可以自请求时对请求做一些处理
// 比如统一加 token 对请求参数进行统一加密

request.interceptors.request.use(config => {
  config.headers['Content-Type'] = 'application/json;charset=utf-8';
  // let user = localStorage.getItem("honey-user") ? JSON.parse(localStorage.getItem("user")) : null
  let user = JSON.parse(localStorage.getItem("honey-user") || '{}')  // 先把 honey-user 解析出来,有可能没有，所以给个空对象
  config.headers['token'] = user.token;  // 设置请求头
  
  return config;
}, error => {
  console.error('request error: ' + error);   // for debug
  return Promise.reject(error)
});

// response 拦截器
// 可以在接口响应后统一处理接口
request.interceptors.response.use(response => {
  let res = response.data;

  // 兼容服务端返回的字符串数据
  if(typeof res === 'string') {
    res = res ? JSON.parse(res) : res
  }
  if (res.code === '401') {
    router.push('/login')
  }
  return res;
}, error => {
  console.error('response error: ' + error);   // for debug
  return Promise.reject(error)
});


export default request