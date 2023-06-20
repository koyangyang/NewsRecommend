<template>
  <div class="p-20">
    <el-form :model="form">
      <div class="flex p-5 border-1 border-gray-500">
        <el-avatar :size="large" :src="form.userUrl" />
        <p class="font-bold text-lg pl-10 pt-1">用户名：{{ form.userName }}</p>
        <p class="font-bold text-lg pl-10 pt-1">性别：</p>
        <p v-if="form.sex==1" class="font-bold text-lg pl-10 pt-1">男</p>
        <p v-else="" class="font-bold text-lg pl-10 pt-1">女</p>
      </div>
      <div class="flex p-5 border-1 border-gray-500">
        <div class="flex font-bold text-lg w-[200px]">
          <p>身份信息：</p>
          <p>管理员</p>
        </div>
        <div class="font-bold text-lg w-[100px]">出生日期：</div>
        <div>
          <el-date-picker
            v-model="form.Date"
            type="date"
            placeholder="选择日期"
            :disabled-date="disabledDate"
          />
        </div>
      </div>
      <div class="p-5 border-1 border-gray-500">
        <p class="font-bold text-lg">用户个人介绍：</p>
        <v-md-preview :text="form.userCnt"></v-md-preview>
      </div>
      <div class="p-5 border-1 border-gray-500">
        <p class="font-bold text-lg">联系方式：</p>
        <p class="font-bold text-lg">电话号码：{{ form.phone }}</p>
        <p class="font-bold text-lg">电子邮件：{{ form.email }}</p>
      </div>
    </el-form>
  </div>
</template>

<script>
import { reactive, getCurrentInstance, onMounted } from "vue";

export default {
  name: "edit",
  setup() {
    const form = reactive({
      userName: "",
      userId: "",
      userCnt: "",
      userUrl: "",
      Date: "",
      sex: "",
      phone: "",
      email: "",
    });
    const {
      appContext: {
        config: { globalProperties },
      },
    } = getCurrentInstance();
    onMounted(async () => {
      form.userName = globalProperties.$uname;
      form.userId = globalProperties.$uid;
      form.userCnt = globalProperties.$ucnt;
      form.userUrl = globalProperties.$uurl;
      form.Date = new Date(globalProperties.$birthday);
      form.phone = globalProperties.$phone;
      form.sex = globalProperties.$sex;
      form.email = globalProperties.$email;
    });
    return {
      form,
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
