Ext.define('DSBY.controller.customer.CustomerController', {
    extend: 'DSBY.base.BaseController',
    requires: ['DSBY.view.customer.CustomerList'],
    refs: [
        {
            ref: 'customerList',
            selector: 'customerList'
        }
    ],
    init: function () {
        this.control({
            'customerList button[action=add]': {
                click: this.add
            },
            'customerWin button[action=close]': {
                click: this.close
            },
            'customerList button[action=edit]': {
                click: this.edit
            },
            'customerWin button[action=save]': {
                click: this.save
            },
            'customerManager button[action=search]': {
                click: this.search
            },
        });
    },
});
