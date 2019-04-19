
Ext.define('DSBY.view.goodsType.GoodsTypeList', {
    extend: 'DSBY.base.BaseList',
    alias: 'widget.goodsTypeList',
    requires: ['DSBY.store.goods.GoodsType'],
    store: Ext.create('DSBY.store.goods.GoodsType'),
    popWin: null,
    popWinName: 'DSBY.view.goodsType.GoodsTypeWin',
    columnLines: true,
    forceFit: true,
    listeners:{
        celldblclick:function (me, td, cellIndex, record, tr, rowIndex, e, eOpts ) {//gridpanel 单元格双击事件
            // 双击查看详情
            // alert(record.get('id'))
        }
    },
    initComponent: function () {
        var me = this;
        me.columns = [
            {
                header: 'id',
                dataIndex: 'id',
                minWidth: 150
            },
            {
                header: '编号',
                dataIndex: 'no',
                minWidth: 150
            },
            {
                header: '类型名称',
                dataIndex: 'name',
                minWidth: 150
            },
            {
                header: '创建时间',
                dataIndex: 'gmtCreate',
                minWidth: 150,
                renderer: Ext.util.Format.dateRenderer('Y-m-d')
            }
        ];
        me.dockedItems = [
            {
                xtype: 'toolbar',
                items: [
                    {
                        xtype: 'button',
                        id: 'goodsTypeList-look',
                        text: '添加',
                        icon: 'images/add.png',
                        action:'add'
                    },'-',
                    {
                        xtype: 'button',
                        id: 'goodsTypeList-edit',
                        text: '编辑',
                        icon: 'images/edit.png',
                        action:'edit'
                    }
                ]
            }
        ];
        me.callParent(arguments);
    }
});
