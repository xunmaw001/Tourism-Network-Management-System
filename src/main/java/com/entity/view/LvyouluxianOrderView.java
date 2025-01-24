package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.LvyouluxianOrderEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 旅游路线订单
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("lvyouluxian_order")
public class LvyouluxianOrderView extends LvyouluxianOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 订单类型的值
	*/
	@ColumnInfo(comment="订单类型的字典表值",type="varchar(200)")
	private String lvyouluxianOrderValue;
	/**
	* 支付类型的值
	*/
	@ColumnInfo(comment="支付类型的字典表值",type="varchar(200)")
	private String lvyouluxianOrderPaymentValue;

	//级联表 旅游路线
					 
		/**
		* 旅游路线 的 商家
		*/
		@ColumnInfo(comment="商家",type="int(11)")
		private Integer lvyouluxianShangjiaId;
		/**
		* 路线名称
		*/

		@ColumnInfo(comment="路线名称",type="varchar(200)")
		private String lvyouluxianName;
		/**
		* 路线编号
		*/

		@ColumnInfo(comment="路线编号",type="varchar(200)")
		private String lvyouluxianUuidNumber;
		/**
		* 路线照片
		*/

		@ColumnInfo(comment="路线照片",type="varchar(200)")
		private String lvyouluxianPhoto;
		/**
		* 出发地点
		*/

		@ColumnInfo(comment="出发地点",type="varchar(200)")
		private String lvyouluxianChufaAddress;
		/**
		* 经过地点
		*/

		@ColumnInfo(comment="经过地点",type="varchar(200)")
		private String lvyouluxianLuguoAddress;
		/**
		* 截止地点
		*/

		@ColumnInfo(comment="截止地点",type="varchar(200)")
		private String lvyouluxianJiezhiAddress;
		/**
		* 旅游路线类型
		*/
		@ColumnInfo(comment="旅游路线类型",type="int(11)")
		private Integer lvyouluxianTypes;
			/**
			* 旅游路线类型的值
			*/
			@ColumnInfo(comment="旅游路线类型的字典表值",type="varchar(200)")
			private String lvyouluxianValue;
		/**
		* 路线花费
		*/
		@ColumnInfo(comment="路线花费",type="decimal(10,2)")
		private Double lvyouluxianNewMoney;
		/**
		* 旅游路线热度
		*/

		@ColumnInfo(comment="旅游路线热度",type="int(11)")
		private Integer lvyouluxianClicknum;
		/**
		* 旅游路线介绍
		*/

		@ColumnInfo(comment="旅游路线介绍",type="text")
		private String lvyouluxianContent;
		/**
		* 是否上架
		*/
		@ColumnInfo(comment="是否上架",type="int(11)")
		private Integer shangxiaTypes;
			/**
			* 是否上架的值
			*/
			@ColumnInfo(comment="是否上架的字典表值",type="varchar(200)")
			private String shangxiaValue;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer lvyouluxianDelete;
	//级联表 用户
		/**
		* 用户姓名
		*/

		@ColumnInfo(comment="用户姓名",type="varchar(200)")
		private String yonghuName;
		/**
		* 用户手机号
		*/

		@ColumnInfo(comment="用户手机号",type="varchar(200)")
		private String yonghuPhone;
		/**
		* 用户身份证号
		*/

		@ColumnInfo(comment="用户身份证号",type="varchar(200)")
		private String yonghuIdNumber;
		/**
		* 用户头像
		*/

		@ColumnInfo(comment="用户头像",type="varchar(200)")
		private String yonghuPhoto;
		/**
		* 余额
		*/
		@ColumnInfo(comment="余额",type="decimal(10,2)")
		private Double newMoney;
		/**
		* 电子邮箱
		*/

		@ColumnInfo(comment="电子邮箱",type="varchar(200)")
		private String yonghuEmail;



	public LvyouluxianOrderView() {

	}

	public LvyouluxianOrderView(LvyouluxianOrderEntity lvyouluxianOrderEntity) {
		try {
			BeanUtils.copyProperties(this, lvyouluxianOrderEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 订单类型的值
	*/
	public String getLvyouluxianOrderValue() {
		return lvyouluxianOrderValue;
	}
	/**
	* 设置： 订单类型的值
	*/
	public void setLvyouluxianOrderValue(String lvyouluxianOrderValue) {
		this.lvyouluxianOrderValue = lvyouluxianOrderValue;
	}
	//当前表的
	/**
	* 获取： 支付类型的值
	*/
	public String getLvyouluxianOrderPaymentValue() {
		return lvyouluxianOrderPaymentValue;
	}
	/**
	* 设置： 支付类型的值
	*/
	public void setLvyouluxianOrderPaymentValue(String lvyouluxianOrderPaymentValue) {
		this.lvyouluxianOrderPaymentValue = lvyouluxianOrderPaymentValue;
	}


	//级联表的get和set 旅游路线
		/**
		* 获取：旅游路线 的 商家
		*/
		public Integer getLvyouluxianShangjiaId() {
			return lvyouluxianShangjiaId;
		}
		/**
		* 设置：旅游路线 的 商家
		*/
		public void setLvyouluxianShangjiaId(Integer lvyouluxianShangjiaId) {
			this.lvyouluxianShangjiaId = lvyouluxianShangjiaId;
		}

		/**
		* 获取： 路线名称
		*/
		public String getLvyouluxianName() {
			return lvyouluxianName;
		}
		/**
		* 设置： 路线名称
		*/
		public void setLvyouluxianName(String lvyouluxianName) {
			this.lvyouluxianName = lvyouluxianName;
		}

		/**
		* 获取： 路线编号
		*/
		public String getLvyouluxianUuidNumber() {
			return lvyouluxianUuidNumber;
		}
		/**
		* 设置： 路线编号
		*/
		public void setLvyouluxianUuidNumber(String lvyouluxianUuidNumber) {
			this.lvyouluxianUuidNumber = lvyouluxianUuidNumber;
		}

		/**
		* 获取： 路线照片
		*/
		public String getLvyouluxianPhoto() {
			return lvyouluxianPhoto;
		}
		/**
		* 设置： 路线照片
		*/
		public void setLvyouluxianPhoto(String lvyouluxianPhoto) {
			this.lvyouluxianPhoto = lvyouluxianPhoto;
		}

		/**
		* 获取： 出发地点
		*/
		public String getLvyouluxianChufaAddress() {
			return lvyouluxianChufaAddress;
		}
		/**
		* 设置： 出发地点
		*/
		public void setLvyouluxianChufaAddress(String lvyouluxianChufaAddress) {
			this.lvyouluxianChufaAddress = lvyouluxianChufaAddress;
		}

		/**
		* 获取： 经过地点
		*/
		public String getLvyouluxianLuguoAddress() {
			return lvyouluxianLuguoAddress;
		}
		/**
		* 设置： 经过地点
		*/
		public void setLvyouluxianLuguoAddress(String lvyouluxianLuguoAddress) {
			this.lvyouluxianLuguoAddress = lvyouluxianLuguoAddress;
		}

		/**
		* 获取： 截止地点
		*/
		public String getLvyouluxianJiezhiAddress() {
			return lvyouluxianJiezhiAddress;
		}
		/**
		* 设置： 截止地点
		*/
		public void setLvyouluxianJiezhiAddress(String lvyouluxianJiezhiAddress) {
			this.lvyouluxianJiezhiAddress = lvyouluxianJiezhiAddress;
		}
		/**
		* 获取： 旅游路线类型
		*/
		public Integer getLvyouluxianTypes() {
			return lvyouluxianTypes;
		}
		/**
		* 设置： 旅游路线类型
		*/
		public void setLvyouluxianTypes(Integer lvyouluxianTypes) {
			this.lvyouluxianTypes = lvyouluxianTypes;
		}


			/**
			* 获取： 旅游路线类型的值
			*/
			public String getLvyouluxianValue() {
				return lvyouluxianValue;
			}
			/**
			* 设置： 旅游路线类型的值
			*/
			public void setLvyouluxianValue(String lvyouluxianValue) {
				this.lvyouluxianValue = lvyouluxianValue;
			}

		/**
		* 获取： 路线花费
		*/
		public Double getLvyouluxianNewMoney() {
			return lvyouluxianNewMoney;
		}
		/**
		* 设置： 路线花费
		*/
		public void setLvyouluxianNewMoney(Double lvyouluxianNewMoney) {
			this.lvyouluxianNewMoney = lvyouluxianNewMoney;
		}

		/**
		* 获取： 旅游路线热度
		*/
		public Integer getLvyouluxianClicknum() {
			return lvyouluxianClicknum;
		}
		/**
		* 设置： 旅游路线热度
		*/
		public void setLvyouluxianClicknum(Integer lvyouluxianClicknum) {
			this.lvyouluxianClicknum = lvyouluxianClicknum;
		}

		/**
		* 获取： 旅游路线介绍
		*/
		public String getLvyouluxianContent() {
			return lvyouluxianContent;
		}
		/**
		* 设置： 旅游路线介绍
		*/
		public void setLvyouluxianContent(String lvyouluxianContent) {
			this.lvyouluxianContent = lvyouluxianContent;
		}
		/**
		* 获取： 是否上架
		*/
		public Integer getShangxiaTypes() {
			return shangxiaTypes;
		}
		/**
		* 设置： 是否上架
		*/
		public void setShangxiaTypes(Integer shangxiaTypes) {
			this.shangxiaTypes = shangxiaTypes;
		}


			/**
			* 获取： 是否上架的值
			*/
			public String getShangxiaValue() {
				return shangxiaValue;
			}
			/**
			* 设置： 是否上架的值
			*/
			public void setShangxiaValue(String shangxiaValue) {
				this.shangxiaValue = shangxiaValue;
			}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getLvyouluxianDelete() {
			return lvyouluxianDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setLvyouluxianDelete(Integer lvyouluxianDelete) {
			this.lvyouluxianDelete = lvyouluxianDelete;
		}
	//级联表的get和set 用户

		/**
		* 获取： 用户姓名
		*/
		public String getYonghuName() {
			return yonghuName;
		}
		/**
		* 设置： 用户姓名
		*/
		public void setYonghuName(String yonghuName) {
			this.yonghuName = yonghuName;
		}

		/**
		* 获取： 用户手机号
		*/
		public String getYonghuPhone() {
			return yonghuPhone;
		}
		/**
		* 设置： 用户手机号
		*/
		public void setYonghuPhone(String yonghuPhone) {
			this.yonghuPhone = yonghuPhone;
		}

		/**
		* 获取： 用户身份证号
		*/
		public String getYonghuIdNumber() {
			return yonghuIdNumber;
		}
		/**
		* 设置： 用户身份证号
		*/
		public void setYonghuIdNumber(String yonghuIdNumber) {
			this.yonghuIdNumber = yonghuIdNumber;
		}

		/**
		* 获取： 用户头像
		*/
		public String getYonghuPhoto() {
			return yonghuPhoto;
		}
		/**
		* 设置： 用户头像
		*/
		public void setYonghuPhoto(String yonghuPhoto) {
			this.yonghuPhoto = yonghuPhoto;
		}

		/**
		* 获取： 余额
		*/
		public Double getNewMoney() {
			return newMoney;
		}
		/**
		* 设置： 余额
		*/
		public void setNewMoney(Double newMoney) {
			this.newMoney = newMoney;
		}

		/**
		* 获取： 电子邮箱
		*/
		public String getYonghuEmail() {
			return yonghuEmail;
		}
		/**
		* 设置： 电子邮箱
		*/
		public void setYonghuEmail(String yonghuEmail) {
			this.yonghuEmail = yonghuEmail;
		}


	@Override
	public String toString() {
		return "LvyouluxianOrderView{" +
			", lvyouluxianOrderValue=" + lvyouluxianOrderValue +
			", lvyouluxianOrderPaymentValue=" + lvyouluxianOrderPaymentValue +
			", lvyouluxianName=" + lvyouluxianName +
			", lvyouluxianUuidNumber=" + lvyouluxianUuidNumber +
			", lvyouluxianPhoto=" + lvyouluxianPhoto +
			", lvyouluxianChufaAddress=" + lvyouluxianChufaAddress +
			", lvyouluxianLuguoAddress=" + lvyouluxianLuguoAddress +
			", lvyouluxianJiezhiAddress=" + lvyouluxianJiezhiAddress +
			", lvyouluxianNewMoney=" + lvyouluxianNewMoney +
			", lvyouluxianClicknum=" + lvyouluxianClicknum +
			", lvyouluxianContent=" + lvyouluxianContent +
			", lvyouluxianDelete=" + lvyouluxianDelete +
			", yonghuName=" + yonghuName +
			", yonghuPhone=" + yonghuPhone +
			", yonghuIdNumber=" + yonghuIdNumber +
			", yonghuPhoto=" + yonghuPhoto +
			", newMoney=" + newMoney +
			", yonghuEmail=" + yonghuEmail +
			"} " + super.toString();
	}
}
