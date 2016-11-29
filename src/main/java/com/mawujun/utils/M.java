package com.mawujun.utils;
public final class M {
public static final class Brand {
	public static final String id="id";
	public static final String name="name";
	public static final String status="status";
}
public static final class Customer {
	public static final String name="name";
	public static final String type="type";
	public static final String parent_id="parent_id";
	public static final String memo="memo";
	public static final String status="status";
	public static final String id="id";
}
public static final class CustomerContact {
	public static final String contact="contact";
	public static final String phone="phone";
	public static final String mobile="mobile";
	public static final String position="position";
	public static final String email="email";
	public static final String fax="fax";
	public static final String address="address";
	public static final String postcode="postcode";
	public static final String customer_id="customer_id";
	public static final String id="id";
}
public static final class Equipment {
	public static final String ecode="ecode";
	public static final String subtype_id="subtype_id";
	public static final String prod_id="prod_id";
	public static final String brand_id="brand_id";
	public static final String supplier_id="supplier_id";
	public static final String style="style";
	public static final String orderlist_id="orderlist_id";
	public static final String isnew="isnew";
	public static final String memo="memo";
	public static final String status="status";
	public static final String place="place";
	public static final String value_original="value_original";
	public static final String value_net="value_net";
	public static final String first_install_date="first_install_date";
	public static final String fisData="fisData";
	public static final String last_installIn_id="last_installIn_id";
	public static final String last_borrow_id="last_borrow_id";
	public static final String last_workunit_id="last_workunit_id";
	public static final String last_install_date="last_install_date";
	public static final String last_task_id="last_task_id";
	public static final String last_pole_id="last_pole_id";
	public static final String currt_task_id="currt_task_id";
}
public static final class EquipmentCycle {
	public static final String ecode="ecode";
	public static final String operateDate="operateDate";
	public static final String operater_id="operater_id";
	public static final String operater_name="operater_name";
	public static final String operater_ipAddr="operater_ipAddr";
	public static final String operateType="operateType";
	public static final String type_id="type_id";
	public static final String source_id="source_id";
	public static final String source_name="source_name";
	public static final String targetType="targetType";
	public static final String target_id="target_id";
	public static final String target_name="target_name";
	public static final String memo="memo";
	public static final String id="id";
}
public static final class EquipmentPole {
	public static final String ecode="ecode";
	public static final String pole_id="pole_id";
	public static final String num="num";
	public static final String inDate="inDate";
	public static final String type="type";
	public static final String type_id="type_id";
	public static final String from_id="from_id";
}
public static final class EquipmentProd {
	public static final String id="id";
	public static final String name="name";
	public static final String status="status";
	public static final String subtype_id="subtype_id";
	public static final String memo="memo";
	public static final String unit="unit";
	public static final String spec="spec";
	public static final String style="style";
	public static final String brand_id="brand_id";
	public static final String quality_month="quality_month";
	public static final String lock_style="lock_style";
	public static final String id_suffix="id_suffix";
	public static final String depreci_year="depreci_year";
}
public static final class EquipmentRepair {
	public static final String ecode="ecode";
	public static final String repair_id="repair_id";
	public static final String num="num";
	public static final String type="type";
	public static final String type_id="type_id";
	public static final String from_id="from_id";
	public static final String inDate="inDate";
}
public static final class EquipmentStore {
	public static final String ecode="ecode";
	public static final String store_id="store_id";
	public static final String num="num";
	public static final String type="type";
	public static final String type_id="type_id";
	public static final String from_id="from_id";
	public static final String inDate="inDate";
}
public static final class EquipmentSubtype {
	/**
	* 这里一般是集合属性，返回的是prodes
	*/
	public static final String prodes="prodes";
	public static final String id="id";
	public static final String name="name";
	public static final String status="status";
	public static final String parent_id="parent_id";
	public static final String memo="memo";
	public static final String leaf="leaf";
}
public static final class EquipmentType {
	/**
	* 这里一般是集合属性，返回的是subtypes
	*/
	public static final String subtypes="subtypes";
	public static final String id="id";
	public static final String name="name";
	public static final String status="status";
	public static final String parent_id="parent_id";
	public static final String memo="memo";
	public static final String leaf="leaf";
}
public static final class EquipmentWorkunit {
	public static final String ecode="ecode";
	public static final String workunit_id="workunit_id";
	public static final String num="num";
	public static final String inDate="inDate";
	public static final String type="type";
	public static final String type_id="type_id";
	public static final String from_id="from_id";
	public static final String project_id="project_id";
}
public static final class Pole {
	public static final String code="code";
	public static final String name="name";
	public static final String province="province";
	public static final String city="city";
	public static final String area="area";
	public static final String address="address";
	public static final String poleType="poleType";
	public static final String longitude="longitude";
	public static final String latitude="latitude";
	public static final String longitude_orgin="longitude_orgin";
	public static final String latitude_orgin="latitude_orgin";
	public static final String lngLatIsTrans="lngLatIsTrans";
	public static final String customer_id="customer_id";
	public static final String workunit_id="workunit_id";
	public static final String project_id="project_id";
	public static final String status="status";
	public static final String id="id";
}
public static final class Project {
	public static final String name="name";
	public static final String sname="sname";
	public static final String memo="memo";
	public static final String status="status";
	public static final String id="id";
}
public static final class Supplier {
	public static final String id="id";
	public static final String name="name";
	public static final String sname="sname";
	public static final String website="website";
	public static final String memo="memo";
	public static final String status="status";
}
public static final class SupplierContact {
	public static final String contact="contact";
	public static final String phone="phone";
	public static final String mobile="mobile";
	public static final String position="position";
	public static final String email="email";
	public static final String fax="fax";
	public static final String address="address";
	public static final String postcode="postcode";
	public static final String supplier_id="supplier_id";
	public static final String id="id";
}
public static final class Org {
	public static final String id="id";
	public static final String code="code";
	public static final String name="name";
	public static final String name_short="name_short";
	public static final String orgType="orgType";
	public static final String state="state";
	public static final String sort="sort";
}
public static final class OrgOrg {
	 /**
	 * 返回关联对象的属性，，以对象关联的方式(a.b这种形式)，只有一些基本属性，层级不再往下了
	 */
	public static final class parent {
		public static final String id="parent.id";
		public static final String code="parent.code";
		public static final String name="parent.name";
		public static final String name_short="parent.name_short";
		public static final String orgType="parent.orgType";
		public static final String state="parent.state";
		public static final String sort="parent.sort";
			
