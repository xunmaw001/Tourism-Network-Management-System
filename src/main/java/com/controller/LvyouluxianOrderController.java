
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 旅游路线订单
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/lvyouluxianOrder")
public class LvyouluxianOrderController {
    private static final Logger logger = LoggerFactory.getLogger(LvyouluxianOrderController.class);

    private static final String TABLE_NAME = "lvyouluxianOrder";

    @Autowired
    private LvyouluxianOrderService lvyouluxianOrderService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private GonggaoService gonggaoService;//公告
    @Autowired
    private JingdianService jingdianService;//景点
    @Autowired
    private JingdianCollectionService jingdianCollectionService;//景点收藏
    @Autowired
    private JingdianCommentbackService jingdianCommentbackService;//景点评价
    @Autowired
    private JingdianOrderService jingdianOrderService;//景点门票购买
    @Autowired
    private LvyouluxianService lvyouluxianService;//旅游路线
    @Autowired
    private LvyouluxianCollectionService lvyouluxianCollectionService;//旅游路线收藏
    @Autowired
    private LvyouluxianCommentbackService lvyouluxianCommentbackService;//旅游路线评价
    @Autowired
    private ShangjiaService shangjiaService;//旅游公司
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("旅游公司".equals(role))
            params.put("shangjiaId",request.getSession().getAttribute("userId"));
        CommonUtil.checkMap(params);
        PageUtils page = lvyouluxianOrderService.queryPage(params);

