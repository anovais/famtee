$(
		function(){
			var dialog;
			dialog = $("#dialog-telemetry").dialog({
				autoOpen : false,
				height : 300,
				width : 450,
				modal : true,
				show: 'drop',
				buttons : {
					"Upload" : uploadTelemetry,
					Cancel : function() {
						dialog.dialog("close");
					}
				},
				close : function() {
					clean();
				}
			});
			$("#success-alert").hide();		
			$(".addTelemetry").on("click", function(event) {
				dialog.dialog("open");			            
			});

		});



function telemetrySearch(){
	var type = $("#typeTelemetry"),from= $("#begin-date"),to=$("#end-date"),target;
	target = '/datacenterframework/mission/search';

	var oMyForm = new FormData();
	oMyForm.append("type",type.val());
	oMyForm.append("from",from.val());
	oMyForm.append("to",to.val());
	$.ajax({
		url : target,
		data : oMyForm,
		dataType : 'text',
		processData : false,
		contentType : false,
		mimeType:"text/html; charset=UTF-8",
		type : 'POST',
		success : function(data){
			data = typeof data == 'string' ? JSON.parse(data) : data;			
			var r = data.data[0].columns[0].key;
			var v = data.data[0].columns[0].value;
		}
	});
}

function telemetrySuccess(){
	$("#dialog-form").dialog("close");
	$("#success-alert").show();
	window.setTimeout(function () { 
		$("#success-alert").fadeTo(2000, 50).slideUp(500, function(){
			$("#success-alert").slideUp(500);
		});
	}, 500);   

}

function uploadTelemetry() {
	alert('uploading');
	var type = $("#file-type"),mission= $("#mission-code"),target;
	target = '/datacenterframework/mission/telemetry';
	
	var oMyForm = new FormData();
	oMyForm.append("type",type.val());
	oMyForm.append("missionCode",mission);
	oMyForm.append("file", file2.files[0]);

	$.ajax({
		url : target,
		data : oMyForm,
		dataType : 'text',
		processData : false,
		contentType : false,
		mimeType:"text/html; charset=UTF-8",
		type : 'POST',
		success : telemetrySuccess()
	});
}

function clean(){
	$("#file-type").val("");		
	$("#file-upload-div input").remove();
	$("#file-upload-div").html( '<input name="file2" id="file2" type="file" />');
}