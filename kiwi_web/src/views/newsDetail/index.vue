<template>
  <div>
    <div v-html="editorData"></div>
    <button @click="back">返回</button>
  </div>
</template>
<script>
import newApi from "@/api/news";
export default {
  data() {
    return {
      editorData: null,
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
      newApi.findNewsById(id).then((response) => {
        console.log(response.data);
        this.editorData = response.data.data.richContent.content;
      });
    },
  },
};
</script>