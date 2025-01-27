import Vue from 'vue'
import VueRouter from 'vue-router'
import LoginView from '../views/LoginView.vue'
import RegisterView from '../views/RegisterView.vue'

// 解决导航栏或者底部导航tabBar中的vue-router在3.0版本以上频繁点击菜单报错的问题
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push (location) {
  return originalPush.call(this, location).catch(error => error)
}

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Manager',
    component: () => import( '../views/Manager.vue'),
    redirect: '/home',  // 重定向
    children: [
      {path: 'home',name: 'HomeView',component: () => import('../views/Manager/HomeView.vue'), meta: { requiresAuth: true ,name: '系统首页'}},
      {path: 'user',name: 'UserView',component: () => import( '../views/Manager/UserView.vue'), meta: { requiresAuth: true ,name: '教师管理'}},
      {path: 'einfo',name: 'EinfoView',component: () => import( '../views/Manager/EinfoView.vue'), meta: { requiresAuth: true ,name: '监考信息管理'}},
      {path: 'person',name: 'PersonView',component: () => import( '../views/Manager/PersonView.vue'), meta: { requiresAuth: true ,name: '个人信息'}},
      {path: 'password',name: 'Password',component: () => import( '../views/Manager/Password.vue'), meta: { requiresAuth: true ,name: '修改密码'}},
      {path: 'examlog',name: 'ExamlogView',component: () => import( '../views/Manager/LogView.vue'), meta: { requiresAuth: true ,name: '考试日志管理'}},
    ],
    
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView,
    meta: { requiresAuth: false ,name: '登录'}, // 不需要 token
    
  },
  {
    path: '/register',
    name: 'register',
    component: RegisterView,
    meta: { requiresAuth: false ,name: '注册'}, // 不需要 token
    
  },
  {
    path: '*',
    name: '404',
    component: () => import( '../views/404.vue'),
    meta: { requiresAuth: true ,name: '无法访问'}, 
    
  },
  
]



const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

//路由守卫
// to 是到达的路由信息    from 是来源的路由信息   next 是帮助跳转路由的函数 console.log(to,from,next)
router.beforeEach((to, from, next) => {
  const token = JSON.parse(localStorage.getItem('honey-user') || '{}');
  const requiresAuth = to.matched.some(record => record.meta.requiresAuth);

  if (requiresAuth && !token.token) {
    // 如果路由需要认证，且用户未认证，则重定向到登录页面
    next('/login');
  } else {
    // 如果用户已认证或路由不需要认证，继续访问目标路由
    next();
  }
});



export default router
