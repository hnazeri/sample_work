
function exitDashboard(){
//	alert("exit clicked!");
	removeCookie("authentication");
	window.location.href="https://localhost/";
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
