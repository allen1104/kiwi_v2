<template>
  <div>
    <div>联盟新闻</div>
    <el-row>
      <el-col :span="8" v-for="item in newsList" :key="item.newsId">
        <el-card :body-style="{ padding: '0px' }">
          <img :src="item.titleUrl" class="image" />
          <div style="padding: 14px">
            <span>{{ item.title }}</span>
            <div class="bottom clearfix">
              <!-- <time class="time">{{ new Date(item.pubdate) }}</time> -->
              <el-button
                type="text"
                @click="goDetail(item.newsId)"
                class="button"
                >查看详情</el-button
              >
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
  clear: both;
}
</style>

<script>
import indexApi from "@/api/index";
export default {
  data() {
    return {
      newsList: [],
      currentDate: new Date()
    };
  },
  created() {
    this.getNewsIndex();
  },
  methods: {
    goDetail(id) {
      this.$router.push({
        path: "newsDetail/"+id,
      });
    },
    getNewsIndex() {
      indexApi.getNewsIndex().then(response => {
        console.log(response.data);
        this.newsList = response.data.data;
        // this.list = response.data;
      });
    }
  }
};
</script>
