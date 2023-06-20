<template>
  <div class="p-5 border-1 border-gray-500">
    <el-form :model="form">
      <div class="flex">
        <el-form-item class="pr-10 w-[450px]">
          <p class="font-bold text-lg">用户名：</p>
          <el-input v-model="form.userName" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item class="w-[450px]">
          <p class="font-bold text-lg">密码：</p>
          <el-input v-model="form.userPwd" show-password placeholder="请输入密码" />
        </el-form-item>
      </div>
      <div>
        <el-form-item class="w-[900px]">
          <p class="font-bold text-lg">头像Url地址：</p>
          <el-input v-model="form.userUrl" placeholder="请输入头像图片地址" />
        </el-form-item>
      </div>
      <el-form-item>
        <div class="flex">
          <div class="font-bold text-lg w-[100px]">性别：</div>
          <div class="pr-13">
            <el-input v-model="form.gender" placeholder="请输入性别" />
          </div>
          <div class="font-bold text-lg w-[100px]">出生年月：</div>
          <div>
            <el-date-picker v-model="form.Date" type="date" placeholder="选择日期" :disabled-date="disabledDate" />
          </div>
        </div>
      </el-form-item>
      <p class="font-bold text-lg">个人描述：</p>
      <div class="pt-1">
        <v-md-editor v-model="form.userCnt" height="180px"></v-md-editor>
      </div>
      <div class="flex pt-5">
        <el-form-item class="pr-10 w-[450px]">
          <p class="font-bold text-lg">手机号：</p>
          <el-input v-model="form.phone" placeholder="请输入手机号" />
        </el-form-item>
        <el-form-item class="w-[450px]">
          <p class="font-bold text-lg">电子邮箱：</p>
          <el-input v-model="form.email" placeholder="请输入电子邮件" />
        </el-form-item>
      </div>
      <el-form-item>
        <div class="pt-3 pl-25 pr-25">
          <el-button class="w-[250px]" type="primary" @click="onSubmit" color="#38bdf8">提交</el-button>
        </div>
        <div class="pt-3 pl-30 pr-25">
          <el-button class="w-[250px]" type="primary" @click="Restore" color="#38bdf8">复位</el-button>
        </div>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { reactive, getCurrentInstance, onMounted } from "vue";
import { useRouter } from "vue-router";
import { ElNotification } from "element-plus";
import { updateuser } from "~/api/user";
export default {
  name: "edit",
  setup() {
    const form = reactive({
      userName: "",
      userPwd: "",
      userId: "",
      userCnt: "",
      userUrl: "",
      Date: "",
      sex: "",
      gender:"",
      phone: "",
      email: "",
    });
    const router = useRouter();
    const onSubmit = () => {
      let formData = new FormData();
      formData.append("userName", form.userName);
      formData.append("userPwd", form.userPwd);
      formData.append("userId", form.userId);
      formData.append("userCnt", form.userCnt);
      formData.append("userUrl", form.userUrl);
      formData.append("userBirthday", form.Date);
      formData.append("userGender", form.sex);
      formData.append("userPhone", form.phone);
      formData.append("userEmail", form.email);
      updateuser(formData).then((res) => {
        if (!res.data) {
          ElNotification({
            title: "修改失败",
            message: "请修改内容后重新提交",
            type: "error",
            duration: 3000,
          });
        } else {
          ElNotification({
            title: "修改成功",
            message: "修改成功即将跳转至首页",
            type: "success",
            duration: 2000,
          });
          globalProperties.$uname = form.userName;
          globalProperties.$upwd = form.userPwd;
          globalProperties.$uid = form.userId;
          globalProperties.$ucnt = form.userCnt;
          globalProperties.$uurl = form.userUrl;
          globalProperties.$birthday = form.Date;
          globalProperties.$phone = form.phone;
          globalProperties.$sex = form.sex;
          globalProperties.$email = form.email;
          router.push("/index");
        }
      });
    };
    const Restore = () => { };
    const {
      appContext: {
        config: { globalProperties },
      },
    } = getCurrentInstance();
    onMounted(async () => {
      form.userName = globalProperties.$uname;
      form.userPwd = globalProperties.$upwd;
      form.userId = globalProperties.$uid;
      form.userCnt = globalProperties.$ucnt;
      form.userUrl = globalProperties.$uurl;
      form.Date = new Date(globalProperties.$birthday);
      form.phone = globalProperties.$phone;
      form.sex = globalProperties.$sex;
      form.gender = globalProperties.$sex == 1?"男":"女";
      form.email = globalProperties.$email;
    });
    return {
      form,
      onSubmit,
      Restore,
      onMounted,
    };
  },
  data() {
    return {};
  },
  methods: {
    disabledDate(time) {
      return time.getTime() > Date.now();
    },
  },
};
</script>
