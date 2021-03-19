import Vue from "vue";
import ElementUI from "element-ui";
// import MintUI from "mint-ui";    //package.json for mobile "mint-ui": "^2.2.13",
import "element-ui/lib/theme-chalk/index.css";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import moment from 'moment';

Vue.prototype.$moment = moment;

Vue.use(ElementUI);
// Vue.use(MintUI);
import "./permission";

Vue.config.productionTip = process.env.NODE_ENV === "porduction";

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
