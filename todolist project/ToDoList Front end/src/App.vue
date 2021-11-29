<template>
  <div class="common-layout">
    <el-container>
      <el-header>{{ $t('title') }}</el-header>
      <el-container>
        <el-aside>
          <SideMenu></SideMenu>
        </el-aside>
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script lang="ts">
import SideMenu from '@/components/SideMenu.vue'
import { changeLanguage } from '@/utils/lang'
import { Language } from './utils/type'
import { allToDos } from './api/todo'
import { message, notification, toFormat } from './utils/common'
import { useI18n } from 'vue-i18n'

export default {
  components: {
    SideMenu
  },
  setup() {
    // 设置语言
    const language = sessionStorage.getItem('language') || Language.zh
    changeLanguage(language as Language)

    // 监听任务
    const { t } = useI18n()
    allToDos().then(({ data }) => {
      // 当前时间
      const currentDate = new Date()
      data.forEach((item: any) => {
        const // 结束时间
          endDate = new Date(Date.parse(item.endTimeStr.replace(/-/g, '/'))),
          // 时间差
          gap = endDate.getTime() - currentDate.getTime()

        if (gap <= 0) {
          return
        }
        setTimeout(() => {
          notification(item.title + '\n' + t('message.hasBeenReached'))
        }, gap)
      })
    })
    return {}
  }
}
</script>

<style lang="scss" scoped>
@import '@/style/base.scss';
@import '@/style/mixin.scss';

.common-layout,
.el-container {
  height: 100%;
}

.el-footer {
  background-color: #b3c0d1;
  color: var(--el-text-color-primary);
  text-align: center;
  line-height: 60px;
}

.el-header {
  height: 50px;
  line-height: 50px;
  text-align: left;
  // background-color: $bg-color-3;
  background-color: $bg-color;
  padding: 0 40px;
  font-size: 18px;
  box-shadow: 0 1px 4px rgb(0 21 41 / 8%);
  border-bottom: 1px solid #409eff;
  @include disable-select;
  @include letter-spacing-little;
}

.el-aside {
  width: auto;
  height: auto;
  background-color: $bg-color-2;
  color: var(--el-text-color-primary);
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: $bg-color;
  color: var(--el-text-color-primary);
  overflow: auto;
  // text-align: center;
  // line-height: 160px;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
</style>
