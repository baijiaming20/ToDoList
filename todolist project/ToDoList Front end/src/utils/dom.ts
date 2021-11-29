import { Ref, UnwrapRef } from 'vue'

// 设置一个html元素的行内css属性
export const css = (obj: HTMLElement, key: string, value: string): void => {
  obj.style[key as any] = value
}
// 设置一个Ref<UnwrapRef<HTMLElement | null>>对象中HTMLElement的行内css属性
export const rcss = (
  obj: Ref<UnwrapRef<HTMLElement | null>>,
  key: string,
  value: string
) => {
  (obj.value as HTMLElement).style[key as any] = value
}
