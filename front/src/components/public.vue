<template>
    <div>
        <el-form :model="form">
            <div class="flex">
                <el-form-item class="pr-10 w-[450px]">
                    <p class="font-bold text-lg">新闻标题：</p>
                    <el-input v-model="form.newTitle" placeholder="请输入新闻标题" />
                </el-form-item>
                <el-form-item class="w-[450px]">
                    <p class="font-bold text-lg">封面图片地址(Url)：</p>
                    <el-input v-model="form.newCover" placeholder="请输入封面图片地址" />
                </el-form-item>
            </div>
            <el-form-item>
                <div class="flex">
                    <div class="font-bold text-lg w-[100px]">新闻分类：</div>
                    <div class="pr-13"><el-input v-model="form.newCategory" placeholder="请输入新闻分类" /></div>
                    <div class="font-bold text-lg w-[100px]">发布日期：</div>
                    <div><el-date-picker v-model="form.Date" type="date" placeholder="选择日期"
                            :disabled-date="disabledDate" /></div>
                </div>
            </el-form-item>
            <p class="font-bold text-lg">新闻详细内容：</p>
            <div class="pt-1">
                <v-md-editor v-model="form.newCnt" height="380px"></v-md-editor>
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
import { reactive } from 'vue'
import { ref } from 'vue'
import { addnew } from '~/api/news'
import { useRouter } from 'vue-router'
export default {
    name: 'publicedit',
    setup() {
        const value = ref('');
        const form = reactive({
            newTitle: '',
            newCategory: '',
            newCnt: '',
            newUrl: '',
            newCover: '',
            newDate: '',
            Date: value
        });
        const router= useRouter()
        const onSubmit = () => {
            var now = new Date();
            var month = now.getMonth() + 1 > 10 ? now.getMonth() + 1 : '0' + (now.getMonth() + 1);
            var day = now.getDate() > 10 ? now.getDate() : '0' + now.getDate();
            form.newDate = now.getFullYear() + '-' + month + '-' + day;
            addnew(form.newCategory, form.newTitle, form.newCnt, form.newCover, form.newDate).then(res => {
                if(res.status == 200 && res.data.newId > 0)
                {
                    router.push('/newsdetail/'+res.data.newId)
                }else
                {
                    alert('提交失败')
                }
            })
        };
        const Restore = () => {
            form.newTitle = '';
            form.newCategory = '';
            form.newCnt = '';
            form.newUrl = '';
            form.Date = '';
        };
        return {
            form,
            onSubmit,
            Restore
        };
    },
    data() {
        return {
        };
    },
    methods: {
        disabledDate(time) {
            return time.getTime() > Date.now()
        }
    }
};
</script>