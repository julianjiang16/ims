Ext.define('DSBY.view.Header', {
    layout: {
        type: 'hbox',       // Arrange child items vertically
        align: 'middle',    // Each takes up full width
        padding: 5
    },
    extend: 'Ext.panel.Panel',
    height: 50,
    region: 'north',
    bodyStyle: "background-image:url() !important;background-color:transparent;",
    //bodyStyle:'background-color:#07507F',
    initComponent: function () {
        var me = this;
        me.items = [
            {
                xtype : 'box',
                width: 10,//图片宽度
                height: 30,
                flex:9,
                html:'<img src="images/logo.png" style="height: 30px">',
                layout:'west',
            },
            {
                html:"<div style='background-color: rgb(2,125,203);color:white'>欢迎您，</div>"
            },
            {
                xtype:'box',
                isFormField: true,
                style: "background-color: rgb(2,125,203);",
                margin:'0 6 0 0',
                autoEl:{
                    //html: '&nbsp;<a href>Link To Prospect</a>'
                    tag: 'a',
                    href: '#',
                    cn: Ext.util.Cookies.get("loginName")
                },
                listeners: {
                    render: function(component) {
                        component.getEl().on('click', function(e) {
                            var win = Ext.create('DSBY.view.user.UserPasswordWin');
                            win.show();
                        });
                    }
                }
            },
            {
                xtype: 'button',
                iconCls: 'user_logout',

                width:110,
                height:20,
                text: '退出登录',
                handler: function () {
                    Ext.Msg.confirm("提示","您确定要退出系统吗？",function (btn) {
                        if(btn=='yes')
                        {
                            Ext.Ajax.request({
                                method: 'POST',
                                url : 'back/user/loginout',
                                success: function (response, options) {
                                    window.location.href = 'login.html';
                                }
                            });
                        }
                    })
                }
            }
        ];
        me.callParent(arguments);
    }
});