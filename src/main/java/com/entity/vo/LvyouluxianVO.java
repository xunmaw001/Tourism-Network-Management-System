package com.entity.vo;

import com.entity.LvyouluxianEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 旅游路线
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("lvyouluxian")
public class LvyouluxianVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 商家
     */

    @TableField(value = "shangjia_id")
    private Integer shangjiaId;


    /**
     * 路线名称
     */

    @TableField(value = "lvyouluxian_name")
    private String lvyouluxianName;


    /**
     * 路线编号
     */

    @TableField(value = "lvyouluxian_uuid_number")
    private String lvyouluxianUuidNumber;


    /**
     * 路线照片
     */

    @TableField(value = "lvyouluxian_photo")
    private String lvyouluxianPhoto;


    /**
     * 出发地点
     */

    @TableField(value = "lvyouluxian_chufa_address")
    private String lvyouluxianChufaAddress;


    /**
     * 经过地点
     */

    @TableField(value = "lvyouluxian_luguo_address")
    private String lvyouluxianLuguoAddress;


    /**
     * 截止地点
     */

    @TableField(value = "lvyouluxian_jiezhi_address")
    private String lvyouluxianJiezhiAddress;


    /**
     * 旅游路线类型
     */

    @TableField(value = "lvyouluxian_types")
    private Integer lvyouluxianTypes;


    /**
     * 路线花费
     */

    @TableField(value = "lvyouluxian_new_money")
    private Double lvyouluxianNewMoney;


    /**
     * 旅游路线热度
     */

    @TableField(value = "lvyouluxian_clicknum")
    private Integer lvyouluxianClicknum;


    /**
     * 旅游路线介绍
     */

    @TableField(value = "lvyouluxian_content")
    private String lvyouluxianContent;


    /**
     * 是否上架
     */

    @TableField(value = "shangxia_types")
    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */

    @TableField(value = "lvyouluxian_delete")
    private Integer lvyouluxianDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：商家
	 */
    public Integer getShangjiaId() {
        return shangjiaId;
    }


    /**
	 * 获取：商家
	 */

    public void setShangjiaId(Integer shangjiaId) {
        this.shangjiaId = shangjiaId;
    }
    /**
	 * 设置：路线名称
	 */
    public String getLvyouluxianName() {
        return lvyouluxianName;
    }


    /**
	 * 获取：路线名称
	 */

    public void setLvyouluxianName(String lvyouluxianName) {
        this.lvyouluxianName = lvyouluxianName;
    }
    /**
	 * 设置：路线编号
	 */
    public String getLvyouluxianUuidNumber() {
        return lvyouluxianUuidNumber;
    }


    /**
	 * 获取：路线编号
	 */

    public void setLvyouluxianUuidNumber(String lvyouluxianUuidNumber) {
        this.lvyouluxianUuidNumber = lvyouluxianUuidNumber;
    }
    /**
	 * 设置：路线照片
	 */
    public String getLvyouluxianPhoto() {
        return lvyouluxianPhoto;
    }


    /**
	 * 获取：路线照片
	 */

    public void setLvyouluxianPhoto(String lvyouluxianPhoto) {
        this.lvyouluxianPhoto = lvyouluxianPhoto;
    }
    /**
	 * 设置：出发地点
	 */
    public String getLvyouluxianChufaAddress() {
        return lvyouluxianChufaAddress;
    }


    /**
	 * 获取：出发地点
	 */

    public void setLvyouluxianChufaAddress(String lvyouluxianChufaAddress) {
        this.lvyouluxianChufaAddress = lvyouluxianChufaAddress;
    }
    /**
	 * 设置：经过地点
	 */
    public String getLvyouluxianLuguoAddress() {
        return lvyouluxianLuguoAddress;
    }


    /**
	 * 获取：经过地点
	 */

    public void setLvyouluxianLuguoAddress(String lvyouluxianLuguoAddress) {
        this.lvyouluxianLuguoAddress = lvyouluxianLuguoAddress;
    }
    /**
	 * 设置：截止地点
	 */
    public String getLvyouluxianJiezhiAddress() {
        return lvyouluxianJiezhiAddress;
    }


    /**
	 * 获取：截止地点
	 */

    public void setLvyouluxianJiezhiAddress(String lvyouluxianJiezhiAddress) {
        this.lvyouluxianJiezhiAddress = lvyouluxianJiezhiAddress;
    }
    /**
	 * 设置：旅游路线类型
	 */
    public Integer getLvyouluxianTypes() {
        return lvyouluxianTypes;
    }


    /**
	 * 获取：旅游路线类型
	 */

    public void setLvyouluxianTypes(Integer lvyouluxianTypes) {
        this.lvyouluxianTypes = lvyouluxianTypes;
    }
    /**
	 * 设置：路线花费
	 */
    public Double getLvyouluxianNewMoney() {
        return lvyouluxianNewMoney;
    }


    /**
	 * 获取：路线花费
	 */

    public void setLvyouluxianNewMoney(Double lvyouluxianNewMoney) {
        this.lvyouluxianNewMoney = lvyouluxianNewMoney;
    }
    /**
	 * 设置：旅游路线热度
	 */
    public Integer getLvyouluxianClicknum() {
        return lvyouluxianClicknum;
    }


    /**
	 * 获取：旅游路线热度
	 */

    public void setLvyouluxianClicknum(Integer lvyouluxianClicknum) {
        this.lvyouluxianClicknum = lvyouluxianClicknum;
    }
    /**
	 * 设置：旅游路线介绍
	 */
    public String getLvyouluxianContent() {
        return lvyouluxianContent;
    }


    /**
	 * 获取：旅游路线介绍
	 */

    public void setLvyouluxianContent(String lvyouluxianContent) {
        this.lvyouluxianContent = lvyouluxianContent;
    }
    /**
	 * 设置：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }


    /**
	 * 获取：是否上架
	 */

    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getLvyouluxianDelete() {
        return lvyouluxianDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setLvyouluxianDelete(Integer lvyouluxianDelete) {
        this.lvyouluxianDelete = lvyouluxianDelete;
    }
    /**
	 * 设置：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
