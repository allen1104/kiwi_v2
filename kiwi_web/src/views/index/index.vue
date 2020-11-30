<template>
  <el-container style="border: 1px solid #eee; background-color:#f9fbe4;">
    <el-container>
      <el-header>
        <Header />
      </el-header>
      <el-main>
        <Carousel />
        <News />
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
    News
  },
  data() {
    return {
      form: {
        name: "",
        region: "",
        date1: "",
        date2: "",
        delivery: false,
        type: [],
        resource: "",
        desc: ""
      }
    };
  },
  methods: {
    onSubmit() {
      console.log("submit!");
    }
  }
};
</script>
