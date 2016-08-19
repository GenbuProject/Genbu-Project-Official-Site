//珠照：109100500854083629580
//プログラマーGenboo：106666684430101995501

Date.prototype.getToday = function (SplitWord) {
	var Day = new Date().toLocaleDateString().split("/");
	var Dates = [Day[0], Day[1], Day[2]];
	
	if (Dates[1].length == 1) Dates[1] = "0" + Day[1];
	if (Dates[2].length == 1) Dates[2] = "0" + Day[2];
	
	return Dates[0] + SplitWord + Dates[1] + SplitWord + Dates[2];
}

var Library = [];

function Update() {
	var Token = "";
	var LoadingCount = 0;
	
	Library = [];
	
	document.getElementById("Accept").disabled = "disabled";
	
	var Dialog = document.createElement("Dialog");
		Dialog.style.position = "Fixed";
		Dialog.style.left = (window.innerWidth / 4) + "px";
		Dialog.style.top = (window.innerHeight / 4) + "px";
		Dialog.style.width = (window.innerWidth / 2) + "px";
		Dialog.style.height = (window.innerHeight / 2) + "px";
		
		document.body.appendChild(Dialog);
		
	var Timer = setInterval(function () {
		var Content = "読み込み中...";
		
		Dialog.textContent += Content.substr(LoadingCount, 1);
		LoadingCount++;
		
		if (LoadingCount == Content.length - 1) {
			Dialog.textContent = "";
			LoadingCount = 0;
		}
	}, (1000 / 3));
	
	var Looper = setInterval(function () {
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
			
			clearInterval(Timer);
			Dialog.parentElement.removeChild(Dialog);
			
			var DL = "";
			
			for (var i = 0; i < Library.length; i++) {
				if (i == Library[i].length - 1) {
					DL += Library[i].Reading + "\t" + Library[i].Word + "\t" + "顔文字" + "\t";
				} else {
					DL += Library[i].Reading + "\t" + Library[i].Word + "\t" + "顔文字" + "\t" + "\n";
				}
			}
			
			var Data = new Blob([DL], {
				type: "Text/Plain"
			});
			
			if (window.navigator.msSaveBlob) {
				window.navigator.msSaveBlob(DL, "珠照語録 " + new Date().getToday("-") + ".txt");
			} else {
				Link = document.createElement("A");
				Link.href = URL.createObjectURL(Data);
				Link.download = "珠照語録 " + new Date().getToday("-") + ".txt";
				Link.target = "_blank";
				
				var Click = document.createEvent("MouseEvents");
					Click.initEvent("click", false, true);
					
				Link.dispatchEvent(Click);
				
				URL.revokeObjectURL(Data);
			}
			
			clearInterval(Looper);
		}
	}, 50);
}