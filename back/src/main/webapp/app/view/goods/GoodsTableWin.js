Ext.define('DSBY.view.goods.GoodsTableWin', {
    extend: 'Ext.window.Window',
    requires: ['DSBY.model.goods.Goods'],
    alias: 'widget.goodsTableWin',
    modelName: 'DSBY.model.goods.Goods',
    list: null,
    modal: true,
    title: '商品字段选择',
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
                        xtype: 'checkboxgroup',
                        defaults: {
                            margin:'20 10 15 7',
                            width:150,
                            checked: true
                        },
                        name: 'field',
                        columns:4,
                        items: [
                            {
                                boxLabel: 'id',
                                name: 'field',
                                inputValue: '01'
                            },
                            {
                                boxLabel: '商品编号',
                                name: 'field',
                                inputValue: '02'
                            },
                            {
                                boxLabel: '商品名称',
                                name: 'field',
                                inputValue: '03'
                            },
                            {
                                boxLabel: '分类编号',
                                name: 'field',
                                inputValue: '04'
                            },
                            {
                                boxLabel: '拼音助记码',
                                name: 'field',
                                inputValue: '05'
                            },
                            {
                                boxLabel: '简称',
                                name: 'field',
                                inputValue: '06'
                            },
                            {
                                boxLabel: '辅助单位1',
                                name: 'field',
                                inputValue: '07'
                            },
                            {
                                boxLabel: '辅助单位1的换算值',
                                name: 'field',
                                inputValue: '08'
                            },
                            {
                                boxLabel: '辅助单位2',
                                name: 'field',
                                inputValue: '09'
                            },
                            {
                                boxLabel: '辅助单位2的换算值',
                                name: 'field',
                                inputValue: '10'
                            },
                            {
                                boxLabel: '库存数量',
                                name: 'field',
                                inputValue: '11'
                            },
                            {
                                boxLabel: '有效天数',
                                name: 'field',
                                inputValue: '12'
                            },
                            {
                                boxLabel: '备注',
                                name: 'field',
                                inputValue: '13'
                            },
                            {
                                boxLabel: '创建时间',
                                name: 'field',
                                inputValue: '14'
                            }
                        ]
                    }
                ]
            }
        ];
        // Reset and Submit buttons
        me.buttons = [
            {
                text: '确认导出',
                action:'trueExport',
            },
            {
                text: '关闭',
                action: 'close'
            }
        ];
        me.callParent(arguments);
    }
});