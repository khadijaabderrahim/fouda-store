<template>
  <button class="btn" :disabled="disabled" :class="{ 'btn-disabled': disabled }">
    <slot></slot>
  </button>
</template>

<script setup>
import { defineProps } from "vue";



const prop = defineProps(["disabled"]);
console.log(prop.disabled)
</script>

<style scoped>
.btn {
  font-size: 18px;
  padding: 5px 20px;
  margin: 8px 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
  background: #777988;
  color: #dbe4e5;
  cursor: pointer;
}

.btn:hover {
  background: var(--noir);
  color: #dbe4e5;
}


.btn-disabled {
  opacity: 0.6;
  cursor: not-allowed;
}
</style>
