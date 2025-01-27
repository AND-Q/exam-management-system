<template>
  <div class="back"  >
    <div style=" display: flex; align-items: center; justify-content: center; background-color: white; width: 28%; border-radius: 10px;">
      <!-- 表单验证 :rules="rules" ||  ref="loginRef" 方便拿到这个表单 -->
      <el-form :model="user" :rules="rules" ref="registerRef">
        <div style="font-size: 20px;font-weight: bold; text-align: center; margin-bottom: 20px; cursor: default;">欢迎注册后台管理系统</div>
        <el-form-item prop="username">
          <el-input prefix-icon="el-icon-user" size="medium" placeholder="请输入账号：" v-model="user.username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input prefix-icon="el-icon-lock" size="medium" show-password placeholder="请输入密码：" v-model="user.password"></el-input>
        </el-form-item>
        <el-form-item prop="confirmPass">
          <el-input prefix-icon="el-icon-lock" size="medium" show-password placeholder="请确认密码：" v-model="user.confirmPass"></el-input>
        </el-form-item>
        <el-form-item >
          <el-button type="primary" style="width: 100%;" @click="register">注 册</el-button>
        </el-form-item>
        <div style="cursor: default; display: flex;">
          <div style="flex: 1;">已有账号？请 <span style="color: forestgreen; cursor: pointer; font-weight: bold;" @click="$router.push('/login')">登录</span></div>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "RegisterView",
  data() {

    // 自定义密码校验
    const validatePassword = (rule, confirmPass, callback) => {
      if (confirmPass === '') {
        callback(new Error('请确认密码'))
      } else if (confirmPass !== this.user.password) {
        // this.user.password = ''
        this.user.confirmPass = ''
        callback(new Error('两次密码输入不一致'))
      } else {
        callback()
      }
    }
    return {
      user: {
        username: '',
        password: '',
        confirmPass: ''
      },
      rules: {
        // 必须跟上面输入账号的 prop="username" 一样，而且必须在自定的  user 对象里有 username
          username: [
            { required: true, message: '请输入账号', trigger: 'blur' },
            { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '请输入密码', trigger: 'blur' }
          ],
          confirmPass: [
            { validator: validatePassword, trigger: 'blur' }
          ],
      }
    }
  },
  created() {

  },
  methods: {
    register() {
      // 上面在el-form定义的ref在这里用
      this.$refs['registerRef'].validate((valid) => {
        if (valid) {
          // 验证通过
          this.$request.post('/register',this.user).then(res =>{
            if (res.code === '200') {
              this.$router.push('/login')
              // element 提供的 消息组件
              this.$message.success('注册成功')
            } else {
              // 返回我定义的错误信息 如：账号或密码错误
              this.$message.error(res.msg)
            }
          })
        }     
      })
    }
  }
}
</script>

<style>
.back {
  width: 100%;
  height: 100vh; 
  display: flex; 
  align-items: center; 
  justify-content: center;
  background-image: url('../assets/background.jpg'); 
  background-size: cover;
 
}

.el-form {
  margin: 15px;
  width: 80%;

}
</style>