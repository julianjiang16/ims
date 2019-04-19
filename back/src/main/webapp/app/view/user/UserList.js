
Ext.define('DSBY.view.user.UserList', {
    extend: 'DSBY.base.BaseList',
    alias: 'widget.userList',
    requires: ['DSBY.store.user.User'],
    store: Ext.create('DSBY.store.user.User'),
    popWin: null,
    popWinName: 'DSBY.view.user.UserWin',
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
                header: '用户名',
                dataIndex: 'userName',
                minWidth: 150
            },
            {
                header: '角色',
                dataIndex: 'roleName',
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
                        id: 'userList-look',
                        text: '添加',
                        icon: 'images/add.png',
                        action:'add'
                    },'-',
                    {
                        xtype: 'button',
                        id: 'userList-edit',
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
