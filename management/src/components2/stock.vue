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
        <el-button size="small" type="primary" icon="el-icon-plus" @click="">添加</el-button>
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
            @click="updateGoodsInfo(scope.row)" 
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

</div>
</template>

<script>
import {req} from '../api/axiosFun'
export default {
    name:'stock',
    data(){
        return {
            today:'',
            goodsStockInfo:{},
            reqParam:{
                goods_sale_date:"",
                goods_name: "",
                pageIndex:1,
                pageSize:5
            }
        }
    },
    mounted(){
        this.today = this.dateFormat()
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
        }
    }
}
</script>

<style scoped>

</style>