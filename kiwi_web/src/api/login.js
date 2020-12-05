import request from '@/utils/request'


export function login(username, password) {
    return request({ // Promise
        url: '/login',
        method: 'post',
        data: {
            username, // username: username
            password
        }
    })
}

export function getUserInfo(token) {
    return request({
        url: `/user/info/${token}`,
        method: 'get'
    })
}

export function logout(token) {
    return request({
        url: `/logout`,
        method: 'post',
        data: {
            token //token: token
        }
    })
}

