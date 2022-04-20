import axios from 'axios'


// 响应拦截器
axios.interceptors.response.use(
    (response)=>{ // 响应成功执行，状态码为2开头
        // console.log('响应拦截器执行，响应对象：',response.data.msg)
        if (response.data.msg == '请登录后完成操作') {  // session失效跳转登录页面
            window.location.href = '/login'
        }
        return response
    }
)

// 暴露给外部引用
export default axios