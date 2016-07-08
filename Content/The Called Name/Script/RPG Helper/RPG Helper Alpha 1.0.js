var RPGHelper = function () {
	this.Canvas = document.getElementById("RPGHelper-Main");
		this.Canvas.style.width = this.Canvas.attributes["width"].value + "px";
		this.Canvas.style.height = this.Canvas.attributes["height"].value + "px";
		this.Canvas.style.position = "Relative";
		
	this.BGM = new Audio("Script/RPG Helper/null.wav");
		this.BGM.loop = true;
		
	this.SE = new Audio("Script/RPG Helper/null.wav");
		this.SE.loop = false;
		
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
	
	/*/
	 *##################################################
	 *#Pos : R.POS型
	 *#Content : String型
	 *#Speed : R.SPEED型
	 *##################################################
	/*/
	this.MsgBox = function (Pos, Content, Speed) {
		var Dialog = document.createElement("RPGHelper-MsgBox");
			Dialog.style.position = "Absolute";
			Dialog.style.width = (this.Canvas.attributes["width"].value - 10) + "px";
			this.Canvas.appendChild(Dialog);
			
		switch (Pos) {
			case 0x0001:
				Dialog.style.top = "0px";
				Dialog.style.height = (this.Canvas.style.height.split("px")[0] / 4 - 5) + "px"; //【縦横500pxの時】500px / 4 - 5px[ボーダー幅] = 120px
				break;
				
			case 0x0002:
				Dialog.style.top = (this.Canvas.style.height.split("px")[0] - (this.Canvas.style.height.split("px")[0] / 4)) + "px"; //【縦横500pxの時】500px - (500px / 4) = 500px - 125px = 375px
				Dialog.style.height = (this.Canvas.style.height.split("px")[0] / 4 - 10) + "px"; //【縦横500pxの時】500px / 4 - 10px[ボーダー幅 * 2] = 115px
				break;
				
			case 0x0003:
				Dialog.style.top = ((this.Canvas.style.height.split("px")[0] / 2) - (this.Canvas.style.height.split("px")[0] / 4)) + "px"; //【縦横500pxの時】(500px / 2) - (500px - 2) = 250px - 125px = 125px
				Dialog.style.height = (this.Canvas.style.height.split("px")[0] / 2 - 5) + "px"; //【縦横500pxの時】500px / 2 - 5 = 245px
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
		
		/*/
		 *##################################################
		 *#Size : Array型
		 *#|=> [0] : int型(00 ～ 99)
		 *#|=> [1] : int型(00 ～ 99)
		 *##################################################
		/*/
		MenuPanel: function (Size) {
			var Dialog = document.createElement("RPGHelper-Menu-MenuPanel");
				Dialog.style.position = "Absolute";
				Dialog.style.width = (this.Canvas.style.width.split("px")[0] / 10) * (Math.max(Size[0].substr(1, 1), Size[1].substr(1, 1)) - Math.min(Size[0].substr(1, 1), Size[1].substr(1, 1))) + "px";
				Dialog.style.height = (this.Canvas.style.height.split("px")[0] / 10) * (Math.max(Size[0].substr(0, 1), Size[1].substr(0, 1)) - Math.min(Size[0].substr(0, 1), Size[1].substr(0, 1))) + "px";
				Dialog.style.top = (this.Canvas.style.height.split("px")[0] / 10) * (Math.max(Size[0].substr(0, 1), Size[1].substr(0, 1)) - Math.min(Size[0].substr(0, 1), Size[1].substr(0, 1))) + "px";
				
				this.Canvas.appendChild(Dialog);
		}
	}
	
	this.Sound = {
		BGM: this.BGM,
		SE: this.SE,
		
		/*/
		 *##################################################
		 *#URL : String型
		 *#Volume : double型
		 *##################################################
		/*/
		PlayBGM: function (URL, Volume) {
			this.BGM.src = URL;
			this.BGM.volume = Volume;
			this.BGM.play();
		},
		
		/*/
		 *##################################################
		 *#URL : String型
		 *#Volume : double型
		 *##################################################
		/*/
		PlaySE: function (URL, Volume) {
			this.SE.src = URL;
			this.SE.volume = Volume;
			this.SE.play();
		},
		
		StopBGM: function () {
			this.SE.pause();
			this.SE.currentTime = 0;
			this.SE.src = "Script/RPG Helper/null.wav";
			this.SE.volume = 1;
		}
	}
}
