<template>
  <div class="clients">
    <header>
      <slot name="title"></slot>
    </header>
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

</style>
