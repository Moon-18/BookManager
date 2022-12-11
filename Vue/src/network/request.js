import axios from "axios";

import { useStore } from "vuex";
const store = useStore();
export function request(config) {
  // 1.鍒涘缓axios鐨勫疄渚�
  const instance = axios.create({
    // 璁剧疆鍩虹鐨剈rl閰嶇疆椤癸紝杩欐牱鎺ュ彛澶勭殑url鍓嶉潰灏变笉鐢ㄥ啓url:'http://127.0.0.1:8000/api/home'锛岀洿鎺ュ啓鎴� url:'/api/home', 灏卞彲浠ヤ簡
    // baseURL: 'http://localhost:8081',
    baseURL: "http://localhost:8222", //璁块棶鍚庣缁熶竴鐨勭綉鍏�,缃戝叧鍐嶈嚜琛岃繘琛屽垎閰�
    //璁剧疆璇锋眰瓒呮椂鏃堕棿
    timeout: 5000,
    // headers: {
    //   satoken: store.getters.getTokenValue,
    // },
  });

  // 2.axios鐨勬嫤鎴櫒锛岀敤涓嶅埌鐨勫彲浠ュ拷鐣ヨ繖鑺�
  // 2.1.璇锋眰鎷︽埅鐨勪綔鐢�
  instance.interceptors.request.use(
    (config) => {
      console.log(config);
      return config;
    },
    (err) => {
      console.log("过滤器拦截err: " + err);
    }
  );

  // 2.2.鍝嶅簲鎷︽埅
  instance.interceptors.response.use(
    (res) => {
      console.log(res);
      return res.data;
    },
    (err) => {
      console.log("拦截器err: ");
      console.log(err);
      return err; //涔嬪墠鏄湭杩斿洖閿欒鏁版嵁,鐜板湪杩斿洖浜�,浣垮緱绋嬪簭涔熻兘缁х画鎵ц
    }
  );

  // 3.鍙戦€佺湡姝ｇ殑缃戠粶璇锋眰
  return instance(config);
}
