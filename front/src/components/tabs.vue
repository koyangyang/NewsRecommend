<template>
    <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
        <el-tab-pane label="推荐" name="0">
            <Recommend/>
        </el-tab-pane>
        <el-tab-pane label="全部" name="11">
            <News />
        </el-tab-pane>
        <el-tab-pane label="国际" name="1">
            <Technology />
        </el-tab-pane>
        <el-tab-pane label="财经" name="2">
            <Finace />
        </el-tab-pane>
        <el-tab-pane label="体育" name="3">
            <Sport />
        </el-tab-pane>
        <el-tab-pane label="文化" name="4">
            <Culture />
        </el-tab-pane>
        <el-tab-pane label="国内" name="5">
            <China />
        </el-tab-pane>
        <el-tab-pane label="军事" name="6">
            <Video />
        </el-tab-pane>
        <el-tab-pane label="美食" name="7">
            <Picture />
        </el-tab-pane>
        <el-tab-pane label="社会" name="8">
            <Society />
        </el-tab-pane>
        <el-tab-pane label="生活" name="9">
            <Life />
        </el-tab-pane>
        <el-tab-pane label="华人" name="10">
            <Huaren />
        </el-tab-pane>
        <el-tab-pane label="人文" name="12">

        </el-tab-pane>
    </el-tabs>
</template>

<script setup>
import { ref } from 'vue'
import News from '~/components/news.vue'
import Technology from '~/views/technology.vue'
import Finace from '~/views/finance.vue'
import Sport from '~/views/sport.vue'
import Culture from '~/views/culture.vue'
import China from '~/views/china.vue'
import Video from '~/views/video.vue'
import Picture from '~/views/picture.vue'
import Society from '~/views/society.vue'
import Life from '~/views/life.vue'
import Huaren from '~/views/huaren.vue'
import Recommend from '../views/Recommend.vue'

const activeName = ref('0')

const handleClick = (tab, event) => {
    
}
</script>
  