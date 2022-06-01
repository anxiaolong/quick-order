<template>
  <div>
      <van-picker
        style="margin:20px"
        title="选择"
        show-toolbar
        :columns="columns"
        @confirm="onConfirm"
        @cancel="onCancel"
       />
  </div>
</template>

<script>
import { Toast } from 'vant'
import Cookies from 'js-cookie'
import {req} from '../api/fun'
export default {
    name:'supplier',
    data(){
        return {
            supplierInfoList:[],
            columns:[]
        }
    },
    mounted(){
        this.loadSupplierInfoList()
    },
    methods:{
        loadSupplierInfoList(){
            req('post','/api3/supplier/list',JSON.parse(Cookies.get('user')),JSON.parse(Cookies.get('channel')))
                .then((res)=>{
                    this.supplierInfoList = res.data.data
                    const columns = []
                    for (let index = 0; index < this.supplierInfoList.length; index++) {
                        columns[index] = this.supplierInfoList[index].supplier_id +'-'+ this.supplierInfoList[index].supplier_name
                    }
                    this.columns = columns
                })  
        },
        onConfirm(val){
            // console.log(val)
            this.$router.push({path: '/index/goods', query:{supplier : val}})
        },
        onCancel(){
            Toast('请选择您当前所在门店！')
        }
    }
}
</script>

<style>

</style>