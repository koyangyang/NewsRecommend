import axios from '~/util/axios'
export function getnewstatus(nid) {
    return axios.get('/newstatus/status/' + nid)
}
export function addlike(nid,like,dislike,forward) {
    let formData = new FormData();
    formData.append('newId', nid);
    formData.append('newLike', like);
    formData.append('newDislike', dislike);
    formData.append('newForward', forward);
    return axios.post('/newstatus/like',formData)
}
export function adddislike(nid,like,dislike,forward) {
    let formData = new FormData();
    formData.append('newId', nid);
    formData.append('newLike', like);
    formData.append('newDislike', dislike);
    formData.append('newForward', forward);
    return axios.post('/newstatus/dislike',formData)
}
export function addforward(nid,like,dislike,forward) {
    let formData = new FormData();
    formData.append('newId', nid);
    formData.append('newLike', like);
    formData.append('newDislike', dislike);
    formData.append('newForward', forward);
    return axios.post('/newstatus/forward',formData)
}