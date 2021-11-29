import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    redirect: '/view'
  },
  {
    path: '/view',
    name: 'view',
    component: () => import('@/views/View.vue')
  },
  {
    path: '/addition',
    name: 'addition',
    component: () => import('@/views/Addition.vue')
  },
  {
    path: '/setting',
    name: 'setting',
    component: () => import('@/views/Setting.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
