Ext.define('DSBY.controller.priceTemplate.PriceTemplateController', {
    extend: 'DSBY.base.BaseController',
    requires: ['DSBY.view.priceTemplate.PriceTemplateList'],
    refs: [
        {
            ref: 'priceTemplateList',
            selector: 'priceTemplateList'
        }
    ],
    init: function () {
        this.control({
            'priceTemplateList button[action=add]': {
                click: this.add
            },
            'priceTemplateWin button[action=close]': {
                click: this.close
            },
            'priceTemplateList button[action=edit]': {
                click: this.edit
            },
            'priceTemplateWin button[action=save]': {
                click: this.save
            },
            'priceTemplateManager button[action=search]': {
                click: this.search
            },
        });
    },
});
