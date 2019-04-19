Ext.define('Ext.ux.LinkButton', {
    extend: 'Ext.Component',
    alias: 'widget.linkbutton',
    baseCls: Ext.baseCSSPrefix + 'linkbutton',
    autoEl: {
        tag: 'a',
        href: this.path,
        title: this.title
    },
    renderTpl: '{text}',
    path: '#',
    title: null,
    defaultParams: {},
    stopEvent: true,
    initComponent: function () {
        'use strict';
        var me = this;
        me.renderData = {
            text: me.text
        };
        me.addEvents('click');
        me.superclass.initComponent.apply(me, arguments);
    },
    afterRender: function () {
        'use strict';
        var me = this;
        me.mon(me.getEl(), 'click', me.handler, me, {stopEvent: me.stopEvent});
        me.setParams();
    },
    setParams: function (params) {
        'use strict';
        var me = this,
            el = me.getEl(),
            p,
            prop,
            path;
        p = Ext.clone(me.defaultParams);
        if (params) {
            p = Ext.merge(p, params);
        }
        path = me.path + '?';
        for (prop in p) {
            if (p.hasOwnProperty(prop)) {
                path += prop + '=' + p[prop] + '&';
            }
        }
        path = path.substring(0, path.length - 1);
        if (el && el.dom) {
            el.dom.href = path;
            el.dom.title = me.title;
        }
    },
    handler: function (e, el, eOpts) {
        'use strict';
        var me = this;
        me.fireEvent('click', me, e, eOpts);
    }
});