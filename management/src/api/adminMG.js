import axios from 'axios';
import { loginreq,req } from './axiosFun';

// 1.管理员登录接口
export const login = (params) => {
    return loginreq('post','/admin/login',params)
};