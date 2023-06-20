<template>
  <div>
    <div class="pb-2 font-bold text-xl text-red-600">今日热榜：</div>
    <div>
      <el-scrollbar ref="scrollbarRef" height="500px" always>
        <div class="flex-row justify-center items-center">
          <div v-for="item in newsList" :key="item">
            <div>
              <el-button
                type="primary"
                color="#38bdf8"
                :id="item.newId"
                @click="onSubmit"
                class="scrollbar-demo-item bg-light-50"
                >{{ item.newTitle.slice(0, 14) }}...</el-button
              >
            </div>
          </div>
        </div>
      </el-scrollbar>
    </div>
  </div>
</template>

<script>
import { gethotnews } from "~/api/news";
import { useRouter } from "vue-router";
export default {
  name: "hotnews",
  setup() {
    const router = useRouter();
    const onSubmit = (e) => {
      router.push("/newsdetail/" + e.currentTarget.id);
    };
    return {
      onSubmit,
    };
  },
  data() {
    return {
      newsList: [],
    };
  },
  mounted() {
    gethotnews().then((res) => {
      this.newsList = res.data;
    });
  },
};
</script>
<style scoped>
.scrollbar-demo-item {
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 5px;
  height: 35px;
  text-align: center;
  border-radius: 4px;
  /* background: var(--el-color-primary-light-9);
  color: var(--el-color-primary); */
  width: 220px;
  /* border: 2px solid #0094ff; */
  border: 2px solid #10B981;
}

.el-slider {
  margin-top: 20px;
}
</style>
