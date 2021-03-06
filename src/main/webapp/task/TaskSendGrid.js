Ext.require('Ems.task.TaskForm');
Ext.define('Ems.task.TaskSendGrid',{
	extend:'Ext.grid.Panel',
	requires: [
	     'Ems.task.TaskPole'
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
	selModel: {
          selType: 'checkboxmodel'
          //,checkOnly:true
    },
	pageSize:50,
//	selModel:new Ext.selection.CheckboxModel({
//		checkOnly:true,
//		showHeaderCheckbox:true//防止点全选，去选择
////		renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
////			alert(record.get("status")+"测试在没有复选框的时候，getSelect会不会选择");
////			if(record.get("status")!='installing' && !record.get("status")=='cancel' ){
////				var baseCSSPrefix = Ext.baseCSSPrefix;
////		        metaData.tdCls = baseCSSPrefix + 'grid-cell-special ' + baseCSSPrefix + 'grid-cell-row-checker';
////		        return '<div class="' + baseCSSPrefix + 'grid-row-checker">&#160;</div>';
////			} else {
////				return "";
////			}
////	        
////	    }
//	}),
	initComponent: function () {
      var me = this;
      me.columns=[
		{dataIndex:'status',text:'状态',width:40,renderer : function(value,metadata, record, rowIndex, columnIndex, store) {
      	   metadata.tdAttr = "data-qtip='" + record.get("status_name")+ "'";
		   if (value == 'uninstall') {
		    return "<img src='../icons/help_circle_blue.png' />";
		   } else if (value == 'installing'){
		    return "<img src='../icons/circle_blue.png' />";
		   }else if (value == 'using'){
		    return "<img src='../icons/circle_green.png' />";
		   }else if (value == 'hitch'){
		    return "<img src='../icons/circle_yellow.png' />";
		   }else if (value == 'cancel'){
		    return "<img src='../icons/circle_red.png' />";
		   }
		   return record.get("status_name");
		 }},
		{dataIndex:'code',text:'编号',width:90},
		{dataIndex:'name',text:'点位名称',width:150,renderer:function(value,metadata ,record){
			if(record.get("task_num")){
				 return "<a href='javascript:void(0);'>("+record.get("task_num")+")"+value+"</a>";
			}
			return value;
		}},
		{dataIndex:'workunit_name',text:'作业单位'},
		{dataIndex:'province',text:'地址',flex:1,renderer:function(value,metadata ,record){
      		var aaa=value+record.get("city")+record.get("area")+record.get("address");
      		metadata.tdAttr = "data-qtip='" + aaa+ "'";
      		return aaa;
      	}},
		//{dataIndex:'area_name',text:'所属片区'},
		
		{dataIndex:'customer_name',text:'所属客户'}
      ];
      
	  me.store=Ext.create('Ext.data.Store',{
			autoSync:false,
			pageSize:me.pageSize,
			model: 'Ems.task.TaskPole',
			autoLoad:true,
			proxy:{
				type:'ajax',
				url:Ext.ContextPath+'/task/queryPoles.do',
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
      
	  me.on('cellclick',function(view, td, cellIndex, record, tr, rowIndex, e, eOpts){
	  	//alert(cellIndex);
	  	if(cellIndex==2){
//	  		//me.showTaskForm(record,record.get("task_type"))
//	  		//var iframe=top.window.docPanel.loadPage("/task/TaskQueryApp.jsp?callBack=query4Pole&pole_id="+record.get("id"),
//	  		//	"dd2f0178-bc59-422e-ae96-0af92bc6cc0c","任务查询管理","other");
//	  		
//	  		var iframe=top.window.docPanel.loadPage("/task/TaskQueryApp.jsp?autoLoad=false",
//	  			"dd2f0178-bc59-422e-ae96-0af92bc6cc0c","任务查询管理","other");
//	  		if(iframe){
//	  			iframe.on('load',function(){
//		  			//alert(111);
//		  			iframe.getWin().query4Pole(record.get("id"));
//		  		});
//		  		me.iframe=iframe;
//	  		} else {
//	  			me.iframe.getWin().query4Pole(record.get("id"));
//	  		}
	  		
	  		
	  	}
	  });
	  me.initToolbar();
      me.callParent();
	},
	//初始化工具栏
	initToolbar:function(){
		var me=this;
//		var customer_combox=Ext.create('Ext.form.field.ComboBox',{
//	        fieldLabel: '客户名称',
//	        labelAlign:'right',
//            labelWidth:60,
//            //width:250,
//	        //xtype:'combobox',
//	        //afterLabelTextTpl: Ext.required,
//	        name: 'customer_id',
//		    displayField: 'name',
//		    valueField: 'id',
//		    queryParam: 'name',
//    		queryMode: 'remote',
//    		triggerAction: 'query',
//    		minChars:-1,
//		    trigger1Cls: Ext.baseCSSPrefix + 'form-clear-trigger',
//		    trigger2Cls: Ext.baseCSSPrefix + 'form-arrow-trigger',//'form-search-trigger',
//			onTrigger1Click : function(){
//			    var me = this;
//			    me.setValue('');
//			},
//	        //allowBlank: false,
//	        store:Ext.create('Ext.data.Store', {
//		    	fields: ['id', 'name'],
//			    proxy:{
//			    	type:'ajax',
//			    	//extraParams:{type:1,edit:true},
//			    	url:Ext.ContextPath+"/customer/queryCombo.do",
//			    	reader:{
//			    		type:'json',
//			    		rootProperty:'root'
//			    	}
//			    }
//		   })
//	    });
		var customer_combox=Ext.create('Ems.baseinfo.CustomerCombo',{});
	    var filter_other_combox=Ext.create('Ext.form.field.ComboBox',{
	        fieldLabel: '过滤',
	        labelAlign:'right',
            labelWidth:40,
            //width:250,
	        //xtype:'combobox',
	        //afterLabelTextTpl: Ext.required,
	        name: 'filter_other',
		    displayField: 'name',
		    valueField: 'id',
		    //queryParam: 'name',
    		//queryMode: 'locale',
	        //allowBlank: false,
	        store:Ext.create('Ext.data.ArrayStore', {
		    	fields: ['id', 'name'],
			    data:[['no_filter','无'],['no_send_task','未发送过任务']]
		   })
	    });
	    

		var workunit_combox= Ext.create('Ems.baseinfo.WorkunitCombo', {
			edit:true,
			showBlank:true,
			allowBlank : true,
			fieldLabel : '作业单位'
		});
	    
	    var pole_textfield=Ext.create('Ext.form.field.Text',{
			labelAlign:'right',
			name:'pole_name',
			//fieldLabel: '点位名称',
			emptyText:'请输入点位编号或名称',
			selectOnFocus:true,
			labelWidth:80,
			width:120,
			allowBlank:true
		});
		
		
		me.store.on("beforeload",function(store){
			store.getProxy().extraParams={
					customer_id:customer_combox.getValue(),
					filter_other:filter_other_combox.getValue(),
					pole_name:pole_textfield.getValue(),
					//area_id:area_combox.getValue(),
					workunit_id:workunit_combox.getValue()
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
		//判断一个点位是否能存在多个任务
		function checkTasknum(record){
			if(record.get("task_num")>2){
				alert("点位‘"+record.get("name")+"’已经存在3个任务,不能再发送了。");
				return false;
			} else {
				return true;
			}
		}
		window.checkTasknum=checkTasknum;
		var install_button=Ext.create('Ext.button.Button',{
			text:'发送安装任务',
			margin:'0 0 0 5',
			icon:'../icons/install.png',
			hidden:!Permision.canShow('task_send_install'),
			handler:function(){
				var records=me.getSelectionModel().getSelection();
				if(!records || records.length==0){
					alert("请先选择点位");
					return;
				}
				
				if(records.length==1){
					if(records[0].get("status")!="uninstall" && records[0].get("status")!="cancel"){
						alert("只有'未安装'或'取消'状态的点位，才能发送新安装任务!");
						return;
					}
					//所有点位都必须属于同一个作业单位才允许发送
					
					var bool=checkTasknum(records[0]);
					if(!bool){
						return;
					}
					me.showTaskForm(records[0],"newInstall");
					
				} else {
					Ext.Msg.confirm("提醒","只会为对'未安装'的点位发送安装任务,选'是'进行发送,并且将会直接发送，不能填写任务描述信息",function(btn){
						if(btn=='yes'){
							var taskes=[];
							for(var i=0;i<records.length;i++){
								if(records[i].get("status")!="uninstall"){
									alert("点位‘"+records[i].get("name")+"’不能发送新安装任务!");
									return;
								}
								var bool=checkTasknum(records[i]);
								if(!bool){
									return;
								}
								taskes.push(me.initTask_value(records[i],"newInstall"));
							}
							//
							Ext.Ajax.request({
								method:'POST',
								jsonData:taskes,
								headers:{ 'Content-Type':'application/json;charset=UTF-8'},
								url:Ext.ContextPath+"/task/create.do",
								success:function(response){
									var obj=Ext.decode(response.responseText);
									if(obj.success){
										me.getSelectionModel( ).deselectAll();
										me.getStore().reload();
										alert("保存成功!");
									}
									
								}
							});
						}
					});
				}
				
			}
		});
		
		var repair_button=Ext.create('Ext.button.Button',{
			text:'发送维修/维护任务',
			margin:'0 0 0 5',
			hidden:!Permision.canShow('task_send_repair'),
			icon:'../icons/repair.png',
			handler:function(){
				var records=me.getSelectionModel().getSelection();
				if(!records || records.length==0){
					alert("请先选择点位");
					return;
				}

				if(records.length==1){
					var pole_status=records[0].get("status");
					//alert(pole_status);
					if(pole_status!="using" && pole_status!="hitch"){
						alert("只有'使用中','有损坏'状态的点位，才能发送维修/维护任务!");
						return;
					}
					var bool=checkTasknum(records[0]);
								if(!bool){
									return;
								}
					me.showTaskForm(records[0],"repair");
				} else {
					//alert("维修任务只能一个一个发，因为要选故障时间!");
					me.createBatchRepairTask(records);
				}
				
			}
		});
		
		var patrol_button=Ext.create('Ext.button.Button',{
			text:'发送巡检任务',
			margin:'0 0 0 5',
			hidden:!Permision.canShow('task_send_patrol'),
			icon:'../icons/patrols.png',
			handler:function(){
				var records=me.getSelectionModel().getSelection();
				if(!records || records.length==0){
					alert("请先选择点位");
					return;
				}
				if(records.length==1){
					var pole_status=records[0].get("status");
					if(pole_status!="using" && pole_status!="hitch"){
						alert("只有'使用中','有损坏'状态的点位，才能发送巡检任务!");
						return;
					}
					var bool=checkTasknum(records[0]);
								if(!bool){
									return;
								}
					me.showTaskForm(records[0],"patrol");
				} else {
					me.createBatchPatrolTask(records);
				}
				
			}
		});
		var check_button=Ext.create('Ext.button.Button',{
			text:'发送盘点任务',
			margin:'0 0 0 5',
			hidden:!Permision.canShow('task_send_check'),
			icon:'../icons/patrols.png',
			handler:function(){
				var records=me.getSelectionModel().getSelection();
				if(!records || records.length==0){
					alert("请先选择点位");
					return;
				}
				if(records.length==1){
//					var pole_status=records[0].get("status");
//					if(pole_status!="using" && pole_status!="hitch"){
//						alert("只有'使用中','有损坏'状态的点位，才能发送巡检任务!");
//						return;
//					}
//					var bool=checkTasknum(records[0]);
//								if(!bool){
//									return;
//								}
					//me.showTaskForm(records[0],"check");
					me.createBatchcheckTask(records);
				} else {
					me.createBatchcheckTask(records);
				}
				
			}
		});
		
		
		var cancel_button=Ext.create('Ext.button.Button',{
			text:'取消点位',
			margin:'0 0 0 5',
			hidden:!Permision.canShow('task_send_cancel'),
			icon:'../icons/cancel.png',
			handler:function(){
				var records=me.getSelectionModel().getSelection();
				if(!records || records.length==0){
					alert("请先选择点位");
					return;
				}
				if(records.length==1){
//					var pole_status=records[0].get("status");
//					if(pole_status!="using" && pole_status!="hitch"){
//						alert("只有'使用中','有损坏'状态的点位，才能发送巡检任务!");
//						return;
//					}
					var bool=checkTasknum(records[0]);
					if(!bool){
						return;
					}
					me.showTaskForm(records[0],"cancel");
				} else {
					Ext.Msg.alert("消息","为了防止错误取消点位,一次只能取消一个点位!");
					return;
				}
				
			}
		});
		
		me.tbar={
			xtype: 'container',
			layout: 'anchor',
			defaults: {anchor: '0'},
			defaultType: 'toolbar',
			items: [{
				items: [customer_combox,filter_other_combox,workunit_combox,pole_textfield,query_button] // toolbar 1
			}, {
				items: [install_button,repair_button,patrol_button,check_button,cancel_button] // toolbar 2
			}]
		  }	
		
	},
	initTask_value:function(pole,task_type){
		var pole_values=pole.getData();
	
		var values={};
		//values.pole_id=pole_values.id.split('-')[0];
		values.pole_id=pole_values.id;
		values.pole_name=pole_values.name;
		values.pole_address=pole_values.province+pole_values.city+pole_values.area+pole_values.address;
					
		values.customer_id=pole_values.customer_id;
		values.customer_name=pole_values.customer_name;
		values.workunit_id=pole_values.workunit_id;
		values.workunit_name=pole_values.workunit_name;
		values.type=task_type;
		
		values.status='newTask';
		return values
	},
	showTaskForm:function(pole,task_type){
		
		var me=this;
		var values=me.initTask_value(pole,task_type);
					var title="发送任务";
					//如果存在，表明该点位已经存在任务关联了，所以直接设置这几个值
					var showSendButton=true;
					
					//如果已经存在巡检任务，就可以新建维修任务
					if(pole.get('task_type')=="patrol" && task_type=="repair"){
						showSendButton=true;
					} else if(pole.get('task_status')){
						values.status=pole.get('task_status');
						values.status_name=pole.get('task_status_name');
						values.memo=pole.get('task_memo');
						showSendButton=false;//标识我现在只是查看这个任务的状态
						title="查看任务详情";
					}
					
					var record=Ext.create('Ems.task.Task',values);
					var form=Ext.create('Ems.task.TaskForm',{
						url:Ext.ContextPath+'/task/create.do',
						showSendButton:showSendButton,
						task_type:task_type,
						listeners:{
							sended:function(){
								win.close();
								me.getSelectionModel( ).deselectAll();
								me.getStore().reload();
							}
						}
					});
					//var record=me.showTaskForm(records[0]);
					//record.set("type","newInstall");
					form.loadRecord(record);
					
					var win=Ext.create('Ext.Window',{
		        		layout:'fit',
		        		width:450,
		        		title:title,
		        		height:420,
		        		items:[form],
		        		modal:true
		        	});
		        	win.show();
		        	
		return record;
	},
	/**
	 * 创建批量的维修任务
	 */
	createBatchRepairTask:function(records){
		var me=this;
					Ext.Msg.confirm("提醒","只会为对'使用中','有损坏'的点位发送维修/维护任务,选'是'进行发送",function(btn){
						if(btn=='yes'){
							var taskes=[];
							var workunit_name='';//只有所有点位都属于一个作业单位才允许发送
							for(var i=0;i<records.length;i++){
								if(records[i].get("status")!="using" && records[i].get("status")!="hitch"){
									alert("点位‘"+records[i].get("name")+"’不能发送维修任务!");
									return;
								}
								if(!workunit_name){
									workunit_name=records[i].get("workunit_name");
								} else if(workunit_name!=records[i].get("workunit_name")){
									//客户要求，只有同一个作业单位的才能批量发送
									alert("点位‘"+records[i].get("name")+"’的作业单位不一致，不能发送!");
									return;
								}
								var bool=window.checkTasknum(records[i]);
								if(!bool){
									return;
								}
								taskes.push(me.initTask_value(records[i],"repair"));
							}
							//填写任务信息
							var win=Ext.create('Ext.window.Window',{
								modal:true,
								width:350,
								height:250,
								title:'批量提交任务',
								items:[{
						            fieldLabel: '故障时间',
						            afterLabelTextTpl: Ext.required,
						            itemId: 'hitchDate',
						            editable:false,
						            allowBlank: true,
						            format : "Y-m-d H:i:s",
						            xtype: 'datetimefield'
						        },
								{
							        fieldLabel: '任务描述',
							        afterLabelTextTpl: Ext.required,
							        itemId: 'memo',
							        readOnly:false,
							        xtype:'textareafield',
							        grow:true,
							        allowBlank: false
							    },
								{
							        fieldLabel: '作业单位',
							        //afterLabelTextTpl: Ext.required,
							        name: 'workunit_name',
							        readOnly:true,
							        value:workunit_name,
							        xtype:'textfield',
							        allowBlank: true
							    }],
							    buttons:[{
							    	text:'取 消',
							    	handler:function(btn){
							    		win.close();
							    	}
							    },{
							    	text:'保存',
							    	handler:function(btn){
							    		var hitchDate=win.down("#hitchDate").getRawValue();
							    		if(!hitchDate){
							    			Ext.Msg.alert("消息","请先输入故障时间");
							    			return;
							    		}
							    		var memo=win.down("#memo").getValue();
							    		if(!memo){
							    			Ext.Msg.alert("消息","请先输入备注");
							    			return;
							    		}
							    		for(var i=0;i<taskes.length;i++) {
							    			taskes[i].hitchDate=hitchDate;
							    			taskes[i].memo=memo;
							    		}
							    		Ext.Ajax.request({
											method:'POST',
											jsonData:taskes,
											headers:{ 'Content-Type':'application/json;charset=UTF-8'},
											url:Ext.ContextPath+"/task/create.do",
											success:function(response){
												var obj=Ext.decode(response.responseText);
												if(obj.success){
													me.getSelectionModel( ).deselectAll();
													me.getStore().reload();
													alert("发送成功!");
													win.close();
												}
												
											}
										});
							    	}
							    
							    }]
								
							});
							win.show();
							
							
							//
							
						}
					});
	},
	
	createBatchPatrolTask:function(records){
		var me=this;
		Ext.Msg.confirm("提醒","只会为对'使用中','有损坏'的点位发送巡检任务,选'是'进行发送",function(btn){
			if(btn=='yes'){
				var taskes=[];
				//var workunit_name="";
				for(var i=0;i<records.length;i++){
					if(records[i].get("status")!="using" && records[i].get("status")!="hitch"){
						alert("点位‘"+records[i].get("name")+"’不能发送巡检任务!");
						return;
					}
					var bool=checkTasknum(records[i]);
					if(!bool){
						return;
					}
					taskes.push(me.initTask_value(records[i],"patrol"));
					//workunit_name+=","+records[i].get("workunit_name");
				}
				//
				var win=Ext.create('Ext.window.Window',{
					modal:true,
					width:280,
					height:250,
					title:'批量提交任务',
					items:[{
			            fieldLabel: '巡检类型',
			            afterLabelTextTpl: Ext.required,
			            itemId: 'patrolTaskType_id',
			            editable:false,
			            xtype:'combo',
			            allowBlank: true,
			            displayField: 'name',
					    valueField: 'id',
				        store:Ext.create('Ext.data.Store', {
					    	fields: ['id', 'name'],
						    proxy:{
						    	type:'ajax',
						    	//extraParams:{type:1,edit:true},
						    	url:Ext.ContextPath+"/patrolTaskType/queryAll.do",
						    	reader:{
						    		type:'json',
						    		rootProperty:'root'
						    	}
						    }
					   })
			            
			        },
					{
				        fieldLabel: '任务描述',
				        afterLabelTextTpl: Ext.required,
				        itemId: 'memo',
				        readOnly:false,
				        xtype:'textareafield',
				        grow:true,
				        allowBlank: false
				    }],
				    buttons:[{
				    	text:'取 消',
				    	handler:function(btn){
				    		win.close();
				    	}
				    },{
				    	text:'保存',
				    	handler:function(btn){
				    		var patrolTaskType_id=win.down("#patrolTaskType_id").getValue();
				    		if(!patrolTaskType_id){
				    			Ext.Msg.alert("消息","请先输入巡检类型");
				    			return;
				    		}
				    		var memo=win.down("#memo").getValue();
				    		if(!memo){
				    			Ext.Msg.alert("消息","请先输入备注");
				    			return;
				    		}
				    		for(var i=0;i<taskes.length;i++) {
				    			taskes[i].patrolTaskType_id=patrolTaskType_id;
				    			taskes[i].memo=memo;
				    		}
				    		Ext.Ajax.request({
								method:'POST',
								jsonData:taskes,
								headers:{ 'Content-Type':'application/json;charset=UTF-8'},
								url:Ext.ContextPath+"/task/create.do",
								success:function(response){
									var obj=Ext.decode(response.responseText);
									if(obj.success){
										me.getSelectionModel( ).deselectAll();
										me.getStore().reload();
										alert("保存成功!");
									}
									
								}
							});
				    	}
				    
				    }]
				});
				win.show();
			}
		});
		
	},//createBatchPatrolTask
	createBatchcheckTask:function(records){
		var me=this;
		var taskes=[];
				//var workunit_name="";
			for(var i=0;i<records.length;i++){
//					if(records[i].get("status")!="using" && records[i].get("status")!="hitch"){
//					var bool=checkTasknum(records[i]);
//					if(!bool){
//						return;
//					}
					taskes.push(me.initTask_value(records[i],"check"));
					//workunit_name+=","+records[i].get("workunit_name");
				
			}
		var win=Ext.create('Ext.window.Window',{
					modal:true,
					width:280,
					height:250,
					title:'批量提交任务',
					items:[
					{
				        fieldLabel: '任务描述',
				        afterLabelTextTpl: Ext.required,
				        itemId: 'memo',
				        readOnly:false,
				        xtype:'textareafield',
				        value:'扫描点位上所有的设备',
				        grow:true,
				        allowBlank: false
				    }],
				    buttons:[{
				    	text:'取 消',
				    	handler:function(btn){
				    		win.close();
				    	}
				    },{
				    	text:'保存',
				    	handler:function(btn){
				    		
				    		var memo=win.down("#memo").getValue();
				    		if(!memo){
				    			Ext.Msg.alert("消息","请先输入备注");
				    			return;
				    		}
				    		for(var i=0;i<taskes.length;i++) {
				    			//taskes[i].patrolTaskType_id=patrolTaskType_id;
				    			taskes[i].memo=memo;
				    		}
				    		Ext.Ajax.request({
								method:'POST',
								jsonData:taskes,
								headers:{ 'Content-Type':'application/json;charset=UTF-8'},
								url:Ext.ContextPath+"/task/create.do",
								success:function(response){
									var obj=Ext.decode(response.responseText);
									if(obj.success){
										me.getSelectionModel( ).deselectAll();
										me.getStore().reload();
										alert("发送成功!");
										win.close();
									}
									
								}
							});
				    	}
				    
				    }]
				});
				win.show();
				
			
	}
	
});
