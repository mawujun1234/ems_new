Ext.define('Ems.permission.UserTabpanel',{
	extend:'Ext.tab.Panel',
	initComponent: function () {
      var me = this;

      me.items=[];
      
      me.createRoleTab();
      me.createMenuTab();
      
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
     
     },
     createMenuTab:function(){
     	var me=this;
     	var store = Ext.create('Ext.data.TreeStore', {
     		autoLoad:true,
	       	nodeParam :'parent_id',//传递到后台的数据，默认是node
	       	//model:'Ems.permission.Menu',
	       	fields:['id','name'],
			root: {
			    expanded: true,
			    //checked:false,
			    name:"根菜单"
			},
			proxy:{
				type: 'ajax',
				extraParams:{expanded:false},
				url:Ext.ContextPath+"/user/queryMenuByUser.do"
			}
		});
		
		
		var tree=Ext.create('Ext.tree.Panel', {
		    title: '可访问的菜单',
		    displayField:'name',
		    store: store,
		    rootVisible: true
		});

		
		me.items.push(tree);
     
     },
     createOrgPositionTab:function(){
     	var me=this;
     	var store = Ext.create('Ext.data.TreeStore', {
     		autoLoad:true,
	       	nodeParam :'parent_id',//传递到后台的数据，默认是node
	       	//model:'Ems.permission.Menu',
	       	fields:['id','name'],
			root: {
			    expanded: true,
			    //checked:false,
			    name:"宁波东望"
			},
			proxy:{
				type: 'ajax',
				extraParams:{expanded:false},
				url:Ext.ContextPath+"/user/queryOrgPositionByUser.do"
			}
		});
		
		
		var tree=Ext.create('Ext.tree.Panel', {
		    title: '所属职位',
		    displayField:'name',
		    store: store,
		    rootVisible: true
		});

		
		me.items.push(tree);
     
     }
})