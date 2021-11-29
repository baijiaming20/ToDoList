<template>
  <el-table :data="tableData" stripe>
    <el-table-column type="expand">
      <template #default="props">
        <p style="word-wrap: break-word">{{ props.row.details }}</p>
      </template>
    </el-table-column>
    <!-- 标题 -->
    <el-table-column
      prop="title"
      :label="$t('viewTable.title')"
      width="300"
      :show-overflow-tooltip="true"
    />
    <!-- 结束时间 -->
    <el-table-column prop="endTimeStr" :label="$t('viewTable.end_time')" />
    <!-- 状态 -->
    <el-table-column :label="$t('viewTable.state')">
      <template #default="scope">
        <el-popover effect="light" trigger="hover" placement="top">
          <template #default>
            <p>{{ $t('viewTable.timeRemaining') + ': ' }}</p>
            <p>{{ scope.row.time || $t('viewTable.done') }}</p>
          </template>
          <template #reference>
            <div class="name-wrapper">
              <el-tag
                size="medium"
                v-if="scope.row.state === 'going'"
                @mouseenter="tagEnter(scope.row, scope.row.endTimeStr)"
                >{{ $t('viewTable.going') }}</el-tag
              >
              <el-tag
                size="medium"
                v-if="scope.row.state === 'done'"
                type="success"
                >{{ $t('viewTable.done') }}</el-tag
              >
            </div>
          </template>
        </el-popover>
      </template>
    </el-table-column>
    <!-- 操作 -->
    <el-table-column :label="$t('viewTable.operation')">
      <template #default="props">
        <el-button
          size="mini"
          type="primary"
          @click="handleEdit(props.row)"
          plain
        >
          {{ $t('viewTable.edit') }}
        </el-button>
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(props.row.id)"
          plain
        >
          {{ $t('viewTable.delete') }}
        </el-button>
      </template>
    </el-table-column>
  </el-table>
  <!-- 分页 -->
  <div class="pagination-block">
    <el-pagination
      v-model:currentPage="currentPage"
      layout="prev, pager, next, jumper"
      :page-size="pageSize"
      :total="paginationTotal"
      @current-change="currentChange(currentPage)"
      :background="true"
      :default-current-page="1"
    >
    </el-pagination>
  </div>

  <el-dialog v-model="dialogVisible" :title="$t('viewTable.edit')">
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
          :autosize="{ minRows: 2, maxRows: 4 }"
          type="textarea"
          :placeholder="$t('viewTable.pleaseInput')"
          maxlength="250"
        >
        </el-input>
      </el-form-item>
    </el-form>

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">
          {{ $t('sundry.cancel') }}
        </el-button>
        <el-button type="primary" @click="updateConfirm">
          {{ $t('sundry.confirm') }}
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts">
import { ref, onMounted } from 'vue'
import {
  selectToDoList,
  selectToDoTotal,
  updateToDo,
  deleteToDo
} from '@/api/todo'
import { MessageType, message, toFormat } from '@/utils/common'
import { useI18n } from 'vue-i18n'

export default {
  setup() {
    const tableData = ref(null),
      paginationTotal = ref(0),
      currentPage = ref(1),
      // 每页现实数据的数量
      pageSize = 8,
      dialogVisible = ref(false),
      toDoEditInfo = ref({
        id: null,
        title: '',
        details: '',
        endTime: null
      }),
      currentEditId = ref(0),
      { t } = useI18n()

    const // 获取数据并更新表格
      updateTable = (start: number, end: number) => {
        selectToDoList(start, end).then(({ data }) => {
          const currentDate = new Date()
          data.forEach((item: any) => {
            const endDate = new Date(
                Date.parse(item.endTimeStr.replace(/-/g, '/'))
              ),
              // 时间差
              gap = endDate.getTime() - currentDate.getTime()
            if (gap <= 0) {
              item.state = 'done'
            } else {
              item.state = 'going'
              item.time = toFormat(gap)
            }
          })
          tableData.value = data
        })
      },
      // 分页改变
      currentChange = (index: number) => {
        updateTable((index - 1) * pageSize, pageSize)
      },
      // 任务编辑
      handleEdit = (row: any) => {
        dialogVisible.value = true
        toDoEditInfo.value.id = row.id
        toDoEditInfo.value.title = row.title && row.title.toString()
        toDoEditInfo.value.details = row.details && row.details.toString()
        toDoEditInfo.value.endTime = row.endTimeStr && row.endTimeStr.toString()
      },
      // 任务删除
      handleDelete = (id: number) => {
        deleteToDo(id).then(({ data }) => {
          if (data) {
            message('删除成功！')
            updateTable(0, pageSize)
          }
        })
      },
      // 任务更新
      updateConfirm = () => {
        if (!toDoEditInfo.value.title) {
          message(t('message.taskTitleCantBeEmpty'), MessageType.error)
          return
        }

        if (!toDoEditInfo.value.details) {
          toDoEditInfo.value.details = ''
        }

        dialogVisible.value = false

        updateToDo(toDoEditInfo.value).then(({ data }) => {
          if (data) {
            updateTable(0, pageSize)
            message(t('message.updateSuccess'))
          }
        })
      },
      tagEnter = (item: any, endTimeStr: string) => {
        const endDate = new Date(Date.parse(endTimeStr.replace(/-/g, '/')))
        item.time = toFormat(endDate.getTime() - new Date().getTime())
      }

    // mounted
    onMounted(() => {
      selectToDoTotal()
        .then(data => {
          paginationTotal.value = data.data
        })
        .then(() => {
          updateTable(0, pageSize)
        })
    })

    return {
      tableData,
      paginationTotal,
      currentPage,
      pageSize,
      dialogVisible,
      toDoEditInfo,
      currentEditId,
      currentChange,
      handleEdit,
      handleDelete,
      updateConfirm,
      tagEnter
    }
  }
}
</script>

<style scoped lang="scss">
.pagination-block {
  margin-top: 20px;
}
</style>
