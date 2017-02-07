$(function(){
	var dialog,name = $("#name"), description= $("#description"), file = $("#file"), allFields = $(
			[]).add(name).add(description).add(file);
	dialog = $("#dialog-form").dialog({
		autoOpen : false,
		height : 400,
		width : 450,
		modal : true,
		show: 'drop',
		buttons : {
			"Adicionar" : addArtefact,
			Cancel : function() {
				dialog.dialog("close");
			}
		},
		close : function() {
			clean();

		}
	});

	function addArtefact(){
		var level = $("#dialog-level").val();
		var type = $("#dialog-type").val();			
		uploadFormData(level,type);

	}

	$(".addFile").button().on("click", function(event) {
		var level = $(this).attr('id');		
		$("#dialog-level").val(level);
		$("#dialog-type").val('others');
		dialog.dialog("open");
	});


	$(".addFile4Answer").on("click", function(event) {
		var level = $(this).attr('id');		
		$("#dialog-level").val(level);
		$("#dialog-type").val('answer');
		dialog.dialog("open");
	});

	$(".answering").click(function(e) {
		el = $(this);
		var divAnswer = $(this).closest('[data-answerid]');
		var answerValue = divAnswer.data('value');
		var answerId = divAnswer.data('answerid');
		switch (answerValue) {
		case 0:
			divAnswer.data('value',60);
			break;
		case 60:
			divAnswer.data('value',80);
			break;
		case 80:
			divAnswer.data('value',0);
			break;
		}
		var ob = new FormData();
		ob.append("answer", answerId);
		ob.append("value", divAnswer.data('value'));
		$.ajax({
			url : '/datacenterframework/assessment/answer',
			data : ob,
			dataType : 'text',
			processData : false,
			contentType : false,
			mimeType:"text/html; charset=UTF-8",
			type : 'POST',
			success : function(data) {
				el.prop('src','/datacenterframework/resources/icons/'.concat(data));
			}
		});


	});



});


function clean(){
	$("#name").val("");		
	$("#description").val("");
	$("#file-upload-div input").remove();
	$("#file-upload-div").html( '<input name="file2" id="file2" type="file" />');

	$("#dialog-level").val("");
}

$(function() {
	$("#tabs").tabs();
});




function removeArtefact(el){
	var line = $(el).closest('[artefactline]') ;
	var id = line.data('idartefact');

	var ob = new FormData();
	ob.append("code", id);
	$.ajax({
		url : '/datacenterframework/assessment/removeArtefact',
		data : ob,
		dataType : 'text',
		processData : false,
		contentType : false,
		mimeType:"text/html; charset=UTF-8",
		type : 'POST',
		success : function(data) {
			line.remove();
		}
	});


}




function uploadFormData(level,type) {
	var name = $("#name"), description= $("#description"), allFields = $(
			[]).add(name).add(description), target;

	target = '/datacenterframework/assessment/artefact';

	var oMyForm = new FormData();
	oMyForm.append("type",type);
	oMyForm.append("id", level);
	oMyForm.append("name", name.val());
	oMyForm.append("description", description.val());
	oMyForm.append("file", file2.files[0]);

	$.ajax({
		url : target,
		data : oMyForm,
		dataType : 'text',
		processData : false,
		contentType : false,
		mimeType:"text/html; charset=UTF-8",
		type : 'POST',
		success : function(data) {
			callbackHandleData(data,type,level);
		}
	});
}


function formatMax(text,limit){
	if(text.lengh > limit)
		return text.substring(0,limit).concat('...');
	return text;
}




function callbackHandleData(response,type,level){
	var name = $("#name"),		
	description = $("#description");
	var text , param;
	param = response.split(',');

	if(type == 'answer'){
		text = '<span  class="tag label label-primary" style="padding:5px 15px;" artefactline="true" data-idartefact="'.concat(param[3]).concat('">')
		.concat('<a href="../').concat(param[1]).concat('" download="').concat(name.val()).concat('.').concat(param[2]).concat('" style="text-decoration: none">')			
		.concat('<span style="color: #fff;">').concat(name.val()).concat('</span></a>')
		.concat('<a><i class="remove glyphicon glyphicon-remove-sign glyphicon-white" style="width:2px;"></i></a>')
		.concat('</span>');
		$('#artefactsArea'.concat(level)).append(text);
	}else{
		text = '<tr class="item-lista" artefactline="true" data-idartefact="'.concat(param[3]).concat('">')
		.concat('<td>').concat(formatMax(name.val(),50)).concat('</td>')
		.concat('<td>').concat(formatMax(description.val(),50)).concat('</td>')
		.concat('<td>').concat(param[0]).concat('</td>')
		.concat('<td style="width: 35px"> <a href="/datacenterframework/').concat(param[1]).concat('" download="').concat(name.val()).concat('.').concat(param[2]).concat('" style="text-decoration: none"> <span class="glyphicon glyphicon-save" aria-hidden="true"></span></a></td>')
		.concat('<td style="width: 35px"> <a href="#" class="deletelink" onclick="removeArtefact(this)" style="text-decoration: none"> <span class="glyphicon glyphicon-remove" aria-hidden="true" ></span> </a></td>')
		.concat('</tr>');

		$('#artefactsTable'.concat(level)).append(text);
	}
	$("#dialog-form").dialog("close");

}