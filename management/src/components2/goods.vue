<template>
  <div class="goodsDiv">
    <!-- 面包屑 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/index2' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>商品管理</el-breadcrumb-item>
        <el-breadcrumb-item>商品管理</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 搜索筛选 -->
    <el-form :inline="true" :model="reqJson" style="margin:20px">
      <el-form-item label="搜索：">
        <el-input size="small" v-model="reqJson.goods_name" placeholder="商品名称"></el-input>
      </el-form-item>
      <el-form-item label="">
        <el-input size="small" v-model="reqJson.goods_intro" placeholder="商品简介"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button size="small" type="primary" icon="el-icon-search" @click="loadGoodsList">搜索</el-button>
        <el-button size="small" type="primary" icon="el-icon-refresh" @click="cleanSearchData">清空条件</el-button>
        <el-button size="small" type="primary" icon="el-icon-plus" @click="addGoods">添加</el-button>
      </el-form-item>
    </el-form>

    <!-- 新增窗口 -->
    <el-dialog title="添加商品" :visible.sync="addGoodsVisible" width="30%" @click="closeDialog">
      <el-form label-width="120px" :model="addGoodsJson" :rules="rules" ref="addGoodsJson">
        <el-form-item label="商品名称" prop="goods_name">
          <el-input size="small" v-model="addGoodsJson.goods_name" auto-complete="off" placeholder="请输入商品名称"></el-input>
        </el-form-item>
        <el-form-item label="商品简介" prop="goods_name">
          <el-input
            type="textarea"
            :rows="2"
            placeholder="请输入商品简介"
            v-model="addGoodsJson.goods_intro">
          </el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="closeDialog">取消</el-button>
        <el-button size="small" type="primary" class="title" @click="addGoodsSubmit">保存</el-button>
      </div>
    </el-dialog>

    <!-- 商品列表 -->
    <el-table size="small" :data="goodsListInfo.goodsInfos" highlight-current-row border style="margin: 10px">
    <el-table-column sortable prop="goods_id" label="商品编号" width="100px">
    </el-table-column>
    <el-table-column sortable prop="goods_name" label="商品名称" >
    </el-table-column>
    <el-table-column sortable prop="goods_intro" label="商品简介">
    </el-table-column>
    <el-table-column sortable prop="goods_status" label="商品状态">
      <template slot-scope="scope">
            {{ scope.row.goods_status == 1 ? "上架" : "下架" }}
      </template> 
    </el-table-column>
    <el-table-column align="center" label="操作" min-width="">
        <template slot-scope="scope">
        <el-button plain type="primary" size="mini" @click="">修改</el-button>
        <el-button plain type="danger" size="mini" @click="">上架/下架</el-button>
        </template>
    </el-table-column>
    </el-table>

  </div>
</template>

<script>
import {req} from '../api/axiosFun'
export default {
    name:'goods',
    data(){
        return {
          addGoodsVisible:false, // 添加商品弹窗状态
          goodsListInfo:{}, // 商品列表数据
          supplierInfo:JSON.parse(localStorage.getItem('supplierInfo')), //供应商信息
          reqJson:{ // 查询商品列表参数
            goods_name:"",
            goods_intro:"",
            pageIndex:"1",
            pageSize:"10"
          },
          addGoodsJson:{
            goods_name:"",
            goods_intro:""
          },
          rules:{}
        }
    },
    created(){
      this.loadGoodsList()
    },
    methods:{
      // 加载商品列表
      loadGoodsList(){
        req('post','/api2/supplier/goods/list/'+this.supplierInfo.supplier_id,this.reqJson)
        .then((res)=>{
          if (res.resCode == '0000') {
            this.goodsListInfo = res.data
          }else{
            this.$message.error('获取商品列表失败')
          }
        })
      },

      // 清空搜索
      cleanSearchData(){
        this.reqJson.goods_name=''
        this.reqJson.goods_intro=''
        this.loadGoodsList()
      },

      addGoods(){
        this.addGoodsVisible=true
      },
      closeDialog(){
        this.addGoodsVisible=false
      },
      addGoodsSubmit(){
        req('POST','/api2/supplier/goods/add/'+this.supplierInfo.supplier_id,this.addGoodsJson)
          .then(res=>{
            if (res.resCode == '0000') {
              this.closeDialog()
              this.$message.success('添加成功')
              this.loadGoodsList()
            }else{
              this.$message.error('添加失败')
            }
          })
          .catch(()=>{
            this.$message.error('添加失败')
          })
      }
    }

}
</script>

<style>

</style>