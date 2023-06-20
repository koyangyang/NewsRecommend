<template>
  <div class="p-1 rounded-xl border-3 border-light-blue-500">
    <el-form :model="form">
      <el-form-item>
        <div class="pt-2 pl-25 pr-25">
          <el-button
            class="w-[100px]"
            type="primary"
            @click="alike"
            color="#38bdf8"
            round
            >点赞 {{ form.like }}</el-button
          >
        </div>
        <div class="pt-2 pl-30 pr-25">
          <el-button
            class="w-[100px]"
            type="primary"
            @click="adislike"
            color="#38bdf8"
            round
            >点踩 {{ form.dislike }}</el-button
          >
        </div>
        <div class="pt-2 pl-30 pr-25">
          <el-button
            class="w-[100px]"
            type="primary"
            @click="aforward"
            color="#38bdf8"
            round
            >转发 {{ form.forward }}</el-button
          >
        </div>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { reactive } from "vue";
import { getnewstatus, adddislike, addforward, addlike } from "~/api/newstatus";
import { useRouter } from "vue-router";
export default {
  name: "newstatus",
  setup() {
    var form = reactive({
      nid: 0,
      like: 0,
      dislike: 0,
      forward: 0,
    });
    const router = useRouter();
    const alike = () => {
      addlike(form.nid, form.like, form.dislike, form.forward).then((res) => {
        form.like = res.data.newLike;
        form.dislike = res.data.newDislike;
        form.forward = res.data.newForward;
      });
    };
    const adislike = () => {
      adddislike(form.nid, form.like, form.dislike, form.forward).then(
        (res) => {
          form.like = res.data.newLike;
          form.dislike = res.data.newDislike;
          form.forward = res.data.newForward;
        }
      );
    };
    const aforward = () => {
      addforward(form.nid, form.like, form.dislike, form.forward).then(
        (res) => {
          form.like = res.data.newLike;
          form.dislike = res.data.newDislike;
          form.forward = res.data.newForward;
        }
      );
    };
    return {
      form,
      alike,
      adislike,
      aforward,
    };
  },
  data() {
    return {};
  },
  mounted() {
    var url = window.location.href;
    this.form.nid = url.slice(url.lastIndexOf("/") + 1);
    getnewstatus(this.form.nid).then((res) => {
      this.form.like = res.data.newLike == null ? 0 : res.data.newLike;
      this.form.dislike = res.data.newDislike == null ? 0 : res.data.newDislike;
      this.form.forward = res.data.newForward == null ? 0 : res.data.newForward;
    });
  },
};
</script>
