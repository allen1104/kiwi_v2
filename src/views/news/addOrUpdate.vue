<template>
  <div class="home">
    <el-form :inline="true" :model="form" class="demo-form-inline">
      <el-form-item label="标题">
        <el-input v-model="form.title" placeholder="标题"></el-input>
      </el-form-item>
      <el-form-item label="描述">
        <el-input v-model="form.description" placeholder="描述"></el-input>
      </el-form-item>
      <el-form-item label="内容">
        <div id="demo1"></div>
      </el-form-item>
    </el-form>
    <div style="text-align: center">
      <el-button @click="save">保存</el-button>
      <el-button type="primary" @click="publish">发布</el-button>
      <el-button @click="submit">取消发布</el-button>
      <el-button @click="goback">返回</el-button>
    </div>

    <!-- <h3>内容</h3> -->

    <!-- <button type="button" class="btn" @click="getEditorData">获取当前内容</button> -->
    <!-- <h3>内容预览</h3> -->
    <!-- <textarea name="" id="" cols="170" rows="20" readonly v-model="editorData"></textarea> -->
  </div>
</template>

<script>
// 引入 wangEditor
import wangEditor from "wangeditor";

export default {
  data() {
    return {
      form: {
        id: null,
        title: null,
        description: null,
        content: null,
      },
      editor: null,
      //   editorData: "",
    };
  },
  mounted() {
    const editor = new wangEditor(`#demo1`);

    // 配置 onchange 回调函数，将数据同步到 vue 中
    editor.config.onchange = (newHtml) => {
      this.form.content = newHtml;
    };

    // 创建编辑器
    editor.create();

    this.editor = editor;
  },
  methods: {
    publish() {
      alert("提交成功");
    },
    goback() {
      this.$router.go(-1);
    },
    save() {
      alert("保存成功");
    },
  },
};
</script>

<style lang="scss">
.home {
  padding: 20px;
  width: 1200px;
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
