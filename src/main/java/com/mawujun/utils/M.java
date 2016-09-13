package com.mawujun.utils;
public final class M {
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
		public static final String remark="user.remark";
			
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
		public static final String remark="user.remark";
			
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
	public static final String remark="remark";
}
}
