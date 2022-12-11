import { createStore } from "vuex";

export default createStore({
  state: {
    User: {
      id: "",
      name: "",
      account: "",
      password: "",
      type: "",
      sex: "",
      maxNum: "",
      maxTime: "",
    },
  },
  mutations: {
    setUser(state, user) {
      state.User = user;
    },
    clearUser(state) {
      state.User = null;
    },
  },
  actions: {},
  getters: {
    getUser(state) {
      return state.User;
    },
    isUser(state) {
      return state.User.type == "user";
    },
  },
  modules: {},
});
