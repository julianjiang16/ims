Ext.define('DSBY.view.goods.GoodsManager',{
    extend:'DSBY.base.BaseManager',
    alias: 'widget.goodsManager',
    requires: ['DSBY.view.goods.GoodsList'],
    border: false,
    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    initComponent: function () {
        var me = this;
        var store=Ext.create('DSBY.store.goods.GoodsType')
        me.items = [
            {
                xtype: 'form',
                layout: 'column',
                border: false,
                items: [
                    {
                        flex: 1,
                        xtype: 'fieldcontainer',
                        layout: 'hbox',
                        defaults: {
                            flex: 1,
                            margin: '5 0 5 5',
                            xtype: 'textfield',
                            width: 320
                        },
                        items: [
                            {
                                name: 'name',
                                fieldLabel: '商品名称',
                            },
                            {
                                name: 'no',
                                fieldLabel: '商品编号',
                            },
                            {
                                name: 'py',
                                fieldLabel: '拼音助记码',
                            },
                            {
                                xtype: 'combobox',
                                store: store,
                                editable:false,
                                name:'type',
                                displayField: 'name',
                                valueField: 'no',
                                fieldLabel: '商品分类',
                            },
                            {
                                xtype:'button',
                                text:'搜索',
                                action:'search',
                                width: 60
                            }
                        ]
                    }
                ]
            },
            {
                xtype: 'goodsList',
                forceFit: true,
                flex: 1
            },
        ];
        me.callParent(arguments);
    }
});