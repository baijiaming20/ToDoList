<template>
  <nMenu :router="true">
    <el-menu-item index="/view">
      <el-icon><document /></el-icon>
      <template #title>{{ $t('sideMenu.view') }}</template>
    </el-menu-item>
    <el-menu-item index="/addition">
      <el-icon><document-add /></el-icon>
      <template #title>{{ $t('sideMenu.add') }}</template>
    </el-menu-item>
    <el-menu-item index="/setting">
      <el-icon><setting /></el-icon>
      <template #title>{{ $t('sideMenu.setting') }}</template>
    </el-menu-item>
  </nMenu>
</template>

<script>
import {
  Location,
  Document,
  Menu as IconMenu,
  Setting,
  ArrowRightBold,
  ArrowLeftBold,
  DocumentAdd
} from '@element-plus/icons'
import nMenu from '@/components/library/NSideMenu.vue'

export default {
  components: {
    Location,
    Document,
    Setting,
    IconMenu,
    ArrowRightBold,
    ArrowLeftBold,
    Setting,
    DocumentAdd,
    nMenu
  },
  setup() {
  }
}
</script>

<style></style>
