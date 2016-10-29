//アクセストークン：ec4a5f7617aaa8d3ce0d26e94c4bd83909c3dc4c
var RTR = function () {
	RTR_this = this;
	
	this.Song = [];
	
	this.System = {
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
	}
}