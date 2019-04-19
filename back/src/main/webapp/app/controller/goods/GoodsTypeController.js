Ext.define('DSBY.controller.goods.GoodsTypeController', {
    extend: 'DSBY.base.BaseController',
    requires: ['DSBY.view.goodsType.GoodsTypeList'],
    refs: [
        {
            ref: 'goodsTypeList',
            selector: 'goodsTypeList'
        }
    ],
    init: function () {
        this.control({
            'goodsTypeList button[action=add]': {
                click: this.add
            },
            'goodsTypeWin button[action=close]': {
                click: this.close
            },
            'goodsTypeList button[action=edit]': {
                click: this.edit
            },
            'goodsTypeWin button[action=save]': {
                click: this.save
            },
            'goodsTypeManager button[action=search]': {
                click: this.search
            },
        });
    },
});
