<template>
  <div ref="myChart" :style="{ width: '450px', height: '400px' }"></div>
</template>

<script lang="ts">
import { ref } from "vue";
import * as echarts from "echarts";

export default {
  name: "Home",
  setup() {
    const myChart = ref<HTMLElement>();  //也可以用const myChart = ref<any>(); 

    console.log(myChart);
    const myCharts = ref<any>();
    setTimeout(() => {
      // 绘制图表
      myCharts.value = echarts.init(myChart.value!);
      myCharts.value.setOption({
        title: { text: "当日借阅总量" },
        tooltip: {},
        xAxis: {
          data: ["12-3", "12-4", "12-5", "12-6", "12-7", "12-8"],
        },
        yAxis: {},
        series: [
          {
            name: "书籍",
            type: "line",
            data: [5, 20, 36, 10, 10, 20],
          },
        ],
      });
    }, 10);

    return {
      myChart,
    };
  },
};
</script>
