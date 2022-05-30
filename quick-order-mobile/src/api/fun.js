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


export{
    req
}