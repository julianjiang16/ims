Ext.define('DSBY.model.user.User', {
    extend: 'Ext.data.Model',
    fields: [
        {
            name: 'id',
            type: 'string',
            useNull: true
        },
        {
            name: 'userName',
            type: 'string'
        },
        {
            name: 'password',
            type: 'string'
        },
        {
            name: 'roleId',
            type: 'string'
        },
        {
            name: 'roleName',
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
        url: 'back/user/user',
        limitParam: 'rows',
        reader: {
            type: 'json',
            root: 'data',
            totalProperty: 'count'
        }
    }
});