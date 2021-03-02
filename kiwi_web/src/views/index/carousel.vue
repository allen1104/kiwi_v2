<template>
  <div class="block">
    <span class="demonstration">主圖</span>
    <el-carousel :height="carouselHeight" type="card">
      <el-carousel-item v-for="item in list" :key="item.newsId">
        <!-- <h3 class="small">{{ item }}</h3> -->
        <!-- <router-link to="/newsDetail"> -->
        <!-- </router-link> -->
        <el-image
          :src="
            item.titleUrl
              ? item.titleUrl
              : 'http://cdn.kiwialliance.com/default.ico'
          "
          @click="goDetail(item.newsId)"
          fit="contain"
          class="image"
        />
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
      carouselHeight: "7rem",
    };
  },
  created() {
    this.getCarousel();
    if (/Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)) {
      this.carouselHeight = (document.body.clientWidth - 40) / 2.75 + "px";
    } else {
      this.carouselHeight = (document.body.clientWidth - 40) / 2.75 + "px";
    }
  },
  methods: {
    getCarousel() {
      newsApi.getCarousel().then((reponse) => {
        this.list = reponse.data.data;
      });
    },
    goDetail(id) {
      this.$router.push({
        path: "newsDetail/" + id,
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
