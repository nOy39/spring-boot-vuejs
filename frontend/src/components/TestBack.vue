<template>
<div class="row">
  <div class="col-10">
    <h5>Полигон</h5>
    <hr>
    <b-card no-body>
      <b-tabs card>
        <b-tab title="Входящие сообщения" active>
          <br>
          <div class="row">

            <b-card v-for="msg in messages"
                    :header="msg.title"
                    header-text-variant="white"
                    header-tag="header"
                    header-bg-variant="dark"
                    :footer="msg.created"
                    footer-text-variant="white"
                    footer-tag="footer"
                    footer-bg-variant="success"
                    footer-border-variant="dark"
                    :title="msg.title"
                    style="max-width: 20rem;"
            >
              <p class="card-text">{{msg.text}}</p>
              <br>
              <b-button
                size="sm"
                :variant="msg.read?'success':'primary'"
                :disabled="msg.read"
                @click="switchMessageStatus(msg)"
              >{{ msg.read?'Прочитанно':'Не прочитанно' }}</b-button>
            </b-card>
          </div>
        </b-tab>
        <b-tab title="Tab 2">
          Tab Contents 2
        </b-tab>
      </b-tabs>
    </b-card>
  </div>
</div>
</template>

<script>
  // import axios from 'axios'
  import {AXIOS} from './http-common'

  export default {
    data() {
      return {
          id: '',
          title:'',
          text: '',
          created: '',
          read: 'false',
        messages: [],
        response: [],
        errors: []
      }
    },

    created: function (){
      this.fetchMessages();
    },

    methods: {
      fetchMessages() {
        AXIOS.get(`/inbox`).then(response => {
            this.messages = response.data;
            console.log(response.data);
          })
            .catch(e => {
              this.errors.push(e)
            })
      },
      switchMessageStatus(msg) {
        AXIOS.post(`/inbox/`+msg.id)
          .then(response => {
            this.response = response.data;
            this.msg.id = response.data;
            console.log(response.data);
          })
        return msg.read=true;
      }
    }
  }
</script>

<style>

</style>
