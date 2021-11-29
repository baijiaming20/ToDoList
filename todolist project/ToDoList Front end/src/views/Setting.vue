<template>
  <div>
    <div>
      <label style="display: block; margin: 20px 0; font-weight: 700">{{
        $t('setting.languageLabel')
      }}</label>
      <el-select v-model="LangNow" @change="saveEvent(LangNow)">
        <el-option :label="$t('setting.language.zh')" value="zh"></el-option>
        <el-option :label="$t('setting.language.en')" value="en"></el-option>
      </el-select>
    </div>
  </div>
</template>

<script lang="ts">
import { Ref, ref } from 'vue'
import { changeLanguageSetting } from '@/utils/lang'
import { useStore } from 'vuex'
import { Language } from '@/utils/type'

export default {
  setup() {
    const LangNow: Ref<string | undefined | null> = ref(sessionStorage.getItem('language'))

    const saveEvent = (lang: Language): void => {
      changeLanguageSetting(lang)
    }

    return {
      saveEvent,
      LangNow
    }
  }
}
</script>

<style scoped>
.btns {
  margin-top: 20px;
}
</style>
