Ext.define("Ems.baseinfo.Brand",{
	extend:"Ext.data.Model",
	//idProperty:'id',
	fields:[
		{name:'id',type:'string'},
		{name:'name',type:'string'},
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
			read:Ext.ContextPath+'/brand/load.do',
			create:Ext.ContextPath+'/brand/create.do',
			update:Ext.ContextPath+'/brand/update.do',
			destroy:Ext.ContextPath+'/brand/destroy.do'
		}
	}
});