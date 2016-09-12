Ext.require("y.permission.User");
Ext.require("y.permission.UserGrid");
Ext.require("y.permission.UserForm");
Ext.onReady(function(){
	var grid=Ext.create('y.permission.UserGrid',{
		region:'center'
	});
	
	var viewPort=Ext.create('Ext.container.Viewport',{
		layout:'border',
		items:[grid]
	});



});