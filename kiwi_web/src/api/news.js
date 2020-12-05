import request from "@/utils/request";

export default {
  save(news) {
    const req = request({
      method: "post",
      url: "/news/config/save",
      data: news
    });
    return req;
  },
  findPageList(page, size) {
    const req = request({
      method: "post",
      url: "/news/config/findPageList/" + size + "/" + page
    });
    return req;
  },
  findNewsById(id) {
    const req = request({
      method: "get",
      url: "/news/search/" + id
    });
    return req;
  },
  deleteNewsById(id) {
    const req = request({
      method: "delete",
      url: "/news/config/" + id
    });
    return req;
  }
};
