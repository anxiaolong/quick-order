<template>
  <el-menu mode="horizontal" background-color="#334157" text-color="#fff" active-text-color="#fff">
    <el-submenu index='1' class="submenu2">
      <template slot="title">{{supplierInfo.supplier_name}}</template>
      <el-menu-item @click="exit">退出</el-menu-item>
    </el-submenu>
  </el-menu>
</template>
<script>
import {req} from '../api/axiosFun'
export default {
  name: 'navcon2',
  data() {
    return {
      supplierInfo: JSON.parse(localStorage.getItem('supplierInfo')),
      user: {}
    }
  },
  // 创建完毕状态(里面是操作)
  created() {
    this.user = JSON.parse(localStorage.getItem('userdata'))
  },
  methods: {
    // 调用后台接口退出登录
    logout(){
      req('post','/api/admin/logout','')
      localStorage.removeItem('uname')
      localStorage.removeItem('supplierInfo')
    },

    // 退出登录
    exit() {
      this.$confirm('退出登录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          setTimeout(() => {
            this.logout()
            this.$router.push({ path: '/supplier/login' })
            this.$message({
              type: 'success',
              message: '已退出登录!'
            })
          }, 1000)
          
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消'
          })
        })
    }
  }
}
</script>
<style scoped>
.submenu2 {
  float: right;
}
.buttonimg {
  height: 60px;
  background-color: transparent;
  border: none;
}
</style>