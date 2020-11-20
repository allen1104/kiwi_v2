import request from '@/utils/request';

export default {
    getNewsIndex() {
        const req = request({
            method: 'get',
            url: '/news/index'
        })
        return req;
    },
    getCarouselIndex() {
        const req = request({
            method: 'get',
            url: '/carousel/index'
        })
        return req;
    }
}