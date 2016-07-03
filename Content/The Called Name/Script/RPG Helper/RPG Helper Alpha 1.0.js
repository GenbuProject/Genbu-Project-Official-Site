var RPGHelper = function (ID) {
	this.Canvas = document.getElementsByTagName("RPGHelper-Main")[ID];
	
	var Style = document.createElement("Link");
		document.head.appendChild(Style);
		Style.setAttribute("Rel", "StyleSheet");
		Style.setAttribute("HREF", "Script/RPG Helper/RPG Helper Alpha 1.0.css");
		
	this.R = {
		SPEED: {
			SLOW: 100,
			NORMAL: 80,
			FAST: 50
		}
	}
	
	this.MsgBox = {
		Canvas: this.Canvas,
		
		TypeA: function (Content, Speed) {
			var Dialog = document.createElement("RPGHelper-Dialog");
				this.Canvas.appendChild(Dialog);
				
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
	}
}
