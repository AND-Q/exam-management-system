<template>
  <div class="back"  >
    <div style=" display: flex; align-items: center; justify-content: center; background-color: white; width: 28%; border-radius: 10px;">
      <!-- 表单验证 :rules="rules" ||  ref="loginRef" 方便拿到这个表单 -->
      <el-form :model="user" :rules="rules" ref="loginRef">
        <div style="font-size: 20px;font-weight: bold; text-align: center; margin-bottom: 20px; cursor: default;">欢迎登录后台管理系统</div>
        <el-form-item prop="username">
          <el-input prefix-icon="el-icon-user" size="medium" placeholder="请输入账号：" v-model="user.username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input prefix-icon="el-icon-lock" size="medium" show-password placeholder="请输入密码：" v-model="user.password" ></el-input>
        </el-form-item>
        <el-form-item >
          <el-button type="primary" style="width: 100%;" @click="login">登 录</el-button>
        </el-form-item>
        <div style="cursor: default; display: flex;">
          <div style="flex: 1;">还没有账号？请 <span style="color: forestgreen; cursor: pointer; font-weight: bold;" @click="$router.push('/register')">注册</span></div>
          <!-- <div style="flex: 1; text-align: right;"><span style="color: forestgreen; font-weight: bold; cursor: pointer;">忘记密码</span></div> -->
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "LoginView",
  data() {
    return {
      user: {
        username: '',
        password: ''
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
      }
    }
  },
  created() {

  },
  methods: {
    login() {
      // 上面在el-form定义的ref在这里用
      this.$refs['loginRef'].validate((valid) => {
        if (valid) {
          // 验证通过
          this.$request.post('/login',this.user).then(res =>{
            if (res.code === '200') {
              // this.$router.push('/')
              
              // element 提供的 消息组件
              this.$message.success('登录成功')

              // 存储用户数据 自定义 honey-user key 并将用户数据转为JSON存储起来
              localStorage.setItem("honey-user",JSON.stringify(res.data))

              setTimeout(() => {
                this.$router.push('/'); // 使用 setTimeout 延迟跳转
              }, 100);
              
            } else {
              // 返回我定义的错误信息 如：账号或密码错误
              this.$message.error(res.msg)
              this.user.password = ''
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