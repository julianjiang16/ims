Ext.define('DSBY.store.customer.Customer', {
			extend : 'Ext.data.Store',
			model : 'DSBY.model.customer.Customer',
			autoLoad : true,
			autoSync : false
		});