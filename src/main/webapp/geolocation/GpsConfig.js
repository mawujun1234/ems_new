Ext.define("Ems.geolocation.GpsConfig",{
	extend:"Ext.data.Model",
	idProperty:'id',
	fields:[
		{name:'id',type:'int'},
		{name:'interval',type:'int'}
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
			read:Ext.ContextPath+'/gpsConfig/load.do',
			create:Ext.ContextPath+'/gpsConfig/create.do',
			update:Ext.ContextPath+'/gpsConfig/update.do',
			destroy:Ext.ContextPath+'/gpsConfig/destroy.do'
		}
	}
});