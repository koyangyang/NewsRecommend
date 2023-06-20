<template>
    <div class=" p-3 rounded-xl border-1 border-emerald-500 bg-yellow-50" v-for=" item in commentsList" style="margin-bottom: 5px;">
      <div class="font-bold text-2xl p-1">用户：{{ item.userName }}</div>
      <div class="p-1">评论内容：{{ item.ccnt }}</div>
        <div class="p-1">发表日期：{{ item.cdate.slice(0,10) }} {{ item.cdate.slice(11,19) }}</div>
    </div>
    <div class="flex justify-center items-center p-3">
      <el-pagination v-model:page-size="pageSize" :page-sizes="pageSizes" layout=" prev, pager, next" :total="pageTotal"
        @size-change="handleSizeChange" @current-change="changePage" background />
    </div>
  </template>
  <script>
  import { getcomments } from '~/api/comments'
  import { useRouter } from 'vue-router'
  
  export default {
    name: 'Comment',
    setup() {
      const router= useRouter()
      const onSubmit = (e) => {
        router.push('/newsdetail/'+e.currentTarget.id)
      }
      return {
        onSubmit
      }
    },
    data() {
      return {
        commentsList: [
        ],
        pageSizes: [1, 2, 3],
        pageSize: 5,
        pageTotal: 20,
      }
    },
    methods: {
      changePage(val) {
        var nId = window.location.href.slice(33);
        getcomments(nId,val + 1, 5).then(res => {
          this.commentsList = res.data.records
        })
      }
    },
    mounted() {
      var nId = window.location.href.slice(33);
        getcomments(nId,0, 5).then(res => {
        this.commentsList = res.data.records
        this.pageTotal = res.data.total - 1
      })
    }
  }
  </script>
    
    