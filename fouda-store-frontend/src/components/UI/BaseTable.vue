<template>
  <header>
    <slot name="title"></slot>
  </header>
  <div class="table-panel">
    <table>
      <thead>
        <tr>
          <slot name="header"></slot>
        </tr>
      </thead>
      <tbody>
        <slot name="body"></slot>
      </tbody>
    </table>
  </div>
</template>

<style scoped>
header {
  font-weight: 600;
}

.table-panel {
  overflow-y: auto;
  height: 450px;
}

thead :slotted(th) {
  position: sticky;
  top: 0px;
}

</style>
