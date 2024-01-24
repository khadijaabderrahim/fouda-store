<template>
  <header>
    <nav class="navigation">
      <router-link to="/home">Home</router-link>
      <router-link to="/clients">Clients</router-link>
      <router-link to="/products">Products</router-link>
      <router-link to="/orders">Orders</router-link>
      <span>
        <span style="font-size: xx-small;">{{env}}</span> <span class="logo"> <baby-icon></baby-icon></span> {{ title }}
      </span>
      
    </nav>
  </header>

  <main>
    <router-view></router-view>
  </main>
</template>

<script>
import { ref } from "vue";
export default {
  name: "App",

  data() {
    return {
      title: ref(process.env.VUE_APP_TITLE),
      env: ref(process.env.VUE_APP_ENV),
    };
  },
};
</script>

<style>
* {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  width: auto;
  margin: 0;
  padding: 0;
  vertical-align: none;
  --red-color: red;
  --noir: #34374e;
  --vert: #17a195;
  --gris-fonce: #777988;
  --gris: #b2c4c8;
  --gris-clair: #dbe4e5;
}

header {
  overflow: hidden;
  width: 100%;
  position: fixed;
  top: 0;
}

nav a {
  padding: 5px 5px;
  margin: 5px;
  text-align: center;
  background: var(--noir);
  color: var(--gris-clair);
  text-decoration: none;
  border-radius: 5px;
}

nav a:hover,
nav a:active,
nav a.router-link-active {
  background: var(--gris-clair);
  color: var(--noir);
}

.navigation {
  width: auto;
  text-align: left;
  background-color: var(--noir);
  padding: 30px;
}

main {
  font-family: Arial, Helvetica, sans-serif;
  font-size: 16px;
  padding: 15px 10px;
  color: var(--noir);
  margin-top: 70px;
}

p {
  font-size: 16px;
}

h2 {
  font-size: 20px;
  font-weight: 600;
}

button {
  color: var(--gris-fonce);
  border: none;
  background: none;
  font-size: 5px;
  padding: 0;
}
button:hover {
  color: rgb(164, 69, 69);
  cursor: pointer;
}

.navigation span {
  color: var(--gris-clair);
  float: right;
  font-size: 20px;
}
.logo {
  float: left;
}

table {
  width: 100%;
  margin-top: 10px;
}

table,
th,
td {
  border-collapse: collapse;
  border-bottom: 1px solid var(--gris-clair);
  text-align: left;
  color: var(--noir);
}
th,
td {
  height: 15px;
  padding: 5px;
}

th {
  background: var(--gris-fonce);
  color: var(--gris-clair);
}

tr:nth-child(even) {
  background-color: var(--gris-clair);
}

.btn {
  font-size: 16px;
  padding: 5px;
  margin: 20px 10px;
  border-radius: 2px;
  background: #777988;
  color: #dbe4e5;
  cursor: pointer;
  /* color: #777988;
  background: #dbe4e5; */
}

.btn:hover {
  background: #777988;
  color: #dbe4e5;
}
.add-btn {
  float: right;
}

.disabled {
  opacity: 0.6;
  cursor: not-allowed;
}
</style>
