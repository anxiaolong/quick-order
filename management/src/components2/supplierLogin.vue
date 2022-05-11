<template>
    <div class="login-wrap">
    <el-form label-position="left" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="0px" class="login-container">

      <h3 class="title">供应商-登录</h3>

      <el-form-item prop="uname"> 
        <el-input type="text" v-model="ruleForm.phone" auto-complete="off" placeholder="手机号"></el-input>
      </el-form-item>

      <el-row>
        <el-col :span="12">
          <el-form-item prop="code">
            <el-input type="text" v-model="ruleForm.verificationCode" auto-complete="off" placeholder="输入验证码"></el-input>
          </el-form-item>
        </el-col>
        <el-button style="margin-left:30px" type="primary" :disabled='isDisableButton' @click="sendMsgCode">{{buttonName}}</el-button>
      </el-row>

      <el-form-item style="width:100%;">
        <el-button type="primary" style="width:100%;" @click="submitForm('ruleForm')">登录</el-button>
      </el-form-item>

    </el-form>
    </div>
</template>

<script>
// 引入axios发送请求
import {req} from '../api/axiosFun'
export default {
    name:'supplierLogin',

    data() {
        return {
          buttonName:'发送短信验证码',
          isDisableButton:false, // 发送验证码按钮状态
          ruleForm:{
            phone:'',
            verificationCode:''
          },
          rules:{}
        }
    },

    // vue对象创建后要执行的操作
    created(){
      setTimeout(()=>{
        this.$message.info('欢迎使用！')
      })
    },

    methods: {
        // 提交表单方法
        submitForm(formName){
        this.$refs[formName].validate(valid => {
            const res = req('post','/api2/supplier/login',this.ruleForm)

            res.then(res=>{ 
            if (res.resCode=='0000') {
              localStorage.setItem('uname',this.ruleForm.phone)
              localStorage.setItem('supplierInfo',JSON.stringify(res.data))
                setTimeout(()=>{
                  this.$message({type:'success',message:'登录成功'})
                    this.$router.push('index2/goods')
                },1000)
            }else{
                this.$message({type:'error',message:'登录失败'})
            }
            })
        })
        },

        // 发送短信验证码
        sendMsgCode(){
          req('post','/api2/supplier/getVerificationCode',{"phone":this.ruleForm.phone})
            .then((res)=>{
              if (res.resCode=='0000') {
                this.$message.success('发送成功！')
              }else{
                this.$message.error(res.resMsg);
              }

              this.isDisableButton=true
              var i = 120
              const sh = setInterval(()=>{
                i--
                this.buttonName = i + '秒后可重试'
                if (i==0) {
                  clearInterval(sh)
                  this.isDisableButton=false
                  this.buttonName = '发送短信验证码'
                }
              },1000)
            })
            .catch(()=>{
              this.$message.error('服务异常！');
            })

          
        }
    }

}
</script>

<style scoped>
.login-wrap {
  box-sizing: border-box;
  width: 100%;
  height: 100%;
  padding-top: 10%;
  background-image: url('../assets/img/bg.png');
  /* background-color: #112346; */
  background-repeat: no-repeat;
  background-position: center right;
  background-size: 100%;
}
.login-container {
  border-radius: 10px;
  margin: 0px auto;
  width: 350px;
  padding: 30px 35px 15px 35px;
  background: rgb(148, 189, 216);
  border: 1px solid #eaeaea;
  text-align: left;
  box-shadow: 0 0 20px 2px rgba(0, 0, 0, 0.1);
}
.title {
  margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;
}
</style>
