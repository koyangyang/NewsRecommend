import axios from '~/util/axios'
export function getcomments(nid,id,size) {
    return axios.get('/comment/'+nid+'/'+id+'/'+size)
}
export function getcommentlike(nid) {
    return axios.get('/comment/like'+nid)
}
export function getcommentdislike(nid) {
    return axios.get('/comment/dislike'+nid)
}
export function postcommentlike(nid) {
    return axios.get('/comment/plike'+nid)
}
export function postcommentdislike(nid) {
    return axios.get('/comment/pdislike'+nid)
}
export function insertComment(userId,newId,cCnt,uname) {
    let formData = new FormData();//可以将form表单直接放入构造器，自动封装
    formData.append("userId", userId);
    formData.append("newId", newId);
    formData.append("cCnt", cCnt);
    formData.append("userName",uname)
    return axios.post('/comment/insert', formData)
}