<template>
<div>
    <!-- 面包屑 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/index2' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item >商品管理</el-breadcrumb-item>
        <el-breadcrumb-item >库存管理</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 搜索筛选 -->
    <el-form :inline="true" :model="reqParam" style="margin:20px">
      <el-form-item label="搜索：">
        <el-input size="small" v-model="reqParam.goods_name" placeholder="商品名称"></el-input>
      </el-form-item>
      <el-form-item label="">
        <el-date-picker
            v-model="reqParam.goods_sale_date"
            type="date" 
            size="small"
            value-format='yyyy-MM-dd'
            placeholder="选择日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button size="small" type="primary" icon="el-icon-search" @click="loadGoodsStockInfo">搜索</el-button>
        <el-button size="small" type="primary" icon="el-icon-refresh" @click="cleanSearchData">清空条件</el-button>
        <el-button size="small" type="primary" icon="el-icon-plus" @click="addGoodsStockMethod">添加</el-button>
      </el-form-item>
    </el-form>

    <!-- 库存列表 -->
    <el-table size="small" :data="goodsStockInfo.goodsStockList" highlight-current-row border style="margin: 10px">
      <el-table-column sortable prop="id" label="库存编号" width="">
      </el-table-column>
      <el-table-column sortable prop="goods_id" label="商品编号" width="">
      </el-table-column>
      <el-table-column sortable prop="goods_name" label="商品名称" width="">
      </el-table-column>
      <el-table-column sortable prop="goods_sale_date" label="销售日期" width="">
      </el-table-column>
      <el-table-column sortable prop="goods_price" label="单价（元）" width="">
        <template slot-scope="scope">
            {{scope.row.goods_price/100}}
        </template> 
      </el-table-column>
      <el-table-column sortable prop="goods_count" label="库存数量" width="">
      </el-table-column>
      <el-table-column align="center" label="操作" width="">
          <template slot-scope="scope">
          <el-button plain type="primary" size="mini" 
            @click="showUpdateDilog(scope.row)" 
            :disabled='scope.row.goods_sale_date==today?false:true'>修改</el-button>
          </template>
      </el-table-column>
    </el-table>

    <!-- 分页组件 -->
    <el-pagination
      background
      layout="prev, pager, next,total,jumper,sizes"
      :current-page="reqParam.pageIndex"
      :page-size="reqParam.pageSize"
      :page-sizes= "[5,10,20]"
      @size-change='pageSizeChange'
      :total="goodsStockInfo.count"
      @current-change='currentIndexChange'
      style="margin:20px">
    </el-pagination>

    <!-- 修改弹窗 -->
    <el-dialog :title="updateGoodsStockOBJ.id+'-'+updateGoodsStockOBJ.goods_name" :visible.sync="updateGoodsStockVisible" width="60%" @close="closeDialog">
      <el-form label-width="120px" :model="updateGoodsStockOBJ" :rules="rules" ref="updateForm">
        <el-form-item label="商品单价(分)" prop="goods_price">
          <el-input size="small" v-model="updateGoodsStockOBJ.goods_price" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="库存数量" prop="goods_count">
          <el-input size="small" v-model="updateGoodsStockOBJ.goods_count" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="updateGoodsStockInfoSubmit(updateGoodsStockOBJ,'updateForm')">提交</el-button>
        <el-button size="small" @click="closeDialog">关闭</el-button>
      </div>
    </el-dialog>

    <!-- 添加弹窗 -->
    <el-dialog title="添加库存" :visible.sync="addGoodsStockVisible" width="40%" @close="closeDialog">
      <el-form label-width="120px" :model="addGoodsStockOBJ" :rules="rules" width='50px' ref="addForm">
        <el-form-item label="销售日期" prop="goods_sale_date">
          <el-input size="small" v-model="addGoodsStockOBJ.goods_sale_date=today" auto-complete="off" disabled></el-input>
        </el-form-item>

        <el-form-item label="商品" prop="goods_id">
          <el-select
            v-model="addGoodsStockOBJ.goods_id"
            size="small"
            :multiple="false"
            filterable
            remote
            reserve-keyword
            placeholder="搜索商品"
            :remote-method="inputSearchGoods"
            :loading="false">
            <el-option
              v-for="item in goodsList"
              :key="item.goods_id"
              :label="item.goods_id+'-'+item.goods_name"
              :value="item.goods_id">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="单位（分）" prop="goods_price">
          <el-input size="small" v-model="addGoodsStockOBJ.goods_price" auto-complete="off" placeholder="输入整数"></el-input>
        </el-form-item>
        <el-form-item label="库存数量" prop="goods_count">
          <el-input size="small" v-model="addGoodsStockOBJ.goods_count" auto-complete="off" placeholder="输入整数"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="addGoodsStockInfoSubmit(addGoodsStockOBJ,'addForm')">提交</el-button>
        <el-button size="small" @click="closeDialog">关闭</el-button>
      </div>
    </el-dialog>

