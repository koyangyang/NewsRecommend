import axios from '~/util/axios'
export function query(id, size) {
    return axios.get('/news/findbypage/' + id + '/' + size)
}
//根据新闻分类id查询新闻
export function querybyid(id, size, newsid) {
    return axios.get('/news/' + newsid + '/' + id + '/' + size)
}
//根据新闻日期查新闻
export function querybydate(id, size, newsdate) {
    return axios.get('/news/pagebydate/' + newsdate + '/' + id + '/' + size)
}
//根据新闻id查新闻详细信息
export function querybynid(id) {
    return axios.get('/newdetail/' + id)
}
//插入一条新闻
export function addnew(newCategory, newTitle, newCnt, newCover, newDate) {
    var catorgy = {'国际': 1, '财经': 2,'体育':3,'文化':4,'国内':5,'军事':6,'美食':7,'社会':8,'生活':9,'华人':10}
    let formData = new FormData();//可以将form表单直接放入构造器，自动封装
    formData.append("newCategory", newCategory);
    formData.append("newTitle", newTitle);
    formData.append("newCid", catorgy[newCategory]);
    formData.append("newCover", newCover);
    formData.append("newDate", newDate);
    formData.append("newCnt", newCnt);
    return axios.post('/news/insert', formData)
}

//根据用户id查看系统推荐新闻
export function getrecommendnews(id,size) {
    return axios.get('/recommend/' + id+'/'+size)
}

//获取系统的热门新闻
export function gethotnews() {
    return axios.get('/recommend/hot')
}