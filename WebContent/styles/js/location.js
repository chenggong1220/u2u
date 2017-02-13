$(function(){
	$('#provinceId').combobox({
		editable:false,
	    url: WEB_APP + '/web/location/getProvinces',
	    valueField:'id',
	    textField:'name',
	    onSelect: function (record) {
	    	$('#cityId').combobox({
				editable:false,
			    url: WEB_APP + '/web/location/getCities?provinceId='+record.id,
			    valueField:'id',   
			    textField:'name'
			});
	    }
	});
})