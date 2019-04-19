Ext.define('DSBY.view.customer.CustomerList', {
    extend: 'DSBY.base.BaseList',
    alias: 'widget.customerList',
    requires: ['DSBY.store.customer.Customer'],
    store: Ext.create('DSBY.store.customer.Customer'),
    popWin: null,
    popWinName: 'DSBY.view.customer.CustomerWin',
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
                header: '客户名',
                dataIndex: 'name',
                minWidth: 150
            },
            {
                header: '简称',
                dataIndex: 'abbr',
                minWidth: 150
            },
            {
                header: '详细地址',
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
                header: '客户类型',
                dataIndex: 'type',
                minWidth: 150,
                renderer:function (v) {
                    if(v==='01')
                        return '企业客户';
                    return '个人客户';
                }
            },
            {
                header: '税务登记号',
                dataIndex: 'taxNum',
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
                        id: 'customerList-look',
                        text: '添加',
                        icon: 'images/add.png',
                        action:'add'
                    },'-',
                    {
                        xtype: 'button',
                        id: 'customerList-edit',
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
