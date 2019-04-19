/**
 * 图片上传插件。
 */
Ext.define('Ext.ux.form.field.ImageFileField', {
    alias: 'widget.imagefilefield',
    extend: 'Ext.form.FormPanel',
    name: 'imagePath',
    allowBlank: true,
    blankText: '',
    msgTarget: 'side',
    serverImageUrl: '',
    tips: '',
    imagePath: '',
    afterLabelTextTpl: '',
    fileType: '',
    header: false,
    border: false,
    multiple: false,
    labelWidth: 80,//控制label的宽度
    smallImageFlag: '',
    bigImageFlag: '',
    layout: 'column',
    pid: '',
    initComponent: function () {
        var me = this;
        this.items = [
            {
                xtype: 'fieldcontainer',
                fieldLabel: me.fieldLabel,
                afterLabelTextTpl: me.afterLabelTextTpl,
                labelWidth: me.labelWidth,
                tips: me.tips,
                allowBlank: me.allowBlank,
                layout: 'hbox',
                items: [
                    {
                        xtype: 'filefield',
                        itemId: 'imagefilefield_filefield',
                        name:'file',
                        buttonOnly: true,
                        buttonText: '选择',
                        listeners: {
                            change: function () {
                                var form = this.up("form").getForm();
                                var fileField = me.down("#imagefilefield_filefield");
                                var imageField = me.down("#imagefilefield_image");
                                if (fileField.getValue() == '') {
                                    return;
                                }
                                var fileName = fileField.getValue().split('\\');
                                if (!me.checkFile(fileName[fileName.length - 1])) {
                                    return;
                                }

                                me.down("#imagefilefield_imagePath").setDisabled(true);
                                form.submit({
                                    url: 'back/common/upload',
                                    timeout: 60,
                                    waitMsg: '正在上传...',
                                    header:{
                                        'Content-Type':'multipart/form-data'
                                    },
                                    success: function (fp, o) {
                                        var result = Ext.decode(o.response.responseText);
                                        me.serverImageUrl = result.url;
                                        imageField.setSrc(result.url);
                                        me.down("#imagefilefield_imagePath").setValue(result.imagePath);
                                        me.imagePath = result.imagePath;
                                        if (!imageField.isVisible()) {
                                            imageField.show();
                                        }
                                        me.down("button").setDisabled(false);
                                        me.down("button").setText('删除');
                                        me.down("#imagefilefield_imagePath").setDisabled(false);
                                    },
                                    failure: function (fp, o) {
                                        me.up("panel").getEl().unmask();
                                        me.down("#imagefilefield_imagePath").setDisabled(false);
                                        Ext.MessageBox.alert("错误", "图片上传失败,请检查网络是否有问题或尝试重新上传！");
                                    }
                                });
                            }
                        }
                    },
                    {
                        xtype: 'splitter'
                    },
                    {
                        xtype: 'button',
                        text: '删除',
                        handler: function () {
                            if (this.text == '删除') {
                                me.down("#imagefilefield_image").hide();
                                me.down("#imagefilefield_imagePath").setValue('');
                                this.setText('还原');
                            } else {
                                this.setText('删除');
                                me.down("#imagefilefield_image").show();
                                me.down("#imagefilefield_imagePath").setValue(me.imagePath);
                            }
                        }
                    },
                    {
                        xtype: 'splitter'
                    },
                    {
                        xtype: 'image',
                        width: 24,
                        height: 24,
                        itemId: 'imagefilefield_image',
                        overCls: 'app_mobile_text_over',
                        src: me.getSmallImageUrl(),
                        listeners: {
                            el: {
                                click: function () {
                                    me.showPreview();
                                }
                            }
                        }
                    },
                    {
                        xtype: 'textfield',
                        name: me.name,
                        itemId: 'imagefilefield_imagePath',
                        allowBlank: me.allowBlank,
                        blankText: me.blankText,
                        hidden: true,
                        hideMode: 'visibility',
                        msgTarget: 'side'
                    }
                ]
            }
        ];
        this.callParent(arguments);
        if (me.serverImageUrl == '') {
            me.down("#imagefilefield_image").hide();
            me.down("button").disable();
        } else {
            me.imagePath = me.serverImageUrl.substringAfter(".com/");
            me.down("#imagefilefield_imagePath").setValue(this.imagePath);
        }
    },
    /**
     * 获取小图的路径。
     */
    getSmallImageUrl: function () {
        if (this.serverImageUrl == '') {
            return '';
        }
        return this.serverImageUrl;
    },

    /**
     * 获取大图的路径
     */
    getBigImageUrl: function () {
        if (this.serverImageUrl == '') {
            return '';
        }
        if (this.bigImageFlag != '') {
            return this.serverImageUrl
                ;
        } else {
            return this.serverImageUrl;
        }
    },
    showPreview: function (btn, e) {
        var me = this;
        try {
            var img = new Image(),
                style;
            img.src = this.getBigImageUrl();
            if (400 / img.width < 400 / img.height) {
                style = 'style="max-width:100%"';
            } else {
                style = 'style="max-height:100%"';
            }
        } catch (err) {
        }
        var win = Ext.create('Ext.window.Window', {
            title: '预览',
            height: 400,
            width: 400,
            modal: true,
            layout: 'fit',
            html: '<img src="' + img.src + '" ' + style + ' />',
            tools: [
                {
                    type: 'maximize',
                    handler: function (event, toolEl, owner, tool) {
                        win.toggleMaximize();
                    },
                    scope: win
                }
            ]
        }).show();
    },
    setServerImageUrl: function (url) {
        if (!url) {
            this.serverImageUrl = '';
            this.down("#imagefilefield_image").setSrc('');
            this.imagePath = '';
            this.down("#imagefilefield_imagePath").setValue(this.imagePath);
            this.down("#imagefilefield_image").hide();
            this.down("button").setDisabled(true);
            this.down("button").setText("删除");
        } else {
            this.serverImageUrl = url;
            this.down("#imagefilefield_image").setSrc(this.getSmallImageUrl());
            this.imagePath = url;
            this.down("#imagefilefield_imagePath").setValue(this.imagePath);
            if (!this.down("#imagefilefield_image").isVisible()) {
                this.down("#imagefilefield_image").show();
                this.down("button").setDisabled(false);
                this.down("button").setText("删除");
            }
        }
    },
    setDisabled:function(disabled){
        this.down("#imagefilefield_imagePath").setDisabled(disabled);
    },
    checkFile: function (v) {
        //验证图片文件的正则
        var img_reg = /\.([jJ][pP][gG]){1}$|\.([jJ][pP][eE][gG]){1}$|\.([pP][nN][gG]){1}$/;
        if (!img_reg.test(v)) {
            Ext.Msg.alert('提示', '文件类型错误,请选择图片文件(jpe/jpeg/png)');
            return false;
        }
        return true;
    }

});
