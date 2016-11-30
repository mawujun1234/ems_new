Ext.define("Ems.baseinfo.Supplier",{
	extend:"Ext.data.Model",
	idProperty:'id',
	fields:[
		{name:'id',type:'string'},
		{name:'memo',type:'string'},
		{name:'name',type:'string'},
		{name:'sname',type:'string'},
		{name:'status',type:'bool'},
		{name:'website',type:'string'}
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
			read:Ext.ContextPath+'/supplier/load.do',
			create:Ext.ContextPath+'/supplier/create.do',
			update:Ext.ContextPath+'/supplier/update.do',
			destroy:Ext.ContextPath+'/supplier/destroy.do'
		}
	}
});