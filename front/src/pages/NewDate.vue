<script>
import DateNews from '~/views/datenew.vue'
</script>
<template>
  <div>
    <el-container>
      <el-header>
        <el-row>
          <el-col :span="2">
            <img src="https://s1.ax1x.com/2023/04/12/ppjQLff.png" class="pt-5 pl-10" style="width :150px;height :60px" />
          </el-col>
          <el-col :span="16">
            <div>
              <el-form :model="form" class="flex items-center justify-center">
                <el-form-item>
                  <el-input v-model="form.newtitle" class="w-[500px] h-[60px] pr-4 pt-4" placeholder="请输入查找新闻标题或内容" />
                </el-form-item>
                <el-form-item class="pt-4">
                  <el-button class="w-[50px] h-[45px]" type="primary" @click="onSubmit" color="#10B981">搜索</el-button>
                </el-form-item>
              </el-form>
            </div>
          </el-col>
          <el-col :span="6">
            <div class=" pt-4 flex items-center justify-center">
              <div><el-avatar shape="square" :size="large" :src="avatorurl" /></div>
              <div>
                <el-dropdown @command="handleCommand">
                  <span class="pl-2 pt-1">
                    更多操作
                  </span>
                  <template #dropdown>
                    <el-dropdown-menu>
                      <el-dropdown-item command="a">个人中心</el-dropdown-item>
                      <el-dropdown-item command="b">修改信息</el-dropdown-item>
                      <el-dropdown-item command="c">发布文章</el-dropdown-item>
                      <el-dropdown-item command="d" disabled>登出</el-dropdown-item>
                    </el-dropdown-menu>
                  </template>
                </el-dropdown>
              </div>
            </div>
          </el-col>
        </el-row>
      </el-header>
      <el-main>
        <el-row>
          <el-col :span="4">
          </el-col>
          <el-col :span="16" class=" min-h-screen">
            <div>
              <DateNews/>
            </div>
          </el-col>
          <el-col :span="4">
            <div class="border-gray-500">
            </div>
          </el-col>
        </el-row>
      </el-main>
    </el-container>
  </div>
</template>

<script  setup>
import { useRouter } from 'vue-router'
import { reactive } from 'vue'
import url from "~/assets/avatar.jpg";
const router = useRouter()
const avatorurl = url;
const form = reactive({
  newtitle: ""
})

const onSubmit = () => {
}
const handleCommand = (command) => {
  if (command == 'a') {
    router.push('/about')
  } else if (command == 'b') {
    router.push('/edit')
  } else if (command == 'c') {
    router.push('/public')
  } else if (command == 'd') {
    router.push('/')
  }
}
</script>
