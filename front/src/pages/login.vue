<template>
  <el-row class="min-h-screen bg-indigo-500">
    <el-col :span="16" class="flex items-center justify-center">
      <div>
        <div class="font-bold text-5xl text-light-50 mb-4">基于协同过滤算法的新闻推荐系统</div>
        <div class="text-gray-200 text-xl flex item-center justify-center">
          <div>欢迎光临</div>
        </div>
      </div>
    </el-col>
    <el-col
      :span="8"
      class="bg-light-50 flex items-center justify-center flex-col"
    >
      <h2 class="font-bold text-3xl text-gray-800">欢迎回来</h2>
      <div
        class="flex items-center justify-center my-5 text-gray-300 space-x-2"
      >
        <span class="h-[1px] w-16 bg-gray-200"></span>
        <span>账号密码登录</span>
        <span class="h-[1px] w-16 bg-gray-200"></span>
      </div>
      <el-form :model="form" class="w-[250px]">
        <el-form-item>
          <el-input v-model="form.userName" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item>
          <el-input
            v-model="form.userPwd"
            show-password
            placeholder="请输入密码"
          />
        </el-form-item>
        <el-form-item>
          <el-button
            class="w-[250px]"
            type="primary"
            @click="onSubmit"
            round
            color="#626aef"
            >登录</el-button
          >
        </el-form-item>
        <el-form-item>
          <el-button
            class="w-[250px]"
            type="primary"
            @click="register"
            round
            color="#626aef"
            >注册</el-button
          >
        </el-form-item>
        <el-form-item>
          <el-button
            class="w-[250px]"
            type="primary"
            @click="register"
            round
            color="#545454"
            >访客浏览</el-button
          >
        </el-form-item>
      </el-form>
    </el-col>
  </el-row>
</template>

<script setup>
import { reactive, getCurrentInstance } from "vue";
import { login, adduser } from "~/api/user";
import { ElNotification } from "element-plus";
import { useRouter } from "vue-router";

const router = useRouter();

const form = reactive({
  userName: "",
  userPwd: "",
});
const {
  appContext: {
    config: { globalProperties },
  },
} = getCurrentInstance();
const onSubmit = () => {
  login(form.userName, form.userPwd).then((res) => {
    if (!res.data) {
      ElNotification({
        title: "登录失败",
        message: "用户名或密码错误",
        type: "error",
        duration: 3000,
      });
    } else {
      ElNotification({
        title: "登录成功",
        message: "登录成功等待系统生成推荐数据",
        type: "success",
        duration: 3000,
      });
      globalProperties.$uname = res.data.userName;
      globalProperties.$upwd = res.data.userPwd;
      globalProperties.$uid = res.data.userId;
      globalProperties.$ucnt = res.data.userCnt;
      globalProperties.$uurl= res.data.userUrl;
      globalProperties.$birthday = res.data.userBirthday;
      globalProperties.$sex = res.data.userGender;
      globalProperties.$phone = res.data.userPhone;
      globalProperties.$email = res.data.userEmail;
      router.push("/index");
    }
  });
};
const register = () => {
  adduser(form.userName, form.userPwd).then((res) => {
    if (!res.data) {
      ElNotification({
        title: "注册失败",
        message: "请检测用户名和密码是否重复",
        type: "error",
        duration: 3000,
      });
    } else {
      ElNotification({
        title: "注册成功",
        message: "注册成功等待系统跳转",
        type: "success",
        duration: 2000,
      });
      router.push("/index");
    }
  });
};
</script>
