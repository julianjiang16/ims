Ext.define('DSBY.model.supplier.Supplier', {
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
            name: 'bank',
            type: 'string'
        },
        {
            name: 'bankNo',
            type: 'string'
        },
        {
            name: 'remarks',
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
        url: 'back/supplier/supplier',
        limitParam: 'rows',
        reader: {
            type: 'json',
            root: 'data',
            totalProperty: 'count'
        }
    }
});