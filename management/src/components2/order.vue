<template>
  <div>
    <!-- 面包屑 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/index2' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item >订单管理</el-breadcrumb-item>
        <el-breadcrumb-item >订单管理</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 搜索控件 -->
    <el-form :inline="true" :model="reqJson" style="margin:20px">
      <el-form-item label="搜索：">
        <el-input size="small" v-model="reqJson.phone" placeholder="手机号"></el-input>
      </el-form-item>
      <el-form-item label="">
        <el-input size="small" v-model="reqJson.order_id" placeholder="订单号"></el-input>
      </el-form-item>
      <el-form-item label="">
        <el-date-picker
            v-model="reqJson.saleDate"
            type="date" 
            size="small"
            value-format='yyyy-MM-dd'
            placeholder="下单日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button size="small" type="primary" icon="el-icon-search" @click="loadOrderList">搜索</el-button>
        <el-button size="small" type="primary" icon="el-icon-refresh" @click="cleanSearchData">清空条件</el-button>
      </el-form-item>
    </el-form>

    <!-- 订单列表 -->
    <el-table size="small" :data="orderList.orderInfos" highlight-current-row border style="margin: 10px">
      <el-table-column sortable prop="id" label="订单编号" width="100px">
      </el-table-column>
      <el-table-column sortable prop="order_id" label="订单号" width="">
          <template slot-scope="scope">
              <el-link type="primary" @click="showOrderDetailDialog(scope.row)">{{scope.row.order_id}}</el-link>
          </template>
      </el-table-column>
      <el-table-column sortable prop="create_time" label="下单时间" width="">
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
      <el-table-column align="center" label="操作" width="200px">
          <template slot-scope="scope">
              <div :hidden="scope.row.status==4?false:true">
                  <el-button plain type="danger" size="mini" @click="refund(scope.row)">同意退款</el-button>
              </div>
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
      :total="orderList.count"
      @current-change='currentIndexChange'
      style="margin:20px">
    </el-pagination>

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

    <!-- 订单详情弹窗 -->
    <el-dialog
        :title="order.order_id"
        :visible.sync="orderDialogVisible"
        width="40%">
        <el-descriptions title="" direction="horizontal" :column="1" border>
            <el-descriptions-item label="订单序号">{{order.id}}</el-descriptions-item>
            <el-descriptions-item label="商品信息">{{goodsInfo.goods_name}}</el-descriptions-item>
            <el-descriptions-item label="数量">{{order.goods_count}}</el-descriptions-item>
            <el-descriptions-item label="备注留言">{{order.tips}}</el-descriptions-item>
            <el-descriptions-item label="下单手机">{{order.phone}}</el-descriptions-item>
            <el-descriptions-item label="UID">{{order.uid}}</el-descriptions-item>
            <el-descriptions-item label="总价">{{order.total_price/100}}元</el-descriptions-item>
            <el-descriptions-item label="销售日期">{{order.saleDate}}</el-descriptions-item>
            <el-descriptions-item label="下单时间">{{order.create_time}}</el-descriptions-item>
            <el-descriptions-item label="最后操作订单时间">{{order.modified_time}}</el-descriptions-item>
            <el-descriptions-item label="状态">{{
                      order.status==0?'新增':
                      order.status==1?'过期':
                      order.status==2?'支付完成':
                      order.status==3?'已取消':
                      order.status==4?'退款中':
                      order.status==5?'已退款':'状态异常'
                }}</el-descriptions-item>

        </el-descriptions>

        <span slot="footer" class="dialog-footer">
            <el-button size="small" @click="orderDialogVisible = false">关 闭</el-button>
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
            orderDialogVisible:false, //订单详情弹窗
            goodsDialogVisible:false,//商品查看弹窗
            orderList:{},
            order:{},//订单详情对象
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
        this.reqJson.saleDate = this.dateFormat()
        this.loadOrderList()
    },
    methods:{
        showOrderDetailDialog(row){
            this.order = row

            req('GET','/api2/supplier/goods/get/'+row.goods_id)
                .then((res)=>{
                    this.goodsInfo = res.data
                })

            this.orderDialogVisible = true
        },
        currentIndexChange(value){
            this.reqJson.pageIndex = value
            this.loadOrderList()
        },
        pageSizeChange(value){
            this.reqJson.pageSize = value
            this.loadOrderList()
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
        dateFormat2(){
            var date = new Date()
            var year = date.getFullYear()
            var month = date.getMonth() + 1 < 10 ? 
                            '0' + (date.getMonth() + 1) : date.getMonth()+ 1
            var day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate()
            var hours = date.getHours() < 10 ? '0' + date.getHours() : date.getHours()
            var minutes = date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()
            var seconds = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds()
            return year + '-' +month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds
        },
        cleanSearchData(){
            this.reqJson.phone = ''
            this.reqJson.saleDate = ''
            this.reqJson.order_id = ''
            this.loadOrderList()
        },
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
        },
        refund(row){
            let refundNotify = {}
            refundNotify.transaction_id = row.order_id
            refundNotify.order_id = row.order_id
            refundNotify.pay_date = this.dateFormat2()
            refundNotify.pay_amount = -(row.total_price)
            refundNotify.pay_status = '1'
            
            this.$confirm('确定执行退款给买家?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
                    }).then(() => {
                        setTimeout(()=>{
                            req('post','/api3/order/notify',refundNotify)
                            .then((res)=>{
                                if (res.resCode == '0000') {
                                    this.$message.success('退款操作成功！')
                                    this.loadOrderList()
                                }else{
                                    this.$message.error('退款操作失败！');
                                }
                            })
                        },1000)
                            
                    }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消'
                    });          
                    });
        },
        
    }
}
</script>

<style scoped>

</style>