<template>
  <div class="home">
    <el-form
      :inline="true"
      :model="form"
      class="demo-form-inline"
      label-width="100px"
    >
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="标题">
            <el-input
              v-model="form.title"
              placeholder="标题"
              style="width: 100%"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="描述">
            <el-input v-model="form.description" placeholder="描述"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="展示图片地址">
            <el-input
              v-model="form.titleUrl"
              placeholder="首页展示图片地址"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="发布日期">
            <el-date-picker
              v-model="form.pubdate"
              type="date"
              size="mini"
              placeholder="选择日期"
            >
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="业务类型">
            <el-select v-model="form.bizType" placeholder="请选择">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="是否轮播">
            <el-checkbox v-model="form.isCarousel"></el-checkbox>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <div id="contentDiv"></div>
        </el-col>
      </el-row>
    </el-form>
    <div style="text-align: center; margin-top: 10px">
      <el-button @click="save">保存</el-button>
      <el-button type="primary" @click="publish">发布</el-button>
      <el-button @click="cancelPublish">取消发布</el-button>
      <el-button @click="goback">返回</el-button>
    </div>
  </div>
</template>

<script>
// 引入 wangEditor
import wangEditor from "wangeditor";
import newsApi from "@/api/news";

export default {
  data() {
    return {
      form: {
        newsId: null,
        title: null,
        description: null,
        titleUrl: null,
        bizType: "1",
        pubdate: new Date(),
        isCarousel: false,
        richContent: {
          richContenId: null,
          content: null,
        },
      },
      editor: null,
      options: [
        {
          value: "1",
          label: "新闻",
        },
        {
          value: "2",
          label: "还没想好",
        },
        {
          value: "3",
          label: "暂时不要选",
        },
        {
          value: "4",
          label: "暂时不要选",
        },
        {
          value: "5",
          label: "暂时不要选",
        },
      ],
      //   editorData: "",
    };
  },
  created() {
    let id = this.$route.params.id;
    if (id) {
      console.info(id);
      this.findNewsById(id);
    }
  },
  mounted() {
    const editor = new wangEditor(`#contentDiv`);

    // 配置 onchange 回调函数，将数据同步到 vue 中
    editor.config.onchange = (newHtml) => {
      this.form.richContent.content = newHtml;
    };
    editor.config.zIndex = 2000;
    // 创建编辑器
    editor.create();

    this.editor = editor;
  },
  methods: {
    publish() {
      newsApi.save(this.form);
      alert("提交成功");
    },
    goback() {
      this.$router.go(-1);
    },
    save() {
      alert("保存成功");
    },
    cancelPublish() {},
    findNewsById(id) {
      newsApi.findNewsById(id).then((response) => {
        console.info(response.data);
        this.form = response.data.data;
        this.editor.txt.html(response.data.data.richContent.content);
      });
    },
  },
};
</script>

<style lang="scss">
.home {
  padding: 20px;
  // width: 1200px;
  margin: auto;
  position: relative;
  .btn {
    position: absolute;
    right: 0;
    top: 0;
    padding: 5px 10px;
    cursor: pointer;
  }
  h3 {
    margin: 30px 0 15px;
  }
}
</style>
