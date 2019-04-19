Ext.define('DSBY.base.BaseManager', {
	extend : 'Ext.panel.Panel',
    store: Ext.create("DSBY.base.BaseStore"),
    rights:[],
    listeners : {
        afterrender: function (manager) {
            me = this;
            me.store.on("load", function(){
                me.store.each(function(rec){
                    me.rights.push(rec.get('id'));
                    if(Ext.getCmp(rec.get('id'))){
                        Ext.getCmp(rec.get('id')).destroy();
                    }
                });
            });
            Ext.Array.forEach(me.rights,function(id,index){
                if(Ext.getCmp(id)){
                    Ext.getCmp(id).destroy();
                }
            });
        }
    }
});