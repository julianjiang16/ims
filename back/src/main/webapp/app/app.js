var Ext = Ext || {};
Ext.manifest = { // the same content as "app.json"
    compatibility: {
        ext: '4.2'
    }
}
Ext.Loader.setConfig({
    enabled: true
});
Ext.Loader.setPath('Ext.ux', 'extjs/ux');
var application;
Ext.application({
    name: 'DSBY',
    appFolder: 'app',
    controllers: ['Menu'],
    autoCreateViewport: true,
    loadController: function (controllers) {
        var me = this;
        var controllers = Ext.Array.from(controllers), ln = controllers.length, i, controller;
        for (i = 0; i < ln; i++) {
            var name = controllers[i];
            if (!me.controllers.containsKey(name)) {
                controller = Ext.create(me.getModuleClassName(name,
                    'controller'), {
                    application: me,
                    id: name
                });
                controller.init(me);
                controller.onLaunch(me);
                me.controllers.add(controller);
            }
        }
    }, launch: function () {
        application = this;
        Ext.tip.QuickTipManager.init();
    }



});


Ext.getApplication = function() {
    return application;
};

Ext.getController = function(name) {
    return Ext.getApplication().getController(name);
};
// 修正grid删除非第一页的所有数据时不能操作的bug
Ext.toolbar.Paging.prototype.onLoad = function() {
    var me = this, pageData, currPage, pageCount, afterText, count, isEmpty;
    count = me.store.getCount();
    isEmpty = count === 0;
    if (!isEmpty) {
        pageData = me.getPageData();
        currPage = pageData.currentPage;
        pageCount = pageData.pageCount;
        afterText = Ext.String.format(me.afterPageText, isNaN(pageCount)
            ? 1
            : pageCount);
    } else if (isEmpty && me.store.getTotalCount() > 0) {
        pageData = me.getPageData();
        if (pageData.currentPage > pageData.pageCount
            && (pageData.pageCount > 0)) {
            me.store.loadPage(pageData.pageCount);
        }
        return;
    } else {
        currPage = 0;
        pageCount = 0;
        afterText = Ext.String.format(me.afterPageText, 0);
    }
    Ext.suspendLayouts();
    me.child('#afterTextItem').setText(afterText);
    me.child('#inputItem').setDisabled(isEmpty).setValue(currPage);
    me.child('#first').setDisabled(currPage === 1 || isEmpty);
    me.child('#prev').setDisabled(currPage === 1 || isEmpty);
    me.child('#next').setDisabled(currPage === pageCount || isEmpty);
    me.child('#last').setDisabled(currPage === pageCount || isEmpty);
    me.child('#refresh').enable();
    me.updateInfo();
    Ext.resumeLayouts(true);
    if (me.rendered) {
        me.fireEvent('change', me, pageData);
    }
};

//修改4.2.1中的一个bug
Ext.define('Ext.view.override.Table', {
    override: 'Ext.view.Table',
    doStripeRows: function(startRow, endRow) {
        var me = this,
            rows,
            rowsLn,
            i,
            row;

        if (me.rendered && me.stripeRows) {
            rows = me.getNodes(startRow, endRow);

            for (i = 0, rowsLn = rows.length; i < rowsLn; i++) {
                row = rows[i];

                if (row) { // self updating; check for row existence
                    row.className = row.className.replace(me.rowClsRe, ' ');
                    startRow++;

                    if (startRow % 2 === 0) {
                        row.className += (' ' + me.altRowCls);
                    }
                }
            }
        }
    }

});

var common = {};
common.renderDate = function(val) {
    if (val == null || val == 0) {
        return '';
    }
    return Ext.Date.format(new Date(val), 'Y-m-d');
};

