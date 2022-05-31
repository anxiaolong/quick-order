<template>
  <div>
    <van-nav-bar
    style="background-color:aqua"
    title="QUICK闪购"
    />

    <van-dialog 
      title="登录授权"
      v-model='dialogShow'
      :showConfirmButton='false'
      width="250px"
      >
      <van-form v-model="user" ref="userForm" @submit="syncUser(user)">
        <van-field style="width:150px;margin:auto" name="phone" v-model="user.phone" type="tel" placeholder="请输入手机号码" :rules="rules.phone" />
        <div style="margin: 16px;">
          <van-button round block type="info" native-type="submit">确认授权</van-button>
        </div>
      </van-form>
    </van-dialog>
    
  </div>
</template>

<script>
import Cookies from 'js-cookie'
import {Toast} from 'vant'
import {req} from '../api/fun'
import uuidv1 from 'uuid/v1'
export default {
    name:'sync',
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
              phone:[{required: true,message:'输入有误',pattern:/^1[3456789]\d{9}$/,max:11},]
              }
        }
    },
    mounted(){
      try {
        this.user.phone = JSON.parse(Cookies.get('user')).phone
      } catch (error) {

      }
      Cookies.set('channel',JSON.stringify(this.channel),{expires:1})//首页设置渠道
      this.user.uid = uuidv1().toString().replace(/-/g, "") //默认生成一个uid，如果手机号不存在就用这个uid和手机号作为同步数据
      
    },
    methods:{
      syncUser(user){ // 提交表单方法执行前，会自动校验表单，不用再手动校验
        req('post','/api3/customer/sync/user',user,JSON.parse(Cookies.get('channel')))
          .then((res)=>{
            if (res.data.resCode=='0000') {
              this.user = res.data.data //登录成功 把返回的对象复制给user
              Cookies.set('user',JSON.stringify(res.data.data),{expires:1}) //用户数据存到cookie中，有效期一天
              this.$router.push('/index/shopping')            //跳转选择商家列表页面
              Toast.success('同步成功！')
              this.dialogShow = false
            }else{
              Toast.fail('同步失败！')
            }
          })
      }
    }
}
</script>

<style scoped>

</style>