import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/login'
import Index from '@/components/index'
import Navcon from '@/components/navcon'
import Leftnav from '@/components/leftnav'
import Managementadmin from '@/components/managementadmin'
import Supplier from '@/components/supplier'


Vue.use(Router)

export default new Router({
  mode:'history', // 去掉路由中的#号
  routes: [
    {
      path: '/login',
      name: 'login',
      component: Login,
      meta:{
        title:'超管登录'
      }
    },
    {
      path: '/',
      name: '跳转登录',
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
        title:'首页'
      },
      children:[
        {
          path: '/navcon',
          name: 'navcon',
          component: Navcon,
          meta:{
           title:'navcon'
          }
        },
        {
          path: '/leftnav',
          name: 'leftnav',
          component: Leftnav,
          meta:{
           title:'leftnav'
          }
        },
        {
          path: '/index/managementadmin',
          name: 'managementadmin',
          component: Managementadmin,
          meta:{
           title:'用户管理'
          }
        },
        {
          path: '/index/supplier',
          name: 'supplier',
          component: Supplier,
          meta:{
           title:'供应商管理'
          }
        }
      ]
    }
  ]
})
