//アクセストークン：atob("YWUzY2I0YTU0ZDdkMTJiMDMzODRiODk2YThiOWZlZGZhMGIwMTZiMw==")
var Util = {
	CreateDialog: function (Title, Content, FooterContent) {
		var DialogBack = document.createElement("Div");
			DialogBack.className = "DialogBack";
			
		var DialogFront = document.createElement("Div");
			DialogFront.className = "DialogFront";
			
		var DialogName = document.createElement("Name");
			DialogName.textContent = Title;
			
		var DialogSeparater = document.createElement("Hr");
		
		var DialogContent = document.createElement("Content");
			DialogContent.innerHTML = Content;
			
		var DialogFooter = document.createElement("Footer");
		
		document.body.appendChild(DialogBack);
		document.body.appendChild(DialogFront);
		
		DialogFront.appendChild(DialogName);
		DialogFront.appendChild(DialogSeparater);
		DialogFront.appendChild(DialogContent);
		DialogFront.appendChild(DialogFooter);
		
		DialogFooter.innerHTML = FooterContent;
		
		DialogFront.style.top = (document.documentElement.clientHeight - DialogFront.clientHeight) / 2 + "px";
		DialogFront.style.left = (document.documentElement.clientWidth - DialogFront.clientWidth) / 2 + "px";
	}
}

var Net = {
	LoginWithGoogle: function () {
		window.open("https://accounts.google.com/o/oauth2/v2/auth?redirect_uri=https://genbuproject.github.io/Genbu-Project-Official-Site/Content/RhythmTapRide/Content/Uploader/&scope=https://www.googleapis.com/auth/plus.login+https://www.googleapis.com/auth/plus.me&response_type=code&client_id=568561761665-fgnn7jvnf1rt5pb8r275o8uagkjfusjf.apps.googleusercontent.com&key=AIzaSyDdyecB-0e1qMwYDd46w4p5Iki-TVf3_HM&access_type=offline&approval_prompt=force", "_blank");
	}
}