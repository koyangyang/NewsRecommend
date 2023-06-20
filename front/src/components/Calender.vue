<template>
    <el-calendar>
        <template #date-cell="{ data }">
            <p :class="data.isSelected ? 'is-selected' : ''" @click="ClickTime(data.day)">
                {{ data.day.split('-').slice(1).join('-') }}
                {{ data.isSelected ? '✔️' : '' }}
            </p>
        </template>
    </el-calendar>
</template>
<script setup>
import { useRouter } from 'vue-router'
const router = useRouter()
const ClickTime = (date) => {
    router.push("/newdate/"+date)
}
</script>
<style>
.is-selected {
    color: #1989fa;
}
</style>
  