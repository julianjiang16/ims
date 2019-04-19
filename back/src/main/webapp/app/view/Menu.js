var store = Ext.create('Ext.data.TreeStore', {
    proxy:{
        type: 'ajax',
        url:'back/menu/menu',
        /*reader: {
            type: 'json',
            root: 'data',
        }*/
    },
    fields:['nodeId','id','text','name','leaf','qtitle','parentId','icon','systemId','level']
});

Ext.define('DSBY.view.Menu', {
	extend : 'Ext.tree.Panel',
	requires: [
        'Ext.tree.*',
        'Ext.data.*'
    ],
	alias : 'widget.smsmenu',
	id : 'mainMenu',
	initComponent : function() {
		Ext.apply(this, {
			title : Ext.util.Cookies.get('loginName'),
			border : false,
			region : 'west',
			width : 180,
			autoHeight : true,
			collapsible : true,
			split : true,
			rootVisible:false,
			useArrows:true,
			store:store

		});
		this.callParent(arguments);
	}
});
