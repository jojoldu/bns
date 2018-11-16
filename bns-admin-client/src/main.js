/**
 * Created by jojoldu@gmail.com on 2018. 11. 12.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

import Vue from 'vue'
import router from './router' // index.js는 상위 디렉토리 지정시 디폴트값
import App from './App.vue'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(router);
Vue.use(BootstrapVue);

new Vue({
    el: '#app',
    components: {App},
    template: '<App/>'
});
