/**
 * 
 */
 Ext.define('Ext.ux.form.MultiCheckBox',{
 	extend:'Ext.form.CheckboxGroup',
 	alias:['widget.multiCheckBox'],
 	autoScroll:true,
 	columns: 6,
     vertical: true,
     width: 700,
 	initComponent: function(){
		this.callParent(arguments);
 	},
 	listeners: {
 		beforerender:function(){
 		var me = this;
// 		//加载可选项
 		var checkItems = [];
 		me.store.each(function(record){
			checkItems.push({
				boxLabel: record.raw.name,
				name:/*'privilegeIds'*/me.name,
				inputValue:record.raw.id
			});
		});
		me.add(checkItems);
		me.setValue();
		//保证每次打开窗口都可以显示内容
		me.up('form').up('window').on('beforeshow',function(){
			me.setValue();
		
		});
		
 		}
 	
 	},
 	setValue:function(){
        debugger;
 		var me = this;
 		//赋值
		var form = me.up('form');
		var record = form.getRecord();
		if(Ext.isEmpty(record))return false;
		var boxs = me.items;
		//var ids = record.raw.privilegeIds.split(',');
		Ext.Array.each(record.raw.privilegeIds,function(value, index, id){
			boxs.each(function(box){
//				console.log("input value :"+box.inputValue);
//				console.log("record value :"+id);
	               if(value==box.inputValue){
					box.setValue(true);
					}
			})
		});
 		}
 
 });