Ext.define('DSBY.view.South', {
	extend : 'Ext.Toolbar',
	initComponent : function() {
		Ext.apply(this, {
			id : "bottom",
			region : "south",
			border : false,
			height : 25,
			items : [{
						flex : 1,
						disabled : true
					}, {
						flex : 1,
						border : false,
						html : "万家欢 版权所有"
					}, {
						flex : 1,
						disabled : true
					}]
		});
		this.callParent(arguments);
	}
});
