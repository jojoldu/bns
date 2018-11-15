<template>
    <div>
        Board
        <div v-if="loading">loading...</div>
        <div v-else>
            <div>boardId: {{boardId}}</div>
            <router-link to="/board/{$boardId}/card/1">Card 1</router-link>
            <router-link to="/board/{$boardId}/card/2">Card 2</router-link>
            <hr/>
        </div>
        <router-view></router-view>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                boardId: 0,
                loading: false
            }
        },
        created() {
            this.fetchData();
        },
        methods: {
            fetchData() {
                this.loading = true;
                setTimeout(() => {
                    this.boardId = this.$route.params.boardId;
                    this.loading = false;
                }, 500);
            }
        }
    }
</script>

<style scoped>

</style>