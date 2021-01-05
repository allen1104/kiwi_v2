import request from "@/utils/request";

export default {
  getToken() {
    return request({
      // Promise
      url: "/qiniu/getToken",
      method: "get",
    });
  }
}