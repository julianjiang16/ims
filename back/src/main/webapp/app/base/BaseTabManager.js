Ext.define('DSBY.base.BaseTabManager', {
    extend : 'Ext.tab.Panel',
    store: Ext.create("DSBY.base.BaseStore"),
    rights:[],
    listeners : {
        afterrender: function (manager) {
            me = this;
            me.store.on("load", function(){
                me.store.each(function(rec){
                    me.rights.push(rec.get('id'));
                    if(me.down("#"+rec.get('id'))){
                        me.down("#"+rec.get('id')).destroy();
                    }
                });
            });
            Ext.Array.forEach(me.rights,function(id,index){
                if( me.down("#"+id)){
                    me.down("#"+id).destroy();
                }
            });
        }
    }
});