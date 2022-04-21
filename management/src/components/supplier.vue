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
        <el-button size="small" type="primary" icon="el-icon-plus" @click="handleEdit()">添加</el-button>
      </el-form-item>
    </el-form>

    <!-- 列表 -->
    <el-table size="small" :data="listData" highlight-current-row border style="width: 100%;">
      <el-table-column sortable prop="supplier_id" label="编号" width="70">
      </el-table-column>
      <el-table-column sortable prop="supplier_name" label="供应商名称" >
      </el-table-column>
      <el-table-column sortable prop="supplier_code" label="供应商编码">
      </el-table-column>
      <el-table-column sortable prop="link_man" label="联系人">
      </el-table-column>
      <el-table-column sortable prop="phone_number" label="联系电话">
      </el-table-column>
      <el-table-column sortable  label="状态" width="130">
        <template slot-scope="scope">
            {{ scope.row.supplier_status == 1 ? "已启用" : "已禁用" }}
        </template> 
      </el-table-column>
      <el-table-column align="center" label="操作" >
          <template slot-scope="scope">
            <el-button type="primary" size="mini" @click="handleEdit(scope.$index, scope.row)">详情</el-button>
            <el-button type="primary" size="mini" @click="handleEdit(scope.$index, scope.row)">修改</el-button>
            <el-button type="primary" size="mini" @click="handleEdit(scope.$index, scope.row)">启用</el-button>
            <el-button type="primary" size="mini" @click="handleEdit(scope.$index, scope.row)">禁用</el-button>
          </template>
      </el-table-column>
    </el-table>

    <!-- 分页组件 -->

  </div>
</template>

<script>
import {req} from '../api/axiosFun'
export default {
  name:'supplier',
  data() {
    return {
      listData:[],
      reqJson:{
        supplier_name: "",
        link_man: "",
        phone_number: "",
        address: "",
        pageIndex:"1",
        pageSize:"5"
      }
    }
  },
  created(){
    this.loadSupplierList()
    console.log(this.listData)
  },
  methods:{
    loadSupplierList(){
      req('post','/api/supplier/search',this.reqJson).then(res => {
        console.log(res)
        this.listData = res.data.supplierInfos
      })
    }
  }
}
</script>

<style>

</style>