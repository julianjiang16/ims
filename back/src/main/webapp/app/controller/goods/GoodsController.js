Ext.define('DSBY.controller.goods.GoodsController', {
    extend: 'DSBY.base.BaseController',
    requires: ['DSBY.view.goods.GoodsList'],
    refs: [
        {
            ref: 'goodsList',
            selector: 'goodsList'
        }
    ],
    init: function () {
        this.control({
            'goodsList button[action=add]': {
                click: this.add
            },
            'goodsWin button[action=close]': {
                click: this.close
            },
            'goodsList button[action=edit]': {
                click: this.edit
            },
            'goodsWin button[action=save]': {
                click: this.save
            },
            'goodsManager button[action=search]': {
                click: this.search
            },
            'goodsList button[action=export]': {
                click: this.export
            },
            'goodsTableWin button[action=trueExport]': {
                click: this.trueExport
            },
            'goodsTableWin button[action=close]': {
                click: this.close
            },
        });
    },
    export:function (btn) {
        var win=Ext.create('DSBY.view.goods.GoodsTableWin');
        win.show();
    },
    trueExport:function (btn) {
        var win = btn.up('window');
        var form = win.down('form').getForm();
        var field=form.findField('field').getValue();
        var result="";
        var i=0
        Ext.each(field.field,function (item) {
            if (i!==0){
                result=result+","+item;
            }else {
                result=item;
            }
            i++;
        })
        Ext.Msg.confirm("确认","请确认需要导出的字段！",function (btn) {
            if (btn==='yes'){
                window.location.href = 'back/goods/export?field='+result;
                win.destroy()
                Ext.Msg.alert("消息","表格导出成功");
            }
        })
    }
});
