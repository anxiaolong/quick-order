<template>
  <div class="index">
    <van-nav-bar
    title="quick-order"
    />

    <van-dialog 
      title="登录授权"
      v-model='dialogShow'
      :showConfirmButton='false'
      width="250px"
      >
      <van-form v-model="user" ref="userForm" @submit="syncUser(user)">
        <van-field v-model="user.uid" type="text" :disabled='true' />
        <van-field name="phone" v-model="user.phone" type="tel" placeholder="请输入手机号码" :rules="rules.phone" />
        <div style="margin: 16px;">
          <van-button round block type="info" native-type="submit">确认授权</van-button>
        </div>
      </van-form>
    </van-dialog>

    <router-view></router-view>

    <van-tabbar v-model="active">
        <van-tabbar-item icon="home-o">标签</van-tabbar-item>
        <van-tabbar-item icon="search">标签</van-tabbar-item>
        <van-tabbar-item icon="friends-o">标签</van-tabbar-item>
        <van-tabbar-item icon="setting-o">标签</van-tabbar-item>
    </van-tabbar>

    
  </div>
</template>

<script>
import {Toast} from 'vant'
import {req} from '../api/fun'
import uuidv1 from 'uuid/v1'
import Vue from 'vue'
export default {
    name:'index',
    props:['partner'],
    data(){
        return {
            channel:{//定义全局渠道
              partner:'10011',
              key:'817d285681a34b3794461ab4b0396d73'
            },
            dialogShow:true,
            active:0,
            user:{uid:'',phone:''},
            rules:{
              phone:[{required: true,message:'请输入正确手机号',pattern:/^1[3456789]\d{9}$/}]
              }
        }
    },
    mounted(){
      try {
        this.user.phone = JSON.parse(localStorage.getItem('user')).phone
      } catch (error) {

      }

      Vue.prototype.$channel = this.channel //首页设置渠道
      this.user.uid = uuidv1().toString().replace(/-/g, "")
      
    },
    methods:{
      syncUser(user){ // 提交表单方法执行前，会自动校验表单，不用再手动校验
        // console.log(user)
        // console.log(this.$channel)
        req('post','/api3/customer/sync/user',user,this.$channel)
          .then((res)=>{
            if (res.data.resCode=='0000') {
              localStorage.setItem('user',JSON.stringify(user))
              Toast.success('同步成功！')
            }else{
              Toast.fail('同步失败！')
            }
          })

        this.dialogShow = false
      }
    }
}
</script>

<style scoped>

</style>