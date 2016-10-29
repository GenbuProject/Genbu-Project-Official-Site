var RTR = function () {
	RTR_this = this;
	
	this.Song = [];
	
	this.System = {
		LoginToGithub: function () {
			var Request = location.search.substr(1).split("&");
			var Querys = {};
			
			for (var i = 0; i < Request.length; i++) {
				Querys[Request[i].split("=")[0].toUpperCase()] = Request[i].split("=")[1];
			}
			
			if (Querys.CODE) {
				var TokenGetter = new XMLHttpRequest();
					TokenGetter.open("POST", "https://github.com/login/oauth/access_token?client_id=9949bdc61a863614cd97&client_secret=14ded7dad578046486e41df9401824c70404ce0a&code=" + Querys.CODE, false);
					TokenGetter.send(null);
			}
		},
		
		Load: function (OnLoad) {
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
	with (new RTR()) {
		System.LoginToGithub();
	}
}