<template>
  <el-form
    ref="ruleFormRef"
    :model="store.state.User"
    :rules="userRule"
    label-width="120px"
    class="demo-ruleForm"
  >
   
    <el-form-item label="名称" prop="name">
      <el-row
        ><el-col :span="24">{{ store.state.User.name }}</el-col></el-row
      >
    </el-form-item>
    <el-form-item label="账号" prop="account">
      <el-row
        ><el-col :span="24"
          ><el-input v-model="store.state.User.account"></el-input></el-col
      ></el-row>
    </el-form-item>

    <el-form-item label="类型" prop="type" required>
      <span>普通读者</span>
    </el-form-item>

    <el-form-item label="密码" prop="password">
      <el-row
        ><el-col :span="24"
          ><el-input v-model="store.state.User.password"></el-input></el-col
      ></el-row>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitChange()">修改信息</el-button>
      <el-button type="danger" @click="logout">退出登录</el-button>
    </el-form-item>
  </el-form>
</template>
<script lang='ts' setup>
import { userRule } from "@/utils/userRule";
import { reactive, computed, ref } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router"; //路由
const store = useStore();
const router = useRouter();
const User = reactive({
  name: "",
  account: "",
  type: "user",
  password: "",
  sex:0,
  max_num:2,
  max_time:"",
  gmt_create:"",
  gmt_modified:""
});

const logout=()=>{
store.commit("logout");
 router.push({ name: "Login" });
}
const submitChange=()=>{

}
</script>

<style>
/* 照片上传的相关样式 */
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>