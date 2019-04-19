Ext.define('DSBY.view.role.RoleWin', {
    extend: 'Ext.window.Window',
    requires: ['DSBY.model.role.Role'],
    alias: 'widget.roleWin',
    modelName: 'DSBY.model.role.Role',
    list: null,
    modal:true,
    title: '角色信息',
    height: 115,
    width: 440,
    layout: 'fit',
    maximized: false,
    draggable: true,
    closeAction: 'destroy',
    resizable: true,
    initComponent: function () {
        var me = this;
        me.items = [
            {
                xtype: 'form',
                layout: 'column',
                border: false,
                items: [
                    {
                        flex: 1,
                        xtype: 'fieldcontainer',
                        layout: 'vbox',
                        defaults: {
                            flex: 1,
                            margin: '5 5',
                            xtype: 'textfield',
                            width: 400
                        },
                        items: [
                            {
                                name: 'name',
                                fieldLabel: '角色名',
                                allowBlank: false
                            }
                        ]
                    }
                ]
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