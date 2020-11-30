<template>
  <div style="margin: 10px">
    <div
      style="display: block;margin: 0 auto;max-width: 700px;text-align: center"
    >
      <div style="font-size: x-large;">{{ title }}</div>
      <time class="time">{{ new Date(pubdate) }}</time>
      <div v-html="editorData" style="text-align: left"></div>
      <el-button type="primary" @click="back">返回</el-button>
    </div>
  </div>
</template>
<script>
import newApi from "@/api/news";
export default {
  data() {
    return {
      title: null,
      pubdate: null,
      editorData: null
    };
  },
  created() {
    // alert(this.$route.params.id);
    this.getNewsById(this.$route.params.id);
  },
  methods: {
    back() {
      this.$router.go(-1);
    },
    getNewsById(id) {
      newApi.findNewsById(id).then(response => {
        console.log(response.data);
        this.title = response.data.data.title;
        this.pubdate = response.data.data.pubdate;
        this.editorData = response.data.data.richContent.content;
      });
    }
  }
};
</script>
