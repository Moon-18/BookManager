// 导入封装好的Axios
import { request } from "./request"; //注意request.js的相对路径问题

//3.借阅相关网络请求
//3.1增加借阅信息
export function addBorrow(userAccount, bookName) {
  return request({
    url: `/borrow/addBorrow/${userAccount}/${bookName}/${-1}`, //es6语法,用反单引号拼接路径
    method: "get",
  });
}
//3.2更新借阅信息,也就是还书
export function finishBorrow(userAccount, bookName, state, message) {
  return request({
    url: `/borrow/finishBorrow/${userAccount}/${bookName}/${state}/${message}`, //es6语法,用反单引号拼接路径
    method: "get",
  });
}
//3.3分页请求借阅信息
export function pageBorrow(current, limit) {
  return request({
    url: `/borrow/pageBorrow/${current}/${limit}`, //es6语法,用反单引号拼接路径
    method: "get",
  });
}

export function getAll() {
  return request({
    url: `/borrow/getAll`, //es6语法,用反单引号拼接路径
    method: "get",
  });
}

export function getOne(userAccount) {
  return request({
    url: `/borrow/getOne/${userAccount}`, //es6语法,用反单引号拼接路径
    method: "get",
  });
}
