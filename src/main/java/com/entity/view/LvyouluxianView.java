package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.LvyouluxianEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 旅游路线
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("lvyouluxian")
public class LvyouluxianView extends LvyouluxianEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 旅游路线类型的值
	*/
	@ColumnInfo(comment="旅游路线类型的字典表值",type="varchar(200)")
	private String lvyouluxianValue;
	/**
	* 是否上架的值
	*/
	@ColumnInfo(comment="是否上架的字典表值",type="varchar(200)")
	private String shangxiaValue;

	//级联表 旅游公司
		/**
		* 旅游公司名称
		*/

		@ColumnInfo(comment="旅游公司名称",type="varchar(200)")
		private String shangjiaName;
		/**
		* 联系方式
		*/

		@ColumnInfo(comment="联系方式",type="varchar(200)")
		private String shangjiaPhone;
		/**
		* 邮箱
		*/

		@ColumnInfo(comment="邮箱",type="varchar(200)")
		private String shangjiaEmail;
		/**
		* 营业执照展示
		*/

		@ColumnInfo(comment="营业执照展示",type="varchar(200)")
		private String shangjiaPhoto;
		/**
		* 旅游公司信用类型
		*/
		@ColumnInfo(comment="旅游公司信用类型",type="int(11)")
		private Integer shangjiaXingjiTypes;
			/**
			* 旅游公司信用类型的值
			*/
			@ColumnInfo(comment="旅游公司信用类型的字典表值",type="varchar(200)")
			private String shangjiaXingjiValue;
		/**
		* 现有余额
		*/
		@ColumnInfo(comment="现有余额",type="decimal(10,2)")
		private Double newMoney;
		/**
		* 旅游公司介绍
		*/

		@ColumnInfo(comment="旅游公司介绍",type="text")
		private String shangjiaContent;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer shangjiaDelete;



	public LvyouluxianView() {

	}

	public LvyouluxianView(LvyouluxianEntity lvyouluxianEntity) {
		try {
			BeanUtils.copyProperties(this, lvyouluxianEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
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
	//当前表的
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


	//级联表的get和set 旅游公司

		/**
		* 获取： 旅游公司名称
		*/
		public String getShangjiaName() {
			return shangjiaName;
		}
		/**
		* 设置： 旅游公司名称
		*/
		public void setShangjiaName(String shangjiaName) {
			this.shangjiaName = shangjiaName;
		}

		/**
		* 获取： 联系方式
		*/
		public String getShangjiaPhone() {
			return shangjiaPhone;
		}
		/**
		* 设置： 联系方式
		*/
		public void setShangjiaPhone(String shangjiaPhone) {
			this.shangjiaPhone = shangjiaPhone;
		}

		/**
		* 获取： 邮箱
		*/
		public String getShangjiaEmail() {
			return shangjiaEmail;
		}
		/**
		* 设置： 邮箱
		*/
		public void setShangjiaEmail(String shangjiaEmail) {
			this.shangjiaEmail = shangjiaEmail;
		}

		/**
		* 获取： 营业执照展示
		*/
		public String getShangjiaPhoto() {
			return shangjiaPhoto;
		}
		/**
		* 设置： 营业执照展示
		*/
		public void setShangjiaPhoto(String shangjiaPhoto) {
			this.shangjiaPhoto = shangjiaPhoto;
		}
		/**
		* 获取： 旅游公司信用类型
		*/
		public Integer getShangjiaXingjiTypes() {
			return shangjiaXingjiTypes;
		}
		/**
		* 设置： 旅游公司信用类型
		*/
		public void setShangjiaXingjiTypes(Integer shangjiaXingjiTypes) {
			this.shangjiaXingjiTypes = shangjiaXingjiTypes;
		}


			/**
			* 获取： 旅游公司信用类型的值
			*/
			public String getShangjiaXingjiValue() {
				return shangjiaXingjiValue;
			}
			/**
			* 设置： 旅游公司信用类型的值
			*/
			public void setShangjiaXingjiValue(String shangjiaXingjiValue) {
				this.shangjiaXingjiValue = shangjiaXingjiValue;
			}

		/**
		* 获取： 现有余额
		*/
		public Double getNewMoney() {
			return newMoney;
		}
		/**
		* 设置： 现有余额
		*/
		public void setNewMoney(Double newMoney) {
			this.newMoney = newMoney;
		}

		/**
		* 获取： 旅游公司介绍
		*/
		public String getShangjiaContent() {
			return shangjiaContent;
		}
		/**
		* 设置： 旅游公司介绍
		*/
		public void setShangjiaContent(String shangjiaContent) {
			this.shangjiaContent = shangjiaContent;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getShangjiaDelete() {
			return shangjiaDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setShangjiaDelete(Integer shangjiaDelete) {
			this.shangjiaDelete = shangjiaDelete;
		}


	@Override
	public String toString() {
		return "LvyouluxianView{" +
			", lvyouluxianValue=" + lvyouluxianValue +
			", shangxiaValue=" + shangxiaValue +
			", shangjiaName=" + shangjiaName +
			", shangjiaPhone=" + shangjiaPhone +
			", shangjiaEmail=" + shangjiaEmail +
			", shangjiaPhoto=" + shangjiaPhoto +
			", newMoney=" + newMoney +
			", shangjiaContent=" + shangjiaContent +
			", shangjiaDelete=" + shangjiaDelete +
			"} " + super.toString();
	}
}
