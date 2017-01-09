Ext.define("Ems.adjust.Adjust",{
	extend:"Ext.data.Model",
	idProperty:'id',
	fields:[
		{name:'id',type:'string'},
		{name:'memo',type:'string'},
		{name:'status',type:'string'},
		{name:'returnStatus',type:'string'},
		{name:'str_in_date',type:'date', dateFormat: 'Y-m-d'},
		{name:'str_in_id',type:'string'},
		{name:'str_in_oper_id',type:'string'},
		{name:'str_out_date',type:'date', dateFormat: 'Y-m-d'},
		{name:'str_out_id',type:'string'},
		{name:'str_out_oper_id',type:'string'},
		{name:'adjustType',type:'string'},
		
		{name:'adjustType_name',type:'string'},
		{name:'str_in_name',type:'string'},
		{name:'str_out_name',type:'string'},
		{name:'status_name',type:'string'},
		{name:'ecode',type:'string'},
		{name:'returnStatus_name',type:'string'},
		{name:'project_id',type:'string'},
		{name:'prod_id',type:'string'},
		
		
		{name:'brand_name',type:'string'},
		{name:'prod_name',type:'string'},
		{name:'prod_spec',type:'string'},
		{name:'subtype_name',type:'string'},
		{name:'supplier_name',type:'string'},
		{name:'prod_style',type:'string'}
		
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
			read:Ext.ContextPath+'/adjust/load.do',
			create:Ext.ContextPath+'/adjust/create.do',
			update:Ext.ContextPath+'/adjust/update.do',
			destroy:Ext.ContextPath+'/adjust/destroy.do'
		}
	}
});