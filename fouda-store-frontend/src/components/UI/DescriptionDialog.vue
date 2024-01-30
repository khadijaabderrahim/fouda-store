<script setup>
import {defineProps} from "vue";

const props = defineProps(['content'])


</script>

<template>
  <dialog open>
    <p>{{ props.content }}</p>
  </dialog>

</template>


<style scoped>
dialog {
  position: relative;
  width: auto;
  height: auto;
  margin-top: -1px;
  border : none;
}
</style>