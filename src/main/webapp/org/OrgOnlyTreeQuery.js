/**
  * 只能选择组织单元
 */
Ext.define('y.org.OrgOnlyTreeQuery', {
    extend: 'Ext.tree.Panel',
    requires:['y.org.Org'],
    displayField:'name',
    exclude_id:null,//这个节点，及子节点不显示，防止循环嵌套
    initComponent: function () {
		var me = this;

        me.store = Ext.create('Ext.data.TreeStore', {
	       	autoLoad:true,
	       	nodeParam :'parent_id',//传递到后台的数据，默认是node
	       	//model:'y.org.Org',
	       	fields:['id','name','leaf','orgno','remark','type'],
			root: {
			    expanded: true,
			    name:"宁波东望" 
			},
			proxy:{
				type:'ajax',
				url:Ext.ContextPath+'/org/queryOnlyOrg.do',
				actionMethods: { read: 'POST' },
				extraParams:{
					exclude_id:me.exclude_id
				},
				timeout :600000,
				headers:{ 'Accept':'application/json;'}
			
			}
//			listeners:{
//				beforeload:function(store,operation){
//					//var record=me.getSelectionModel( ).getSelection( );
//					//console.log(record);
//					//store.getProxy().extraParams=Ext.apply(store.getProxy().extraParams,{
//					//	parent_no:"root"//record.get("orgno")
//					//});
//				}
//			}
		});
//		me.initAction();
       
		me.callParent(arguments);
    }
   
});
