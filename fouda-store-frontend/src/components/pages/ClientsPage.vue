<template>
  <div v-if="this.clients">
    <div class="conatiner">
      <save-client
          v-if="formVisible"
          :client="clientToSave"
          :title="title"
          @close-dialog="closeSaveDialog"
      ></save-client>

      <base-alert
          v-if="deleteConfirm"
          @confirm-action="deleteClient"
          @cancel-action="cancelDelete">
        Are you sure you want to delete the client {{ clientToDeleteName }} ?
      </base-alert>
      <base-button class="btn" @click="add">Add client</base-button>
      <base-table>
        <template v-slot:title>
          <span>Clients</span>
        </template>
        <template v-slot:header>
          <th>Firstname</th>
          <th>Lastname</th>
          <th>Email</th>
          <th>Actions</th>
        </template>

        <template v-slot:body>
          <tr v-for="client in clients" :key="client.id">
            <td>{{ client.firstname }}</td>
            <td>{{ client.lastname }}</td>
            <td>{{ client.email }}</td>
            <td>
              <base-button
                  @click="viewClient(client.id)"
                  title="view client details"
                  class="icon-btn"
              >
                <view-icon></view-icon
                >
              </base-button>
              <base-button @click="edit(client)" title="Edit client" class="icon-btn"
              >
                <edit-icon></edit-icon
                >
              </base-button>
              <base-button @click="confirmDelete(client)" title="Delete client" class="icon-btn"
              >
                <delete-icon></delete-icon
                >
              </base-button>
            </td>
          </tr>
        </template>
      </base-table>
    </div>
  </div>
  <spinner-page v-else></spinner-page>
</template>

<script>
import BaseButton from "../UI/BaseButton.vue";
import SaveClient from "../clients/SaveClient.vue";
import BaseTable from "../UI/BaseTable.vue";
import BaseAlert from "../UI/BaseAlert.vue";
import SpinnerPage from "@/components/UI/SpinnerPage";

export default {
  components: {
    SpinnerPage,
    BaseButton,
    SaveClient,
    BaseTable,
    BaseAlert,
  },
  data() {
    return {
      formVisible: false,
      title: "",
      errors: [],
      dialogStyle: "dialog-init",
      disabledSave: false,
      deleteConfirm: false,
      clientToDeleteName: "",
      clientToDeleteId: 0,
      clientToSave: {},
      clients: null
    };
  },

  methods: {
    add() {
      this.title = "Add Client";
      this.formVisible = true;
    },

    closeSaveDialog() {
      this.formVisible = false;
      this.clientToSave = {};
    },

    edit(client) {
      this.title = "Edit Client";
      this.clientToSave = client;
      this.formVisible = true;
    },

    confirmDelete(client) {
      this.clientToDeleteName = client.firstname + " " + client.lastname;
      this.clientToDeleteId = client.id;
      this.deleteConfirm = true;
    },

    cancelDelete() {
      this.clientToDeleteId = 0;
      this.clientToDeleteName = "";
      this.deleteConfirm = false;
    },

    deleteClient() {
      this.$store
          .dispatch("clients/delete", this.clientToDeleteId)
          .then((this.deleteConfirm = false));
    },

    viewClient(id) {
      this.$router.push("/clients/" + id);
    },

    async loadClients() {
      console.log("is loading")
      await this.$store.dispatch("clients/loadClients");
      this.clients = await this.$store.getters["clients/findClients"];
      console.log(this.clients)
    }
  },
  mounted() {
    this.loadClients();
  }
};
</script>

<style scoped>
.all-line {
  width: 100%;
  margin: 0px auto;
  height: 70px;
}
</style>
