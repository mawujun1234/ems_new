Ext.define('y.permission.RoleUserGrid',{
	extend:'Ext.grid.Panel',
	requires: [
	     'y.permission.User',
	     'y.permission.UserForm',
	     'y.org.SelPositionUserWindow'
	],
	columnLines :true,
	stripeRows:true,

	initComponent: function () {
      var me = this;
      me.columns=[
      	{xtype: 'rownumberer'},
		{dataIndex:'name',header:'姓名'
        },
		{dataIndex:'loginName',header:'登录名'
        },
		{dataIndex:'pwd',header:'密码'
        },
		{dataIndex:'remark',header:'备注'
        }
      ];
      
	  me.store=Ext.create('Ext.data.Store',{
			autoSync:false,
			pageSize:50,
			autoLoad:false,
			model: 'y.permission.User',
			proxy:{
				type: 'ajax',
			    url : Ext.ContextPath+'/user/queryByRole.do',
			    headers:{ 'Accept':'application/json;'},
			    actionMethods: { read: 'POST' },
			    extraParams:{limit:50},
			    reader:{
					type:'json',
					rootProperty:'root',
					successProperty:'success',
					totalProperty:'total'		
				}
			}
	  });
	  me.dockedItems=[];
      me.dockedItems.push({
	        xtype: 'pagingtoolbar',
	        store: me.store,  
	        dock: 'bottom',
	        displayInfo: true
	  });
	  
	  me.dockedItems.push({
	  	xtype: 'toolbar',
	  	dock:'top',
	  	//enableOverflow:true,
		items:[
			{
                xtype: 'textfield',
				itemId:'name',
                fieldLabel: '姓名',
                labelWidth:40,
                width:150,
                selectOnFocus:true 
            },
			{
                xtype: 'textfield',
				itemId:'loginName',
                fieldLabel: '登录名',
                labelWidth:50,
                width:150,
                selectOnFocus:true 
            },
	    	{
            	text:'查询',
            	iconCls:'icon-search',
            	handler:function(btn){
            		var grid=btn.up("grid");
	            	grid.getStore().getProxy().extraParams=Ext.apply(grid.getStore().getProxy().extraParams,{
						"params['name']":grid.down("#name").getValue(),
						"params['loginName']":grid.down("#loginName").getValue()
	                });
            		grid.getStore().reload();
            	}
            }
	  	]
	  });
	  
	  me.dockedItems.push({
	  		xtype: 'toolbar',
	  		dock:'top',
		  	items:[{
				text: '从职位添加',
				//itemId:'create',
				handler: function(btn){
					me.onCreateByPosition();
				},
				iconCls: 'icon-plus'
			},
			{
				text: '选择用户',
				//itemId:'create',
				handler: function(btn){
					me.onCreateByUser();
				},
				iconCls: 'icon-plus'
			},
			{
			    text: '新增',
			    //itemId:'update',
			    handler: function(){
			    	me.onCreate();
					
			    },
			   iconCls: 'icon-plus'
			},
				{
			    text: '删除',
			    itemId:'destroy',
			    handler: function(){
			    	me.onDelete();    
			    },
			    iconCls: 'icon-trash'
			},{
				text: '刷新',
				itemId:'reload',
				disabled:me.disabledAction,
				handler: function(btn){
					var grid=btn.up("grid");
					grid.getStore().reload();
				},
				iconCls: 'icon-refresh'
			}]
		});
	  
       
      me.callParent();
	},
	onCreateByPosition:function(){
    	var me=this;
    	
    	var seluserWindow=Ext.create('y.org.SelPositionUserWindow',{
    		listeners:{
    			userdbclick:function(user){
    				Ext.Ajax.request({
						url:Ext.ContextPath+'/user/addToRole.do',
						params:{
							user_id:user.get("id"),
							role_id:window.selected_role.get("id")
						},
						headers:{ 'Accept':'application/json;'},
						success:function(){
							//button.up('window').close();
							me.getStore().reload();
						}
					});
    			}
    		}
    	});
    	seluserWindow.show();
    },
    onCreateByUser:function(){
    	var me=this;
    	var seluserWindow=Ext.create('y.permission.SelUserWindow',{
    		listeners:{
    			userdbclick:function(user){
    				Ext.Ajax.request({
						url:Ext.ContextPath+'/user/addToRole.do',
						params:{
							user_id:user.get("id"),
							role_id:window.selected_role.get("id")
						},
						headers:{ 'Accept':'application/json;'},
						success:function(){
							//button.up('window').close();
							me.getStore().reload();
						}
					});
    			}
    		}
    	});
    	seluserWindow.show();
    },
    
	onCreate:function(){
    	var me=this;
		var child=Ext.create('y.permission.User',{
			role_id:window.selected_role.get("id")
		});
		child.set("id",null);
		
		var formpanel=Ext.create('y.permission.UserForm',{});
		formpanel.loadRecord(child);
		
		formpanel.getForm().getRecord().getProxy( ).extraParams={
			role_id:window.selected_role.get("id")
		}
		
    	var win=Ext.create('Ext.window.Window',{
    		layout:'fit',
    		title:'新增',
    		modal:true,
    		width:400,
    		height:300,
    		closeAction:'hide',
    		items:[formpanel],
    		listeners:{
    			close:function(){
    				me.getStore().reload();
    			}
    		}
    	});
    	win.show();
    },
    
    onDelete:function(){
    	var me=this;
    	var node=me.getSelectionModel( ).getLastSelected( );

		if(!node){
		    Ext.Msg.alert("消息","请先选择一行数据");	
			return;
		}
		
//		node.getProxy( ).extraParams={
//			role_id:window.selected_role.get("id")
//		}
		
		var parent=node.parentNode;
		Ext.Msg.confirm("删除",'确定要删除吗?', function(btn, text){
			if (btn == 'yes'){
				Ext.Ajax.request({
					url:Ext.ContextPath+'/user/deleteFromRole.do',
					params:{
						user_id:node.get("id"),
						role_id:window.selected_role.get("id")
					},
					headers:{ 'Accept':'application/json;'},
					success:function(){
						//button.up('window').close();
						me.getStore().reload();
					}
				});
				
			}
		});
    }
});
