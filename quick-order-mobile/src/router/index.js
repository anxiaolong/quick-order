import Vue from 'vue'
import Router from 'vue-router'
import DEBUG from '@/components/DEBUG'
import index from '@/components/index'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/debug',
      name: 'DEBUG',
      component: DEBUG
    },
    {
      path:'/',
      name:'index',
      component:index
    }
  ]
})
