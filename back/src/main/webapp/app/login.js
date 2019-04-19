/**
 * @description 主页面，采用边界布局
 */
Ext.define('App.LoginPage', {
	extend : 'Ext.window.Window',

	/** 页面基本属性配置 */
	title : '登 录',
	iconCls : 'app-heatIcon',
	width : 550,
	closable : false,
	resizable : false,
	draggable : false,
	layout : {
		type : 'vbox',
		align : 'stretch',
		pack : 'start'
	},

	/** 自定义属性,自动生成setter/getter */
	config : {
		loginForm : {},
		focusField : {}
	},

	/** 初始化页面组件 */
	initComponent : function() {
		
		var loginForm = Ext.create('Ext.form.Panel', {
			flex : 1,
			layout : 'anchor',
			bodyPadding : 10,
			margin : '0 -1 -1 -1',
			defaults : {
				xtype : 'textfield',
				labelAlign : 'right',
				labelWidth : 110,
				allowBlank : false,
				anchor : '85%',
				msgTarget : 'qtip'
			},
			items : [{
				fieldLabel : '帐&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号',
				blankText : '请输入您的登录账号',
				fieldStyle : 'background:url(images/user.png) no-repeat left center; padding-left:20px; font-weight:bold;',
				name : 'loginName',
				margin : '10 0'
			}, {
				fieldLabel : '密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码',
				blankText : '请输入您的登录密码',
				fieldStyle : 'background:url(images/key.png) no-repeat left center; padding-left:20px; font-weight:bold;',
				inputType : 'password',
				name : 'password',
				margin : '10 0'
			}, {
				xtype : 'fieldcontainer',
				layout : 'hbox',
				margin : '10 0',
				items : [{
							xtype : 'container',
							width : 115
						}, {
							flex : 1,
							xtype : 'checkboxfield',
							boxLabel : '记住帐号',
							name : 'remember-loginName'
						}]
			}]
		});

		// 添加回车事件,左右选择不同的角色
		this.on('afterrender', function(thiz, opts) {
					var me=this;
					Ext.create('Ext.util.KeyNav', thiz.getEl(), {
								 enter : me.loginAction,
								 scope : this
							});
				}, this);

		this.buttons = [{
					text : '登&nbsp;&nbsp;录&nbsp;&nbsp;&nbsp;&nbsp;',
					iconCls : 'user_go',
					handler : this.loginAction,
					scope : this
				}/*
					 * , { text : '选&nbsp;&nbsp;项', iconCls : 'app-homeIcon',
					 * handler : function(btn) { }, scope : this }
					 */];

		this.items = [{
			height : 105,
			border : false,
			html : '<div style=\' height:100%;'
					+ 'padding-left:120px; font-size:35px; font-weight:bold; font-family:宋体,sans-serif; line-height:100px;\'>'
					+ '万家欢进销存管理平台' + '</div>'
		}, loginForm];

		this.initConfig({
					loginForm : loginForm
				});

		this.callParent();
		this.on('beforeshow', this.loadData, this);
		this.on('show', function() {
					this.getFocusField().focus(true)
				}, this, {
					delay : 300
				});
	},

	/** 界面显示后加载数据 */
	loadData : function(thiz) {
		var form = thiz.getLoginForm().getForm();

		var loginNameField = form.findField('loginName');
		var passwordField = form.findField('password');
		// var captchaField = form.findField('captcha');
		var reUsernameField = form.findField('remember-loginName');

		// 取出cookies是否记住密码和帐号
		var reUsername = Ext.util.Cookies.get('remember-loginName');
		reUsernameField.setValue(reUsername == 'Y');

		// 根据"记住"情况,给帐号和密码赋值,并确定光标位置
		this.setFocusField(loginNameField);
		if (reUsername == 'Y') {
			loginNameField.setValue(Ext.util.Cookies.get('loginName'));
			this.setFocusField(passwordField);
		}
	},

	/** 登陆操作 */
	loginAction : function() {

		var form = this.getLoginForm().getForm();
		if (!form.isValid()) {
			return;
		}
		// 设置cookies
		this.setUserCookies(form);
		// 执行登陆
		this.getEl().mask('正在登录中，请稍候...');

		Ext.Ajax.request({
					method : 'POST',
					url : 'back/user/login',
					params : form.getValues(),
					success : function(response, options) {
                        var resp = Ext.decode(response.responseText);
                        this.getEl().unmask();
						if(resp.success==true){
							window.location.href="index.html"
						}else
						{
							Ext.Msg.alert("提示",resp.msg);
						}
					},
					failure : function(response, options) {
						this.getEl().unmask();
						Ext.Msg.alert('提示', '登录失败！用户名或密码错误!');
					},
					scope : this
				});
	},
	setUserCookies : function(form) {
		var reUsername = form.findField('remember-loginName').checked;
		// 有效期为一年
		var expires = new Date(new Date().getTime() + (365 * 24 * 60 * 60 * 1000));
		// 记住帐号
		Ext.util.Cookies.set('loginName', form.findField('loginName')
							.getValue(), expires);
		// 记住checkbox
		Ext.util.Cookies.set('remember-loginName', reUsername ? 'Y' : 'N', expires);
	}
});