<template>
    <div class="login-wrapper">
        <b-container>

            <b-row class="text-center">
                <b-col cols="6" offset="3">
                    <b-img :src="login" width="800" height="547" fluid alt="Bitly And SNS"/>
                </b-col>
            </b-row>

            <br/>

            <b-row v-if="message">
                <b-col cols="6" offset="3">
                    <b-alert variant="danger" show>{{ message }}</b-alert>
                </b-col>
            </b-row>

            <!-- Login Button -->
            <b-row>
                <b-col cols="6" offset="3">
                    <loading-button
                            variant="danger"
                            :is-loading="isLoading"
                            text="Bitly계정 로그인"
                            loading-text="Bitly계정 로그인"
                            is-block
                            @on-click="login"></loading-button>

                    <p>Bitly 계정으로 로그인하시기 바랍니다.</p>
                </b-col>
            </b-row>

        </b-container>
    </div>
</template>

<script>
    import LoadingButton from './element/LoadingButton'
    import login from "../static/images/login.png"

    export default {
        name: 'login',
        components: {LoadingButton},
        data() {
            return {
                isLoading: false,
                message: '',
                login: login
            }
        },
        methods: {
            login() {
                this.isLoading = true;
                window.location.href = '/login';
            }
        }
    }
</script>

<style scoped>
    .login-wrapper {
        margin-top: 10rem;
    }

    p {
        font-size: 0.8rem;
        color: #333;
        font-weight: 600;
        line-height: 2rem;
    }

    h1 {
        color: #333;
        size: 20rem;
        font-weight: 500;
    }
</style>
