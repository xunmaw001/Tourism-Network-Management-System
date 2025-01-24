package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 旅游路线
 *
 * @author 
 * @email
 */
@TableName("lvyouluxian")
public class LvyouluxianEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public LvyouluxianEntity() {

	}

	public LvyouluxianEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 商家
     */
    @ColumnInfo(comment="商家",type="int(11)")
    @TableField(value = "shangjia_id")

    private Integer shangjiaId;


    /**
     * 路线名称
     */
    @ColumnInfo(comment="路线名称",type="varchar(200)")
    @TableField(value = "lvyouluxian_name")

    private String lvyouluxianName;


    /**
     * 路线编号
     */
    @ColumnInfo(comment="路线编号",type="varchar(200)")
    @TableField(value = "lvyouluxian_uuid_number")

    private String lvyouluxianUuidNumber;


    /**
     * 路线照片
     */
    @ColumnInfo(comment="路线照片",type="varchar(200)")
    @TableField(value = "lvyouluxian_photo")

    private String lvyouluxianPhoto;


    /**
     * 出发地点
     */
    @ColumnInfo(comment="出发地点",type="varchar(200)")
    @TableField(value = "lvyouluxian_chufa_address")

    private String lvyouluxianChufaAddress;


    /**
     * 经过地点
     */
    @ColumnInfo(comment="经过地点",type="varchar(200)")
    @TableField(value = "lvyouluxian_luguo_address")

    private String lvyouluxianLuguoAddress;


    /**
     * 截止地点
     */
    @ColumnInfo(comment="截止地点",type="varchar(200)")
    @TableField(value = "lvyouluxian_jiezhi_address")

    private String lvyouluxianJiezhiAddress;


    /**
     * 旅游路线类型
     */
    @ColumnInfo(comment="旅游路线类型",type="int(11)")
    @TableField(value = "lvyouluxian_types")

    private Integer lvyouluxianTypes;


    /**
     * 路线花费
     */
    @ColumnInfo(comment="路线花费",type="decimal(10,2)")
    @TableField(value = "lvyouluxian_new_money")

    private Double lvyouluxianNewMoney;


    /**
     * 旅游路线热度
     */
    @ColumnInfo(comment="旅游路线热度",type="int(11)")
    @TableField(value = "lvyouluxian_clicknum")

    private Integer lvyouluxianClicknum;


    /**
     * 旅游路线介绍
     */
    @ColumnInfo(comment="旅游路线介绍",type="text")
    @TableField(value = "lvyouluxian_content")

    private String lvyouluxianContent;


    /**
     * 是否上架
     */
    @ColumnInfo(comment="是否上架",type="int(11)")
    @TableField(value = "shangxia_types")

    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "lvyouluxian_delete")

    private Integer lvyouluxianDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Lvyouluxian{" +
            ", id=" + id +
            ", shangjiaId=" + shangjiaId +
            ", lvyouluxianName=" + lvyouluxianName +
            ", lvyouluxianUuidNumber=" + lvyouluxianUuidNumber +
            ", lvyouluxianPhoto=" + lvyouluxianPhoto +
            ", lvyouluxianChufaAddress=" + lvyouluxianChufaAddress +
            ", lvyouluxianLuguoAddress=" + lvyouluxianLuguoAddress +
            ", lvyouluxianJiezhiAddress=" + lvyouluxianJiezhiAddress +
            ", lvyouluxianTypes=" + lvyouluxianTypes +
            ", lvyouluxianNewMoney=" + lvyouluxianNewMoney +
            ", lvyouluxianClicknum=" + lvyouluxianClicknum +
            ", lvyouluxianContent=" + lvyouluxianContent +
            ", shangxiaTypes=" + shangxiaTypes +
            ", lvyouluxianDelete=" + lvyouluxianDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
