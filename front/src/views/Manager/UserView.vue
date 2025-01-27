<template>

  <div class="employee-list">
    <div style="margin-bottom: 10px;">
      <el-input placeholder="查询教师姓名" style="width: 200px;" v-model="username"></el-input>
<!--      <el-input placeholder="查询监考科目" style="width: 200px; margin: 0 5px;" v-model="name"></el-input>-->
      <el-button type="primary" @click="load(1)">查询</el-button>
      <el-button type="info" @click="reset">重置</el-button>
    </div>
    <el-card>
      <div slot="header" class="clearfix">
        <span>教师列表</span>
        <el-button style="float: right;" type="danger" size="mini" @click.native="delBatch">批量删除</el-button>
        <el-button style="float: right;" type="primary" size="mini" @click.native="handleAdd">添加教师</el-button>
      </div>
      <el-table :data="users" style="width: 100%" stripe :header-cell-style="{ backgroundColor: 'aliceblue' , color: '#666'}" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column prop="id" label="教师ID"></el-table-column>
        <el-table-column prop="username" label="教师姓名"></el-table-column>
<!--        <el-table-column prop="name" label="监考科目"></el-table-column>-->
        <el-table-column prop="phone" label="电话"></el-table-column>
        <el-table-column prop="email" label="邮箱"></el-table-column>
        <el-table-column prop="academy" label="学院"></el-table-column>
        <el-table-column label="头像">
          <template v-slot="scope">
            <div style="display: flex; align-items: center">
              <el-image style="width: 50px;height: 50px;border-radius: 50%;" v-if="scope.row.avatar" :src="scope.row.avatar" :preview-src-list="[scope.row.avatar]"></el-image>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="180" align="center">
          <template v-slot="scope">
            <el-button type="primary" size="mini" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button type="danger" size="mini" @click="handleDelete(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <div style="margin: 10px 0;">
        <el-pagination @current-change="handleCurrentChange" :current-page="pageNum" :page-sizes="[100, 200, 300, 400]" :page-size="pageSize" layout="total, prev, pager, next" :total="total">
        </el-pagination>
      </div>
    </el-card>

    <el-dialog title="添加教师" :visible.sync="userFormVisible" width="40%" center>
      <el-form :model="form" :rules="rules" label-width="80px" ref="formRef">
        <el-form-item label="教师姓名" prop="username">
          <el-input prefix-icon="el-icon-user" v-model="form.username" placeholder="教师姓名"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input show-password prefix-icon="el-icon-lock" v-model="form.password" placeholder="密码"></el-input>
        </el-form-item>
        <el-form-item label="监考科目" prop="name">
          <el-input prefix-icon="el-icon-user" v-model="form.name" placeholder="监考科目"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-input prefix-icon="el-icon-phone" v-model="form.phone" placeholder="电话"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input prefix-icon="el-icon-message" v-model="form.email" placeholder="邮箱"></el-input>
        </el-form-item>
        <el-form-item label="学院" prop="academy">
          <el-input type="textarea" v-model="form.academy" placeholder="学院"></el-input>
        </el-form-item>
        <el-form-item label="头像">
          <el-upload class="avatar-uploader" action="http://localhost:8081/file/upload" :headers="{token: user.token}" :file-list="[form.avatar]" list-type="picture" :on-success="handleAvatarSuccess">
            <el-button type="primary">上传头像</el-button>
          </el-upload>
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


// 常规方法请求数据需要导入axios
// import axios from 'axios';

// 通过封装好的request去请求
// import request from '@/utils/request';

export default {
  name: "UserView",
  data() {
    return {
      users: [],
      pageNum: 1, // 当前页码
      pageSize: 5,  // 每页显示的个数
      username: '',
      name: '',
      total: 0,
      userFormVisible: false,
      form: {},
      user: JSON.parse(localStorage.getItem("honey-user") || '{}'),
      rules: {
        // 必须跟上面输入账号的 prop="username" 一样，而且必须在自定的  user 对象里有 username

        username: [
          { required: true, message: '请输入教师姓名', trigger: 'blur' },
          { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '请输入监考科目', trigger: 'blur' },
          { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入监考科目', trigger: 'blur' },
          { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '请输入电话', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入邮箱', trigger: 'blur' }
        ],
        academy: [
          { required: true, message: '请输入学院', trigger: 'blur' }
        ],
      },
      ids: []
    }
  },
  created() {
    this.load()
  },

  mounted() {

  },

  methods: {
    // 批量删除
    delBatch() {
      if (!this.ids.length) {
        this.$message.warning('请选择数据')
        return
      }
      this.$confirm('你确认批量删除吗？', '确认删除', { type: "warning" }).then(response => {
        this.$request.delete('/user/delete/batch', {
          data: this.ids
        }).then(res => {
          if (res.code === '200') {
            this.$message.success('删除成功')
            // 更新表格数据
            this.load(1)
          } else {
            this.$message.error(res.msg)
          }
        })
      }).catch(() => { })
    },
    // 获取批量的id
    handleSelectionChange(rows) {
      // 把一个对象数组变成数字数组   map
      this.ids = rows.map(v => v.id)
    },
    //删除数据
    handleDelete(id) {
      this.$confirm('你确认删除吗？', '确认删除', { type: "warning" }).then(response => {
        this.$request.delete('/user/delete/' + id).then(res => {
          if (res.code === '200') {
            this.$message.success('删除成功')
            // 更新表格数据
            this.load(1)
          } else {
            this.$message.error(res.msg)
          }
        })
      }).catch(() => { })
    },
    // 编辑数据
    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row))
      // 打开弹窗
      this.userFormVisible = true
    },
    // 新增数据
    handleAdd() {
      // 新增数据的时候清空数据
      this.form = {}
      // 打开对话框
      this.userFormVisible = true
    },
    save() {
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          this.$request({
            url: this.form.id ? '/user/update' : '/user/add',
            method: this.form.id ? 'PUT' : 'POST',
            data: this.form
          }).then(res => {
            if (res.code === '200') {
              // 表示成功
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
      this.name = ''
      this.load()
    },
    load(pageNum) {
      if (pageNum) this.pageNum = pageNum

      this.$request.get('/user/selectByPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          username: this.username,
          name: this.name
        }
      }).then(res => {
        this.users = res.data.records
        this.total = res.data.total
      })
    },
    handleCurrentChange(pageNum) {
      this.load(pageNum)
    },
    handleAvatarSuccess(response, file, fileList) {
      // 把user的头像属性换成上传的图片的链接
      this.form.avatar = response.data
    },
  },
};
</script>

<style>
</style>
