import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from './router'
import VueMarkdownEditor from '@kangc/v-md-editor';
import '@kangc/v-md-editor/lib/style/base-editor.css';
import vuepressTheme from '@kangc/v-md-editor/lib/theme/vuepress.js';
import '@kangc/v-md-editor/lib/theme/style/vuepress.css';
import VMdPreview from '@kangc/v-md-editor/lib/preview';
import '@kangc/v-md-editor/lib/style/preview.css';
import githubTheme from '@kangc/v-md-editor/lib/theme/github.js';
import '@kangc/v-md-editor/lib/theme/style/github.css';

// highlightjs
import hljs from 'highlight.js';

VMdPreview.use(githubTheme, {
  Hljs: hljs,
});
import Prism from 'prismjs';

VueMarkdownEditor.use(vuepressTheme, {
  Prism,
});

const app = createApp(App)
app.config.globalProperties.$avatorurl = 'https://img-oss.langs.ink/2023/04/21/64426202c6c66.webp'
app.config.globalProperties.$uid = 1
app.config.globalProperties.$nid = 1
app.config.globalProperties.$uname = "admin"
app.use(router)
app.use(ElementPlus)
import 'virtual:windi.css'
app.use(VueMarkdownEditor);
app.use(VMdPreview);
app.mount('#app')
