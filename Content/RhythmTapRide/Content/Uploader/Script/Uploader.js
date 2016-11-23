//アクセストークン：atob("YWUzY2I0YTU0ZDdkMTJiMDMzODRiODk2YThiOWZlZGZhMGIwMTZiMw==")
//取得URL：https://www.googleapis.com/plus/v1/people/me
var Token = "";

const ID = "568561761665-fgnn7jvnf1rt5pb8r275o8uagkjfusjf.apps.googleusercontent.com";
const SecretID = atob("Z05EREFjdDdYQXZHMW9iR3Y1NFZtanRu");

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
	},
	
	DismissDialog: function () {
		document.getElementsByClassName("DialogBack")[0].parentElement.removeChild(document.getElementsByClassName("DialogBack")[0]);
		document.getElementsByClassName("DialogFront")[0].parentElement.removeChild(document.getElementsByClassName("DialogFront")[0]);
	},
	
	QuerySort: function () {
		var Querys = {};
		
		for (var i = 0; i < location.search.substr(1).split("&").length; i++) {
			Querys[location.search.substr(1).split("&")[i].split("=")[0].toUpperCase()] = location.search.substr(1).split("&")[i].split("=")[1];
		}
		
		return Querys;
	}
}

var Net = {
	LoginWithGoogle: function () {
		location.href = "https://accounts.google.com/o/oauth2/v2/auth?redirect_uri=https://genbuproject.github.io/Genbu-Project-Official-Site/Content/RhythmTapRide/Content/Uploader/&scope=https://www.googleapis.com/auth/plus.login+https://www.googleapis.com/auth/plus.me&response_type=code&client_id=" + ID + "&key=AIzaSyDdyecB-0e1qMwYDd46w4p5Iki-TVf3_HM&access_type=offline&approval_prompt=force";
	}
}

function Init() {
	var Query = Util.QuerySort();
	
	if (!Query.CODE) {
		Util.CreateDialog("Googleにログインして下さい", "Rhythm Tap Ride(RTR)のアップローダーをご利用頂くため<Br>Googleにログインして下さい。", "<Button OnClick = 'Net.LoginWithGoogle();'>Sign in with Google+</Button><Button OnClick = 'Util.DismissDialog();'>キャンセル</Button>");
	} else {
		var Getter = new XMLHttpRequest();
			Getter.open("POST", "https://www.googleapis.com/oauth2/v4/token?client_id=" + ID + "&client_secret=" + SecretID + "&redirect_uri=https://genbuproject.github.io/Genbu-Project-Official-Site/Content/RhythmTapRide/Content/Uploader/&access_type=offline&grant_type=authorization_code&code=" + Query.CODE, true);
			
			Getter.onload = function () {
				Token = JSON.parse(Getter.response).access_token;
				
				Util.CreateDialog("ログイン成功", "Googleアカウントのログインに成功しました。", "<Button OnClick = 'Util.DismissDialog();'>閉じる</Button>");
			}
			
			Getter.send(null);
	}
}

document.addEventListener("resize", function () {
	if (document.getElementsByClassName("DialogBack")[0] && document.getElementsByClassName("DialogFront")[0]) {
		DialogFront.style.top = (document.documentElement.clientHeight - DialogFront.clientHeight) / 2 + "px";
		DialogFront.style.left = (document.documentElement.clientWidth - DialogFront.clientWidth) / 2 + "px";
	}
}
