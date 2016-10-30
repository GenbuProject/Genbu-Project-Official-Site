//アクセストークン：atob("YWUzY2I0YTU0ZDdkMTJiMDMzODRiODk2YThiOWZlZGZhMGIwMTZiMw==")
var RTR = function () {
	RTR_this = this;
	
	this.Song = [];
	
	this.System = {
		Load: function (OnLoad) {
			var SongListGetter = new XMLHttpRequest();
				SongListGetter.responseType = "json";
				SongListGetter.open("GET", "https://api.github.com/repos/GenbuProject/RhythmTapRide/contents/Songs?access_token=" + atob("YWUzY2I0YTU0ZDdkMTJiMDMzODRiODk2YThiOWZlZGZhMGIwMTZiMw=="), true);
				
				SongListGetter.onload = function () {
					var SongGetter = [];
					
					for (let i = 0; i < SongListGetter.response.length; i++) {
						SongGetter[i] = new XMLHttpRequest();
							SongGetter[i].responseType = "json";
							SongGetter[i].open("GET", SongListGetter.response[i].git_url + "?access_token=" + atob("YWUzY2I0YTU0ZDdkMTJiMDMzODRiODk2YThiOWZlZGZhMGIwMTZiMw=="), true);
							
							SongGetter[i].onload = function () {
								RTR_this.Song[i] = JSON.parse(atob(SongGetter[i].response.content));
								
								var Count = 0;
								
								for (var j = 0; j < SongListGetter.response.length; j++) {
									if (RTR_this.Song[j] != null) {
										Count++;
									}
								}
								
								if (Count == SongListGetter.response.length) {
									OnLoad();
								}
							}
							
							SongGetter[i].send(null);
					}
				}
				
				SongListGetter.send(null);
		}
	}
	
	this.Node = {
		Play: function () {
			var Selecter = document.createElement("Select");
			
			for (var i = 0; i < RTR_this.Song.length; i++) {
				var Item = new Option(RTR_this.Song[i].Name + " / " + RTR_this.Song[i].Author, RTR_this.Song[i].Name);
				Selecter.appendChild(Item);
			}
			
			var Accept = document.createElement("Button");
				Accept.textContent = "Let's Play!";
				
				Accept.onclick = function () {
					var StartMsg = document.createElement("Span");
						StartMsg.id = "Message";
						StartMsg.textContent = "Music Start";
						
					document.body.appendChild(StartMsg);
					
					document.getElementById("SongSelecter").parentElement.removeChild(document.getElementById("SongSelecter"));
					
					var Media = new Audio(RTR_this.Song[Selecter.selectedIndex].Music);
						Media.play();
				}
				
			document.getElementById("SongSelecter").appendChild(Selecter);
			document.getElementById("SongSelecter").appendChild(Accept);
		},
		
		SongMaker: function () {
			
		},
		
		Option: function () {
			
		}
	}
}

function Init() {
	with (new RTR()) {
		System.Load(function () {
			Node.Play();
		});
	}
}