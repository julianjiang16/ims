Ext.define('DSBY.base.LocalCombo', {
	alias: 'widget.localCombo',
    extend: 'Ext.form.field.ComboBox',
    border : false,
    editable: false,
    displayField: 'name',
    valueField: 'val',
    emptyText: '请选择',
    queryMode: 'local',
    triggerAction:'all',
    initComponent: function () {
        var me = this;
        me.callParent(arguments);
    }
});
