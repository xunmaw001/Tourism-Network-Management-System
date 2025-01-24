
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
 * 旅游路线评价
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/lvyouluxianCommentback")
public class LvyouluxianCommentbackController {
    private static final Logger logger = LoggerFactory.getLogger(LvyouluxianCommentbackController.class);

    private static final String TABLE_NAME = "lvyouluxianCommentback";

    @Autowired
    private LvyouluxianCommentbackService lvyouluxianCommentbackService;


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
        PageUtils page = lvyouluxianCommentbackService.queryPage(params);

        //字典表数据转换
        List<LvyouluxianCommentbackView> list =(List<LvyouluxianCommentbackView>)page.getList();
        for(LvyouluxianCommentbackView c:list){
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
        LvyouluxianCommentbackEntity lvyouluxianCommentback = lvyouluxianCommentbackService.selectById(id);
        if(lvyouluxianCommentback !=null){
            //entity转view
            LvyouluxianCommentbackView view = new LvyouluxianCommentbackView();
            BeanUtils.copyProperties( lvyouluxianCommentback , view );//把实体数据重构到view中
            //级联表 旅游路线
            //级联表
            LvyouluxianEntity lvyouluxian = lvyouluxianService.selectById(lvyouluxianCommentback.getLvyouluxianId());
            if(lvyouluxian != null){
            BeanUtils.copyProperties( lvyouluxian , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setLvyouluxianId(lvyouluxian.getId());
            }
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(lvyouluxianCommentback.getYonghuId());
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
    public R save(@RequestBody LvyouluxianCommentbackEntity lvyouluxianCommentback, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,lvyouluxianCommentback:{}",this.getClass().getName(),lvyouluxianCommentback.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            lvyouluxianCommentback.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        lvyouluxianCommentback.setCreateTime(new Date());
        lvyouluxianCommentback.setInsertTime(new Date());
        lvyouluxianCommentbackService.insert(lvyouluxianCommentback);

        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody LvyouluxianCommentbackEntity lvyouluxianCommentback, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,lvyouluxianCommentback:{}",this.getClass().getName(),lvyouluxianCommentback.toString());
        LvyouluxianCommentbackEntity oldLvyouluxianCommentbackEntity = lvyouluxianCommentbackService.selectById(lvyouluxianCommentback.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            lvyouluxianCommentback.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        lvyouluxianCommentback.setUpdateTime(new Date());

            lvyouluxianCommentbackService.updateById(lvyouluxianCommentback);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<LvyouluxianCommentbackEntity> oldLvyouluxianCommentbackList =lvyouluxianCommentbackService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        lvyouluxianCommentbackService.deleteBatchIds(Arrays.asList(ids));

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
            List<LvyouluxianCommentbackEntity> lvyouluxianCommentbackList = new ArrayList<>();//上传的东西
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
                            LvyouluxianCommentbackEntity lvyouluxianCommentbackEntity = new LvyouluxianCommentbackEntity();
//                            lvyouluxianCommentbackEntity.setLvyouluxianId(Integer.valueOf(data.get(0)));   //旅游路线 要改的
//                            lvyouluxianCommentbackEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            lvyouluxianCommentbackEntity.setLvyouluxianCommentbackText(data.get(0));                    //评价内容 要改的
//                            lvyouluxianCommentbackEntity.setInsertTime(date);//时间
//                            lvyouluxianCommentbackEntity.setReplyText(data.get(0));                    //回复内容 要改的
//                            lvyouluxianCommentbackEntity.setUpdateTime(sdf.parse(data.get(0)));          //回复时间 要改的
//                            lvyouluxianCommentbackEntity.setCreateTime(date);//时间
                            lvyouluxianCommentbackList.add(lvyouluxianCommentbackEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        lvyouluxianCommentbackService.insertBatch(lvyouluxianCommentbackList);
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
        PageUtils page = lvyouluxianCommentbackService.queryPage(params);

        //字典表数据转换
        List<LvyouluxianCommentbackView> list =(List<LvyouluxianCommentbackView>)page.getList();
        for(LvyouluxianCommentbackView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        LvyouluxianCommentbackEntity lvyouluxianCommentback = lvyouluxianCommentbackService.selectById(id);
            if(lvyouluxianCommentback !=null){


                //entity转view
                LvyouluxianCommentbackView view = new LvyouluxianCommentbackView();
                BeanUtils.copyProperties( lvyouluxianCommentback , view );//把实体数据重构到view中

                //级联表
                    LvyouluxianEntity lvyouluxian = lvyouluxianService.selectById(lvyouluxianCommentback.getLvyouluxianId());
                if(lvyouluxian != null){
                    BeanUtils.copyProperties( lvyouluxian , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setLvyouluxianId(lvyouluxian.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(lvyouluxianCommentback.getYonghuId());
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
    public R add(@RequestBody LvyouluxianCommentbackEntity lvyouluxianCommentback, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,lvyouluxianCommentback:{}",this.getClass().getName(),lvyouluxianCommentback.toString());
        lvyouluxianCommentback.setCreateTime(new Date());
        lvyouluxianCommentback.setInsertTime(new Date());
        lvyouluxianCommentbackService.insert(lvyouluxianCommentback);

            return R.ok();
        }

}

