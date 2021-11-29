<template>
  <div class="add-box">
    <el-form ref="form" label-position="top">
      <el-form-item :label="$t('viewTable.title')">
        <el-input
          v-model="toDoEditInfo.title"
          maxlength="100"
          :placeholder="$t('viewTable.pleaseInput')"
          show-word-limit
          type="text"
        >
        </el-input>
      </el-form-item>
      <el-form-item :label="$t('viewTable.endTime')">
        <el-date-picker
          v-model="toDoEditInfo.endTime"
          type="datetime"
          :placeholder="$t('viewTable.promptSelection')"
          value-format="YYYY-MM-DD HH:mm:ss"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item :label="$t('viewTable.details')">
        <el-input
          v-model="toDoEditInfo.details"
          :autosize="{ minRows: 3, maxRows: 5 }"
          type="textarea"
          :placeholder="$t('viewTable.pleaseInput')"
          maxlength="250"
        >
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="emptyForm">{{ $t('sundry.empty') }}</el-button>
        <el-button type="primary" @click="addTask">{{
          $t('sundry.confirm')
        }}</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script lang="ts">
import { ref } from 'vue'
import { addToDo } from '@/api/todo'
import { message, MessageType } from '@/utils/common'
import { useI18n } from 'vue-i18n'

export default {
  setup() {
    const toDoEditInfo = ref({
        title: null,
        details: null,
        endTime: null
      }),
      { t } = useI18n(),
      // 添加待办事项
      addTask = () => {
        const {title, endTime} = toDoEditInfo.value
        if (!title || !endTime) {
          message(t('message.cannotBeEmpty'), MessageType.error)
          return
        }
        for (const key in toDoEditInfo.value) {
            (toDoEditInfo.value as any)[key] = (toDoEditInfo.value as any)[key] ? (toDoEditInfo.value as any)[key].toString() : ''
        }
        addToDo(toDoEditInfo.value).then(({ data }) => {
          if (data) {
            message(t('message.addedSuccessfully'))
            setTimeout(() => {
              location.reload()
            }, 1000)
          }
        })
      },
      // 清空表单
      emptyForm = () => {
        for (const key in toDoEditInfo.value) {
          (toDoEditInfo.value as any)[key] = null
        }
      }

    return {
      toDoEditInfo,
      addTask,
      emptyForm
    }
  }
}
</script>

<style scoped>
.add-box {
  width: 500px;
}
</style>
