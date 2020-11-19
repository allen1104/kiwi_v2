import request from '@/utils/request';

export default {
    save(news) {
        const req = request({
            method: 'post',
            url: '/news/save',
            data:news
        })
        return req;
    },
    findPageList(page) {
        const req = request({
            method: 'post',
            url: '/news/findPageList',
            data:page
        })
        return req;
    },
    findNewsById(id) {
        const req = request({
            method: 'get',
            url: '/news/'+id
        })
        return req;
    },
    deleteNewsById(id) {
        const req = request({
            method: 'delete',
            url: '/news/'+id
        })
        return req;
    },
}