<template>
    <div class="order">
        <h4>订单:{{orderinfo.order_id}}</h4>
        <van-image
        width="50%"
        height="50%"
        :src="img"
        />

        <h3 style="color:red">{{orderinfo.total_price/100}}元</h3>
        <h4>{{goodsInfo.goodsName}}*{{orderinfo.goods_count}}</h4>
        <p>
            {{orderinfo.phone}}<br>
            {{orderinfo.tips}}<br>
        </p>
        
        <div style="position:fixed; bottom:20px;left: 30%">
            <van-button size="small" type="danger" :round=true @click="cancel">返回上级</van-button>
            &nbsp&nbsp
            <van-button size="small" type="danger" :round=true @click="pay">微信支付</van-button>
        </div>
    </div>
</template>

<script>
import {now,getOrderId} from '../api/fun'
import axios from 'axios'
import { Toast } from 'vant'
import Cookies from 'js-cookie'
export default {
    name:'order',
    data(){
        return{
            orderinfo:{},
            goodsInfo:{},
            img:require('../img/default00.png'),
            payData:{
                transaction_id:"",
                order_id:"",
                pay_date:"",
                pay_amount:"",
                pay_status:"0"
            }
        }
    },
    mounted(){
        this.orderinfo = this.$route.params.orderinfo
        this.goodsInfo = this.$route.params.goodsInfo
        //console.log(JSON.stringify(this.$route.params.orderinfo))
        //console.log(JSON.stringify(this.$route.params.goodsInfo))
    },
    methods:{
        cancel(){
            this.$router.back()
        },
        pay(){
            this.payData.transaction_id = getOrderId(JSON.parse(Cookies.get('channel')))
            this.payData.order_id = this.orderinfo.order_id
            this.payData.pay_date = now()
            this.payData.pay_amount = this.orderinfo.total_price
            axios({
                method:'post',
                url:'/api3/order/notify',
                Headers:{'Content-Type':'application/json'},
                data:this.payData
                })
                .then((res)=>{
                    if (res.data.resCode == '0000') {
                        Toast.success('支付成功')
                        setTimeout(()=>{
                            this.$router.push('/index/my')
                        },1000)
                    }else{
                        Toast.fail('支付失败')
                    }
                })
        }
    }
}
</script>

<style scoped>
.order{
	width : 100%;
	height : 100%;
}
</style>