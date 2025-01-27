<template>
  <div style="display: flex; align-items: center; justify-content: center;">
    <el-card style="width: 50%;">
      <div style="text-align: center">
        <h2>修改密码</h2>
      </div>
      <el-form :model="user" ref="formRef" :rules="rules" label-width="80px" style="padding-right: 20px;">
        <el-form-item label="教师姓名" prop="username">
          <el-input v-model="user.username" placeholder="教师姓名" disabled></el-input>
        </el-form-item>
        <el-form-item label="原密码" prop="password">
          <el-input show-password v-model="user.password" placeholder="原始密码"></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="newPassword">
          <el-input show-password v-model="user.newPassword" placeholder="新密码"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="confirmPassword">
          <el-input show-password v-model="user.confirmPassword" placeholder="确认密码"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" style="width: 100%;" @click="update">保 存</el-button>
        </el-form-item>
      </el-form>

    </el-card>
  </div>
</template>

<script>
export default {

  name: 'Password',
  data() {

    // 校验器
    const validatePassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请确认密码'))
      } else if (value !== this.user.newPassword) {
        callback(new Error('两次密码输入错误'))
      } else {
        callback()
      }
    }

    return {
      user: JSON.parse(localStorage.getItem("honey-user") || '{}'),
      rules: {
        // 必须跟上面输入账号的 prop="username" 一样，而且必须在自定的  user 对象里有 username

        password: [
          { required: true, message: '请输入原始', trigger: 'blur' },
        ],
        newPassword: [
          { required: true, message: '请输入新密码', trigger: 'blur' }
        ],
        confirmPassword: [
          { validator: validatePassword, required: true, trigger: 'blur' }
        ],

      }
    }
  },


  methods: {

    update() {

      this.$refs.formRef.validate((valid) => {
        if (valid) {
          this.user.password = this.user.newPassword
          // 保存当前教师管理到数据库
          this.$request.put('/user/update', this.user).then(res => {
            if (res.code === '200') {
              // 成功更新
              this.$message.success('保存成功')
              setTimeout(() => {
                this.$router.push('/login') // 使用 setTimeout 延迟跳转
              }, 2000);
            } else {
              // 更新失败
              this.$message.error(res.msg)
            }
          })
        }
      })


    },
    handleAvatarSuccess(response, file, fileList) {
      // 把user的头像属性换成上传的图片的链接
      this.user.avatar = response.data
    },

  }
}
</script>

<style>
.el-form-item__label {
  font-weight: bold;
}
</style>