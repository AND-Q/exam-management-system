<template>
  <div class="log-list">
    <el-card>
      <div slot="header" class="clearfix">
        <span>监考日志列表</span>
      </div>
      <el-table :index="indexMethod" :data="einfos" style="width: 100%" stripe
                :header-cell-style="{ backgroundColor: 'aliceblue', color: '#666'}">

        <el-table-column prop="logid" label="日志编号" width="200"></el-table-column>
        <el-table-column prop="operationtype" label="操作类型"></el-table-column>
        <el-table-column prop="infoid" label="监考信息ID"></el-table-column>
        <el-table-column prop="teacherid" label="监考教师"></el-table-column>
        <!-- 格式化监考时间 -->
        <el-table-column prop="operationtime" label="操作时间" :formatter="formatDate"></el-table-column>
        <el-table-column prop="operator" label="操作人"></el-table-column>
      </el-table>
      <div style="margin: 10px 0;">
        <el-pagination @current-change="handleCurrentChange" :current-page="pageNum" :page-sizes="[100, 200, 300, 400]"
                       :page-size="pageSize" layout="total, prev, pager, next" :total="total">
        </el-pagination>
      </div>
    </el-card>
  </div>
</template>

<script>
import { format } from 'date-fns'; // 使用 date-fns 来格式化日期

export default {
  name: "EinfoView",
  data() {
    return {
      einfos: [], // 存储查询结果的监考日志数据
      pageNum: 1, // 当前页码
      pageSize: 12, // 每页显示的条数
      username: '', // 查询条件：监考科目
      total: 0, // 总条数
    };
  },
  created() {
    this.load(); // 页面加载时查询数据
  },
  methods: {
    // 格式化日期的方法
    formatDate(row, column, cellValue) {
      if (!cellValue) return '';
      const date = new Date(cellValue);
      return format(date, 'yyyy-MM-dd HH:mm:ss'); // 使用 date-fns 格式化日期
    },

    // 重置查询条件
    reset() {
      this.username = '';
      this.load(); // 重置后重新加载数据
    },

    // 查询并加载数据
    load(pageNum) {
      if (pageNum) this.pageNum = pageNum;
      this.$request.get('/examlog/selectByPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          username: this.username,
        }
      }).then(res => {
        console.log(res.data); // 输出响应数据
        this.einfos = res.data.records; // 存储返回的监考日志数据
        this.total = res.data.total; // 存储总条数
      });
    },

    // 当前页码改变时的处理
    handleCurrentChange(pageNum) {
      this.load(pageNum);
    },

    // 行索引方法
    indexMethod(index) {
      return index + 1;
    }
  },
};
</script>

<style scoped>
/* 可以在这里进行样式定制 */
</style>
