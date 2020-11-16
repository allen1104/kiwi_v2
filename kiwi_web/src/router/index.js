import Vue from "vue";
import VueRouter from "vue-router";
import Index from "../views/index";
import newsDetail from "../views/newsDetail";
import Layout from '@/components/Layout.vue'
import Home from '../views/home/index.vue'
import CarouselList from '../views/carousel/index.vue'
import CarouselAddOrUpdate from '../views/carousel/addOrUpdate.vue'
import NewsList from '../views/news/index.vue'
import NewsAddOrUpdate from  '../views/news/addOrUpdate.vue'
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
  },
  {
    path: '/admin',
    name: 'layout', //路由名称
    component: Layout, //组件对象
    children: [
      {
        path: '/',
        component: Home,
        meta: { title: 'admin' }
      },
      {
        path: 'newsList',
        component: NewsList,
        meta: { title: 'newsList' }
      },
      {
        path: 'newsList/add',
        component: NewsAddOrUpdate,
        meta: { title: 'newsAdd' }
      },
      {
        path: 'carouselList',
        component: CarouselList,
        meta: { title: 'carouselList' }
      },
      {
        path: 'carouselList/add',
        component: CarouselAddOrUpdate,
        meta: { title: 'carouselAdd' }
      }
    ]
  },
];

const router = new VueRouter({
  // mode: 'history',
  base: process.env.BASE_URL,
  routes
});

export default router;
