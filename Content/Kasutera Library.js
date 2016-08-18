function Update() {
	var Token = "";
	
	while (true) {
		var Reader = new XMLHttpRequest();
			Reader.open("GET", "https://www.googleapis.com/plus/v1/people/109100500854083629580/activities/public?key=AIzaSyBIYF6mrAIJpngIQAxqARVCqhqxQFq7qXc&maxResults=100&pageToken=" + Token, false);
			Reader.send(null);
			
		var Data = JSON.parse(Reader.responseText);
		
		document.getElementById("Contents").textContent += JSON.stringify(Data, null, "\t") + "\n\n";
		
		Token = Data.nextPageToken;
		
		if (Token == undefined) {
			break;
		}
	}
}
