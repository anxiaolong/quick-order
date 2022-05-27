<template>
  <div class="hello">
    <h3>测试</h3>
    <van-button @click="test" type="danger">危险按钮</van-button>
    <title>测试rem转换</title>
    <p class="p1">2011年发行首张专辑「学不会」，上线三个月，横扫所有实体、数字排行榜冠军：超越18个以上金榜冠军专辑。
　　2013年，发行出道十年纪念专辑《因你而在》，以单周销售占比83.35%的成绩打破此前台湾五大金榜华语榜单纪录 ，并拿下该榜单年度销售冠军。
　　2014年，发行第11张创作专辑《新地球》 ，蝉联台湾五大金榜华语榜年度销售冠军。
　　2017年，歌曲【不为谁而作的歌】与【修炼爱情】MV皆在YouTube网站达到一亿次观看次数。成为华语歌坛中第一位拥有两首破亿歌曲的歌手</p>
  </div>
</template>

<script>
import {Toast} from 'vant'
export default {
  name: 'DEBUG',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  },
  mounted(){
    Toast('欢迎使用')
  },
  methods:{
    test(){
      Toast.success('欢迎使用！')
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.p1{
  font-size: 30px;
}
.hello{
  margin: 40px;
}
</style>
