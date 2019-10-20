import Vue from 'vue'
import Vuetify from 'vuetify'
import '@babel/polyfill'
import 'api/resource'
import router from 'router/router'
import App from 'pages/App.vue'
import store from 'store/store'
import {connect} from './util/ws'
import 'vuetify/dist/vuetify.min.css'
import * as Sentry from '@sentry/browser'
import * as Integrations from '@sentry/integrations'

Sentry.init({
  dsn: 'https://9cd2c6c6144e44dbb7b0e997e5d54813@sentry.io/1785022',
  integrations: [new Integrations.Vue({Vue, attachProps: true})],
});

Sentry.configureScope(scope =>
  scope.setUser({
    id: profile && profile.id,
    username: profile && profile.name
  })
)

if (profile) {
    connect()
}

Vue.use(Vuetify)

new Vue({
    el: '#app',
    store,
    router,
    render: a => a(App)
})
