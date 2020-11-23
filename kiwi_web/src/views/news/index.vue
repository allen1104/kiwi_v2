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
    <el-table :data="list" style="width: 100%">
      <el-table-column type="index" prop="index" label="序号">
      </el-table-column>
      <el-table-column prop="title" label="标题"> </el-table-column>
      <el-table-column prop="description" label="描述"> </el-table-column>
      <el-table-column prop="bizType" label="业务类型" width="80">
      </el-table-column>
      <el-table-column prop="isCarousel" label="轮播" width="50">
      </el-table-column>
      <el-table-column prop="status" label="状态" width="50"> </el-table-column>
      <el-table-column label="操作" width="150">
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
      currentPage: 0,
      total: 0,
      searchMap: {
        name: "",
        code: "",
      },
      rules: {
        name: [{ required: true, message: "标题不能为空", trigger: "blur" }],
        code: [{ required: true, message: "详情不能为空", trigger: "blur" }],
      },
    };
  },

  created() {
    this.fetchData();
  },

  methods: {
    fetchData() {
      let page = this.currentPage;
      let size = this.pageSize;
      newsApi.findPageList(page, size).then((response) => {
        console.info(response.data);
        this.currentPage = response.data.data.number;
        this.total = response.data.data.totalElements;
        this.list = response.data.data.content;
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
        this.fetchData();
      });
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageSize = val;
      this.currentPage = 0;
      this.fetchData()
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.currentPage = val-1;
      this.fetchData()
    },
  },
};
</script>