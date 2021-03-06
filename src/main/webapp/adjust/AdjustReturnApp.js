//Ext.require("Ems.store.Barcode");
Ext.require("Ems.adjust.Adjust");
Ext.require("Ems.adjust.AdjustBorrowGrid");
//Ext.require("Ems.store.BarcodeForm");
Ext.onReady(function(){
        
//	var store_out_combox=Ext.create('Ext.form.field.ComboBox',{
//	        fieldLabel: '<b>调出仓库</b>',
//	        labelAlign:'right',
//            labelWidth:60,
//	        //xtype:'combobox',
//	        //afterLabelTextTpl: Ext.required,
//	        name: 'store_out_id',
//		    displayField: 'name',
//		    valueField: 'id',
//		    readOnly:true,
//		    emptyText:'点击选择按钮',
//		    editable:false,
//	        allowBlank: false,
//	        store:Ext.create('Ext.data.Store', {
//	        	autoLoad:true,
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
//	});
	var store_out_combox = Ext.create('Ems.baseinfo.StoreCombo', {
				edit : true,
				allowBlank : true,
				fieldLabel: '<b>调出仓库</b>'
	 });
//	var store_in_combox=Ext.create('Ext.form.field.ComboBox',{
//	        fieldLabel: '<b>调入仓库</b>',
//	        labelAlign:'right',
//            labelWidth:60,
//	        //xtype:'combobox',
//	        //afterLabelTextTpl: Ext.required,
//	        name: 'store_in_id',
//		    displayField: 'name',
//		    valueField: 'id',
//		    readOnly:true,
//		    emptyText:'点击选择按钮',
//		    editable:false,
//	        allowBlank: false,
//	        store:Ext.create('Ext.data.Store', {
//	        	autoLoad:true,
//		    	fields: ['id', 'name'],
//			    proxy:{
//			    	type:'ajax',
//			    	extraParams:{type:[1,3],look:true},
//			    	url:Ext.ContextPath+"/store/queryCombo.do",
//			    	reader:{
//			    		type:'json',
//			    		root:'root'
//			    	}
//			    }
//		   })
//	});
	 var store_in_combox = Ext.create('Ems.baseinfo.StoreCombo', {
				look : true,
				allowBlank : true,
				fieldLabel: '<b>调入仓库</b>'
	 });
	var adjustType_combox=Ext.create('Ext.form.field.Hidden',{
	        name: 'adjustType',
		    value:'returnback'
	});
	//要归还的借用单的id
	var adjust_id_borrow_textfield=Ext.create('Ext.form.field.Text',{
		fieldLabel: '借用调拨单号',
		 emptyText:'点击选择按钮',
	        name: 'adjust_id_borrow',
	        allowBlank:false,
	        readOnly:true,
		    value:''
	});
	var select_borrow_adjust_id=Ext.create('Ext.button.Button',{
		text:'选择',
		handler:function(){
			var borrowAdjuestGrid=Ext.create('Ems.adjust.AdjustBorrowGrid',{
				listeners:{
					itemdblclick:function( view, record, item, index, e, eOpts ) {
						var adjust_id=record.get("id");//全局变量保存当前的订单
						adjust_id_borrow_textfield.setValue(adjust_id);
						//正好相反
						store_out_combox.setValue(record.get("str_in_id"));
						store_in_combox.setValue(record.get("str_out_id"));
						win.close();
					}
				}
			});
			var win=Ext.create('Ext.window.Window',{
				layout:'fit',
				title:'双击选择',
				items:[borrowAdjuestGrid],
				modal:true,
				width:650,
				height:360
			});
			win.show();
		
		}
	});
	var ecode_textfield=Ext.create('Ext.form.field.Text',{
		labelAlign:'right',
		name:'ecode',
		fieldLabel: '输入条码',
		minLength:Ext.ecode_length,
		maxLength:Ext.ecode_length,
		length:Ext.ecode_length,
		selectOnFocus:true,
		labelWidth:60,
		width:250,
		//allowBlank:false,
		listeners:{
			blur:function(f,e){
				if(!f.getValue()||f.getValue()==''){
					f.clearInvalid();
				}
			},
			focus:function(){
				//alert(type_radio.getValue());
				//console.dir(type_radio.getValue());
//				if(type_radio.getValue().type!=1){
//					alert("设备返库和维修入库还没有做!");
//					return;
//				}
//				if(!type_radio.getValue().type){
//					Ext.Msg.alert("消息","请先选择入库类型!");
//					return;
//				}
				if(!store_in_combox.getValue()){
					Ext.Msg.alert("消息","请先选择仓库!");
					return;
				}
			},
			change:equipScan
		}
	});
	
	var clear_button=Ext.create('Ext.button.Button',{
		text:'清除',
		margin:'0 0 0 5',
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
	
	//var store_id_temp=null;//用来判断仓库的id有没有变
	function equipScan(field,newValue,oldValue,e){
//		if(!store_id_temp){
//			store_id_temp=store_out_combox.getValue();
//		} else if(store_id_temp!=store_out_combox.getValue()){
//			Ext.Msg.alert("消息","对不起，一次出库只能选择一个仓库.");
//			ecode_textfield.setValue("");
//			ecode_textfield.clearInvalid( );
//			return;
//		}
		
		var form= step1.down('form').getForm();
		if(newValue.length>=Ext.ecode_length){
		   if(field.isValid()){
			  // form.load({
		   	Ext.Ajax.request({
					params : {ecode:newValue,store_id:store_out_combox.getValue()},//传递参数   
					url : Ext.ContextPath+'/adjust/getAdjustListVOByEcode.do',//请求的url地址   
					method : 'GET',//请求方式   
					success : function(response) {//加载成功的处理函数   
						var ret=Ext.decode(response.responseText);
						if(ret.success){
//							//为新增的equipment添加仓库等其他信息
//							ret.root.str_out_id=store_out_combox.getValue();
//							ret.root.str_out_name=store_out_combox.getRawValue();
//							ret.root.str_in_id=store_in_combox.getValue();
//							ret.root.str_in_name=store_in_combox.getRawValue();
	
							
							var scanrecord = Ext.create('Ems.adjust.AdjustList', ret.root);

							ecode_textfield.setValue("");
							ecode_textfield.clearInvalid( );

							var exist=false;
							equipStore.each(function(record){
								if(newValue==record.get('ecode')){
								    exist=true;
								    return !exist;
								}
							});
							if(exist){
								Ext.Msg.alert('提示','该设备已经存在');
							}else{
								//equipStore.insert(0, scanrecord);	
								//equipStore.add(scanrecord);
								equipStore.insert(0, scanrecord);	
							    equip_grid.getView().refresh();	
								toolbar_title_text_num.update(""+equipStore.getCount());
							}			
							store_out_combox.disable();
							store_in_combox.disable();
						}
					}
//					failure : function(response) {//加载失败的处理函数   
//						Ext.Msg.alert('提示', '设备加载失败：'+ response.responseText);
//						ecode_textfield.setValue("");
//						ecode_textfield.clearInvalid( );
//					}
				});
		   } 
		}else{
			//form.reset();
		}
	}
	
	//==========================================================================================
	
	var toolbar_title_text_num=Ext.create('Ext.form.Label',{
    	html:"0"
    });
	var equipStore = Ext.create('Ext.data.Store', {
        autoDestroy: true,
        model: 'Ems.adjust.Adjust',
        proxy: {
            type: 'memory'
        }
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
	                        var rec = equipStore.getAt(rowIndex);
	                        Ext.MessageBox.confirm('确认', '您确认要删除该记录吗?', function(btn){
	                        	if(btn=='yes'){
	                        		equipStore.remove(rec);
	                        		toolbar_title_text_num.update(""+equipStore.getCount());
	                        	}
	                        });
	                    }
	                }]
	            },
    			  {header: '条码', dataIndex: 'ecode',width:160},
    	          {header: '设备类型', dataIndex: 'subtype_name',width:120},
    	          {header: '品名', dataIndex: 'prod_name'},
    	          {header: '品牌', dataIndex: 'brand_name',width:120},
    	          {header: '供应商', dataIndex: 'supplier_name'},
    	          {header: '设备型号', dataIndex: 'prod_style',width:120},
    	          {header:'规格',dataIndex:'prod_spec',minWidth:100,flex:1,renderer:function(value,metadata,record){
						metadata.tdAttr = "data-qtip='" + value+ "'";
					    return value;
						}
				  }
    	          //{header: '出库仓库', dataIndex: 'str_out_name'},
    	          //{header: '入库仓库', dataIndex: 'str_in_name'},
				  ],
        tbar:['<pan id="toolbar-title-text">当前归还记录:</span>',toolbar_title_text_num,'->',
              {text:'清空列表中设备',
        	   iconCls:'icon-trash',
        	   handler:function(){
        		   Ext.MessageBox.confirm('确认', '您确认要清除所有记录吗?', function(btn){
						if(btn=='yes'){
							equipStore.removeAll();
							store_out_combox.enable();
							store_in_combox.enable();
							toolbar_title_text_num.update(""+equipStore.getCount());
						}
					});
        	   }
        }]
	});
	
	

	
	var step1=Ext.create('Ext.panel.Panel',{
        layout: {
            type:'vbox',
            padding:'5',
            align:'stretch'
        },
        defaults:{margins:'0 0 5 0',border:false},
        items:[{xtype:'form',items:[{xtype:'fieldcontainer',layout: 'hbox',items:[adjust_id_borrow_textfield,select_borrow_adjust_id,store_out_combox,store_in_combox,adjustType_combox,ecode_textfield,clear_button]},
                                    {xtype:'fieldcontainer',layout: 'hbox',items:[storeman_textfield,inDate_textfield,memo_textfield]}
		            		        //{xtype:'columnbox',columnSize:4,items:[{xtype:'listcombox',url:Ext.ContextPath+'/dataExtra/stockList.do',itemId:'stock_field',fieldLabel:'库房',name:'stid',allowBlank:false,emptyText:'未选择库房',labelAlign:'right'},{xtype:'textfield',name:'stmemo',fieldLabel:'库房描述',columnWidth:3/4,labelAlign:'right'}]}
		            		        ]},
        {layout:{type:'hbox',algin:'stretch'},items:[{flex:1,border:false,html:'<HR style="FILTER: alpha(opacity=100,finishopacity=0,style=3)" width="100%" color=#987cb9 SIZE=3>'}
        //,{xtype:'button',text:'添加',handler:addEquip,width:70,iconCls:'icon-add',margin:'0 5px 0 5px'}
        ]},
        equip_grid,
        {html:'<HR style="FILTER: alpha(opacity=100,finishopacity=0,style=3)" width="100%" color=#987cb9 SIZE=3>'},
        //{html:'<img src="../images/error.gif" style="vertical-align:middle">&nbsp;库房人员应当根据采购单，对设备分类后，一次对同类设备批量“添加”入库，直到所有采购单设备根据设备类型都已经“添加”到入库清单后，可以选择“下一步”，进入到二维码生成步骤'}],
        {html:'<img src="../images/error.gif" style="vertical-align:middle">&nbsp;一次调拨出库只能选择一个仓库'}],
        buttons:[{text:'归还',handler:function(btn){
            Ext.Msg.confirm("提示","当前归还出库的记录是:<span style='color:red;'>"+equipStore.getCount()+"</span>,是否继续?",function(btn){	
        	
            if (btn=='yes') { 
            	var form= step1.down('form').getForm();
	        	if(!form.isValid()){
	        		alert("请在出现红框的地方选择值!");
	        		return;
	        	}

            	Ext.getBody().mask("正在执行....");
            	var equipments = new Array();
            	equipStore.each(function(record){
            		equipments.push(record.data);
            	});
            	

            	var params={
            		str_out_id:store_out_combox.getValue(),
            		str_in_id:store_in_combox.getValue(),
            		memo:memo_textfield.getValue(),
            		adjustType:adjustType_combox.getValue(),
            		adjust_id_borrow:adjust_id_borrow_textfield.getValue()
            	}
				Ext.Ajax.request({
					url:Ext.ContextPath+'/adjust/adjuestReturn.do',
					method:'POST',
					timeout:600000000,
					headers:{ 'Content-Type':'application/json;charset=UTF-8'},
					params:params,
					jsonData:equipments,
					success:function(response){
						var obj=Ext.decode(response.responseText);
						//store_id_temp=null;
						Ext.Msg.alert("消息","调拨归还成功!");
						equipStore.removeAll();
						Ext.getBody().unmask();
						store_out_combox.enable();
						store_in_combox.enable();
					},
					failure:function(){
						Ext.getBody().unmask();
					}
				});
            }
            
            })
		}}]
	});
	
	
	var viewPort=Ext.create('Ext.container.Viewport',{
		layout:'fit',
		items:[step1]
	});

});