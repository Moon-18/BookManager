<template>
  <el-table :data="state.users" style="width: 100%">
    <el-table-column fixed prop="id" label="ID" width="180" />
    <el-table-column fixed prop="name" label="姓名" width="150" />
    <el-table-column prop="account" label="账号" width="240" />
    <el-table-column prop="type" label="类型" width="120" />
    <el-table-column prop="password" label="密码" width="120" />
    <!-- <el-table-column prop="history" label="已借阅(本)" width="120" />
    <el-table-column prop="current" label="正借阅(本)" width="120" /> -->
    <el-table-column fixed="right" label="Operations" width="300">
      <!-- 问题是不能正确的传递值 -->
      <!-- <SingleUserBorrowInfDialogVue userAccount="scope.row.account"/> -->
      <el-button type="text" size="small">编辑</el-button>
      <el-button type="text" size="small" style="color: rgb(245, 108, 108)"
        >删除</el-button
      >
    </el-table-column>
  </el-table>
</template>

<script lang="ts" setup>
import { reactive, ref } from "vue";
import { Calendar, Search } from "@element-plus/icons-vue";
import SingleUserBorrowInfDialogVue from "@/components/SingleUserBorrowInfDialog.vue";
import { listAllUser } from "@/network/userNetwork";
const state = reactive({
  users: [],
});
listAllUser().then((res) => {
  console.log(res.data);
  state.users = res.data.items;
});
</script>

<style>
.el-row {
  margin-bottom: 20px;
}
.el-row:last-child {
  margin-bottom: 0;
}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
</style>