<template>
    <div class="bg-yellow-50 p-3 rounded border-3 border-green-500">
        <diV class="font-bold text-3xl">
            {{ news.newTitle }}
        </diV>
        <div class="flex items-center justify-center my-5 text-gray-300 space-x-2">
            <span class="h-[1px] w-screen bg-gray-500"></span>
        </div>
        <div class="flex pt-3 pb-2">
            <div class="pr-3 font-bold"> {{ news.newDate }}</div>
            <div class="pl-3 font-bold">{{ news.newCategory }}</div>
        </div>
        <div class="flex items-center justify-center my-5 text-gray-300 space-x-2">
            <span class="h-[1px] w-screen bg-gray-500"></span>
        </div>
        <div>
            <v-md-preview :text="news.newCnt"></v-md-preview>
        </div>
    </div>
</template>
<script>
import { querybynid } from '~/api/news'
export default {
    name: 'News',
    setup() {
        const onSubmit = (e) => {
            router.push('/newsdetail/'+e.currentTarget.id)
        }
        return {
            onSubmit
        }
    },
    data() {
        return {
            news: [
            ]
        }
    },
    mounted() {
        var urldate = window.location.href;
        querybynid(urldate.split("/")[4]).then(res => {
            this.news = res.data
        })
    }
}
</script>
    
    