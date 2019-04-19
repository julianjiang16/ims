Ext.define('DSBY.view.role.RoleManager',{
    extend:'DSBY.base.BaseManager',
    alias: 'widget.menuManager',
    requires: ['DSBY.view.role.RoleList'],
    border: false,
    layout: {
        type: 'hbox',
        align: 'stretch'
    },
    initComponent: function () {
        var me = this;
        me.items = [
            {
                xtype: 'roleList',
                forceFit: true,
                flex: 2
            },
        ];
        me.callParent(arguments);
    }
});