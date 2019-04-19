Ext.define('DSBY.view.goodsType.GoodsTypeWin', {
    extend: 'Ext.window.Window',
    requires: ['DSBY.model.goods.GoodsType'],
    alias: 'widget.goodsTypeWin',
    modelName: 'DSBY.model.goods.GoodsType',
    list: null,
    modal: true,
    title: '商品类型信息',
    height: 120,
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
                                name: 'name',
                                fieldLabel: '商品类型名称',
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
            },
            {
                text: '关闭',
                action: 'close'
            }
        ];
        me.callParent(arguments);
    }
});