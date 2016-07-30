//?key=AIzaSyBMB3VAvofs_51Nu3c9_7FsxQLvlqGkUmc
//100110299413815947503
function Search(ID, Amount) {
	var Memory;
	
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
			break;
		}
	}
	
	console.log("Finished.");
	alert("Finished.");
}