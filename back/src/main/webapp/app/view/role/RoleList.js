
Ext.define('DSBY.view.role.RoleList', {
    extend: 'DSBY.base.BaseList',
    alias: 'widget.roleList',
    requires: ['DSBY.store.role.Role'],
    store: Ext.create('DSBY.store.role.Role'),
    popWin: null,
    popWinName: 'DSBY.view.role.RoleWin',
    columnLines: true,
    forceFit: true,
    initComponent: function () {
        var me = this;
        me.columns = [
            {
                header: 'id',
                dataIndex: 'id',
                minWidth: 150
            },
            {
                header: '角色名',
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
                        id: 'roleList-add',
                        text: '添加',
                        icon: 'images/add.png',
                        handler:function (btn) {
                            var store=btn.up('panel').store;
                            var win=Ext.create('DSBY.view.role.RoleWin',{
                                store:store
                            });
                            win.show();
                        }
                    },
                    {
                        xtype: 'button',
                        id: 'roleList-menu-edit',
                        text: '编辑角色菜单',
                        icon: 'images/edit.png',
                        action:'editMenu'
                    }
                ]
            }
        ];
        me.callParent(arguments);
    }
});
