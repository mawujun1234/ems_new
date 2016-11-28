Ext.require("y.org.Org");
Ext.require("y.org.OrgTree");
Ext.require("y.org.OrgForm");
Ext.onReady(function(){
	var tree=Ext.create('y.org.OrgTree',{
		title:'组织维护',
		width:400,
		split:true,
		collapsible : true,
		readOnly:true,
		region:'west'
	});
	var usergrid=Ext.create('y.org.PositionUserGrid',{
		title:'拥有的用户',
		region:'center'
	});
	
	var positionStoreGrid=Ext.create("y.permission.PositionOrgAccessGrid",{
		title:'仓库权限',
		listeners : {
			orgSelect : function(record, type) {
				var params = {
					position_id:window.selected_position.get("id"),
					org_id : record.get("org_id"),
					look : record.get("look"),
					edit : record.get("edit")
				};
				Ext.Ajax.request({
					url : Ext.ContextPath + "/position/selectOrg.do",
					params : params,
					method : 'POST',
					success : function(response) {
						record.commit();
					}

				});
			},
			orgDeselect : function(record, type) {
				var params = {
					position_id:window.selected_position.get("id"),
					org_id : record.get("org_id"),
					look : record.get("look"),
					edit : record.get("edit")
				};
				Ext.Ajax.request({
					url : Ext.ContextPath + "/position/deselectOrg.do",
					params : params,
					method : 'POST',
					success : function(response) {
						record.commit();
					}

				});
			}
		}
	});
	
	var positionWorkunitGrid=Ext.create("y.permission.PositionOrgAccessGrid",{
		title:'作业单位权限',
		
		listeners : {
			orgSelect : function(record, type) {
				var params = {
					position_id:window.selected_position.get("id"),
					org_id : record.get("org_id"),
					look : record.get("look"),
					edit : record.get("edit")
				};
				Ext.Ajax.request({
					url : Ext.ContextPath + "/position/selectOrg.do",
					params : params,
					method : 'POST',
					success : function(response) {
						record.commit();
					}

				});
			},
			orgDeselect : function(record, type) {
				var params = {
					position_id:window.selected_position.get("id"),
					org_id : record.get("org_id"),
					look : record.get("look"),
					edit : record.get("edit")
				};
				Ext.Ajax.request({
					url : Ext.ContextPath + "/position/deselectOrg.do",
					params : params,
					method : 'POST',
					success : function(response) {
						record.commit();
					}

				});
			}
		}
	});
	
	var tabpanel=Ext.create('Ext.tab.Panel',{
		region:'center',
		items:[positionStoreGrid,positionWorkunitGrid,usergrid],
		listeners:{
	    	render:function(tabpanel){
	    		tabpanel.mask();
	    	}
	    }
	});
	var viewPort=Ext.create('Ext.container.Viewport',{
		layout:'border',
		items:[tree,tabpanel]
	});
	
	tree.on("itemclick",function( view, record, item, index, e, eOpts ){
	
		if(record.get("type")=="position"){		
			tabpanel.unmask();
		} else {
			tabpanel.mask();
			return;
		}

		window.selected_position=record;
		usergrid.getStore().getProxy().extraParams=Ext.apply(usergrid.getStore().getProxy().extraParams,{
			"params['position_id']":record.get("id"),
			"params['org_id']":record.get("org_id")
		});
		usergrid.getStore().reload();

		//刷新整颗权限树
		positionWorkunitGrid.getStore().getProxy().extraParams=Ext.apply(positionWorkunitGrid.getStore().getProxy().extraParams,{
			orgType:'workunit',
			position_id:record.get("id")
		});
		positionWorkunitGrid.getStore().reload();
		
		positionStoreGrid.getStore().getProxy().extraParams=Ext.apply(positionStoreGrid.getStore().getProxy().extraParams,{
			orgType:'store',
			position_id:record.get("id")
		});
		positionStoreGrid.getStore().reload();
	});



});