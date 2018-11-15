<template>
    <div>
        Board
        <div>boardId: {{boardId}}</div>
        <router-link to="/board/{$boardId}/card/1">Card 1</router-link>
        <router-link to="/board/{$boardId}/card/2">Card 2</router-link>
        <hr/>
        <router-view></router-view>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                boardId: 0
            }
        },
        created() {
            console.log(this.$route.params.boardId)
        }
    }
</script>

<style scoped>

</style>