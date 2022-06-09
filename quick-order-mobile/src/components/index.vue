<template>
  <div>
    <van-nav-bar
      title="QUICK闪购"
      class="nav"
      left-text="返回"
      left-arrow
      @click-left="onClickLeft"
      :fixed='true'
    />


    <router-view style="margin-top:50px"></router-view>

    <van-tabbar :toute='true' v-model="active">
        <van-tabbar-item to="/index/shopping" icon="shop-o">下单</van-tabbar-item>
        <van-tabbar-item to="/index/my" icon="cart-o">已购</van-tabbar-item>
    </van-tabbar>

  </div>
</template>

<script>
import { Notify } from 'vant'
export default {
    name:'index',
    data(){
        return {
           active:0
        }
    },
    mounted(){
      Notify({ type: 'primary',duration: 5000, message: '欢迎使用QUICK闪购！' });      
    },
    methods:{
      onClickLeft(){
        this.$router.push('/')
      }
    }
}
</script>

<style scoped>
  .nav{
    background-color:aqua;
  }
</style>