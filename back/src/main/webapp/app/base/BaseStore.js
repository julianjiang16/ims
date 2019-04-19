Ext.define('DSBY.base.BaseStore', {
			extend : 'Ext.data.Store',
    proxy: {
        type: 'rest',
        url: 'back',
        reader: {
            type: 'json',
            root: 'root'
        }
    },
    fields: ['id'],
    autoLoad : true,
    autoSync : false
		});