Ext.define("Ems.repair.Repair",{
	extend:"Ext.data.Model",
	idProperty:'id',
	fields:[
		{name:'id',type:'string'},
		{name:'ecode',type:'string'},
		{name:'prod_id',type:'string'},
		{name:'rpa_id',type:'string'},
		
		{name:'installIn_id',type:'string'},
		{name:'repair_date',type:'string', dateFormat: 'Y-m-d'},
		{name:'workunit_id',type:'string'},
		{name:'broken_memo',type:'string'},
		
		{name:'rpa_in_date',type:'string', dateFormat: 'Y-m-d'},
		{name:'rpa_in_oper_id',type:'string'},
		{name:'rpa_out_date',type:'string', dateFormat: 'Y-m-d'},
		{name:'rpa_out_oper_id',type:'string'},
		{name:'rpa_user_id',type:'string'},
		{name:'rpa_type',type:'string'},
		{name:'status',type:'string'},
		{name:'str_in_date',type:'date', dateFormat: 'Y-m-d'},
		{name:'str_in_id',type:'string'},
		
		{name:'str_in_oper_id',type:'string'},
		{name:'str_out_date',type:'string', dateFormat: 'Y-m-d'},
		{name:'str_out_id',type:'string'},
		
		{name:'str_out_oper_id',type:'string'},
		{name:'broken_memo',type:'string'},
		{name:'broken_reson',type:'string'},
		{name:'memo',type:'string'},
		
		{name:'send_date',type:'string', dateFormat: 'Y-m-d'},
		{name:'sendno',type:'string'},
		{name:'receive_date',type:'string', dateFormat: 'Y-m-d'},
		{name:'repairFactory',type:'string'},
		
		{name:'workunit_name',type:'string'},
		{name:'rpa_name',type:'string'},
		{name:'rpa_user_name',type:'string'},
		{name:'str_in_name',type:'string'},
		{name:'str_out_name',type:'string'},
		{name:'brand_name',type:'string'},
		{name:'prod_name',type:'string'},
		{name:'prod_spec',type:'string'},
		{name:'subtype_name',type:'string'},
		{name:'supplier_name',type:'string'},
		{name:'equipment_style',type:'string'},
		{name:'equipment_status',type:'string'},
		{name:'equipment_status_name',type:'string'},
		{name:'status_name',type:'string'},
		{name:'rpa_type_name',type:'string'},
		
		{name:'depreci_year',type:'string'},
		{name:'depreci_month',type:'string'},
		{name:'depreci_day',type:'string'},
		
		{name:'handler_method',type:'string'},
		
		{name:'scrap_id',type:'string'}
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
			read:Ext.ContextPath+'/repair/load.do',
			create:Ext.ContextPath+'/repair/create.do',
			update:Ext.ContextPath+'/repair/update.do',
			destroy:Ext.ContextPath+'/repair/destroy.do'
		}
	}
});