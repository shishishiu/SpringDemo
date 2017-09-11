function buscar(){
	document.getElementById("buscarForm").submit();
}

function deleteUser(loginid){
	
	document.getElementById("buscarForm").action="/admin/usuario/delete/"+loginid;
	document.getElementById("buscarForm").submit();
	
}
function dl(){
	document.getElementById("buscarForm").action="/admin/usuario/dl";
	document.getElementById("buscarForm").submit();
}

