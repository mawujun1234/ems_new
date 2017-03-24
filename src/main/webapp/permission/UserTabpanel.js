Ext.define('Ems.permission.UserTabpanel',{
	extend:'Ext.tab.Panel',
	initComponent: function () {
      var me = this;

      me.items=[];
      
      me.createRoleTab();
      me.callParent();
     },
     createRoleTab:function(){
     	var me=this;
     	var store = Ext.create('Ext.data.TreeStore', {
     		//autoLoad:true,
		    root: {
		    	id:'root',
		        expanded: true,
		        name:"角色" 
		    },
		    nodeParam :'parent_id',
		    //model:'Ems.permission.Role',
		    fields:['id','name']
//		    proxy:{
//		    	type: 'ajax',
//		    	extraParams:{
//		    		user_id:me.user_id
//		    	},
//		    	url : Ext.ContextPath+'/user/queryRoleByUser.do',
//			    headers:{ 'Accept':'application/json;'},
//			    actionMethods: { read: 'POST' }
////			    reader:{
////					type:'json',
////					rootProperty:'root',
////					successProperty:'success',
////					totalProperty:'total'		
////				}
//		    	
//		    }
		    
		});
		
		
		var tree=Ext.create('Ext.tree.Panel', {
		    title: '所属角色',
		    displayField:'name',
		    store: store,
		    rootVisible: false
		});
		
		Ext.Ajax.request({
			url : Ext.ContextPath+'/user/queryRoleByUser.do',
			headers:{ 'Accept':'application/json;'},
			params:{
				user_id:me.user_id
			},
			success:function(response){
				var array=Ext.decode(response.responseText);
//				for(var i=0;i<array.length;i++){
//					store.getRootNode().appendChild(array[i]);
//				}
				store.getRootNode().appendChild(array);
				tree.expandAll();
			}
		
		});
		
		me.items.push(tree);
     
     }
})