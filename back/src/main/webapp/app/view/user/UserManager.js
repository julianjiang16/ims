Ext.define('DSBY.view.user.UserManager',{
    extend:'DSBY.base.BaseManager',
    alias: 'widget.menuManager',
    requires: ['DSBY.view.user.UserList'],
    border: false,
    layout: {
        type: 'hbox',
        align: 'stretch'
    },
    initComponent: function () {
        var me = this;
        me.items = [
            {
                xtype: 'userList',
                forceFit: true,
                flex: 2
            },
        ];
        me.callParent(arguments);
    }
});