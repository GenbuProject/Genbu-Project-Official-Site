var TipDatas = [];
var MapDatas = [];

function Load(File) {
	var Reader = new FileReader();
		Reader.readAsArrayBuffer(File);
		
		Reader.onload = function () {
			var Link = URL.createObjectURL(
				new Blob(
					[Reader.result],
					{type: "image/png"}
				)
			);
			
			var Img = new Image();
				Img.src = Link;
				
				Img.onload = function () {
					console.log(Img);
					
					var Cvs = document.getElementById("TipCanvas");
						Cvs.width = Img.width;
						Cvs.height = Img.height;
						
					var Ctx = Cvs.getContext("2d");
						Ctx.clearRect(0, 0, Img.width, Img.height);
						Ctx.drawImage(Img, 0, 0);
						
					for (var i = 0; i < (Img.width / 16) * (Img.height / 16); i++) {
						var X = i % ((Img.width / 16));
						var Y = Math.floor(i / (Img.width / 16));
						
						TipDatas[i] = Ctx.getImageData(X * 16, Y * 16, 16, 16).data;
					}
				}
		}
}

function Create(File) {
	var Reader = new FileReader();
		Reader.readAsArrayBuffer(File);
		
		Reader.onload = function () {
			var MapWidth;
			var MapHeight;
			
			MapWidth = new Uint8Array(Reader.result)[38];
			MapHeight = new Uint8Array(Reader.result)[42];
			
			MapDatas = new Uint8Array(Reader.result, 50);
			
			console.log("Xサイズ：" + MapWidth + ", Yサイズ：" + MapHeight);
			console.log("チップタイル上限値：" + TipDatas.length);
			
			console.log("レイヤー1範囲：" + (MapWidth * MapHeight * 4 * 0) + "～" + (MapWidth * MapHeight * 4 * 1 - 1));
			console.log("レイヤー2範囲：" + (MapWidth * MapHeight * 4 * 1) + "～" + (MapWidth * MapHeight * 4 * 2 - 1));
			console.log("レイヤー3範囲：" + (MapWidth * MapHeight * 4 * 2) + "～" + (MapWidth * MapHeight * 4 * 3 - 1));
		}
}
