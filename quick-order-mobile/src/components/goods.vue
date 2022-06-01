<template>
  <div>
      <van-nav-bar
      :title="supplierName"
      class="nav"
      left-text=""
      left-arrow
      @click-left="onClickLeft"/>
      
      <div v-for='(item,i) in goodsList'>
          <van-card
            @click="onClickCard(item)"
            style="text-align:left"
            :tag="Math.random()*10>4?'热卖':'限量'"
            :origin-price="parseFloat(item.price*(Math.random()+1)/100).toFixed(2)"
            :num='item.goodsCount'
            :price='item.price/100'
            :desc='item.goods_intro'
            :title='item.goodsName'
            :thumb="imgArr[Math.ceil(Math.random()*8)].defaultImg">

            <template #tags>
                <van-tag plain type="danger">{{Math.random()*10>4?'抢购中': '再售'+(Math.ceil(Math.random()*10+1))+'份恢复原价' }}</van-tag>
            </template>
          </van-card>
      </div>


  </div>
</template>

<script>
import { Toast } from 'vant'
import {req} from '../api/fun'
import Cookies from 'js-cookie'
export default {
    name:'goods',
    data(){
        return{
            supplierName:'',
            imgArr:[
                {defaultImg:require('../img/default00.png')},
                {defaultImg:require('../img/default01.png')},
                {defaultImg:require('../img/default02.png')},
                {defaultImg:require('../img/default03.png')},
                {defaultImg:require('../img/default04.png')},
                {defaultImg:require('../img/default05.png')},
                {defaultImg:require('../img/default06.png')},
                {defaultImg:require('../img/default07.png')},
                {defaultImg:require('../img/default08.png')}
                ],
            reqJson:{
                date:'',
                supplierId:''
            },
            goodsList:{}
        }
    },
    mounted(){
        // alert(this.$route.query.supplier)
        const supplier =  this.$route.query.supplier;
        const supplierArr  = supplier.toString().split('-') 
        this.reqJson.supplierId = supplierArr[0]
        this.supplierName = supplierArr[1]
        this.reqJson.date = this.dateFormat()
        this.loadGoodsList()
    },
    methods:{
        onClickLeft(){
            this.$router.push('/index/shopping')
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
        loadGoodsList(){
            req('post','/api3/stock/query/today',this.reqJson,JSON.parse(Cookies.get('channel')))
                .then((res)=>{
                    this.goodsList = res.data.data
                    if (res.data.data=='') {
                        Toast('无可售商品，重新选个商家吧！')
                        setTimeout(()=>{
                            this.$router.push('/index/shopping')
                        },3000)
                    }
                })
        },
        onClickCard(goods){
            console.log(goods)
        }
    }
}
</script>

<style>

</style>