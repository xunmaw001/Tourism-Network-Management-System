
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
 * 旅游路线收藏
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/lvyouluxianCollection")
public class LvyouluxianCollectionController {
    private static final Logger logger = LoggerFactory.getLogger(LvyouluxianCollectionController.class);

    private static final String TABLE_NAME = "lvyouluxianCollection";

    @Autowired
    private LvyouluxianCollectionService lvyouluxianCollectionService;


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
        CommonUtil.checkMap(params);
        PageUtils page = lvyouluxianCollectionService.queryPage(params);

        //字典表数据转换
        List<LvyouluxianCollectionView> list =(List<LvyouluxianCollectionView>)page.getList();
        for(LvyouluxianCollectionView c:list){
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
        LvyouluxianCollectionEntity lvyouluxianCollection = lvyouluxianCollectionService.selectById(id);
        if(lvyouluxianCollection !=null){
            //entity转view
            LvyouluxianCollectionView view = new LvyouluxianCollectionView();
            BeanUtils.copyProperties( lvyouluxianCollection , view );//把实体数据重构到view中
            //级联表 旅游路线
            //级联表
            LvyouluxianEntity lvyouluxian = lvyouluxianService.selectById(lvyouluxianCollection.getLvyouluxianId());
            if(lvyouluxian != null){
            BeanUtils.copyProperties( lvyouluxian , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setLvyouluxianId(lvyouluxian.getId());
            }
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(lvyouluxianCollection.getYonghuId());
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
    public R save(@RequestBody LvyouluxianCollectionEntity lvyouluxianCollection, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,lvyouluxianCollection:{}",this.getClass().getName(),lvyouluxianCollection.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            lvyouluxianCollection.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<LvyouluxianCollectionEntity> queryWrapper = new EntityWrapper<LvyouluxianCollectionEntity>()
            .eq("lvyouluxian_id", lvyouluxianCollection.getLvyouluxianId())
            .eq("yonghu_id", lvyouluxianCollection.getYonghuId())
            .eq("lvyouluxian_collection_types", lvyouluxianCollection.getLvyouluxianCollectionTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        LvyouluxianCollectionEntity lvyouluxianCollectionEntity = lvyouluxianCollectionService.selectOne(queryWrapper);
        if(lvyouluxianCollectionEntity==null){
            lvyouluxianCollection.setInsertTime(new Date());
            lvyouluxianCollection.setCreateTime(new Date());
            lvyouluxianCollectionService.insert(lvyouluxianCollection);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody LvyouluxianCollectionEntity lvyouluxianCollection, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,lvyouluxianCollection:{}",this.getClass().getName(),lvyouluxianCollection.toString());
        LvyouluxianCollectionEntity oldLvyouluxianCollectionEntity = lvyouluxianCollectionService.selectById(lvyouluxianCollection.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            lvyouluxianCollection.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            lvyouluxianCollectionService.updateById(lvyouluxianCollection);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<LvyouluxianCollectionEntity> oldLvyouluxianCollectionList =lvyouluxianCollectionService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        lvyouluxianCollectionService.deleteBatchIds(Arrays.asList(ids));

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
            List<LvyouluxianCollectionEntity> lvyouluxianCollectionList = new ArrayList<>();//上传的东西
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
                            LvyouluxianCollectionEntity lvyouluxianCollectionEntity = new LvyouluxianCollectionEntity();
//                            lvyouluxianCollectionEntity.setLvyouluxianId(Integer.valueOf(data.get(0)));   //旅游路线 要改的
//                            lvyouluxianCollectionEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            lvyouluxianCollectionEntity.setLvyouluxianCollectionTypes(Integer.valueOf(data.get(0)));   //类型 要改的
//                            lvyouluxianCollectionEntity.setInsertTime(date);//时间
//                            lvyouluxianCollectionEntity.setCreateTime(date);//时间
                            lvyouluxianCollectionList.add(lvyouluxianCollectionEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        lvyouluxianCollectionService.insertBatch(lvyouluxianCollectionList);
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
        PageUtils page = lvyouluxianCollectionService.queryPage(params);

        //字典表数据转换
        List<LvyouluxianCollectionView> list =(List<LvyouluxianCollectionView>)page.getList();
        for(LvyouluxianCollectionView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        LvyouluxianCollectionEntity lvyouluxianCollection = lvyouluxianCollectionService.selectById(id);
            if(lvyouluxianCollection !=null){


                //entity转view
                LvyouluxianCollectionView view = new LvyouluxianCollectionView();
                BeanUtils.copyProperties( lvyouluxianCollection , view );//把实体数据重构到view中

                //级联表
                    LvyouluxianEntity lvyouluxian = lvyouluxianService.selectById(lvyouluxianCollection.getLvyouluxianId());
                if(lvyouluxian != null){
                    BeanUtils.copyProperties( lvyouluxian , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setLvyouluxianId(lvyouluxian.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(lvyouluxianCollection.getYonghuId());
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
    public R add(@RequestBody LvyouluxianCollectionEntity lvyouluxianCollection, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,lvyouluxianCollection:{}",this.getClass().getName(),lvyouluxianCollection.toString());
        Wrapper<LvyouluxianCollectionEntity> queryWrapper = new EntityWrapper<LvyouluxianCollectionEntity>()
            .eq("lvyouluxian_id", lvyouluxianCollection.getLvyouluxianId())
            .eq("yonghu_id", lvyouluxianCollection.getYonghuId())
            .eq("lvyouluxian_collection_types", lvyouluxianCollection.getLvyouluxianCollectionTypes())
//            .notIn("lvyouluxian_collection_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        LvyouluxianCollectionEntity lvyouluxianCollectionEntity = lvyouluxianCollectionService.selectOne(queryWrapper);
        if(lvyouluxianCollectionEntity==null){
            lvyouluxianCollection.setInsertTime(new Date());
            lvyouluxianCollection.setCreateTime(new Date());
        lvyouluxianCollectionService.insert(lvyouluxianCollection);

            return R.ok();
        }else {
            return R.error(511,"您已经收藏过了");
        }
    }

}

