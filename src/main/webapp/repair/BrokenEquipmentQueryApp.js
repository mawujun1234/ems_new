Ext.require("Ems.repair.Repair");

Ext.onReady(function(){
//	var store_combox=Ext.create('Ext.form.field.ComboBox',{
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
				fieldLabel: '<b>仓库</b>'
			});
	    
	 var query_button=Ext.create('Ext.Button',{
	 	text:'查询',
	 	iconCls:'icon-search',
	 	handler:function(){
	 		if(!store_combox.getValue()){
	 			alert("请先选择一个仓库!");
	 			return;
	 		}
	 		equipStore.getProxy().extraParams={
	 			store_id:store_combox.getValue()
	 		}
	 		equipStore.reload();
	 	}
	 });
	 
	 var equipStore = Ext.create('Ext.data.Store', {
        autoDestroy: true,
        pageSize:50,
        model: 'Ems.repair.Repair',
        autoLoad:false,
        proxy: {
	        type: 'ajax',
	        url: '/repair/queryBrokenEquipment.do',  // url that will load data with respect to start and limit params
	        reader: {
	            type: 'json',
	            rootProperty: 'root',
	            totalProperty: 'total'
	        }
	    }
    });

	var equip_grid=Ext.create('Ext.grid.Panel',{
		flex:1,
		store:equipStore,
    	columns: [Ext.create('Ext.grid.RowNumberer'),
    			  {header: '条码', dataIndex: 'ecode',width:150},
    	          {header: '设备类型', dataIndex: 'subtype_name',width:120},
    	          {header: '品名', dataIndex: 'prod_name'},
    	          {header: '品牌', dataIndex: 'brand_name',width:120},
    	          {header: '供应商', dataIndex: 'supplier_name'},
    	          {header: '设备型号', dataIndex: 'style',width:120},
    	          {header: '规格', dataIndex: 'prod_spec',flex:1,renderer:function(value,metadata,record){
						metadata.tdAttr = "data-qtip='" + value+ "'";
					    return value;
					}
				  },
    	         // {header: '仓库', dataIndex: 'store_name'},
    	          //{header: '数量', dataIndex: 'serialNum',width:70},
  
    	          {header: '状态', dataIndex: 'status_name',width:100}
    	          ],
        tbar:[store_combox,query_button,'-','-',
              {text:'一键生成维修单',
               //icon:'../icons/gnext.png',
        	   iconCls:'icon-wrench',
        	   handler:function(){
        		   Ext.MessageBox.confirm('确认', '您确认把<b>所有</b>坏件生成维修单并出库吗?', function(btn){
					   if(btn=='yes'){
							createRepair();
						}
					});
        	   }
        	}
        ]
//        bbar:{
//	        xtype: 'pagingtoolbar',
//	        store: equipStore,  
//	        dock: 'bottom',
//	        displayInfo: true
//	  }
	});
	//生成维修单
	function createRepair(){
		var repair_combox=Ext.create('Ext.form.field.ComboBox',{
	        fieldLabel: '<b>维修中心</b>',
	        labelAlign:'right',
            labelWidth:60,
	        name: 'rpa_id',
		    displayField: 'name',
		    valueField: 'id',
	        allowBlank: false,
	        store:Ext.create('Ext.data.Store', {
		    	fields: ['id', 'name'],
			    proxy:{
			    	type:'ajax',
			    	extraParams:{type:2,look:true},
			    	url:Ext.ContextPath+"/store/queryCombo.do",
			    	reader:{
			    		type:'json',
			    		rootProperty:'root'
			    	}
			    }
		   })
		});
		var win=Ext.create('Ext.window.Window',{
			items:[repair_combox],
			modal:true,
			//layout:'fit',
			height:120,
			width:240,
			title:'选择维修中心',
			buttons:[{
				text:'出库转维修单',
				handler:function(){
					//alert("还未完成，请稍后!");
					//return;
					if(!repair_combox.getValue()){
						alert("请先选择一个维修中心!");
						return;
					}
					Ext.getBody().mask("正在出库,请稍后....");
					Ext.Ajax.request({
						url:Ext.ContextPath+'/repair/brokenEquipment2Repair.do',
						params:{store_id:store_combox.getValue(),rpa_id:repair_combox.getValue()},
						success:function(response){
							var obj=Ext.decode(response.responseText);
							
							if(obj.success){
								alert("出库成功!");
								win.close();
								equipStore.reload();
								Ext.getBody().unmask();
							} else {
								alert("出库失败!,请重新尝试 一次!");	
							}
						}
					});
				}
			}]
		});
		win.show();
	}
	
	var viewPort=Ext.create('Ext.container.Viewport',{
		layout:'fit',
		items:[equip_grid]
	});

});