//Ext.defineModel("Ems.baseinfo.CustomerContact",{
Ext.define("Ems.baseinfo.CustomerContact",{
	extend:"Ext.data.Model",
	idProperty:'id',
	fields:[
		{name:'id',type:'string'},
		{name:'address',type:'string'},
		{name:'contact',type:'string'},
		{name:'customer_id',type:'string'},
		{name:'email',type:'string'},
		{name:'fax',type:'string'},
		{name:'mobile',type:'string'},
		{name:'phone',type:'string'},
		{name:'position',type:'string'},
		{name:'postcode',type:'string'}
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
//			rootProperty:'root',
//			successProperty:'success',
//			totalProperty:'total'		
		},
		api:{
			read:Ext.ContextPath+'/customerContact/load.do',
			create:Ext.ContextPath+'/customerContact/create.do',
			update:Ext.ContextPath+'/customerContact/update.do',
			destroy:Ext.ContextPath+'/customerContact/destroy.do'
		}
	}
});