<template>
  <div class="card">
    <slot> </slot>
  </div>
</template>

<style scoped>
.card {
  border-radius: 5px;
  margin: 10px auto;
  border: 1px solid #dbe4e5;
  /* box-shadow: 0 10px 5px rgba(0, 0, 0, 0.2); */
  padding: 10px 5px;
  background: #fcffff;
  color: #34374e;
  font-size: 14px;
}

.card :slotted(label) {
  margin-right: 10px;
  margin-left: 10px;
}

.card :slotted(input) {
  margin-right: 10px;
  margin-left: 10px;
}

.card :slotted(span) {
  margin-right: 10px;
  margin-left: 10px;
}

.card :slotted(.action-panel) {
  margin-top: 30px;
}
</style>