//必填项添加红色'*'标识
Ext.override(Ext.form.field.Base, {// 针对form中的基本组件
    initComponent : function() {
        if (this.allowBlank !== undefined && !this.allowBlank) {
            if (this.fieldLabel) {
                this.fieldLabel += '<span style="vertical-align: middle;display: -moz-inline-stack;display: inline-block; zoom:1;display:inline;color:red;font-size: 16px">*</span>';
            }
        }
        if (this.tips) {
            this.afterLabelTextTpl = '<img src="images/tips.png" class="icon-help" data-qtip="' + this.tips + '"/>';
        }

        this.msgTarget = 'side';
        this.callParent(arguments);
    }
});
Ext.override(Ext.container.Container, {// 针对form中的容器组件
    initComponent : function() {
        if (this.allowBlank !== undefined && !this.allowBlank) {
            if (this.fieldLabel) {
                this.fieldLabel += '<span style="vertical-align: middle;display: -moz-inline-stack;display: inline-block; zoom:1;display:inline;color:red;font-size: 16px">*</span>';
            }
        }
        if (this.tips) {
            this.afterLabelTextTpl = '<img src="images/tips.png" class="icon-help" data-qtip="' + this.tips + '"/>';
        }

        this.callParent(arguments);
    }
});


Ext.apply(Ext.form.VTypes, {
    //密码重复相同的检查
    repetition: function (val, field) {
        var cmp = field.targetCmp;   //通过targetCmpId的字段查找组件
        if (val == cmp.getValue()) {
            return true;
        } else {
            return false;
        }
    },
    repetitionText: '两个指定组件的组件值不一样',
    //大于等于的检查
    greaterOrEqualThan: function (val, field) {
        var targetVal = field.vtypeConfig.targetCmp.getValue();   //通过targetCmpId的字段查找组件
        if (val >= targetVal) {
            return true;
        } else {
            field.vtypeText = field.vtypeConfig.textName + '的值不能小于' + field.vtypeConfig.targetTextName + "的值";
            return false;
        }
    },
    greaterOrEqualThanText: '',
    //小于等于的检查
    lessOrEqualThan: function (val, field) {
        var targetVal = field.vtypeConfig.me.down("#" + field.vtypeConfig.targetItemId).getValue();   //通过targetCmpId的字段查找组件
        if (val <= targetVal) {
            return true;
        } else {
            field.vtypeText = field.vtypeConfig.textName + '的值不能大于' + field.vtypeConfig.targetTextName + "的值";
            return false;
        }
    },
    lessOrEqualThanText: '',
    //手机号码的检查
    mobile: function (val, field) {
        return /^1[3|4|5|7|8]\d{9}$/.test(value);
    },
    mobileText: '请输入正确的手机号码！',
    //手机和固话的检查
    phone: function () {
        return /(^(0\d{2,3}-)?[2-9]{7,8}$)|(^1[3|4|5|7|8]\d{9}$)/.test(value);
    },
    phoneText: '请输入正确的电话号码！',
    //多个手机的检查，多个手机之间逗号隔开。
    multiMobiles: function (val, field) {
        return /^1[3|4|5|7|8]\d{9}([,，]1[3|4|5|7|8]\d{9})*$/.test(value);
    },
    multiMobilesText: '请输入正确的手机号码，多个号码用逗号隔开！',
    multiPhones: function (val, field) {
        if (val) {
            var phones = val.split(/[,，]/);
            for (var i = 0; i < phones.length; i++) {
                if (!/(^(0\d{2,3}-)?[2-9]{7,8}$)|(^1[3|4|5|7|8]\d{9}$)/.test(phones[i])) {
                    return false;
                }
            }
        }
        return true;
    },
    mobilePhoneText: '请输入正确的手机号码'
});

/**
 * 处理Grid重新加载过后selectionModel中的记录不更新的问题
 * me.selected中存放的是选中的记录的集合
 */
Ext.override(Ext.selection.Model,{
    onStoreLoad:function(store, records, successful, eOpts){
        var me = this,
            length = me.selected.getCount( );

        //如果没有选中的记录，则不需要进行任何的操作
        if(length===0)return;

        //遍历selected并更新其中的记录
        me.selected.eachKey(function(key,item){
            var model = store.getById(key);

            //如果获取到了model就更新，否则从selected中移除
            if(model){
                me.selected.add(model);//add时会覆盖掉原来的值
            }else{
                me.selected.removeAtKey(key);
            }
        })

    }
});



