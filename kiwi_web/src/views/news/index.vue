<template>
  <div>
    <!-- :inline="true" 表单一行显示 -->
    <el-form
      ref="searchForm"
      :inline="true"
      :model="searchMap"
      style="margin-top: 20px"
    >
      <!-- 重置会看 el-form-item 组件元素的 prop 上是否指定了字段名，指定了才会重置生效 -->
      <el-form-item prop="name">
        <el-input
          v-model="searchMap.name"
          placeholder="标题"
          style="width: 200px"
        ></el-input>
      </el-form-item>
      <el-form-item prop="code">
        <el-input
          v-model="searchMap.code"
          placeholder="详情"
          style="width: 200px"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="fetchData">查询</el-button>
        <el-button type="primary" @click="handleAdd">新增</el-button>
        <el-button @click="$refs['searchForm'].resetFields()">重置</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="list"
      style="width: 100%"
      max-height="350"
      fixed
      stripe
      v-loading="loading"
    >
      <el-table-column
        type="index"
        prop="index"
        label="序号"
        @click="gotoDetail(scope.row)"
      >
      </el-table-column>
      <el-table-column prop="title" label="标题"> </el-table-column>
      <el-table-column prop="description" label="描述"> </el-table-column>
      <el-table-column
        prop="bizType"
        label="业务类型"
        width="80"
        :formatter="formatterBizType"
      >
      </el-table-column>
      <el-table-column
        prop="isCarousel"
        label="轮播"
        width="50"
        :formatter="formatterIsCarousel"
      >
      </el-table-column>
      <el-table-column
        prop="status"
        label="状态"
        width="70"
        :formatter="formatterStatus"
      >
      </el-table-column>
      <el-table-column label="操作" width="150" fixed="right">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.row)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页组件 -->
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-sizes="[10, 20, 50]"
      :page-size="pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
    >
    </el-pagination>
  </div>
</template>

<script>
import newsApi from "@/api/news";
export default {
  data() {
    return {
      list: [],
      pageSize: 10,
      currentPage: 1,
      total: 0,
      searchMap: {
        name: "",
        code: "",
      },
      rules: {
        name: [{ required: true, message: "标题不能为空", trigger: "blur" }],
        code: [{ required: true, message: "详情不能为空", trigger: "blur" }],
      },
      loading: true,
    };
  },

  created() {
    this.fetchData();
  },

  methods: {
    fetchData() {
      let page = this.currentPage - 1;
      let size = this.pageSize;
      this.loading = true;
      newsApi.findPageList(page, size).then((response) => {
        console.info(response.data);
        this.currentPage = response.data.data.number + 1;
        this.total = response.data.data.totalElements;
        this.list = response.data.data.content;
        this.loading = false;
      });
    },
    handleAdd() {
      this.$router.push("/admin/newsList/add");
    },
    handleEdit(row) {
      console.info(row.newsId);
      this.$router.push({
        name: "newsUpdate",
        params: {
          id: row.newsId,
        },
      });
    },
    handleDelete(row) {
      newsApi.deleteNewsById(row.newsId).then((response) => {
        if (response.data.data === 1) {
          this.$message({
            message: "删除成功",
            type: "success",
          });
        } else {
          let message = response.data.message;
          this.$message({
            message: "删除失败：" + message,
            type: "fail"
          });
        }
        this.fetchData();
      });
    },
    handleSizeChange(val) {
      this.pageSize = val;
      this.currentPage = 1;
      this.fetchData();
    },
    handleCurrentChange(val) {
      this.currentPage = val;
      this.fetchData();
    },
    formatterIsCarousel(row, column, cellValue, index) {
      if (cellValue) {
        return "是";
      } else {
        return "否";
      }
    },
    formatterBizType(row, column, cellValue, index) {
      switch (cellValue) {
        case "1":
          return "新闻";
        case "2":
          return "没想好";
        default:
          return "-";
      }
    },
    formatterStatus(row, column, cellValue, index) {
      switch (cellValue) {
        case "0":
          return "草稿";
        case "1":
          return "已发布";
        case "2":
          return "取消发布";
        default:
          return "-";
      }
    },
    gotoDetail(row) {
      let id = row.newsId;
      this.$router.push({
        path: "newsDetail/" + id
      });
    },
  },
};
</script>