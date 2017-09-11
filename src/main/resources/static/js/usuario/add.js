function dl(){
	var loginId = $("#loginId").val();
	document.getElementById("addForm").action="/admin/usuario/dl/" + loginId;
	document.getElementById("addForm").submit();
}
