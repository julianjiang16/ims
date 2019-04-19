Ext.define('DSBY.model.role.Role', {
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
            name:'gmtCreate',
            type:'date',
            convert:function (v) {
                if (v != null && v != '')
                {
                    return new Date(v)
                }else
                    return v
            }
        }
    ],
    idProperty: 'id',
    proxy: {
        type: 'rest',
        url: 'back/role/roles',
        limitParam: 'rows',
        reader: {
            type: 'json',
            root: 'data',
            totalProperty: 'count'
        }
    }
});