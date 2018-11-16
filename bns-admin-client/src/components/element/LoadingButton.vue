<template>
    <b-button :variant="variant"
              :disabled="disabled || (isLoading && disableWhenLoading)"
              :block="isBlock"
              :size="size"
              @click="clicked">

        <span>{{loadingText}}</span>

        <icon v-if="isLoading" :scale="iconScale" :name="icon" :spin="iconSpinWhenLoading"/>

    </b-button>
</template>

<script>
    export default {
        name: 'loading-button',
        props: {
            text: {
                type: String,
                required: true
            },
            variant: {
                type: String,
                default: 'primary',
                validator: function (value) {
                    return ['primary', 'secondary', 'success', 'warning', 'danger', 'info', 'light', 'dark'].indexOf(value) !== -1
                }
            },
            size: {
                type: String,
                default: 'md',
                validator: function (value) {
                    return ['lg', 'md', 'sm'].indexOf(value) !== -1
                }
            },
            isBlock: {
                type: Boolean,
                default: false
            },
            loadingText: {
                type: String,
                default: ''
            },
            disabled: {
                type: Boolean,
                default: false
            },
            icon: {
                type: String,
                default: 'spinner'
            },
            iconScale: {
                type: String,
                default: '1.2'
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
