const app = {
	userId : null,
	baseURL : 'rest/api'
};

function doLogin() {

	const details = {
		userName : element("user").value,
		password : element("password").value
	};

	const xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState === 4 && this.status === 200) {
			log("User id:" + this.responseText);
			app.userId = this.responseText;
		}
	};
	xhttp.open("POST", app.baseURL + "/login", true);
	xhttp.setRequestHeader("Content-type", "application/json");
	xhttp.send(JSON.stringify(event));
}

function testHeader() {
	const xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState === 4) {
			if (this.status === 200) {
				alert("OK: " + this.responseText);
			} else {
				alert("FAILURE: " + this.responseText);
			}
		}
	};
	xhttp.open("GET", app.baseURL + "/action", true);
	xhttp.setRequestHeader("Content-type", "application/json");
	xhttp.setRequestHeader("Authorization", app.userId);
	xhttp.send();
}

function element(elementId) {
	return document.getElementById(elementId);
}

function log(item) {
	console.log(item);
}
