<template>
    <div class="update-pwd">
        <el-input placeholder="请输入新密码" v-model="pwd" show-password></el-input>
        <el-button class="updateBtn" @click="updatePwd">修改</el-button>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    name:'password',
    data(){
        return {
            pwd:''
        }
    },
    methods:{
        updatePwd(){
           this.$confirm('确定将密码修改为：'+this.pwd, '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
            })
            .then(() => {
            setTimeout(() => {

                axios({
                    method:'put',
                    url:'/admin/2/update',
                    headers:{'Content-Type':'application/json'},
                    data:{'pwd':this.pwd}
                }).then(res => {
                    console.log(res)
                    this.$message({
                    type: 'success',
                    message: res.data.resMsg
                    })
                    this.pwd = '' //密码修改成功后清空pwd
                })
            }, 1000)})
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
.update-pwd {
    width: 20%;
    margin: 10px;
}
.updateBtn {
    margin-top: 10px;
}
</style>