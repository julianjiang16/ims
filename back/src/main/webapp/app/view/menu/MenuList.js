Ext.define('DSBY.view.Menu.MenuList', {
    extend: 'Ext.tree.TreePanel',
    alias: 'widget.menuList',
    requires: [
        'Ext.tree.*',
        'Ext.data.*'
    ],
    store: Ext.create('DSBY.store.menu.MenuTree'),
    modelName: null,
    initComponent: function () {
        Ext.apply(this, {
            border: false,
            region: 'west',
            width: 200,
            autoHeight: true,
            collapsible: false,
            split: true,
            rootVisible: true,
            useArrows: true,
            viewConfig: {
                plugins: {
                    ptype: 'treeviewdragdrop',
                    containerScroll: true
                }
                /*,
                listeners: {
                    beforedrop: function (node, data, overModel, dropPosition, dropHandlers) {
                        dropHandlers.wait = true;
                        if ((data.records[0].raw.systemId != overModel.raw.systemId) && (data.records[0].raw.systemId != overModel.raw.id)){
                            Ext.MessageBox.alert('提示', '不可跨系统拖放菜单');
                            return;
                        }
                        var fromId = data.records[0].getId();
                        var destId = overModel.getId();
                        var level = overModel.raw.level + 1;
                        if (overModel.raw.qtitle == "SYS") {
                            destId = "0";
                            level = 1;
                        }
                        var srcMenuText = data.records[0].data.text;
                        var desMenuText = overModel.data.text;
                        if (dropPosition == 'append') {
                            Ext.MessageBox.confirm('提示', '你确定要把菜单【' + srcMenuText + "】放到【" + desMenuText + "】菜单下面？", function (btn, txt) {
                                if (btn == 'yes') {
                                    Ext.Ajax.request({
                                        url: 'api/menu/drop',
                                        method: 'PUT',
                                        params: {
                                            fromId: fromId,
                                            destId: destId,
                                            level: level
                                        },
                                        success: function (response) {
                                            dropHandlers.processDrop();
                                        },
                                        failure: function (response) {
                                            dropHandlers.cancelDrop();
                                            return;
                                        }
                                    });
                                }
                            });
                        }
                    }
                }*/
            }

        });
        this.store.load();
        this.callParent(arguments);
    },
    listeners: {
        render: function () {
            Ext.getBody().on("contextmenu", Ext.emptyFn, null, {preventDefault: true});
        }
    }
});
