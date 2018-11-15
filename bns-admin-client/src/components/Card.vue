<template>
    <div>
        Card
        <div v-if="loading">loading...</div>
        <div v-else>
            <div>cardId:{{cardId}}</div>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                cardId: 0,
                loading: false
            }
        },

        watch: {
            '$route': {
                handler: 'fetchData',
                immediate: true
            }
        },
        methods: {
            fetchData() {
                this.loading = true;
                setTimeout(() => {
                    this.cardId = this.$route.params.cardId;
                    this.loading = false;
                }, 500);
            }
        }

    }
</script>

<style scoped>

</style>