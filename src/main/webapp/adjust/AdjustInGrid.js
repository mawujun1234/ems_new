Ext.define('Ems.adjust.AdjustInGrid',{
	extend:'Ext.grid.Panel',
	requires: [
	     'Ems.adjust.Adjust'
	],
	columnLines :true,
	stripeRows:true,
	viewConfig:{
		stripeRows:true,
		enableTextSelection:true,
		listeners:{
			refresh:function(){
				//this.select(0);
			}
		}
	},
	initComponent: function () {
      var me = this;
      me.columns=[
      	Ext.create('Ext.grid.RowNumberer'),
		{dataIndex:'id',text:'单号',width:130},
		{dataIndex:'status_name',text:'状态',width:60},
		{dataIndex:'adjustType_name',text:'类型',width:60},
		{dataIndex: 'str_out_name',text: '出库仓库'},
    	{dataIndex: 'str_in_name',text: '入库仓库'},
    	{dataIndex:'str_out_date',text:'出库时间',width:160},
    	{dataIndex:'memo',text:'备注',flex:1}
      ];
      
	  me.store=Ext.create('Ext.data.Store',{
			autoSync:false,
			pageSize:50,
			model: 'Ems.adjust.Adjust',
			autoLoad:true,
			proxy:{
				type:'ajax',
				actionMethods:{read:'POST'},
				url:Ext.ContextPath+'/adjust/query4InStr.do',
				reader:{
					type:'json',
					rootProperty:'root'
				}
			}
	  });
	  
      me.dockedItems= [{
	        xtype: 'pagingtoolbar',
	        store: me.store,  
	        dock: 'bottom',
	        displayInfo: true
	  }];
	  
	  me.tbar=	[{
			text: '刷新',
			itemId:'reload',
			//disabled:me.disabledAction,
			handler: function(btn){
				var grid=btn.up("grid");
				grid.getStore().reload();
				//grid.getStore().loadPage(1);
			},
			iconCls: 'icon-refresh'
		}]
       
      me.callParent();
	}
});
