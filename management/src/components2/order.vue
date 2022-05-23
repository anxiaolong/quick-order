<template>
  <div>
    <!-- 面包屑 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/index2' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item >订单管理</el-breadcrumb-item>
        <el-breadcrumb-item >订单管理</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 订单列表 -->
    <el-table size="small" :data="orderList.orderInfos" highlight-current-row border style="margin: 10px">
      <el-table-column sortable prop="id" label="订单编号" width="100px">
      </el-table-column>
      <el-table-column sortable prop="saleDate" label="销售日期" width="">
      </el-table-column>
      <el-table-column sortable prop="order_id" label="订单号" width="">
      </el-table-column>
      <el-table-column sortable prop="phone" label="下单电话" width="">
      </el-table-column>
      <el-table-column sortable prop="goods_id" label="商品号" width="">
          <template slot-scope="scope">
              <el-link type="primary" @click="showGoodsDialog(scope.row.goods_id)">{{scope.row.goods_id}}</el-link>
          </template>
      </el-table-column>
      <el-table-column sortable prop="total_price" label="总价（元）" width="">
          <template slot-scope="scope">
              <span>{{scope.row.total_price/100}}</span>
          </template>
      </el-table-column>
      <el-table-column sortable prop="status" label="订单状态" width="">
          <template slot-scope="scope">
              <span>{{scope.row.status==0?'新增':
                      scope.row.status==1?'过期':
                      scope.row.status==2?'支付完成':
                      scope.row.status==3?'已取消':
                      scope.row.status==4?'退款中':
                      scope.row.status==5?'已退款':'状态异常'
                  }}</span>
          </template>
      </el-table-column>
 
    </el-table>

    <!-- 商品信息显示弹窗 -->
    <el-dialog
        :title="goodsInfo.goods_id+''"
        :visible.sync="goodsDialogVisible"
        width="30%">
        <el-descriptions title="" direction="vertical" :column="4" border>
            <el-descriptions-item label="商品名">{{goodsInfo.goods_name}}</el-descriptions-item>
            <el-descriptions-item label="简介">{{goodsInfo.goods_intro}}</el-descriptions-item>
        </el-descriptions>

        <span slot="footer" class="dialog-footer">
            <el-button size="small" @click="goodsDialogVisible = false">关 闭</el-button>
        </span>
    </el-dialog>

  </div>

</template>

<script>
import {req} from '../api/axiosFun'
export default {
    name:'order',
    data(){
        return {
            goodsDialogVisible:false,//商品查看弹窗
            orderList:{},
            reqJson:{
                saleDate:'',
                phone:'',
                order_id:'',
                pageIndex:1,
                pageSize:10
            },
            goodsInfo:{},
        }
    },
    mounted(){
        this.loadOrderList()
    },
    methods:{
        loadOrderList(){
            req('post','/api2/order/query/'+JSON.parse(localStorage.getItem('supplierInfo')).supplier_id,this.reqJson)
                .then((res)=>{
                    this.orderList = res.data
                })
        },
        showGoodsDialog(goods_id){
            req('GET','/api2/supplier/goods/get/'+goods_id)
                .then((res)=>{
                    this.goodsInfo = res.data
                })
            this.goodsDialogVisible = true
        }
    }
}
</script>

<style scoped>

</style>