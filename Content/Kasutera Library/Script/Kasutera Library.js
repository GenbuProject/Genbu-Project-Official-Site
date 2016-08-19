//珠照：109100500854083629580
//プログラマーGenboo：106666684430101995501

var Library = [];

function Update() {
	var Token = "";
	Library = [];
	
	document.getElementById("Accept").disabled = "disabled";
	
	var Dialog = document.createElement("Dialog");
		Dialog.style.left = (window.innerWidth / 4) + "px";
		Dialog.style.top = (window.innerHeight / 4) + "px";
		Dialog.style.width = (window.innerWidth / 2) + "px";
		Dialog.style.height = (window.innerHeight / 2) + "px";
		
		document.body.appendChild(Dialog);
		
	while (true) {
		var Reader = new XMLHttpRequest();
			Reader.open("GET", "https://www.googleapis.com/plus/v1/people/106666684430101995501/activities/public?key=AIzaSyAmcraDAZp5kah2o3oCnH8HCnhUVbQwZ2g&maxResults=100&pageToken=" + Token, false);
			Reader.send(null);
			
		var Data = JSON.parse(Reader.responseText);
		
		for (var i = 0; i < Data.items.length; i++) {
			if (Data.items[i].title.split(/#珠照語録更新 [\n↵]{1}/)[1] != undefined) {
				var Mem = Data.items[i].title.split(/#珠照語録更新 [\n↵]{1}/)[1];
				
				Library.push({
					Reading: Mem.split(/[\n↵]{2}/)[0],
					Word: Mem.split(/[\n↵]{2}/)[1]
				});
			}
		}
		
		Token = Data.nextPageToken;
		
		if (Token == undefined) {
			document.getElementById("Contents").textContent = JSON.stringify(Library, null, "\t");
			document.getElementById("Accept").disabled = null;
			
			Dialog.parentElement.removeChild(Dialog);
			
			break;
		}
	}
}
