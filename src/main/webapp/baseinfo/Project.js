Ext.define("Ems.baseinfo.Project",{
	extend:"Ext.data.Model",
	idProperty:'id',
	fields:[
		{name:'id',type:'string'},
		{name:'memo',type:'string'},
		{name:'name',type:'string'},
		{name:'sname',type:'string'},
		{name:'status',type:'bool'}
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
			read:Ext.ContextPath+'/project/load.do',
			create:Ext.ContextPath+'/project/create.do',
			update:Ext.ContextPath+'/project/update.do',
			destroy:Ext.ContextPath+'/project/destroy.do'
		}
	}
});