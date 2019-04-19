Ext.define('DSBY.view.user.UserWin', {
    extend: 'Ext.window.Window',
    requires: ['DSBY.model.user.User'],
    alias: 'widget.userWin',
    modelName: 'DSBY.model.user.User',
    list: null,
    modal: true,
    title: '账号信息',
    height: 160,
    width: 500,
    layout: 'fit',
    maximized: false,
    draggable: true,
    closeAction: 'destroy',
    resizable: true,
    initComponent: function () {
        var me = this;
        var store = Ext.create('DSBY.store.role.Role')
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
                            margin: '5 0 5 5',
                            xtype: 'textfield',
                            width: 480
                        },
                        items: [
                            {
                                name: 'userName',
                                fieldLabel: '账户姓名',
                                allowBlank: false
                            },
                            {
                                xtype: 'combobox',
                                store: store,
                                editable:false,
                                name:'roleId',
                                displayField: 'name',
                                valueField: 'id',
                                fieldLabel: '角色',
                                allowBlank: false
                            },
                            {
                                hidden: true,
                                name: 'id'
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
                action:'save',
               /* handler: function (btn) {
                    var form = btn.up('window').down('form').getForm();
                    if (form.isValid()) {
                        form.submit({
                            url: 'api/user/user',
                            method: 'post',
                            success: function (fp) {
                                var win = btn.up("window");
                                win.list.store.reload();
                                Ext.Msg.alert('提示', '保存成功');
                                win.destroy();
                            },
                            failure: function (fp) {
                                Ext.Msg.alert('提示', '保存失败' + fp.Msg);
                            }
                        });
                    }
                }*/
            },
            {
                text: '关闭',
                action: 'close'
            }
        ];
        me.callParent(arguments);
    }
});