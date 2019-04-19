Ext.define('DSBY.view.supplier.SupplierList', {
    extend: 'DSBY.base.BaseList',
    alias: 'widget.supplierList',
    requires: ['DSBY.store.supplier.Supplier'],
    store: Ext.create('DSBY.store.supplier.Supplier'),
    popWin: null,
    popWinName: 'DSBY.view.supplier.SupplierWin',
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
                header: '供应商名称',
                dataIndex: 'name',
                minWidth: 150
            },
            {
                header: '简称',
                dataIndex: 'abbr',
                minWidth: 150
            },
            {
                header: '地址',
                dataIndex: 'address',
                minWidth: 150
            },
            {
                header: '联系人',
                dataIndex: 'contacts',
                minWidth: 150
            },
            {
                header: '联系人电话',
                dataIndex: 'contactsPhone',
                minWidth: 150
            },
            {
                header: '开户行',
                dataIndex: 'bank',
                minWidth: 150
            },
            {
                header: '开户行账号',
                dataIndex: 'bankNo',
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
                        id: 'supplierList-look',
                        text: '添加',
                        icon: 'images/add.png',
                        action:'add'
                    },'-',
                    {
                        xtype: 'button',
                        id: 'supplierList-edit',
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
