<template>
  <p>馆藏图书分类</p>
  <div
    ref="myChart"
    id="main2"
    :style="{ width: '1000px', height: '580px' }"
  ></div>
</template>

<script lang="ts">
import { onMounted, onBeforeMount, watch, ref } from "vue";
import * as echarts from "echarts";
import { getCategory } from "@/network/bookNetwork";

export default {
  name: "Home",
  setup() {
    //总结,vue如何结合echarts

    onMounted(() => {
      getCategory().then((res) => {
        let myChart = echarts.init(document.getElementById("main2")); //捕获dom节点
        let data; //配置数据
        let options; //配置选项
        //获取数据
        data = res.data.category_num;
        options = {
          legend: {
            top: "bottom",
          },
          toolbox: {
            show: true,
            feature: {
              mark: { show: true },
              dataView: { show: true, readOnly: false },
              restore: { show: true },
              saveAsImage: { show: true },
            },
          },
          series: [
            {
              name: "Nightingale Chart",
              type: "pie",
              radius: [50, 250],
              center: ["50%", "50%"],
              roseType: "area",
              itemStyle: {
                borderRadius: 8,
              },
              data,
            },
          ],
        }; //表格
        console.log(myChart);
        console.log("init方法", options.series["data"]);
        console.log("赋值后");
        console.log("数据更新为", options.series["data"]);
        console.log(options);
        myChart.setOption(options, true); //将dom节点设置好配置
      });
    });
  },
};
</script>
