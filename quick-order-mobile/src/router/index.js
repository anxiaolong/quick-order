import Vue from 'vue'
import Router from 'vue-router'
import DEBUG from '@/components/DEBUG'
import index from '@/components/index'
import Sync from '@/components/sync'
import SupplierInfo from '@/components/SupplierInfo'
import My from '@/components/my'


Vue.use(Router)

const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}//解决路由重复报错问题

export default new Router({
  routes: [
    {
      path: '/debug',
      name: 'DEBUG',
      component: DEBUG
    },
    {
      path: '/',
      name: 'sync',
      component: Sync
    },
    {
      path:'/index',
      name:'index',
      component:index,
      children:[
        {
          path: '/index/shopping',
          name: 'SupplierInfo',
          component: SupplierInfo
        },
        {
          path: '/index/my',
          name: 'my',
          component: My
        }
      ]
    }
  ]
})
