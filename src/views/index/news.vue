<template>
    <div>
    <div>news標題</div>
    <el-row>
        <el-col :span="8" v-for="(o) in 3" :key="o">
            <el-card :body-style="{ padding: '0px' }">
                <router-link to="/newsDetail"><img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" class="image"></router-link>
                <div style="padding: 14px;">
                    <span>好吃的汉堡</span>
                    <div class="bottom clearfix">
                    <time class="time">{{ currentDate }}</time>
                    <el-button type="text" click="goDetail" class="button"><router-link to="/newsDetail">查看详情</router-link></el-button>
                    </div>
                </div>
            </el-card>
        </el-col>
    </el-row>
    </div>
</template>
<style>
  .time {
    font-size: 13px;
    color: #999;
  }
  
  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image {
    width: 100%;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
      display: table;
      content: "";
  }
  
  .clearfix:after {
      clear: both
  }
</style>

<script>
export default {
  data() {
    return {
      currentDate: new Date()
    };
  },
  methods: {
    goDetail(){

    }  
  },
}
</script>