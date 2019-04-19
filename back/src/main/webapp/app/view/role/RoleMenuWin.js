Ext.define('DSBY.view.role.RoleMenuWin', {
    extend: 'Ext.window.Window',
    requires: ['DSBY.model.role.Role','DSBY.view.role.RoleMenuTreeWin'],
    alias: 'widget.roleMenuWin',
    modelName: 'DSBY.model.role.Role',
    list: null,
    modal:true,
    title: '菜单树',
    height: 600,
    width: 500,
    layout: 'fit',
    maximized: false,
    draggable: true,
    closeAction: 'destroy',
    resizable: true,
    initComponent: function () {
        var me = this;
        me.items = [
            {
                xtype:'roleMenuTreeWin',
                rid:me.rid
            }
        ];
        // Reset and Submit buttons
        me.buttons = [
            {
                text: '保存',
                action:'save'
            },
            {
                text: '关闭',
                action: 'close'
            }
        ];
        me.callParent(arguments);
    }
});