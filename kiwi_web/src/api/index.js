import request from '@/utils/request';

export default {
    getNewsIndex() {
        const req = request({
            method: 'get',
            url: '/news/Index'
        })
        return req;
    },
    getCarouselIndex() {
        const req = request({
            method: 'get',
            url: '/carousel/Index'
        })
        return req;
    }
}