import { CriaRequest } from "./JS";

CriaRequest();

function submitLogin() {
    var xhr = CriaRequest();

    var form = document.getElementById("form-login");
    var user = document.getElementById("user").value;
    var pass = document.getElementById("password").value;

    var action = "---/SGD/PEG" + user;

    xhr.open(form.method, action, true);
    xhr.send();

    xhr.onreadystatechange = function() {
        if(xhr.readyState === 4 && xhr.status === 200) {
            json = JSON.parse(xhr.responseText);
            if (json.password === pass) {
                window.open("index.html");
            }
        }
    }
}