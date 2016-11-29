Ext.define("Ems.baseinfo.EquipmentType",{
	extend:"Ext.data.Model",
	idProperty:'id',
	fields:[
		{name:'id',type:'string'},
		//{name:'levl',type:'int'},
		{name:'status',type:'string'},
		{name:'name',type:'string'},
		{name:'text',type:'string'},
		//{name:'unit',type:'string'},
		//{name:'spec',type:'string'},
		//{name:'style',type:'string'},
		//{name:'brand_id',type:'string'},
		{name:'parent_id',type:'string'},
		{name:'memo',type:'string'},
		//{name:'type',type:'string'},
		//{name:'type_parent_id',type:'string'},
		
		//{name:'type_name',type:'string'},
		
		{name:'status_name',type:'String'}
		//{name:'brand_name',type:'string'}
	]
//	proxy:{
//		type:'ajax',
//		actionMethods: { read: 'POST' },
//		timeout :600000,
//		headers:{ 'Accept':'application/json;'},
//		writer:{
//			type:'json',
//			writeAllFields:true
//		},
//		api:{
//			read:Ext.ContextPath+'/equipmentType/query.do',
//			load : Ext.ContextPath+'/equipmentType/load.do',
//			create:Ext.ContextPath+'/equipmentType/create.do',
//			update:Ext.ContextPath+'/equipmentType/update.do',
//			destroy:Ext.ContextPath+'/equipmentType/destroy.do'
//		}
//	}
});