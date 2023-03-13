function loginForm() {
	console.log('loginForm() CALLED!!');
	
	var form = document.login_form;
	
	if (form.t_id.value == ' ') {
		alert('INPUT YOUR ID');
		form.t_id.focus();
		
	} else if (form.t_pw.value == ' ') {
		alert('INPUT YOUR PW');
		form.t_pw.focus();
		
	} else {
		form.submit();
		
	}
	
}