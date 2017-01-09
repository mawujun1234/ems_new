Ext.define("Ems.install.InstallOutType",{
	extend:"Ext.data.Model",
	idProperty:'id',
	fields:[
		{name:'id',type:'string'},
		{name:'memo',type:'string'},
		{name:'name',type:'string'}
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
			read:Ext.ContextPath+'/installOutType/load.do',
			create:Ext.ContextPath+'/installOutType/create.do',
			update:Ext.ContextPath+'/installOutType/update.do',
			destroy:Ext.ContextPath+'/installOutType/destroy.do'
		}
	}
});