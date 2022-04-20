<template>
    <div class="managementadmin">
        <!-- 面包屑 -->
        <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>超级用户管理</el-breadcrumb-item>
        <el-breadcrumb-item>用户管理</el-breadcrumb-item>
        </el-breadcrumb>

        <!-- 列表 -->
        <el-table size="small" :data="listData" highlight-current-row border style="width: 80%;margin: 10px">
        <el-table-column sortable prop="id" label="编号" width="300">
        </el-table-column>
        <el-table-column sortable prop="uname" label="用户" width="300">
        </el-table-column>
        <el-table-column sortable prop="pwd" label="密码" width="300">
        </el-table-column>
        <el-table-column align="center" label="操作" min-width="300">
            <template slot-scope="scope">
            <el-button type="primary" size="mini" @click="handleEdit(scope.$index, scope.row)">修改密码</el-button>
            </template>
        </el-table-column>
        </el-table>

        <!-- 修改密码界面 -->
        <el-dialog title="修改密码" :visible.sync="editFormVisible" width="30%">
        <el-form label-width="120px" :model="editForm" :rules="rules" ref="editForm">
            <el-form-item label="编码" prop="id">
            <el-input size="small" v-model="editForm.id" auto-complete="off" placeholder="" disabled></el-input>
            </el-form-item>
            <el-form-item label="用户" prop="uname">
            <el-input size="small" v-model="editForm.uname" auto-complete="off" placeholder="" disabled></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="pwd">
            <el-input size="small" v-model="editForm.pwd" auto-complete="off" placeholder=""></el-input>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button size="small" @click="closeDialog">取消</el-button>
            <el-button size="small" type="primary" class="title" @click="updatePwd">保存</el-button>
        </div>
        </el-dialog>
    </div>
</template>

<script>
import axios from '../ajax/axios'
export default {
    name:'managementadmin',
    data(){
        return {
            pwd:'',
            listData:[],
            editFormVisible:false,
            editForm: {
                id: '',
                uname: '',
                pwd: ''
            },
            rules: {
                pwd: [{ required: true, message: '请输入新密码', trigger: 'blur' }]
            }
        }
    },
    created() {
        this.loadManagemenAdminList()
    },

    methods:{
        loadManagemenAdminList(){
            axios({
                method:'POST',
                url:'/admin/list/get',
                headers:{'Content-Type':'application/json'},
                data:""
            }).then(res => {
                // console.log(res.data.data)
                this.listData = res.data.data
            })
        },

        updatePwd(){
           this.$confirm('确定将密码修改为：'+this.editForm.pwd, '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
            })
            .then(() => {
            setTimeout(() => {

                axios({
                    method:'put',
                    url:'/admin/'+this.editForm.id+'/update',
                    headers:{'Content-Type':'application/json'},
                    data:{'pwd':this.editForm.pwd}
                }).then(res => {
                    // console.log(res)
                    this.$message({
                    type: 'success',
                    message: res.data.resMsg
                    })
                    this.closeDialog()
                    this.loadManagemenAdminList()
                })
            }, 1000)})
            .catch(() => {
            this.$message({
                type: 'info',
                message: '已取消'
            })
            })
            
        },

        handleEdit: function(index,row){
            this.editFormVisible = true
            this.editForm.id = row.id
            this.editForm.uname = row.uname
            this.editForm.pwd = row.pwd
        },

        // 关闭编辑窗
        closeDialog(){
            this.editFormVisible = false
        }
    }
}
</script>

<style scoped>

</style>