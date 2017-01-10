Ext.define('Ems.task.HandleMethodGrid',{
	extend:'Ext.grid.Panel',
	requires: [
	     'Ems.task.HandleMethod'
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
		//{dataIndex:'id',text:'id'},
		{dataIndex:'name',text:'处理方法',flex:1}
      ];
      
	  me.store=Ext.create('Ext.data.Store',{
			autoSync:false,
			pageSize:50,
			model: 'Ems.task.HandleMethod',
			autoLoad:true,
			proxy:{
				type: 'ajax',
			    url : Ext.ContextPath+'/handleMethod/query.do',
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
	  
	  me.tbar=	[{
			text: '刷新',
			itemId:'reload',
			//disabled:me.disabledAction,
			handler: function(btn){
				var grid=btn.up("grid");
				grid.getStore().reload();
			},
			iconCls: 'icon-refresh'
		}]
       
      me.callParent();
	}
});
