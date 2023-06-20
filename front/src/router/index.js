import {
    createRouter,
    createWebHistory
} from 'vue-router'

import Index from '~/pages/index.vue'
import Login from '~/pages/login.vue'
import NotFound from '~/pages/404.vue'
import About from '~/pages/about.vue'
import Public from '~/pages/Public.vue'
import Edit from '~/pages/edit.vue'
import NewDate from '~/pages/NewDate.vue'
import NewDetail from '~/views/NewDetail.vue'
const routes = [
    {
        path: '/',
        component: Login
    },
    {
        path: '/index',
        component: Index
    },
    {
        path: '/about',
        component: About
    }, {
        path: '/public',
        component: Public
    },
    {
        path: '/edit',
        component: Edit
    },
    {
        path: '/newdate/:date',
        component: NewDate,
         props: true
    },
    {
        path: '/newsdetail/:id',
        component: NewDetail,
         props: true
    },
    {
        path: '/:pathMatch(.*)*',
        name: 'NotFound',
        component: NotFound
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes: routes
})
export default router
