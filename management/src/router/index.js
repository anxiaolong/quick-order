import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Index from '@/components/Index'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'login',
      component: Login,
      meta:{
        title:'超管登录'
      }
    },
    {
      path: '/index',
      name: 'index',
      component: Index,
      meta:{
        title:'超级管理服务'
      }
    }
  ]
})
