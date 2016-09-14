Ext.require("y.org.Org");
Ext.require("y.org.OrgTree");
Ext.require("y.org.OrgForm");
Ext.onReady(function(){
	var tree=Ext.create('y.org.OrgTree',{
		title:'组织维护',
		width:400,
		split:true,
		collapsible : true,
		region:'west'
	});
	var usergrid=Ext.create('y.org.PositionUserGrid',{
		title:'用户维护',
		region:'center'
	});
	
	var positionStoreGrid=Ext.create("y.org.PositionStoreGrid",{
		title:'仓库权限',
		listeners : {
			storeSelect : function(record, type) {
				var params = {
					position_id:window.selected_position.get("id"),
					org_id : record.get("org_id"),
					look : record.get("look"),
					edit : record.get("edit")
				};
				Ext.Ajax.request({
					url : Ext.ContextPath + "/position/selectStore.do",
					params : params,
					method : 'POST',
					success : function(response) {
						record.commit();
					}

				});
			},
			storeDeselect : function(record, type) {
				var params = {
					position_id:window.selected_position.get("id"),
					org_id : record.get("org_id"),
					look : record.get("look"),
					edit : record.get("edit")
				};
				Ext.Ajax.request({
					url : Ext.ContextPath + "/position/deselectStore.do",
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
		items:[usergrid,positionStoreGrid],
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
		//alert(record.get("type"));
		if(window.selected_position==record){
			return;
		}
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
		positionStoreGrid.getStore().getProxy().extraParams=Ext.apply(positionStoreGrid.getStore().getProxy().extraParams,{
			position_id:record.get("id")
		});
		positionStoreGrid.getStore().reload();
	});



});