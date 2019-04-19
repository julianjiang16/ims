Ext.define('DSBY.view.menu.MenuManager', {
    extend: 'DSBY.base.BaseManager',
    alias: 'widget.menuManager',
    requires: ['DSBY.view.menu.MenuList'],
    border: false,
    layout: {
        type: 'hbox',
        align: 'stretch'
    },
    initComponent: function () {
        var me = this;
        me.items = [
            {
                xtype: 'menuList',
                forceFit: true,
                flex: 2
            },
        ];
        me.callParent(arguments);
    }
});