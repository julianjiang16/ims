Ext.define('DSBY.view.priceTemplate.PriceTemplateWin', {
    extend: 'Ext.window.Window',
    requires: ['DSBY.model.priceTemplate.PriceTemplate'],
    alias: 'widget.priceTemplateWin',
    modelName: 'DSBY.model.priceTemplate.PriceTemplate',
    list: null,
    modal: true,
    title: '价格模板信息',
    width: 670,
    layout: 'fit',
    maximized: false,
    draggable: true,
    closeAction: 'destroy',
    resizable: true,
    initComponent: function () {
        var me = this;
        var store = new Ext.data.SimpleStore({
            data: [['01','供应商'],['02','客户']],
            fields: ["id", "name"]
        });
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
                                fieldLabel: '模板名称',
                                allowBlank: false
                            },
                            {
                                xtype: 'combobox',
                                store: store,
                                editable:false,
                                name:'type',
                                displayField: 'name',
                                valueField: 'id',
                                fieldLabel: '模板类型',
                                allowBlank: false
                            },
                            {
                                name: 'remarks',
                                fieldLabel: '备注',
                            },
                            {
                                hidden: true,
                                name: 'id'
                            },
                            {
                                hidden: true,
                                name: 'no'
                            }
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