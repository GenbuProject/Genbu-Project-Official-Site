//key=AIzaSyBMB3VAvofs_51Nu3c9_7FsxQLvlqGkUmc
function Search(ID, Amount) {
	var Memory;
	var Result;
	
	for (var i = 0; i < Amount; i++) {
		var Token = "";
		
		if (i != 0) {
			Token = Memory.nextPageToken;
		}
		
		var X = new XMLHttpRequest();
			X.open("GET", "https://www.googleapis.com/plus/v1/people/" + ID + "/activities/public?key=AIzaSyBIYF6mrAIJpngIQAxqARVCqhqxQFq7qXc&maxResults=100&pageToken=" + Token, false);
			X.send();
			
		Memory = JSON.parse(X.responseText);
		
		if (Memory.nextPageToken == undefined) {
			Result = Memory;
			break;
		}
	}
	
	document.getELementById("Date").textContent = Result.items[Result.items.length - 1].published;
	document.getELementById("Link").textContent = Result.items[Result.items.length - 1].url;
	
	console.log("Finished.");
	alert("Finished.");
}
