Ext.define('DSBY.base.RemoteCombo', {
	alias: 'widget.remoteCombo',
    extend: 'Ext.form.field.ComboBox',
    border : false,
    editable: true,
    minChars : 1,
    displayField: 'name',
    valueField: 'id',
    emptyText: '请输入或选择',
    triggerAction:'all',
    queryMode:"remote",
    initComponent: function () {
        var me = this;
        me.callParent(arguments);
    }
});
