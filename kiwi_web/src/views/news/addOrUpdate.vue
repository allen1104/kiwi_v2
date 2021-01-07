<template>
  <div class="home" v-loading="loading">
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
        <!-- <el-col :span="12">
          <el-form-item label="展示图片地址">
            <el-input
              v-model="form.titleUrl"
              placeholder="首页展示图片地址"
            ></el-input>
          </el-form-item>
        </el-col> -->
        <el-col :span="12">
          <el-form-item label="展示图片地址">
            <el-upload
              class="upload-demo"
              action="http://upload-z2.qiniup.com"
              :data="qn"
              :on-preview="handlePreview"
              :before-upload="beforeUpload"
              :on-remove="handleRemove"
              :before-remove="beforeRemove"
              :multiple="false"
              :on-exceed="handleExceed"
              :file-list="fileList"
              :on-error="uploadError"
              :limit="1"
              :on-success="uploadSuccess"
            >
              <el-button size="small" type="primary">点击上传</el-button>
            </el-upload>
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
import qiniuApi from "@/api/qiniu";
import * as qiniu from "qiniu-js";

export default {
  data() {
    return {
      form: {
        newsId: null,
        title: null,
        description: null,
        titleUrl: null,
        bizType: "10",
        pubdate: new Date(),
        isCarousel: false,
        status: 0,
        richContent: {
          richContenId: null,
          content: null,
        },
      },
      editor: null,
      options: [
        {
          value: "10",
          label: "联盟简介",
        },
        {
          value: "20",
          label: "联盟活动",
        },
        {
          value: "30",
          label: "通知公告",
        },
        {
          value: "40",
          label: "实例展示",
        },
        {
          value: "50",
          label: "技术园地",
        },
        {
          value: "60",
          label: "相关政策",
        },
      ],
      loading: true,
      //   editorData: "",
      fileList: [],
      qn: {
        token: "token",
        key: "",
      },
    };
  },
  created() {
    let id = this.$route.params.id;
    if (id) {
      console.info(id);
      this.findNewsById(id);
    } else {
      this.loading = false;
    }
    this.refreshToken();
  },
  mounted() {
    const editor = new wangEditor(`#contentDiv`);

    // 配置 onchange 回调函数，将数据同步到 vue 中
    editor.config.onchange = (newHtml) => {
      this.form.richContent.content = newHtml;
    };
    editor.config.zIndex = 1000;
    // editor.config.uploadImgAccept = ["abc"];
    // editor.config.linkImgCheck = function(imgSrc) {
    //   console.log(imgSrc);
    //   return true;
    // }; // 无需校验
    // 插入网络图片的回调
    editor.config.linkImgCallback = function (src) {
      console.log("图片 src ", src);
    };
    editor.config.customUploadImg = function (resultFiles, insertImgFn) {
      console.log("resultFiles, insertImgFn:", resultFiles, insertImgFn);
      // resultFiles 是 input 中选中的文件列表
      // insertImgFn 是获取图片 url 后，插入到编辑器的方法
      qiniuApi.getToken().then((response) => {
        let token = response.data.data;
        const observer = {
          next(res) {
            //..
          },
          error(err) {
            //..
          },
          complete(res) {
            insertImgFn(`http://cdn.kiwialliance.com/${resultFiles[0].name}`);
          },
        };
        const observable = qiniu.upload(
          resultFiles[0],
          resultFiles[0].name,
          token,
          null,
          null
        );
        const config = {
          useCdnDomain: true,
          region: qiniu.region.z2,
        };
        const subscription = observable.subscribe(observer); // 上传开始
        // 上传图片，返回结果，将图片插入到编辑器中
        console.log("customUploadImg subscription:", subscription);
        // insertImgFn(imgUrl);
      });
    };
    // 创建编辑器
    editor.create();

    this.editor = editor;
  },
  methods: {
    publish() {
      this.form.status = 1;
      newsApi.save(this.form).then((response) => {
        if (response.data.status === 1) {
          this.$message({
            message: "发布成功",
            type: "success",
          });
          this.$router.go(-1);
        } else {
          let message = response.data.message;
          this.$message({
            message: "发布失败：" + message,
            type: "fail",
          });
        }
      });
    },
    goback() {
      this.$router.go(-1);
    },
    save() {
      newsApi.save(this.form).then((response) => {
        if (response.data.status === 1) {
          this.$message({
            message: "保存成功",
            type: "success",
          });
          this.form = response.data.data;
        } else {
          let message = response.data.message;
          this.$message({
            message: "保存失败：" + message,
            type: "fail",
          });
        }
      });
    },
    cancelPublish() {
      this.form.status = 0;
      newsApi.save(this.form).then((response) => {
        if (response.data.status === 1) {
          this.$message({
            message: "取消发布成功",
            type: "success",
          });
          this.form = response.data.data;
        } else {
          let message = response.data.message;
          this.$message({
            message: "取消发布失败：" + message,
            type: "fail",
          });
        }
      });
    },
    findNewsById(id) {
      newsApi.findNewsById(id).then((response) => {
        console.info(response.data);
        this.form = response.data.data;
        this.editor.txt.html(response.data.data.richContent.content);
        if (this.form.titleUrl) {
          let names = this.form.titleUrl.split("/");
          this.fileList = [
            {
              name: names[names.length - 1],
              url: this.form.titleUrl,
            },
          ];
        }
        this.loading = false;
      });
    },
    handleRemove(file, fileList) {
      console.log("handleRemove", file, fileList);
      this.form.titleUrl = "";
    },
    handlePreview(file) {
      console.log("handlePreview", file);
    },
    handleExceed(files, fileList) {
      this.$message.warning(`只能上传一个文件，请删除后再继续上传`);
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },
    beforeUpload(file) {
      this.qn.key = file.name;
      return true;
    },
    refreshToken() {
      qiniuApi.getToken().then((response) => {
        this.qn.token = response.data.data;
      });
    },
    uploadError(err, file, fileList) {
      if (err.message === '{"error":"bad token"}') {
        this.refreshToken();
      }
      this.$message({
        message: "上传失败，请重试",
        type: "fail",
      });
      console.info("uploadError", err, file, fileList);
    },
    uploadSuccess(response, file, fileList) {
      this.fileList = [
        {
          name: response.key,
          url: `http://cdn.kiwialliance.com/${response.key}`,
        },
      ];
      this.form.titleUrl = `http://cdn.kiwialliance.com/${response.key}`;
      console.info("uploadSuccess", response, file, fileList);
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
