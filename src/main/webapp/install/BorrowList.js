Ext.define("Ems.install.BorrowList",{
	extend:"Ext.data.Model",
	idProperty:'id',
	fields:[
		{name:'id',type:'string'},
		{name:'borrow_id',type:'string'},
		{name:'ecode',type:'string'},
		{name:'isReturn',type:'bool'},
		{name:'isnew',type:'bool'},
		{name:'memo',type:'string'},
		{name:'returnDate',type:'date', dateFormat: 'Y-m-d'},
		{name:'borrowListType',type:'string'},
		
		{name:'borrowListType_name',type:'string'},
		{name:'subtype_name',type:'string'},
		{name:'prod_name',type:'string'},
		{name:'prod_spec',type:'string'},
		{name:'brand_name',type:'string'},
		{name:'supplier_name',type:'string'},
		{name:'style',type:'string'}
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
			read:Ext.ContextPath+'/borrowList/load.do',
			create:Ext.ContextPath+'/borrowList/create.do',
			update:Ext.ContextPath+'/borrowList/update.do',
			destroy:Ext.ContextPath+'/borrowList/destroy.do'
		}
	}
});