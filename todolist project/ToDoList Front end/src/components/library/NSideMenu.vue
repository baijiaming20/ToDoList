<template>
  <div class="n-menu" ref="nMenu">
    <div @click="isCollapse = !isCollapse" class="switch-btn">
      <div class="switchIcon" ref="switchIcon">
        <el-icon><arrow-right-bold /></el-icon>
      </div>
    </div>
    <div class="elMenu">
      <el-menu
        class="el-menu-vertical"
        :collapse="isCollapse"
        :router="props.router"
      >
        <slot></slot>
      </el-menu>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, Ref, ref, UnwrapRef, watch, onMounted } from 'vue'
import {
  Location,
  Document,
  Menu as IconMenu,
  Setting,
  ArrowRightBold,
  ArrowLeftBold
} from '@element-plus/icons'
import { rcss } from '@/utils/dom'

export default defineComponent({
  components: {
    Location,
    Document,
    Setting,
    IconMenu,
    ArrowRightBold,
    ArrowLeftBold
  },
  props: ['router'],
  setup(props) {
    const isCollapse = ref(true),
      switchIcon: Ref<UnwrapRef<HTMLElement | null>> = ref(null),
      nMenu: Ref<UnwrapRef<HTMLElement | null>> = ref(null),
      maxWidth = '200px'

    watch(isCollapse, (n: boolean, o?: boolean): void => {
      if (n) {
        rcss(nMenu, 'width', '63px')
        rcss(switchIcon, 'transform', 'scale(1)')
      } else {
        rcss(nMenu, 'width', maxWidth)
        rcss(switchIcon, 'transform', 'scale(-1, 1)')
      }
    })

    onMounted(() => {
      isCollapse.value = false
    })

    return {
      isCollapse,
      switchIcon,
      nMenu,
      props
    }
  }
})
</script>

<style lang="scss" scoped>
@import '@/style/base.scss';

.n-menu {
  display: flex;
  flex-direction: column;
  transition: width 320ms;
  width: 63px;
  overflow: hidden;
  background-color: #eef2f5;
}
.switch-btn {
  width: calc(100% - 2px);
  height: calc(40px - 2px);
  line-height: 40px;
  cursor: pointer;
  display: flex;
  justify-content: center;
  transition: background-color .3s;
  border: 1px solid #eef2f5;
  border-radius: 2px;
  &:hover {
    background-color: #ecf5ff;
  }
}

.elMenu {
  width: 100%;
}
.el-menu-vertical {
  border: none;
}

.el-menu-item,
.switch-btn,
.elMenu,
.el-menu-vertical {
  background-color: $bg-color-2;
}
</style>
