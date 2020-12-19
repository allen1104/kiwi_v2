<template>
  <div class="block">
    <span class="demonstration">主圖</span>
    <el-carousel :height="carouselHeight">
      <el-carousel-item v-for="item in list" :key="item.newsId">
        <!-- <h3 class="small">{{ item }}</h3> -->
        <!-- <router-link to="/newsDetail"> -->
        <!-- </router-link> -->
        <img :src="item.titleUrl" class="image" />
      </el-carousel-item>
    </el-carousel>
  </div>
</template>
<script>
import newsApi from "@/api/index";
export default {
  data() {
    return {
      list: [],
      carouselHeight: "180px",
    };
  },
  created() {
    this.getCarousel();
    if (/Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)) {
      this.carouselHeight = "180px";
    } else {
      this.carouselHeight = "600px";
    }
  },
  methods: {
    getCarousel() {
      newsApi.getCarousel().then((reponse) => {
        this.list = reponse.data.data;
      });
    },
  },
};
</script>

<style>
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 250px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>
