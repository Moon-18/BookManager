<template>
  <div class="center">
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <span>增加读者</span>
        </div>
      </template>

      <el-form
        ref="ruleFormRef"
        :model="newUser"
        :rules="userRule"
        label-width="120px"
        class="demo-ruleForm"
        :size="formSize"
      >
        <el-form-item label="名称" prop="name">
          <el-row
            ><el-col :span="24"
              ><el-input v-model="newUser.name"></el-input></el-col
          ></el-row>
        </el-form-item>
        <el-form-item label="账号" prop="account">
          <el-row
            ><el-col :span="24"
              ><el-input v-model="newUser.account"></el-input></el-col
          ></el-row>
        </el-form-item>

        <el-form-item label="类型" prop="type" required>
          <span>普通读者</span>
        </el-form-item>

        <el-form-item label="密码" prop="password">
          <el-row
            ><el-col :span="24"
              ><el-input v-model="newUser.password"></el-input></el-col
          ></el-row>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm()">确认</el-button>
          <el-button @click="resetForm(ruleFormRef)">取消</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script lang="ts" setup>
import { reactive, ref } from "vue";
// More info see https://github.com/element-plus/element-plus/blob/dev/docs/examples/form/utils.ts
// import { resetForm, submitForm } from './utils'//自定义规则
import type { ElForm } from "element-plus";
import { register } from "@/network/userNetwork";
import { news } from "@/utils/cartoon";
import {userRule} from '@/utils/userRule'
const formSize = ref("");
const ruleFormRef = ref<InstanceType<typeof ElForm>>();
const newUser = reactive({
  name: "",
  account: "",
  type: "user",
  password: "",
});

//方法
const submitForm = () => {
  console.log("注册")
  console.log(newUser.account, newUser.password, newUser.type, newUser.name)
  register(newUser.account, newUser.password, newUser.type, newUser.name)
    .then((res) => {
      console.log(res);
      // 此处res 为返回的数据，将返回的数据进行处理显示
      if (res.success == true) {
        //结果正确
        news("添加成功", "success");
      } else {
        news("添加失败", "error");
      }
    })
    .catch((err) => {
    });
};

type FormInstance = InstanceType<typeof ElForm>;
const resetForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.resetFields();
};
</script>


<style>
.center {
  margin: 25px 160px;
}
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.box-card {
  width: 960px;
}
</style>
