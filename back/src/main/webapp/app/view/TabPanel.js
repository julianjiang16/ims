Ext.define('DSBY.view.TabPanel', {
    extend: 'Ext.tab.Panel',
    requires: ['Ext.ux.TabCloseMenu','DSBY.view.user.UserList'],
    initComponent: function () {
        var me = this;
        Ext.apply(this, {
            id: 'content-panel',
            region: 'center',
            layout:'fit',
            defaults: {
                autoScroll: true
            },
            activeTab: 0,
            border: false,
            items: [
                /*{
                    title: '首页',
                    border: false,
                    iconCls: 'home',
                    xtype: 'container',
                    itemId: 'home',
                    padding: "20px",
                    items: [
                        {
                            xtype: 'panel',
                            bodyPadding: 5,
                            title: '销售订单状态',
                            margin: '20 0 0 0',
                            width:180,
                            height:160,
                            layout: {
                                type: 'vbox',
                                defaultMargins: {top: 8},
                                align: 'stretch'
                            },
                            items: [
                                {
                                xtype: 'displayfield',
                                name: 'status0',
                                id:'status0',
                                fieldLabel: "待发货订单",
                                labelWidth: 120,
                                width: 60
                               },
                                {
                                    xtype: 'displayfield',
                                    name: 'status1',
                                    id:'status1',
                                    fieldLabel: "部分发货订单",
                                    labelWidth: 120,
                                    width: 60,
                                },
                                {
                                    xtype: 'displayfield',
                                    name: 'status3',
                                    id:'status3',
                                    fieldLabel: "本月总订单",
                                    labelWidth: 120,
                                    width: 60
                                },
                            ]
                        },
/!*                        {
                            xtype: 'repairingServicePanel'
                        }*!/
                    ]
                }*/
            ],
            plugins: new Ext.create('Ext.ux.TabCloseMenu',{

                closeTabText: '关闭面板',

                closeOthersTabsText: '关闭其他',

                closeAllTabsText: '关闭所有'

            })
        });
        this.callParent(arguments);
    },
    listeners: {
        afterrender: function () {
            Ext.onReady(function () {
                /*Ext.Ajax.request({
                    url: 'web/order/v1/order',
                    method: 'GET',
                    cache: false,
                    params: {
                        status: 1
                    },
                    success: function (response) {
                        Ext.getCmp("status1").setValue(Ext.JSON.decode(response.responseText).data);
                    },
                    failure: function () {
                        Ext.Msg.alert("提示", "页面加载失败！");
                    }
                });
                Ext.Ajax.request({
                    url: 'web/order/v1/order',
                    method: 'GET',
                    cache: false,
                    params: {
                        status: 0
                    },
                    success: function (response) {
                        Ext.getCmp("status0").setValue(Ext.JSON.decode(response.responseText).data);
                    },
                    failure: function () {
                        Ext.Msg.alert("提示", "页面加载失败！");
                    }
                });
                Ext.Ajax.request({
                    url: 'web/order/v1/orderNum',
                    method: 'GET',
                    cache: false,
                    params: {
                        status: 0
                    },
                    success: function (response) {
                        Ext.getCmp("status3").setValue(Ext.JSON.decode(response.responseText).data);
                    },
                    failure: function () {
                        Ext.Msg.alert("提示", "页面加载失败！");
                    }
                });*/
            });

        }
    }
});
