Ext.define('DSBY.view.menu.MenuWin', {
    extend: 'Ext.window.Window',
    requires: ['DSBY.model.menu.Menu'],
    alias: 'widget.menuWin',
    modelName: 'DSBY.model.menu.Menu',
    list: null,
    title: '菜单信息',
    height: 250,
    width: 350,
    maximized: false,
    draggable: true,
    modal:true,
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
                            margin: '5 20 5 10 ',
                            xtype: 'textfield',
                            width: 320
                        },
                        items: [
                            {
                                name: 'name',
                                fieldLabel: '菜单名',
                                allowBlank: false
                            },
                            {
                                name: 'nodeId',
                                fieldLabel: 'view'
                            },
                            {
                                name: 'qtitle',
                                fieldLabel: 'controller'
                            },
                            {
                                name: 'icon',
                                fieldLabel: '图标'
                            },
                            {
                                xtype: "hidden",
                                name:'parentId'
                            },
                            {
                                xtype: "hidden",
                                name:'leaf'
                            },
                            {
                                xtype: "hidden",
                                name:'systemId'
                            },
                            {
                                xtype: "hidden",
                                name:'level'
                            },
                            {
                                xtype: "hidden",
                                name:'id'
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
                action: 'save'
            },
            {
                text: '关闭',
                action: 'close'
            }
        ];
        me.callParent(arguments);
    }
});