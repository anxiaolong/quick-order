import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/login'
import Index from '@/components/index'
import Navcon from '@/components/navcon'

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
      },
      children:[
        {
          path: '/navcon',
          name: 'navcon',
          component: Navcon,
          meta:{
            title:'头部菜单'
          }
        }
      ]
    }
  ]
})
