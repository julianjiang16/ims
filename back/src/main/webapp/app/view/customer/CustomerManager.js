Ext.define('DSBY.view.customer.CustomerManager',{
    extend:'DSBY.base.BaseManager',
    alias: 'widget.customerManager',
    requires: ['DSBY.view.customer.CustomerList'],
    border: false,
    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    initComponent: function () {
        var me = this;
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
                                fieldLabel: '客户名称',
                            },
                            {
                                name: 'no',
                                fieldLabel: '客户编号',
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
                xtype: 'customerList',
                forceFit: true,
                flex: 1
            },
        ];
        me.callParent(arguments);
    }
});