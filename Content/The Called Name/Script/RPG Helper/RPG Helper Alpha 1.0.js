var RPGHelper = function (ID) {
	this.Canvas = document.getElementById(ID);
	
	var Style = document.createElement("Link");
		document.head.appendChild(Style);
		Style.setAttribute("Rel", "StyleSheet");
		Style.setAttribute("HREF", "Script/RPG Helper/RPG Helper Alpha 1.0.css");
		
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
			var Dialog = document.createElement("RPGHelper-Dialog");
				this.Canvas.appendChild(Dialog);
				
			if (typeof Content == "string") {
				Dialog.textContent = Content;
			}
			
			Dialog.onClick = function (Canvas, Dialog) {
				Canvas.removeChild(Dialog)
			}(this.Canvas, Dialog);
		}
	}
}
