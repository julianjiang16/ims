Ext.define('DSBY.view.priceTemplate.PriceTemplateManager',{
    extend:'DSBY.base.BaseManager',
    alias: 'widget.priceTemplateManager',
    requires: ['DSBY.view.priceTemplate.PriceTemplateList'],
    border: false,
    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    initComponent: function () {
        var me = this;
        var store = new Ext.data.SimpleStore({
            data: [['00','不限'],['01','供应商'],['02','客户']],
            fields: ["id", "name"]
        });
        me.items = [
            {
                xtype: 'form',
                layout: 'column',
                border: false,
                items: [
                    {
                        flex: 1,
                        xtype: 'fieldcontainer',
                        layout: 'hbox',
                        defaults: {
                            flex: 1,
                            margin: '5 0 5 5',
                            xtype: 'textfield',
                            width: 320
                        },
                        items: [
                            {
                                name: 'name',
                                fieldLabel: '名称',
                            },
                            {
                                name: 'no',
                                fieldLabel: '编号',
                            },
                            {
                                xtype: 'combobox',
                                store: store,
                                editable:false,
                                name:'type',
                                displayField: 'name',
                                valueField: 'id',
                                fieldLabel: '模板类型',
                                allowBlank: false
                            },
                            {
                                xtype:'button',
                                text:'搜索',
                                action:'search',
                                width: 60
                            }
                        ]
                    }
                ]
            },
            {
                xtype: 'priceTemplateList',
                forceFit: true,
                flex: 1
            },
        ];
        me.callParent(arguments);
    }
});