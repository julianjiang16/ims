Ext.define('DSBY.view.goods.GoodsWin', {
    extend: 'Ext.window.Window',
    requires: ['DSBY.model.goods.Goods'],
    alias: 'widget.goodsWin',
    modelName: 'DSBY.model.goods.Goods',
    list: null,
    modal: true,
    title: '商品信息',
    width: 670,
    layout: 'fit',
    maximized: false,
    draggable: true,
    closeAction: 'destroy',
    resizable: true,
    initComponent: function () {
        var me = this;
        var store=Ext.create('DSBY.store.goods.GoodsType')
        me.items = [
            {
                xtype: 'form',
                layout: 'column',
                border: false,
                items: [
                    {
                        flex: 1,
                        xtype: 'fieldcontainer',
                        layout: 'vbox',
                        defaults: {
                            flex: 2,
                            margin: '5 0 15 5',
                            xtype: 'textfield',
                            width: 580,
                            labelWidth:135
                        },
                        items: [
                            {
                                name: 'goodsName',
                                fieldLabel: '商品名称',
                                allowBlank: false
                            },
                            {
                                xtype: 'combobox',
                                store: store,
                                editable:false,
                                name:'typeNo',
                                displayField: 'name',
                                valueField: 'no',
                                fieldLabel: '商品分类',
                                allowBlank: false
                            },
                            {
                                name: 'spec',
                                fieldLabel: '商品规格',
                                allowBlank: false
                            },
                            {
                                name: 'auxUnitFirst',
                                fieldLabel: '辅助单位1',
                                allowBlank: false
                            },
                            {
                                name: 'auxValFirst',
                                fieldLabel: '辅助单位1换算率',
                                allowBlank: false
                            },
                            {
                                name: 'auxUnitSecond',
                                fieldLabel: '辅助单位2',
                                allowBlank: false
                            },
                            {
                                name: 'auxValSecond',
                                fieldLabel: '辅助单位2换算率',
                                allowBlank: false
                            },
                            {
                                name: 'effectiveDays',
                                fieldLabel: '保质期（天）',
                                allowBlank: false
                            },
                            {
                                name: 'remarks',
                                fieldLabel: '备注',
                                allowBlank: false
                            },
                            {
                                hidden: true,
                                name: 'id'
                            },
                            {
                                hidden: true,
                                name: 'goodsNo'
                            },
                            {
                                hidden: true,
                                name: 'spell'
                            }
                        ]
                    }
                ]
            }
        ];
        // Reset and Submit buttons
        me.buttons = [
            {
                text: '保存',
                action:'save',
               /* handler: function (btn) {
                    var form = btn.up('window').down('form').getForm();
                    if (form.isValid()) {
                        form.submit({
                            url: 'api/goods/goods',
                            method: 'post',
                            success: function (fp) {
                                var win = btn.up("window");
                                win.list.store.reload();
                                Ext.Msg.alert('提示', '保存成功');
                                win.destroy();
                            },
                            failure: function (fp) {
                                Ext.Msg.alert('提示', '保存失败' + fp.Msg);
                            }
                        });
                    }
                }*/
            },
            {
                text: '关闭',
                action: 'close'
            }
        ];
        me.callParent(arguments);
    }
});