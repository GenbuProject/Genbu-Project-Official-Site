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
						var SongGetter;
						
						SongGetter[i] = new XMLHttpRequest();
							SongGetter[i].responseType = "json";
							SongGetter[i].open("GET", SongListGetter.response[i].url, true);
							
							SongGetter[i].onload = function () {
								RTR_this.Song[i] = JSON.parse(atob(SongGetter[i].response.content));
							}
							
							SongGetter[i].send(null);
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
		System.Load(function () {
			
		});
	}
}