var RPGHelper = function () {
	this.Canvas = document.getElementById("RPGHelper-Main");
		this.Canvas.style.width = this.Canvas.attributes["width"].value + "px";
		this.Canvas.style.height = this.Canvas.attributes["height"].value + "px";
		this.Canvas.style.position = "Relative";
		
	var Counter = 0;
	
	for (var i = 0; i < document.head.children.length; i++) {
		if (document.head.children[i].tagName == "LINK") {
			if (document.head.children[i].attributes["href"].value == "Script/RPG Helper/RPG Helper Alpha 1.0.css") {
				Counter++;
			}
		}
	}
	
	if (Counter == 0) {
		var Style = document.createElement("Link");
			document.head.appendChild(Style);
			Style.setAttribute("Rel", "StyleSheet");
			Style.setAttribute("HREF", "Script/RPG Helper/RPG Helper Alpha 1.0.css");
	}
	
	this.R = {
		SPEED: {
			SLOW: 100,
			NORMAL: 80,
			FAST: 50
		},
		
		POS: {
			TOP: 0x0001,
			BOTTOM: 0x0002,
			CENTER: 0x0003
		}
	}
	
	this.MsgBox = function (Pos, Content, Speed) {
		var Dialog = document.createElement("RPGHelper-MsgBox");
			Dialog.style.width = (this.Canvas.attributes["width"].value - 10) + "px";
			this.Canvas.appendChild(Dialog);
		
		switch (Pos) {
			case 0x0001:
				Dialog.style.position = "Absolute";
				Dialog.style.top = "0px";
				Dialog.style.height = (this.Canvas.style.height.split("px")[0] / 4 - 5) + "px";
				break;
				
			case 0x0002:
				Dialog.style.position = "Absolute";
				Dialog.style.top = (this.Canvas.style.height.split("px")[0] - (this.Canvas.style.height.split("px")[0] / 4)) + "px";
				Dialog.style.height = (this.Canvas.style.height.split("px")[0] / 4 - 10) + "px";
				break;
				
			case 0x0003:
				Dialog.style.position = "Absolute";
				Dialog.style.top = ((this.Canvas.style.height.split("px")[0] / 2) - (this.Canvas.style.height.split("px")[0] / 4)) + "px";
				Dialog.style.height = (this.Canvas.style.height.split("px")[0] / 2 - 5) + "px";
				break;
		}
		
		if (typeof Content == "string") {
			var Counter = 0;
			
			var Timer = setInterval((function (Dialog, Counter) {
				return function () {
					if (Counter <= Content.length) {
						Dialog.textContent = Content.substr(0, Counter);
						Counter++;
					} else {
						clearInterval(Timer);
					}
				}
			})(Dialog, Counter), Speed);
		}
		
		Dialog.onclick = (function (Canvas, Dialog) {
			return function () {
				Canvas.removeChild(Dialog);
			}
		})(this.Canvas, Dialog);
	}
	
	this.Menu = {
		Canvas: this.Canvas,
		
		MenuPanel: function (Pos, Amount) {
			var Dialog = document.createElement("RPGHelper-Menu-MenuPanel");
				
		}
	}
}
