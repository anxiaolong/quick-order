import axios from '../ajax/axios'

// 发送请求模板
const req = (method,url,params) => {
    return axios({
        method:method,
        url:url,
        headers:{'Content-Type':'application/json'},
        data:params
    }).then(res => res.data)
}

export {
    req
}