/**
 * 功能的扩展，添加自定义的怎，删，改
 * 添加右键菜单，增，删，改，并且增加工具栏，增，删，改。
 * 后台的类最好继承TreeNode类，这样就可以少写很多代码
 */
Ext.define('y.org.OrgTree', {
    extend: 'Ext.tree.Panel',
    requires:['y.org.Org'],
    displayField:'name',
    initComponent: function () {
		var me = this;

        me.store = Ext.create('Ext.data.TreeStore', {
	       	autoLoad:true,
	       	nodeParam :'parent_id',//传递到后台的数据，默认是node
	       	//model:'y.org.Org',
	       	fields:['id','name','leaf','org_id','remark','type'],
			root: {
			    expanded: true,
			    name:"宁波东望" 
			},
			proxy:{
				type:'ajax',
				url:Ext.ContextPath+'/org/queryNodeVO.do',
				actionMethods: { read: 'POST' },
				timeout :600000,
				extraParams:{dim:'base'},
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
		me.initAction();
       
		me.callParent(arguments);
    },

    initAction:function(){
     	var me = this;
     	var actions=[];
     	
     	var reload = new Ext.Action({
		    text: '刷新',
		    itemId:'reload',
		    handler: function(){
		    	me.onReload();
		    },
		    iconCls: 'icon-refresh'
		});
		//me.addAction(reload);
		actions.push(reload);
		
		var create = new Ext.Action({
		    text: '新建',
		    itemId:'create',
		    handler: function(b){
		    	me.onCreate(null,b);
		    },
		    iconCls: 'icon-plus'
		});
		//me.addAction(create);
		actions.push(create);
		
		var update = new Ext.Action({
		    text: '更新',
		    itemId:'update',
		    handler: function(){
		    	me.onUpdate();
				
		    },
		    iconCls: 'icon-edit'
		});
		actions.push(update);
		
		var destroy = new Ext.Action({
		    text: '删除',
		    itemId:'destroy',
		    handler: function(){
		    	me.onDelete();    
		    },
		    iconCls: 'icon-trash'
		});
		//me.addAction(destroy);
		actions.push(destroy)
     	
       var createPosition = new Ext.Action({
		    text: '新建职位',
		    itemId:'createPosition',
		    handler: function(b){
		    	me.onCreatePosition();
		    },
		    iconCls: 'icon-plus'
		});
		//me.addAction(create);
		actions.push(createPosition);
		
		var updatePosition = new Ext.Action({
		    text: '更新职位',
		    itemId:'updatePosition',
		    handler: function(){
		    	me.onUpdatePosition();
				
		    },
		    iconCls: 'icon-edit'
		});
		actions.push(updatePosition);
		
		var destroyPosition = new Ext.Action({
		    text: '删除职位',
		    itemId:'destroyPosition',
		    handler: function(){
		    	me.onDeletePosition();    
		    },
		    iconCls: 'icon-trash'
		});
		//me.addAction(destroy);
		actions.push(destroyPosition)
		
		
		

		var menu=Ext.create('Ext.menu.Menu', {
			items: actions
		});	
		me.on('itemcontextmenu',function(tree,record,item,index,e){
			menu.showAt(e.getXY());
			e.stopEvent();
		});
		me.contextMenu=menu;
		
		//维度
		//me.selected_dim="sale";
		var dim_combobox=Ext.create('Ext.form.field.ComboBox',{
			fieldLabel: '维度',
			labelWidth:40,
			name: 'dim',
			queryMode: 'local',
			editable:false,
			forceSelection:true,
		    displayField: 'name',
		    valueField: 'key',
		    store: {
		    	autoLoad:true,
			    fields: ['key', 'name'],
			    proxy:{
			    	actionMethods :{read: 'POST'},
				    type: 'ajax',
				    url: Ext.ContextPath+'/enum/queryDimes.do',
				    reader: {
				        type: 'json'
				    }
			    }
			},
			value:'base',
            //allowBlank: false,
            //afterLabelTextTpl: Ext.required,
            //blankText:"菜单类型不允许为空",
			xtype:'combobox',
			listeners:{
				select:function(combo, record){
					//me.selected_dim=record.get("key");
					me.getStore().getProxy().extraParams=Ext.apply(me.getStore().getProxy().extraParams,{
						dim:record.get("key")
					});
					me.getStore().reload({node:me.getStore().getRoot()});
				}
			}
		});
		me.tbar={
			itemId:'action_toolbar',
			layout: {
	               overflowHandler: 'Menu'
	        },
			items:[dim_combobox]
			//,autoScroll:true		
		};
		
    },
     onCreatePosition:function(){
    	var me=this;
		
    	var parent=me.getSelectionModel( ).getLastSelected( )||me.getRootNode( );   
    	if(parent.get("type")=='position'){
    		Ext.Msg.alert("消息","职位下不能新建职位和组织单元");
    		return;
    	}
		
		var child=Ext.create('y.org.Position',{
		    'org_id':parent.get("id"),
		    name:''
		});
		child.set("id",null);
		
		var formpanel=Ext.create('y.org.PositionForm',{});
		formpanel.loadRecord(child);
		
    	var win=Ext.create('Ext.window.Window',{
    		layout:'fit',
    		title:'新增',
    		modal:true,
    		width:400,
    		height:300,
    		closeAction:'hide',
    		items:[formpanel],
    		listeners:{
    			close:function(){
    				me.onReload(parent);
    			}
    		}
    	});
    	win.show();
    },
    
     onUpdatePosition:function(){
    	var me=this;

    	var node=me.getSelectionModel( ).getLastSelected();
    	if(node==null || node.isRoot()){
    		Ext.Msg.alert("提醒","请选择一个不是根节点的节点!");
    		return;
    	}
    	if(node.get("type")!='position'){
    		return;
    	}
//从后台获取这个职位的数据，然后在load到form中
		var formpanel=Ext.create('y.org.PositionForm',{});
		
		y.org.Position.load(node.get("id"),{
			success:function(record){
				formpanel.loadRecord(record);
				var win=Ext.create('Ext.window.Window',{
		    		layout:'fit',
		    		title:'更新',
		    		modal:true,
		    		width:400,
		    		height:300,
		    		closeAction:'hide',
		    		items:[formpanel]
		    	});
		    	win.show();
			}
		});
		
		
    	
    },
    
    onDeletePosition:function(){
    	var me=this;
    	var node=me.getSelectionModel( ).getLastSelected( );

		if(!node){
		    Ext.Msg.alert("消息","请先选择节点");	
			return;
		}
		if(node.get("type")!='position'){
    		return;
    	}
		if(node.isRoot()){
			Ext.Msg.alert("消息","根节点不能删除!");	
			return;
		}
		var parent=node.parentNode;
		Ext.Msg.confirm("删除",'确定要删除吗?', function(btn, text){
				if (btn == 'yes'){
				Ext.Ajax.request({
					url:Ext.ContextPath+'/position/destroy.do',
					jsonData:node.getData(),
					headers:{ 'Accept':'application/json;'},
					success:function(){
						//button.up('window').close();
						me.onReload(parent);
					}
				});
			}
		});
    },
    onCreate:function(){
    	var me=this;

    	var parent=me.getSelectionModel( ).getLastSelected( )||me.getRootNode( );  
    	if(parent.get("type")=='position'){
    		Ext.Msg.alert("消息","职位下不能新建职位和组织单元");
    		return;
    	}
		
		var child=Ext.create('y.org.Org',{
		    'parent_id':parent.get("id"),
		    'dim':me.getStore().getProxy().extraParams.dim
		});
		child.set("id",null);
		
		var formpanel=Ext.create('y.org.OrgForm',{});
		formpanel.loadRecord(child);
		
    	var win=Ext.create('Ext.window.Window',{
    		layout:'fit',
    		title:'新增',
    		modal:true,
    		width:400,
    		height:300,
    		closeAction:'hide',
    		items:[formpanel],
    		listeners:{
    			close:function(){
    				me.onReload(parent);
    			}
    		}
    	});
    	win.show();
    },
    
     onUpdate:function(){
    	var me=this;

    	var node=me.getSelectionModel( ).getLastSelected();
    	if(node==null || node.isRoot()){
    		Ext.Msg.alert("提醒","请选择一个不是根节点的节点!");
    		return;
    	}
    	if(node.get("type")=='position'){
    		return;
    	}
    	y.org.Org.load(node.get("id"), {
		    success: function(org) {
		        var formpanel=Ext.create('y.org.OrgForm',{});
				formpanel.loadRecord(org);
				
		    	var win=Ext.create('Ext.window.Window',{
		    		layout:'fit',
		    		title:'更新',
		    		modal:true,
		    		width:400,
		    		height:300,
		    		closeAction:'hide',
		    		items:[formpanel],
		    		listeners:{
		    			close:function(){
		    				me.onReload(node.parentNode);
		    			}
		    		}
		    	});
		    	win.show();
		    }
		});

		
    },
    
    onDelete:function(){
    	var me=this;
    	var node=me.getSelectionModel( ).getLastSelected( );

		if(!node){
		    Ext.Msg.alert("消息","请先选择节点");	
			return;
		}
		if(node.isRoot()){
			Ext.Msg.alert("消息","根节点不能删除!");	
			return;
		}
		if(node.get("type")=='position'){
    		return;
    	}
		var parent=node.parentNode;
		Ext.Msg.confirm("删除",'确定要删除吗?', function(btn, text){
			if (btn == 'yes'){
				Ext.Ajax.request({
					url:Ext.ContextPath+'/org/destroy.do',
					jsonData:node.getData(),
					params:{
						dim:me.getStore().getProxy().extraParams.dim
					},
					headers:{ 'Accept':'application/json;'},
					success:function(){
						//button.up('window').close();
						me.onReload(parent);
					}
				});
			}
		});
    },
    onReload:function(node){
    	var me=this;
    	var parent=node||me.getSelectionModel( ).getLastSelected( );
		if(parent){
		    me.getStore().reload({node:parent});
		} else {
		    me.getStore().reload();	
		}      
    },
   
    
    getContextMenu:function(){
    	return this.contextMenu;
    },
    getLastSelected:function(){
    	return this.getSelectionModel( ).getLastSelected( );
    }
    
});
