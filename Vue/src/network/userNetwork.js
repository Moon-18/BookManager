// �����װ�õ�Axios
import { request } from "./request"; //ע��request.js�����·������

// import { loginUser} from "@/utils/loginValidators";
import { registerUser } from "@/utils/registerValidators";
import Qs from "qs";
import { createInferTypeNode } from "typescript";

//1.�û������������
//1.1��¼post
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
//ע��
export function logout() {
  return request({
    url: `/access/logout`, //es6�﷨,�÷�������ƴ��·��
    method: "get",
  });
}
//1.2ע��post
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
//1.3��ȡȫ���û���Ϣ
export function listAllUser(cur, size) {
  return request({
    url: `/user/getAll`, //es6�﷨,�÷�������ƴ��·��
    method: "get",
  });
}
