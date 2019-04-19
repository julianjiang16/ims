Ext.define('DSBY.controller.user.UserController', {
    extend: 'DSBY.base.BaseController',
    requires: ['DSBY.view.user.UserList'],
    refs: [
        {
            ref: 'userList',
            selector: 'userList'
        }
    ],
    init: function () {
        this.control({
            'userList button[action=add]': {
                click: this.add
            },
            'userWin button[action=close]': {
                click: this.close
            },
            'userList button[action=edit]': {
                click: this.edit
            },
            'userWin button[action=save]': {
                click: this.save
            },
            'userPasswordWin button[action=close]': {
                click: this.close
            },
        });
    },
});
