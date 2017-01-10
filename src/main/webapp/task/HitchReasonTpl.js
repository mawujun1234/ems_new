Ext.define("Ems.task.HitchReasonTpl",{
	extend:"Ext.data.Model",
	idProperty:'id',
	fields:[
		{name:'id',type:'int'},
		{name:'name',type:'string'},
		{name:'tpl',type:'string'},
		
		{name:'hitchType_id',type:'string'}
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
			read:Ext.ContextPath+'/hitchReasonTpl/load.do',
			create:Ext.ContextPath+'/hitchReasonTpl/create.do',
			update:Ext.ContextPath+'/hitchReasonTpl/update.do',
			destroy:Ext.ContextPath+'/hitchReasonTpl/destroy.do'
		}
	}
});