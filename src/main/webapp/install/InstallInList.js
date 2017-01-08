Ext.define("Ems.install.InstallInList",{
	extend:"Ext.data.Model",
	idProperty:'id',
	fields:[
		{name:'id',type:'string'},
		{name:'ecode',type:'string'},
		{name:'installIn_id',type:'string'},
		{name:'isBad',type:'bool'},
		{name:'isnew',type:'bool'},
		{name:'installInListType',type:'string'},
		{name:'installout_id',type:'string'},
		{name:'project_id',type:'string'},
		
		{name:'installInListType_name',type:'string'},
		{name:'subtype_name',type:'string'},
		{name:'prod_name',type:'string'},
		{name:'prod_spec',type:'string'},
		{name:'brand_name',type:'string'},
		{name:'supplier_name',type:'string'},
		{name:'style',type:'string'},
		{name:'num',type:'int'}
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
			read:Ext.ContextPath+'/installInList/load.do',
			create:Ext.ContextPath+'/installInList/create.do',
			update:Ext.ContextPath+'/installInList/update.do',
			destroy:Ext.ContextPath+'/installInList/destroy.do'
		}
	}
});