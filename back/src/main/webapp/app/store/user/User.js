Ext.define('DSBY.store.user.User', {
			extend : 'Ext.data.Store',
			model : 'DSBY.model.user.User',
			autoLoad : true,
			autoSync : false
		});