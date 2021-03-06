 Ext.require('Ems.task.FinishRepairForm') ;
Ext.define('Ems.task.TaskQueryGrid',{
	extend:'Ext.grid.Panel',
	requires: [
	     'Ems.task.Task'
	],
	columnLines :true,
	stripeRows:true,
	viewConfig:{
		stripeRows:true,
		enableTextSelection:true,
		listeners:{
			expandBody:function(rowNode, record, expandRow){
				Ext.Ajax.request({
					url:Ext.ContextPath+"/task/queryEquipList.do",
					params:{task_id:record.get("id")},
					success:function(response){
						var obj=Ext.decode(response.responseText);
						record.set("equipList",obj.root);
					}
				});
			}
		}
	},
	plugins:[{
        ptype: 'rowexpander',
        rowBodyTpl : new Ext.XTemplate(
            '' +
            '{equipList}' +
            ''
        )
    }],
	pageSize:50,

	initComponent: function () {
      var me = this;
      me.columns=[
        {dataIndex:'id',text:'任务编号',width:130},
		{dataIndex:'status_name',text:'状态',width:80,renderer:function(value,meta,record){
			if(record.get("status")=='submited'){
				return '<span style="color:red;">'+value+'</span>';
			}
			return value;
		}},
		{dataIndex:'type_name',text:'任务类型',width:80},
		{dataIndex:'pole_code',text:'点位编号',width:80},
		{dataIndex:'pole_name',text:'点位名称',width:180},
		{dataIndex:'workunit_name',text:'作业单位',width:160},
		{dataIndex:'hitchType',text:'故障类型'},
		{dataIndex:'hitchReason',text:'故障原因',width:150},
		{dataIndex:'createDate',text:'创建时间',width:150},
		{dataIndex:'submitDate',text:'提交时间',width:150},
		{dataIndex:'completeDate',text:'完成时间',width:150},
		
		{dataIndex:'customer_name',text:'所属客户',width:150},
		{dataIndex:'memo',text:'任务描述',width:180,renderer:function(value,metadata,record){
			metadata.tdAttr = "data-qtip='" + value+ "'";
		    return value;
		}},
		{dataIndex:'pole_address',text:'地址',width:260}
      ];

	  me.store=Ext.create('Ext.data.Store',{
			autoSync:false,
			pageSize:me.pageSize,
			model: 'Ems.task.Task',
			autoLoad:me.autoLoad1,
			proxy:{
				type:'ajax',
				actionMethods: { read: 'POST' },
				url:Ext.ContextPath+'/task/query.do',
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
      
//	  //双击，查看该任务涉及的设备情况
//	  me.on('itemdblclick',function(view, record, item, index){
//	  	
//	  });
	  
	 
      
	  me.initToolbar();
      me.callParent();
	},
	//初始化工具栏
	initToolbar:function(){
		var me=this;
		var customer_combox=Ext.create('Ext.form.field.ComboBox',{
	        fieldLabel: '客户名称',
	        labelAlign:'right',
            labelWidth:60,
            //width:250,
	        //xtype:'combobox',
	        //afterLabelTextTpl: Ext.required,
	        name: 'customer_id',
		    displayField: 'name',
		    valueField: 'id',
		    queryParam: 'name',
    		queryMode: 'remote',
    		triggerAction: 'query',
    		minChars:-1,
    		triggers : {
				foo : {
					cls : Ext.baseCSSPrefix + 'form-clear-trigger',
					weight : -1,
					handler : function() {
						var me = this;
						me.setValue('');
					}
				}
			},
	        //allowBlank: false,
	        store:Ext.create('Ext.data.Store', {
		    	fields: ['id', 'name'],
			    proxy:{
			    	type:'ajax',
			    	actionMethods: { read: 'POST' },
			    	//extraParams:{type:1,edit:true},
			    	url:Ext.ContextPath+"/customer/queryCombo.do",
			    	reader:{
			    		type:'json',
			    		rootProperty:'root'
			    	}
			    }
		   })
	    });
	    

	    
//	    var workunit_combox=Ext.create('Ext.form.field.ComboBox',{
//	        fieldLabel: '作业单位',
//	        labelAlign:'right',
//            labelWidth:55,
//            //width:250,
//	        //xtype:'combobox',
//	        //afterLabelTextTpl: Ext.required,
//	        name: 'workunit_id',
//		    displayField: 'name',
//		    valueField: 'id',
////		    queryParam: 'name',
////    		queryMode: 'remote',
////    		triggerAction: 'query',
////    		minChars:-1,
////		    trigger1Cls: Ext.baseCSSPrefix + 'form-clear-trigger',
////		    trigger2Cls: Ext.baseCSSPrefix + 'form-arrow-trigger',//'form-search-trigger',
////			onTrigger1Click : function(){
////			    var me = this;
////			    me.setValue('');
////			},
//	        //allowBlank: false,
//	        store:Ext.create('Ext.data.Store', {
//		    	fields: ['id', 'name'],
//			    proxy:{
//			    	type:'ajax',
//			    	//extraParams:{type:1,edit:true},
//			    	url:Ext.ContextPath+"/workUnit/queryCombo.do",
//			    	reader:{
//			    		type:'json',
//			    		root:'root'
//			    	}
//			    }
//		   })
//	    });
	    
	  var workunit_combox= Ext.create('Ems.baseinfo.WorkunitCombo', {
		edit:true,
		allowBlank : true,
		showBlank : true,
		fieldLabel : '<b>作业单位</b>'
	  });
	    
	  var task_type_combox=Ext.create('Ext.form.field.ComboBox',{
	        fieldLabel: '类型',
	        labelAlign:'right',
            labelWidth:40,
            width:120,
	        //xtype:'combobox',
	        //afterLabelTextTpl: Ext.required,
	        name: 'status',
		    displayField: 'name',
		    valueField: 'key',
	        //allowBlank: false,
	        store:Ext.create('Ext.data.Store', {
		    	fields: ['key', 'name'],
			    data:[{key:'',name:'无'},{key:'newInstall',name:'新安装'},{key:'repair',name:'维修维护'},{key:'patrol',name:'巡检'},{key:'check',name:'盘点'},{key:'cancel',name:'取消'}]
		   })
	  }); 
		
		var status_combox=Ext.create('Ext.form.field.ComboBox',{
	        fieldLabel: '状态',
	        labelAlign:'right',
            labelWidth:40,
            width:120,
	        //xtype:'combobox',
	        //afterLabelTextTpl: Ext.required,
	        name: 'status',
		    displayField: 'name',
		    valueField: 'key',
	        //allowBlank: false,
	        store:Ext.create('Ext.data.Store', {
		    	fields: ['key', 'name'],
			    data:[{key:'',name:'无'},{key:'newTask',name:'新任务'},{key:'read',name:'已阅'},{key:'handling',name:'处理中'},{key:'submited',name:'已提交'},{key:'complete',name:'完成'}]
		   })
	  }); 
	    var pole_textfield=Ext.create('Ext.form.field.Text',{
			labelAlign:'right',
			name:'pole_name',
			//fieldLabel: '杆位名称',
			emptyText:'请输入点位编号或名称',
			selectOnFocus:true,
			labelWidth:80,
			width:120,
			allowBlank:true
		});
	  var isOvertime_checkbox=Ext.create('Ext.form.field.Checkbox',{
			fieldLabel  : '超期',
			labelWidth:30,
            name      : 'isovertime'
            //inputValue: '1'
		});
		
		me.getStore().on("beforeload",function(store){
			store.getProxy().extraParams={
				customer_id:customer_combox.getValue(),
				pole_name:pole_textfield.getValue(),
					//area_id:area_combox.getValue(),
				status:status_combox.getValue(),
				type:task_type_combox.getValue(),
				workunit_id:workunit_combox.getValue(),
				isOvertime:isOvertime_checkbox.getValue()
			};
		});
		var query_button=Ext.create('Ext.button.Button',{
			text:'查询',
			margin:'0 0 0 5',
			iconCls:'icon-search',
			handler:function(){
				me.store.loadPage(1);
			}
		});
		
//		var install_button=Ext.create('Ext.button.Button',{
//			text:'确认',
//			margin:'0 0 0 5',
//			icon:'../icons/gem_okay.png',
//			handler:function(){
//				var records=me.getSelectionModel().getSelection();
//				if(!records || records.length==0){
//					alert("请先选择任务");
//					return;
//				}
//				
//				if(records.length==1){
//					if(records[0].get("status")!="submited"){
//						alert("只有'已提交'状态下，才能确认!");
//						return;
//					}	
//					Ext.Ajax.request({
//						url:Ext.ContextPath+'/task/confirm.do',
//						method:'POST',
//						params:{id:records[0].get("id")},
//						success:function(response){
//							records[0].set("status","complete");
//							records[0].set("status_name","完成");
//						}
//					});
//				} else {
//					Ext.Msg.confirm("提醒","只会为对'只有'已提交'状态的状态，才能确认",function(btn){
//						if(btn=='yes'){
//						
//						}
//					});
//				}
//				
//			}
//		});
//		
//		var back_button=Ext.create('Ext.button.Button',{
//			text:'退回',
//			margin:'0 0 0 5',
//			icon:'../icons/arrow_undo.png',
//			handler:function(){
//				Ext.Msg.confirm("消息","确定要退回吗?",function(btn){
//					if(btn=='no'){
//						return;
//					}
//					var records=me.getSelectionModel().getSelection();
//					if(!records || records.length==0){
//						alert("请先选择任务");
//						return;
//					}
//					
//					if(records.length==1){
//						if(records[0].get("status")!="submited"){
//							alert("只有'已提交'状态下，才能退回!");
//							return;
//						}	
//						Ext.Ajax.request({
//							url:Ext.ContextPath+'/task/back.do',
//							method:'POST',
//							params:{id:records[0].get("id")},
//							success:function(response){
//								records[0].set("status","handling");
//								records[0].set("status_name","处理中");
//							}
//						});
//					} else {
//						Ext.Msg.confirm("提醒","只会为对'只有'已提交'状态的状态，才能确认",function(btn){
//							if(btn=='yes'){
//							
//							}
//						});
//					}
//				});
//			}
//			
//		});
		
		var cancel_button=Ext.create('Ext.button.Button',{
			text:'取消',
			margin:'0 0 0 5',
			hidden:!Permision.canShow('task_cancel'),
			iconCls:'icon-remove',
			handler:function(){
				Ext.Msg.confirm("消息","确定要取消该任务吗?",function(btn){
					if(btn=='no'){
						return;
					}
					var records=me.getSelectionModel().getSelection();
					if(!records || records.length==0){
						alert("请先选择任务");
						return;
					}
					
					if(records.length==1){
//						if(records[0].get("status")!="submited"){
//							alert("只有'已提交'状态下，才能退回!");
//							return;
//						}	
						Ext.Ajax.request({
							url:Ext.ContextPath+'/task/cancel.do',
							method:'POST',
							params:{id:records[0].get("id")},
							success:function(response){
								me.getStore().remove(records);
							}
						});
					} else {
						Ext.Msg.confirm("提醒","只会为对'只有'已提交'状态的状态，才能确认",function(btn){
							if(btn=='yes'){
							
							}
						});
					}
				});
			}
			
		});
		
		//手工结束维修任务
		var finish_repair_task_button=Ext.create('Ext.button.Button',{
			text:'手工结束维修任务',
			hidden:!Permision.canShow('task_finishRepairTask'),
			margin:'0 0 0 5',
			iconCls:'icon-remove-circle',
			handler:function(){
				
				Ext.Msg.confirm("消息","只有没有扫描过设备的任务才能后台结束任务，确定要结束该任务吗?",function(btn){
					if(btn=='no'){
						return;
					}
					var records=me.getSelectionModel().getSelection();
					if(!records || records.length==0){
						alert("请先选择任务");
						return;
					}
					
					if(records.length==1){
						if(records[0].get("type")!="repair"){
							alert("只有维修任务才能手工结束!");
							return
						}	
						if(records[0].get("status")=="submited" || records[0].get("status")=="complete"){
							alert("'已提交'和完'成状'态下的任务不能结束!");
							return;
						}	
						
						var finish_form=Ext.create('Ems.task.FinishRepairForm',{
							task_id:records[0].get("id"),
							listeners:{
								sended:function(){
									me.getStore().reload();
									win.close();
									
								}							
							}
						});
						var win=Ext.create('Ext.window.Window',{
							layout:'fit',
							width:260,
							height:300,
							items:[finish_form]
						});
						win.show();
					} 
				});
			}
			
		});
		
		
		me.tbar={
			xtype: 'container',
			layout: 'anchor',
			defaults: {anchor: '0'},
			defaultType: 'toolbar',
			items: [{
				items: [customer_combox,workunit_combox,status_combox,task_type_combox,pole_textfield,isOvertime_checkbox,query_button] // toolbar 1
			}, {
				items: [cancel_button,finish_repair_task_button] // toolbar 2
			}]
		 }	
		
	}
//	showTaskForm:function(pole,task_type){
//		var pole_values=pole.getData();
//	
//					var values={};
//					values.pole_id=pole_values.id
//					values.pole_name=pole_values.name;
//					values.pole_address=pole_values.province+pole_values.city+pole_values.area+pole_values.address;
//					
//					values.customer_id=pole_values.customer_id;
//					values.customer_name=pole_values.customer_name;
//					values.workunit_id=pole_values.workunit_id;
//					values.workunit_name=pole_values.workunit_name;
//					values.type=task_type;
//					var title="发送任务";
//					//如果存在，表明该杆位已经存在任务关联了，所以直接设置这几个值
//					var showSendButton=true;
//					if(pole.get('task_status')){
//						values.status=pole.get('task_status');
//						values.status_name=pole.get('task_status_name');
//						values.memo=pole.get('task_memo');
//						showSendButton=false;//标识我现在只是查看这个任务的状态
//						title="查看任务详情";
//					}
//					var record=Ext.create('Ems.task.Task',values);
//					var form=Ext.create('Ems.task.TaskForm',{
//						url:Ext.ContextPath+'/task/create.do',
//						showSendButton:showSendButton,
//						listeners:{
//							sended:function(){
//								win.close();
//							}
//						}
//					});
//					//var record=me.showTaskForm(records[0]);
//					//record.set("type","newInstall");
//					form.loadRecord(record);
//					
//					var win=Ext.create('Ext.Window',{
//		        		layout:'fit',
//		        		width:450,
//		        		title:title,
//		        		height:420,
//		        		items:[form],
//		        		modal:true
//		        	});
//		        	win.show();
//		return record;
//	}
});
