Ext.define('DSBY.controller.menu.MenuController', {
    extend: 'DSBY.base.BaseController',
    requires: ['DSBY.view.menu.MenuList', 'DSBY.view.menu.MenuWin'],
    refs: [
        {
            ref: 'menuList',
            selector: 'menuList'
        },
        {
            ref: 'menuWin',
            selector: 'menuWin'
        }
    ],
    init: function () {
        this.control({
            'menuList': {
                itemcontextmenu: this.showContextMenu,
            },
            'menuList button[action=edit]': {
                click: this.edit
            },
            'menuList button[action=delete]': {
                click: this.del
            },
            'menuWin button[action=close]': {
                click: this.close
            },
            'menuWin button[action=save]': {
                click: this.save
            }
        });
    },
    showContextMenu: function (grid, record, item1, index, event) {
        var me = this;
        var contextMenu = Ext.create("Ext.menu.Menu");
        var isLeaf = record.data.leaf;
        if (record.data.id == "0") {
            contextMenu.add({
                iconCls: 'add', text: '添加菜单',
                handler: function () {
                    me.addMenu(record, false);
                }
            });
            contextMenu.add({
                iconCls: 'add', text: '添加功能菜单',
                handler: function () {
                    me.addMenu(record, true);
                }
            });
        }else {
            if (!isLeaf || record.data.qtitle == "SYS") {
                contextMenu.add({
                    iconCls: 'add', text: '添加菜单',
                    handler: function () {
                        me.addMenu(record, false);
                    }
                });
            }
            if (index > 0 && !isLeaf && record.data.qtitle != "SYS") {
                contextMenu.add({
                    iconCls: 'add', text: '添加功能菜单',
                    handler: function () {
                        me.addMenu(record, true);
                    }
                });
            }
            if (index > 0 && record.data.qtitle != "SYS") {
                contextMenu.add([
                    {
                        iconCls: 'edit', text: '修改',
                        handler: function () {
                            me.modMenu(record);
                        }

                    },
                    {
                        iconCls: 'delete', text: '删除',
                        handler: function () {
                            me.delMenu(record, isLeaf);
                        }
                    }
                ]);
            }
        }

        var x = event.browserEvent.clientX;
        var y = event.browserEvent.clientY;
        contextMenu.showAt([x, y]);
    },
    addMenu: function (record, isleaf) {
        var parentId = record.raw.id;
        var level = record.raw.level + 1;
        if(isNaN(level))
            level=1;
        var win = Ext.create("DSBY.view.menu.MenuWin");
        var form = win.down("form").getForm();
        var record = Ext.create("DSBY.model.menu.Menu", {
            level: level,
            leaf: isleaf,
            parentId: parentId,
        });
        form.loadRecord(record);
        win.show();
    },
    modMenu: function (record) {
        var win = Ext.create("DSBY.view.menu.MenuWin");
        var form = win.down("form").getForm();
        var rec = Ext.create("DSBY.model.menu.Menu", {
            name: record.raw.name,
            qtitle: record.raw.qtitle,
            nodeId: record.raw.nodeId,
            icon: record.data.icon,
            id: record.raw.id,
            leaf:record.raw.leaf,
            level: record.raw.level,
            parentId: record.raw.parentId
        });
        form.loadRecord(rec);
        win.show();
    },
    delMenu: function (record, isLeaf) {
        var me = this;
        if (!isLeaf && record.data.children.length > 0) {
            Ext.MessageBox.alert("警告", "该菜单下还有子菜单，请先删除所有子菜单！");
            return;
        }
        Ext.MessageBox.confirm('提示', '确定要删除该菜单？', function (btn, txt) {
            if (btn == 'yes') {
                var menu = Ext.create("DSBY.model.menu.Menu");
                menu.setId(record.data.id);
                menu.destroy({
                    success: function (a, operation, c) {
                        Ext.MessageBox.alert('提示', '删除成功！');
                        window.location.reload();
                        // me.getMenuList().store.load();
                    },
                    failure: function (records, option) {
                        Ext.MessageBox.alert('提示', '删除失败-' + option.request.scope.reader.jsonData.message);
                    }
                });
            }
        });
    },
    addParentMenu:function (record,leaf) {//添加平级菜单
        var parentId = record.raw.id;
        var level = record.raw.level + 1;
        var win = Ext.create("DSBY.view.menu.MenuWin");
        var form = win.down("form").getForm();
        var record = Ext.create("DSBY.model.menu.Menu", {
            level: level,
            leaf: leaf,
            parentId: 0
        });
        form.loadRecord(record);
        win.show();
    }

   /* add: function (btn) {
        var list = btn.up("panel");
        var record = Ext.create('DSBY.model.user.Privilege', {menuId: list.menuId, systemId: list.systemId});
        list.popWin = Ext.create(list.popWinName, {
            list: list
        });
        list.popWin.down('form').getForm().loadRecord(record);
        list.popWin.show();
    }*/
});
