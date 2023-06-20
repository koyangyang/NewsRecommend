<template>
  <div class=" p-2 rounded-xl border-3 border-light-blue-500">
    <el-form :model="form">
      <p class="font-bold text-lg">发表评论：</p>
      <div class="pt-1">
        <v-md-editor v-model="form.cCnt" height="200px"></v-md-editor>
      </div>
      <el-form-item>
        <div class="pt-3 pl-25 pr-25">
          <el-button
            class="w-[250px]"
            type="primary"
            @click="onSubmit"
            color="#38bdf8"
            >提交</el-button
          >
        </div>
        <div class="pt-3 pl-30 pr-25">
          <el-button
            class="w-[250px]"
            type="primary"
            @click="Restore"
            color="#38bdf8"
            >复位</el-button
          >
        </div>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { reactive, getCurrentInstance } from "vue";
import { insertComment } from "~/api/comments";
import { useRouter } from "vue-router";
export default {
  name: "replycomment",
  setup() {
    const form = reactive({
      uId: 1,
      cCnt: "",
    });
    const {
      appContext: {
        config: { globalProperties },
      },
    } = getCurrentInstance();
    const router = useRouter();
    const onSubmit = () => {
      var nId = window.location.href.slice(33);
      insertComment(form.uId, nId, form.cCnt,globalProperties.$uname).then((res) => {
        router.push("/newsdetail/" + nId);
      });
    };
    const Restore = () => {
      form.cCnt = "";
    };
    return {
      form,
      onSubmit,
      Restore,
    };
  },
  data() {
    return {};
  },
};
</script>
