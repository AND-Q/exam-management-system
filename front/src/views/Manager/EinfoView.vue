<template>
  <div class="employee-list">
    <div style="margin-bottom: 10px;">
      <el-input placeholder="查询监考科目" style="width: 200px;" v-model="username"></el-input>
      <el-button type="primary" @click="load(1)">查询</el-button>
      <el-button type="info" @click="reset">重置</el-button>
    </div>
    <el-card>
      <div slot="header" class="clearfix">
        <span>员工列表</span>
        <el-button style="float: right;" type="primary" size="mini" @click.native="handleAdd">添加信息</el-button>
      </div>
      <el-table :index="indexMethod" :data="einfos" style="width: 100%" stripe
                :header-cell-style="{ backgroundColor: 'aliceblue' , color: '#666'}"
                @selection-change="handleSelectionChange">
        <el-table-column type="index" label="考试编号" width="200"></el-table-column>
        <el-table-column prop="username" label="监考科目"></el-table-column>
        <el-table-column prop="teacher" label="监考教师"></el-table-column>
        <!-- 格式化监考时间 -->
        <el-table-column prop="time" label="监考时间" :formatter="formatDate"></el-table-column>
        <el-table-column prop="position" label="监考地点"></el-table-column>



        <el-table-column label="操作" align="center">
          <template v-slot="scope">
            <el-button type="primary" size="mini" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button type="danger" size="mini" @click="handleDelete(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div style="margin: 10px 0;">
        <el-pagination @current-change="handleCurrentChange" :current-page="pageNum" :page-sizes="[100, 200, 300, 400]"
                       :page-size="pageSize" layout="total, prev, pager, next" :total="total">
        </el-pagination>
      </div>
    </el-card>

    <el-dialog title="添加信息" :visible.sync="userFormVisible" width="40%" center>
      <el-form :model="form" :rules="rules" label-width="80px" ref="formRef">
        <el-form-item label="监考科目" prop="username">
          <el-input prefix-icon="el-icon-user" v-model="form.username" placeholder="监考科目"></el-input>
        </el-form-item>
        <el-form-item label="监考教师" prop="teacher">
          <el-input prefix-icon="el-icon-office-building" v-model="form.teacher" placeholder="监考教师"></el-input>
        </el-form-item>
        <el-form-item label="监考地点" prop="position">
          <el-input prefix-icon="el-icon-postcard" v-model="form.position" placeholder="监考地点"></el-input>
        </el-form-item>

        <!-- 监考时间选择 -->
        <el-form-item label="监考时间" prop="time">
          <el-date-picker prefix-icon="el-icon-postcard" v-model="form.time" type="datetime" placeholder="请选择监考时间"></el-date-picker>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="userFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { format } from 'date-fns'; // 使用 date-fns 来格式化日期

export default {
  name: "EinfoView",
  data() {
    return {
      einfos: [],
      pageNum: 1, // 当前页码
      pageSize: 5,  // 每页显示的个数
      username: '',
      total: 0,
      userFormVisible: false,
      form: {},
      rules: {
        username: [
          {required: true, message: '请输入监考科目', trigger: 'blur'},
          {min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur'}
        ],
        teacher: [
          {required: true, message: '请输入监考教师', trigger: 'blur'}
        ],
        position: [
          {required: true, message: '请输入监考地点', trigger: 'blur'}
        ],
        time: [
          {required: true, message: '请输入监考时间', trigger: 'blur'}
        ],
      },
    };
  },
  created() {
    this.load()
  },
  methods: {
    // 格式化日期的方法
    formatDate(row, column, cellValue) {
      if (!cellValue) return '';
      const date = new Date(cellValue);
      return format(date, 'yyyy-MM-dd HH:mm:ss'); // 使用 date-fns 格式化日期
    },

    // 删除数据
    handleDelete(id) {
      this.$confirm('你确认删除吗？', '确认删除', {type: "warning"}).then(response => {
        this.$request.delete('/einfo/delete/' + id).then(res => {
          if (res.code === '200') {
            this.$message.success('删除成功')
            this.load(1)
          } else {
            this.$message.error(res.msg)
          }
        })
      }).catch(() => {})
    },

    // 编辑数据
    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.userFormVisible = true
    },

    // 新增数据
    handleAdd() {
      this.form = {}
      this.userFormVisible = true
    },

    // 保存数据
    save() {
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          this.$request({
            url: this.form.id ? '/einfo/update' : '/einfo/add',
            method: this.form.id ? 'PUT' : 'POST',
            data: this.form
          }).then(res => {
            if (res.code === '200') {
              this.$message.success('保存成功')
              this.load(1)
              this.userFormVisible = false
            } else {
              this.$message.error(res.msg)
            }
          })
        }
      })
    },

    reset() {
      this.username = ''
      this.load()
    },

    load(pageNum) {
      if (pageNum) this.pageNum = pageNum
      this.$request.get('/einfo/selectByPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          username: this.username,
        }
      }).then(res => {
        this.einfos = res.data.records
        this.total = res.data.total
      })
    },

    handleCurrentChange(pageNum) {
      this.load(pageNum)
    },

    indexMethod(index) {
      return index + 1;
    }
  },
};
</script>

<style>
</style>
