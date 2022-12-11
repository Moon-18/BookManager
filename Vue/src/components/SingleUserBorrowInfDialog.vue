<template>
  <el-button type="text" @click="dialogTableVisible = true"
    >查看借阅信息</el-button
  >

  <el-dialog v-model="dialogTableVisible" title="借阅信息">
    <el-table :data="gridData.borrowInf">
      <el-table-column property="bookName" label="书名" width="150" />
      <el-table-column property="state" label="状态" />
      <el-table-column property="message" label="信息" />
      <el-table-column property="gmtCreate" label="创建日期" />
      <el-table-column property="gmtModified" label="修改日期" />
      <el-table-column property="deadline" label="截止日期" />
    </el-table>
  </el-dialog>
</template>

<script lang="ts" setup>
import { onMounted, reactive, ref } from 'vue'
import {getOne} from "@/network/borrowNetwork.js"

const dialogTableVisible = ref(false)
const props = defineProps(['userAccount'])

const gridData = reactive({
  borrowInf:[]//全部借阅信息
})
onMounted(()=>{
  console.log(props.userAccount)
  getOne(props.userAccount).then((res)=>{
    gridData.borrowInf=res.data.items
  })
})
</script>
<style scoped>
.el-button--text {
  margin-right: 15px;
}
.el-select {
  width: 300px;
}
.el-input {
  width: 300px;
}
.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>
