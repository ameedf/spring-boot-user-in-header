const app = {
	userName : null,
	baseURL : 'rest/api'
};

function testLogin() {
	const details = {
		userName : element("user").value,
		password : element("password").value
	};

	const xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState === 4) {
			if (this.status === 200) {
				var user = JSON.parse(this.responseText);
				log(user);
				app.userName = user.userName;
			} else {
				alert("Error in login process: " + this.responseText)
			}
		}
	};
	xhttp.open("POST", app.baseURL + "/login", true);
	xhttp.setRequestHeader("Content-type", "application/json");
	xhttp.send(JSON.stringify(details));
}

function testLogout() {
	const xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState === 4) {
			log("logout: " + this.responseText);
			app.userName = null;
		}
	};
	xhttp.open("GET", app.baseURL + "/logout", true);
	xhttp.setRequestHeader("Content-type", "application/json");
	xhttp.setRequestHeader("Authorization", app.userName);
	xhttp.send();
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
	xhttp.setRequestHeader("Authorization", app.userName);
	xhttp.send();
}

function element(elementId) {
	return document.getElementById(elementId);
}

function log(item) {
	console.log(item);
}
