Ext.define('DSBY.model.priceTemplate.PriceTemplate', {
    extend: 'Ext.data.Model',
    fields: [
        {
            name: 'id',
            type: 'string',
            useNull: true
        },
        {
            name: 'no',
            type: 'string'
        },
        {
            name: 'name',
            type: 'string'
        },
        {
            name: 'type',
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
        url: 'back/price/template',
        limitParam: 'rows',
        reader: {
            type: 'json',
            root: 'data',
            totalProperty: 'count'
        }
    }
});