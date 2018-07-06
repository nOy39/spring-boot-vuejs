<template>
  <div class="user">
    <h1>Create User</h1>

    <h3>Just some database interaction...</h3>

    <input type="text" v-model="user.firstName" placeholder="first name">
    <input type="text" v-model="user.lastName" placeholder="last name">

    <button @click="createUser()">Create User</button>

    <div v-if="showResponse"><h6>User created with Id: {{ response }}</h6></div>

    <template>
      <b-table striped hover :items="users" :fields="fields">
        <span slot="html" slot-scope="data" v-html="data.value">
      </span>
      </b-table>
    </template>
  </div>
</template>

<script>
  // import axios from 'axios'
  import {AXIOS} from './http-common'

  export default {
    name: 'user',

    data () {
      return {
        response: [],
        errors: [],
        user: {
          lastName: '',
          firstName: '',
          id: 0
        },
        showResponse: false,
        retrievedUser: {},
        showRetrievedUser: false,
        users: [],
        fields: ['id',
          'firstName',
          'lastName',
          'manage']
      }
    },
    created: function () {
      this.fetchUsers();
    },

    methods: {
      // Fetches posts when the component is created.
      createUser () {
        var params = new URLSearchParams()
        params.append('firstName', this.user.firstName)
        params.append('lastName', this.user.lastName)

        AXIOS.post(`/user`, params)
          .then(response => {
            // JSON responses are automatically parsed.
            this.response = response.data;
            this.user.id = response.data;
            console.log(response.data);
            this.showResponse = true;
            this.fetchUsers()
          })
          .catch(e => {
            this.errors.push(e)
          })
      },
      fetchUsers() {
        AXIOS.get(`/user`).then(response => {
          this.users = response.data;
          console.log(response.data)
        })
          .catch(e => {
            this.errors.push(e)
          })
      },
      deleteUser() {

      }
    }
  }

</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h1, h2 {
    font-weight: normal;
  }

  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    display: inline-block;
    margin: 0 10px;
  }

  a {
    color: #42b983;
  }
</style>
