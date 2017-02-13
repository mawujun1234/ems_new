Ext.require("Ems.report.TaskRepairReport");
Ext.onReady(function(){

	var date_start=Ext.create('Ext.form.field.Date',{
	  	fieldLabel: '创建时间',
	  	labelWidth:60,
	  	hidden:false,
	  	//editable:false,
	  	format:'Y-m-d',
	  	width:175,
        //name: 'str_out_date_start',
	  	editable:false,
        value:  Ext.Date.add(new Date(), Ext.Date.DAY, -7)
	});
	var date_end=Ext.create('Ext.form.field.Date',{
	  	fieldLabel: '到',
	  	hidden:false,
	  	format:'Y-m-d',
	  	//editable:false,
	  	labelWidth:15,
	  	width:155,
	  	editable:false,
        //name: 'str_out_date_end',
        value: new Date()
	});
	var pole_code=Ext.create('Ext.form.field.Text',{
		fieldLabel: '点位号',
		labelWidth:50,
		width:155,
		name: 'pole_code'
	})
	
	var hitchType_id=Ext.create('Ext.form.field.ComboBox',{
		fieldLabel: '故障类型',
		labelWidth:60,
		width:180,
	    queryMode: 'remote',
	    displayField: 'name',
	    valueField: 'id',
	    store:Ext.create('Ext.data.Store',{
	    	fields: ['id', 'name'],
	    	proxy:{
	    		url:Ext.ContextPath+'/hitchType/query.do',
	    		type:'ajax',
	    		reader:{
		    		type:'json',
		    		rootProperty:'root'
		    	}
	    	}
	    })
	});
	
	var query_button=Ext.create('Ext.button.Button',{
			text:'查询',
			margin:'0 0 0 5',
			iconCls:'icon-search',
			handler:function(){
				//store.load();
				store.loadPage(1);
			}
	});
	var exportPoles = new Ext.Action({
		    text: '导出',
		    //itemId:'reload',
		    margin :'0 0 0 10',
		    icon:'../icons/page_excel.png',
		    handler: function(){
		    	var me=this;
		    	var params=getParams();
				var pp=Ext.Object.toQueryString(params);
				window.open(Ext.ContextPath+"/report/taskrepair/exportRepairReport.do?"+pp, "_blank");
		    }
		});
	var toolbar=Ext.create('Ext.toolbar.Toolbar',{
		dock:'top',
		items:[date_start,date_end,pole_code,hitchType_id,query_button,exportPoles]
	});
	var dockedItems=[];
	dockedItems.push(toolbar);
	dockedItems.push({
		dock:'top',
		xtype:'toolbar',
		items:[{
			xtype:'label',
			html:'总耗时=完成时间-任务下发时间,修复耗时=提交时间-任务下发时间,超时=总耗时-超时设置'
		}]
	});
	
	
	function getParams(){
		var params={
		    date_start:date_start.getRawValue(),
		    date_end:date_end.getRawValue(),
		    pole_code:pole_code.getValue(),
		    hitchType_id:hitchType_id.getValue()
		}
		return params;
	}
	var store=Ext.create('Ext.data.Store',{
			autoSync:false,
			pageSize:50,
			model: 'Ems.report.TaskRepairReport',
			autoLoad:false,
			proxy:{
				type:'ajax',
				actionMethods: {
			        create : 'POST',
			        read   : 'POST',
			        update : 'POST',
			        destroy: 'POST'
			    },
				url:Ext.ContextPath+'/report/taskrepair/queryRepairReport.do',
				reader:{
					type:'json',
					rootProperty:'root'
				}
			}
	});
	store.on("beforeload",function(store){
		store.getProxy().extraParams=getParams();
	});
	dockedItems.push({
	        xtype: 'pagingtoolbar',
	        store: store,  
	        dock: 'bottom',
	        displayInfo: true
	  	});		
	var grid=Ext.create('Ext.grid.Panel',{
		columnLines :true,
		stripeRows:true,
		columns:[
			{xtype: 'rownumberer'},
			{dataIndex:'customer_name',text:'客户名称',width:160},
			{dataIndex:'pole_code',text:'点位编号',width:80},
			{dataIndex:'pole_name',text:'点位名称',width:160},
			{dataIndex:'workunit_name',text:'作业单位',width:120},
			{dataIndex:'memo',text:'故障现象'},
			{dataIndex:'hitchDate',text:'故障时间',width:150},
			{dataIndex:'createDate',text:'任务下发时间',width:150},
			{dataIndex:'startHandDate',text:'开始处理时间',width:150},
			{dataIndex:'submitDate',text:'提交时间',width:150},
			{dataIndex:'completeDate',text:'完成时间',width:150},
			{dataIndex:'usedTime',text:'总耗时'},
			{dataIndex:'repairUsedTime',text:'维修耗时'},
			{dataIndex:'result',text:'维修结果'},
			{dataIndex:'overtime',text:'超时',width:110},
			{dataIndex:'hitchType',text:'故障类型'},
			{dataIndex:'hitchReason',text:'故障原因'},
			{dataIndex:'handleMethod_name',text:'处理方法',width:140},
			{dataIndex:'handle_contact',text:'备注'}
	    ],
      	store:store,
      	//tbar:toolbar,
      	dockedItems: dockedItems
	  	 
	});
	



	
	var viewPort=Ext.create('Ext.container.Viewport',{
		layout:'fit',
		items:[grid],
		listeners:{
			render:function(){
				
			}
		}
	});

});