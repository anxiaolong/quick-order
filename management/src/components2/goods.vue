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
      <el-table-column sortable prop="goods_name" label="商品名称" width="200px">
      </el-table-column>
      <el-table-column sortable prop="goods_intro" label="商品简介">
      </el-table-column>
      <el-table-column sortable prop="goods_status" label="商品状态" width="200px">
        <template slot-scope="scope">
              {{ scope.row.goods_status == 1 ? "上架" : "下架" }}
        </template> 
      </el-table-column>
      <el-table-column align="center" label="操作" width="300px">
          <template slot-scope="scope">
          <el-button plain type="primary" size="mini" @click="updateGoodsInfo(scope.row)">修改</el-button>
          <el-button plain type="danger" size="mini" @click="isDisableGoods(scope.row)">上架/下架</el-button>
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
      :total="goodsListInfo.count"
      @current-change='currentChangeAfter'
      style="margin:20px"
      >
    </el-pagination>

    <!-- 修改弹窗 -->
    <el-dialog :title="updateGoodsInfoData.goods_id+''" :visible.sync="updateGoodsVisible" width="60%" @click="closeDialog">
      <el-form label-width="120px" :model="updateGoodsInfoData" :rules="rules">
        <el-form-item label="商品名称" prop="goods_name">
          <el-input size="small" v-model="updateGoodsInfoData.goods_name" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="简介" prop="goods_intro">
          <el-input type="textarea" size="small" v-model="updateGoodsInfoData.goods_intro" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="updateGoodsInfoSubmit(updateGoodsInfoData.goods_id)">提交</el-button>
        <el-button size="small" @click="closeDialog">关闭</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import {req} from '../api/axiosFun'
export default {
    name:'goods',
    data(){
        return {
          addGoodsVisible:false, // 添加商品弹窗状态
          updateGoodsVisible:false, // 修改商品信息弹窗
          goodsListInfo:{}, // 商品列表数据
          updateGoodsInfoData:{}, // 修改商品信息行数据
          supplierInfo:JSON.parse(localStorage.getItem('supplierInfo')), //供应商信息
          reqJson:{ // 查询商品列表参数
            goods_name:"",
            goods_intro:"",
            pageIndex:1,
            pageSize:5
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
      updateGoodsInfo(row){
        this.updateGoodsVisible=true
        this.updateGoodsInfoData = row
      },
      closeDialog(){
        this.addGoodsVisible=false
        this.updateGoodsVisible = false

        setTimeout(()=>{
          this.loadGoodsList()
        },500)
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
      },
      isDisableGoods(row){
        this.$confirm('确定要 上架/下架 商品？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
          })
          .then(() => {
            req("post","/api2/supplier/goods/isDisable/"+row.goods_id,{"isDisable" : row.goods_status==1?true:false })
            .then((res)=>{
              if (res.resCode=='0000') {
                this.$message.success("操作成功！")
                this.loadGoodsList()
              }else{
                this.$message.error("操作失败！")
              }
            })
            .catch(()=>{
              this.$message.error("操作失败！")
            })
          })
          .catch(() => {
          this.$message({
              type: 'info',
              message: '已取消'
          })
          })
      },
      pageSizeChange(val){
        this.reqJson.pageSize = val
        this.loadGoodsList()
      },
      currentChangeAfter(val){
        this.reqJson.pageIndex = val
        this.loadGoodsList()
      },
      updateGoodsInfoSubmit(goodsId){
        req("post",'/api2/supplier/goods/update/'+JSON.parse(localStorage.getItem('supplierInfo')).supplier_id+'/'+goodsId,this.updateGoodsInfoData)
          .then((res)=>{
            if (res.resCode=='0000') {
              this.$message.success('操作成功！')
            }else{
              this.$message.error('操作失败！')
            }
          })
          .catch(()=>{
            this.$message.error('操作失败！')
          })

          this.closeDialog()
      }
    }

}
</script>

<style>

</style>