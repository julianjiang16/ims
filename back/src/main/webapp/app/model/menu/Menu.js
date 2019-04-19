Ext.define('DSBY.model.menu.Menu', {
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
            name: 'nodeId',
            type: 'string'
        },
        {
            name: 'qtitle',
            type: 'string'
        },
        {
            name: 'level',
            type: 'string'
        },
        {
            name: 'parentId',
            type: 'string'
        },
        {
            name: 'icon',
            type: 'string'
        },
        {
            name: 'leaf',
            type: 'boolean'
        }
    ],
    idProperty: 'id',
    proxy: {
        type: 'rest',
        url: 'back/menu/menu',
        limitParam: 'rows',
        reader: {
            type: 'json',
            root: 'data',
            totalProperty: 'count'
        }
    }
});