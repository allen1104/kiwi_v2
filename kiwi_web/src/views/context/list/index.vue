<template>
  <div style="margin: 10px">
    <a href="#/">
      <img class="logo" src="@/assets/logo.png" width="50px" />
      <span class="company">全国猕猴桃联盟</span>
    </a>
    <el-table
      :data="list"
      style="width: 100%"
      max-height="350"
      fixed
      stripe
      v-loading="loading"
      @row-click="gotoDetail"
    >
      <el-table-column type="index" prop="index" label="序号" fixed="left">
      </el-table-column>
      <el-table-column prop="title" label="标题">
        <template slot-scope="scope">
          <el-button type="text" class="button">{{
            scope.row.title
          }}</el-button>
        </template>
      </el-table-column>
      <el-table-column
        prop="pubdate"
        fixed="right"
        label="发布日期"
        :formatter="dateFormat"
      ></el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="page.currentPage"
      :page-sizes="[10, 20, 50]"
      :page-size="page.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="page.total"
    >
    </el-pagination>
  </div>
</template>
<script>
import indexApi from "@/api/index";
import moment from "moment";
export default {
  data() {
    return {
      list: [],
      page: {
        pageSize: 10,
        currentPage: 1,
        total: 0,
      },
      loading: true,
    };
  },
  created() {
    this.fetchData();
  },
  methods: {
    gotoDetail(row, event, column) {
      this.$router.push({
        path: "/newsDetail/" + row.newsId,
      });
    },
    fetchData() {
      let requestBody = {
        pageVO: this.page,
        searchParams: null,
      };
      indexApi
        .findPageList(this.$route.params.type, requestBody)
        .then((response) => {
          this.page.currentPage = response.data.data.number + 1;
          this.page.total = response.data.data.totalElements;
          this.list = response.data.data.content;
          this.loading = false;
        });
    },
    handleSizeChange(val) {
      this.page.pageSize = val;
      this.page.currentPage = 1;
      this.fetchData();
    },
    handleCurrentChange(val) {
      this.page.currentPage = val;
      this.fetchData();
    },
    dateFormat(row, column) {
      var date = row[column.property];
      if (date === undefined) {
        return "";
      }
      return moment(date).format("YYYY-MM-DD");
    },
  },
};
</script>
<style scoped>
.button {
  padding: 0;
  float: left;
}
</style>