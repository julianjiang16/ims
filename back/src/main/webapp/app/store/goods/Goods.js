Ext.define('DSBY.store.goods.Goods', {
			extend : 'Ext.data.Store',
			model : 'DSBY.model.goods.Goods',
			autoLoad : true,
			autoSync : false
		});