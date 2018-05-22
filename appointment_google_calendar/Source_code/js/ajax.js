document.getElementById('available_dates').addEventListener('focusout',function(){
	var date = this.value;
	this.type = 'date';
	this.value = date;
    get_times(this);
    this.type = 'text';
    this.value = date;
});

document.getElementById('submit').addEventListener('click',function(){	
	let check = true;
	if(document.getElementById('name').value == ""){
		check = false;
	}
	else if(document.getElementById('email').value == ""){
		check = false;
	}
	else if(document.getElementById('available_dates').value == ""){
		check = false;
	}
	else if(document.getElementById('available_times').value == ""){
		check = false;
	}
	if(check){
		schedule_me(this);
		document.getElementById('name').value = "";
		document.getElementById('email').value = "";
		document.getElementById('available_dates').value = "";
		document.getElementById('available_times').value = "";
	}
});
    

function get_times(date_picker) {
    var date = date_picker.value;
    //console.log(date);
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("available_times").innerHTML = xhttp.responseText;
        }
    };
    xhttp.open('GET', "calendars.php?action=get_times&date="+date+"&t="+ Math.random());
    xhttp.setRequestHeader('X-Requested-With','xmlhttprequest');
    xhttp.send();
}

function schedule_me(btn){
	var date = document.getElementById('available_dates').value;
	var time = document.getElementById('available_times').value;
	var email = document.getElementById('email').value;
	var name = document.getElementById('name').value;

	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function(){
		if (this.readyState == 4 && this.status == 200) {
            document.getElementById("dump").innerHTML = xhttp.responseText;
        }
	};

	//more information pass by following,
	//ex. patient_name,patient_email
	//
	xhttp.open('GET', "calendars.php?action=schedule_me&date="+date+"&time="+time+"&email="+email+"&name="+name+"&t="+Math.random());
    xhttp.setRequestHeader('X-Requested-With','xmlhttprequest');
    xhttp.send();
}