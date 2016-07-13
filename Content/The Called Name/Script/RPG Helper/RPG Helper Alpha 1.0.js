var RPGHelper = function () {
	this.Canvas = document.getElementById("RPGHelper-Main");
		this.Canvas.style.width = this.Canvas.attributes["width"].value + "px";
		this.Canvas.style.height = this.Canvas.attributes["height"].value + "px";
		this.Canvas.style.position = "Relative";
		
	this.BGM = new Audio("Audio/null.wav");
		this.BGM.loop = true;
		
	this.SE = new Audio("Audio/null.wav");
		this.SE.loop = false;
		
	/*/
	 *##################################################
	 *#>>R<<
	 *#レイアウトシステム定数
	 *##################################################
	/*/
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
	 *#>>Resource<<
	 *#RPGのセーブデータの情報
	 *##################################################
	/*/
	Resource = {
		
	}
	
	/*/
	 *##################################################
	 *#>>Save<<
	 *#RPGのセーブデータを保存する
	 *##################################################
	/*/
	this.Save = function (FileName) {
		var Data = new Blob([JSON.stringify(Resource, null, "\t")], {
			type: "Text/Plain"
		});
		
		if (window.navigator.msSaveBlob) {
			window.navigator.msSaveBlob(Data, FileName);
		} else {
			Link = document.createElement("A");
			Link.href = URL.createObjectURL(Data);
			Link.download = FileName;
			Link.target = "_blank";
			
			var Click = document.createEvent("MouseEvents");
				Click.initEvent("click", false, true);
				
			Link.dispatchEvent(Click);
			
			URL.revokeObjectURL(Data);
		}
	}
	
	/*/
	 *##################################################
	 *#>>Load<<
	 *#RPGのセーブデータを読み込む
	 *#
	 *#>>引数<<
	 *#LoadFuc : Function型
	 *##################################################
	/*/
	this.Load = function (LoadFuc) {
		var Reader = new FileReader();
		
		var Filer = document.createElement("Input");
			Filer.type = "File";
			
			Filer.addEventListener("change", function (Event) {
				Reader.readAsText(Event.target.files[0]);
				
				Reader.onloadend = function () {
					Resource = JSON.parse(Reader.result);
					LoadFuc();
				}
			});
			
			Filer.click();
	}
	
	/*/
	 *##################################################
	 *#>>MsgBox<<
	 *#メッセージダイアログを表示
	 *#
	 *#>>引数<<
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
		
		return Dialog;
	}
	
	this.Menu = {
		Canvas: this.Canvas,
		
		/*/
		 *##################################################
		 *#>>MenuPanel<<
		 *#メニュー画面を表示
		 *#
		 *#>>引数<<
		 *#Size : Array型
		 *#|=> [0] : String型(00 ～ 99, ^^)
		 *#|=> [1] : String型(00 ～ 99, ^^)
		 *##################################################
		/*/
		MenuPanel: function (Size) {
			var Dialog = document.createElement("RPGHelper-Menu-MenuPanel");
				Dialog.style.position = "Absolute";
				
				if (Size[0].substr(0, 1) != "^" && Size[1].substr(0, 1) != "^" && Size[0].substr(1, 1) != "^" && Size[1].substr(1, 1) != "^") {
					Dialog.style.width = (this.Canvas.style.width.split("px")[0] / 10) * (Math.max(Size[0].substr(0, 1), Size[1].substr(0, 1)) - Math.min(Size[0].substr(0, 1), Size[1].substr(0, 1))) - 5 + "px";
					Dialog.style.height = (this.Canvas.style.height.split("px")[0] / 10) * (Math.max(Size[0].substr(1, 1), Size[1].substr(1, 1)) - Math.min(Size[0].substr(1, 1), Size[1].substr(1, 1))) - 5 + "px";
					Dialog.style.top = (this.Canvas.style.height.split("px")[0] / 10) * (Math.min(Size[0].substr(1, 1), Size[1].substr(1, 1))) + "px";
					Dialog.style.left = (this.Canvas.style.width.split("px")[0] / 10) * (Math.min(Size[0].substr(0, 1), Size[1].substr(0, 1))) + "px";
				} else if (Size[1].substr(0, 1) != "^" && Size[1].substr(1, 1) == "^") {
					Dialog.style.width = (this.Canvas.style.width.split("px")[0] / 10) * (Math.max(Size[0].substr(0, 1), Size[1].substr(0, 1)) - Math.min(Size[0].substr(0, 1), Size[1].substr(0, 1))) - 5 + "px";
					Dialog.style.height = (this.Canvas.style.height.split("px")[0] / 10) * (10 - Size[0].substr(1, 1)) - 10 + "px";
					Dialog.style.top = this.Canvas.style.height.split("px")[0] - (this.Canvas.style.height.split("px")[0] / 10) * (10 - Size[0].substr(1, 1)) + "px";
					Dialog.style.left = (this.Canvas.style.width.split("px")[0] / 10) * (Math.min(Size[0].substr(0, 1), Size[1].substr(0, 1))) + "px";
				} else if (Size[1].substr(0, 1) == "^" && Size[1].substr(1, 1) != "^") {
					Dialog.style.width = (this.Canvas.style.width.split("px")[0] / 10) * (10 - Size[0].substr(0, 1)) - 10 + "px";
					Dialog.style.height = (this.Canvas.style.height.split("px")[0] / 10) * (Math.max(Size[0].substr(1, 1), Size[1].substr(1, 1)) - Math.min(Size[0].substr(1, 1), Size[1].substr(1, 1))) - 5 + "px";
					Dialog.style.top = (this.Canvas.style.height.split("px")[0] / 10) * (Math.min(Size[0].substr(1, 1), Size[1].substr(1, 1))) + "px";
					Dialog.style.left = this.Canvas.style.width.split("px")[0] - (this.Canvas.style.width.split("px")[0] / 10) * (10 - Size[0].substr(0, 1)) + "px";
				} else if (Size[1].substr(0, 1) == "^" && Size[1].substr(1, 1) == "^") {
					Dialog.style.width = (this.Canvas.style.width.split("px")[0] / 10) * (10 - Size[0].substr(0, 1)) - 10 + "px";
					Dialog.style.height = (this.Canvas.style.height.split("px")[0] / 10) * (10 - Size[0].substr(1, 1)) - 10 + "px";
					Dialog.style.top = this.Canvas.style.height.split("px")[0] - (this.Canvas.style.height.split("px")[0] / 10) * (10 - Size[0].substr(1, 1)) + "px";
					Dialog.style.left = this.Canvas.style.width.split("px")[0] - (this.Canvas.style.width.split("px")[0] / 10) * (10 - Size[0].substr(0, 1)) + "px";
				}
				
			this.Canvas.appendChild(Dialog);
			return Dialog;
		},
		
		/*/
		 *##################################################
		 *#>>MenuItem<<
		 *#メニューのアイテムを表示
		 *#
		 *#>>引数<<
		 *#ParentPanel : Element型
		 *#Size : Array型
		 *#|=> [0] : int型(00 ～ 99)
		 *#|=> [1] : int型(00 ～ 99)
		 *#
		 *#Content : String型
		 *#ClickFuc : Function型
		 *##################################################
		/*/
		MenuItem: function (ParentPanel, Size, Content, ClickFuc) {
			var Dialog = document.createElement("RPGHelper-Menu-MenuItem");
				Dialog.style.position = "Absolute";
				Dialog.textContent = Content;
				
				if (Size[0].substr(0, 1) != "^" && Size[1].substr(0, 1) != "^" && Size[0].substr(1, 1) != "^" && Size[1].substr(1, 1) != "^") {
					Dialog.style.width = (this.Canvas.style.width.split("px")[0] / 10) * (Math.max(Size[0].substr(0, 1), Size[1].substr(0, 1)) - Math.min(Size[0].substr(0, 1), Size[1].substr(0, 1))) - 5 + "px";
					Dialog.style.height = (this.Canvas.style.height.split("px")[0] / 10) * (Math.max(Size[0].substr(1, 1), Size[1].substr(1, 1)) - Math.min(Size[0].substr(1, 1), Size[1].substr(1, 1))) - 5 + "px";
					Dialog.style.top = (this.Canvas.style.height.split("px")[0] / 10) * (Math.min(Size[0].substr(1, 1), Size[1].substr(1, 1))) + "px";
					Dialog.style.left = (this.Canvas.style.width.split("px")[0] / 10) * (Math.min(Size[0].substr(0, 1), Size[1].substr(0, 1))) + "px";
				} else if (Size[1].substr(0, 1) != "^" && Size[1].substr(1, 1) == "^") {
					Dialog.style.width = (this.Canvas.style.width.split("px")[0] / 10) * (Math.max(Size[0].substr(0, 1), Size[1].substr(0, 1)) - Math.min(Size[0].substr(0, 1), Size[1].substr(0, 1))) - 5 + "px";
					Dialog.style.height = (this.Canvas.style.height.split("px")[0] / 10) * (10 - Size[0].substr(1, 1)) - 10 + "px";
					Dialog.style.top = this.Canvas.style.height.split("px")[0] - (this.Canvas.style.height.split("px")[0] / 10) * (10 - Size[0].substr(1, 1)) + "px";
					Dialog.style.left = (this.Canvas.style.width.split("px")[0] / 10) * (Math.min(Size[0].substr(0, 1), Size[1].substr(0, 1))) + "px";
				} else if (Size[1].substr(0, 1) == "^" && Size[1].substr(1, 1) != "^") {
					Dialog.style.width = (this.Canvas.style.width.split("px")[0] / 10) * (10 - Size[0].substr(0, 1)) - 10 + "px";
					Dialog.style.height = (this.Canvas.style.height.split("px")[0] / 10) * (Math.max(Size[0].substr(1, 1), Size[1].substr(1, 1)) - Math.min(Size[0].substr(1, 1), Size[1].substr(1, 1))) - 5 + "px";
					Dialog.style.top = (this.Canvas.style.height.split("px")[0] / 10) * (Math.min(Size[0].substr(1, 1), Size[1].substr(1, 1))) + "px";
					Dialog.style.left = this.Canvas.style.width.split("px")[0] - (this.Canvas.style.width.split("px")[0] / 10) * (10 - Size[0].substr(0, 1)) + "px";
				} else if (Size[1].substr(0, 1) == "^" && Size[1].substr(1, 1) == "^") {
					Dialog.style.width = (this.Canvas.style.width.split("px")[0] / 10) * (10 - Size[0].substr(0, 1)) - 10 + "px";
					Dialog.style.height = (this.Canvas.style.height.split("px")[0] / 10) * (10 - Size[0].substr(1, 1)) - 10 + "px";
					Dialog.style.top = this.Canvas.style.height.split("px")[0] - (this.Canvas.style.height.split("px")[0] / 10) * (10 - Size[0].substr(1, 1)) + "px";
					Dialog.style.left = this.Canvas.style.width.split("px")[0] - (this.Canvas.style.width.split("px")[0] / 10) * (10 - Size[0].substr(0, 1)) + "px";
				}
				
				Dialog.onclick = function () {
					ClickFuc();
				}
				
			ParentPanel.appendChild(Dialog);
			
			if (parseInt(Dialog.style.width.split("px")[0]) + parseInt(Dialog.parentElement.style.left.split("px")[0]) < parseInt(this.Canvas.style.width.split("px")[0] - 5)) {
				
			} else {
				Dialog.style.width = parseInt(Dialog.style.width.split("px")[0]) - 5 + "px";
			}
			
			return Dialog;
		}
	}
	
	this.Sound = {
		BGM: this.BGM,
		SE: this.SE,
		
		PlayBGM: function (URL, Volume) {
			if (!this.BGM.paused) {
				this.BGM.pause();
				this.BGM.currentTime = 0;
				this.BGM.src = "Audio/null.wav";
			}
			
			this.BGM.src = URL;
			this.BGM.volume = Volume;
			
			this.BGM.play();
		},
		
		StopBGM: function () {
			this.BGM.pause();
			this.BGM.currentTime = 0;
			this.BGM.src = "Audio/null.wav";
		},
		
		PlaySE: function (URL, Volume) {
			this.SE.src = URL;
			this.SE.volume = Volume;
			
			this.SE.play();
		}
	}
}
