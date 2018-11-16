<template>
    <div>
        Home
        <div v-if="loading">Loading...</div>
        <div v-else="">
            Api Result:{{response.data}}
        </div>
    </div>
</template>

<script>
    export default {
        name: "Home",
        data() {
            return {
                loading: false,
                response: {
                    code: '',
                    data: ''
                }
            }
        }
    }
</script>

<style scoped>

</style>