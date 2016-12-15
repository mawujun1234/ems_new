Ext.define("Ems.store.Order",{
	extend:"Ext.data.Model",
	idProperty:'id',
	fields:[
		{name:'id',type:'string'},
		{name:'orderNo',type:'string'},
		{name:'brand_id',type:'string'},
		{name:'operater',type:'string'},
		{name:'orderDate',type:'string'},
		{name:'createDate',type:'date', dateFormat: 'Y-m-d'},
		{name:'supplier_id',type:'string'},
		{name:'project_id',type:'string'},
		//{name:'orderNum',type:'int'},
		//{name:'prod_id',type:'string'},
		{name:'store_id',type:'string'},
		//{name:'style',type:'string'},
		//{name:'subtype_id',type:'string'},
		//{name:'supplier_id',type:'string'},
		//{name:'totalNum',type:'int'},
		//{name:'type_id',type:'string'},
		//{name:'unitPrice',type:'float'},
		//{name:'totalprice',type:'int'},
		{name:'status',type:'string'},
		{name:'orderType',type:'string'},
		{name:'orderType_name',type:'string'},

		
		//{name:'brand_name',type:'string'},
		//{name:'prod_name',type:'string'},
		//{name:'prod_spec',type:'string'},
		//{name:'prod_unit',type:'string'},
		//{name:'type_name',type:'string'},
		//{name:'subtype_name',type:'string'},
		//{name:'supplier_name',type:'string'},
		{name:'store_name',type:'string'},
		{name:'project_name',type:'string'},
		{name:'supplier_name',type:'string'},
		//{name:'printNum',type:'int'},
		//{name:'exportStatus',type:'bool'},
		{name:'operater_name',type:'string'},
		{name:'status_name',type:'string'}
	],
	proxy:{
		type:'ajax',
		actionMethods: { read: 'POST' },
		timeout :600000,
		headers:{ 'Accept':'application/json;'},
		writer:{
			type:'json',
			writeRecordId:true,
			writeAllFields:true
		},
		reader:{
			type:'json'
			///rootProperty:'root',
			//successProperty:'success',
			//totalProperty:'total'		
		},
		api:{
			read:Ext.ContextPath+'/order/load.do',
			create:Ext.ContextPath+'/order/create.do',
			update:Ext.ContextPath+'/order/update.do',
			destroy:Ext.ContextPath+'/order/destroy.do'
		}
	}
});