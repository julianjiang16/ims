Ext.define("Ext.ux.ComboBoxTree",

    {

        extend: "Ext.form.field.Picker",
        requires: ["Ext.tree.Panel"],
        alias: 'widget.checkcombo',
        xtype: 'comboboxtree',
        hiddenValue: null,
        getHiddenValue: function () {
            return this.hiddenValue;
        },
        /*getValue: function () { 
          return this.hiddenValue; 
          }, */
        //点击获取值
        setHiddenValue: function (id, text) {
            Ext.form.ComboBox.superclass.setValue.call(this, text);
            this.hiddenValue = id;
        },
        initComponent: function() {
            var self = this;
            Ext.apply(self, {
                fieldLabel: self.fieldLabel,
                labelWidth: self.labelWidth
            });
            self.callParent();
        },
        createPicker: function() {
            var self = this;
            var store = Ext.create('Ext.data.TreeStore', {
                proxy: {
                    type: 'ajax',
                    url: self.storeUrl
                },
                sorters: [{
                    property: 'leaf',
                    direction: 'ASC'
                },
                    {
                        property: 'text',
                        direction: 'ASC'
                    }],
                root: {
                    id: self.rootId,
                    text: self.rootText
                },
                nodeParameter: self.treeNodeParameter
            });
            self.picker = new Ext.tree.Panel({
                height: 300,
                autoScroll: true,
                floating: true,
                focusOnToFront: false,
                shadow: true,
                ownerCt: this.ownerCt,
                useArrows: true,
                store: store,
                rootVisible: false
            });
            self.picker.on({
                checkchange: function(record, checked) {
                    var checkModel = self.checkModel;
                    if (checkModel == 'double') {
                        var root = self.picker.getRootNode();
                        root.cascadeBy(function(node) {
                            if (node.get('text') != record.get('text')) {
                                node.set('checked', false);
                            }
                        });
                        if (record.get('leaf') && checked) {
                            self.setHiddenValue(values.join(','), names.join(','));//id-隐藏值,text-显示值       
                        } else {
                            record.set('checked', false);
                            self.setHiddenValue('', '');//id-隐藏值,text-显示值       
                        }
                    } else {

                        var cascade = self.cascade;

                        if (checked == true) {
                            if (cascade == 'both' || cascade == 'child' || cascade == 'parent') {
                                if (cascade == 'child' || cascade == 'both') {
                                    if (!record.get("leaf") && checked) record.cascadeBy(function(record) {
                                        record.set('checked', true);
                                    });

                                }
                                if (cascade == 'parent' || cascade == 'both') {
                                    pNode = record.parentNode;
                                    for (; pNode != null; pNode = pNode.parentNode) {
                                        pNode.set("checked", true);
                                    }
                                }

                            }

                        } else if (checked == false) {
                            if (cascade == 'both' || cascade == 'child' || cascade == 'parent') {
                                if (cascade == 'child' || cascade == 'both') {
                                    if (!record.get("leaf") && !checked) record.cascadeBy(function(record) {

                                        record.set('checked', false);

                                    });
                                }

                            }

                        }

                        var records = self.picker.getView().getChecked(),
                            names = [],
                            values = [];
                        Ext.Array.each(records,
                            function(rec) {
                                names.push(rec.get('text'));
                                values.push(rec.get('id'));
                            });
                        self.setHiddenValue(values.join(','), names.join(','));//id-隐藏值,text-显示值       
                    }

                },
                itemclick: function(tree, record, item, index, e, options) {

                    var checkModel = self.checkModel;

                    if (checkModel == 'single') {
                        if (record.get('leaf')) {
                            self.setHiddenValue(values.join(','), names.join(','));//id-隐藏值,text-显示值       
                        } else {
                            self.setHiddenValue('', '');//id-隐藏值,text-显示值       
                        }
                    }

                }
            });
            return self.picker;
        },
        alignPicker: function() {
            var me = this,
                picker, isAbove, aboveSfx = '-above';
            if (this.isExpanded) {
                picker = me.getPicker();
                if (me.matchFieldWidth) {
                    picker.setWidth(me.bodyEl.getWidth());
                }
                if (picker.isFloating()) {
                    picker.alignTo(me.inputEl, "", me.pickerOffset); // ""->tl  
                    isAbove = picker.el.getY() < me.inputEl.getY();
                    me.bodyEl[isAbove ? 'addCls': 'removeCls'](me.openCls + aboveSfx);
                    picker.el[isAbove ? 'addCls': 'removeCls'](picker.baseCls + aboveSfx);
                }
            }
        }
    });