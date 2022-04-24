<template>
  <div>
    <!-- 面包屑 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>供应商管理</el-breadcrumb-item>
      <el-breadcrumb-item>供应商管理</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 搜索筛选 -->
    <el-form :inline="true" :model="reqJson" style="margin:20px">
      <el-form-item label="搜索：">
        <el-input size="small" v-model="reqJson.supplier_name" placeholder="供应商名称"></el-input>
      </el-form-item>
      <el-form-item label="">
        <el-input size="small" v-model="reqJson.link_man" placeholder="联系人"></el-input>
      </el-form-item>
      <el-form-item label="">
        <el-input size="small" v-model="reqJson.phone_number" placeholder="联系电话"></el-input>
      </el-form-item>
      <el-form-item label="">
        <el-input size="small" v-model="reqJson.address" placeholder="地址"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button size="small" type="primary" icon="el-icon-search" @click="loadSupplierList">搜索</el-button>
        <el-button size="small" type="primary" icon="el-icon-refresh" @click="cleanSearchData">清空条件</el-button>
        <el-button size="small" type="primary" icon="el-icon-plus" @click="handleEdit">添加</el-button>
      </el-form-item>
    </el-form>

    <!-- 列表 -->
    <el-table size="small" :data="listData.supplierInfos" highlight-current-row border style="width:100%">
      <el-table-column sortable prop="supplier_id" label="编号" width="80">
      </el-table-column>
      <el-table-column sortable prop="supplier_name" label="供应商名称">
      </el-table-column>
      <el-table-column sortable prop="address" label="地址">
      </el-table-column>
      <el-table-column sortable prop="link_man" label="联系人"  >
      </el-table-column>
      <el-table-column sortable prop="phone_number" label="联系电话" >
      </el-table-column>
      <el-table-column sortable  label="供应商类型">
        <template slot-scope="scope">
            {{ scope.row.supplier_type == 0 ? "自营" : "第三方" }}
        </template> 
      </el-table-column>
      <el-table-column sortable  label="状态" >
        <template slot-scope="scope">
            {{ scope.row.supplier_status == 1 ? "已启用" : "已禁用" }}
        </template> 
      </el-table-column>
      <el-table-column align="center" label="操作" width="350px">
          <template slot-scope="scope">
            <el-button plain type="primary" size="mini" @click="details(scope.row)">详情</el-button>
            <el-button plain type="primary" size="mini" @click="updateSupplier(scope.row)">修改</el-button>
            <el-button plain type="danger" size="mini" @click="disableSupplier(scope.row)">启用/禁用</el-button>
          </template>
      </el-table-column>
    </el-table>

    <!-- 分页组件 -->
    <el-pagination
      background
      layout="prev, pager, next,total,jumper,sizes"
      :current-page="reqJson.pageIndex"
      :page-size="reqJson.pageSize"
      :page-sizes= "[5,10,20]"
      @size-change='pageSizeChange'
      :total="listData.count"
      @current-change='currentChangeAfter'
      style="margin:20px"
      >
    </el-pagination>

    <!-- 新增窗口 -->
    <el-dialog title="添加供应商" :visible.sync="addFormVisible" width="30%" @click="closeDialog">
      <el-form label-width="120px" :model="addSupplierJson" :rules="rules" ref="addSupplierJson">
        <el-form-item label="供应商编码" prop="supplier_code">
          <el-input size="small" v-model="addSupplierJson.supplier_code" auto-complete="off" placeholder="请输入供应商编码"></el-input>
        </el-form-item>
        <el-form-item label="供应商名称" prop="supplier_name">
          <el-input size="small" v-model="addSupplierJson.supplier_name" auto-complete="off" placeholder="请输入供应商名称"></el-input>
        </el-form-item>
        <el-form-item label="联系人" prop="link_man">
          <el-input size="small" v-model="addSupplierJson.link_man" auto-complete="off" placeholder="请输入联系人"></el-input>
        </el-form-item>
        <el-form-item label="联系电话" prop="phone_number">
          <el-input size="small" v-model="addSupplierJson.phone_number" auto-complete="off" placeholder="请输入联系电话"></el-input>
        </el-form-item>
        <el-form-item label="开户行" prop="bank_name">
          <el-input size="small" v-model="addSupplierJson.bank_name" auto-complete="off" placeholder="请输入开户行"></el-input>
        </el-form-item>
        <el-form-item label="银行卡号" prop="bank_account">
          <el-input size="small" v-model="addSupplierJson.bank_account" auto-complete="off" placeholder="请输入银行卡号"></el-input>
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input size="small" v-model="addSupplierJson.address" auto-complete="off" placeholder="请输入地址"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="closeDialog">取消</el-button>
        <el-button size="small" type="primary" class="title" @click="addSupplier">保存</el-button>
      </div>
    </el-dialog>

    <!-- 详情窗口 -->
    <el-dialog :title="detailsData.supplier_name" :visible.sync="detailsVisible" width="60%" @click="closeDialog">
      <el-descriptions :border=true size='small'>
          <el-descriptions-item label="供应商ID">{{detailsData.supplier_id}}</el-descriptions-item>
          <el-descriptions-item label="供应商名">{{detailsData.supplier_name}}</el-descriptions-item>
          <el-descriptions-item label="供应商编码">{{detailsData.supplier_code}}</el-descriptions-item>
          <el-descriptions-item label="供应商类型">{{detailsData.supplier_type== 0 ? "自营" : "第三方"}}</el-descriptions-item>
          <el-descriptions-item label="联系人">{{detailsData.link_man}}</el-descriptions-item>
          <el-descriptions-item label="联系电话">{{detailsData.phone_number}}</el-descriptions-item>
          <el-descriptions-item label="银行">{{detailsData.bank_name}}</el-descriptions-item>
          <el-descriptions-item label="银行账号">{{detailsData.bank_account}}</el-descriptions-item>
          <el-descriptions-item label="供应商状态">{{detailsData.supplier_status== 1 ? "已启用" : "已禁用"}}</el-descriptions-item>
          <el-descriptions-item label="供应商地址">{{detailsData.address}}</el-descriptions-item>
      </el-descriptions>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="closeDialog">关闭</el-button>
      </div>
    </el-dialog>

    <!-- 修改窗口 -->
    <el-dialog :title="detailsData.supplier_name" :visible.sync="updateVisible" width="60%" @click="closeDialog">
      <el-form label-width="120px" :model="detailsData" :rules="rules">
        <el-form-item label="供应商ID" prop="supplier_id">
          <el-input size="small" v-model="detailsData.supplier_id" auto-complete="off" disabled></el-input>
        </el-form-item>
        <el-form-item label="供应商编码" prop="supplier_code">
          <el-input size="small" v-model="detailsData.supplier_code" auto-complete="off" disabled></el-input>
        </el-form-item>
        <el-form-item label="供应商状态" prop="supplier_status">
          <el-input size="small" v-model="detailsData.supplier_status== 1 ? '已启用' : '已禁用'" auto-complete="off" disabled></el-input>
        </el-form-item>
        <el-form-item label="供应商名称" prop="supplier_name">
          <el-input size="small" v-model="detailsData.supplier_name" auto-complete="off" placeholder="请输入供应商名称"></el-input>
        </el-form-item>
        <el-form-item label="供应商类型" prop="supplier_type">
          <el-input size="small" v-model.number="detailsData.supplier_type" auto-complete="off" placeholder="0：自营，1：第三方"></el-input>
        </el-form-item>
        <el-form-item label="联系人" prop="link_man">
          <el-input size="small" v-model="detailsData.link_man" auto-complete="off" placeholder="请输入联系人"></el-input>
        </el-form-item>
        <el-form-item label="联系电话" prop="phone_number">
          <el-input size="small" v-model="detailsData.phone_number" auto-complete="off" placeholder="请输入联系电话"></el-input>
        </el-form-item>
        <el-form-item label="开户行" prop="bank_name">
          <el-input size="small" v-model="detailsData.bank_name" auto-complete="off" placeholder="请输入开户行"></el-input>
        </el-form-item>
        <el-form-item label="银行卡号" prop="bank_account">
          <el-input size="small" v-model="detailsData.bank_account" auto-complete="off" placeholder="请输入银行卡号"></el-input>
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input size="small" v-model="detailsData.address" auto-complete="off" placeholder="请输入地址"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="submitUpdate">提交</el-button>
        <el-button size="small" @click="closeDialog">关闭</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import {req} from '../api/axiosFun'
