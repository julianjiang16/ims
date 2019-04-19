Ext.define('DSBY.model.customer.Customer', {
    extend: 'Ext.data.Model',
    fields: [
        {
            name: 'id',
            type: 'string',
            useNull: true
        },
        {
            name: 'name',
            type: 'string'
        },
        {
            name: 'no',
            type: 'string'
        },
        {
            name: 'abbr',
            type: 'string'
        },
        {
            name: 'address',
            type: 'string'
        },
        {
            name: 'contacts',
            type: 'string'
        },
        {
            name: 'contactsPhone',
            type: 'string'
        },
        {
            name: 'type',
            type: 'string'
        },
        {
            name: 'taxNum',
            type: 'string'
        },
        {
            name: 'remarks',
            type: 'string'
        },
        {
            name: 'bank',
            type: 'string'
        },
        {
            name: 'bankNo',
            type: 'string'
        },
        {
            name:'gmtCreate',
            type:'date',
            convert:function (v) {
                if (v != null && v != '') {
                    return new Date(v)
                } else
                    return v
            }
        }
    ],
    idProperty: 'id',
    proxy: {
        type: 'rest',
        url: 'back/customer/customer',
        limitParam: 'rows',
        reader: {
            type: 'json',
            root: 'data',
            totalProperty: 'count'
        }
    }
});