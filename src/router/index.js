import Vue from "vue";
import VueRouter from "vue-router";
import Index from "../views/index";
import newsDetail from "../views/newsDetail";

Vue.use(VueRouter);

const routes = [
  {
    // 登录页
    path: '/',
    name: 'index', //路由名称
    component: Index
  },
  {
    // 登录页
    path: '/newsDetail',
    name: 'newsDetail', //路由名称
    component: newsDetail
  }
];

const router = new VueRouter({
  // mode: 'history',
  base: process.env.BASE_URL,
  routes
});

export default router;
