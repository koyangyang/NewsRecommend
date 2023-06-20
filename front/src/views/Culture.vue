<template>
  <div class=" p-3 rounded-xl border-1 border-emerald-500 bg-yellow-50" v-for=" item in newsList" style="margin-bottom: 5px;">
    <div class="font-bold text-2xl p-2">{{ item.newTitle }}</div>
    <div class="p-2">内容简介：{{ item.newCnt.slice(0,100) }}......</div>
    <div class="flex">
      <div class="p-2">发表日期：{{ item.newDate }}</div>
      <div class="p-2">
          新闻分类：<el-tag size="default">{{ item.newCategory }}</el-tag>
      </div>
      <div style="margin-left: 45%;" class="p-2">
        <el-button type="primary" color="#38bdf8" :id="item.newId"  @click="onSubmit" round>点击查看详情</el-button>
      </div>
    </div>
  </div>
  <div class="flex justify-center items-center p-3">
    <el-pagination v-model:page-size="pageSize" :page-sizes="pageSizes" layout=" prev, pager, next" :total="pageTotal"
      @size-change="handleSizeChange" @current-change="changePage" background />
  </div>
</template>
  <script>
import { querybyid } from '~/api/news'
import { useRouter } from 'vue-router'
  
  export default {
    name: 'Culture',
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
        newsList: [
        ],
        pageSizes: [1, 2, 3],
        pageSize: 5,
        pageTotal: 20
      }
    },
    methods: {
      changePage(val) {
        querybyid(val + 1, 5,4).then(res => {
          this.newsList = res.data.records
        })
      }
    },
    mounted() {
        querybyid(0, 5,4).then(res => {
        this.newsList = res.data.records
        this.pageTotal = res.data.total - 1
      })
    }
  }
  </script>
    
    