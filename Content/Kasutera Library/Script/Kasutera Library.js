//珠照：109100500854083629580
//プログラマーGenboo：106666684430101995501

var Library = [];

function Update() {
	var Token = "";
	
	while (true) {
		var Reader = new XMLHttpRequest();
			Reader.open("GET", "https://www.googleapis.com/plus/v1/people/106666684430101995501/activities/public?key=AIzaSyAmcraDAZp5kah2o3oCnH8HCnhUVbQwZ2g&maxResults=100&pageToken=" + Token, false);
			Reader.send(null);
			
		var Data = JSON.parse(Reader.responseText);
		
		for (var i = 0; i < Data.items.length; i++) {
			if (Data.items[i].title.match("#珠照語録更新") != -1) {
				var Mem = Data.items[i].title.replace("#珠照語録更新 \n", "");
				
				Library.push({
					Reading: Mem.substring(0, Mem.match(/[\n↵]+/).index),
					Word: Mem.substring(Mem.match(/[\n↵]+/).index).replace(/[\n↵]+/, "")
				});
			}
		}
		document.getElementById("Contents").textContent += JSON.stringify(Data, null, "\t") + "\n\n";
		
		Token = Data.nextPageToken;
		
		if (Token == undefined) {
			break;
		}
	}
}
