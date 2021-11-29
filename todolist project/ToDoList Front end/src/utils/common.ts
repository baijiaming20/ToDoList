import { ElMessage } from 'element-plus'

export enum MessageType {
  info = 'info',
  success = 'success',
  warning = 'warning',
  error = 'error'
}

// 设置路由
export const setRouter = (router: string = '') => {
  const location = window.location
  location.href = location.origin + '/#/' + router
}

// 消息提示
export const message = (
  content: string,
  type: MessageType = MessageType.success
) => {
  ElMessage({
    showClose: true,
    message: content,
    type: type
  })
}

// 获取url中的参数
export const queryParam = (key: string): string | null => {
  const reg = new RegExp('(^|&)' + key + '=([^&]*)(&|$)', 'i'),
    hash = window.location.hash

  const r = hash.substring(hash.lastIndexOf('?')).substr(1).match(reg)

  if (r != null) {
    return decodeURIComponent(r[2])
  }
  return null
}

//替换指定传入参数的值,paramName为参数,replaceWith为新值
export const replaceParamVal = (
  paramName: string,
  replaceWith: string
): void => {
  var oUrl = window.location.href.toString()
  var re = eval('/(' + paramName + '=)([^&]*)/gi')
  var nUrl = oUrl.replace(re, paramName + '=' + replaceWith)
  // window.location = nUrl;
  window.location.href = nUrl
}

// 读取参数的值
export const getParamVal = (name: string) => {
  var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)')
  var r = window.location.search.substr(1).match(reg)
  if (r != null) {
    return unescape(r[2])
  }
  return null
}

// 发送windows通知
export const notification = (content: string) => {
  if (window.Notification !== undefined) {
    Notification.requestPermission(() => {
      const n = new Notification('ToDoList', {
        body: content
        // icon: ''
      })
      setTimeout(() => {
        n.close()
      }, 4000)
    })
  }
}

// 将毫秒转换为小时、分钟、秒
export const toFormat = (ms: number) => {
  const hour: number = parseInt((ms / 1000 / 60 / 60).toString()),
    minute: number = parseInt((ms / 1000 / 60).toString()) - hour * 60,
    second: number = parseInt((ms / 1000).toString()) - minute * 60 - hour * 60 * 60

  return `${hour}:${minute}:${second}`
}
