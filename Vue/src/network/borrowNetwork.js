// �����װ�õ�Axios
import { request } from "./request"; //ע��request.js�����·������

//3.���������������
//3.1���ӽ�����Ϣ
export function addBorrow(userAccount, bookName) {
  return request({
    url: `/borrow/addBorrow/${userAccount}/${bookName}/${-1}`, //es6�﷨,�÷�������ƴ��·��
    method: "get",
  });
}
//3.2���½�����Ϣ,Ҳ���ǻ���
export function finishBorrow(userAccount, bookName, state, message) {
  return request({
    url: `/borrow/finishBorrow/${userAccount}/${bookName}/${state}/${message}`, //es6�﷨,�÷�������ƴ��·��
    method: "get",
  });
}
//3.3��ҳ���������Ϣ
export function pageBorrow(current, limit) {
  return request({
    url: `/borrow/pageBorrow/${current}/${limit}`, //es6�﷨,�÷�������ƴ��·��
    method: "get",
  });
}

export function getAll() {
  return request({
    url: `/borrow/getAll`, //es6�﷨,�÷�������ƴ��·��
    method: "get",
  });
}

export function getOne(userAccount) {
  return request({
    url: `/borrow/getOne/${userAccount}`, //es6�﷨,�÷�������ƴ��·��
    method: "get",
  });
}
