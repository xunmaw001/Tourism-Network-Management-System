import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
    // 解决多次点击左侧菜单报错问题
    const VueRouterPush = VueRouter.prototype.push
    VueRouter.prototype.push = function push (to) {
    return VueRouterPush.call(this, to).catch(err => err)
    }
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
import beifen from '@/views/modules/databaseBackup/beifen'
import huanyuan from '@/views/modules/databaseBackup/huanyuan'

     import users from '@/views/modules/users/list'
    import dictionary from '@/views/modules/dictionary/list'
    import gonggao from '@/views/modules/gonggao/list'
    import jingdian from '@/views/modules/jingdian/list'
    import jingdianCollection from '@/views/modules/jingdianCollection/list'
    import jingdianCommentback from '@/views/modules/jingdianCommentback/list'
    import jingdianOrder from '@/views/modules/jingdianOrder/list'
    import lvyouluxian from '@/views/modules/lvyouluxian/list'
    import lvyouluxianCollection from '@/views/modules/lvyouluxianCollection/list'
    import lvyouluxianCommentback from '@/views/modules/lvyouluxianCommentback/list'
    import lvyouluxianOrder from '@/views/modules/lvyouluxianOrder/list'
    import shangjia from '@/views/modules/shangjia/list'
    import yonghu from '@/views/modules/yonghu/list'
    import config from '@/views/modules/config/list'
    import dictionaryGonggao from '@/views/modules/dictionaryGonggao/list'
    import dictionaryJingdian from '@/views/modules/dictionaryJingdian/list'
    import dictionaryJingdianCollection from '@/views/modules/dictionaryJingdianCollection/list'
    import dictionaryJingdianOrder from '@/views/modules/dictionaryJingdianOrder/list'
    import dictionaryJingdianOrderPayment from '@/views/modules/dictionaryJingdianOrderPayment/list'
    import dictionaryLvyouluxian from '@/views/modules/dictionaryLvyouluxian/list'
    import dictionaryLvyouluxianCollection from '@/views/modules/dictionaryLvyouluxianCollection/list'
    import dictionaryLvyouluxianOrder from '@/views/modules/dictionaryLvyouluxianOrder/list'
    import dictionaryLvyouluxianOrderPayment from '@/views/modules/dictionaryLvyouluxianOrderPayment/list'
    import dictionarySex from '@/views/modules/dictionarySex/list'
    import dictionaryShangjiaXingji from '@/views/modules/dictionaryShangjiaXingji/list'
    import dictionaryShangxia from '@/views/modules/dictionaryShangxia/list'





//2.配置路由   注意：名字
const routes = [{
    path: '/index',
    name: '首页',
    component: Index,
    children: [{
      // 这里不设置值，是把main作为默认页面
      path: '/',
      name: '首页',
      component: Home,
      meta: {icon:'', title:'center'}
    }, {
      path: '/updatePassword',
      name: '修改密码',
      component: UpdatePassword,
      meta: {icon:'', title:'updatePassword'}
    }, {
      path: '/pay',
      name: '支付',
      component: pay,
      meta: {icon:'', title:'pay'}
    }, {
      path: '/center',
      name: '个人信息',
      component: center,
      meta: {icon:'', title:'center'}
    }, {
        path: '/huanyuan',
        name: '数据还原',
        component: huanyuan
    }, {
        path: '/beifen',
        name: '数据备份',
        component: beifen
    }, {
        path: '/users',
        name: '管理信息',
        component: users
    }
    ,{
        path: '/dictionaryGonggao',
        name: '公告类型',
        component: dictionaryGonggao
    }
    ,{
        path: '/dictionaryJingdian',
        name: '景点类型',
        component: dictionaryJingdian
    }
    ,{
        path: '/dictionaryJingdianCollection',
        name: '收藏表类型',
        component: dictionaryJingdianCollection
    }
    ,{
        path: '/dictionaryJingdianOrder',
        name: '订单类型',
        component: dictionaryJingdianOrder
    }
    ,{
        path: '/dictionaryJingdianOrderPayment',
        name: '订单支付类型',
        component: dictionaryJingdianOrderPayment
    }
    ,{
        path: '/dictionaryLvyouluxian',
        name: '旅游路线类型',
        component: dictionaryLvyouluxian
    }
    ,{
        path: '/dictionaryLvyouluxianCollection',
        name: '收藏表类型',
        component: dictionaryLvyouluxianCollection
    }
    ,{
        path: '/dictionaryLvyouluxianOrder',
        name: '订单类型',
        component: dictionaryLvyouluxianOrder
    }
    ,{
        path: '/dictionaryLvyouluxianOrderPayment',
        name: '支付类型',
        component: dictionaryLvyouluxianOrderPayment
    }
    ,{
        path: '/dictionarySex',
        name: '性别类型',
        component: dictionarySex
    }
    ,{
        path: '/dictionaryShangjiaXingji',
        name: '旅游公司信用类型',
        component: dictionaryShangjiaXingji
    }
    ,{
        path: '/dictionaryShangxia',
        name: '上下架',
        component: dictionaryShangxia
    }
    ,{
        path: '/config',
        name: '轮播图',
        component: config
    }


    ,{
        path: '/dictionary',
        name: '字典',
        component: dictionary
      }
    ,{
        path: '/gonggao',
        name: '公告',
        component: gonggao
      }
    ,{
        path: '/jingdian',
        name: '景点',
        component: jingdian
      }
    ,{
        path: '/jingdianCollection',
        name: '景点收藏',
        component: jingdianCollection
      }
    ,{
        path: '/jingdianCommentback',
        name: '景点评价',
        component: jingdianCommentback
      }
    ,{
        path: '/jingdianOrder',
        name: '景点门票购买',
        component: jingdianOrder
      }
    ,{
        path: '/lvyouluxian',
        name: '旅游路线',
        component: lvyouluxian
      }
    ,{
        path: '/lvyouluxianCollection',
        name: '旅游路线收藏',
        component: lvyouluxianCollection
      }
    ,{
        path: '/lvyouluxianCommentback',
        name: '旅游路线评价',
        component: lvyouluxianCommentback
      }
    ,{
        path: '/lvyouluxianOrder',
        name: '旅游路线订单',
        component: lvyouluxianOrder
      }
    ,{
        path: '/shangjia',
        name: '旅游公司',
        component: shangjia
      }
    ,{
        path: '/yonghu',
        name: '用户',
        component: yonghu
      }


    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
    meta: {icon:'', title:'login'}
  },
  {
    path: '/register',
    name: 'register',
    component: register,
    meta: {icon:'', title:'register'}
  },
  {
    path: '/',
    name: '首页',
    redirect: '/index'
  }, /*默认跳转路由*/
  {
    path: '*',
    component: NotFound
  }
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
  mode: 'hash',
  /*hash模式改为history*/
  routes // （缩写）相当于 routes: routes
})

export default router;
