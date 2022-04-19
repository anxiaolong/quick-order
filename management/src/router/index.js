import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/login'
import Index from '@/components/index'
import Navcon from '@/components/navcon'
import Leftnav from '@/components/leftnav'
import Password from '@/components/password'


Vue.use(Router)

export default new Router({
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
      path: '/index',
      name: 'index',
      component: Index,
      meta:{
        title:'超级后台管理'
      },
      children:[
        {
          path: '/navcon',
          name: 'navcon',
          component: Navcon,
          meta:{
           title:'index'
          }
        },
        {
          path: '/leftnav',
          name: 'leftnav',
          component: Leftnav,
          meta:{
           title:'index'
          }
        },
        {
          path: '/user/password',
          name: 'password',
          component: Password,
          meta:{
           title:'修改密码'
          }
        }
      ]
    }
  ]
})
