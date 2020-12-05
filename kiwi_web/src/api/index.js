import request from "@/utils/request";

export default {
  getNewsIndex() {
    const req = request({
      method: "get",
      url: "/news/search/index"
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
  }
};
