Ext.define('Ems.baseinfo.WorkUnitGrid',{
	extend:'Ext.grid.Panel',
	requires: [
	     'Ems.org.Org'
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
		//
        {xtype: 'rownumberer'},
		{dataIndex:'code',text:'编码',width:60},
		{dataIndex:'name',text:'名称',flex:1},
		{dataIndex:'state',text:'状态',width:50,renderer:function(value){
			if(value=='valid'){
				return "有效";
			} else {
				return "<span style='color:red'>无效</>";
			}
		}}
      ];
      
	  me.store=Ext.create('Ext.data.Store',{
			autoSync:false,
			pageSize:50,
			model: 'Ems.org.Org',
			autoLoad:true,
			proxy:{
				type: 'ajax',
			    url : Ext.ContextPath+'/org/queryWorkunits.do',
			    headers:{ 'Accept':'application/json;'},
			    actionMethods: { read: 'POST' },
			    extraParams:{limit:50},
			    reader:{
					type:'json',//如果没有分页，那么可以把后面三行去掉，而且后台只需要返回一个数组就行了
					rootProperty:'root',
					//root:'root',
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
			disabled:me.disabledAction,
			handler: function(btn){
				var grid=btn.up("grid");
				grid.getStore().reload();
			},
			iconCls: 'icon-refresh'
		}]
       
      me.callParent();
	}
});
