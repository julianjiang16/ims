/**
 * Created by Mo on 2015/6/17.
 */
Ext.define('DSBY.model.menu.MenuParent', {
    extend: 'Ext.data.Model',
    fields: [
        {
            name: 'id',
            type: 'integer',
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
            type: 'int'
        },
        {
            name: 'parentId',
            type: 'int'
        },
        {
            name: 'isLeaf',
            type: 'int'
        }
    ],
    idProperty: 'id',
    proxy: {
        type: 'rest',
        url: 'web/menu/v1/parentmenus',
        limitParam: 'rows',
        reader: {
            type: 'json',
            root: 'data',
            totalProperty: 'count'
        }
    }
});