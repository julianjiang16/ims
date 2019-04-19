Ext.define('DSBY.store.menu.MenuTree', {
    extend: 'Ext.data.TreeStore',
    proxy: {
        type: 'ajax',
        url: 'back/menu/menu'
    },
    root: {
        text: '系统菜单',
        id: '0',
        expanded: true,
        children: []
    },
    fields:['name','text','id','nodeId','qtitle','icon','leaf','id','parentId','level']
});