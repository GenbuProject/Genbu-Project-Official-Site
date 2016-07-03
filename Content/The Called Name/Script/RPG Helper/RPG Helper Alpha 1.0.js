var RPGHelper = function (ID) {
	this.Canvas = document.getElementsByTagName("RPGHelper-Main")[ID];
		Canvas.style.width = Canvas.attributes["Width"].value;
		Canvas.style.Height = Canvas.attributes["Height"].value;
	
	var Style = document.createElement("Link");
		document.head.appendChild(Style);
		Style.setAttribute("Rel", "StyleSheet");
		Style.setAttribute("HREF", "Script/RPG Helper/RPG Helper Alpha 1.0.css");
		
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
	
	this.MsgBox = {
		Canvas: this.Canvas,
		
		TypeA: function (Pos, Content, Speed) {
			var Dialog = document.createElement("RPGHelper-Dialog");
				Dialog.style.height = this.Canvas.style.height / 4
				this.Canvas.appendChild(Dialog);
				
				switch (Pos) {
					case 0x0001:
						break;
						
					case 0x0002:
						Dialog.style.position = "absolute";
						Dialog.style.top = this.Canvas.style.height - (this.Canvas.style.height / 4);
						break;
						
					case 0x0003:
						Dialog.style.position = "absolute";
						Dialog.style.top = (this.Canvas.style.height / 2) - (this.Canvas.style.height / 8);
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
	}
}
