Ext.define("Ems.store.InStoreList",{
	extend:"Ext.data.Model",
	idProperty:'id',
	fields:[
		{name:'id',type:'string'},
		{name:'ecode',type:'string'},
		{name:'inStore_id',type:'string'},
		{name:'isnew',type:'bool'},
		
		{name:'subtype_name',type:'string'},
		{name:'prod_name',type:'string'},
		{name:'prod_spec',type:'string'},
		{name:'brand_name',type:'string'},
		{name:'supplier_name',type:'string'},
		{name:'style',type:'string'},
		{name:'num',type:'int'}
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
			read:Ext.ContextPath+'/inStoreList/load.do',
			create:Ext.ContextPath+'/inStoreList/create.do',
			update:Ext.ContextPath+'/inStoreList/update.do',
			destroy:Ext.ContextPath+'/inStoreList/destroy.do'
		}
	}
});