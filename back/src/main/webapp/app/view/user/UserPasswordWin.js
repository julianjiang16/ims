Ext.define('DSBY.view.user.UserPasswordWin', {
    extend: 'Ext.window.Window',
    requires: ['DSBY.model.user.User'],
    alias: 'widget.userPasswordWin',
    modelName: 'DSBY.model.user.User',
    list: null,
    modal: true,
    title: '修改密码',
    height: 140,
    width: 522,
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
                            margin: '15 0 15 15',
                            xtype: 'textfield',
                            width: 480,
                            labelWidth:70
                        },
                        items: [
                            {
                                name:'password',
                                fieldLabel:'新密码',
                                allowBlank:false
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
                handler: function (btn) {
                    var form = btn.up('window').down('form').getForm();
                    if (form.isValid()) {
                        form.submit({
                            url: 'back/user/password',
                            method: 'put',
                            params:form.getValues(),
                            success: function (fp) {
                                window.location.href = 'login.html';
                            },
                            failure: function (fp) {
                                Ext.Msg.alert('提示', '保存失败' + fp.Msg);
                            }
                        });
                    }
                }
            },
            {
                text: '关闭',
                handler:function (btn) {
                    btn.up('window').destroy();
                }
            }
        ];
        me.callParent(arguments);
    }
});