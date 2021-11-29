<template>
  {{ $t('message.string_lang') }}
  <div>
    {{ $t('message.string_lang') }}
    <span @click="changeLang">语言切换</span>
  </div>
</template>

<script lang="ts">
// import { defineComponent, getCurrentInstance, reactive, toRefs } from 'vue-demi'
import { useI18n } from 'vue-i18n'

import { defineComponent, ref } from 'vue'

export default defineComponent({
  setup() {
    //下面这行代码要写在所有代码前面
    const { locale, t } = useI18n()
    //在setup中使用
    console.log(t('message.string_lang'))

    //如果在ref / reactive中使用
    //最好这样使用
    let lang = ref('message.string_lang')
    //let lang = ref(t'message.string_lang') 这种方式目前我测试  在语言切换后页面上达不到响应式

    //语言切换
    const changeLang = () => {
      //切换语言 值要对应上面index中导出的名字  要一致  第四步
      locale.value = 'en'
      //缓存到localStorage中下次进来还是切换后的语言  如果不需要可以删除下面代码
      localStorage.setItem('lang', 'en')
    }

    return {
      lang,
      changeLang
    }
  }
})
</script>

<style></style>
