<template>
  <dialog open>
    <div>
      <slot></slot>
    </div>
    <div class="btn-block">
      <base-button class="btn" @click="$emit('confirm-action')"
        >confirm</base-button
      >
      <base-button class="btn" @click="$emit('cancel-action')"
        >cancel</base-button
      >
    </div>
  </dialog>
</template>

<style scoped>
dialog {
  width: 20%;
  border-radius: 5px;
  margin: 50px auto;
  border: 1px #dbe4e5;
  box-shadow: 0 10px 10px rgba(0, 0, 0, 0.2);
  padding: 5px 5px;
  background: #fcffff;
  color: #34374e;
}

dialog div {
  font-size: 14px;
  margin-left: 10px;
  padding: 10px;
}

.btn-block {
  margin: 10px 0px;
}
</style>
