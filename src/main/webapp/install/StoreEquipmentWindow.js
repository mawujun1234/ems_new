/**
 * 作业单位的手持设备情况
 */
Ext.define('Ems.install.StoreEquipmentWindow',{
	extend:'Ext.window.Window',
	requires: [
	     'Ems.baseinfo.Equipment'
	],
	layout:'fit',
	store_id:null,
	store_name:null,
	initComponent: function () {
		var me=this;
		var equip_store=Ext.create('Ext.data.Store',{
			//fields:[],
			pageSize:50,
			autoLoad:false,
			model:'Ems.baseinfo.Equipment',
			proxy:{
				type:'ajax',
				url:Ext.ContextPath+'/equipment/queryByStore.do',
				reader:{
					type:'json',
					rootProperty:'root'
				}
			}
		});
		
		
//		var store_combox=Ext.create('Ext.form.field.ComboBox',{
//	        fieldLabel: '<b>仓库</b>',
//	        labelAlign:'right',
//            labelWidth:40,
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
//	        store:Ext.create('Ext.data.Store', {
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
//	    }); 
		var store_combox = Ext.create('Ems.baseinfo.StoreCombo', {
				edit : true,
				allowBlank : false,
				fieldLabel : '<b>仓库</b>'
			});
			//alert(store_combox.getStore().model.getName( ));
	    var store_model=Ext.create(store_combox.getStore().model.getName( ),{id:me.store_id,name:me.store_name});
	    store_combox.setValue(store_model);
	    
		
//	    var subtype_combox=Ext.create('Ems.baseinfo.SubtypeCombo',{
//			labelAlign:'right',
//			labelWidth:40,
//			editable:false,
//			hidden:true,
//			minChars:-1//表示默认点击的时候就查询出所有的数据
//			,listeners:{
//				change:function(field,newValue, oldValue){
//					prod_combox.clearValue( );
//					prod_combox.getStore().getProxy().extraParams={equipmentSubtype_id:newValue};
//					prod_combox.getStore().reload();
//				}
//			}
//		});
//		var prod_combox=Ext.create('Ems.baseinfo.ProdCombo',{
//			labelAlign:'right',
//			labelWidth:40,
//			hidden:true,
//			editable:false,
//			minChars:-1
//		});
		var brand_combox=Ext.create('Ems.baseinfo.BrandCombo',{
			labelAlign:'right',
			labelWidth:40,
			editable:false,
			containAll:true,
			minChars:-1
		});
		var supplier_combox=Ext.create('Ems.baseinfo.SupplierCombo',{
			labelAlign:'right',
			labelWidth:40,
			editable:true,
			width:260,
			//containAll:true,
			minChars:2
		});
		
		equip_store.on("beforeload",function(store){
			store.getProxy().extraParams={
				store_id:store_combox.getValue(),
					subtype_id:subtype_id,//subtype_combox.getValue(),
					prod_id:prod_id,//mbox.getValue(),
					style:style,
					brand_id:brand_combox.getValue(),
					supplier_id:supplier_combox.getValue(),
					level:level
			};
		});
		var level=1;
		var subtype_id=null;
		var prod_id=null;
		var style=null;
		var button=Ext.create("Ext.button.Button",{
			text:'查询',
			margin:'0 0 0 5',
			iconCls:'icon-search',
			handler:function(){
				equip_store.load();
			}
		});
		equip_store.load();
		
		var equip_grid=Ext.create('Ext.grid.Panel',{
			//flex:1,
//			tbar:{
//			  xtype: 'container',
//			  layout: 'anchor',
//			  defaults: {anchor: '0'},
//			  defaultType: 'toolbar',
//			  items: [{
//			    items: [store_combox,subtype_combox,prod_combox] // toolbar 1
//			  }, {
//			    items: [brand_combox,supplier_combox,button] // toolbar 2
//			  }]
//			},
			tbar:{
			    items: [store_combox,brand_combox,supplier_combox,button] // toolbar 1
			  },
			store:equip_store,
	    	columns: [Ext.create('Ext.grid.RowNumberer'),
	    	          {header: '设备类型', dataIndex: 'subtype_name',width:120,renderer:function(value,metaData,record,rowIndex){
	    	          	if(record.get("subtype_id")=="total"){
	    	          		return "<a href='javascript:void(0);'>"+value+"</a>";
	    	          	} else {
	    	          		return value;
	    	          	}
	    	          }},
	    	          {header: '品名', dataIndex: 'prod_name',flex:1},
	    	          {header: '设备型号', dataIndex: 'style',width:120,hidden:true},
	    	          {dataIndex:'prod_spec',text:'规格',flex:1,renderer:function(value,metadata,record){
						metadata.tdAttr = "data-qtip='" + value+ "'";
					    return value;
						}
					  },
	    	          {header: '条码', dataIndex: 'ecode',width:120,hidden:true},
	    	          {header: '品牌', dataIndex: 'brand_name',width:120,hidden:true},
	    	          {header: '供应商', dataIndex: 'supplier_name',hidden:true},     
	    	         // {header: '状态', dataIndex: 'status_name',width:100,hidden:true},
	    	          {header: '数量', dataIndex: 'num',width:70,renderer:function(value,metaData,record,rowIndex){
	    	          	if(level==3 || record.get("subtype_id")=="total"){
	    	          		return value;
	    	          	} else {
	    	          		return "<a href='javascript:void(0);'>"+value+"</a>";
	    	          	}
	    	          	
	    	          }}
	    	],
	    	bbar:[{
		        xtype: 'pagingtoolbar',
		        store: equip_store,  
		        //dock: 'bottom',
		        displayInfo: true
		  }]
		});
		equip_grid.on('cellclick',function(grid, td, cellIndex, record, tr, rowIndex, e){
			//alert(equip_grid.columns );
			var columns=equip_grid.columns;
			//alert(columns[cellIndex].dataIndex);
			//console.log(level);
			
			var dataIndex=equip_grid.headerCt.getHeaderAtIndex(cellIndex).dataIndex;
			//console.log(dataIndex);
			if(level==1&&dataIndex=="num"){
				level=2;
				subtype_id=record.get("subtype_id");
				//var subtype_model=Ext.createModel(subtype_combox.getStore().model.getName( ),{id:record.get("subtype_id"),text:record.get("subtype_name")});
	    		//subtype_combox.setValue(subtype_model);

				equip_store.load({
					callback:function(records, operation, success){			
						for(var i=0;i<columns.length;i++){
							//console.log(columns[i].dataIndex);
							if(columns[i].dataIndex=="num" || columns[i].dataIndex=="ecode"){
								//columns[i].hide();
							} else{
								columns[i].show();
							}
						}
					}
				});
			} else if(level==2&&dataIndex=="num"){
				level=3;
				prod_id=record.get("prod_id");
				style=record.get("style");
				equip_store.load({
				callback:function(records, operation, success){
					
					for(var i=0;i<columns.length;i++){
						//console.log(columns[i].dataIndex);
						if(columns[i].dataIndex=="num"){
							//columns[i].hide();
						} else{
							columns[i].show();
						}
					}
				}});
				
			}

			//----点击合计回退到前面的时候
			if(level==2&&equip_store.getAt(rowIndex).get("subtype_id")=="total"&&dataIndex=="subtype_name"){
				level=1;
				//subtype_combox.clearValue( );
				subtype_id=null;
				equip_store.load({
				callback:function(records, operation, success){
					
					for(var i=0;i<columns.length;i++){
						//console.log(columns[i].dataIndex);
						var dataIndex=columns[i].dataIndex;
						if(dataIndex=="num" ||dataIndex=="subtype_name" || dataIndex=="prod_name"){
							//columns[i].hide();
						} else{
							columns[i].hide();
						}
					}
				}});
			}
			if(level==3&&equip_store.getAt(rowIndex).get("subtype_id")=="total"&&dataIndex=="subtype_name"){
				level=2;
				//subtype_combox.clearValue( );
				prod_id=null;
				style=null;
				equip_store.load({
				callback:function(records, operation, success){
					
					for(var i=0;i<columns.length;i++){
						//console.log(columns[i].dataIndex);
						var dataIndex=columns[i].dataIndex;
						if(dataIndex=="num" ||dataIndex=="subtype_name" || dataIndex=="prod_name"){
							//columns[i].hide();
						} else{
							columns[i].hide();
						}
					}
				}});
			}


		});
		
		me.items=[equip_grid];
		me.callParent();	
	}
	
});