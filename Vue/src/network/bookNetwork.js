// 导入封装好的Axios
import { request } from "./request"; //注意request.js的相对路径问题

// import { loginUser} from "@/utils/loginValidators";
import { registerUser } from "@/utils/registerValidators";
import Qs from "qs";
import { createInferTypeNode } from "typescript";

//2.书籍相关网络请求
//2.1分页列出全部书籍
export function listAllBook(cur, size) {
  return request({
    url: `/book/pageBook/${cur}/${size}`, //es6语法,用反单引号拼接路径
    method: "get",
    header: {
      // "content-type": "application/x-www-form-urlencoded",
      // satoken: store.getters.getTokenValue,
      satoken: "e0a58eda-10ea-4f1b-ab07-a51c85ba5056",
    },
  });
}
export function getAll() {
  return request({
    url: `/book/getAll`, //es6语法,用反单引号拼接路径
    method: "get",
    headers: {
      "content-type": "application/x-www-form-urlencoded",
      // satoken: store.getters.getTokenValue,
      satoken: "e0a58eda-10ea-4f1b-ab07-a51c85ba5056",
    },
  });
}
//2.2增加图书
export function createBook(name, writer, kind, allow, sumNum, comment) {
  return request({
    url: "/book/addBook",
    method: "post",
    data: {
      name: name,
      writer: writer,
      kind: kind,
      allow: allow,
      sumNum: sumNum,
      curNum: sumNum,
      comment: comment,
    },
  });
}
//2.3更新图书
export function updateBook(id, name, writer, kind, allow, sumNum, comment) {
  return request({
    url: "/book/updateBook",
    headers: {
      "Content-Type": "application/json",
    },
    method: "post",
    data: {
      id: id,
      name: name,
      writer: writer,
      kind: kind,
      allow: allow,
      sumNum: sumNum,
      curNum: sumNum,
      comment: comment,
    },
  });
}
//2.4删除图书
export function deleteBook(id) {
  return request({
    url: `/book/deleteBook/${id}`, //es6语法,用反单引号拼接路径
    method: "delete",
  });
}
export function getCategory() {
  return request({
    url: `/book/getCategory`, //es6语法,用反单引号拼接路径
    method: "get",
  });
}
export function getOne(keyword) {
  return request({
    url: `/book/getOne/${keyword}`, //es6语法,用反单引号拼接路径
    method: "get",
  });
}
