// 导入封装好的Axios
import { request } from "./request"; //注意request.js的相对路径问题

// import { loginUser} from "@/utils/loginValidators";
import { registerUser } from "@/utils/registerValidators";
import Qs from "qs";
import { createInferTypeNode } from "typescript";

//1.用户相关网络请求
//1.1登录post
export function loginIn(account, password, type) {
  return request({
    url: "/user/login",
    headers: {
      "Content-Type": "application/json",
    },
    method: "post",
    data: {
      account: account,
      password: password,
    },
  });
}
//注销
export function logout() {
  return request({
    url: `/access/logout`, //es6语法,用反单引号拼接路径
    method: "get",
  });
}
//1.2注册post
export function register(account, password, type, name) {
  return request({
    url: "/user/register",
    headers: {
      "Content-Type": "application/json",
    },
    method: "post",
    data: {
      account: account,
      name: name,
      password: password,
      type: type,
    },
  });
}
//1.3读取全部用户信息
export function listAllUser(cur, size) {
  return request({
    url: `/user/getAll`, //es6语法,用反单引号拼接路径
    method: "get",
  });
}
