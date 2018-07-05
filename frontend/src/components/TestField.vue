<template>
  <div class="row">
    <div class="col-2">
      <b-btn @click="showProgressBarExample = !showProgressBarExample" variant="info" class="m-1">
        Progress bar ({{showProgressBarExample?'visible':'hidden'}})
      </b-btn>
      <b-btn @click="buttonGroup = !buttonGroup" variant="info" class="m-1">
        Button group ({{buttonGroup?'visible':'hidden'}})
      </b-btn>
    </div>
    <div class="col-8">
      <!--ПрогресБар-->
      <b-alert variant="success"
               dismissible
               :show="showProgressBarExample"
               @dismissed="showProgressBarExample = false">
        <div class="container">
          <div>
            <h4>Вы ввели {{sum}} знака, максимум 255</h4>
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
      <!--Группа кнопок-->
      <b-alert :variant="variantStyle"
               dismissible
               :show="buttonGroup"
               @dismissed="buttonGroup = false">
        <div class="container">
          <div>
            <h4>Тест кнопок</h4>
            <hr>
            <div>
              <h6>Нажми чтобы чтобы изменить тип окна.</h6>

              <b-button-group :size="size">
                <b-button v-for="btn in buttons"
                          :pressed.sync="btn.state"
                          :variant="btn.variant"
                          :key="btn.variant"
                          @click="changeWindow(btn.caption)">
                  {{ btn.caption }}
                  <b-badge variant="light">{{btn.state}}</b-badge>
                </b-button>
              </b-button-group>
              <hr>
              <h6>Измени размер кнопок.</h6>
              <b-button-group
              :size="size">
                <b-button
                  variant="secondary"
                  @click="changeSize(-1)"><<
                </b-button>
                <b-button
                  variant="secondary"
                  @click="changeSize(1)">>>
                </b-button>
                <b-button
                  @click="currentSize=0">SMALL
                </b-button>
                <b-button
                  @click="currentSize=1">NORMAL</b-button>
                <b-button
                  @click="currentSize=2">LARGE</b-button>
              </b-button-group>
              <!--<p><strong>{{ btnStates }}</strong></p>-->
            </div>
          </div>
        </div>
      </b-alert>
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        text: '',
        currentNum: 0,
        maxVal: 255,
        showProgressBarExample: false,
        buttonGroup: false,
        myToggle: false,
        variantStyle: '',
        //Массив кнопок
        buttons: [
          {variant: 'outline-primary', caption: 'primary', state: false},
          {variant: 'outline-danger', caption: 'danger', state: false},
          {variant: 'outline-warning', caption: 'warning', state: false},
          {variant: 'outline-success', caption: 'success', state: false},
          {variant: 'outline-secondary', caption: 'secondary', state: false}
        ],
        //Переменные для изменения размеров кнопок
        sizeArr: ['sm', '', 'lg'],
        currentSize: 1,
        num: '',
      }
    },
    methods: {
      changeWindow(type) {
        return this.variantStyle = type
      },
      changeSize(num) {
        if (num > 0 && this.currentSize < 2) {
          this.currentSize++;
        } else if (num < 0 && this.currentSize > 0) {
          this.currentSize--;
        }
        return this.currentSize;
      }
    },
    computed: {
      sum() {
        return this.text.length;
      },

      size() {
        return this.sizeArr[this.currentSize];
      }
    }
  }
</script>
<style>

</style>
