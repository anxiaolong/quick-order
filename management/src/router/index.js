import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/login'
import Index from '@/components/index'
import Navcon from '@/components/navcon'
import Leftnav from '@/components/leftnav'
import Managementadmin from '@/components/managementadmin'
import Supplier from '@/components/supplier'

import SupplierLogin from '@/components2/SupplierLogin'
import Index2 from '@/components2/index2'
import Goods from '@/components2/goods'
import Stock from '@/components2/stock'


Vue.use(Router)

export default new Router({
  base:'/management/', // tomcat需要部署到management文件加中
  // mode:'history', // 去掉路由中的#号,使用history模式会引发刷新页面404问题
  routes: [
    { // 默认更目录定位到供应商登录
      path: '/',
      name: '后台管理系统',
      component: SupplierLogin,
      meta:{
        title:'后台管理系统'
      }
    },
    { // 供应商登录（为了和管理员后台做区分）
      path: '/supplier/login',
      name: '供应商登录',
      component: SupplierLogin,
      meta:{
        title:'供应商登录'
      }
    },
    { // 管理员登录（为了和供应商登录区分）
      path: '/management/login',
      name: '超管登录',
      component: Login,
      meta:{
        title:'超管登录'
      }
    },
    { // index及其子路由为管理员后台使用
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
    },
    { // index2及其子路由全部为供应商管理相关
      path: '/index2',
      name: 'index2',
      component: Index2,
      meta:{
        title:'首页'
      },
      children:[
        {
          path: '/index2/goods',
          name: 'goods',
          component: Goods,
          meta:{
           title:'商品管理'
          }
        },
        {
          path: '/index2/stock',
          name: 'stock',
          component: Stock,
          meta:{
           title:'库存管理'
          }
        }
      ]
    }
  ]
})
