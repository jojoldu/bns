/**
 * Created by jojoldu@gmail.com on 2018. 11. 14.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../components/Home.vue'
import Login from '../components/Login.vue'
import NotFound from '../components/NotFound.vue'

Vue.use(VueRouter);


const router = new VueRouter({
    mode: 'history', // hash back 제거
    routes: [
        {path: '/', component: Home},
        {path: '/login', component: Login},
        {path: '/*', component: NotFound},
    ]
});

export default router;