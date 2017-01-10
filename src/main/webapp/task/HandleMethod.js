Ext.define("Ems.task.HandleMethod",{
	extend:"Ext.data.Model",
	idProperty:'id',
	fields:[
		{name:'id',type:'string'},
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
			read:Ext.ContextPath+'/handleMethod/load.do',
			create:Ext.ContextPath+'/handleMethod/create.do',
			update:Ext.ContextPath+'/handleMethod/update.do',
			destroy:Ext.ContextPath+'/handleMethod/destroy.do'
		}
	}
});