        //字典表数据转换
        List<LvyouluxianOrderView> list =(List<LvyouluxianOrderView>)page.getList();
        for(LvyouluxianOrderView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        LvyouluxianOrderEntity lvyouluxianOrder = lvyouluxianOrderService.selectById(id);
        if(lvyouluxianOrder !=null){
            //entity转view
            LvyouluxianOrderView view = new LvyouluxianOrderView();
            BeanUtils.copyProperties( lvyouluxianOrder , view );//把实体数据重构到view中
            //级联表 旅游路线
            //级联表
            LvyouluxianEntity lvyouluxian = lvyouluxianService.selectById(lvyouluxianOrder.getLvyouluxianId());
            if(lvyouluxian != null){
            BeanUtils.copyProperties( lvyouluxian , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setLvyouluxianId(lvyouluxian.getId());
            }
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(lvyouluxianOrder.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody LvyouluxianOrderEntity lvyouluxianOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,lvyouluxianOrder:{}",this.getClass().getName(),lvyouluxianOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            lvyouluxianOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        lvyouluxianOrder.setCreateTime(new Date());
        lvyouluxianOrder.setInsertTime(new Date());
        lvyouluxianOrderService.insert(lvyouluxianOrder);

        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody LvyouluxianOrderEntity lvyouluxianOrder, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,lvyouluxianOrder:{}",this.getClass().getName(),lvyouluxianOrder.toString());
        LvyouluxianOrderEntity oldLvyouluxianOrderEntity = lvyouluxianOrderService.selectById(lvyouluxianOrder.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            lvyouluxianOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            lvyouluxianOrderService.updateById(lvyouluxianOrder);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<LvyouluxianOrderEntity> oldLvyouluxianOrderList =lvyouluxianOrderService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        lvyouluxianOrderService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<LvyouluxianOrderEntity> lvyouluxianOrderList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            LvyouluxianOrderEntity lvyouluxianOrderEntity = new LvyouluxianOrderEntity();
//                            lvyouluxianOrderEntity.setLvyouluxianOrderUuidNumber(data.get(0));                    //订单号 要改的
//                            lvyouluxianOrderEntity.setLvyouluxianId(Integer.valueOf(data.get(0)));   //旅游路线 要改的
//                            lvyouluxianOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            lvyouluxianOrderEntity.setBuyNumber(Integer.valueOf(data.get(0)));   //购买数量 要改的
//                            lvyouluxianOrderEntity.setLvyouluxianOrderTime(sdf.parse(data.get(0)));          //预约时间 要改的
//                            lvyouluxianOrderEntity.setLvyouluxianOrderTruePrice(data.get(0));                    //实付价格 要改的
//                            lvyouluxianOrderEntity.setLvyouluxianOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            lvyouluxianOrderEntity.setLvyouluxianOrderPaymentTypes(Integer.valueOf(data.get(0)));   //支付类型 要改的
//                            lvyouluxianOrderEntity.setInsertTime(date);//时间
//                            lvyouluxianOrderEntity.setCreateTime(date);//时间
                            lvyouluxianOrderList.add(lvyouluxianOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //订单号
                                if(seachFields.containsKey("lvyouluxianOrderUuidNumber")){
                                    List<String> lvyouluxianOrderUuidNumber = seachFields.get("lvyouluxianOrderUuidNumber");
                                    lvyouluxianOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> lvyouluxianOrderUuidNumber = new ArrayList<>();
                                    lvyouluxianOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("lvyouluxianOrderUuidNumber",lvyouluxianOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //订单号
                        List<LvyouluxianOrderEntity> lvyouluxianOrderEntities_lvyouluxianOrderUuidNumber = lvyouluxianOrderService.selectList(new EntityWrapper<LvyouluxianOrderEntity>().in("lvyouluxian_order_uuid_number", seachFields.get("lvyouluxianOrderUuidNumber")));
                        if(lvyouluxianOrderEntities_lvyouluxianOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(LvyouluxianOrderEntity s:lvyouluxianOrderEntities_lvyouluxianOrderUuidNumber){
                                repeatFields.add(s.getLvyouluxianOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [订单号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        lvyouluxianOrderService.insertBatch(lvyouluxianOrderList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }




    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = lvyouluxianOrderService.queryPage(params);

        //字典表数据转换
        List<LvyouluxianOrderView> list =(List<LvyouluxianOrderView>)page.getList();
        for(LvyouluxianOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        LvyouluxianOrderEntity lvyouluxianOrder = lvyouluxianOrderService.selectById(id);
            if(lvyouluxianOrder !=null){


                //entity转view
                LvyouluxianOrderView view = new LvyouluxianOrderView();
                BeanUtils.copyProperties( lvyouluxianOrder , view );//把实体数据重构到view中

                //级联表
                    LvyouluxianEntity lvyouluxian = lvyouluxianService.selectById(lvyouluxianOrder.getLvyouluxianId());
                if(lvyouluxian != null){
                    BeanUtils.copyProperties( lvyouluxian , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setLvyouluxianId(lvyouluxian.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(lvyouluxianOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody LvyouluxianOrderEntity lvyouluxianOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,lvyouluxianOrder:{}",this.getClass().getName(),lvyouluxianOrder.toString());
            LvyouluxianEntity lvyouluxianEntity = lvyouluxianService.selectById(lvyouluxianOrder.getLvyouluxianId());
            if(lvyouluxianEntity == null){
                return R.error(511,"查不到该旅游路线");
            }
            // Double lvyouluxianNewMoney = lvyouluxianEntity.getLvyouluxianNewMoney();

            if(false){
            }
            else if(lvyouluxianEntity.getLvyouluxianNewMoney() == null){
                return R.error(511,"路线花费不能为空");
            }

            //计算所获得积分
            Double buyJifen =0.0;
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");
            double balance = yonghuEntity.getNewMoney() - lvyouluxianEntity.getLvyouluxianNewMoney()*lvyouluxianOrder.getBuyNumber();//余额
            if(balance<0)
                return R.error(511,"余额不够支付");
            lvyouluxianOrder.setLvyouluxianOrderTypes(101); //设置订单状态为已支付
            lvyouluxianOrder.setLvyouluxianOrderTruePrice(lvyouluxianEntity.getLvyouluxianNewMoney()*lvyouluxianOrder.getBuyNumber()); //设置实付价格
            lvyouluxianOrder.setYonghuId(userId); //设置订单支付人id
            lvyouluxianOrder.setLvyouluxianOrderUuidNumber(String.valueOf(new Date().getTime()));
            lvyouluxianOrder.setLvyouluxianOrderPaymentTypes(1);
            lvyouluxianOrder.setInsertTime(new Date());
            lvyouluxianOrder.setCreateTime(new Date());
                lvyouluxianOrderService.insert(lvyouluxianOrder);//新增订单
            //更新第一注册表
            yonghuEntity.setNewMoney(balance);//设置金额
            yonghuService.updateById(yonghuEntity);

            ShangjiaEntity shangjiaEntity =shangjiaService.selectById(lvyouluxianEntity.getShangjiaId());
            shangjiaEntity.setNewMoney(shangjiaEntity.getNewMoney()+lvyouluxianOrder.getLvyouluxianOrderTruePrice());//动态计算金额
            shangjiaService.updateById(shangjiaEntity);

            return R.ok();
    }


    /**
    * 退款
    */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request){
        logger.debug("refund方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

            LvyouluxianOrderEntity lvyouluxianOrder = lvyouluxianOrderService.selectById(id);//当前表service
            Integer buyNumber = lvyouluxianOrder.getBuyNumber();
            Integer lvyouluxianId = lvyouluxianOrder.getLvyouluxianId();
            if(lvyouluxianId == null)
                return R.error(511,"查不到该旅游路线");
            LvyouluxianEntity lvyouluxianEntity = lvyouluxianService.selectById(lvyouluxianId);
            if(lvyouluxianEntity == null)
                return R.error(511,"查不到该旅游路线");
            //获取商家信息
            Integer shangjiaId = lvyouluxianEntity.getShangjiaId();
            ShangjiaEntity shangjiaEntity = shangjiaService.selectById(shangjiaId);//商家
            Double lvyouluxianNewMoney = lvyouluxianEntity.getLvyouluxianNewMoney();
            if(lvyouluxianNewMoney == null)
                return R.error(511,"旅游路线价格不能为空");

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
            return R.error(511,"用户金额不能为空");
            Double zhekou = 1.0;

                //计算金额
                Double money = lvyouluxianEntity.getLvyouluxianNewMoney() * buyNumber  * zhekou;
                //计算所获得积分
                Double buyJifen = 0.0;
                yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() + money); //设置金额


                //修改商家余额
                shangjiaEntity.setNewMoney(shangjiaEntity.getNewMoney() - money);



            lvyouluxianOrder.setLvyouluxianOrderTypes(102);//设置订单状态为已退款
            lvyouluxianOrderService.updateById(lvyouluxianOrder);//根据id更新
            shangjiaService.updateById(shangjiaEntity);
            yonghuService.updateById(yonghuEntity);//更新用户信息
            lvyouluxianService.updateById(lvyouluxianEntity);//更新订单中旅游路线的信息

            return R.ok();
    }

    /**
    * 评价
    */
    @RequestMapping("/commentback")
    public R commentback(Integer id, String commentbackText, Integer lvyouluxianCommentbackPingfenNumber, HttpServletRequest request){
        logger.debug("commentback方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
            LvyouluxianOrderEntity lvyouluxianOrder = lvyouluxianOrderService.selectById(id);
        if(lvyouluxianOrder == null)
            return R.error(511,"查不到该订单");
        Integer lvyouluxianId = lvyouluxianOrder.getLvyouluxianId();
        if(lvyouluxianId == null)
            return R.error(511,"查不到该旅游路线");

        LvyouluxianCommentbackEntity lvyouluxianCommentbackEntity = new LvyouluxianCommentbackEntity();
            lvyouluxianCommentbackEntity.setId(id);
            lvyouluxianCommentbackEntity.setLvyouluxianId(lvyouluxianId);
            lvyouluxianCommentbackEntity.setYonghuId((Integer) request.getSession().getAttribute("userId"));
            lvyouluxianCommentbackEntity.setLvyouluxianCommentbackText(commentbackText);
            lvyouluxianCommentbackEntity.setInsertTime(new Date());
            lvyouluxianCommentbackEntity.setReplyText(null);
            lvyouluxianCommentbackEntity.setUpdateTime(null);
            lvyouluxianCommentbackEntity.setCreateTime(new Date());
            lvyouluxianCommentbackService.insert(lvyouluxianCommentbackEntity);

            lvyouluxianOrder.setLvyouluxianOrderTypes(105);//设置订单状态为已评价
            lvyouluxianOrderService.updateById(lvyouluxianOrder);//根据id更新
            return R.ok();
    }

    /**
     * 用户使用
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id ){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        LvyouluxianOrderEntity  lvyouluxianOrderEntity = lvyouluxianOrderService.selectById(id);
        lvyouluxianOrderEntity.setLvyouluxianOrderTypes(103);//设置订单状态为已用户使用
        lvyouluxianOrderService.updateById( lvyouluxianOrderEntity);

        return R.ok();
    }


    /**
     * 参与
     */
    @RequestMapping("/receiving")
    public R receiving(Integer id){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        LvyouluxianOrderEntity  lvyouluxianOrderEntity = lvyouluxianOrderService.selectById(id);
        lvyouluxianOrderEntity.setLvyouluxianOrderTypes(104);//设置订单状态为参与
        lvyouluxianOrderService.updateById( lvyouluxianOrderEntity);
        return R.ok();
    }

}

