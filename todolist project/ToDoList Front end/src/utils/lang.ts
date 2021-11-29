import { useI18n } from 'vue-i18n'
import { Language } from './type'

// 用于在setup最上面时使用，不用跳转页面
export const changeLanguage = (lang: Language) => {
  sessionStorage.setItem('language', lang)
  useI18n().locale.value = lang
  localStorage.setItem('lang', lang)
}
// 用于直接实现改变语言的功能
export const changeLanguageSetting = (lang: Language) => {
  sessionStorage.setItem('language', lang)
  location.reload()
}
