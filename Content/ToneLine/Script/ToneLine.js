var ToneLine = function () {
	TL_this = this;
	
	this.Ctx = new (window.webkitAudioContext || window.AudioContext)();
	this.ToneType = "sine";
	
	this.Play = function (Level, Length) {
		var Audio = this.Ctx.createOscillator();
			Audio.type = this.ToneType;
			Audio.frequency.value = 27.500 * Math.pow(Math.pow(Math.sqrt(2), 1 / (12 / 2)), Level - 1);
			
			Audio.connect(this.Ctx.destination);
			Audio.start(0);
			
		setTimeout(function () {
			Audio.stop();
		}, Length);
	}
	
	this.ChangeToneType = function (ID) {
		this.ToneType = ID == 0 ? "sine" : Type == 1 ? "square" : Type == 2 ? "sawtooth" : Type == 3 ? "triangle" : "sine";
	}
	
	this.Run = function (Ary) {
		var Tones = [],
			Events = [];
			
		for (var i = 0; i < Ary.length; i++) {
			if (Ary[i].Type == "Tone") {
				Tones.push([
					Ary[i].Value.Pos,
					Ary[i].Value.Pitch,
					Ary[i].Value.Length
				]);
			} else {
				Events.push([
					Ary[i].Value.Pos,
					Ary[i].Value.Type,
					Ary[i].Value.Data
				]);
			}
		}
		
		for (let i = 0; i < Tones.length; i++) {
			setTimeout(function () {
				TL_this.Play(Tones[i][1], Tones[i][2]);
			}, Tones[i][0]);
		}
		
		for (let i = 0; i < Events.length; i++) {
			switch (Events[i][1].toLowerCase()) {
				case "tonetype":
					setTimeout(function () {
						TL_this.ChangeToneType(Events[i][2].ID);
					}, Tones[i][0]);
					
					break;
					
				default:
					break;
			}
		}
	}
}

with (new ToneLine()) {
	var Frog = [
		{
			Type: "Event",
			
			Value: {
				Pos: 0,
				Type: "ToneType",
				
				Data: {
					ID: 0
				}
			}
		},
		
		{
			Type: "Tone",
			
			Value: {
				Pos: 0,
				Pitch: 52,
				Length: 500
			}
		},
		
		{
			Type: "Tone",
			
			Value: {
				Pos: 500,
				Pitch: 54,
				Length: 500
			}
		},
		
		{
			Type: "Tone",
			
			Value: {
				Pos: 1000,
				Pitch: 56,
				Length: 1000
			}
		},
		
		{
			Type: "Tone",
			
			Value: {
				Pos: 2000,
				Pitch: 52,
				Length: 500
			}
		},
		
		{
			Type: "Tone",
			
			Value: {
				Pos: 2500,
				Pitch: 54,
				Length: 500
			}
		},
		
		{
			Type: "Tone",
			
			Value: {
				Pos: 3000,
				Pitch: 56,
				Length: 1000
			}
		}
	];
	
	Run(Frog);
}