</div>
</template>

<script>
import {req} from '../api/axiosFun'
export default {
    name:'stock',
    data(){
        return {
            addGoodsStockVisible:false, //添加库存弹窗
            updateGoodsStockVisible:false, //修改弹窗显示
            updateGoodsStockOBJ:{},
            today:'',
            goodsStockInfo:{},
            reqParam:{
                goods_sale_date:"",
                goods_name: "",
                pageIndex:1,
                pageSize:5
            },
            addGoodsStockOBJ:{
              goods_id:"",
              goods_name:"",
              goods_price:"",
              goods_count:"",
              goods_sale_date:""
            },
            goodsList:{},//商品列表数据
            rules:{
              goods_id:[{required: true, message: '请选择需要添加库存的商品'}],
              goods_price:[{required: true, message: '商品单价为整数(分)',pattern:/^[1-9]\d*$/}], //商品单价为正整数
              goods_count:[{required: true, message: '库存为整数',pattern:/^[1-9]\d*$/}], //库存为正整数
              }
        }
    },
    mounted(){
        this.today = this.dateFormat()
        this.reqParam.goods_sale_date = this.today
        this.loadGoodsStockInfo()
    },
    methods:{
        loadGoodsStockInfo(){
            req('post','/api2/goods/stock/list/'+JSON.parse(localStorage.getItem("supplierInfo")).supplier_id,this.reqParam)
                .then((res)=>{
                    if (res.resCode=='0000') {
                        this.goodsStockInfo = res.data
                    }else{
                        this.$message.error('获取商品库存失败！')
                    }
                })
        },
        cleanSearchData(){
            this.reqParam.goods_sale_date=''
            this.reqParam.goods_name=''
            this.loadGoodsStockInfo()
        },
        currentIndexChange(val){
            this.reqParam.pageIndex = val
            this.loadGoodsStockInfo()
        },
        pageSizeChange(val){
            this.reqParam.pageSize = val
            this.loadGoodsStockInfo()
        },
        //当前日期格式化
        dateFormat(){
            var date = new Date()
            var year = date.getFullYear()
            var month = date.getMonth() + 1 < 10 ? 
                            '0' + (date.getMonth() + 1) : date.getMonth()+ 1
            var day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate()
            // var hours = date.getHours() < 10 ? '0' + date.getHours() : date.getHours()
            // var minutes = date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()
            // var seconds = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds()
            return year + '-' +month + '-' + day
        },
        closeDialog(){
            this.updateGoodsStockVisible = false
            this.addGoodsStockVisible = false
            this.loadGoodsStockInfo()
        },
        addGoodsStockMethod(){
          this.addGoodsStockVisible = true
        },
        updateGoodsStockInfoSubmit(updateGoodsStockOBJ,updateForm){
            this.$refs[updateForm].validate((valid)=>{
              if (valid) {
                req('put','/api2/goods/stock/update/'+updateGoodsStockOBJ.goods_id+'/'+updateGoodsStockOBJ.id,
                {goods_price:updateGoodsStockOBJ.goods_price,goods_count:updateGoodsStockOBJ.goods_count})
                    .then((res)=>{
                        if (res.resCode=='0000') {
                            this.closeDialog()
                            this.$message.success('修改成功！')
                            this.loadGoodsStockInfo()
                        }else{
                            this.$message.error('修改失败！')
                            this.loadGoodsStockInfo()
                        }
                    })
              }else{
                this.$message.error('表单填写有误！')
              }
            })

            
        },
        showUpdateDilog(row){
            this.updateGoodsStockOBJ = row
            this.updateGoodsStockVisible = true
        },
        inputSearchGoods(value){
          // alert(value)
          // alert(this.addGoodsStockOBJ.goods_name)
          const reqJson = {goods_name:value,pageIndex:1,pageSize:10}
           req('post','/api2/supplier/goods/list/'+JSON.parse(localStorage.getItem('supplierInfo')).supplier_id,reqJson)
            .then((res)=>{
              if (res.resCode == '0000') {
                this.goodsList = res.data.goodsInfos
              }else{
                this.$message.error('查询商品失败！')
              }
            })
        },
        addGoodsStockInfoSubmit(addGoodsStockOBJ,addForm){
          this.$refs[addForm].validate((valid)=>{
            if (valid) {
              req('post','/api2/goods/stock/add/'+addGoodsStockOBJ.goods_id,addGoodsStockOBJ)
                .then((res)=>{
                  if (res.resCode == '0000') {
                    this.$message.success('添加成功！')
                  }else{
                    this.$message.error(res.data)
                  }
                  this.closeDialog()
                })
                .catch(()=>{
                  this.$message.error('添加失败！')
                })
            }else{
              this.$message.error('表单填写有误！')
            }
          })
        }
    }
}
</script>

<style scoped>

</style>