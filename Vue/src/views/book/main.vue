<template>
  <el-row>
    <el-col :span="1"> </el-col>
    <el-col :span="6">
      <el-input
        placeholder="输入图书名、作者、关键字"
        :prefix-icon="Search"
        v-model="keyword"
      />
    </el-col>
    <el-col :span="2"></el-col>
    <el-col :span="6"
      ><el-col :span="3"
        ><el-button type="primary" @click="getOneBook"
          >模糊搜索</el-button
        ></el-col
      >
    </el-col>
  </el-row>
  <el-row> </el-row>

  <!-- 弹窗 -->
  <el-dialog
    v-model="dialogFormVisible"
    title="详情信息"
    :append-to-body="true"
  >
    <el-form :model="dialogForm" :rules="bookRule" ref="updateForm">
      <el-form-item label="id" prop="id">
        <el-row
          ><el-col :span="24"
            ><span>{{ dialogForm.id }}</span></el-col
          ></el-row
        >
      </el-form-item>
      <el-form-item label="名称" prop="name">
        <el-row
          ><el-col :span="12"
            ><el-input v-model="dialogForm.name"></el-input></el-col
        ></el-row>
      </el-form-item>
      <el-form-item label="作者" prop="writer">
        <el-row
          ><el-col :span="12"
            ><el-input v-model="dialogForm.writer"></el-input></el-col
        ></el-row>
      </el-form-item>

      <el-form-item label="种类" prop="kind">
        <el-checkbox-group v-model="dialogForm.kind" :min="0" :max="1">
          <el-checkbox label="科技" name="kind"></el-checkbox>
          <el-checkbox label="教育" name="kind"></el-checkbox>
          <el-checkbox label="文化" name="kind"></el-checkbox>
          <el-checkbox label="卫生" name="kind"></el-checkbox>
          <el-checkbox label="体育" name="kind"></el-checkbox>
          <el-checkbox label="其他" name="kind"></el-checkbox>
        </el-checkbox-group>
      </el-form-item>
      <el-form-item label="现有" prop="curNum">
        <el-row
          ><el-col :span="12"
            ><el-input v-model="dialogForm.curNum"></el-input></el-col
        ></el-row>
      </el-form-item>
      <el-form-item label="总数" prop="sumNum">
        <el-row
          ><el-col :span="12"
            ><el-input v-model="dialogForm.sumNum"></el-input></el-col
        ></el-row>
      </el-form-item>
      <el-form-item label="简评">
        <el-input v-model="dialogForm.comment" type="textarea"></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="submitForm('updateForm')"
          >确认</el-button
        >
      </span>
    </template>
  </el-dialog>

  <!-- 表单 -->
  <el-table :data="state.tableData" stripe style="width: 100%">
    <el-table-column prop="name" label="名称" width="150" />
    <el-table-column prop="writer" label="作者" width="220" />
    <el-table-column prop="createTime" sortable label="入馆时间" width="120" />
    <el-table-column prop="kind" label="种类" width="90" />
    <el-table-column prop="comment" label="简评" width="270" />
    <el-table-column prop="curNum" sortable label="现存" width="80" />
    <el-table-column prop="sumNum" sortable label="总数" width="100" />
    <el-table-column fixed="right" label="Operations" width="150">
      <template #default="scope">
        <el-button type="text" size="small" @click="handleClick(scope.row)"
          >编辑</el-button
        >
        <el-button
          type="text"
          size="small"
          style="color: rgb(245, 108, 108)"
          @click="deleteOne(scope.row)"
          >删除</el-button
        >
      </template>
    </el-table-column>
  </el-table>
  <el-pagination
    background
    layout="sizes,prev, pager, next,jumper,->,total"
    :total="sumPage"
    @current-change="handleCurrentChange"
  >
  </el-pagination>
</template>

<script lang="ts" setup>
import {
  onMounted,
  reactive,
  ref,
  onBeforeMount,
  getCurrentInstance,
  computed,
  watch,
} from "vue";
import type { ElForm } from "element-plus";
import { Calendar, Search } from "@element-plus/icons-vue";
import {
  listAllBook,
  updateBook,
  deleteBook,
  getAll,
  getOne,
} from "@/network/bookNetwork";
import { bookRule } from "@/utils/bookRule";
import { news } from "@/utils/cartoon";
import { useRouter } from "vue-router"; //路由
//数据部分
const sumPage = ref(20); //总页数
const state = reactive({
  //全部图书信息
  tableData: [],
  curPage: 1,
});
//模糊检索功能
const keyword = ref("");
const getOneBook = () => {
  if (keyword.value == "") {
    news("输入为空", "error");
    return;
  }
  news("搜索成功", "success");
  getOne(keyword.value).then((res) => {
    state.tableData = res.data.bookList;
  });
};
//切换页面函数
const handleCurrentChange = (currentPageNum: string) => {
  // 如果能够打印出来相应的页码，就证明获取成功了。
  console.log(currentPageNum);
  console.log("切换页面");
  listAllBook(currentPageNum, 10).then((res) => {
    state.tableData = res.data.items;
    console.log(res.data);
    // console.log(curPage.value)
  });
};
onMounted(() =>
  listAllBook(1, 10).then((res) => {
    state.tableData = res.data.items;
    console.log(res.data);
    // console.log(curPage.value)
  })
);

//弹窗处理
const formLabelWidth = "140px";
const dialogFormVisible = ref(false); //编辑 按钮对应的弹窗
const handleClick = (data) => {
  // console.log(data)
  //展示弹窗内容
  dialogFormVisible.value = true;
  dialogForm.id = data.id;
  dialogForm.name = data.name;
  dialogForm.writer = data.writer;
  dialogForm.kind.push(data.kind);
  dialogForm.curNum = data.curNum;
  dialogForm.sumNum = data.sumNum;
  dialogForm.comment = data.comment;
};
const dialogForm = reactive({
  id: "",
  name: "",
  writer: "",
  kind: [],
  curNum: "",
  sumNum: "",
  comment: "",
});
//提交修改内容
const router = useRouter();
const { ctx } = getCurrentInstance() as any;
const submitForm = (formName: string) => {
  console.log(dialogForm.comment);
  updateBook(
    dialogForm.id,
    dialogForm.name,
    dialogForm.writer,
    dialogForm.kind[0],
    dialogForm.curNum,
    dialogForm.sumNum,
    dialogForm.comment
  ).then(
    (res) => {
      if (res.success == true) {
        news("修改成功", "success");
        dialogFormVisible.value = false;
        location.reload();
      } else {
        news("修改失败", "error");
      }

      console.log(res);
    },
    (err) => {
      news("添加失败", "error");
    }
  );
};
//删除图书
const deleteOne = (data) => {
  deleteBook(data.id).then((res) => {
    news("删除成功", "success");
    location.reload();
  });
};
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