<template>
    <div class="login-wrap">
    <el-form label-position="left" :model="ruleForm" :rules="rules" ref="loginForm" label-width="0px" class="login-container">

      <h3 class="title">供应商-登录</h3>

      <el-form-item prop="phone"> 
        <el-input type="text" v-model="ruleForm.phone" auto-complete="off" placeholder="手机号"></el-input>
      </el-form-item>

      <el-row>
        <el-col :span="12">
          <el-form-item prop="verificationCode">
            <el-input type="text" v-model="ruleForm.verificationCode" auto-complete="off" placeholder="输入验证码"></el-input>
          </el-form-item>
        </el-col>
        <el-button style="margin-left:30px" type="primary" :disabled='isDisableButton' @click="sendMsgCode">{{buttonName}}</el-button>
      </el-row>

      <el-form-item style="width:100%;">
        <el-button type="primary" style="width:100%;" @click="loginButtonMethod('loginForm','slideverify')">登录</el-button>
      </el-form-item>

    </el-form>

    <el-dialog
      title="滑动验证"
      :visible.sync="dialogVisible"
      width="350px">
      
      <slide-verify
          :l="42"
          :r="10"
          :w="310"
          :h="155"
          :imgs="picArray"
          :show="true"
          slider-text="向右滑动完成验证"
          ref="slideverify"
          @success="submitForm('slideverify')"
        ></slide-verify>
    </el-dialog>

    </div>
</template>

<script>
// 引入axios发送请求
import {req} from '../api/axiosFun'
export default {
    name:'supplierLogin',

    data() {
        return {
          dialogVisible:false,
          buttonName:'发送短信验证码',
          isDisableButton:false, // 发送验证码按钮状态
          ruleForm:{
            phone:'',
            verificationCode:''
          },
          rules:{
            phone:[{required: true, message:'请输入手机号码', trigger:'blur',pattern:/^1[3456789]\d{9}$/}], //11位符合规则的手机号
            verificationCode:[{required: true, message:'请输入验证码', trigger: 'blur',pattern:/^\d{6}$/}] //6位数字验证码
            },
          picArray: [
          require("@/assets/verify/1.png"),
          require("@/assets/verify/2.png"),
          require("@/assets/verify/3.png"),
          require("@/assets/verify/4.png"),
          require("@/assets/verify/5.png")
        ],
        }
    },

    // vue对象创建后要执行的操作
    created(){
      setTimeout(()=>{
        this.$message.info('欢迎使用！')
      })
    },

    methods: {
        loginButtonMethod(loginForm){
          // 滑动验证前进行表单验证
          this.$refs[loginForm].validate((valid)=>{
            if (valid) {
              this.dialogVisible = true
            }else{
              this.$message.error('表单填写有误！')
            }
          })
        },

        // 提交表单方法
        submitForm(slideverify){
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
        
              this.dialogVisible = false;
              this.$refs[slideverify].reset()

        },

        // 发送短信验证码
        sendMsgCode(){
          if (/^1[3456789]\d{9}$/.test(this.ruleForm.phone)) {
            req('post','/api2/supplier/getVerificationCode',{"phone":this.ruleForm.phone})
            .then((res)=>{
              if (res.resCode=='0000') {
                this.$message.success('发送成功！')
              }else{
                this.$message.error(res.data);
                return // 发送验证码失败 方法直接结束
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
          }else{
            this.$message.error('请输入正确的手机号！')
          }
          
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
