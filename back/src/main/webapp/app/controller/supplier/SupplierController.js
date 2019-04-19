Ext.define('DSBY.controller.supplier.SupplierController', {
    extend: 'DSBY.base.BaseController',
    requires: ['DSBY.view.supplier.SupplierList'],
    refs: [
        {
            ref: 'supplierList',
            selector: 'supplierList'
        }
    ],
    init: function () {
        this.control({
            'supplierList button[action=add]': {
                click: this.add
            },
            'supplierWin button[action=close]': {
                click: this.close
            },
            'supplierList button[action=edit]': {
                click: this.edit
            },
            'supplierWin button[action=save]': {
                click: this.save
            },
            'supplierManager button[action=search]': {
                click: this.search
            },
        });
    },
});
