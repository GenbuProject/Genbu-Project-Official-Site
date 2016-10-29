var RTR = function () {
	RTR_this = this;
	this.Song = [];
	
	this.System = {
		Load: function (OnLoad) {
			var TokenGetter = new XMLHttpRequest();
				TokenGetter.open("GET", "https://github.com/login/oauth/access_token?" + location.search.split("?")[0] + "&client_id=9949bdc61a863614cd97&client_secret=8ee6a6c35d9e544060516035f06f670323d00912", false);
				TokenGetter.send(null);
				
			var SongGetter = new XMLHttpRequest();
				SongGetter.open("GET", "", true);
				
				SongGetter.onload = function () {
					RTR_this.Songs = null;
					OnLoad();
				}
				
				SongGetter.send(null);
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
	
}