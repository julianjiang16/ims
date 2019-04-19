Ext.define('DSBY.view.priceTemplate.PriceTemplateList', {
    extend: 'DSBY.base.BaseList',
    alias: 'widget.priceTemplateList',
    requires: ['DSBY.store.priceTemplate.PriceTemplate'],
    store: Ext.create('DSBY.store.priceTemplate.PriceTemplate'),
    popWin: null,
    popWinName: 'DSBY.view.priceTemplate.PriceTemplateWin',
    columnLines: true,
    forceFit: true,
    listeners:{
        celldblclick:function (me, td, cellIndex, record, tr, rowIndex, e, eOpts ) {//gridpanel 单元格双击事件
            // 双击查看详情
            // alert(record.get('id'))
            alert(record.get('id')+"查看模板价格商品")
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
                header: '模板名称',
                dataIndex: 'name',
                minWidth: 150
            },
            {
                header: '类型',
                dataIndex: 'type',
                minWidth: 150,
                renderer:function (v) {
                    if (v === '01') {
                        return '供应商'
                    }
                    return '客户';
                }
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
                        id: 'priceTemplateList-look',
                        text: '添加',
                        icon: 'images/add.png',
                        action:'add'
                    },'-',
                    {
                        xtype: 'button',
                        id: 'priceTemplateList-edit',
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
