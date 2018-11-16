<template>
    <b-button :variant="variant"
              :disabled="disabled || (isLoading && disableWhenLoading)"
              block
              @click="clicked">
        <icon v-if="isLoading === false" :scale="iconScale" :name="icon"/>
        <icon v-else :scale="iconScale" :name="loadingIcon" :spin="iconSpinWhenLoading"/>
    </b-button>
</template>

<script>
    export default {
        name: 'loading-button',
        props: {
            icon: {
                type: String,
                default: 'spinner'
            },
            loadingIcon: {
                type: String,
                default: ''
            },
            variant: {
                type: String,
                default: 'primary',
                validator: function (value) {
                    return ['primary', 'secondary', 'success', 'warning', 'danger', 'info', 'light', 'dark'].indexOf(value) !== -1
                }
            },
            iconScale: {
                type: String,
                default: '1.2'
            },
            disabled: {
                type: Boolean,
                default: false
            },
            iconSpinWhenLoading: {
                type: Boolean,
                default: true
            },
            isLoading: {
                type: Boolean,
                default: false
            },
            disableWhenLoading: {
                type: Boolean,
                default: true
            },
            onClick: {
                type: Function
            }
        },
        methods: {
            clicked() {
                this.$emit('on-click')
            }
        }

    }
</script>

<style scoped>
</style>
