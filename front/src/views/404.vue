<template>
  <div class="container">
    <div class="div-center">
      <!-- 内容 -->
      <h1>404 找不到页面 <router-link to="/">返回首页</router-link></h1>
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style>
.container {
  display: flex;
  justify-content: center; /* 水平居中 */
  align-items: center; /* 垂直居中 */
  height: 100vh; /* 容器高度为视口的100% */
}

.div-center {
  width: 50%; /* 或者具体的宽度值 */
  text-align: center
  /* 其他样式 */
}
</style>