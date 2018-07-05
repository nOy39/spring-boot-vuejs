<template>
  <div class="row">
    <div class="col-2">
      <b-btn @click="showProgressBarExample = !showProgressBarExample" variant="info" class="m-1">
        Progress bar ({{showProgressBarExample?'visible':'hidden'}})
      </b-btn>
    </div>
    <div class="col-8">
      <b-alert variant="success"
               dismissible
               :show="showProgressBarExample"
               @dismissed="showProgressBarExample = false">
        <div class="container">
          <div>
            <h4>Вы ввели {{sum}} символов максимум 255</h4>
            <b-form-textarea id="textarea1"
                             v-model="text"
                             placeholder="Введите любой текст не длиннее 255 символов"
                             :rows="3"
                             :max-rows="6"
                             maxlength="255">
            </b-form-textarea>
            <hr>

            <div>
              <b-progress :value="sum" max="255" show-progress animated></b-progress>
            </div>
          </div>
        </div>
      </b-alert>

    </div>
  </div>
</template>

<script>
  export default {
    data () {
      return {
        text: '',
        currentNum:0,
        maxVal:255,
        showProgressBarExample: false
      }
    },
    methods: {
      showExample() {
        return this.showProgressBarExample = true;
      }
    },
    computed: {
      sum() {
        return this.currentNum = this.text.trim().length;
      },
    }
  }
</script>
<style>

</style>
