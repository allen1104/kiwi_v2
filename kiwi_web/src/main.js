import Vue from "vue";
import ElementUI from "element-ui";
import MintUI from "mint-ui";
import "element-ui/lib/theme-chalk/index.css";
import App from "./App.vue";
import router from "./router";
import store from "./store";

Vue.use(ElementUI);
Vue.use(MintUI);

Vue.config.productionTip = process.env.NODE_ENV === "porduction";

console.log(process.env.NODE_ENV);
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
