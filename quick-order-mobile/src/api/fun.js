import axios from 'axios'
import MD5 from "js-md5"

const req = (method,url,params,channel)=>{
    const sign = MD5(JSON.stringify(params)+'&key='+channel.key)
    const data = {
        data:params,
        partner:channel.partner,
        sign:sign
    }

    return axios({
        method:method,
        url:url,
        Headers:{'Content-Type':'application/json'},
        data:data
    })
}

const today = () =>{
    var date = new Date()
    var year = date.getFullYear()
    var month = date.getMonth() + 1 < 10 ? 
                    '0' + (date.getMonth() + 1) : date.getMonth()+ 1
    var day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate()
    // var hours = date.getHours() < 10 ? '0' + date.getHours() : date.getHours()
    // var minutes = date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()
    // var seconds = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds()
    return year + '-' +month + '-' + day
}

const now = () =>{
    var date = new Date()
    var year = date.getFullYear()
    var month = date.getMonth() + 1 < 10 ? 
                    '0' + (date.getMonth() + 1) : date.getMonth()+ 1
    var day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate()
    var hours = date.getHours() < 10 ? '0' + date.getHours() : date.getHours()
    var minutes = date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()
    var seconds = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds()
    return year + '-' +month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds
}

const getOrderId = (channel)=>{
    var date = new Date()
    var year = date.getFullYear()
    var month = date.getMonth() + 1 < 10 ? 
                    '0' + (date.getMonth() + 1) : date.getMonth()+ 1
    var day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate()
    var hours = date.getHours() < 10 ? '0' + date.getHours() : date.getHours()
    var minutes = date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()
    var seconds = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds()
    return channel.partner + year + month + day + hours + minutes + seconds + Math.floor((Math.random()*900+100))
}


export{
    req,today,getOrderId,now
}