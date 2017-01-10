Ext.define("Ems.task.HitchType",{
	extend:"Ext.data.Model",
	idProperty:'id',
	fields:[
		{name:'id',type:'int'},
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
			read:Ext.ContextPath+'/hitchType/load.do',
			create:Ext.ContextPath+'/hitchType/create.do',
			update:Ext.ContextPath+'/hitchType/update.do',
			destroy:Ext.ContextPath+'/hitchType/destroy.do'
		}
	}
});