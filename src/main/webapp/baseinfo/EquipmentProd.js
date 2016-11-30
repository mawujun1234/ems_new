Ext.define("Ems.baseinfo.EquipmentProd",{
	extend:"Ext.data.Model",
	idProperty:'id',
	fields:[
		{name:'id',type:'string'},
		{name:'status',type:'bool'},
		{name:'name',type:'string'},
		{name:'text',type:'string'},
		{name:'unit',type:'string'},
		{name:'spec',type:'string'},
		{name:'style',type:'string'},
		{name:'brand_id',type:'string'},
		//{name:'parent_id',type:'string'},
		{name:'subtype_id',type:'string'},
		{name:'memo',type:'string'},
		//{name:'type',type:'string'},
		{name:'quality_month',type:'int'},
		{name:'lock_style',type:'bool'},
		//{name:'type_parent_id',type:'string'},
		{name:'depreci_year',type:'string'},
		
		{name:'id_suffix',type:'string'},
		{name:'type_name',type:'string'},
		
		{name:'status_name',type:'string'},
		{name:'brand_name',type:'string'}
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
			read:Ext.ContextPath+'/equipmentType/load.do',
			create:Ext.ContextPath+'/equipmentType/create.do',
			update:Ext.ContextPath+'/equipmentType/update.do',
			destroy:Ext.ContextPath+'/equipmentType/destroy.do'
		}
	}
});