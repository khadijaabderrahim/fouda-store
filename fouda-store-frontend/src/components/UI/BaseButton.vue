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
  font-size: 14px;
  padding: 5px 5px;
  border: 1px solid #ccc;
  border-radius: 4px;
  background: #777988;
  color: #dbe4e5;
  cursor: pointer;
  width: auto;
  height: auto;
}

.btn:hover {
  background: var(--noir);
  color: #dbe4e5;
}


.btn-disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

.icon-btn {
  border : none;
  color: var(--gris-fonce);
  background-color: transparent;
  margin: 0px;
  padding: 0px;
}
</style>
