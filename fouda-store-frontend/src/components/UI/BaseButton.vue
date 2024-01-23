<template>
    <button :title="title">
        <slot></slot>
    </button>
</template>

<script>
export default {
    props:['title']
}
</script>



