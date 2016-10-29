var RTR = function () {
	RTR_this = this;
	this.Song = [];
	
	this.System = {
		Load: function (OnLoad) {
			var Getter = new XMLHttpRequest();
				Getter.open("GET", "https://api.github.com/", true);
				
				Getter.onload = function () {
					RTR_this.Songs = null;
					OnLoad();
				}
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

var Base = new RPGHelper();

function Init() {
	with (Base) {
		Menu.MenuPanel(["36", "79"]);
	}
}