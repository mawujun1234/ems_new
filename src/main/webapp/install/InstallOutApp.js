Ext.require("Ems.install.InstallOutEditGrid");
Ext.require("Ems.install.WorkUnitEquipmentWindow");
Ext.require("Ems.install.StoreEquipmentWindow");
Ext.require("Ems.install.InstallOutList");
Ext.onReady(function(){
//	var type_radio=Ext.create('Ext.form.RadioGroup',{
//            //xtype      : 'fieldcontainer',
//            fieldLabel : '<b>出库类型</b>',
//            labelAlign:'right',
//            labelWidth:60,
//            allowBlank:false,
//            //width:200,
//            defaultType: 'radiofield',
//            defaults: {flex: 1},
//            layout: 'hbox',
//            items: [
//                {
//                    boxLabel  : '设备领用',
//                    name      : 'type',
//                    inputValue: '1'
//                }, {
//                    boxLabel  : '设备维修',
//                    name      : 'type',
//                    inputValue: '2'
//                }
//            ]
//     });
//     var store_combox=Ext.create('Ext.form.field.ComboBox',{
//	        fieldLabel: '<b>仓库</b>',
//	        labelAlign:'right',
//            labelWidth:55,
//	        //xtype:'combobox',
//	        //afterLabelTextTpl: Ext.required,
//	        name: 'store_id',
//		    displayField: 'name',
//		    valueField: 'id',
//		    //queryParam: 'name',
//    		//queryMode: 'remote',
//    		//triggerAction: 'query',
//    		//minChars:-1,
//		    //trigger1Cls: Ext.baseCSSPrefix + 'form-clear-trigger',
//		    //trigger2Cls: Ext.baseCSSPrefix + 'form-arrow-trigger',//'form-search-trigger',
//			//onTrigger1Click : function(){
//			//    var me = this;
//			//    me.setValue('');
//			//},
//	        allowBlank: false,
//	        editable:false,
//	        store:Ext.create('Ext.data.Store', {
//		    	fields: ['id', 'name'],
//			    proxy:{
//			    	type:'ajax',
//			    	extraParams:{type:[1,3],edit:true},
//			    	url:Ext.ContextPath+"/store/queryCombo.do",
//			    	reader:{
//			    		type:'json',
//			    		root:'root'
//			    	}
//			    }
//		   })
//	    });
		var store_combox = Ext.create('Ems.baseinfo.StoreCombo', {
				edit : true,
				allowBlank : false,
				fieldLabel : '<b>仓库</b>'
			});
	var queryStoEquip_button=Ext.create('Ext.button.Button',{
		text:'库存',
		margin:'0 0 0 5',
		handler:function(){
			var store_id=store_combox.getValue();
			if(!store_id){
				Ext.Msg.alert("消息","请先选择一个仓库");
				return;
			}
			showStoreEquipment(store_id,store_combox.getRawValue());
		}
	});
	//显示某个作业单位的手持设备情况
	function showStoreEquipment(store_id,store_name) {
		var win=Ext.create('Ems.install.StoreEquipmentWindow',{
			title:'库存设备情况',
			width:800,
			height:400,
			closeAction:'hide',
			constrainHeader:true,
			modal:true,
			store_id:store_id,
			store_name:store_name
		});
		win.show();	
	}
        
//	var workUnit_combox=Ext.create('Ext.form.field.ComboBox',{
//	        fieldLabel: '<b>作业单位</b>',
//	        labelAlign:'right',
//            labelWidth:60,
//	        //xtype:'combobox',
//	        //afterLabelTextTpl: Ext.required,
//	        name: 'workUnit_id',
//		    displayField: 'name',
//		     editable:false,
//		    valueField: 'id',
//		    //queryParam: 'name',
//    		//queryMode: 'remote',
//    		//triggerAction: 'query',
//    		//minChars:-1,
//		    //trigger1Cls: Ext.baseCSSPrefix + 'form-clear-trigger',
//		    //trigger2Cls: Ext.baseCSSPrefix + 'form-arrow-trigger',//'form-search-trigger',
//			//onTrigger1Click : function(){
//			//    var me = this;
//			//    me.setValue('');
//			//},
//	        allowBlank: false,
//	        store:Ext.create('Ext.data.Store', {
//		    	fields: ['id', 'name'],
//			    proxy:{
//			    	type:'ajax',
//			    	url:Ext.ContextPath+"/workUnit/queryCombo.do",
//			    	reader:{
//			    		type:'json',
//			    		root:'root'
//			    	}
//			    }
//		   })
//	    });
	
	var workUnit_combox= Ext.create('Ems.baseinfo.WorkunitCombo', {
		look:true,
		allowBlank : false,
		name:'workUnit_id',
		fieldLabel : '<b>作业单位</b>'
	});
	var queryWorkUnitEquip_button=Ext.create('Ext.button.Button',{
		text:'持有',
		margin:'0 0 0 5',
		handler:function(){
			var workUnit_id=workUnit_combox.getValue();
			if(!workUnit_id){
				Ext.Msg.alert("消息","请先选择一个作业单位");
				return;
			}
			showWorkUnitEquipment(workUnit_id);
		}
	});
	//显示某个作业单位的手持设备情况
	function showWorkUnitEquipment(workUnit_id) {
		var win=Ext.create('Ems.install.WorkUnitEquipmentWindow',{
			title:'作业单位手持设备情况',
			width:800,
			height:400,
			constrainHeader:true,
			closeAction:'hide',
			modal:true,
			workUnit_id:workUnit_id
		});
		win.show();	
	}

	var installOutType_combox=Ext.create('Ext.form.field.ComboBox',{
	        fieldLabel: '领用类型',
	        labelAlign:'right',
            labelWidth:60,
	        //xtype:'combobox',
	        //afterLabelTextTpl: Ext.required,
	        name: 'installOutType_id',
		    displayField: 'name',
		    editable:false,
		    valueField: 'id',
	        allowBlank: true,
	        store:Ext.create('Ext.data.Store', {
		    	fields: ['id', 'name'],
			    proxy:{
			    	type:'ajax',
			    	//extraParams:{type:[1,3],edit:true},
			    	url:Ext.ContextPath+"/installOutType/query.do",
			    	reader:{
			    		type:'json',
			    		rootProperty:'root'
			    	}
			    }
		   }),
		   listeners:{
		   	change:function(field,newValue, oldValue){
				installOutType_content_textfield.setValue(field.getRawValue());
			}
		   }
	});
	var installOutType_content_textfield=Ext.create('Ext.form.field.Text',{
		labelAlign:'right',
		labelWidth:80,
		fieldLabel: '领用类型二级',
		name:'installOutType_content',
		readOnly:false,
		allowBlank:true,
		emptyText:'输入车牌号等内容'
	});
	var project_combox=Ext.create('Ems.baseinfo.ProjectCombo',{
		width:500	,
		allowBlank: false
	});
	
	var ecode_textfield=Ext.create('Ext.form.field.Text',{
		labelAlign:'right',
		name:'ecode',
		fieldLabel: '输入条码',
		minLength:Ext.ecode_length,
		maxLength:Ext.ecode_length,
		length:Ext.ecode_length,
		selectOnFocus:true,
		labelWidth:80,
		width:250,
		allowBlank:true,
		listeners:{
			blur:function(f,e){
				if(!f.getValue()||f.getValue()==''){
					f.clearInvalid();
				}
			},
			focus:function(){
				if(!workUnit_combox.getValue()){
					Ext.Msg.alert("消息","请先选择作业单位!");
					return;
				}
			},
			change:equipScan
		}
	});
	
	var clear_button=Ext.create('Ext.button.Button',{
		text:'清除',
		margin:'0 0 0 5',
		icon:'../icons/delRole.png',
		handler:function(){
			ecode_textfield.setValue('');
		}
	});
	var storeman_textfield=Ext.create('Ext.form.field.Text',{
		labelAlign:'right',
		labelWidth:55,
		fieldLabel: '经办人',
		name:'operater',
		readOnly:true,
		allowBlank:false,
		value:Ext.util.Cookies.get("loginName")
	});
	
	var inDate_textfield=Ext.create('Ext.form.field.Text',{
		labelAlign:'right',
		fieldLabel: '时间',
		labelWidth:55,
		name:'operateDate',
		readOnly:true,
		allowBlank:false,
		value:Ext.Date.format(new Date(),'Y-m-d')
	});
	var memo_textfield=Ext.create('Ext.form.field.Text',{
		labelAlign:'right',
		fieldLabel: '备注',
		labelWidth:55,
		name:'memo',
		flex:1,
		allowBlank:true
	});
	
	var select_edit_installout=Ext.create('Ext.button.Button',{
		text:'选择编辑中领用单',
		handler:function(){
			var installOutEdtiGrid=Ext.create('Ems.install.InstallOutEditGrid',{
				listeners:{
					itemdblclick:function( view, record, item, index, e, eOpts ) {
						installOut_id=record.get("id");//全局变量保存当前的订单
						reloadInstallout_content(record);
						win.close();
					}
				}
			});
			var win=Ext.create('Ext.window.Window',{
				layout:'fit',
				title:'双击选择',
				items:[installOutEdtiGrid],
				modal:true,
				width:500,
				height:360
			});
			win.show();
		
		}
	});
	
	function reloadInstallout_content(installout){
		store_combox.getStore().load();
		workUnit_combox.getStore().load();
		//installOutType_combox.getStore().load();
		
		var form= step1.down('form').getForm();
		form.loadRecord(installout);
		var project_model= project_combox.getStore().createModel({id:installout.get("project_id"),name:installout.get("project_name")});
		project_combox.setValue(project_model);

		//获取领用单的明细数据
		Ext.Ajax.request({
			url:Ext.ContextPath+"/installOut/qeryEditInstallOutListVO.do",
			method:'POST',
			params:{installOut_id:installout.get("id"),store_id:installout.get("store_id"),checkDate:checkDate},
			success:function(response){
				var obj=Ext.decode(response.responseText);
				equipStore.removeAll();
				equipStore.loadData(obj);
			}
		})
	}
	
	//var store_id_temp=null;//用来判断仓库的id有没有变
	//var workUnit_id_temp=null;
	function equipScan(field,newValue,oldValue,e){
		if(!installOutType_combox.getValue()){
			alert("请先选择领用类型!");
			return;
		}
		if(!installOutType_content_textfield.getValue()){
			alert("请填写领用类型的二级内容,如果不确定就填写和领用类型同样的名称!");
			return;
		}
		var form= step1.down('form').getForm();
		if(newValue.length>=Ext.ecode_length){
		   if(field.isValid()){
			  // form.load({
		   	Ext.Ajax.request({
					params : {
						ecode:newValue,
						store_id:store_combox.getValue(),
						project_id:project_combox.getValue(),
						checkDate:checkDate,
						installOutType_content:installOutType_content_textfield.getValue(),
						installOutType_id:installOutType_combox.getValue(),
						installOutType_name:installOutType_combox.getRawValue()
					},//传递参数   
					url : Ext.ContextPath+'/installOut/getEquipmentByEcode.do',//请求的url地址   
					method : 'POST',//请求方式   
					success : function(response) {//加载成功的处理函数   
						var ret=Ext.decode(response.responseText);
						if(ret.success){
							//为新增的equipment添加仓库等其他信息
							//ret.root.installOutType_id=installOutType_combox.getValue();
							//ret.root.installOutType_content=installOutType_content_textfield.getValue();
							//ret.root.installOutType_name=installOutType_combox.getRawValue();
							var scanrecord = Ext.create('Ems.install.InstallOutList', ret.root);

							ecode_textfield.setValue("");
							ecode_textfield.clearInvalid();

//							var exist=false;
//							equipStore.each(function(record){
//								if(newValue==record.get('ecode')){
//								    exist=true;
//								    return !exist;
//								}
//							});
//							if(exist){
//								Ext.Msg.alert('提示','该设备已经存在');
//							}else{
								//equipStore.insert(0, scanrecord);		
								//equipStore.add(scanrecord);	
								equipStore.insert(0, scanrecord);	
							    equip_grid.getView().refresh();	
								toolbar_title_text_num.update(""+equipStore.getCount());
							//}	
							workUnit_combox.disable();
							store_combox.disable();
							project_combox.disable();
						}
					},
					failure : function(response) {//加载失败的处理函数   
						//var ret=Ext.decode(response.responseText);
						//Ext.Msg.alert("消息",response.msg);
						ecode_textfield.setValue("");
						ecode_textfield.clearInvalid( );
					}
				});
		   }
		}else{
			//form.reset();
		}
	}
	
	//==========================================================================================
	
	var checkDate=(new Date()).getTime();
	var equipStore = Ext.create('Ext.data.Store', {
        autoDestroy: true,
        model: 'Ems.install.InstallOutList',
        proxy: {
            type: 'memory'
        }
    });
    var toolbar_title_text_num=Ext.create('Ext.form.Label',{
    	html:"0"
    });
    var  cellEditing = new Ext.grid.plugin.CellEditing({  
            clicksToEdit : 1  
      }); 
    cellEditing.on("edit",function(editor, context){
	  	var record=context.record;
	  	var grid=context.grid;
	  	var field =context.field ;
	  	var value=context.value;
	  	
	  	Ext.Ajax.request({
						url:Ext.ContextPath+'/installOut/updateMemoFromCache.do',
						params:{
							ecode:record.get("ecode"),
							memo:value,
							store_id:store_combox.getValue(),
							checkDate:checkDate
						},
						success:function(){
							record.commit();
							//me.getStore().reload();
						}
						
					});
    });
	var equip_grid=Ext.create('Ext.grid.Panel',{
		flex:1,
		store:equipStore,
    	columns: [Ext.create('Ext.grid.RowNumberer'),
    				{ header:'操作',
	                xtype: 'actioncolumn',
	                width: 50,
	                items: [{
	                    icon   : '../images/delete.gif',  // Use a URL in the icon config
	                    tooltip: '删除',
	                    handler: function(grid, rowIndex, colIndex) {
	                        var record = equipStore.getAt(rowIndex);
	                        Ext.MessageBox.confirm('确认', '您确认要删除该记录吗?', function(btn){
	                        	if(btn=='yes'){
	                        		Ext.Ajax.request({
										params : {ecode:record.get("ecode"),store_id:store_combox.getValue(),checkDate:checkDate},//传递参数   
										url : Ext.ContextPath+'/installOut/removeEquipFromCache.do',//请求的url地址   
										method : 'GET',//请求方式   
										success : function(response) {//加载成功的处理函数   
											var ret=Ext.decode(response.responseText);
											if(ret.success){
												equipStore.remove(record);
												toolbar_title_text_num.update(""+equipStore.getCount());
											}
										}
									});
	                        	}
	                        });
	                    }
	                }]
	            },
    			  {header: '条码', dataIndex: 'ecode',width:160},
    			  {header: '领用类型', dataIndex: 'installOutType_name'},
    			  {header: '领用类型二级', dataIndex: 'installOutType_content'},
    	          {header: '设备类型', dataIndex: 'subtype_name',width:120},
    	          {header: '明细备注(可编辑)', dataIndex: 'memo',width:120,
	    	         renderer:function(value, metaData, record, rowIndex, colIndex, store){
						//metaData.tdStyle = 'color:red;background-color:#98FB98;' ;
		            	metaData.tdCls  ='edit_grid_cell';
		            	 return value;
		            },editor: {
		                xtype: 'textfield',
		                selectOnFocus:true 
		            }
	              },
    	          {header: '品名', dataIndex: 'prod_name'},
    	          {header: '品牌', dataIndex: 'brand_name',width:120},
    	          {header: '供应商', dataIndex: 'supplier_name'},
    	          {header: '设备型号', dataIndex: 'style',width:120},
    	          {dataIndex:'prod_spec',header:'规格',flex:1,renderer:function(value,metadata,record){
						metadata.tdAttr = "data-qtip='" + value+ "'";
					    return value;
						}
				  }
    	          //{header: '仓库', dataIndex: 'store_name'},
    	          //{header: '作业单位', dataIndex: 'workUnit_name'},
    	          //{header: '数量', dataIndex: 'serialNum',width:70},
    	          
    	          
    	          //{header: 'stid', dataIndex: 'stid',hideable:false,hidden:true},
    	         // {header: '库房', dataIndex: 'stock',width:120},
    	          //{header: '状态', dataIndex: 'status_name',width:100}
    	          ],
    	plugins:[cellEditing],
        tbar:['<span id="toolbar-title-text">当前领用记录:</span>',toolbar_title_text_num,'->',
              {text:'清空所有的设备',
        	   iconCls:'icon-trash',
        	   handler:function(){
        		   Ext.MessageBox.confirm('确认', '您确认要清除所有记录吗?', function(btn){
						if(btn=='yes'){
							Ext.Ajax.request({
								params : {store_id:store_combox.getValue(),checkDate:checkDate},//传递参数   
								url : Ext.ContextPath+'/installOut/clearEquipFromCache.do',//请求的url地址   
								method : 'GET',//请求方式   
								success : function(response) {//加载成功的处理函数   
									var ret=Ext.decode(response.responseText);
									if(ret.success){
										equipStore.removeAll();
										workUnit_combox.enable();
										store_combox.enable();
										project_combox.enable();
										toolbar_title_text_num.update(""+equipStore.getCount());
									}
								}
							});
						}
					});
        	   }
        },'-',{text:'刷新',
        	   iconCls:'icon-refresh',
        	   handler:function(){
					var params={store_id:store_combox.getValue(),checkDate:checkDate};
					Ext.Ajax.request({
						url:Ext.ContextPath+'/installOut/refreshEquipFromCache.do',
						method:'POST',
						params:params,
						success:function(response){
							var obj=Ext.decode(response.responseText);
							equipStore.loadData( obj, false );
							toolbar_title_text_num.update(""+equipStore.getCount());
						}
					});
        	   	
        	   }
        }]
	});

	
	var installOut_id=null;
	var step1=Ext.create('Ext.panel.Panel',{
        layout: {
            type:'vbox',
            padding:'5',
            align:'stretch'
        },
        defaults:{margins:'0 0 5 0',border:false},
        items:[{xtype:'form',items:[{xtype:'fieldcontainer',layout: 'hbox',items:[store_combox,queryStoEquip_button,workUnit_combox,queryWorkUnitEquip_button,project_combox]},
                                     {xtype:'fieldcontainer',layout: 'hbox',items:[installOutType_combox,installOutType_content_textfield,ecode_textfield,clear_button]},
                                    {xtype:'fieldcontainer',layout: 'hbox',items:[storeman_textfield,inDate_textfield,memo_textfield]}
		            		        //{xtype:'columnbox',columnSize:4,items:[{xtype:'listcombox',url:Ext.ContextPath+'/dataExtra/stockList.do',itemId:'stock_field',fieldLabel:'库房',name:'stid',allowBlank:false,emptyText:'未选择库房',labelAlign:'right'},{xtype:'textfield',name:'stmemo',fieldLabel:'库房描述',columnWidth:3/4,labelAlign:'right'}]}
		            		        ]},
        {layout:{type:'hbox',algin:'stretch'},items:[select_edit_installout,{flex:1,border:false,html:'<HR style="FILTER: alpha(opacity=100,finishopacity=0,style=3)" width="100%" color=#987cb9 SIZE=3>'}
        //,{xtype:'button',text:'添加',handler:addEquip,width:70,iconCls:'icon-add',margin:'0 5px 0 5px'}
        
        ]},
        equip_grid,
        {html:'<HR style="FILTER: alpha(opacity=100,finishopacity=0,style=3)" width="100%" color=#987cb9 SIZE=3>'},
        //{html:'<img src="../images/error.gif" style="vertical-align:middle">&nbsp;库房人员应当根据采购单，对设备分类后，一次对同类设备批量“添加”入库，直到所有采购单设备根据设备类型都已经“添加”到入库清单后，可以选择“下一步”，进入到二维码生成步骤'}],
        {html:'<img src="../images/error.gif" style="vertical-align:middle">&nbsp;'}],
        buttons:[{text:'保存并打印',handler:function(btn){
        	var form= step1.down('form').getForm();
        	if(!form.isValid()){
        		alert("请在出现红框的地方选择值!");
        		return;
        	}
        	Ext.Msg.confirm("提示","当前领用的记录是:<span style='color:red;'>"+equipStore.getCount()+"</span>,是否继续?",function(btn){	
            if (btn=='yes') { 
            	Ext.getBody().mask("正在执行....");
            	var equipments = new Array();
            	equipStore.each(function(record){
            		equipments.push(record.data);
            	});
            	
				Ext.Ajax.request({
					url:Ext.ContextPath+'/installOut/equipmentOutStoreSaveAndPrint.do',
					method:'POST',
					timeout:600000000,
					//headers:{ 'Content-Type':'application/json;charset=UTF-8'},
					params:{memo:memo_textfield.getValue(),store_id:store_combox.getValue(),workUnit_id:workUnit_combox.getValue()
					,project_id:project_combox.getValue()
					,installOut_id:installOut_id
					,checkDate:checkDate
					},
					//jsonData:equipments,
					success:function(response){
						var obj=Ext.decode(response.responseText);
						
						//Ext.Msg.alert("消息","领用出库完成!");
						installOut_id=obj.root;
						Ext.Msg.confirm("消息","保存成功,是否打印该领用单?",function(btn){
								if(btn=='yes'){
									window.open("/installOut/equipmentOutStorePrint.do?installOut_id="+obj.root,"_blank");
								}
						});
						Ext.getBody().unmask();

					},
					failure:function(){
						Ext.getBody().unmask();
					}
					
				});
            }
            
            })
		}},{
			text:'领用出库',
			handler:function(){
				var form= step1.down('form').getForm();
	        	if(!form.isValid()){
	        		alert("请在出现红框的地方选择值!");
	        		return;
	        	}

	            Ext.Msg.confirm("提示","当前领用的记录是:<span style='color:red;'>"+equipStore.getCount()+"</span>,是否继续?",function(btn){	
            	if (btn=='yes') { 
		            Ext.Msg.confirm("消息","正准备领用出库,是否确认要领用出库?",function(btn){	
		            	if(btn=='yes'){
			            	Ext.getBody().mask("正在执行....");
			            	var equipments = new Array();
			            	equipStore.each(function(record){
			            		equipments.push(record.data);
			            	});
							Ext.Ajax.request({
								url:Ext.ContextPath+'/installOut/equipmentOutStore.do',
								method:'POST',
								timeout:600000000,
								//headers:{ 'Content-Type':'application/json;charset=UTF-8'},
								params:{memo:memo_textfield.getValue(),store_id:store_combox.getValue(),workUnit_id:workUnit_combox.getValue()
								,project_id:project_combox.getValue()
								,installOut_id:installOut_id
								,checkDate:checkDate
								},
								//jsonData:equipments,
								//params:{jsonStr:Ext.encode(equiplist)},
								success:function(response){
									//store_id_temp=null;//用来判断仓库的id有没有变
									//workUnit_id_temp=null;
									var obj=Ext.decode(response.responseText);
									
									Ext.Msg.confirm("消息","领用出库完成,是否还要打印该领用单?",function(btn){
										if(btn=='yes'){
											window.open("/installOut/equipmentOutStorePrint.do?installOut_id="+obj.root,"_blank");
										}
									});
									
									
									equipStore.removeAll();
									Ext.getBody().unmask();
									workUnit_combox.enable();
									store_combox.enable();
									project_combox.enable();
									installOut_id=null;
								},
								failure:function(){
									Ext.getBody().unmask();
								}
							});
						}
					});//Ext.Msg.confirm
				}
				
				})
					
			}
		}]
	});
	
	
	
	var viewPort=Ext.create('Ext.container.Viewport',{
		layout:'fit',
		items:[step1]
	});

});