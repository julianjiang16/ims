Ext.define('DSBY.view.supplier.SupplierWin', {
    extend: 'Ext.window.Window',
    requires: ['DSBY.model.supplier.Supplier'],
    alias: 'widget.supplierWin',
    modelName: 'DSBY.model.supplier.Supplier',
    list: null,
    modal: true,
    title: '供应商信息',
    width: 670,
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
                            flex: 2,
                            margin: '5 0 15 5',
                            xtype: 'textfield',
                            width: 580,
                            labelWidth:135
                        },
                        items: [
                            {
                                name: 'name',
                                fieldLabel: '供应商名称',
                                allowBlank: false
                            },
                            {
                                name: 'abbr',
                                fieldLabel: '简称',
                                allowBlank: false
                            },
                            {
                                name: 'address',
                                fieldLabel: '地址',
                                allowBlank: false
                            },
                            {
                                name: 'contacts',
                                fieldLabel: '联系人',
                                allowBlank: false
                            },
                            {
                                name: 'contactsPhone',
                                fieldLabel: '联系人电话',
                                allowBlank: false
                            },
                            {
                                name: 'bank',
                                fieldLabel: '开户行',
                                allowBlank: false
                            },
                            {
                                name: 'bankNo',
                                fieldLabel: '开户行账号',
                                allowBlank: false
                            },
                            {
                                name: 'remarks',
                                fieldLabel: '备注',
                                allowBlank: false
                            },
                            {
                                hidden: true,
                                name: 'id'
                            },
                            {
                                hidden: true,
                                name: 'no'
                            },
                        ]
                    }
                ]
            }
        ];
        me.buttons = [
            {
                text: '保存',
                action:'save',
            },
            {
                text: '关闭',
                action: 'close'
            }
        ];
        me.callParent(arguments);
    }
});