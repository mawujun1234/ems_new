package com.mawujun.org;

/**
 * 渠道类型
 * @author mawujun qq:16064988 mawujun1234@163.com
 *
 */
public enum OrgType {
	company("公司","icon-home"),department("部门","icon-sitemap"),workunit("作业单位","icon-truck")
	,store_build("在建仓库","icon-download-alt")
	,store_prepare("备品备件仓库","icon-download-alt")
	,repair_centre("维修中心","icon-wrench");
	
	private String name;
	private String iconCls;
	
	OrgType(String name,String iconCls){
		this.name=name;
		this.iconCls=iconCls;
	}

	public String getName() {
		return name;
	}

	public String getIconCls() {
		return iconCls;
	}
}
