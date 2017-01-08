Ext.define("Ems.install.InstallIn",{
	extend:"Ext.data.Model",
	idProperty:'id',
	fields:[
		{name:'id',type:'string'},
		{name:'memo',type:'string'},
		{name:'operateDate',type:'date', dateFormat: 'Y-m-d'},
		{name:'operater',type:'string'},
		{name:'store_id',type:'string'},
		{name:'type',type:'string'},
		{name:'workUnit_id',type:'string'},
		
		{name:'type_name',type:'string'},
		{name:'workUnit_name',type:'string'},
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
			read:Ext.ContextPath+'/installIn/load.do',
			create:Ext.ContextPath+'/installIn/create.do',
			update:Ext.ContextPath+'/installIn/update.do',
			destroy:Ext.ContextPath+'/installIn/destroy.do'
		}
	}
});