

function loginLink(){
	
	let jwt="";
	jwt=getCookie("authentication");
//	alert(jwt)
	if (jwt!="" ){
		postRequestWithJWT();
	}
	else{
		getRequest();
	}
}

function postRequestWithJWT(){

    lp2('https://localhost/dashboard');
}

function getRequest(){
	
    return $.ajax({
		url:'https://localhost/login',
		type:"post",
		success: function(data){
			//window.location.href = "https://localhost/login"
			document.body.innerHTML=data;           
    		   }
    	}
    );
	 
}

function loginProcess(){

	document.getElementById("log").innerText="input!!!";
		lp1('https://localhost/auth/signin');
		//lp2('https://localhost/dashboard');
		

}

function getCookie(cname) {
  let name = cname + "=";
  //let decodedCookie = decodeURIComponent(document.cookie);
  let decodedCookie =document.cookie;
  let ca = decodedCookie.split(';');
  for(let i = 0; i <ca.length; i++) {
    let c = ca[i];
    while (c.charAt(0) == ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}

function setCookie(cname, cvalue, exdays) {
  const d = new Date();
  d.setTime(d.getTime() + (exdays*24*60*60*1000));
  let expires = "expires="+ d.toUTCString();
  //let expires = "expires=" + date.toGMTString();
  document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function removeCookie(name) {
    setCookie(name, "", -1);
}

function lp1(url){
	document.getElementById("log").innerText=JSON.stringify({AuthRequest:{email:document.getElementById("email").value,password:document.getElementById("password").value}});
	return $.ajax({
			url:url,
			type:'POST',
			headers:{'content-type':'application/json'},
			data:JSON.stringify({email:document.getElementById("email").value,password:document.getElementById("password").value}),
			success: function(response){
				
				//let obj=JSON.parse(response);
				let jwt=response.accessToken;	
				removeCookie("authentication");			
				setCookie("authentication",jwt,1);
				lp2('https://localhost/dashboard');
				},
			error:
				function(data){
					removeCookie("authentication");	
					document.getElementById("log").innerText+="Fail!!!";
				}	
		}
	);	
}

function lp2(url){
	let jwt=getCookie("authentication");
	return $.ajax({  
	    		 url: url,
	    		 type: 'POST',
	    		 //contentType: 'application/json',
	    		 headers: {'content-type':'application/text','Authorization':'Bearer '+jwt },
	    		 success: 
	    		 	function(data) {  
	    		    	document.body.innerHTML=data;             
	    		    },
	    		 error:
	    		 	function(data) {
	    		 		removeCookie("authentication");
	    		 	}
	    		 }        
	    		   
	    		 ); 
}
