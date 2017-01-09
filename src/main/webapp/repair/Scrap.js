Ext.define("Ems.repair.Scrap",{
	extend:"Ext.data.Model",
	idProperty:'id',
	fields:[
		{name:'id',type:'string'},
		{name:'ecode',type:'string'},
		{name:'operateDate',type:'date', dateFormat: 'Y-m-d'},
		{name:'operater',type:'string'},
		{name:'reason',type:'string'},
		{name:'repair_id',type:'string'},
		{name:'residual',type:'string'},
		{name:'store_id',type:'string'},
		{name:'status',type:'string'},
		{name:'rpa_id',type:'string'}
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
			read:Ext.ContextPath+'/scrap/load.do',
			create:Ext.ContextPath+'/scrap/create.do',
			update:Ext.ContextPath+'/scrap/update.do',
			destroy:Ext.ContextPath+'/scrap/destroy.do'
		}
	}
});