export default {
  name:'supplier',
  data() {
    return {
      updateVisible:false,// 修改窗口
      detailsVisible:false,// 详情查看窗口
      addFormVisible:false, // 添加窗口显示状态
      listData:{}, // 查询供应商响应中data数据
      reqJson:{ // 查询供应商参数
        supplier_name: "",
        link_man: "",
        phone_number: "",
        address: "",
        pageIndex:1,
        pageSize:5
      },
      addSupplierJson:{ // 添加供应商参数
        supplier_code: "",
        supplier_name: "",
        link_man: "",
        phone_number: "",
        bank_name: "",
        bank_account: "",
        address: ""
      },
      detailsData:{}, // 详情页数据
      rules:{ // 新增供应商form校验
        
      }
    }
  },
  created(){
    this.loadSupplierList()
    // console.log(this.listData)
  },
  methods:{
    // 提交修改
    submitUpdate(){
      req('put','/api/supplier/update/'+this.detailsData.supplier_code,this.detailsData)
        .then(res=>{
          if (res.resCode == '0000') {
            this.$message.success('修改成功')
            this.closeDialog()
          }else{
            this.$message.error('修改失败')
          }
          this.closeDialog()
        })
    },


    // 修改供应商信息
    updateSupplier(row){
      // console.log(row)
      this.updateVisible=true
      this.detailsData=row
    },

    // 查看供应商详情页
    details(row){
      this.detailsVisible = true
      // console.log(row)
      this.detailsData = row
    },

    // 分页组件切换不同页面
    currentChangeAfter(val){
      // alert(val)
      this.reqJson.pageIndex = val
      this.loadSupplierList()
    },

    // 关闭窗口
    closeDialog(){
      this.addFormVisible = false
      this.detailsVisible = false
      this.updateVisible = false
    },

    // 刷新供应商列表数据
    loadSupplierList(){
      req('post','/api/supplier/search',this.reqJson).then(res => {
        // console.log(res)
        this.listData = res.data
      })
    },
    handleEdit: function(){
        this.addFormVisible = true
    },

    // 添加供应商逻辑
    addSupplier(){
      req('post','/api/supplier/add',this.addSupplierJson)
      .then(res => {
          if (res.resCode == '0000') {
            this.$message.success('添加成功')
            this.loadSupplierList()
          }else{
            this.$message.error('添加失败')
          }

          this.closeDialog()
        })
    },

    // 启用/禁用供应商
    disableSupplier(row){
      // alert(row.supplier_status)
      // alert(row.supplier_code)

      const isDisable = {isDisable: row.supplier_status==1?true:false}

      this.$confirm('确定要 启用/禁用 供应商？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
          })
          .then(() => {
            req('put','/api/supplier/disable/'+row.supplier_code,isDisable).then(res=>{
              if (res.resCode == '0000') {
                  this.$message.success(res.resMsg)
                  this.loadSupplierList()
                }else{
                  this.$message.error(res.resMsg)
                }
            })
          })
          .catch(() => {
          this.$message({
              type: 'info',
              message: '已取消'
          })
          })


      
    },

    // 清空搜索条件 刷新列表数据
    cleanSearchData(){
      this.reqJson.supplier_name = ''
      this.reqJson.link_man = ''
      this.reqJson.phone_number = ''
      this.reqJson.address = ''
      this.loadSupplierList()
    },

    pageSizeChange(val){
      this.reqJson.pageSize = val
      this.loadSupplierList()
    }
  }
}
</script>

<style>

</style>