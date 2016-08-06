function View(File) {
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
					
					var Cvs = document.getElementById("Canvas");
						Cvs.width = Img.width;
						Cvs.height = Img.height;
						
					var Ctx = Cvs.getContext("2d");
						Ctx.clearRect(0, 0, Img.width, Img.height);
						Ctx.drawImage(Img, 0, 0);
				}
		}
}
