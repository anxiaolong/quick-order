// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import SlideVerify from 'vue-monoplasty-slide-verify'; //滑动验证插件

Vue.use(SlideVerify);

Vue.config.productionTip = false

Vue.use(ElementUI);


router.beforeEach((to,from,next)=>{
  
  // 这是页面title
  if(to.meta.title){
    document.title = to.meta.title
  }

  next()

})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>',
  data:{
    // 空实例放到根组件下，所有的自组件都能调用
    Bus: new Vue()
  }
})
