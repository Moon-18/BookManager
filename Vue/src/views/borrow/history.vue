<template>
  <el-button @click="resetDateFilter">reset date filter</el-button>
  <el-button @click="clearFilter">reset all filters</el-button>
  <el-table
    ref="tableRef"
    row-key="gmtCreate"
    :data="state.tableData"
    style="width: 100%"
  >
    <el-table-column
      prop="gmtCreate"
      label="创建时间"
      sortable
      width="180"
      column-key="gmtCreate"
      :filters="state.gmtCreatefilters"
      :filter-method="filterHandler"
    />
    <el-table-column prop="gmtModified" label="修改时间" />
    <el-table-column prop="deadline" label="截止时间" />
    <el-table-column prop="bookName" label="书名" />
    <el-table-column prop="readerAccount" label="读者账号" />
    <el-table-column
      prop="state"
      label="状态"
      width="100"
      :filters="[
        { text: 'true', value: true },
        { text: 'false', value: false },
      ]"
      :filter-method="filterTag"
      filter-placement="bottom-end"
    >
      <template #default="scope">
        <el-tag
          :type="scope.row.state === true ? '' : 'error'"
          disable-transitions
          >{{ scope.row.state }}</el-tag
        >
      </template>
    </el-table-column>

    <el-table-column prop="message" label="借阅信息" />
  </el-table>
</template>

<script lang="ts" setup>
import { ref, onMounted, reactive } from "vue";
import { ElTable } from "element-plus";
import { getAll, getOne } from "@/network/borrowNetwork";
import type { TableColumnCtx } from "element-plus/es/components/table/src/table-column/defaults";

import { useStore } from "vuex";
const store = useStore();
interface Borrow {
  id: string;
  bookName: string;
  readerAccount: string;
  state: boolean;
  message: string;
  gmtCreate: string;
  gmtModified: string;
  deadline: string;
}

const tableRef = ref<InstanceType<typeof ElTable>>();

const resetDateFilter = () => {
  //清除全部筛选器
  tableRef.value!.clearFilter(["gmtCreate"]);
};
// TODO: improvement typing when refactor table
const clearFilter = () => {
  // eslint-disable-next-line @typescript-eslint/ban-ts-comment
  // @ts-expect-error
  tableRef.value!.clearFilter();
};
//下面两个方法是处理筛选的
const filterTag = (value: boolean, row: Borrow) => {
  return row.state === value;
};
const filterHandler = (
  value: string,
  row: Borrow,
  column: TableColumnCtx<Borrow>
) => {
  const property = column["property"];
  return row[property] === value;
};

const state = reactive({
  tableData: [],
  gmtCreatefilters: [],
});
onMounted(() => {
  if (store.getters.isUser) {
    getOne(store.getters.getUser.account).then((res) => {
      state.tableData = res.data.items;
    });
  } else {
    getAll().then((res) => {
      state.tableData = res.data.items;
    });
  } //筛选出不重复的时间来当做筛选标签
  var arr = [];
  for (var j = 0, len = state.tableData.length; j < len; j++) {
    arr.push({
      text: state.tableData[j].gmtCreate,
      value: state.tableData[j].gmtCreate,
    });
  }
  // 序列化去重
  const noRepeat = [...new Set(arr.map((item) => JSON.stringify(item)))];
  // 反序列化
  state.gmtCreatefilters = noRepeat.map((item) => JSON.parse(item));
});
</script>
