<template>

<!-- 后台主体界面 -->

  <el-container>
    <!-- 侧边栏 -->
    <!--  min-height: 100vh  占满整个屏幕-->
    <el-aside :width="asideWidth" style="min-height: 100vh; background-color: #001529">
      <div style="
          height: 60px;
          color: white;
          display: flex;
          align-items: center;
          justify-content: center;
        ">
        <img src="@/assets/touxiang.jpg" style="width: 40px; height: 40px" />
        <!-- 收缩文字消失 -->
        <span class="Logo-title" v-show="!isCollapse">监考管理系统</span>
      </div>

      <!-- 设置高亮,动态绑定路由 加':'+ "$route.path"-->
      <el-menu :collapse="isCollapse" :collapse-transition="false" router="" background-color="#001529" text-color="rgba(255,255,255,0.65)" 
      active-text-color="#fff" style="border: none" :default-active="$route.path">
        <el-menu-item index="/home">
          <i class="el-icon-s-home"></i>
          <span slot="title">系统首页</span>
        </el-menu-item>

        <!-- <el-menu-item index="/about">about</el-menu-item> -->

        <el-submenu index="info">
          <template slot="title">
            <i class="el-icon-menu"></i>
            <span>信息管理</span>
          </template>
<!--          <el-menu-item index="/user">教师管理</el-menu-item>-->
<!--          <el-menu-item index="/einfo">监考信息管理</el-menu-item>-->

          <!-- 只有管理员role=1才能看到教师管理 -->
          <el-menu-item v-if="user.role === 1" index="/user">教师管理</el-menu-item>


          <!-- 所有用户都能看到监考信息管理 -->
          <el-menu-item index="/einfo">监考信息管理</el-menu-item>


          <el-menu-item v-if="user.role === 1"index="/examlog">日志信息</el-menu-item>


        </el-submenu>
      </el-menu>
    </el-aside>

    <!-- 主体 -->
    <el-container>
      <!-- 头 -->
      <el-header>
        <i :class="collapseIcon" style="font-size: 26px" @click="handleCollapse"></i>

        <!-- 面包屑 -->
        <el-breadcrumb separator-class="el-icon-arrow-right" style="margin-left: 20px">
          <el-breadcrumb-item :to="{ path: '/' }">主页</el-breadcrumb-item>
          <el-breadcrumb-item :to="{ path: $route.path }">{{ $route.meta.name }}</el-breadcrumb-item>
        </el-breadcrumb>

        <div style="flex: 1; width: 0; display: flex; align-items: center; justify-content: flex-end">
          <!-- 设置居中 placement="bottom" -->
          <el-dropdown placement="bottom">
            <div style="display: flex; align-items: center; cursor: default;">
              <img :src="user.avatar" style="width: 40px; height: 40px; border-radius: 50%; margin: 0 5px;" />
              <span>{{user.name}}</span>
            </div>

            <!-- 下拉菜单 -->
            <!-- 因为在  el-dropdown-item 中没有 click 事件，所以加个  native 让事件生效-->
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="$router.push('/person')">个人信息</el-dropdown-item>
              <el-dropdown-item @click.native="$router.push('/password')">修改密码</el-dropdown-item>
              <!--native 允许在 Vue 组件的模板中直接监听原生 DOM 事件 -->
              <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </el-header>

      <!-- 页面更改区域：主体区域 -->
      <el-main>
        <router-view @update:user="updateInfo" />

      </el-main>

    </el-container>

  </el-container>
</template>

<script>
export default {
  name: "Manager",

  data() {
    return {
      user: JSON.parse(localStorage.getItem("honey-user") || '{}'),
      isCollapse: false, //默认不收缩
      asideWidth: "200px",
      collapseIcon: "el-icon-s-fold",
    };
  },
  mounted(){
    
  },
  methods: {
    // 获取子组件传过来的数据，更新当前页面的数据
    updateInfo(user) {
      // 在第一次更新完成之后父级的user和子组件的user绑定在一起，导致还没发生请求，父级已经更新数据
      // this.user = user
      
      // 让父级的对象和子级的对象毫无关系
      this.user = JSON.parse(JSON.stringify(user))
    },
    logout() {
      localStorage.removeItem('honey-user')   // 清楚当前token和用户数据
      this.$router.push('/login')
    },
    handleCollapse() {
      this.isCollapse = !this.isCollapse;
      this.asideWidth = this.isCollapse ? "64px" : "200px";
      this.collapseIcon = this.isCollapse ? "el-icon-s-unfold" : "el-icon-s-fold";
    },
  },
};
</script>

<style>
/* 设置二级菜单颜色 */
.el-menu--inline {
  background-color: #000c17 !important;
}
.el-menu--inline .el-menu-item {
  background-color: #000c17 !important;
  padding-left: 49px !important;
}
/* 设置滑动高亮 */
.el-menu-item:hover,
.el-submenu__title:hover {
  color: #fff !important;
}
.el-submenu__title:hover i {
  color: #fff !important;
}
.el-menu-item.is-active {
  background-color: #40a9ff !important;
  border-radius: 28px !important;
  width: calc(100%-8px);
  margin-left: 4px !important;
}
.el-menu-item.is-active i,
.el-menu-item.is-active .el-tooltip {
  margin-left: -4px;
}
.el-menu-item {
  height: 40px !important;
  line-height: 40px !important;
}
.el-submenu {
  height: 40px !important;
  line-height: 40px !important;
}
.el-aside {
  transition: width 0.3s;
  /* 添加阴影 */
  box-shadow: 2px 0 6px rgba(0, 21, 41, 0.35);
}
.Logo-title {
  margin-left: 5px;
  transition: all 0.3s;
}
.el-header {
  box-shadow: 2px 0 6px rgba(0, 21, 41, 0.35);
  display: flex;
  align-items: center;
}

/* 首页主体样式 */
header {
  padding: 20px;
  text-align: center;
  background-color: #f5f5f5;
  border-bottom: 1px solid #eaeaea;
}

h1 {
  font-size: 2.5em; /* 大号字体 */
  color: #333;
  margin: 0;
}
.like {
    cursor: pointer;
    font-size: 25px;
    display: inline-block;
  }
</style>
