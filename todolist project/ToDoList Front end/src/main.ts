import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

import zhCn from 'element-plus/es/locale/lang/zh-cn'
import i18n from '@/language/i18n' // 引入i18n模块

import '@/style/base.scss'

createApp(App)
.use(store)
.use(router)
.use(ElementPlus)
// ElementUI组件默认语言设为中文
.use(ElementPlus, {
    locale: zhCn
})
// 多语言
.use(i18n)
.mount('#app')
