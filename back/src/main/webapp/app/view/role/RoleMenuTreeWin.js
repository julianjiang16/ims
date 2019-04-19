Ext.define('DSBY.view.role.RoleMenuTreeWin', {
    extend: 'Ext.tree.Panel',
    requires: [
        'Ext.data.TreeStore'
    ],
    xtype: 'check-tree',
    rootVisible: false,
    alias: 'widget.roleMenuTreeWin',
    useArrows: true,
    width: 250,
    height: 300,
    listeners:{
        checkchange: function(node, checked, eOpts){
            // 重定义this 不然会出错
            var me=this;
            me.travelChildrenChecked(node, checked, eOpts);
            me.travelParentChecked(node, checked, eOpts);
        }
    },
    initComponent: function(){
        var me=this;
        Ext.apply(this, {
            store: new Ext.data.TreeStore({
                proxy: {
                    type: 'ajax',
                    url: 'back/menu/roleMenu?roleId='+me.rid
                },
                sorters: [{
                    property: 'leaf',
                    direction: 'ASC'
                }, {
                    property: 'text',
                    direction: 'ASC'
                }]
            })
        });
        this.callParent();
    },
    /** 递归遍历父节点 **/
    travelParentChecked : function(node, checkStatus, opts){
        var me=this;
        //父节点
        var upNode = node.parentNode;
        if(upNode != null){
            var opts = {};
            opts["isPassive"] = true;
            //父节点当前选中状态
            var upChecked = upNode.data.checked;

            //选中状态，遍历父节点，判断有父节点下的子节点是否都全选
            if(checkStatus){
                var allChecked = true;
                //此时父节点不可能是选中状态
                //如果有一个节点未选中，可以判断，当前父节点肯定是未选中状态，所以此时不必向上遍历
                upNode.eachChild(function (child) {
                    if(!child.data.checked){
                        allChecked = false;

                        return false;
                    }
                });

                upNode.set('checked', allChecked);
                if(allChecked){
                    me.travelParentChecked(upNode, allChecked, opts);
                }else{//如果后台传递数据时，选择状态正确的话，此处不需要执行
                    me.travelParentChecked(upNode, allChecked, opts);
                }
            }else{//未选中，让父节点全都 不选
                if(upNode.data.checked){
                    upNode.set('checked', checkStatus);
                    me.travelParentChecked(upNode, checkStatus, opts);
                }else{
                    //travelParentChecked(upNode, allChecked, opts);
                }
            }
        }
    },

    /** 递归遍历子节点，复选框 **/
    travelChildrenChecked : function(node, checkStatus, eOpts){
        var me=this;
        var isLeaf = node.data.leaf;
        if(!isLeaf){
            node.expand(false, function(){
                if(eOpts["isPassive"] == null){//主动点击
                    node.eachChild(function (child) {
                        child.set('checked', checkStatus);

                        me.travelChildrenChecked(child, checkStatus, eOpts);
                        //child.fireEvent('checkchange',child, checked);//不知什么原因，不起作用
                    });
                }
            });
        }
        node.set('checked', checkStatus);
    }
});