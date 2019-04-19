Ext.define('DSBY.view.goodsType.GoodsTypeManager',{
    extend:'DSBY.base.BaseManager',
    alias: 'widget.goodsTypeManager',
    requires: ['DSBY.view.goodsType.GoodsTypeList'],
    border: false,
    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    initComponent: function () {
        var me = this;
        me.items = [
            {
                xtype: 'goodsTypeList',
                forceFit: true,
                flex: 1
            },
        ];
        me.callParent(arguments);
    }
});