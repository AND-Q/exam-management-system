<!--<template>-->
<!--  <div>-->
<!--    <div id="app">-->
<!--          <header>-->
<!--            <h1>{{ welcomeMessage }}</h1>-->
<!--          </header>-->
<!--        </div>-->

<!--        <div style="margin-top: 100px; cursor: default;">-->
<!--          <el-row :gutter="20">-->
<!--            <el-col :span="6">-->
<!--              <div>-->
<!--                <el-statistic group-separator="," :precision="2" :value="value2" :title="title"></el-statistic>-->
<!--              </div>-->
<!--            </el-col>-->
<!--            <el-col :span="6">-->
<!--              <div>-->
<!--                <el-statistic title="男女比">-->
<!--                  <template slot="formatter">-->
<!--                    456/2-->
<!--                  </template>-->
<!--                </el-statistic>-->
<!--              </div>-->
<!--            </el-col>-->
<!--            <el-col :span="6">-->
<!--              <div>-->
<!--                <el-statistic group-separator="," :precision="2" decimal-separator="." :value="value1" :title="title">-->
<!--                  <template slot="prefix">-->
<!--                    <i class="el-icon-s-flag" style="color: red"></i>-->
<!--                  </template>-->
<!--                  <template slot="suffix">-->
<!--                    <i class="el-icon-s-flag" style="color: blue"></i>-->
<!--                  </template>-->
<!--                </el-statistic>-->
<!--              </div>-->
<!--            </el-col>-->
<!--            <el-col :span="6">-->
<!--              <div>-->
<!--                <el-statistic :value="like ? 521 : 520" title="Feedback">-->
<!--                  <template slot="suffix">-->
<!--                    <span @click="like = !like" class="like">-->
<!--                      <i class="el-icon-star-on" style="color:red" v-show="!!like"></i>-->
<!--                      <i class="el-icon-star-off" v-show="!like"></i>-->
<!--                    </span>-->
<!--                  </template>-->
<!--                </el-statistic>-->
<!--              </div>-->
<!--            </el-col>-->
<!--          </el-row>-->
<!--        </div>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--export default {-->
<!--  name: "HomeView",-->
<!--data() {-->
<!--  return {-->
<!--    welcomeMessage: '欢迎进入监考管理系统系统',-->
<!--      like: true,-->
<!--        value1: 4154.564,-->
<!--        value2: 1314,-->
<!--        title: "增长人数",-->
<!--  }-->
<!--}-->
<!--}-->
<!--</script>-->

<!--<style>-->

<!--</style>-->
<template>
  <div>
    <!-- 页面整体容器 -->
    <div id="app" class="app-container">
      <!-- 标题部分 -->
      <header>
        <h1>{{ welcomeMessage }}</h1>
      </header>

      <!-- 背景区域 -->
      <div class="background-section">
        <div class="content-wrapper">
          <h2>监考管理系统</h2>
          <p>欢迎使用我们的监考管理系统，简化您的日常管理工作。</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "HomeView",
  data() {
    return {
      welcomeMessage: '欢迎进入监考管理系统',
    };
  },
};
</script>

<style scoped>
/* 引入 Google 字体 */
@import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&family=Poppins:wght@400;600;700&display=swap');

/* 整体容器样式 */
.app-container {
  font-family: 'Roboto', sans-serif; /* 默认使用 Roboto 字体 */
  text-align: center;
  margin: 0;
  padding: 0;
}

/* 标题样式 */
header {
  margin-top: 50px;
  color: #ffffff;
  font-size: 42px;
  font-weight: 700;
  text-shadow: 2px 2px 8px rgba(0, 0, 0, 0.5);
  letter-spacing: 1px;
}

/* 背景区域样式 */
.background-section {
  background-image: url('@/assets/background.jpg');
  background-size: cover;
  background-position: center;
  height: 600px;
  display: flex;
  justify-content: center;
  align-items: center;
  color: white;
  text-shadow: 2px 2px 10px rgba(0, 0, 0, 0.7);
  padding: 0 20px;
}

/* 内容容器 */
.content-wrapper {
  text-align: center;
  padding: 30px;
  background-color: rgba(0, 0, 0, 0.6); /* 半透明背景使文本更清晰 */
  border-radius: 12px;
  max-width: 600px; /* 限制内容的最大宽度 */
  margin: 0 auto; /* 居中对齐 */
}

/* 副标题样式 */
h2 {
  font-family: 'Poppins', sans-serif; /* 使用 Poppins 字体 */
  font-size: 32px;
  font-weight: 600;
  color: #f0f0f0;
  margin-bottom: 20px;
}

/* 描述文本样式 */
p {
  font-family: 'Poppins', sans-serif;
  font-size: 18px;
  color: #f7f7f7;
  line-height: 1.6;
  margin-top: 10px;
}

/* 增加响应式设计 */
@media (max-width: 768px) {
  header {
    font-size: 36px; /* 小屏幕下调整标题大小 */
  }

  .content-wrapper {
    padding: 20px;
  }

  h2 {
    font-size: 28px;
  }

  p {
    font-size: 16px;
  }
}
</style>
