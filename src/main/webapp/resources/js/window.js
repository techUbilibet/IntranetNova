/**
 * 
 */

function ObrirFinestra(target, Ample, Alt) {
	finestra=window.open(target,"_blank","left=300,top=300,width="+Ample+",height="+Alt+",toolbar=no,directories=no,scrollbars=yes,menubar=no,status=no"); 
	finestra.focus;
	return finestra;
}

function UsuariEditor(id) {
	var param="";
	if (id!=undefined)
		param="?id="+id;
	ObrirFinestra("usuariEditView.jsf"+param,800,600);
}
