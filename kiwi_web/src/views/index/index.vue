<template>
  <el-container style="border: 1px solid #eee; background-color: #f9fbe4">
    <el-container>
      <el-header style="margin-top: 20px;">
        <Header />
      </el-header>
      <el-main>
        <Carousel />
        <template v-for="item in items">
          <News :title="item.title" :bizType="item.bizType" :key="item.bizType"/>
        </template>
      </el-main>
    </el-container>
  </el-container>
</template>
<script>
import Header from "@/views/index/header.vue";
import Carousel from "@/views/index/carousel.vue";
import News from "@/views/index/news.vue";
export default {
  components: {
    Header,
    Carousel,
    News,
  },
  data() {
    return {
      items: [
          {
            title: "联盟简介",
            bizType: "10",
          },
          {
            title: "联盟活动",
            bizType: "20",
          },
          {
            title: "通知公告",
            bizType: "30",
          },
          {
            title: "实例展示",
            bizType: "40",
          },
          {
            title: "技术园地",
            bizType: "50",
          },
          {
            title: "相关政策",
            bizType: "60",
          }
        ]
    };
  },
  methods: {
    onSubmit() {
      console.log("submit!");
    },
  },
};
</script>
