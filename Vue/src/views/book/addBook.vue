<template>
  <div class="center">
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <span>增加图书</span>
        </div>
      </template>

      <el-form
        ref="ruleFormRef"
        :model="newBook"
        :rules="bookRule"
        label-width="120px"
        class="demo-ruleForm"
        :size="formSize"
      >
        <el-form-item label="名称" prop="name">
          <el-row
            ><el-col :span="12"
              ><el-input v-model="newBook.name"></el-input></el-col
          ></el-row>
        </el-form-item>
        <el-form-item label="作者" prop="writer">
          <el-row
            ><el-col :span="12"
              ><el-input v-model="newBook.writer"></el-input></el-col
          ></el-row>
        </el-form-item>

        <el-form-item label="种类" prop="kind">
          <el-checkbox-group v-model="newBook.kind" :min="0" :max="1">
            <el-checkbox label="科技" name="kind"></el-checkbox>
            <el-checkbox label="教育" name="kind"></el-checkbox>
            <el-checkbox label="文化" name="kind"></el-checkbox>
            <el-checkbox label="卫生" name="kind"></el-checkbox>
            <el-checkbox label="体育" name="kind"></el-checkbox>
            <el-checkbox label="其他" name="kind"></el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="总数" prop="sumNum">
          <el-row
            ><el-col :span="12"
              ><el-input v-model="newBook.sumNum"></el-input></el-col
          ></el-row>
        </el-form-item>
        <el-form-item label="简评">
          <el-input v-model="newBook.comment" type="textarea"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm(newBook)"
            >确认</el-button
          >
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
import { createBook } from "@/network/bookNetwork";
import { news } from "@/utils/cartoon";
import { bookRule } from "@/utils/bookRule";
import { useStore } from "vuex";
const store = useStore();
const formSize = ref("");
const ruleFormRef = ref<InstanceType<typeof ElForm>>();
const newBook = reactive({
  name: "",
  writer: "",
  kind: [],
  curNum: 0,
  sumNum: 0,
  comment: "",
});

//方法
const submitForm = (object) => {
  console.log(111)
  createBook(
    newBook.name,
    newBook.writer,
    newBook.kind[0],
    newBook.curNum,
    newBook.sumNum,
    newBook.comment
  ).then(
    (res) => {
      
      if (res.success == true) {
        news("添加成功", "success");
      } else {
        news("添加失败", "error");
      }
      console.log(res);
    },
    (err) => {
      news("添加失败", "error");
    }
  );
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