	    /**
	    * 返回的是关联类的属性名称，主要用于属性过滤的时候
	    */
	    public static String name(){ 
		    return "parent";
	    }
	}
	 /**
	 * 返回关联对象的属性，，以对象关联的方式(a.b这种形式)，只有一些基本属性，层级不再往下了
	 */
	public static final class child {
		public static final String id="child.id";
		public static final String code="child.code";
		public static final String name="child.name";
		public static final String name_short="child.name_short";
		public static final String orgType="child.orgType";
		public static final String state="child.state";
		public static final String sort="child.sort";
			
	    /**
	    * 返回的是关联类的属性名称，主要用于属性过滤的时候
	    */
	    public static String name(){ 
		    return "child";
	    }
	}
	public static final String dim="dim";
	public static final String parent_reportCode="parent_reportCode";
	public static final String parent_reportLevel="parent_reportLevel";
	public static final String child_reportCode="child_reportCode";
	public static final String child_reportLevel="child_reportLevel";
}
public static final class Position {
	public static final String id="id";
	public static final String name="name";
	public static final String remark="remark";
	public static final String org_id="org_id";
	public static final String positionType_id="positionType_id";
}
public static final class PositionOrgAccess {
	 /**
	 * 返回关联对象的属性，，以对象关联的方式(a.b这种形式)，只有一些基本属性，层级不再往下了
	 */
	public static final class position {
		public static final String id="position.id";
		public static final String name="position.name";
		public static final String remark="position.remark";
		public static final String org_id="position.org_id";
		public static final String positionType_id="position.positionType_id";
			
	    /**
	    * 返回的是关联类的属性名称，主要用于属性过滤的时候
	    */
	    public static String name(){ 
		    return "position";
	    }
	}
	 /**
	 * 返回关联对象的属性，，以对象关联的方式(a.b这种形式)，只有一些基本属性，层级不再往下了
	 */
	public static final class org {
		public static final String id="org.id";
		public static final String code="org.code";
		public static final String name="org.name";
		public static final String name_short="org.name_short";
		public static final String orgType="org.orgType";
		public static final String state="org.state";
		public static final String sort="org.sort";
			
	    /**
	    * 返回的是关联类的属性名称，主要用于属性过滤的时候
	    */
	    public static String name(){ 
		    return "org";
	    }
	}
	public static final String look="look";
	public static final String edit="edit";
}
public static final class PositionOrgUser {
	 /**
	 * 返回关联对象的属性，，以对象关联的方式(a.b这种形式)，只有一些基本属性，层级不再往下了
	 */
	public static final class position {
		public static final String id="position.id";
		public static final String name="position.name";
		public static final String remark="position.remark";
		public static final String org_id="position.org_id";
		public static final String positionType_id="position.positionType_id";
			
