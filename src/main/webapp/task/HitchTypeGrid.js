Ext.define('Ems.task.HitchTypeGrid',{
	extend:'Ext.grid.Panel',
	requires: [
	     'Ems.task.HitchType',
	     'Ems.task.HitchTypeForm'
	],
	columnLines :true,
	stripeRows:true,
	viewConfig:{
		stripeRows:true,
		listeners:{
			refresh:function(){
				//this.select(0);
			}
		}
	},
	initComponent: function () {
      var me = this;
      me.columns=[
		{dataIndex:'id',text:'id',xtype: 'numbercolumn', format:'0'},
		{dataIndex:'name',text:'name'}
      ];
      
	  me.store=Ext.create('Ext.data.Store',{
			autoSync:false,
			pageSize:50,
			model: 'Ems.task.HitchType',
			autoLoad:true,
			proxy:{
				type: 'ajax',
			    url : Ext.ContextPath+'/hitchType/query.do',
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
	  
//      me.dockedItems= [{
//	        xtype: 'pagingtoolbar',
//	        store: me.store,  
//	        dock: 'bottom',
//	        displayInfo: true
//	  }];
	  
	  me.initAction(); 
      me.callParent();
	},
		initAction:function(){
     	var me = this;
     	var actions=[];
     	
       var create = new Ext.Action({
		    text: '新建',
		    itemId:'create',
		    hidden:!Permision.canShow('hitchtype_create'),
		    disabled:me.disabledAction,
		    handler: function(b){
		    	me.onCreate(null,b);
		    },
		    iconCls: 'icon-plus'
		});
		//me.addAction(create);
		actions.push(create);
		var update = new Ext.Action({
		    text: '更新',
		    itemId:'update',
		    hidden:!Permision.canShow('hitchtype_update'),
		    disabled:me.disabledAction,
		    handler: function(b){
		    	me.onUpdate(null,b);
		    },
		    iconCls: 'icon-edit'
		});
		//me.addAction(create);
		actions.push(update);
		
		var destroy = new Ext.Action({
		    text: '删除',
		    itemId:'destroy',
		    hidden:true,
		    //hidden:!Permision.canShow('hitchtype_delete'),
		    disabled:me.disabledAction,
		    handler: function(){
		    	me.onDelete();    
		    },
		    iconCls: 'icon-trash'
		});
		//me.addAction(destroy);
		actions.push(destroy)
		
		var reload = new Ext.Action({
		    text: '刷新',
		    itemId:'reload',
		    handler: function(){
		    	me.onReload();
		    },
		    iconCls: 'icon-refresh'
		});
		//me.addAction(reload);
		actions.push(reload);

		me.tbar={
			itemId:'action_toolbar',
			layout: {
	               overflowHandler: 'Menu'
	        },
			items:actions
			//,autoScroll:true		
		};

    },
    onCreate:function(){
    	var me=this;

		var form=new Ems.task.HitchTypeForm({
			listeners:{
				saved:function(){
					win.close();
					me.getStore().reload();
					//me.tree.getStore().reload({node:parent});
				}
			}
		});
		
		var data=form.getValues();
		data.customer_id=me.customer_id;
		data.status=true;
		var record=new Ems.task.HitchType(data);    
		form.getForm().loadRecord(record);
		var win=new Ext.window.Window({
			items:[form],
			layout:'fit',
			title:'故障类型',
			closeAction:'destroy',
			width:300,
			height:200,
			modal:true
		});
		//form.win=win
		win.show();

    },
    onUpdate:function(){
    	var me=this;
    	
    	var record=me.getSelectionModel( ).getLastSelected( );
		if(!record){
			Ext.Msg.alert("消息","请先选择一条记录!");	
			return;
		}
		
		var form=new Ems.task.HitchTypeForm({
			listeners:{
				saved:function(){
					//form.updateRecord();
					win.close();
				}
			}
		});
		form.getForm().loadRecord(record);
		//var ids=record.get("id").split("_");
		//form.getForm().findField("id").setValue(ids[0]);
		var workunit=Ext.create('Ems.task.HitchType',{

		});
		
		var win=new Ext.window.Window({
			items:[form],
			layout:'fit',
			title:'故障类型',
			closeAction:'destroy',
			width:300,
			height:200,
			modal:true
		});
		//form.win=win
		win.show();	
		    	
		
    },
    onDelete:function(){
    	var me=this;
    	var record=me.getSelectionModel( ).getLastSelected( );

		if(!record){
		    Ext.Msg.alert("消息","请先选择类型");	
			return;
		}

		Ext.Msg.confirm("删除",'确定要删除吗?', function(btn, text){
				if (btn == 'yes'){
					Ext.Ajax.request({
						url:Ext.ContextPath+'/hitchType/deleteById.do',
						params:{
							id:record.get("id")
						},
						method:'POST',
						success:function(){
							//var parent=me.tree.getSelectionModel( ).getLastSelected( )||me.tree.getRootNode( );  
							//me.tree.getStore().reload({node:parent});
							
							me.getStore().remove(record);
						}
					});
			}
		});
    },
    onReload:function(){
    	var me=this;
    	me.getStore().reload();	      
    }
});
