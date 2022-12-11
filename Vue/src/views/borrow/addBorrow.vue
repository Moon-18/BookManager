<template>
  <div class="center">
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <span>借阅图书</span>
          <el-button class="button" type="text" @click="submit()"
            >确认借阅</el-button
          >
        </div>
      </template>

      <el-form
        ref="ruleFormRef"
        :model="ruleForm"
        :rules="rules"
        label-width="120px"
        class="demo-ruleForm"
        :size="formSize"
      >
        <el-form-item label="读者账号" prop="userAccount">
          <el-row>
            <el-col :span="20"
              ><el-input v-model="ruleForm.userAccount"></el-input
            ></el-col>
          </el-row>
        </el-form-item>

        <el-form-item label="图书名称" prop="bookName">
          <el-row>
            <el-col :span="18"
              ><el-input v-model="ruleForm.bookName"></el-input
            ></el-col>
          </el-row>
        </el-form-item>


        <el-form-item label="借阅状态">
          <el-row>
            <el-col :span="24"><span>借出</span></el-col>
          </el-row>
        </el-form-item>

      </el-form>
    </el-card>
  </div>
</template>

<script lang="ts" setup>
import { onMounted, reactive, ref } from "vue";
// More info see https://github.com/element-plus/element-plus/blob/dev/docs/examples/form/utils.ts
// import { resetForm, submitForm } from './utils'//自定义规则
import type { ElForm } from "element-plus";
import { addBorrow } from "@/network/borrowNetwork";
import { news } from "@/utils/cartoon";
import { useStore } from "vuex";
const store = useStore();
const formSize = ref("");

const ruleFormRef = ref<InstanceType<typeof ElForm>>();
const ruleForm = reactive({
  bookName: "",
  userAccount: store.getters.getUser.account,
});

const rules = reactive({
  userAccount: [
    {
      type: "email",
      required: true,
      message: "Please input reader name",
      trigger: "blur",
    },
  ],
  bookName: [
    {
      required: true,
      message: "Please input book name",
      trigger: "blur",
    },
    {
      min: 1,
      max: 10,
      message: "Length should be 1 to 10",
      trigger: "blur",
    },
  ],
});
const submit = () => {
  addBorrow(ruleForm.userAccount, ruleForm.bookName).then((res) => {
    // console.log(res);
    if (res.success == true) {
      news(res.message, "success");
    } else {
      news(res.message, "error");
    }
  });
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
