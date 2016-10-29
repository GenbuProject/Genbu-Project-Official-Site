/*/
 *######################################################################
 *#RPG Helper Release 1.4 [Last Updated: 2016/09/19]
 *#Copyright (C) Genbu Project & Genbu Hase 2016 All Rights Reversed.
 *######################################################################
/*/
var RPGHelper = function () {
	this.Canvas = document.getElementById("RPGHelper-Main");
		this.Canvas.style.width = this.Canvas.attributes["width"].value + "px";
		this.Canvas.style.height = this.Canvas.attributes["height"].value + "px";
		this.Canvas.style.position = "Relative";
		
	this.BGM = [];
	this.SE = [];
	this.Util = [];
	
	this.CharaPos = [null, null, null]; //X座標(int), Y座標(int), 方向(R.DIRECTION.*)
	
	/*/
	 *##################################################
	 *#【R】
	 *#レイアウトシステム定数
	 *##################################################
	/*/
	R = {
		COLOR: {
			BLACK: "Black",
			BLUE: "Blue",
			CYAN: "Cyan",
			GLAY: "Glay",
			GREEN: "Green",
			MAGENTA: "Magenta",
			ORANGE: "Orange",
			RED: "Red",
			WHITE: "White"
		},
		
		POS: {
			TOP: 0x0001,
			BOTTOM: 0x0002,
			CENTER: 0x0003
		},
		
		SPEED: {
			SLOW: 100,
			NORMAL: 80,
			FAST: 50
		},
		
		DIRECTION: {
			E: "East",
			W: "West",
			S: "South",
			N: "North"
		},
		
		THIS: this
	}
	
	Object.freeze(R);
	
	/*/
	 *##################################################
	 *#【Resource】
	 *#RPGのセーブデータの情報
	 *##################################################
	/*/
	Resource = {
		SystemData: {
			Audio: {
				BGM: {},
				SE: {},
				Util: {}
			}
		},
		
		UserData: {
			Character: []
		}
	}
	
	/*/
	 *##################################################
	 *#【Sound】
	 *#音源の操作を行うクラス
	 *##################################################
	/*/
	this.Sound = {
		BGM: this.BGM,
		SE: this.SE,
		Util: this.Util,
		
		/*/
		 *##################################################
		 *#【Load】
		 *#使用する音源を一斉読み込みする
		 *##################################################
		/*/
		Load: function (LoadFuc) {
			var Ctx = new AudioContext();
			
			var BGM = this.BGM;
			var SE = this.SE;
			var Util = this.Util,
				UtilCount = 0;
			
			for (var Key in Resource.SystemData.Audio.BGM) {
				BGM[Resource.SystemData.Audio.BGM[Key].ID] = new Audio("Audio/" + Key);
					BGM[Resource.SystemData.Audio.BGM[Key].ID].loop = true;
					BGM[Resource.SystemData.Audio.BGM[Key].ID].volume = Resource.SystemData.Audio.BGM[Key].Volume;
					
					BGM[Resource.SystemData.Audio.BGM[Key].ID].onload = function () {
						var Source = Ctx.createMediaElementSource(BGM[Resource.SystemData.Audio.BGM[Key].ID]);
							Source.connect(Ctx.destination);
					}
			}
			
			for (var Key in Resource.SystemData.Audio.SE) {
				SE[Resource.SystemData.Audio.SE[Key].ID] = new Audio("Audio/SE/" + Key);
					SE[Resource.SystemData.Audio.SE[Key].ID].loop = false;
					SE[Resource.SystemData.Audio.SE[Key].ID].volume = Resource.SystemData.Audio.SE[Key].Volume;
					
					SE[Resource.SystemData.Audio.SE[Key].ID].onload = function () {
						var Source = Ctx.createMediaElementSource(SE[Resource.SystemData.Audio.BGM[Key].ID]);
							Source.connect(Ctx.destination);
					}
			}
			
			for (var Key in Resource.SystemData.Audio.Util) {
				Util[UtilCount] = new Audio("Audio/" + Resource.SystemData.Audio.Util[Key]);
					Util[UtilCount].loop = false;
					
					Util[UtilCount].onload = function () {
						var Source = Ctx.createMediaElementSource(Util[UtilCount]);
							Source.connect(Ctx.destination);
					}
					
				UtilCount++;
			}
			
			LoadFuc();
		},
		
		/*/
		 *##################################################
		 *#【PlayBGM】
		 *#指定されたIDの音源をBGMとして鳴らす
		 *#
		 *#≪引数≫
		 *#ID : int型
		 *##################################################
		/*/
		PlayBGM: function (ID) {
			for (var Key in Resource.SystemData.Audio.BGM) {
				if (!this.BGM[Resource.SystemData.Audio.BGM[Key].ID].paused) {
					this.BGM[Resource.SystemData.Audio.BGM[Key].ID].pause();
				}
			}
			
			for (var Key in Resource.SystemData.Audio.BGM) {
				if (Resource.SystemData.Audio.BGM[Key].ID == ID) {
					this.BGM[Resource.SystemData.Audio.BGM[Key].ID].load();
					this.BGM[Resource.SystemData.Audio.BGM[Key].ID].play();
				}
			}
		},
		
		/*/
		 *##################################################
		 *#【StopBGM】
		 *#BGMを停止する
		 *##################################################
		/*/
		StopBGM: function (ID) {
			if (ID == undefined) {
				for (var i = 0; i < this.BGM.length; i++) {
					if (this.BGM[i] != undefined && !this.BGM[i].paused) {
						this.BGM[i].pause();
					}
				}
			} else {
				for (var Key in Resource.SystemData.Audio.BGM) {
					if (Resource.SystemData.Audio.BGM[Key].ID == ID) {
						this.BGM[Resource.SystemData.Audio.BGM[Key].ID].pause();
					}
				}
			}
		},
		
		/*/
		 *##################################################
		 *#【PlaySE】
		 *#指定されたIDの音源をSEとして鳴らす
		 *#
		 *#≪引数≫
		 *#ID : int型
		 *##################################################
		/*/
		PlaySE: function (ID) {
			if (typeof arguments[0] == "number") {
				for (var Key in Resource.SystemData.Audio.SE) {
					if (Resource.SystemData.Audio.SE[Key].ID == ID) {
						this.SE[Resource.SystemData.Audio.SE[Key].ID].load();
						this.SE[Resource.SystemData.Audio.SE[Key].ID].play();
					}
				}
			} else if (typeof arguments[0] == "string") {
				for (var i = 0; i < this.Util.length; i++) {
					if (this.Util[i].src.split("Audio/")[1] == Resource.SystemData.Audio.Util[ID]) {
						this.Util[i].load();
						this.Util[i].play();
					}
				}
			}
		}
	}
	
	/*/
	 *##################################################
	 *#【Save】
	 *#RPGのセーブデータを保存する
	 *#
	 *#≪引数≫
	 *#FileName : String型
	 *##################################################
	/*/
	this.Save = function (FileName) {
		var Data = new Blob([JSON.stringify(Resource.UserData, null, "\t")], {
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
	 *#【Load】
	 *#RPGのセーブデータを読み込む
	 *#Resource.UserData内に格納される
	 *#
	 *#≪引数≫
	 *#Extention : String型
	 *#LoadFuc : Function型
	 *##################################################
	/*/
	this.Load = function (Extention, LoadFuc) {
		var Click = document.createEvent("MouseEvents");
			Click.initEvent("click", false, true);
			
		var Filer = document.createElement("Input");
			Filer.type = "File";
			Filer.accept = Extention;
			
			Filer.addEventListener("change", function (Event) {
				var Reader = new FileReader();
					Reader.readAsText(Event.target.files[0]);
					
					Reader.onload = function () {
						Resource.UserData = JSON.parse(Reader.result);
						LoadFuc();
					}
			});
			
			Filer.dispatchEvent(Click);
	}
	
	/*/
	 *##################################################
	 *#【SystemLoad】
	 *#RPGのシステムデータを読み込む
	 *#Resource.SystemData内に格納される
	 *##################################################
	/*/
	this.SystemLoad = function () {
		try {
			var Loader = new XMLHttpRequest();
				Loader.open("GET", "SystemData.Json", false);
				Loader.send(null);
				
				Resource.SystemData = JSON.parse(Loader.responseText);
		} catch (Error) {
			console.log("<RPG Helper> SystemData.Jsonが存在しません");
		}
	}
	
	/*/
	 *##################################################
	 *#【Effect】
	 *#エフェクトの描画を行うクラス
	 *##################################################
	/*/
	this.Effect = {
		Canvas: this.Canvas,
		
		/*/
		 *##################################################
		 *#【BlackOut】
		 *#黒色へフェーズインする
		 *#
		 *#≪引数≫
		 *#Sec : int型
		 *#Delay : int型
		 *#FinishFuc : Function型
		 *##################################################
		/*/
		BlackOut: function (Sec, Delay, FinishFuc) {
			var Style = document.createElement("Style");
				Style.id = "RPGHelper-Effect";
				Style.innerHTML = "@keyframes BlackOut {0% {BackGround: Transparent;} 100% {BackGround: Black;}}";
				
				document.head.appendChild(Style);
				
			var Effecter = document.createElement("Canvas");
				Effecter.style.position = "Absolute";
				Effecter.style.width = this.Canvas.style.width;
				Effecter.style.height = this.Canvas.style.height;
				Effecter.style.animation = "BlackOut " + Sec + "s Ease " + Delay + "s 1 Normal";
				
				this.Canvas.appendChild(Effecter);
				
			setTimeout(function () {
				Effecter.parentElement.removeChild(Effecter);
				document.head.removeChild(Style);
				
				FinishFuc();
			}, (Sec + Delay) * 1000);
			
			return Effecter;
		},
		
		/*/
		 *##################################################
		 *#【WhiteOut】
		 *#白色へフェーズインする
		 *#
		 *#≪引数≫
		 *#Sec : int型
		 *#Delay : int型
		 *#FinishFuc : Function型
		 *##################################################
		/*/
		WhiteOut: function (Sec, Delay, FinishFuc) {
			var Style = document.createElement("Style");
				Style.id = "RPGHelper-Effect";
				Style.innerHTML = "@keyframes WhiteOut {0% {BackGround: Transparent;} 100% {BackGround: White;}}";
				
				document.head.appendChild(Style);
				
			var Effecter = document.createElement("Canvas");
				Effecter.style.position = "Absolute";
				Effecter.style.width = this.Canvas.style.width;
				Effecter.style.height = this.Canvas.style.height;
				Effecter.style.animation = "WhiteOut " + Sec + "s Ease " + Delay + "s 1 Normal";
				
				this.Canvas.appendChild(Effecter);
				
			setTimeout(function () {
				Effecter.parentElement.removeChild(Effecter);
				document.head.removeChild(Style);
				
				FinishFuc();
			}, (Sec + Delay) * 1000);
			
			return Effecter;
		},
		
		/*/
		 *##################################################
		 *#【ColorOut】
		 *#指定色からフェーズアウトする
		 *#
		 *#≪引数≫
		 *#Sec : int型
		 *#Delay : int型
		 *#Color : R.COLOR型 || String型
		 *#FinishFuc : Function型
		 *##################################################
		/*/
		ColorOut: function (Sec, Delay, Color, FinishFuc) {
			var Style = document.createElement("Style");
				Style.id = "RPGHelper-Effect";
				Style.innerHTML = "@keyframes BlackOut {0% {BackGround: " + Color + ";} 100% {BackGround: Transparent;}}";
				
				document.head.appendChild(Style);
				
			var Effecter = document.createElement("Canvas");
				Effecter.style.position = "Absolute";
				Effecter.style.width = this.Canvas.style.width;
				Effecter.style.height = this.Canvas.style.height;
				Effecter.style.animation = "BlackOut " + Sec + "s Ease " + Delay + "s 1 Normal";
				
				this.Canvas.appendChild(Effecter);
				
			setTimeout(function () {
				Effecter.parentElement.removeChild(Effecter);
				document.head.removeChild(Style);
				
				FinishFuc();
			}, (Sec + Delay) * 1000);
			
			return Effecter;
		}
	}
	
	/*/
	 *##################################################
	 *#【Map】
	 *#マップ操作を行うクラス
	 *##################################################
	/*/
	this.Map = {
		Canvas: this.Canvas,
		
		TipData: null,
		TipSettingData: null,
		MapData: null,
		
		Timers: [],
		EtcTimers: [],
		Keys: [],
		EtcKeys: [],
		
		/*/
		 *##################################################
		 *#【Show】
		 *#指定したIDのマップを表示する
		 *#
		 *#≪引数≫
		 *#ID : int型
		 *##################################################
		/*/
		Show: function (ID, EventFucs) {
			if (document.getElementById("Map1")) {
				document.getElementById("Map1").parentElement.removeChild(document.getElementById("Map1"));
			}
			
			if (document.getElementById("Map2")) {
				document.getElementById("Map2").parentElement.removeChild(document.getElementById("Map2"));
			}
			
			if (document.getElementById("Map3")) {
				document.getElementById("Map3").parentElement.removeChild(document.getElementById("Map3"));
			}
			
			if (document.getElementById("Map4")) {
				document.getElementById("Map4").parentElement.removeChild(document.getElementById("Map4"));
			}
			
			if (this.Timers != []) {
				for (var i = 0; i < this.Timers.length; i++) {
					clearInterval(this.Timers[i]);
				}
				
				this.Timers = [];
			}
			
			if (this.Keys != []) {
				for (var i = 0; i < this.Keys.length; i++) {
					document.removeEventListener("keydown", this.Keys[i], false);
				}
				
				this.Keys = [];
			}
			
			var TipImg = new Image();
			
			var TipLoader = new XMLHttpRequest();
				TipLoader.open("GET", "Tile/" + Resource.SystemData.Tile[Resource.SystemData.Map[ID].TileID], true);
				TipLoader.responseType = "arraybuffer";
				
				TipLoader.onload = function () {
					TipData = URL.createObjectURL(
						new Blob(
							[TipLoader.response],
							{type: "image/png"}
						)
					);
					
					TipImg.src = TipData;
				}
				
				TipLoader.send(null);
				
			var TipSettingLoader = new XMLHttpRequest();
				TipSettingLoader.open("GET", "Tile/" + Resource.SystemData.Tile[Resource.SystemData.Map[ID].TileID].split(".png")[0] + ".TileSetting", false);
				
				TipSettingLoader.onload = function () {
					TipSettingData = JSON.parse(TipSettingLoader.responseText);
					TipSettingData[-1] = "0";
				}
				
				TipSettingLoader.send(null);
				
			var MapLoader = new XMLHttpRequest();
				MapLoader.open("GET", "Map/" + Resource.SystemData.Map[ID].MapFile, false);
				
				MapLoader.onload = function () {
					MapData = JSON.parse(MapLoader.responseText);
				}
				
				MapLoader.send(null);
				
			var MapCanvas1 = document.createElement("Canvas");
				MapCanvas1.id = "Map1";
				MapCanvas1.width = this.Canvas.style.width.split("px")[0];
				MapCanvas1.height = this.Canvas.style.height.split("px")[0];
				MapCanvas1.style.position = "Absolute";
				
				this.Canvas.appendChild(MapCanvas1);
				
			var MapCanvas2 = document.createElement("Canvas");
				MapCanvas2.id = "Map2";
				MapCanvas2.width = this.Canvas.style.width.split("px")[0];
				MapCanvas2.height = this.Canvas.style.height.split("px")[0];
				MapCanvas2.style.position = "Absolute";
				
				this.Canvas.appendChild(MapCanvas2);
				
			var MapCanvas3 = document.createElement("Canvas");
				MapCanvas3.id = "Map3";
				MapCanvas3.width = this.Canvas.style.width.split("px")[0];
				MapCanvas3.height = this.Canvas.style.height.split("px")[0];
				MapCanvas3.style.position = "Absolute";
				
				this.Canvas.appendChild(MapCanvas3);
				
			var MapCanvas4 = document.createElement("Canvas");
				MapCanvas4.id = "Map4";
				MapCanvas4.width = this.Canvas.style.width.split("px")[0];
				MapCanvas4.height = this.Canvas.style.height.split("px")[0];
				MapCanvas4.style.position = "Absolute";
				
				this.Canvas.appendChild(MapCanvas4);
				
			TipImg.onload = (function (Timers, Keys) {
				return function () {
					var Ctx1 = MapCanvas1.getContext("2d");
					var Ctx2 = MapCanvas2.getContext("2d");
					var Ctx3 = MapCanvas3.getContext("2d");
					var Ctx4 = MapCanvas4.getContext("2d");
					
					for (var y = 0; y < MapData[0].length; y++) {
	 					for (var x = 0; x < MapData[0][y].length; x++) {
	 						if (MapData[0][y][x] != -1 && typeof MapData[0][y][x] == "number") {
	 							Ctx1.drawImage(TipImg, 16 * (MapData[0][y][x] % 8), 16 * (Math.floor(MapData[0][y][x] / 8)), 16, 16, 16 * x, 16 * y, 16, 16);
	 						}
	 					}
	 				}
	 				
					for (var y = 0; y < MapData[1].length; y++) {
						for (var x = 0; x < MapData[1][y].length; x++) {
							if (MapData[1][y][x] != -1 && typeof MapData[1][y][x] == "number") {
								Ctx2.drawImage(TipImg, 16 * (MapData[1][y][x] % 8), 16 * (Math.floor(MapData[1][y][x] / 8)), 16, 16, 16 * x, 16 * y, 16, 16);
							}
						}
					}
					
					for (var y = 0; y < MapData[2].length; y++) {
						for (var x = 0; x < MapData[2][y].length; x++) {
							if (MapData[2][y][x] != -1 && typeof MapData[2][y][x] == "number") {
								Ctx3.drawImage(TipImg, 16 * (MapData[2][y][x] % 8), 16 * (Math.floor(MapData[2][y][x] / 8)), 16, 16, 16 * x, 16 * y, 16, 16);
							}
						}
					}
					
					for (var EventID = 0; EventID < MapData[3].length; EventID++) {
						Ctx4.drawImage(TipImg, 16 * (MapData[3][EventID]["TipID"] % 8), 16 * (Math.floor(MapData[3][EventID]["TipID"] / 8)), 16, 16, 16 * MapData[3][EventID]["Position"][0], 16 * MapData[3][EventID]["Position"][1], 16, 16);
						
						switch (MapData[3][EventID]["Type"]) {
							case 1:
								var X = MapData[3][EventID]["Position"][0], Y = MapData[3][EventID]["Position"][1];
								var Fuc = EventFucs[EventID];
								
								Timers.push(setInterval((function (X, Y, Fuc) {
									return function () {
										if (CharaPos[0] == X && CharaPos[1] == Y) {
											Fuc();
										}
									}
								})(X, Y, Fuc), 10));
								
								break;
								
							case 2:
								var X = MapData[3][EventID]["Position"][0], Y = MapData[3][EventID]["Position"][1];
								var Fuc = EventFucs[EventID];
								
								(function (X, Y, Fuc) {
									Keys.push(function (Event) {
										if (Event.keyCode == Resource.SystemData.Key.Decide) {
											if ((CharaPos[0] == X && CharaPos[1] == Y + 1) && CharaPos[2] == R.DIRECTION.N) {
												Fuc();
											} else if ((CharaPos[0] == X && CharaPos[1] == Y - 1) && CharaPos[2] == R.DIRECTION.S) {
												Fuc();
											} else if ((CharaPos[0] == X + 1 && CharaPos[1] == Y) && CharaPos[2] == R.DIRECTION.W) {
												Fuc();
											} else if ((CharaPos[0] == X - 1 && CharaPos[1] == Y) && CharaPos[2] == R.DIRECTION.E) {
												Fuc();
											}
										}
									});
								})(X, Y, Fuc);
								
								var ID = Keys.length - 1;
								
								document.addEventListener("keydown", Keys[ID]);
								
								break;
						}
					}
				}
			})(this.Timers, this.Keys);
			
			return [MapCanvas1, MapCanvas2, MapCanvas3, MapCanvas4];
		},
		
		/*/
		 *##################################################
		 *#【Hide】
		 *#マップを非表示にする
		 *##################################################
		/*/
		Hide: function () {
			if (document.getElementById("Map1")) {
				document.getElementById("Map1").parentElement.removeChild(document.getElementById("Map1"));
			}
			
			if (document.getElementById("Map2")) {
				document.getElementById("Map2").parentElement.removeChild(document.getElementById("Map2"));
			}
			
			if (document.getElementById("Map3")) {
				document.getElementById("Map3").parentElement.removeChild(document.getElementById("Map3"));
			}
			
			if (document.getElementById("Map4")) {
				document.getElementById("Map4").parentElement.removeChild(document.getElementById("Map4"));
			}
			
			if (this.Timers != []) {
				for (var i = 0; i < this.Timers.length; i++) {
					clearInterval(this.Timers[i]);
				}
				
				this.Timers = [];
			}
			
			if (this.Keys != []) {
				for (var i = 0; i < this.Keys.length; i++) {
					document.removeEventListener("keydown", this.Keys[i], false);
				}
				
				this.Keys = [];
			}
		}
	}
	
	/*/
	 *##################################################
	 *#【Sound】
	 *#キャラクターチップの操作を行うクラス
	 *##################################################
	/*/
	this.Character = {
		Canvas: this.Canvas,
		
		/*/
		 *##################################################
		 *#【Warp】
		 *#指定したIDのキャラクターを表示する
		 *#
		 *#≪引数≫
		 *#CharacterID : int型
		 *#Direction : R.DIRECTION型
		 *#Position : Array型
		 *#|=> [0] : int型
		 *#|=> [1] : int型
		 *##################################################
		/*/
		Warp: function (CharacterID, Direction, Position, VisibleTip, AppendLayerID) {
			if (document.getElementById("Character")) {
				document.getElementById("Character").parentElement.removeChild(document.getElementById("Character"));
			}
			
			var CharaImg = new Image();
			
			var CharaLoader = new XMLHttpRequest();
				CharaLoader.open("GET", "CharacterTip/" + Resource.UserData.Character[CharacterID].CharacterTip, true);
				CharaLoader.responseType = "arraybuffer";
				
				CharaLoader.onload = function () {
					var Link = URL.createObjectURL(
						new Blob(
							[CharaLoader.response], 
							{"type": "image/png"}
						)
					);
					
					CharaImg.src = Link;
				}
				
				CharaLoader.send(null);
				
			var CharaCanvas = document.createElement("Canvas");
				CharaCanvas.id = "Character";
				CharaCanvas.width = this.Canvas.style.width.split("px")[0];
				CharaCanvas.height = this.Canvas.style.height.split("px")[0];
				CharaCanvas.style.position = "Absolute";
				
				if (AppendLayerID == undefined) {
					this.Canvas.appendChild(CharaCanvas);
				} else {
					this.Canvas.insertBefore(CharaCanvas, document.getElementById("Map" + (AppendLayerID + 1)));
				}
				
			CharaImg.onload = function () {
				var Ctx = CharaCanvas.getContext("2d");
				
				if (VisibleTip == null) {
					Ctx.drawImage(CharaImg, 32, Direction == R.DIRECTION.E ? 96 : Direction == R.DIRECTION.W ? 48 : Direction == R.DIRECTION.S ? 0 : Direction == R.DIRECTION.N ? 144 : 0, 32, 48, 16 * Position[0], 16 * (Position[1] - 1), 16, 32);
				} else if (VisibleTip instanceof Array) {
					if (VisibleTip[0] == 0x0000) {
						if (VisibleTip[1] == 0x0000) {
							Ctx.drawImage(CharaImg, 32, Direction == R.DIRECTION.E ? 96 : Direction == R.DIRECTION.W ? 48 : Direction == R.DIRECTION.S ? 0 : Direction == R.DIRECTION.N ? 144 : 0, 32, 48, 16 * Position[0], 16 * (Position[1] - 1), 16, 32);
						} else if (VisibleTip[1] == 0x0001) {
							Ctx.drawImage(CharaImg, 32, Direction == R.DIRECTION.E ? 96 : Direction == R.DIRECTION.W ? 48 : Direction == R.DIRECTION.S ? 0 : Direction == R.DIRECTION.N ? 144 : 0, 32, 24, 16 * Position[0], 16 * (Position[1] - 1), 16, 16);
						} else if (VisibleTip[1] == 0x0002) {
							Ctx.drawImage(CharaImg, 32, Direction == R.DIRECTION.E ? 96 : Direction == R.DIRECTION.W ? 48 : Direction == R.DIRECTION.S ? 0 : Direction == R.DIRECTION.N ? 144 : 0, 32, 24, 16 * Position[0], 16 * (Position[1] - 1), 16, 16);
							
							Ctx.globalAlpha = 0.5;
							Ctx.drawImage(CharaImg, 32, Direction == R.DIRECTION.E ? 120 : Direction == R.DIRECTION.W ? 72 : Direction == R.DIRECTION.S ? 24 : Direction == R.DIRECTION.N ? 168 : 24, 32, 24, 16 * Position[0], 16 * (Position[1]), 16, 16);
						}
					} else if (VisibleTip[0] == 0x0001) {
						if (VisibleTip[1] == 0x0000) {
							Ctx.drawImage(CharaImg, 32, Direction == R.DIRECTION.E ? 120 : Direction == R.DIRECTION.W ? 72 : Direction == R.DIRECTION.S ? 24 : Direction == R.DIRECTION.N ? 168 : 24, 32, 24, 16 * Position[0], 16 * (Position[1]), 16, 16);
						} else if (VisibleTip[1] == 0x0001) {
							
						} else if (VisibleTip[1] == 0x0002) {
							Ctx.globalAlpha = 0.5;
							Ctx.drawImage(CharaImg, 32, Direction == R.DIRECTION.E ? 120 : Direction == R.DIRECTION.W ? 72 : Direction == R.DIRECTION.S ? 24 : Direction == R.DIRECTION.N ? 168 : 24, 32, 24, 16 * Position[0], 16 * (Position[1]), 16, 16);
						}
					} else if (VisibleTip[0] == 0x0002) {
						if (VisibleTip[1] == 0x0000) {
							Ctx.globalAlpha = 0.5;
							Ctx.drawImage(CharaImg, 32, Direction == R.DIRECTION.E ? 96 : Direction == R.DIRECTION.W ? 48 : Direction == R.DIRECTION.S ? 0 : Direction == R.DIRECTION.N ? 144 : 0, 32, 24, 16 * Position[0], 16 * (Position[1] - 1), 16, 16);
							
							Ctx.globalAlpha = 1.0;
							Ctx.drawImage(CharaImg, 32, Direction == R.DIRECTION.E ? 120 : Direction == R.DIRECTION.W ? 72 : Direction == R.DIRECTION.S ? 24 : Direction == R.DIRECTION.N ? 168 : 24, 32, 24, 16 * Position[0], 16 * (Position[1]), 16, 16);
						} else if (VisibleTip[1] == 0x0001) {
							Ctx.globalAlpha = 0.5;
							Ctx.drawImage(CharaImg, 32, Direction == R.DIRECTION.E ? 96 : Direction == R.DIRECTION.W ? 48 : Direction == R.DIRECTION.S ? 0 : Direction == R.DIRECTION.N ? 144 : 0, 32, 24, 16 * Position[0], 16 * (Position[1] - 1), 16, 16);
						} else if (VisibleTip[1] == 0x0002) {
							Ctx.globalAlpha = 0.5;
							Ctx.drawImage(CharaImg, 32, Direction == R.DIRECTION.E ? 96 : Direction == R.DIRECTION.W ? 48 : Direction == R.DIRECTION.S ? 0 : Direction == R.DIRECTION.N ? 144 : 0, 32, 48, 16 * Position[0], 16 * (Position[1] - 1), 16, 32);
						}
					}
				}
			}
			
			CharaPos = [Position[0], Position[1], Direction];
		},
		
		/*/
		 *##################################################
		 *#【Hide】
		 *#キャラクターを非表示にする
		 *##################################################
		/*/
		Hide: function () {
			if (document.getElementById("Character")) {
				document.getElementById("Character").parentElement.removeChild(document.getElementById("Character"));
			}
		}
	}
	
	this.GamePad = {
		Map: this.Map,
		Character: this.Character,
		
		KeyboardType: function (CharacterID) {
			var Map = this.Map;
			var Character = this.Character;
			
			if (sessionStorage.getItem("GamePad") != null) {
				document.removeEventListener("keydown", R.THIS.PadFunc, false);
			}
			
			R.THIS.MoveFuc = function (Key) {
				var MemPos = [Key == Resource.SystemData.Key.Left ? CharaPos[0] - 1 : Key == Resource.SystemData.Key.Right ? CharaPos[0] + 1 : CharaPos[0], Key == Resource.SystemData.Key.Up ? CharaPos[1] - 1 : Key == Resource.SystemData.Key.Down ? CharaPos[1] + 1 : CharaPos[1]];
				var Direction = Key == Resource.SystemData.Key.Up ? R.DIRECTION.N : Key == Resource.SystemData.Key.Down ? R.DIRECTION.S : Key == Resource.SystemData.Key.Left ? R.DIRECTION.W : Key == Resource.SystemData.Key.Right ? R.DIRECTION.E : null;
				
				if ((Key != Resource.SystemData.Key.Up) && (Key != Resource.SystemData.Key.Down) && (Key != Resource.SystemData.Key.Left) && (Key != Resource.SystemData.Key.Right)) {
					return true;
				}
				
				if (MapData[0][MemPos[1]][MemPos[0]] == -1) {
					if (MapData[1][MemPos[1]][MemPos[0]] == -1) {
						if (MapData[2][MemPos[1]][MemPos[0]] == -1) {
							//通行可能
							Character.Warp(CharacterID, Direction, [MemPos[0], MemPos[1]], null);
						}
					}
				}
				
				if ((TipSettingData[MapData[2][MemPos[1]][MemPos[0]]] == "0" && MapData[2][MemPos[1]][MemPos[0]] == -1) || TipSettingData[MapData[2][MemPos[1]][MemPos[0]]] == "200" || TipSettingData[MapData[2][MemPos[1]][MemPos[0]]] == "100200" || TipSettingData[MapData[2][MemPos[1]][MemPos[0]]] == "300" || TipSettingData[MapData[2][MemPos[1]][MemPos[0]]] == "400200") {
					if ((TipSettingData[MapData[1][MemPos[1]][MemPos[0]]] == "0" && MapData[1][MemPos[1]][MemPos[0]] == -1) || TipSettingData[MapData[1][MemPos[1]][MemPos[0]]] == "200" || TipSettingData[MapData[1][MemPos[1]][MemPos[0]]] == "100200" || TipSettingData[MapData[1][MemPos[1]][MemPos[0]]] == "300" || TipSettingData[MapData[1][MemPos[1]][MemPos[0]]] == "400200") {
						if ((TipSettingData[MapData[0][MemPos[1]][MemPos[0]]] == "0" && MapData[0][MemPos[1]][MemPos[0]] == -1) || TipSettingData[MapData[0][MemPos[1]][MemPos[0]]] == "200" || TipSettingData[MapData[0][MemPos[1]][MemPos[0]]] == "100200" || TipSettingData[MapData[0][MemPos[1]][MemPos[0]]] == "300" || TipSettingData[MapData[0][MemPos[1]][MemPos[0]]] == "400200") {
							//通行可能
							if (TipSettingData[MapData[2][MemPos[1]][MemPos[0]]] == "100200" && TipSettingData[MapData[1][MemPos[1]][MemPos[0]]] == "100200" && TipSettingData[MapData[0][MemPos[1]][MemPos[0]]] == "100200") {
								Character.Warp(CharacterID, Direction, [MemPos[0], MemPos[1]], [0x0000, 0x0001]);
							} else if (TipSettingData[MapData[2][MemPos[1]][MemPos[0]]] == "300" && TipSettingData[MapData[1][MemPos[1]][MemPos[0]]] == "300" && TipSettingData[MapData[0][MemPos[1]][MemPos[0]]] == "300") {
								Character.Warp(CharacterID, Direction, [MemPos[0], MemPos[1]], [0x0000, 0x0001]);
							} else if (TipSettingData[MapData[2][MemPos[1]][MemPos[0]]] == "400200" && TipSettingData[MapData[1][MemPos[1]][MemPos[0]]] == "400200" && TipSettingData[MapData[0][MemPos[1]][MemPos[0]]] == "400200") {
								Character.Warp(CharacterID, Direction, [MemPos[0], MemPos[1]], [0x0000, 0x0002]);
							} else {
								Character.Warp(CharacterID, Direction, [MemPos[0], MemPos[1]], null);
							}
						} else {
							switch (TipSettingData[MapData[0][MemPos[1]][MemPos[0]]]) {
								case "0":
									//通行可能
									Character.Warp(CharacterID, Direction, [MemPos[0], MemPos[1]], null);
									break;
									
								case "F0000":
									//通行不可
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "100000":
									//常にキャラの上に表示
									Character.Warp(CharacterID, Direction, [MemPos[0], MemPos[1]], [0x0000, 0x0001], 0);
									break;
									
								case "100":
									//後ろに行くと隠れる
									Character.Warp(CharacterID, Direction, [MemPos[0], MemPos[1]], [0x0000, 0x0001]);
									break;
									
								case "200":
									//下レイヤーに合わせる
									break;
									
								case "400000":
									//下半身が半透明に
									Character.Warp(CharacterID, Direction, [MemPos[0], MemPos[1]], [0x0000, 0x0002]);
									break;
									
								case "100200":
									//常にキャラの上に表示 + 下レイヤーに合わせる
									break;
									
								case "300":
									//後ろに行くと隠れる + 下レイヤーに合わせる
									break;
									
								case "400200":
									//下半身が半透明に + 下レイヤーに合わせる
									break;
									
								case "210000":
									/*/
									 *[○, ○]
									 *[○, ×]
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "220000":
									/*/
									 *[○, ○]
									 *[×, ○]
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "230000":
									/*/
									 *[○, ○]
									 *[×, ×]
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "240000":
									/*/
									 *[○, ×]
									 *[○, ○]
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "250000":
									/*/
									 *[○, ×]
									 *[○, ×]
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "260000":
									/*/
									 *[○, ×]
									 *[×, ○]
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "270000":
									/*/
									 *[○, ×]
									 *[×, ×]
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "280000":
									/*/
									 *[×, ○]
									 *[○, ○]
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "290000":
									/*/
									 *[×, ○]
									 *[○, ×]
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "2A0000":
									/*/
									 *[×, ○]
									 *[×, ○]
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "2B0000":
									/*/
									 *[×, ○]
									 *[×, ×]
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "2C0000":
									/*/
									 *[×, ×]
									 *[○, ○]
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "2D0000":
									/*/
									 *[×, ×]
									 *[○, ×]
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "2E0000":
									/*/
									 *[×, ×]
									 *[×, ○]
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "2F0000":
									/*/
									 *[×, ×]
									 *[×, ×]
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "310000":
									/*/
									 *[○, ○]
									 *[○, ×] + 常にキャラの上に表示
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "320000":
									/*/
									 *[○, ○]
									 *[×, ○] + 常にキャラの上に表示
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "330000":
									/*/
									 *[○, ○]
									 *[×, ×] + 常にキャラの上に表示
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "340000":
									/*/
									 *[○, ×]
									 *[○, ○] + 常にキャラの上に表示
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "350000":
									/*/
									 *[○, ×]
									 *[○, ×] + 常にキャラの上に表示
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "360000":
									/*/
									 *[○, ×]
									 *[×, ○] + 常にキャラの上に表示
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "370000":
									/*/
									 *[○, ×]
									 *[×, ×] + 常にキャラの上に表示
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "380000":
									/*/
									 *[×, ○]
									 *[○, ○] + 常にキャラの上に表示
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "390000":
									/*/
									 *[×, ○]
									 *[○, ×] + 常にキャラの上に表示
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "3A0000":
									/*/
									 *[×, ○]
									 *[×, ○] + 常にキャラの上に表示
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "3B0000":
									/*/
									 *[×, ○]
									 *[×, ×] + 常にキャラの上に表示
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "3C0000":
									/*/
									 *[×, ×]
									 *[○, ○] + 常にキャラの上に表示
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "3D0000":
									/*/
									 *[×, ×]
									 *[○, ×] + 常にキャラの上に表示
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "3E0000":
									/*/
									 *[×, ×]
									 *[×, ○] + 常にキャラの上に表示
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "3F0000":
									/*/
									 *[×, ×]
									 *[×, ×] + 常にキャラの上に表示
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "210100":
									/*/
									 *[○, ○]
									 *[○, ×] + 後ろに行くと隠れる
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "220100":
									/*/
									 *[○, ○]
									 *[×, ○] + 後ろに行くと隠れる
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "230100":
									/*/
									 *[○, ○]
									 *[×, ×] + 後ろに行くと隠れる
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "240100":
									/*/
									 *[○, ×]
									 *[○, ○] + 後ろに行くと隠れる
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "250100":
									/*/
									 *[○, ×]
									 *[○, ×] + 後ろに行くと隠れる
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "260100":
									/*/
									 *[○, ×]
									 *[×, ○] + 後ろに行くと隠れる
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "270100":
									/*/
									 *[○, ×]
									 *[×, ×] + 後ろに行くと隠れる
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "280100":
									/*/
									 *[×, ○]
									 *[○, ○] + 後ろに行くと隠れる
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "290100":
									/*/
									 *[×, ○]
									 *[○, ×] + 後ろに行くと隠れる
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "2A0100":
									/*/
									 *[×, ○]
									 *[×, ○] + 後ろに行くと隠れる
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "2B0100":
									/*/
									 *[×, ○]
									 *[×, ×] + 後ろに行くと隠れる
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "2C0100":
									/*/
									 *[×, ×]
									 *[○, ○] + 後ろに行くと隠れる
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "2D0100":
									/*/
									 *[×, ×]
									 *[○, ×] + 後ろに行くと隠れる
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "2E0100":
									/*/
									 *[×, ×]
									 *[×, ○] + 後ろに行くと隠れる
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "610000":
									/*/
									 *[○, ○]
									 *[○, ×] + 下半身が半透明に
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "620000":
									/*/
									 *[○, ○]
									 *[×, ○] + 下半身が半透明に
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "630000":
									/*/
									 *[○, ○]
									 *[×, ×] + 下半身が半透明に
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "640000":
									/*/
									 *[○, ×]
									 *[○, ○] + 下半身が半透明に
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "650000":
									/*/
									 *[○, ×]
									 *[○, ×] + 下半身が半透明に
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "660000":
									/*/
									 *[○, ×]
									 *[×, ○] + 下半身が半透明に
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "670000":
									/*/
									 *[○, ×]
									 *[×, ×] + 下半身が半透明に
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "680000":
									/*/
									 *[×, ○]
									 *[○, ○] + 下半身が半透明に
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "690000":
									/*/
									 *[×, ○]
									 *[○, ×] + 下半身が半透明に
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "6A0000":
									/*/
									 *[×, ○]
									 *[×, ○] + 下半身が半透明に
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "6B0000":
									/*/
									 *[×, ○]
									 *[×, ×] + 下半身が半透明に
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "6C0000":
									/*/
									 *[×, ×]
									 *[○, ○] + 下半身が半透明に
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "6D0000":
									/*/
									 *[×, ×]
									 *[○, ×] + 下半身が半透明に
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "6E0000":
									/*/
									 *[×, ×]
									 *[×, ○] + 下半身が半透明に
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
									
								case "6F0000":
									/*/
									 *[×, ×]
									 *[×, ×] + 下半身が半透明に
									/*/
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
									break;
							}
						}
					} else {
						switch (TipSettingData[MapData[1][MemPos[1]][MemPos[0]]]) {
							case "0":
								//通行可能
								Character.Warp(CharacterID, Direction, [MemPos[0], MemPos[1]], null);
								break;
								
							case "F0000":
								//通行不可
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "100000":
								//常にキャラの上に表示
								Character.Warp(CharacterID, Direction, [MemPos[0], MemPos[1]], [0x0000, 0x0001]);
								break;
								
							case "100":
								//後ろに行くと隠れる
								Character.Warp(CharacterID, Direction, [MemPos[0], MemPos[1]], [0x0000, 0x0001]);
								break;
								
							case "200":
								//下レイヤーに合わせる
								break;
								
							case "400000":
								//下半身が半透明に
								Character.Warp(CharacterID, Direction, [MemPos[0], MemPos[1]], [0x0000, 0x0002]);
								break;
								
							case "100200":
								//常にキャラの上に表示 + 下レイヤーに合わせる
								break;
								
							case "300":
								//後ろに行くと隠れる + 下レイヤーに合わせる
								break;
								
							case "400200":
								//下半身が半透明に + 下レイヤーに合わせる
								break;
								
							case "210000":
								/*/
								 *[○, ○]
								 *[○, ×]
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "220000":
								/*/
								 *[○, ○]
								 *[×, ○]
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "230000":
								/*/
								 *[○, ○]
								 *[×, ×]
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "240000":
								/*/
								 *[○, ×]
								 *[○, ○]
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "250000":
								/*/
								 *[○, ×]
								 *[○, ×]
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "260000":
								/*/
								 *[○, ×]
								 *[×, ○]
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "270000":
								/*/
								 *[○, ×]
								 *[×, ×]
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "280000":
								/*/
								 *[×, ○]
								 *[○, ○]
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "290000":
								/*/
								 *[×, ○]
								 *[○, ×]
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "2A0000":
								/*/
								 *[×, ○]
								 *[×, ○]
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "2B0000":
								/*/
								 *[×, ○]
								 *[×, ×]
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "2C0000":
								/*/
								 *[×, ×]
								 *[○, ○]
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "2D0000":
								/*/
								 *[×, ×]
								 *[○, ×]
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "2E0000":
								/*/
								 *[×, ×]
								 *[×, ○]
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "2F0000":
								/*/
								 *[×, ×]
								 *[×, ×]
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "310000":
								/*/
								 *[○, ○]
								 *[○, ×] + 常にキャラの上に表示
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "320000":
								/*/
								 *[○, ○]
								 *[×, ○] + 常にキャラの上に表示
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "330000":
								/*/
								 *[○, ○]
								 *[×, ×] + 常にキャラの上に表示
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "340000":
								/*/
								 *[○, ×]
								 *[○, ○] + 常にキャラの上に表示
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "350000":
								/*/
								 *[○, ×]
								 *[○, ×] + 常にキャラの上に表示
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "360000":
								/*/
								 *[○, ×]
								 *[×, ○] + 常にキャラの上に表示
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "370000":
								/*/
								 *[○, ×]
								 *[×, ×] + 常にキャラの上に表示
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "380000":
								/*/
								 *[×, ○]
								 *[○, ○] + 常にキャラの上に表示
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "390000":
								/*/
								 *[×, ○]
								 *[○, ×] + 常にキャラの上に表示
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "3A0000":
								/*/
								 *[×, ○]
								 *[×, ○] + 常にキャラの上に表示
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "3B0000":
								/*/
								 *[×, ○]
								 *[×, ×] + 常にキャラの上に表示
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "3C0000":
								/*/
								 *[×, ×]
								 *[○, ○] + 常にキャラの上に表示
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "3D0000":
								/*/
								 *[×, ×]
								 *[○, ×] + 常にキャラの上に表示
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "3E0000":
								/*/
								 *[×, ×]
								 *[×, ○] + 常にキャラの上に表示
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "3F0000":
								/*/
								 *[×, ×]
								 *[×, ×] + 常にキャラの上に表示
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "210100":
								/*/
								 *[○, ○]
								 *[○, ×] + 後ろに行くと隠れる
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "220100":
								/*/
								 *[○, ○]
								 *[×, ○] + 後ろに行くと隠れる
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "230100":
								/*/
								 *[○, ○]
								 *[×, ×] + 後ろに行くと隠れる
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "240100":
								/*/
								 *[○, ×]
								 *[○, ○] + 後ろに行くと隠れる
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "250100":
								/*/
								 *[○, ×]
								 *[○, ×] + 後ろに行くと隠れる
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "260100":
								/*/
								 *[○, ×]
								 *[×, ○] + 後ろに行くと隠れる
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "270100":
								/*/
								 *[○, ×]
								 *[×, ×] + 後ろに行くと隠れる
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "280100":
								/*/
								 *[×, ○]
								 *[○, ○] + 後ろに行くと隠れる
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "290100":
								/*/
								 *[×, ○]
								 *[○, ×] + 後ろに行くと隠れる
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "2A0100":
								/*/
								 *[×, ○]
								 *[×, ○] + 後ろに行くと隠れる
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "2B0100":
								/*/
								 *[×, ○]
								 *[×, ×] + 後ろに行くと隠れる
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "2C0100":
								/*/
								 *[×, ×]
								 *[○, ○] + 後ろに行くと隠れる
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "2D0100":
								/*/
								 *[×, ×]
								 *[○, ×] + 後ろに行くと隠れる
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "2E0100":
								/*/
								 *[×, ×]
								 *[×, ○] + 後ろに行くと隠れる
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "610000":
								/*/
								 *[○, ○]
								 *[○, ×] + 下半身が半透明に
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "620000":
								/*/
								 *[○, ○]
								 *[×, ○] + 下半身が半透明に
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "630000":
								/*/
								 *[○, ○]
								 *[×, ×] + 下半身が半透明に
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "640000":
								/*/
								 *[○, ×]
								 *[○, ○] + 下半身が半透明に
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "650000":
								/*/
								 *[○, ×]
								 *[○, ×] + 下半身が半透明に
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "660000":
								/*/
								 *[○, ×]
								 *[×, ○] + 下半身が半透明に
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "670000":
								/*/
								 *[○, ×]
								 *[×, ×] + 下半身が半透明に
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "680000":
								/*/
								 *[×, ○]
								 *[○, ○] + 下半身が半透明に
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "690000":
								/*/
								 *[×, ○]
								 *[○, ×] + 下半身が半透明に
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "6A0000":
								/*/
								 *[×, ○]
								 *[×, ○] + 下半身が半透明に
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "6B0000":
								/*/
								 *[×, ○]
								 *[×, ×] + 下半身が半透明に
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "6C0000":
								/*/
								 *[×, ×]
								 *[○, ○] + 下半身が半透明に
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "6D0000":
								/*/
								 *[×, ×]
								 *[○, ×] + 下半身が半透明に
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "6E0000":
								/*/
								 *[×, ×]
								 *[×, ○] + 下半身が半透明に
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
								
							case "6F0000":
								/*/
								 *[×, ×]
								 *[×, ×] + 下半身が半透明に
								/*/
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
								break;
						}
					}
				} else {
					switch (TipSettingData[MapData[2][MemPos[1]][MemPos[0]]]) {
						case "0":
							//通行可能
							Character.Warp(CharacterID, Direction, [MemPos[0], MemPos[1]], null);
							break;
							
						case "F0000":
							//通行不可
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "100000":
							//常にキャラの上に表示
							Character.Warp(CharacterID, Direction, [MemPos[0], MemPos[1]], [0x0000, 0x0001]);
							break;
							
						case "100":
							//後ろに行くと隠れる
							Character.Warp(CharacterID, Direction, [MemPos[0], MemPos[1]], [0x0000, 0x0001]);
							break;
							
						case "200":
							//下レイヤーに合わせる
							break;
							
						case "400000":
							//下半身が半透明に
							Character.Warp(CharacterID, Direction, [MemPos[0], MemPos[1]], [0x0000, 0x0002]);
							break;
							
						case "100200":
							//常にキャラの上に表示 + 下レイヤーに合わせる
							break;
							
						case "300":
							//後ろに行くと隠れる + 下レイヤーに合わせる
							break;
							
						case "400200":
							//下半身が半透明に + 下レイヤーに合わせる
							break;
							
						case "210000":
							/*/
							 *[○, ○]
							 *[○, ×]
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "220000":
							/*/
							 *[○, ○]
							 *[×, ○]
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "230000":
							/*/
							 *[○, ○]
							 *[×, ×]
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "240000":
							/*/
							 *[○, ×]
							 *[○, ○]
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "250000":
							/*/
							 *[○, ×]
							 *[○, ×]
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "260000":
							/*/
							 *[○, ×]
							 *[×, ○]
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "270000":
							/*/
							 *[○, ×]
							 *[×, ×]
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "280000":
							/*/
							 *[×, ○]
							 *[○, ○]
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "290000":
							/*/
							 *[×, ○]
							 *[○, ×]
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "2A0000":
							/*/
							 *[×, ○]
							 *[×, ○]
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "2B0000":
							/*/
							 *[×, ○]
							 *[×, ×]
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "2C0000":
							/*/
							 *[×, ×]
							 *[○, ○]
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "2D0000":
							/*/
							 *[×, ×]
							 *[○, ×]
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "2E0000":
							/*/
							 *[×, ×]
							 *[×, ○]
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "2F0000":
							/*/
							 *[×, ×]
							 *[×, ×]
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "310000":
							/*/
							 *[○, ○]
							 *[○, ×] + 常にキャラの上に表示
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "320000":
							/*/
							 *[○, ○]
							 *[×, ○] + 常にキャラの上に表示
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "330000":
							/*/
							 *[○, ○]
							 *[×, ×] + 常にキャラの上に表示
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "340000":
							/*/
							 *[○, ×]
							 *[○, ○] + 常にキャラの上に表示
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "350000":
							/*/
							 *[○, ×]
							 *[○, ×] + 常にキャラの上に表示
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "360000":
							/*/
							 *[○, ×]
							 *[×, ○] + 常にキャラの上に表示
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "370000":
							/*/
							 *[○, ×]
							 *[×, ×] + 常にキャラの上に表示
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "380000":
							/*/
							 *[×, ○]
							 *[○, ○] + 常にキャラの上に表示
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "390000":
							/*/
							 *[×, ○]
							 *[○, ×] + 常にキャラの上に表示
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "3A0000":
							/*/
							 *[×, ○]
							 *[×, ○] + 常にキャラの上に表示
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "3B0000":
							/*/
							 *[×, ○]
							 *[×, ×] + 常にキャラの上に表示
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "3C0000":
							/*/
							 *[×, ×]
							 *[○, ○] + 常にキャラの上に表示
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "3D0000":
							/*/
							 *[×, ×]
							 *[○, ×] + 常にキャラの上に表示
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "3E0000":
							/*/
							 *[×, ×]
							 *[×, ○] + 常にキャラの上に表示
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "3F0000":
							/*/
							 *[×, ×]
							 *[×, ×] + 常にキャラの上に表示
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "210100":
							/*/
							 *[○, ○]
							 *[○, ×] + 後ろに行くと隠れる
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "220100":
							/*/
							 *[○, ○]
							 *[×, ○] + 後ろに行くと隠れる
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "230100":
							/*/
							 *[○, ○]
							 *[×, ×] + 後ろに行くと隠れる
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "240100":
							/*/
							 *[○, ×]
							 *[○, ○] + 後ろに行くと隠れる
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "250100":
							/*/
							 *[○, ×]
							 *[○, ×] + 後ろに行くと隠れる
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "260100":
							/*/
							 *[○, ×]
							 *[×, ○] + 後ろに行くと隠れる
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "270100":
							/*/
							 *[○, ×]
							 *[×, ×] + 後ろに行くと隠れる
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "280100":
							/*/
							 *[×, ○]
							 *[○, ○] + 後ろに行くと隠れる
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "290100":
							/*/
							 *[×, ○]
							 *[○, ×] + 後ろに行くと隠れる
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "2A0100":
							/*/
							 *[×, ○]
							 *[×, ○] + 後ろに行くと隠れる
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "2B0100":
							/*/
							 *[×, ○]
							 *[×, ×] + 後ろに行くと隠れる
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "2C0100":
							/*/
							 *[×, ×]
							 *[○, ○] + 後ろに行くと隠れる
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "2D0100":
							/*/
							 *[×, ×]
							 *[○, ×] + 後ろに行くと隠れる
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "2E0100":
							/*/
							 *[×, ×]
							 *[×, ○] + 後ろに行くと隠れる
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "610000":
							/*/
							 *[○, ○]
							 *[○, ×] + 下半身が半透明に
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "620000":
							/*/
							 *[○, ○]
							 *[×, ○] + 下半身が半透明に
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "630000":
							/*/
							 *[○, ○]
							 *[×, ×] + 下半身が半透明に
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "640000":
							/*/
							 *[○, ×]
							 *[○, ○] + 下半身が半透明に
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "650000":
							/*/
							 *[○, ×]
							 *[○, ×] + 下半身が半透明に
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "660000":
							/*/
							 *[○, ×]
							 *[×, ○] + 下半身が半透明に
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "670000":
							/*/
							 *[○, ×]
							 *[×, ×] + 下半身が半透明に
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "680000":
							/*/
							 *[×, ○]
							 *[○, ○] + 下半身が半透明に
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "690000":
							/*/
							 *[×, ○]
							 *[○, ×] + 下半身が半透明に
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "6A0000":
							/*/
							 *[×, ○]
							 *[×, ○] + 下半身が半透明に
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "6B0000":
							/*/
							 *[×, ○]
							 *[×, ×] + 下半身が半透明に
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "6C0000":
							/*/
							 *[×, ×]
							 *[○, ○] + 下半身が半透明に
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "6D0000":
							/*/
							 *[×, ×]
							 *[○, ×] + 下半身が半透明に
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "6E0000":
							/*/
							 *[×, ×]
							 *[×, ○] + 下半身が半透明に
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
							
						case "6F0000":
							/*/
							 *[×, ×]
							 *[×, ×] + 下半身が半透明に
							/*/
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], null);
							break;
					}
				}

				if (TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "100000" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "100" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "400000" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "100200" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "300" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "400200" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "310000" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "320000" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "330000" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "340000" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "350000" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "360000" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "370000" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "380000" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "390000" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "3A0000" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "3B0000" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "3C0000" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "3D0000" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "3E0000" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "3F0000" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "210100" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "220100" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "230100" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "240100" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "250100" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "260100" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "270100" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "280100" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "290100" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "2A0100" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "2B0100" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "2C0100" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "2D0100" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "2E0100" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "2F0100" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "610000" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "620000" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "630000" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "640000" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "650000" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "660000" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "670000" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "680000" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "690000" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "6A0000" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "6B0000" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "6C0000" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "6D0000" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "6E0000" && TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] != "6F0000") {
					if (TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "100000" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "100" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "400000" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "100200" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "300" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "400200" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "310000" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "320000" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "330000" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "340000" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "350000" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "360000" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "370000" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "380000" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "390000" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "3A0000" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "3B0000" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "3C0000" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "3D0000" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "3E0000" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "3F0000" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "210100" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "220100" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "230100" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "240100" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "250100" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "260100" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "270100" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "280100" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "290100" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "2A0100" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "2B0100" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "2C0100" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "2D0100" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "2E0100" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "2F0100" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "610000" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "620000" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "630000" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "640000" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "650000" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "660000" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "670000" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "680000" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "690000" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "6A0000" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "6B0000" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "6C0000" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "6D0000" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "6E0000" && TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] != "6F0000") {
						if (TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "100000" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "100" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "400000" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "100200" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "300" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "400200" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "310000" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "320000" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "330000" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "340000" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "350000" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "360000" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "370000" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "380000" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "390000" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "3A0000" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "3B0000" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "3C0000" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "3D0000" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "3E0000" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "3F0000" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "210100" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "220100" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "230100" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "240100" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "250100" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "260100" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "270100" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "280100" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "290100" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "2A0100" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "2B0100" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "2C0100" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "2D0100" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "2E0100" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "2F0100" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "610000" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "620000" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "630000" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "640000" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "650000" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "660000" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "670000" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "680000" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "690000" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "6A0000" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "6B0000" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "6C0000" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "6D0000" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "6E0000" && TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] != "6F0000") {
							if (TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "100000" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "100" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "400000" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "100200" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "300" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "400200" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "310000" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "320000" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "330000" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "340000" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "350000" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "360000" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "370000" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "380000" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "390000" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "3A0000" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "3B0000" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "3C0000" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "3D0000" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "3E0000" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "3F0000" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "210100" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "220100" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "230100" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "240100" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "250100" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "260100" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "270100" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "280100" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "290100" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "2A0100" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "2B0100" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "2C0100" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "2D0100" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "2E0100" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "2F0100" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "610000" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "620000" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "630000" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "640000" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "650000" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "660000" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "670000" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "680000" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "690000" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "6A0000" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "6B0000" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "6C0000" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "6D0000" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "6E0000" && TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] != "6F0000") {
								if (TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "100000" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "100" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "400000" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "100200" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "300" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "400200" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "310000" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "320000" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "330000" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "340000" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "350000" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "360000" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "370000" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "380000" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "390000" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "3A0000" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "3B0000" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "3C0000" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "3D0000" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "3E0000" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "3F0000" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "210100" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "220100" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "230100" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "240100" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "250100" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "260100" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "270100" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "280100" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "290100" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "2A0100" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "2B0100" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "2C0100" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "2D0100" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "2E0100" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "2F0100" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "610000" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "620000" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "630000" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "640000" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "650000" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "660000" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "670000" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "680000" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "690000" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "6A0000" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "6B0000" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "6C0000" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "6D0000" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "6E0000" && TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] != "6F0000") {
									if (TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "100000" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "100" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "400000" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "100200" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "300" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "400200" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "310000" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "320000" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "330000" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "340000" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "350000" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "360000" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "370000" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "380000" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "390000" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "3A0000" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "3B0000" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "3C0000" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "3D0000" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "3E0000" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "3F0000" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "210100" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "220100" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "230100" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "240100" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "250100" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "260100" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "270100" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "280100" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "290100" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "2A0100" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "2B0100" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "2C0100" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "2D0100" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "2E0100" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "2F0100" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "610000" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "620000" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "630000" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "640000" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "650000" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "660000" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "670000" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "680000" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "690000" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "6A0000" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "6B0000" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "6C0000" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "6D0000" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "6E0000" && TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] != "6F0000") {
										Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], [0x0000, 0x0000]);
									} else {
										Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], [0x0000, TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "100000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "100200" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "310000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "320000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "330000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "340000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "350000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "360000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "370000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "380000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "390000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "3A0000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "3B0000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "3C0000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "3D0000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "3E0000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "3F0000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "210100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "220100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "230100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "240100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "250100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "260100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "270100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "280100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "290100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "2A0100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "2B0100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "2C0100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "2D0100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "2E0100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "2F0100" ? 0x0001 : TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "400000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "400200" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "610000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "620000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "630000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "640000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "650000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "660000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "670000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "680000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "690000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "6A0000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "6B0000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "6C0000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "6D0000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "6E0000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "6F0000" ? 0x0002 : 0x0000], TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "300" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "210100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "220100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "230100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "240100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "250100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "260100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "270100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "280100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "290100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "2A0100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "2B0100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "2C0100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "2D0100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "2E0100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "2F0100" ? 0 : undefined);
									}
								} else {
									Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], [0x0000, TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "100000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "100200" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "310000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "320000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "330000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "340000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "350000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "360000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "370000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "380000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "390000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "3A0000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "3B0000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "3C0000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "3D0000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "3E0000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "3F0000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "210100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "220100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "230100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "240100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "250100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "260100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "270100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "280100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "290100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "2A0100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "2B0100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "2C0100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "2D0100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "2E0100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "2F0100" ? 0x0001 : TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "400000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "400200" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "610000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "620000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "630000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "640000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "650000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "660000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "670000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "680000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "690000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "6A0000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "6B0000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "6C0000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "6D0000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "6E0000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "6F0000" ? 0x0002 : 0x0000], TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "300" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "210100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "220100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "230100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "240100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "250100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "260100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "270100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "280100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "290100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "2A0100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "2B0100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "2C0100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "2D0100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "2E0100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "2F0100" ? 1 : undefined);
								}
							} else {
								Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], [0x0000, TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "100000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "100200" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "310000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "320000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "330000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "340000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "350000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "360000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "370000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "380000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "390000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "3A0000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "3B0000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "3C0000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "3D0000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "3E0000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "3F0000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "210100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "220100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "230100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "240100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "250100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "260100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "270100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "280100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "290100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "2A0100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "2B0100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "2C0100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "2D0100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "2E0100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "2F0100" ? 0x0001 : TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "400000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "400200" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "610000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "620000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "630000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "640000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "650000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "660000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "670000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "680000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "690000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "6A0000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "6B0000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "6C0000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "6D0000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "6E0000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "6F0000" ? 0x0002 : 0x0000], TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "300" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "210100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "220100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "230100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "240100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "250100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "260100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "270100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "280100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "290100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "2A0100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "2B0100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "2C0100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "2D0100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "2E0100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "2F0100" ? 2 : undefined);
							}
						} else {
							Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], [TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "100000" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "100200" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "310000" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "320000" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "330000" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "340000" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "350000" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "360000" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "370000" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "380000" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "390000" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "3A0000" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "3B0000" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "3C0000" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "3D0000" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "3E0000" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "3F0000" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "100" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "210100" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "220100" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "230100" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "240100" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "250100" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "260100" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "270100" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "280100" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "290100" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "2A0100" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "2B0100" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "2C0100" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "2D0100" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "2E0100" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "2F0100" ? 0x0001 : TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "400000" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "400200" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "610000" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "620000" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "630000" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "640000" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "650000" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "660000" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "670000" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "680000" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "690000" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "6A0000" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "6B0000" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "6C0000" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "6D0000" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "6E0000" || TipSettingData[MapData[0][CharaPos[1] - 1][CharaPos[0]]] == "6F0000" ? 0x0002 : 0x0000, TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "100000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "100200" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "310000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "320000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "330000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "340000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "350000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "360000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "370000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "380000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "390000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "3A0000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "3B0000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "3C0000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "3D0000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "3E0000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "3F0000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "210100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "220100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "230100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "240100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "250100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "260100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "270100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "280100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "290100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "2A0100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "2B0100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "2C0100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "2D0100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "2E0100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "2F0100" ? 0x0001 : TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "400000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "400200" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "610000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "620000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "630000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "640000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "650000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "660000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "670000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "680000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "690000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "6A0000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "6B0000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "6C0000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "6D0000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "6E0000" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "6F0000" ? 0x0002 : 0x0000], TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "300" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "210100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "220100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "230100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "240100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "250100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "260100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "270100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "280100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "290100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "2A0100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "2B0100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "2C0100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "2D0100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "2E0100" || TipSettingData[MapData[0][CharaPos[1]][CharaPos[0]]] == "2F0100" ? 0 : undefined);
						}
					} else {
						Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], [TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "100000" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "100200" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "310000" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "320000" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "330000" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "340000" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "350000" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "360000" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "370000" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "380000" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "390000" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "3A0000" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "3B0000" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "3C0000" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "3D0000" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "3E0000" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "3F0000" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "100" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "210100" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "220100" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "230100" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "240100" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "250100" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "260100" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "270100" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "280100" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "290100" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "2A0100" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "2B0100" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "2C0100" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "2D0100" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "2E0100" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "2F0100" ? 0x0001 : TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "400000" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "400200" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "610000" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "620000" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "630000" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "640000" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "650000" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "660000" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "670000" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "680000" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "690000" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "6A0000" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "6B0000" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "6C0000" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "6D0000" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "6E0000" || TipSettingData[MapData[1][CharaPos[1] - 1][CharaPos[0]]] == "6F0000" ? 0x0002 : 0x0000, TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "100000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "100200" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "310000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "320000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "330000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "340000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "350000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "360000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "370000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "380000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "390000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "3A0000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "3B0000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "3C0000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "3D0000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "3E0000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "3F0000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "210100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "220100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "230100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "240100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "250100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "260100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "270100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "280100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "290100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "2A0100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "2B0100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "2C0100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "2D0100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "2E0100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "2F0100" ? 0x0001 : TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "400000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "400200" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "610000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "620000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "630000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "640000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "650000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "660000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "670000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "680000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "690000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "6A0000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "6B0000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "6C0000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "6D0000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "6E0000" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "6F0000" ? 0x0002 : 0x0000], TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "300" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "210100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "220100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "230100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "240100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "250100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "260100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "270100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "280100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "290100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "2A0100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "2B0100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "2C0100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "2D0100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "2E0100" || TipSettingData[MapData[1][CharaPos[1]][CharaPos[0]]] == "2F0100" ? 1 : undefined);
					}
				} else {
					Character.Warp(CharacterID, Direction, [CharaPos[0], CharaPos[1]], [TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "100000" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "100200" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "310000" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "320000" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "330000" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "340000" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "350000" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "360000" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "370000" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "380000" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "390000" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "3A0000" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "3B0000" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "3C0000" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "3D0000" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "3E0000" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "3F0000" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "100" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "210100" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "220100" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "230100" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "240100" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "250100" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "260100" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "270100" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "280100" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "290100" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "2A0100" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "2B0100" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "2C0100" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "2D0100" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "2E0100" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "2F0100" ? 0x0001 : TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "400000" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "400200" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "610000" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "620000" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "630000" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "640000" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "650000" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "660000" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "670000" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "680000" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "690000" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "6A0000" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "6B0000" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "6C0000" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "6D0000" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "6E0000" || TipSettingData[MapData[2][CharaPos[1] - 1][CharaPos[0]]] == "6F0000" ? 0x0002 : 0x0000, TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "100000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "100200" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "310000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "320000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "330000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "340000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "350000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "360000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "370000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "380000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "390000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "3A0000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "3B0000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "3C0000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "3D0000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "3E0000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "3F0000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "210100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "220100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "230100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "240100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "250100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "260100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "270100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "280100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "290100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "2A0100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "2B0100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "2C0100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "2D0100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "2E0100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "2F0100" ? 0x0001 : TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "400000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "400200" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "610000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "620000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "630000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "640000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "650000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "660000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "670000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "680000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "690000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "6A0000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "6B0000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "6C0000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "6D0000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "6E0000" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "6F0000" ? 0x0002 : 0x0000], TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "300" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "210100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "220100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "230100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "240100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "250100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "260100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "270100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "280100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "290100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "2A0100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "2B0100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "2C0100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "2D0100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "2E0100" || TipSettingData[MapData[2][CharaPos[1]][CharaPos[0]]] == "2F0100" ? 2 : undefined);
				}
			}
			
			//キーボード関数
			R.THIS.PadFunc = function (Event) {
				Event.preventDefault();
				R.THIS.MoveFuc(Event.keyCode);
			}
			
			document.addEventListener("keydown", R.THIS.PadFunc);
			sessionStorage.setItem("GamePad", "Keyboard");
		},
		
		Disable: function () {
			if (sessionStorage.getItem("GamePad") != null) {
				document.removeEventListener("keydown", R.THIS.PadFunc, false);
				sessionStorage.removeItem("GamePad");
			}
		}
	}
	
	/*/
	 *##################################################
	 *#【MsgBox】
	 *#メッセージダイアログを表示
	 *#
	 *#≪引数≫
	 *#Pos : R.POS型
	 *#Speed : R.SPEED型
	 *#Color : R.COLOR型
	 *#Content : String型
	 *#ClickFuc : Function型
	 *##################################################
	/*/
	this.MsgBox = function (Pos, Speed, Color, Content, FaceURL, ClickFuc) {
		this.GamePad.Disable();
		
		var Dialog = document.createElement("RPGHelper-MsgBox");
			Dialog.style.position = "Absolute";
			Dialog.style.width = (this.Canvas.attributes["width"].value - 10) + "px";
			Dialog.style.color = Color;
			
			this.Canvas.appendChild(Dialog);
			
		var Texts = document.createElement("Span");
			Texts.style.position = "Absolute";
			
			Dialog.appendChild(Texts);
			
		if (FaceURL != "") {
			var Face = new Image();
				Face.src = FaceURL;
				
				Dialog.insertBefore(Face, Dialog.children[0]);
				
			if (Pos == R.POS.CENTER) {
				Face.style.height = "50%";
			} else {
				Face.style.height = "100%";
			}
		}
		
		switch (Pos) {
			case R.POS.TOP:
				Dialog.style.top = "0px";
				Dialog.style.height = (this.Canvas.style.height.split("px")[0] / 4 - 5) + "px"; //【縦横500pxの時】500px / 4 - 5px[ボーダー幅] = 120px
				break;
				
			case R.POS.BOTTOM:
				Dialog.style.top = (this.Canvas.style.height.split("px")[0] - (this.Canvas.style.height.split("px")[0] / 4)) + "px"; //【縦横500pxの時】500px - (500px / 4) = 500px - 125px = 375px
				Dialog.style.height = (this.Canvas.style.height.split("px")[0] / 4 - 10) + "px"; //【縦横500pxの時】500px / 4 - 10px[ボーダー幅 * 2] = 115px
				break;
				
			case R.POS.CENTER:
				Dialog.style.top = ((this.Canvas.style.height.split("px")[0] / 2) - (this.Canvas.style.height.split("px")[0] / 4)) + "px"; //【縦横500pxの時】(500px / 2) - (500px - 2) = 250px - 125px = 125px
				Dialog.style.height = (this.Canvas.style.height.split("px")[0] / 2 - 5) + "px"; //【縦横500pxの時】500px / 2 - 5 = 245px
				break;
		}
		
		if (typeof Content == "string") {
			var Counter = 0;
			
			Content = Content.replace(/\n/g, "<Br>");
			
			var Timer = setInterval(function () {
				if (Counter <= Content.length) {
					Texts.innerHTML = Content.substr(0, Counter);
					Counter++;
				} else {
					clearInterval(Timer);
				}
			}, Speed);
		}
		
		Dialog.onclick = (function (Canvas, Sound) {
			return function () {
				Canvas.removeChild(Dialog);
				
				Sound.PlaySE("Click");
				ClickFuc();
			}
		})(this.Canvas, this.Sound);
		
		return Dialog;
	}
	
	this.Menu = {
		Canvas: this.Canvas,
		Sound: this.Sound,
		
		/*/
		 *##################################################
		 *#【MenuPanel】
		 *#メニュー画面を表示
		 *#
		 *#≪引数≫
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
		 *#【MenuItem】
		 *#メニュー画面用アイテムを表示
		 *#
		 *#≪引数≫
		 *#ParentPanel : Element型
		 *#Size : Array型
		 *#|=> [0] : String型(00 ～ 99, ^^)
		 *#|=> [1] : String型(00 ～ 99, ^^)
		 *#
		 *#Color : R.COLOR型
		 *#Content : String型
		 *#ClickFuc : Function型
		 *##################################################
		/*/
		MenuItem: function (ParentPanel, Size, Color, Content, ClickFuc) {
			var Dialog = document.createElement("RPGHelper-Menu-MenuItem");
				Dialog.style.position = "Absolute";
				Dialog.style.color = Color;
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
				
				Dialog.onclick = (function (Sound) {
					return function () {
						Sound.PlaySE("Click");
						ClickFuc();
					}
				})(this.Sound);
				
			ParentPanel.appendChild(Dialog);
			
			if ((Dialog.style.width.split("px")[0] - 0) + (Dialog.style.left.split("px")[0] - 0) + (Dialog.parentElement.style.left.split("px")[0] - 0) < this.Canvas.style.width.split("px")[0] - 5) {
			} else {
				Dialog.style.width = Dialog.style.width.split("px")[0] - 5 + "px";
			}
			
			if ((Dialog.style.height.split("px")[0] - 0) + (Dialog.style.top.split("px")[0] - 0) + (Dialog.parentElement.style.top.split("px")[0] - 0) < this.Canvas.style.height.split("px")[0] - 5) {
			} else {
				Dialog.style.height = Dialog.style.height.split("px")[0] - 5 + "px";
			}
			
			Dialog.style.lineHeight = Dialog.style.height;
			
			return Dialog;
		},
		
		/*/
		 *##################################################
		 *#【MenuMsgBox】
		 *#メニュー画面用テキスト欄を表示
		 *#
		 *#≪引数≫
		 *#ParentPanel : Element型
		 *#Size : Array型
		 *#|=> [0] : String型(00 ～ 99, ^^)
		 *#|=> [1] : String型(00 ～ 99, ^^)
		 *#
		 *#Color : R.COLOR型
		 *#Content : String型
		 *##################################################
		/*/
		MenuMsgBox: function (ParentPanel, Size, Color, Content) {
			var Dialog = document.createElement("RPGHelper-Menu-MenuMsgBox");
				Dialog.style.position = "Absolute";
				Dialog.style.color = Color;
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
				
			ParentPanel.appendChild(Dialog);
			
			if ((Dialog.style.width.split("px")[0] - 0) + (Dialog.style.left.split("px")[0] - 0) + (Dialog.parentElement.style.left.split("px")[0] - 0) < this.Canvas.style.width.split("px")[0] - 5) {
			} else {
				Dialog.style.width = Dialog.style.width.split("px")[0] - 5 + "px";
			}
			
			if ((Dialog.style.height.split("px")[0] - 0) + (Dialog.style.top.split("px")[0] - 0) + (Dialog.parentElement.style.top.split("px")[0] - 0) < this.Canvas.style.height.split("px")[0] - 5) {
			} else {
				Dialog.style.height = Dialog.style.height.split("px")[0] - 5 + "px";
			}
			
			Dialog.style.lineHeight = Dialog.style.height;
			
			return Dialog;
		},
		
		/*/
		 *##################################################
		 *#【MenuTextArea】
		 *#メニュー画面用入力欄を表示
		 *#
		 *#≪引数≫
		 *#ParentPanel : Element型
		 *#Size : Array型
		 *#|=> [0] : String型(00 ～ 99, ^^)
		 *#|=> [1] : String型(00 ～ 99, ^^)
		 *#
		 *#Color : R.COLOR型
		 *#HintMsg : String型
		 *##################################################
		/*/
		MenuTextArea: function (ParentPanel, Size, Color, HintMsg) {
			var Dialog = document.createElement("TextArea");
				Dialog.setAttribute("Class", "RPGHelper-Menu-MenuTextArea");
				Dialog.style.position = "Absolute";
				Dialog.style.color = Color;
				
				Dialog.setAttribute("PlaceHolder", HintMsg);
				
				if (Size[0].substr(0, 1) != "^" && Size[1].substr(0, 1) != "^" && Size[0].substr(1, 1) != "^" && Size[1].substr(1, 1) != "^") {
					Dialog.style.width = (this.Canvas.style.width.split("px")[0] / 10) * (Math.max(Size[0].substr(0, 1), Size[1].substr(0, 1)) - Math.min(Size[0].substr(0, 1), Size[1].substr(0, 1))) - 7 + "px";
					Dialog.style.height = (this.Canvas.style.height.split("px")[0] / 10) * (Math.max(Size[0].substr(1, 1), Size[1].substr(1, 1)) - Math.min(Size[0].substr(1, 1), Size[1].substr(1, 1))) - 5 + "px";
					Dialog.style.top = (this.Canvas.style.height.split("px")[0] / 10) * (Math.min(Size[0].substr(1, 1), Size[1].substr(1, 1))) + "px";
					Dialog.style.left = (this.Canvas.style.width.split("px")[0] / 10) * (Math.min(Size[0].substr(0, 1), Size[1].substr(0, 1))) + "px";
				} else if (Size[1].substr(0, 1) != "^" && Size[1].substr(1, 1) == "^") {
					Dialog.style.width = (this.Canvas.style.width.split("px")[0] / 10) * (Math.max(Size[0].substr(0, 1), Size[1].substr(0, 1)) - Math.min(Size[0].substr(0, 1), Size[1].substr(0, 1))) - 7 + "px";
					Dialog.style.height = (this.Canvas.style.height.split("px")[0] / 10) * (10 - Size[0].substr(1, 1)) - 10 + "px";
					Dialog.style.top = this.Canvas.style.height.split("px")[0] - (this.Canvas.style.height.split("px")[0] / 10) * (10 - Size[0].substr(1, 1)) + "px";
					Dialog.style.left = (this.Canvas.style.width.split("px")[0] / 10) * (Math.min(Size[0].substr(0, 1), Size[1].substr(0, 1))) + "px";
				} else if (Size[1].substr(0, 1) == "^" && Size[1].substr(1, 1) != "^") {
					Dialog.style.width = (this.Canvas.style.width.split("px")[0] / 10) * (10 - Size[0].substr(0, 1)) - 12 + "px";
					Dialog.style.height = (this.Canvas.style.height.split("px")[0] / 10) * (Math.max(Size[0].substr(1, 1), Size[1].substr(1, 1)) - Math.min(Size[0].substr(1, 1), Size[1].substr(1, 1))) - 5 + "px";
					Dialog.style.top = (this.Canvas.style.height.split("px")[0] / 10) * (Math.min(Size[0].substr(1, 1), Size[1].substr(1, 1))) + "px";
					Dialog.style.left = this.Canvas.style.width.split("px")[0] - (this.Canvas.style.width.split("px")[0] / 10) * (10 - Size[0].substr(0, 1)) + "px";
				} else if (Size[1].substr(0, 1) == "^" && Size[1].substr(1, 1) == "^") {
					Dialog.style.width = (this.Canvas.style.width.split("px")[0] / 10) * (10 - Size[0].substr(0, 1)) - 12 + "px";
					Dialog.style.height = (this.Canvas.style.height.split("px")[0] / 10) * (10 - Size[0].substr(1, 1)) - 10 + "px";
					Dialog.style.top = this.Canvas.style.height.split("px")[0] - (this.Canvas.style.height.split("px")[0] / 10) * (10 - Size[0].substr(1, 1)) + "px";
					Dialog.style.left = this.Canvas.style.width.split("px")[0] - (this.Canvas.style.width.split("px")[0] / 10) * (10 - Size[0].substr(0, 1)) + "px";
				}
				
				if (navigator.userAgent.toLowerCase().indexOf("chrome") != -1) {
					Dialog.style.width = (Dialog.style.width.split("px")[0] - 2) + "px";
					Dialog.style.height = (Dialog.style.height.split("px")[0] - 4) + "px";
				}
				
				Dialog.style.maxWidth = Dialog.style.width;
				Dialog.style.minWidth = Dialog.style.width;
				Dialog.style.maxHeight = Dialog.style.height;
				Dialog.style.minHeight = Dialog.style.height;
				
			ParentPanel.appendChild(Dialog);
			
			if ((Dialog.style.width.split("px")[0] - 0) + (Dialog.style.left.split("px")[0] - 0) + (Dialog.parentElement.style.left.split("px")[0] - 0) < this.Canvas.style.width.split("px")[0] - 5) {
			} else {
				Dialog.style.width = Dialog.style.width.split("px")[0] - 5 + "px";
			}
			
			if ((Dialog.style.height.split("px")[0] - 0) + (Dialog.style.top.split("px")[0] - 0) + (Dialog.parentElement.style.top.split("px")[0] - 0) < this.Canvas.style.height.split("px")[0] - 5) {
			} else {
				Dialog.style.height = Dialog.style.height.split("px")[0] - 5 + "px";
			}
			
			return Dialog;
		}
	}
	
	this.Util = {
		GetImageData: function (URL) {
			var Img = new Image();
				Img.src = URL;
				
			return Img;
		},
		
		DrawTip: function (URL, TipID, Position, Fucs) {
			var TipImg = new Image();
			
			var TipLoader = new XMLHttpRequest();
				TipLoader.open("GET", URL, true);
				TipLoader.responseType = "arraybuffer";
				
				TipLoader.onload = function () {
					TipData = URL.createObjectURL(
						new Blob(
							[TipLoader.response],
							{type: "image/png"}
						)
					);
					
					TipImg.src = TipData;
				}
				
				TipLoader.send(null);
				
			var TipCanvas = document.createElement("Canvas");
				TipCanvas.setAttribute("Class", "DrawedTip");
				TipCanvas.width = this.Canvas.style.width.split("px")[0];
				TipCanvas.height = this.Canvas.style.height.split("px")[0];
				TipCanvas.style.position = "Absolute";
				
			TipImg.onload = function () {
				var Ctx = TipCanvas.getContext("2d");
					Ctx.drawImage(TipImg, 16 * (TipID % 8), 16 * (Math.floor(TipID / 8)), 16, 16, 16 * Position[0], 16 * Position[1], 16, 16);
			}
			
			return [TipCanvas];
		},
		
		DrawCharacter: function (URL, Direction, Position, VisibleTip, AppendLayerID, Type, Fuc) {
			var CharaImg = new Image();
			
			var CharaLoader = new XMLHttpRequest();
				CharaLoader.open("GET", URL, true);
				CharaLoader.responseType = "arraybuffer";
				
				CharaLoader.onload = function () {
					var Link = URL.createObjectURL(
						new Blob(
							[CharaLoader.response], 
							{"type": "image/png"}
						)
					);
					
					CharaImg.src = Link;
				}
				
				CharaLoader.send(null);
				
			var CharaCanvas = document.createElement("Canvas");
				CharaCanvas.setAttribute("Class", "DrawedCharacter");
				CharaCanvas.width = this.Canvas.style.width.split("px")[0];
				CharaCanvas.height = this.Canvas.style.height.split("px")[0];
				CharaCanvas.style.position = "Absolute";
				
				if (AppendLayerID == undefined) {
					this.Canvas.appendChild(CharaCanvas);
				} else {
					this.Canvas.insertBefore(CharaCanvas, document.getElementById("Map" + (AppendLayerID + 1)));
				}
				
			CharaImg.onload = function () {
				var Ctx = CharaCanvas.getContext("2d");
				
				if (VisibleTip == null) {
					Ctx.drawImage(CharaImg, 32, Direction == R.DIRECTION.E ? 96 : Direction == R.DIRECTION.W ? 48 : Direction == R.DIRECTION.S ? 0 : Direction == R.DIRECTION.N ? 144 : 0, 32, 48, 16 * Position[0], 16 * (Position[1] - 1), 16, 32);
				} else if (VisibleTip instanceof Array) {
					if (VisibleTip[0] == 0x0000) {
						if (VisibleTip[1] == 0x0000) {
							Ctx.drawImage(CharaImg, 32, Direction == R.DIRECTION.E ? 96 : Direction == R.DIRECTION.W ? 48 : Direction == R.DIRECTION.S ? 0 : Direction == R.DIRECTION.N ? 144 : 0, 32, 48, 16 * Position[0], 16 * (Position[1] - 1), 16, 32);
						} else if (VisibleTip[1] == 0x0001) {
							Ctx.drawImage(CharaImg, 32, Direction == R.DIRECTION.E ? 96 : Direction == R.DIRECTION.W ? 48 : Direction == R.DIRECTION.S ? 0 : Direction == R.DIRECTION.N ? 144 : 0, 32, 24, 16 * Position[0], 16 * (Position[1] - 1), 16, 16);
						} else if (VisibleTip[1] == 0x0002) {
							Ctx.drawImage(CharaImg, 32, Direction == R.DIRECTION.E ? 96 : Direction == R.DIRECTION.W ? 48 : Direction == R.DIRECTION.S ? 0 : Direction == R.DIRECTION.N ? 144 : 0, 32, 24, 16 * Position[0], 16 * (Position[1] - 1), 16, 16);
							
							Ctx.globalAlpha = 0.5;
							Ctx.drawImage(CharaImg, 32, Direction == R.DIRECTION.E ? 120 : Direction == R.DIRECTION.W ? 72 : Direction == R.DIRECTION.S ? 24 : Direction == R.DIRECTION.N ? 168 : 24, 32, 24, 16 * Position[0], 16 * (Position[1]), 16, 16);
						}
					} else if (VisibleTip[0] == 0x0001) {
						if (VisibleTip[1] == 0x0000) {
							Ctx.drawImage(CharaImg, 32, Direction == R.DIRECTION.E ? 120 : Direction == R.DIRECTION.W ? 72 : Direction == R.DIRECTION.S ? 24 : Direction == R.DIRECTION.N ? 168 : 24, 32, 24, 16 * Position[0], 16 * (Position[1]), 16, 16);
						} else if (VisibleTip[1] == 0x0001) {
							
						} else if (VisibleTip[1] == 0x0002) {
							Ctx.globalAlpha = 0.5;
							Ctx.drawImage(CharaImg, 32, Direction == R.DIRECTION.E ? 120 : Direction == R.DIRECTION.W ? 72 : Direction == R.DIRECTION.S ? 24 : Direction == R.DIRECTION.N ? 168 : 24, 32, 24, 16 * Position[0], 16 * (Position[1]), 16, 16);
						}
					} else if (VisibleTip[0] == 0x0002) {
						if (VisibleTip[1] == 0x0000) {
							Ctx.globalAlpha = 0.5;
							Ctx.drawImage(CharaImg, 32, Direction == R.DIRECTION.E ? 96 : Direction == R.DIRECTION.W ? 48 : Direction == R.DIRECTION.S ? 0 : Direction == R.DIRECTION.N ? 144 : 0, 32, 24, 16 * Position[0], 16 * (Position[1] - 1), 16, 16);
							
							Ctx.globalAlpha = 1.0;
							Ctx.drawImage(CharaImg, 32, Direction == R.DIRECTION.E ? 120 : Direction == R.DIRECTION.W ? 72 : Direction == R.DIRECTION.S ? 24 : Direction == R.DIRECTION.N ? 168 : 24, 32, 24, 16 * Position[0], 16 * (Position[1]), 16, 16);
						} else if (VisibleTip[1] == 0x0001) {
							Ctx.globalAlpha = 0.5;
							Ctx.drawImage(CharaImg, 32, Direction == R.DIRECTION.E ? 96 : Direction == R.DIRECTION.W ? 48 : Direction == R.DIRECTION.S ? 0 : Direction == R.DIRECTION.N ? 144 : 0, 32, 24, 16 * Position[0], 16 * (Position[1] - 1), 16, 16);
						} else if (VisibleTip[1] == 0x0002) {
							Ctx.globalAlpha = 0.5;
							Ctx.drawImage(CharaImg, 32, Direction == R.DIRECTION.E ? 96 : Direction == R.DIRECTION.W ? 48 : Direction == R.DIRECTION.S ? 0 : Direction == R.DIRECTION.N ? 144 : 0, 32, 48, 16 * Position[0], 16 * (Position[1] - 1), 16, 32);
						}
					}
				}
			}
			
			switch (Type) {
				case 1:
					var X = Position[0], Y = Position[1];
					var Fuc = Fuc;
					
					this.Map.EtcTimers.push(setInterval((function (X, Y, Fuc) {
						return function () {
							if (CharaPos[0] == X && CharaPos[1] == Y) {
								Fuc();
							}
						}
					})(X, Y, Fuc), 10));
					
					break;
					
				case 2:
					var X = Position[0], Y = Position[1];
					var Fuc = Fuc;
					
					(function (X, Y, Fuc) {
						this.Map.Keys.push(function (Event) {
							if (Event.keyCode == Resource.SystemData.Key.Decide) {
								if ((CharaPos[0] == X && CharaPos[1] == Y + 1) && CharaPos[2] == R.DIRECTION.N) {
									Fuc();
								} else if ((CharaPos[0] == X && CharaPos[1] == Y - 1) && CharaPos[2] == R.DIRECTION.S) {
									Fuc();
								} else if ((CharaPos[0] == X + 1 && CharaPos[1] == Y) && CharaPos[2] == R.DIRECTION.W) {
									Fuc();
								} else if ((CharaPos[0] == X - 1 && CharaPos[1] == Y) && CharaPos[2] == R.DIRECTION.E) {
									Fuc();
								}
							}
						});
					})(X, Y, Fuc);
					
					var ID = Keys.length - 1;
					
					document.addEventListener("keydown", Keys[ID]);
					
					break;
			}
		}
	}
	
	this.SystemLoad();
	this.Canvas.style.background = "URL('Image/" + Resource.SystemData.BackGround.Title + "')";
	
	if (Resource.SystemData.BackGround.Dialog != "" && !document.getElementById("RPGHelper-BackStyle")) {
		var BackStyle = document.createElement("Style");
			BackStyle.id = "RPGHelper-BackStyle";
			BackStyle.textContent = 'RPGHelper-MsgBox, RPGHelper-Menu-MenuPanel {\n\tBackGround: URL("Image/' + Resource.SystemData.BackGround.Dialog + '");\n}';
			
			document.head.appendChild(BackStyle);
	}
	
	window.AudioContext = window.AudioContext || window.webkitAudioContext;
}