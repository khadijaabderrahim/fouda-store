import { createApp } from "vue";
import App from "./App.vue";
import router from "./router.js";
import DeleteIcon from "vue-material-design-icons/Delete.vue";
import EditIcon from "vue-material-design-icons/Pencil.vue";
import ViewIcon from "vue-material-design-icons/Eye.vue";
import AddIcon from "vue-material-design-icons/Plus.vue";
import QuestionIcon from "vue-material-design-icons/Help.vue";
import BabyBottleIcon from "vue-material-design-icons/BabyBottleOutline.vue";
import store from "./store/index.js";
import BaseCard from "./components/UI/BaseCard";

const app = createApp(App);
app.use(router);
app.use(store);
app.component("delete-icon", DeleteIcon);
app.component("edit-icon", EditIcon);
app.component("view-icon", ViewIcon);
app.component("add-icon", AddIcon);
app.component("question-icon", QuestionIcon);
app.component("baby-icon", BabyBottleIcon);
app.component("base-card", BaseCard);

app.config.globalProperties.$filters = {
  formatDate(value) {
    return new Date(value).toLocaleString();
  },

  euroCurrency(value) {
    let arrondi = Math.round(value * 100) / 100 ;
    return arrondi + "€";
  },
};
app.mount("#app");