	    /**
	    * 返回的是关联类的属性名称，主要用于属性过滤的时候
	    */
	    public static String name(){ 
		    return "position";
	    }
	}
	 /**
	 * 返回关联对象的属性，，以对象关联的方式(a.b这种形式)，只有一些基本属性，层级不再往下了
	 */
	public static final class org {
		public static final String id="org.id";
		public static final String code="org.code";
		public static final String name="org.name";
		public static final String name_short="org.name_short";
		public static final String orgType="org.orgType";
		public static final String state="org.state";
		public static final String sort="org.sort";
			
	    /**
	    * 返回的是关联类的属性名称，主要用于属性过滤的时候
	    */
	    public static String name(){ 
		    return "org";
	    }
	}
	 /**
	 * 返回关联对象的属性，，以对象关联的方式(a.b这种形式)，只有一些基本属性，层级不再往下了
	 */
	public static final class user {
		public static final String id="user.id";
		public static final String name="user.name";
		public static final String loginName="user.loginName";
		public static final String pwd="user.pwd";
		public static final String phone="user.phone";
		public static final String mobile="user.mobile";
		public static final String email="user.email";
		public static final String remark="user.remark";
		public static final String state="user.state";
		public static final String canNotDel="user.canNotDel";
		public static final String temp_workunit="user.temp_workunit";
			
	    /**
	    * 返回的是关联类的属性名称，主要用于属性过滤的时候
	    */
	    public static String name(){ 
		    return "user";
	    }
	}
}
public static final class PositionType {
	public static final String id="id";
	public static final String name="name";
	public static final String remark="remark";
	public static final String canNotDel="canNotDel";
}
public static final class Menu {
	public static final String id="id";
	public static final String code="code";
	public static final String name="name";
	public static final String url="url";
	public static final String leaf="leaf";
	public static final String menuType="menuType";
	public static final String parent_id="parent_id";
	public static final String sort="sort";
	public static final String remark="remark";
}
public static final class Role {
	public static final String id="id";
	public static final String name="name";
	public static final String roleType="roleType";
	public static final String remark="remark";
	public static final String parent_id="parent_id";
}
public static final class RoleMenu {
	 /**
	 * 返回关联对象的属性，，以对象关联的方式(a.b这种形式)，只有一些基本属性，层级不再往下了
	 */
	public static final class menu {
		public static final String id="menu.id";
		public static final String code="menu.code";
		public static final String name="menu.name";
		public static final String url="menu.url";
		public static final String leaf="menu.leaf";
		public static final String menuType="menu.menuType";
		public static final String parent_id="menu.parent_id";
		public static final String sort="menu.sort";
		public static final String remark="menu.remark";
			
	    /**
	    * 返回的是关联类的属性名称，主要用于属性过滤的时候
	    */
	    public static String name(){ 
		    return "menu";
	    }
	}
	 /**
	 * 返回关联对象的属性，，以对象关联的方式(a.b这种形式)，只有一些基本属性，层级不再往下了
	 */
	public static final class role {
		public static final String id="role.id";
		public static final String name="role.name";
		public static final String roleType="role.roleType";
		public static final String remark="role.remark";
		public static final String parent_id="role.parent_id";
			
	    /**
	    * 返回的是关联类的属性名称，主要用于属性过滤的时候
	    */
	    public static String name(){ 
		    return "role";
	    }
	}
}
public static final class RoleUser {
	 /**
	 * 返回关联对象的属性，，以对象关联的方式(a.b这种形式)，只有一些基本属性，层级不再往下了
	 */
	public static final class user {
		public static final String id="user.id";
		public static final String name="user.name";
		public static final String loginName="user.loginName";
		public static final String pwd="user.pwd";
		public static final String phone="user.phone";
		public static final String mobile="user.mobile";
		public static final String email="user.email";
		public static final String remark="user.remark";
		public static final String state="user.state";
		public static final String canNotDel="user.canNotDel";
		public static final String temp_workunit="user.temp_workunit";
			
	    /**
	    * 返回的是关联类的属性名称，主要用于属性过滤的时候
	    */
	    public static String name(){ 
		    return "user";
	    }
	}
	 /**
	 * 返回关联对象的属性，，以对象关联的方式(a.b这种形式)，只有一些基本属性，层级不再往下了
	 */
	public static final class role {
		public static final String id="role.id";
		public static final String name="role.name";
		public static final String roleType="role.roleType";
		public static final String remark="role.remark";
		public static final String parent_id="role.parent_id";
			
	    /**
	    * 返回的是关联类的属性名称，主要用于属性过滤的时候
	    */
	    public static String name(){ 
		    return "role";
	    }
	}
}
public static final class User {
	public static final String id="id";
	public static final String name="name";
	public static final String loginName="loginName";
	public static final String pwd="pwd";
	public static final String phone="phone";
	public static final String mobile="mobile";
	public static final String email="email";
	public static final String remark="remark";
	public static final String state="state";
	public static final String canNotDel="canNotDel";
	public static final String temp_workunit="temp_workunit";
}
}
