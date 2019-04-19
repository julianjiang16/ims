Ext.define('DSBY.base.BaseList', {
    extend: 'Ext.grid.Panel',
    popWin: null,
    viewConfig: {
        enableTextSelection: true//控制文字可选
    },
    initComponent: function () {
        //默认属性
        Ext.applyIf(this, {
            frame: true,
            columnLines: true,
            scroll: true,
            bbar: Ext.create('Ext.PagingToolbar', {
                store: this.store,
                displayInfo: true,
                displayMsg: '显示 {0} - {1} 条，共计 {2} 条',
                emptyMsg: "没有数据"
            })
        });
        this.callParent(arguments);
    }
});