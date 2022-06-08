<template>
  <div>
      <van-notice-bar
      :scrollable='true'
      left-icon="volume-o"
      text="仅展示最近30个订单，如果疑问联系商家！"
      />
      <div v-for='(item,i) in myOrderList'>
          <van-card
            style="text-align:left"
            :title="'订单号：'+item.order_id"
            :thumb="imgArr[0].defaultImg">

            <template #desc>
                <p>{{item.supplier_name}}：<a :href="'tel:'+item.supplier_phone">{{item.supplier_phone}}</a></p>
                <p>手机：{{item.phone}}</p>
                <p style="font-weight:bold;color:red;">{{item.goods_name}} x {{item.goods_count}} = {{item.total_price/100}}元</p>
                <p :hidden="item.tips==''?true:false">留言：{{item.tips}}</p>
                <p>{{item.create_time}}</p>
            </template>

            <template #bottom>
                <p style="font-weight:bold;color:red;">
                  {{item.status==0?'新增订单'
                  :item.status==1?'已过期'
                  :item.status==2?'已支付'
                  :item.status==3?'已取消'
                  :item.status==4?'退款中 待商家处理'
                  :item.status==5?'已退款':'订单异常'
                  }}
                </p>
                <van-button v-show="item.status==0?true:false" :round=true size="small" type="danger" @click="cancel(item)">取消订单</van-button>
                <van-button v-show="item.status==0?true:false" :round=true size="small" type="danger" @click="pay(item)">立即支付</van-button>
                <van-button v-show="item.status==2?true:false" :round=true size="small" type="danger" @click="refund(item)">申请退款</van-button>
            </template>


          </van-card>
      </div>

  </div>
</template>

<script>
import axios from 'axios'
import { Toast } from 'vant'
import {req,now,getOrderId} from '../api/fun'
import Cookies from 'js-cookie'
export default {
    name:'my',
    data(){
      return{
        myOrderList:[],
        imgArr:[
                {defaultImg:require('../img/default00.png')}
                ]
      }
    },
    mounted(){
      req('post','/api3/order/list',{phone:JSON.parse(Cookies.get('user')).phone},JSON.parse(Cookies.get('channel')))
        .then((res)=>{
          if (res.data.resCode =='0000') {
            this.myOrderList = res.data.data
            // console.log(res.data.data)
          }
        })
    },
    methods:{
      pay(val){
        //console.log(JSON.stringify(val))
        let payData = {}
        payData.transaction_id = getOrderId(JSON.parse(Cookies.get('channel')))
        payData.order_id = val.order_id
        payData.pay_date = now()
        payData.pay_amount = val.total_price
        axios({
            method:'post',
            url:'/api3/order/notify',
            Headers:{'Content-Type':'application/json'},
            data:payData
            })
            .then((res)=>{
                if (res.data.resCode == '0000') {
                    Toast.success('支付成功')
                    setTimeout(()=>{
                        this.$router.go(0) //刷新当前页面
                    },1000)
                }else{
                    Toast.fail('支付失败')
                }
            })
      },
      cancel(val){
        req('post','/api3/order/cancel',{order_id:val.order_id},JSON.parse(Cookies.get('channel')))
          .then((res)=>{
                  if (res.data.resCode == '0000') {
                      Toast.success('取消成功')
                      setTimeout(()=>{
                          this.$router.go(0) //刷新当前页面
                      },1000)
                  }else{
                      Toast.fail('取消失败')
                  }
              })
      },
      refund(val){
        req('post','/api3/order/refund',{order_id:val.order_id},JSON.parse(Cookies.get('channel')))
          .then((res)=>{
                  if (res.data.resCode == '0000') {
                      Toast.success('申请成功')
                      setTimeout(()=>{
                          this.$router.go(0) //刷新当前页面
                      },1000)
                  }else{
                      Toast.fail('申请失败')
                  }
              })
      }
    }
}
</script>

<style>

</style>