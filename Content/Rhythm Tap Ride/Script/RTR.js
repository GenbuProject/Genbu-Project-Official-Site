//アクセストークン：ec4a5f7617aaa8d3ce0d26e94c4bd83909c3dc4c
var RTR = function () {
	RTR_this = this;
	
	this.Song = [];
	
	this.System = {
		Load: function (OnLoad) {
			var SongListGetter = new XMLHttpRequest();
				SongListGetter.responseType = "json";
				SongListGetter.open("GET", "https://api.github.com/repos/GenbuProject/RhythmTapRide/contents/Songs", true);
				
				SongListGetter.onload = function () {
					for (let i = 0; i < SongListGetter.response.length; i++) {
						var SongGetter = new XMLHttpRequest();
							SongGetter.responseType = "json";
							SongGetter.open("GET", SongListGetter.response[i].url, true);
							
							SongGetter.onload = function () {
								RTR_this.Song[i] = JSON.parse(atob(SongGetter.response.content));
							}
							
							SongGetter.send(null);
					}
					
					OnLoad();
				}
				
				SongListGetter.send(null);
		}
	}
	
	this.Node = {
		Play: function () {
			
		},
		
		SongMaker: function () {
			
		},
		
		Option: function () {
			
		}
	}
}

function Init() {
	with (new RTR()) {
		System.Load();
	}
}