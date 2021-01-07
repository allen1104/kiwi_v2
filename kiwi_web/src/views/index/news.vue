<template>
  <div style="margin-top: 1rem;">
    <div class="headName">
      <span>{{ title }}</span>
      <el-button type="text" @click="more" class="button"
        >更多</el-button
      >
    </div>
    <el-row>
      <template v-if="newsList.length > 0">
      <el-col :span="8" v-for="item in newsList" :key="item.newsId">
        <el-card :body-style="{ padding: '0px' }">
          <img :src="item.titleUrl" class="image" />
          <div style="padding: 1rem">
            <div style="height: 30px">{{ item.title | ellipsis }}</div>
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
      </template>
      <template v-else>
        <div style="text-align: center;">------暂无内容------</div>
      </template>
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
  props: ["title", "bizType"],
  data() {
    return {
      newsList: [],
      currentDate: new Date(),
    };
  },
  created() {
    this.getNewsIndex();
  },
  methods: {
    goDetail(id) {
      this.$router.push({
        path: "newsDetail/" + id,
      });
    },
    getNewsIndex() {
      indexApi.getNewsIndex(this.bizType).then((response) => {
        this.newsList = response.data.data;
        // this.list = response.data;
      });
    },
    more() {
      this.$router.push({
        path: "contentList/" + this.bizType,
      });
    },
  },
  filters: {
    ellipsis(value) {
      if (!value) return "";
      let length = value.length;
      if (length > 10) {
        return value.slice(0, 10) + "...";
      }
      return value;
    },
  },
};
</script>
<style scoped>
.headName {
  text-align: center;
}
</style>
