Ext.require("y.org.PositionType");
Ext.require("y.org.PositionTypeGrid");
Ext.require("y.org.PositionTypeForm");
Ext.onReady(function(){
	var grid=Ext.create('y.org.PositionTypeGrid',{
		region:'center'
	});
	
	var viewPort=Ext.create('Ext.container.Viewport',{
		layout:'border',
		items:[grid]
	});



});