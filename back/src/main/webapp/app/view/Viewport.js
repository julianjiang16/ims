Ext.define('DSBY.view.Viewport', {
    extend: 'Ext.container.Viewport',
    layout: 'fit',
    hideBorders: true,
    requires: [
        'DSBY.view.Header',
        'DSBY.view.Menu',
        'DSBY.view.TabPanel',
        'DSBY.view.South'
    ],
    initComponent: function () {
        var me = this;
        Ext.apply(me, {
            items: [
                {
                    id: 'desk',
                    layout: 'border',
                    items: [
                        Ext.create('DSBY.view.Header'),
                        Ext.create('DSBY.view.Menu'),
                        Ext.create('DSBY.view.TabPanel'),
                        Ext.create('DSBY.view.South')
                    ]
                }
            ]
        });
        me.callParent(arguments);
    }
});
