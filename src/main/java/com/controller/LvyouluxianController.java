
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
 * 旅游路线
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/lvyouluxian")
public class LvyouluxianController {
    private static final Logger logger = LoggerFactory.getLogger(LvyouluxianController.class);

    private static final String TABLE_NAME = "lvyouluxian";

    @Autowired
    private LvyouluxianService lvyouluxianService;


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
    private LvyouluxianCollectionService lvyouluxianCollectionService;//旅游路线收藏
    @Autowired
    private LvyouluxianCommentbackService lvyouluxianCommentbackService;//旅游路线评价
    @Autowired
    private LvyouluxianOrderService lvyouluxianOrderService;//旅游路线订单
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
        params.put("lvyouluxianDeleteStart",1);params.put("lvyouluxianDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = lvyouluxianService.queryPage(params);

        //字典表数据转换
        List<LvyouluxianView> list =(List<LvyouluxianView>)page.getList();
        for(LvyouluxianView c:list){
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
        LvyouluxianEntity lvyouluxian = lvyouluxianService.selectById(id);
        if(lvyouluxian !=null){
            //entity转view
            LvyouluxianView view = new LvyouluxianView();
            BeanUtils.copyProperties( lvyouluxian , view );//把实体数据重构到view中
            //级联表 旅游公司
            //级联表
            ShangjiaEntity shangjia = shangjiaService.selectById(lvyouluxian.getShangjiaId());
            if(shangjia != null){
            BeanUtils.copyProperties( shangjia , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "shangjiaId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setShangjiaId(shangjia.getId());
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
    public R save(@RequestBody LvyouluxianEntity lvyouluxian, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,lvyouluxian:{}",this.getClass().getName(),lvyouluxian.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("旅游公司".equals(role))
            lvyouluxian.setShangjiaId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<LvyouluxianEntity> queryWrapper = new EntityWrapper<LvyouluxianEntity>()
            .eq("shangjia_id", lvyouluxian.getShangjiaId())
            .eq("lvyouluxian_name", lvyouluxian.getLvyouluxianName())
            .eq("lvyouluxian_chufa_address", lvyouluxian.getLvyouluxianChufaAddress())
            .eq("lvyouluxian_luguo_address", lvyouluxian.getLvyouluxianLuguoAddress())
            .eq("lvyouluxian_jiezhi_address", lvyouluxian.getLvyouluxianJiezhiAddress())
            .eq("lvyouluxian_types", lvyouluxian.getLvyouluxianTypes())
            .eq("lvyouluxian_clicknum", lvyouluxian.getLvyouluxianClicknum())
            .eq("shangxia_types", lvyouluxian.getShangxiaTypes())
            .eq("lvyouluxian_delete", lvyouluxian.getLvyouluxianDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        LvyouluxianEntity lvyouluxianEntity = lvyouluxianService.selectOne(queryWrapper);
        if(lvyouluxianEntity==null){
            lvyouluxian.setLvyouluxianClicknum(1);
            lvyouluxian.setShangxiaTypes(1);
            lvyouluxian.setLvyouluxianDelete(1);
            lvyouluxian.setInsertTime(new Date());
            lvyouluxian.setCreateTime(new Date());
            lvyouluxianService.insert(lvyouluxian);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody LvyouluxianEntity lvyouluxian, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,lvyouluxian:{}",this.getClass().getName(),lvyouluxian.toString());
        LvyouluxianEntity oldLvyouluxianEntity = lvyouluxianService.selectById(lvyouluxian.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("旅游公司".equals(role))
//            lvyouluxian.setShangjiaId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(lvyouluxian.getLvyouluxianPhoto()) || "null".equals(lvyouluxian.getLvyouluxianPhoto())){
                lvyouluxian.setLvyouluxianPhoto(null);
        }

            lvyouluxianService.updateById(lvyouluxian);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<LvyouluxianEntity> oldLvyouluxianList =lvyouluxianService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<LvyouluxianEntity> list = new ArrayList<>();
        for(Integer id:ids){
            LvyouluxianEntity lvyouluxianEntity = new LvyouluxianEntity();
            lvyouluxianEntity.setId(id);
            lvyouluxianEntity.setLvyouluxianDelete(2);
            list.add(lvyouluxianEntity);
        }
        if(list != null && list.size() >0){
            lvyouluxianService.updateBatchById(list);
        }

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
            List<LvyouluxianEntity> lvyouluxianList = new ArrayList<>();//上传的东西
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
                            LvyouluxianEntity lvyouluxianEntity = new LvyouluxianEntity();
//                            lvyouluxianEntity.setShangjiaId(Integer.valueOf(data.get(0)));   //商家 要改的
//                            lvyouluxianEntity.setLvyouluxianName(data.get(0));                    //路线名称 要改的
//                            lvyouluxianEntity.setLvyouluxianUuidNumber(data.get(0));                    //路线编号 要改的
//                            lvyouluxianEntity.setLvyouluxianPhoto("");//详情和图片
//                            lvyouluxianEntity.setLvyouluxianChufaAddress(data.get(0));                    //出发地点 要改的
//                            lvyouluxianEntity.setLvyouluxianLuguoAddress(data.get(0));                    //经过地点 要改的
//                            lvyouluxianEntity.setLvyouluxianJiezhiAddress(data.get(0));                    //截止地点 要改的
//                            lvyouluxianEntity.setLvyouluxianTypes(Integer.valueOf(data.get(0)));   //旅游路线类型 要改的
//                            lvyouluxianEntity.setLvyouluxianNewMoney(data.get(0));                    //路线花费 要改的
//                            lvyouluxianEntity.setLvyouluxianClicknum(Integer.valueOf(data.get(0)));   //旅游路线热度 要改的
//                            lvyouluxianEntity.setLvyouluxianContent("");//详情和图片
//                            lvyouluxianEntity.setShangxiaTypes(Integer.valueOf(data.get(0)));   //是否上架 要改的
//                            lvyouluxianEntity.setLvyouluxianDelete(1);//逻辑删除字段
//                            lvyouluxianEntity.setInsertTime(date);//时间
//                            lvyouluxianEntity.setCreateTime(date);//时间
                            lvyouluxianList.add(lvyouluxianEntity);


                            //把要查询是否重复的字段放入map中
                                //路线编号
                                if(seachFields.containsKey("lvyouluxianUuidNumber")){
                                    List<String> lvyouluxianUuidNumber = seachFields.get("lvyouluxianUuidNumber");
                                    lvyouluxianUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> lvyouluxianUuidNumber = new ArrayList<>();
                                    lvyouluxianUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("lvyouluxianUuidNumber",lvyouluxianUuidNumber);
                                }
                        }

                        //查询是否重复
                         //路线编号
                        List<LvyouluxianEntity> lvyouluxianEntities_lvyouluxianUuidNumber = lvyouluxianService.selectList(new EntityWrapper<LvyouluxianEntity>().in("lvyouluxian_uuid_number", seachFields.get("lvyouluxianUuidNumber")).eq("lvyouluxian_delete", 1));
                        if(lvyouluxianEntities_lvyouluxianUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(LvyouluxianEntity s:lvyouluxianEntities_lvyouluxianUuidNumber){
                                repeatFields.add(s.getLvyouluxianUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [路线编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        lvyouluxianService.insertBatch(lvyouluxianList);
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
    * 个性推荐
    */
    @IgnoreAuth
    @RequestMapping("/gexingtuijian")
    public R gexingtuijian(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("gexingtuijian方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        CommonUtil.checkMap(params);
        List<LvyouluxianView> returnLvyouluxianViewList = new ArrayList<>();

        //查询订单
        Map<String, Object> params1 = new HashMap<>(params);params1.put("sort","id");params1.put("yonghuId",request.getSession().getAttribute("userId"));
        PageUtils pageUtils = lvyouluxianOrderService.queryPage(params1);
        List<LvyouluxianOrderView> orderViewsList =(List<LvyouluxianOrderView>)pageUtils.getList();
        Map<Integer,Integer> typeMap=new HashMap<>();//购买的类型list
        for(LvyouluxianOrderView orderView:orderViewsList){
            Integer lvyouluxianTypes = orderView.getLvyouluxianTypes();
            if(typeMap.containsKey(lvyouluxianTypes)){
                typeMap.put(lvyouluxianTypes,typeMap.get(lvyouluxianTypes)+1);
            }else{
                typeMap.put(lvyouluxianTypes,1);
            }
        }
        List<Integer> typeList = new ArrayList<>();//排序后的有序的类型 按最多到最少
        typeMap.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).forEach(e -> typeList.add(e.getKey()));//排序
        Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
        for(Integer type:typeList){
            Map<String, Object> params2 = new HashMap<>(params);params2.put("lvyouluxianTypes",type);
            PageUtils pageUtils1 = lvyouluxianService.queryPage(params2);
            List<LvyouluxianView> lvyouluxianViewList =(List<LvyouluxianView>)pageUtils1.getList();
            returnLvyouluxianViewList.addAll(lvyouluxianViewList);
            if(returnLvyouluxianViewList.size()>= limit) break;//返回的推荐数量大于要的数量 跳出循环
        }
        //正常查询出来商品,用于补全推荐缺少的数据
        PageUtils page = lvyouluxianService.queryPage(params);
        if(returnLvyouluxianViewList.size()<limit){//返回数量还是小于要求数量
            int toAddNum = limit - returnLvyouluxianViewList.size();//要添加的数量
            List<LvyouluxianView> lvyouluxianViewList =(List<LvyouluxianView>)page.getList();
            for(LvyouluxianView lvyouluxianView:lvyouluxianViewList){
                Boolean addFlag = true;
                for(LvyouluxianView returnLvyouluxianView:returnLvyouluxianViewList){
                    if(returnLvyouluxianView.getId().intValue() ==lvyouluxianView.getId().intValue()) addFlag=false;//返回的数据中已存在此商品
                }
                if(addFlag){
                    toAddNum=toAddNum-1;
                    returnLvyouluxianViewList.add(lvyouluxianView);
                    if(toAddNum==0) break;//够数量了
                }
            }
        }else {
            returnLvyouluxianViewList = returnLvyouluxianViewList.subList(0, limit);
        }

        for(LvyouluxianView c:returnLvyouluxianViewList)
            dictionaryService.dictionaryConvert(c, request);
        page.setList(returnLvyouluxianViewList);
        return R.ok().put("data", page);
    }

    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = lvyouluxianService.queryPage(params);

        //字典表数据转换
        List<LvyouluxianView> list =(List<LvyouluxianView>)page.getList();
        for(LvyouluxianView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        LvyouluxianEntity lvyouluxian = lvyouluxianService.selectById(id);
            if(lvyouluxian !=null){

                //点击数量加1
                lvyouluxian.setLvyouluxianClicknum(lvyouluxian.getLvyouluxianClicknum()+1);
                lvyouluxianService.updateById(lvyouluxian);

                //entity转view
                LvyouluxianView view = new LvyouluxianView();
                BeanUtils.copyProperties( lvyouluxian , view );//把实体数据重构到view中

                //级联表
                    ShangjiaEntity shangjia = shangjiaService.selectById(lvyouluxian.getShangjiaId());
                if(shangjia != null){
                    BeanUtils.copyProperties( shangjia , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setShangjiaId(shangjia.getId());
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
    public R add(@RequestBody LvyouluxianEntity lvyouluxian, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,lvyouluxian:{}",this.getClass().getName(),lvyouluxian.toString());
        Wrapper<LvyouluxianEntity> queryWrapper = new EntityWrapper<LvyouluxianEntity>()
            .eq("shangjia_id", lvyouluxian.getShangjiaId())
            .eq("lvyouluxian_name", lvyouluxian.getLvyouluxianName())
            .eq("lvyouluxian_uuid_number", lvyouluxian.getLvyouluxianUuidNumber())
            .eq("lvyouluxian_chufa_address", lvyouluxian.getLvyouluxianChufaAddress())
            .eq("lvyouluxian_luguo_address", lvyouluxian.getLvyouluxianLuguoAddress())
            .eq("lvyouluxian_jiezhi_address", lvyouluxian.getLvyouluxianJiezhiAddress())
            .eq("lvyouluxian_types", lvyouluxian.getLvyouluxianTypes())
            .eq("lvyouluxian_clicknum", lvyouluxian.getLvyouluxianClicknum())
            .eq("shangxia_types", lvyouluxian.getShangxiaTypes())
            .eq("lvyouluxian_delete", lvyouluxian.getLvyouluxianDelete())
//            .notIn("lvyouluxian_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        LvyouluxianEntity lvyouluxianEntity = lvyouluxianService.selectOne(queryWrapper);
        if(lvyouluxianEntity==null){
            lvyouluxian.setLvyouluxianDelete(1);
            lvyouluxian.setInsertTime(new Date());
            lvyouluxian.setCreateTime(new Date());
        lvyouluxianService.insert(lvyouluxian);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

