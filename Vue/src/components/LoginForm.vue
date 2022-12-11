<template>
  <el-form
    ref="loginForm"
    :model="loginUser"
    :rules="rules"
    label-width="100px"
    class="loginForm sign-in-form"
  >
    <el-form-item label="账号" prop="account">
      <el-input
        v-model="loginUser.account"
        placeholder="Enter Acount..."
      ></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input
        v-model="loginUser.password"
        type="password"
        placeholder="Enter Password..."
      ></el-input>
    </el-form-item>

    <el-form-item>
      <el-button
        @click="handleLogin('loginForm')"
        type="primary"
        class="submit-btn"
        >提交</el-button
      >
    </el-form-item>

    <!-- 找回密码 -->
    <div class="tiparea">
      <p>忘记密码? <a>立即找回</a></p>
    </div>
  </el-form>
</template>

<script lang="ts">
import { ref, getCurrentInstance, computed } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router"; //路由
import { loginIn } from "@/network/userNetwork";
import { loginUser } from "@/utils/loginValidators";
import { news } from "@/utils/cartoon";
// import store from '@/store';
export default {
  props: {
    loginUser: {
      type: Object,
      required: true,
    },
    rules: {
      type: Object,
      required: true,
    },
  },
  setup(props: any) {
    // @ts-ignore
    const { ctx } = getCurrentInstance();
    const status = ref(true);
    const router = useRouter();
    const store = useStore();
    interface token {
      name: string;
      value: string;
    }

    // 触发登录方法
    const handleLogin = (formName: string) => {
      ctx.$refs[formName].validate((valid: boolean) => {
        if (valid) {
          //进行网络请求
          loginIn(loginUser.value.account, loginUser.value.password)
            .then((res) => {
              // console.log(res);
              if (res.success == true) {
                var u = res.data.user;
                store.commit("setUser", {
                  id: u.id,
                  name: u.name,
                  account: u.account,
                  password: u.password,
                  type: u.type,
                  sex: u.sex,
                  maxNum: u.maxNum,
                  maxTime: u.maxTime,
                });
                news("登录成功", "success"); //动画
                router.push({ name: "Index" });
                console.log(
                  "vuex中account取值" + store.getters.getUser.account
                );
              } else {
                news("登录失败", "error");
              }
            })
            .catch((err) => {
              console.log(err);
            });
        } else {
          news("请填写正确信息", "error");
          return false;
        }
      });
    };

    return {
      handleLogin,
      status,
      // 在 computed 函数中访问 state
      tokenName: computed(() => store.state.tokenName),
    };
  },
};
</script>

<style scoped>
/* form */
.loginForm {
  margin-top: 20px;
  background-color: #fff;
  padding: 20px 40px 20px 20px;
  border-radius: 5px;
  box-shadow: 0px 5px 10px #cccc;
}

.submit-btn {
  width: 100%;
}
.tiparea {
  text-align: right;
  font-size: 12px;
  color: #333;
}
.tiparea p a {
  color: #409eff;
}
</style>