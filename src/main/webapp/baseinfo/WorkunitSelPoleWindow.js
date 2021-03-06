Ext.define('Ems.baseinfo.WorkunitSelPoleWindow', {
		extend:'Ext.window.Window',  
		layout:'border',
		requires:['Ems.baseinfo.Customer'],
		modal:true,
	    initComponent: function(){
	    	var me=this;
//	    	var customerStore = Ext.create('Ext.data.TreeStore', {//new Ext.data.TreeStore({
//				model: 'Ems.baseinfo.Customer',
//			    proxy: {
//			        type: 'ajax',
//			        url: Ext.ContextPath+'/customer/query.do'
////			        reader: {
////			        	type:'json',
////			            rootProperty: 'root'
////			        }
//			    }
//			    //autoLoad:true,
////			    listeners:{
////				    load:function(s,records,success,o,e){
////				    	if(success&&records.length>0 && customer_grid){
////				    		customer_grid.getSelectionModel( ).select(0);
////				    	}
////				    }
////			    }
//			    ,root: {
//				    expanded: true,
//				    text: "根节点",
//				    name:'111'
//				}
//			});
//			//customerStore.load();
//			var customer_grid=Ext.create('Ext.tree.Panel', {
//		    	region:'west',
//		    	width:230,
//		    	title:'客户信息',
//			    store: customerStore,
//			    useArrows: true,
//    			rootVisible: false,
//			    columns: [
//			    	
//			        {xtype:'treecolumn',dataIndex:'name',text:'名称',flex:1},
//			        {dataIndex:'type',text:'类型',xtype: 'numbercolumn', renderer:function(value){
//						if(value==0){
//							return "机关";
//						} else if(value==1) {
//							return "企业";
//						} else if(value==2){
//							return "区";
//						}
//					}}
//			    ],
//			    listeners:{
//			    	itemclick:function(row,r,i,e){
//			    		if(r){
//				    		customer_id=r.get('id');
//				    		customer_name=r.get('name');
//				    		//contactStore.load({params:{cid:cid}});
//				    		poleStore.getProxy().extraParams={customer_id:customer_id,filterContainArea:true};
//				    		poleStore.reload();
//				    		//poleStore.load({params:{customer_id:customer_id,filterContainArea:true}});
//			    		}
//			    	}
//			    }
//			});
			
//			var customer_grid=Ext.create('Ext.panel.Panel',{
//				html:'1111'
//			});

			var customer_grid=Ext.create('Ems.baseinfo.CustomerTreeGrid',{
				region:'west',
				width:230,
				split: true,
				collapsible: true,
				readOnly:true
			});
			customer_grid.on("itemclick",function(row,r,i,e){
				if(r){
				    customer_id=r.get('id');
				    customer_name=r.get('name');
				    //contactStore.load({params:{cid:cid}});
				    poleStore.getProxy().extraParams={
				    	customer_id:customer_id,
				    	workunit_id:me.workunit_id
				    }
				    
				    poleStore.reload();
				    //poleStore.load({params:{customer_id:customer_id,filterContainArea:true}});
			    }
			});
			
	    	
			var pole_pageSize=50;
			var poleStore = new Ext.data.JsonStore({
				model: 'Ems.baseinfo.Pole',
				pageSize:pole_pageSize,
			    proxy: {
			        type: 'ajax',
			        url: Ext.ContextPath+'/pole/queryPageFilteContain.do',
			        reader: {
			        	type:'json',
			            rootProperty: 'root'
			        }
			    }
			});
			var pole_grid=Ext.create('Ext.grid.Panel', {
				title:'客户点位',
			    //flex:1,
				split:true,
			    region:'center',
			    margin:'2px 0 0 0',
			    store: poleStore,
			    selModel:Ext.create('Ext.selection.CheckboxModel',{
			    	checkOnly:false
			    }),
			    dockedItems: [{
				        xtype: 'pagingtoolbar',
				        store: poleStore,  
				        dock: 'bottom',
				        displayInfo: true
				}],
			    columns: [
			    		{xtype: 'rownumberer'},
			    		{dataIndex:'code',text:'编号',width:80},
			            {dataIndex:'name',text:'点位名称',width:160},
				        {dataIndex:'province',text:'地址',flex:1,renderer:function(value,metaData ,record){
				      		return value+record.get("city")+record.get("area")+record.get("address")
				        }}
						//{dataIndex:'latitude',text:'经度'},
						//{dataIndex:'longitude',text:'纬度'}
			    ],
			    listeners:{
			    	deselect:function(row,record,i,o){
//			    		if(!record.get('cpid')){
//			    			poleStore.remove(record);
//			    		}
			    	}
			    }
			});
			me.pole_grid=pole_grid;
			
			this.items=[customer_grid,pole_grid];
	    	this.callParent();
	    }
});