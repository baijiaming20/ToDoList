import request from '@/utils/request'

// 查询待办事项
export const selectToDoList = (start: number, end: number) => {
  return request({
    url: '/selectToDoList/' + start + '/' + end
  })
}

// 查询待办事项总数
export const selectToDoTotal = () => {
  return request({
    url: '/selectToDoTotal'
  })
}

// 更新一个待办事项的信息
export const updateToDo = (data: any) => {
  return request({
    url: '/updateToDo',
    method: 'post',
    params: {
      id: data.id,
      title: data.title,
      details: data.details,
      endTimeStr: data.endTime
    }
  })
}

// 添加一条待办事项
export const addToDo = (data: any) => {
  return request({
    url: '/addToDo',
    method: 'post',
    params: {
      id: data.id,
      title: data.title,
      details: data.details,
      endTimeStr: data.endTime
    }
  })
}

// 添加一条待办事项
export const deleteToDo = (id: number) => {
  return request({
    url: '/deleteToDo',
    method: 'post',
    params: {
      id
    }
  })
}

// 全部待办事项
export const allToDos = () => {
  return request({
    url: '/allToDos'
  })
}