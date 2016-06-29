var RPGHelper = function (ID) {
	this.Canvas = document.getElementById(ID);
	
	var Style = document.createElement("Link");
		document.head.appendChild(Style);
		Style.setAttribute("Rel", "StyleSheet");
		Style.setAttribute("HREF", "Script/RPG Helper/RPG Helper Alpha 1.0.js");
		
	this.R = {
		SPEED: {
			SLOW: 100,
			Normal: 80,
			Fast: 50
		}
	}
	
	this.MsgBox = {
		Canvas: this.Canvas,
		
		TypeA: function (Content, Speed) {
			if (this.Canvas.hasAttribute("class")) {
				this.Canvas.setAttribute("class", "RPGHelper-MsgBox-TypeA");
			} else {
				this.Canvas.attributes["class"].value = "RPGHelper-MsgBox-TypeA";
			}
			
			if (typeof Content == "string") {
				var i = 0;
				
				var Timer = setInterval(function () {
					if (i < Content.length) {
						this.Canvas.textContent = Content.substr(0, i);
						i++;
					} else {
						clearInterval(Timer);
					}
				}, Speed);
			}
		}
	}
}