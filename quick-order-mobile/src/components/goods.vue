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
            tag="招牌"
            :origin-price="item.price*1.2/100"
            :num='item.goodsCount'
            :price='item.price/100'
            :title='item.goodsName'
            :thumb="imgArr[0].defaultImg">

            <!-- 用自定义描述把商品简介展示完整 -->
            <template #desc>
                <p>{{item.goods_intro}}</p>
            </template>

            <template #tags>
                <van-tag plain type="danger">热销</van-tag>
            </template>
    
          </van-card>
      </div>

        <van-sku
        v-model="vantsku.show"
        :sku="vantsku.sku"
        :goods="vantsku.goods"
        :goods-id="skugoods.goodsId"
        :quota="10"
        :quota-used="0"
        :hide-stock="false"
        :show-add-cart-btn='false'
        @buy-clicked="onBuyClicked"
        />

  </div>
</template>

<script>
import { Toast } from 'vant'
import {req,today,getOrderId} from '../api/fun'
import Cookies from 'js-cookie'
export default {
    name:'goods',
    data(){
        return{
            supplierName:'',
            imgArr:[
                {defaultImg:require('../img/default00.png')}
                ],
            reqJson:{
                date:'',
                supplierId:''
            },
            goodsList:{},
            skugoods:{},
            vantsku:{
                show:false,
                goods: {
                    // 默认商品 sku 缩略图
                    picture:require('../img/default00.png')
                    },
                sku: {
                    tree: [],
                    price: 0, // 默认价格（单位元）
                    stock_num: 0, // 商品总库存
                    messages: [
                        {
                        // 商品留言
                        multiple: '0', // 留言类型为 text 时，是否多行文本。'1' 表示多行
                        name: '备注', // 留言名称
                        type: 'text', // 留言类型，可选: id_no（身份证）, text, tel, date, time, email
                        required: '0', // 是否必填 '1' 表示必填
                        placeholder: '可留言给商家', // 可选值，占位文本
                        extraDesc: ''  // 可选值，附加描述文案
                        }
                    ]
                    }
            },
            orderReqData:{//下单参数
                order_id:"",
                goods_id:"",
                goods_count:"",
                saleDate:"",
                total_price:"",
                uid:"",
                phone:"",
                tips:''
            }
        }
    },
    mounted(){
        // alert(this.$route.query.supplier)
        const supplier =  this.$route.query.supplier;
        const supplierArr  = supplier.toString().split('-') 
        this.reqJson.supplierId = supplierArr[0]
        this.supplierName = supplierArr[1]
        this.reqJson.date = today()
        this.loadGoodsList()
    },
    methods:{
        onClickLeft(){
            this.$router.push('/index/shopping')
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
        onClickCard(item){
            //console.log(item)
            this.skugoods = item
            this.vantsku.sku.stock_num = item.goodsCount
            this.vantsku.sku.price = item.price/100
            this.vantsku.show = true
            
        },
        onBuyClicked(val){
            //console.log(JSON.stringify(val))

            const channel = JSON.parse(Cookies.get('channel'))

            const user = JSON.parse(Cookies.get('user'))

            this.orderReqData.order_id = getOrderId(JSON.parse(Cookies.get('channel')))
            this.orderReqData.goods_id = this.skugoods.goodsId
            this.orderReqData.goods_count = val.selectedNum
            this.orderReqData.saleDate = today()
            this.orderReqData.total_price = val.selectedNum*this.skugoods.price
            this.orderReqData.uid = user.uid
            this.orderReqData.phone = user.phone
            this.orderReqData.tips = val.messages.message_0

            req('post','/api3/order/create',this.orderReqData,channel)
                .then((res)=>{
                    //console.log(res.data)

                    if (res.data.resCode == '0000') {
                        Toast.loading({
                                message: '下单中...',
                                forbidClick: false,
                                })
                        setTimeout(()=>{
                            this.$router.push({name:'order',params:{orderinfo:this.orderReqData,goodsInfo:this.skugoods}})
                        },1000)
                    }
                })

        }
    }
}
</script>

<style>

</style>