Ext.define("Ems.install.Borrow",{
	extend:"Ext.data.Model",
	idProperty:'id',
	fields:[
		{name:'id',type:'string'},
		{name:'memo',type:'string'},
		{name:'operateDate',type:'string'},
		{name:'operater',type:'string'},
		{name:'project_id',type:'string'},
		{name:'store_id',type:'string'},
		{name:'workUnit_id',type:'string'},
		//{name:'isAllReturn',type:'bool'},
		{name:'status',type:'string'},
		
		{name:'status_name',type:'string'},		
		{name:'project_name',type:'string'},
		{name:'store_name',type:'string'},
		{name:'operater_name',type:'string'},
		{name:'workUnit_name',type:'string'}
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
			read:Ext.ContextPath+'/borrow/load.do',
			create:Ext.ContextPath+'/borrow/create.do',
			update:Ext.ContextPath+'/borrow/update.do',
			destroy:Ext.ContextPath+'/borrow/destroy.do'
		}
	}
});