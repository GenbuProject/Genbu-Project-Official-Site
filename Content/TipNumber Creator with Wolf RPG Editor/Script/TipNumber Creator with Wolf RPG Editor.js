var TipDatas = [];
var TipCtx = null;
var MapDatas = [];
var MapCtx = null;

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
					
					TipCvs = Ctx;
				}
		}
}

function Create(File) {
	var Reader = new FileReader();
		Reader.readAsBinaryString(File);
		
		Reader.onload = function () {
			console.log(Reader.result);
		}
}
