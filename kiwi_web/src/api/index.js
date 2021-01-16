import request from "@/utils/request";

export default {
  getNewsIndex(bizType, size) {
    const req = request({
      method: "get",
      url: `/news/search/index/${bizType}/${size}`
    });
    return req;
  },
  getCarouselIndex() {
    const req = request({
      method: "get",
      url: "/carousel/search/index"
    });
    return req;
  },
  getCarousel() {
    const req = request({
      method: "get",
      url: "/news/search/getCarousel"
    });
    return req;
  },
  findPageList(type, requestBody) {
    const req = request({
      method: "post",
      url: `/news/search/findPageList/${type}`,
      data: requestBody
    });
    return req;
  },
};
