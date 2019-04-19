/**
 * 主要用户手机断显示文字和图片混合的内容编辑器。
 * 类似于商品里面的图文详情。
 */
Ext.define('Ext.ux.form.field.AppTextAndImageEditor', {
    alias: ['widget.apptextandimageeditor'],
    require: ['SMS.view.common.TextAddWin'],
    extend: 'Ext.panel.Panel',
    width: 320,
    height: 567,
    layout: 'column',
    itemIndex: 0,
    name: 'textAndImageDesc',
    currentItem: null,
    value: '',
    isAds:false,
    display: false,
    overflowY: 'auto',
    bodyPadding: '5 2 0 2',
    fileType: 'mall',
    content: [],
    initComponent: function () {
        var me = this;
        me.items = [
            {
                xtype: 'toolbar',
                itemId: 'floatToolbar',
                floating: true,
                hidden: true,
                items: [
                    {
                        text: '上移',
                        itemId: 'btnMoveUp',
                        handler: function () {
                            var index = me.items.indexOf(me.currentItem);
                            var componet = me.currentItem.cloneConfig();
                            var delText = me.content.splice(index - 1, 1);
                            me.remove(me.currentItem);
                            me.content.splice(index - 2, 0, delText[0]);
                            me.insert(index - 1, componet);
                            me.currentItem = componet;
                            me.down("#floatToolbar").alignTo(componet);
                            if (index - 1 == 1) {
                                this.hide();
                            }
                            me.down("#btnMoveDown").show();
                            me.down('#content').setValue(me.getContent());
                        }
                    },
                    {
                        text: '下移',
                        itemId: 'btnMoveDown',
                        handler: function () {
                            var index = me.items.indexOf(me.currentItem);
                            var componet = me.currentItem.cloneConfig();
                            var delText = me.content.splice(index - 1, 1);
                            me.remove(me.currentItem);
                            me.content.splice(index, 0, delText[0]);
                            me.insert(index + 1, componet);
                            me.currentItem = componet;
                            me.down("#floatToolbar").alignTo(componet);
                            if (index + 1 == me.items.length - 1) {
                                this.hide();
                            }
                            if(index==1){
                                me.down("#btnMoveUp").show();
                            }
                            me.down('#content').setValue(me.getContent());
                        }
                    },
                    {
                        text: '编辑',
                        itemId: 'btnMoveEdit',
                        handler: function () {
                            if (me.currentItem.getXType() == 'displayfield') {
                                me.showAddTextWin(true);
                            } else {
                                me.showAddImageWin(true);
                            }
                            me.down("#floatToolbar").hide();
                        }
                    },
                    {
                        text: '链接',
                        itemId: 'btnMoveHref'
                    },
                    {
                        text:'删除链接',
                        itemId:'btnDelHref'
                    },
                    {
                        text: '删除',
                        handler: function () {
                            var index = me.items.indexOf(me.currentItem);
                            me.content.splice(index - 1, 1);
                            me.remove(me.currentItem);
                            me.down("#floatToolbar").hide();
                            me.down('#content').setValue(me.getContent());
                        }
                    }
                ]
            },
            {
                xtype: 'hidden',
                name: me.name,
                itemId: 'content'
            }
        ];
        if (!me.display) {
            Ext.apply(this, {
                tbar: [
                    {
                        xtype: 'toolbar',
                        items: [
                            {
                                text: '添加文字',
                                handler: function () {
                                    me.showAddTextWin(false);
                                }
                            },
                            {
                                text: '添加图片',
                                handler: function () {
                                    me.showAddImageWin(false);
                                }
                            },
                            {
                                text: '清空',
                                handler: function () {
                                    me.setValue('');
                                }
                            }
                        ]
                    }
                ]
            });
        }
        if (me.value != '') {
            me.render();
        }
        me.callParent(arguments);
    },
    render: function () {
        var me = this;
        var tmp = me.value.split("<br/>");
        Ext.Array.forEach(tmp, function (str){
            while(true) {
                if (str.indexOf("<img", 1) != -1) {
                    var i = str.indexOf("<img", 1);
                    me.content.push(str.substring(0,i));
                    str = str.substring(i);
                } else {
                    me.content.push(str);
                    break;
                }
            }
        })
        me.down("#content").setValue(me.value);
        Ext.Array.forEach(me.content, function (content) {
            if (content.startsWith("<img")) {
                var src = content.substringBetween("src=\"", "\"/>");
                me.add({
                    xtype: 'image',
                    src: src,
                    columnWidth: 1,
                    listeners: {
                        render: function (component) {
                            me.imageClick(component);
                        }
                    }
                });
            } else {
                me.add({
                    xtype: 'displayfield',
                    value: content,
                    margin: '5 0 0 0',
                    cls: 'app_mobile_text',
                    columnWidth: 1,
                    overCls: 'app_mobile_text_over',
                    listeners: {
                        render: function (component) {
                            me.textClick(component);
                        }
                    }
                });
            }
        })
    },
    setValue: function (value) {
        var me = this;
        me.content = [];
        if (value) {
            this.value = value;
            this.render();
        } else {
            me.down("#content").setValue("");
            if (me.currentItem) {
                me.currentItem.setValue('');
            }
            var children = me.items ? Ext.Array.clone(me.items.items) : [];
            Ext.each(children, function (child) {
                if (child.getXType() !== 'hiddenfield') {
                    me.remove(child);
                }
            })
        }
    },
    showAddTextWin: function (replace) {
        var me = this;
        var win = Ext.create("SMS.view.common.TextAddWin");
        if (replace) {
            win.title = '编辑文字';
            win.down('button[action=add]').setText('保 存');
            win.down('textfield').setValue(me.currentItem.getValue().replace(/<[^>]+>/g,""));
        } else {
            win.title = '添加文字';
            win.down('button[action=add]').setText('添 加');
        }
        win.down('button[action=add]').on('click', function () {
            var text = win.down('textareafield').getValue();
            if(me.currentItem){
                text=me.currentItem.getValue().replace(me.currentItem.getValue().replace(/<[^>]+>/g,""),text);
            }
            if (replace) {
                me.currentItem.setValue(text);
            } else {
                me.add({
                    xtype: 'displayfield',
                    value: text,
                    overCls: 'app_mobile_text_over',
                    margin: '5 0 0 0',
                    cls: 'app_mobile_text',
                    columnWidth: 1,
                    listeners: {
                        render: function (component) {
                            me.textClick(component);
                        }
                    }
                });
            }
            if (replace) {
                var index = me.items.indexOf(me.currentItem);
                me.content.splice(index - 1, 1, text);
            } else {
                me.content.push(text);
            }
            me.down('#content').setValue(me.getContent());
            win.close();
        })
        win.show();
    },
    showAddImageWin: function (replace) {
        var me = this;
        var win = Ext.create("SMS.view.common.ImageAddWin");
        if (replace) {
            win.title = '替换图片';
            win.down('button[action=add]').setText('替 换');
        } else {
            win.title = '添加图片';
            win.down('button[action=add]').setText('添 加');
        }
        win.down('button[action=add]').on('click', function () {
            var form = win.down("form").getForm();
            if (form.isValid()) {
                form.submit({
                    url: 'web/common/v1/upload?fileType=' + me.fileType,
                    //waitMsg : '正在上传...',
                    success: function (fp, o) {
                        var result = Ext.decode(o.response.responseText);
                        if (replace) {
                            me.currentItem.setSrc(result.url + "!640");
                        } else {
                            me.add({
                                xtype: 'image',
                                src: result.url + "!640",
                                columnWidth: 1,
                                listeners: {
                                    render: function (component) {
                                        me.imageClick(component);
                                    }
                                }
                            });
                        }

                        if (replace) {
                            var index = me.items.indexOf(me.currentItem);
                            me.content.splice(index - 1, 1, "<img src=\"" + result.url + "!640\"/>");
                        } else {
                            me.content.push("<img src=\"" + result.url + "!640\"/>");
                        }
                        me.down('#content').setValue(me.getContent());
                        win.close();
                    },
                    failure: function () {
                        Ext.MessageBox.alert('错误', '上传失败！');
                        win.close();
                    }
                });
            }


        });
        win.show();
    },
    /**
     * 图片和图片之间不需要<br/>换行符，其他情况需要。
     * @returns {string}
     */
    getContent: function () {
        var strContent = "";
        var lastType = "";
        this.content.forEach(function (data) {
            if (data.startsWith('<img')) {
                if (lastType === 'img' || lastType === '') {
                    strContent += data;
                } else {
                    strContent += "<br/>" + data;
                }
                lastType = "img";
            } else {
                if (strContent != '') {
                    strContent += "<br/>" + data;
                } else {
                    strContent += data;
                }
                lastType = "text";
            }
        })
        return strContent;
    },
    textClick: function (component) {
        var me = this;
        component.getEl().on('click', function (e) {
            var floatBar = me.down("#floatToolbar");
            if (floatBar.isVisible()) {
                floatBar.hide();
            } else {
                floatBar.show();
                floatBar.alignTo(component);
                me.currentItem = component;
                me.down("#btnMoveEdit").setText('编辑');
                if (me.items.indexOf(component) == 1) {
                    me.down("#btnMoveUp").hide();
                } else {
                    me.down("#btnMoveUp").show();
                }
                if (me.items.indexOf(component) == me.items.length - 1) {
                    me.down("#btnMoveDown").hide();
                } else {
                    me.down("#btnMoveDown").show();
                }
                if(me.isAds){
                    me.down("#btnMoveHref").show();
                    me.down("#btnDelHref").show();
                }else{
                    me.down("#btnMoveHref").hide();
                    me.down("#btnDelHref").hide();
                }
                if(me.currentItem.getValue().contains("<a href=")){
                    me.down("#btnDelHref").show();
                }else{
                    me.down("#btnDelHref").hide();
                }
            }
        });
    },
    imageClick: function (component) {
        var me = this;
        component.getEl().on('click', function (e) {
            var floatBar = me.down("#floatToolbar");
            if (floatBar.isVisible()) {
                floatBar.hide();
            } else {
                me.down("#btnMoveEdit").setText('替换');
                me.down("#btnMoveHref").hide();
                me.down("#btnDelHref").hide();
                if (me.items.indexOf(component) == 1) {
                    me.down("#btnMoveUp").hide();
                } else {
                    me.down("#btnMoveUp").show();
                }
                if (me.items.indexOf(component) == me.items.length - 1) {
                    me.down("#btnMoveDown").hide();
                } else {
                    me.down("#btnMoveDown").show();
                }
                floatBar.show();
                floatBar.alignTo(component);
                me.currentItem = component;
            }
        });
    }
});