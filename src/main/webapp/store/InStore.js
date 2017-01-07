Ext.define("Ems.store.InStore",{
	extend:"Ext.data.Model",
	idProperty:'id',
	fields:[
		{name:'id',type:'string'},
		{name:'memo',type:'string'},
		{name:'operateDate',type:'string'},
		{name:'operater',type:'string'},
		{name:'store_id',type:'string'},
		
		{name:'operater_name',type:'string'},
		{name:'store_name',type:'string'}
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
			read:Ext.ContextPath+'/inStore/load.do',
			create:Ext.ContextPath+'/inStore/create.do',
			update:Ext.ContextPath+'/inStore/update.do',
			destroy:Ext.ContextPath+'/inStore/destroy.do'
		}
	}
});