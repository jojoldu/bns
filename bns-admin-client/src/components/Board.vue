<template>
    <div>
        Board
        <div>boardId: {{boardId}}</div>
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