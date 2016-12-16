Ext.define("Ems.store.InStore",{
	extend:"Ext.data.Model",
	idProperty:'id',
	fields:[
		{name:'id',type:'string'},
		{name:'memo',type:'string'},
		{name:'operateDate',type:'string'},
		{name:'operater',type:'string'},
		{name:'store_id',type:'string'},
		
		{name:'operater_name',type:'string'},
		{name:'store_name',type:'string'}
	],
	associations:[
	]
});