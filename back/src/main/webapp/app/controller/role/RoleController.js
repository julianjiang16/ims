Ext.define('DSBY.controller.role.RoleController', {
    extend: 'DSBY.base.BaseController',
    requires: ['DSBY.view.role.RoleList','DSBY.view.role.RoleWin','DSBY.view.role.RoleMenuWin'],
    refs: [
        {
            ref: 'roleList',
            selector: 'roleList'
        }
    ],
    init: function () {
        this.control({
            'roleWin button[action=save]': {
                click: this.save
            },
            'roleWin button[action=close]': {
                click: this.close
            },
            'roleList button[action=editMenu]': {
                click: this.editRoleMenuWin
            },
            'roleMenuWin button[action=save]': {
                click: this.saveRoleMenu
            },
            'roleMenuWin button[action=close]': {
                click: this.close
            },
        });
    },
    editRoleMenuWin:function (button) {
        var list = button.up("grid");
        var record = this.getGridSelData(list);
        if(!record){
            return;
        }
        var rid=record.get('id');
        // 加载菜单树
        var win=Ext.create('DSBY.view.role.RoleMenuWin',{
            rid:rid
        });
        win.show()
    },
    saveRoleMenu:function (btn) {
        var win=btn.up('window'),store=win.down('check-tree').getStore(),root=store.getRootNode(),roleMenus=''
        var me=this;
        Ext.each(root.childNodes,function (i) {
            // 是叶子节点直接append
            if (i.data.checked){
                roleMenus+=i.data.id+'$';
            }
            if (!i.data.leaf) {
                roleMenus=me.getChildMenuIds(i.childNodes,roleMenus);
            }
        })
        Ext.Ajax.request({
            url:'back/role/roleMenu',
            method:'POST',
            jsonData:{
                roleId:win.rid,
                menuId:roleMenus
            },
            success:function (res) {
                var data=Ext.decode(res.responseText);
                if (data.success) {
                    win.destroy();
                }
                me.total(data.msg)
            },
            failure:function (e) {
                console.log(e)
            }
        })
    },
    getChildMenuIds:function(node,ids){
        var  me=this;
        Ext.each(node,function (i) {
            if (i.data.checked){
                ids+=i.data.id+'$';
            }
            if (!i.data.leaf){
                ids=me.getChildMenuIds(i.childNodes,ids);
            }
        })
        return ids;
    }
});
