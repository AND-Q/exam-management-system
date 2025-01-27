<template>
  <div style="display: flex; align-items: center; justify-content: center;">
    <el-card style="width: 50%;">
      <div style="text-align: center">
        <h2>个人信息</h2>
      </div>
      <div style="margin: 15px; text-align: center; ">
        <el-upload class="avatar-uploader" action="http://localhost:8081/file/upload" :headers="{token: user.token}" :show-file-list="false" :on-success="handleAvatarSuccess">
          <img v-if="user.avatar" :src="user.avatar" class="avatar" />
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </div>
      <el-form :model="user" :rules="rules" label-width="80px">
        <el-form-item label="教师姓名" prop="username">
          <el-input prefix-icon="el-icon-user" v-model="user.username" placeholder="教师姓名" disabled></el-input>
        </el-form-item>
<!--        <el-form-item label="监考科目" prop="name">-->
<!--          <el-input prefix-icon="el-icon-user" v-model="user.name" placeholder="监考科目"></el-input>-->
<!--        </el-form-item>-->
        <el-form-item label="电话" prop="phone">
          <el-input prefix-icon="el-icon-user" v-model="user.phone" placeholder="电话"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input prefix-icon="el-icon-user" v-model="user.email" placeholder="邮箱"></el-input>
        </el-form-item>
        <el-form-item label="学院" prop="academy">
          <el-input type="textarea" prefix-icon="el-icon-user" v-model="user.academy" placeholder="学院"></el-input>
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
  name: "PersonView",
  data() {
    return {
      user: JSON.parse(localStorage.getItem("honey-user") || '{}'),
      rules: {
        // 必须跟上面输入账号的 prop="username" 一样，而且必须在自定的  user 对象里有 username

        // name: [
        //   { required: true, message: '请输入监考科目', trigger: 'blur' },
        //   { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
        // ],
        phone: [
          { required: true, message: '请输入电话', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入邮箱', trigger: 'blur' }
        ],
        academy: [
          { required: true, message: '请输入学院', trigger: 'blur' }
        ],
      }
    }


  },

  created() {

  },
  mounted() {



  },
  methods: {

    update() {
      // 保存当前教师管理到数据库
      this.$request.put('/user/update', this.user).then(res => {
        if (res.code === '200') {
          // 成功更新
          this.$message.success('保存成功')

          // 更新缓存
          localStorage.setItem("honey-user", JSON.stringify(this.user))

          // 触发父级的数据更新
          this.$emit('update:user' , this.user)
        } else {
          // 更新失败
          this.$message.error(res.msg)
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
.el-upload {
  border-radius: 50% !important;
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
  border-radius: 50%;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
  border-radius: 50%;
}
</style>