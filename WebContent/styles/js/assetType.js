$(function(){
	$('#brandId').combobox({
		editable:false,
	    url: WEB_APP + '/web/asset/brand/json',
	    valueField:'id',
	    textField:'name',
	    onSelect: function (record) {
	    	$('#assetTypeId').combobox({
	    		editable:false,
	    	    url: WEB_APP + '/web/asset/type/json?brandId=' + record.id,
	    	    valueField:'id',
	    	    textField:'model'
	    	});
	    }
	});
})