/**
 * form中必须的字段增加红星。
 */
Ext.define('Ext.ux.Form', {
    extend: 'Ext.form.Panel',
    alias: ['widget.newForm'],
    initComponent: function () {
        this.on('beforeadd', function (me, field) {
            if (!field.allowBlank)
                field.labelSeparator += '<span style="color: rgb(255, 0, 0); padding-left: 2px;font-size:150%">*</span>';
        });
        this.callParent(arguments);
    }

})