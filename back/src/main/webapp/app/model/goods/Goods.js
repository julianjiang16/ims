Ext.define('DSBY.model.goods.Goods', {
    extend: 'Ext.data.Model',
    fields: [
        {
            name: 'id',
            type: 'string',
            useNull: true
        },
        {
            name: 'goodsName',
            type: 'string'
        },
        {
            name: 'goodsNo',
            type: 'string'
        },
        {
            name: 'spell',
            type: 'string'
        },
        {
            name: 'typeNo',
            type: 'string'
        },
        {
            name: 'spec',
            type: 'string'
        },
        {
            name: 'auxUnitFirst',
            type: 'string'
        },
        {
            name: 'auxUnitSecond',
            type: 'string'
        },
        {
            name: 'auxValFirst',
            type: 'string'
        },
        {
            name: 'auxValSecond',
            type: 'string'
        },
        {
            name: 'num',
            type: 'string'
        },
        {
            name: 'effectiveDays',
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
        url: 'back/goods/goods',
        limitParam: 'rows',
        reader: {
            type: 'json',
            root: 'data',
            totalProperty: 'count'
        }
    }
});