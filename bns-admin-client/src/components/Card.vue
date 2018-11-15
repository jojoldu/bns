<template>
    <div>
        Card
        <div>cardId:{{cardId}}</div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                cardId: 0
            }
        },
        created() {
            this.cardId = this.$route.params.cardId;
        },
        watch: {
            '$route'() {
                this.cardId = this.$route.params.cardId
            }

        }
    }
</script>

<style scoped>

</style>