
Ext.define('DSBY.view.goods.GoodsList', {
    extend: 'DSBY.base.BaseList',
    alias: 'widget.goodsList',
    requires: ['DSBY.store.goods.Goods'],
    store: Ext.create('DSBY.store.goods.Goods'),
    popWin: null,
    popWinName: 'DSBY.view.goods.GoodsWin',
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
                dataIndex: 'goodsNo',
                minWidth: 150
            },
            {
                header: '商品名',
                dataIndex: 'goodsName',
                minWidth: 150
            },
            {
                header: '拼音助记码',
                dataIndex: 'spell',
                minWidth: 150
            },
            {
                header: '规格',
                dataIndex: 'spec',
                minWidth: 150
            },
            {
                header: '辅助单位1',
                dataIndex: 'auxUnitFirst',
                minWidth: 150
            },
            {
                header: '辅助单位1换算率',
                dataIndex: 'auxValFirst',
                minWidth: 150
            },
            {
                header: '辅助单位2',
                dataIndex: 'auxUnitSecond',
                minWidth: 150
            },
            {
                header: '辅助单位2换算率',
                dataIndex: 'auxValSecond',
                minWidth: 150
            },
            {
                header: '库存',
                dataIndex: 'num',
                minWidth: 150
            },
            {
                header: '保质天数',
                dataIndex: 'effectiveDays',
                minWidth: 150
            },
            {
                header: '备注',
                dataIndex: 'remarks',
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
                        id: 'goodsList-look',
                        text: '添加',
                        icon: 'images/add.png',
                        action:'add'
                    },'-',
                    {
                        xtype: 'button',
                        id: 'goodsList-edit',
                        text: '编辑',
                        icon: 'images/edit.png',
                        action:'edit'
                    },'-',
                    {
                        xtype: 'button',
                        id: 'goodsList-excel',
                        text: '导出excel',
                        icon: 'images/excel.png',
                        action:'export'
                    }
                ]
            }
        ];
        me.callParent(arguments);
    }
});
