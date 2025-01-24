package com.entity.model;

import com.entity.LvyouluxianEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 旅游路线
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class LvyouluxianModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 商家
     */
    private Integer shangjiaId;


    /**
     * 路线名称
     */
    private String lvyouluxianName;


    /**
     * 路线编号
     */
    private String lvyouluxianUuidNumber;


    /**
     * 路线照片
     */
    private String lvyouluxianPhoto;


    /**
     * 出发地点
     */
    private String lvyouluxianChufaAddress;


    /**
     * 经过地点
     */
    private String lvyouluxianLuguoAddress;


    /**
     * 截止地点
     */
    private String lvyouluxianJiezhiAddress;


    /**
     * 旅游路线类型
     */
    private Integer lvyouluxianTypes;


    /**
     * 路线花费
     */
    private Double lvyouluxianNewMoney;


    /**
     * 旅游路线热度
     */
    private Integer lvyouluxianClicknum;


    /**
     * 旅游路线介绍
     */
    private String lvyouluxianContent;


    /**
     * 是否上架
     */
    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */
    private Integer lvyouluxianDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：商家
	 */
    public Integer getShangjiaId() {
        return shangjiaId;
    }


    /**
	 * 设置：商家
	 */
    public void setShangjiaId(Integer shangjiaId) {
        this.shangjiaId = shangjiaId;
    }
    /**
	 * 获取：路线名称
	 */
    public String getLvyouluxianName() {
        return lvyouluxianName;
    }


    /**
	 * 设置：路线名称
	 */
    public void setLvyouluxianName(String lvyouluxianName) {
        this.lvyouluxianName = lvyouluxianName;
    }
    /**
	 * 获取：路线编号
	 */
    public String getLvyouluxianUuidNumber() {
        return lvyouluxianUuidNumber;
    }


    /**
	 * 设置：路线编号
	 */
    public void setLvyouluxianUuidNumber(String lvyouluxianUuidNumber) {
        this.lvyouluxianUuidNumber = lvyouluxianUuidNumber;
    }
    /**
	 * 获取：路线照片
	 */
    public String getLvyouluxianPhoto() {
        return lvyouluxianPhoto;
    }


    /**
	 * 设置：路线照片
	 */
    public void setLvyouluxianPhoto(String lvyouluxianPhoto) {
        this.lvyouluxianPhoto = lvyouluxianPhoto;
    }
    /**
	 * 获取：出发地点
	 */
    public String getLvyouluxianChufaAddress() {
        return lvyouluxianChufaAddress;
    }


    /**
	 * 设置：出发地点
	 */
    public void setLvyouluxianChufaAddress(String lvyouluxianChufaAddress) {
        this.lvyouluxianChufaAddress = lvyouluxianChufaAddress;
    }
    /**
	 * 获取：经过地点
	 */
    public String getLvyouluxianLuguoAddress() {
        return lvyouluxianLuguoAddress;
    }


    /**
	 * 设置：经过地点
	 */
    public void setLvyouluxianLuguoAddress(String lvyouluxianLuguoAddress) {
        this.lvyouluxianLuguoAddress = lvyouluxianLuguoAddress;
    }
    /**
	 * 获取：截止地点
	 */
    public String getLvyouluxianJiezhiAddress() {
        return lvyouluxianJiezhiAddress;
    }


    /**
	 * 设置：截止地点
	 */
    public void setLvyouluxianJiezhiAddress(String lvyouluxianJiezhiAddress) {
        this.lvyouluxianJiezhiAddress = lvyouluxianJiezhiAddress;
    }
    /**
	 * 获取：旅游路线类型
	 */
    public Integer getLvyouluxianTypes() {
        return lvyouluxianTypes;
    }


    /**
	 * 设置：旅游路线类型
	 */
    public void setLvyouluxianTypes(Integer lvyouluxianTypes) {
        this.lvyouluxianTypes = lvyouluxianTypes;
    }
    /**
	 * 获取：路线花费
	 */
    public Double getLvyouluxianNewMoney() {
        return lvyouluxianNewMoney;
    }


    /**
	 * 设置：路线花费
	 */
    public void setLvyouluxianNewMoney(Double lvyouluxianNewMoney) {
        this.lvyouluxianNewMoney = lvyouluxianNewMoney;
    }
    /**
	 * 获取：旅游路线热度
	 */
    public Integer getLvyouluxianClicknum() {
        return lvyouluxianClicknum;
    }


    /**
	 * 设置：旅游路线热度
	 */
    public void setLvyouluxianClicknum(Integer lvyouluxianClicknum) {
        this.lvyouluxianClicknum = lvyouluxianClicknum;
    }
    /**
	 * 获取：旅游路线介绍
	 */
    public String getLvyouluxianContent() {
        return lvyouluxianContent;
    }


    /**
	 * 设置：旅游路线介绍
	 */
    public void setLvyouluxianContent(String lvyouluxianContent) {
        this.lvyouluxianContent = lvyouluxianContent;
    }
    /**
	 * 获取：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }


    /**
	 * 设置：是否上架
	 */
    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getLvyouluxianDelete() {
        return lvyouluxianDelete;
    }


    /**
	 * 设置：逻辑删除
	 */
    public void setLvyouluxianDelete(Integer lvyouluxianDelete) {
        this.lvyouluxianDelete = lvyouluxianDelete;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：录入时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
