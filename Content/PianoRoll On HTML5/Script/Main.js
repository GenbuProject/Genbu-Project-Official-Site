var Ctx = new (window.webkitAudioContext || window.AudioContext)();
var Keys = [], Sounds = [];

var BaseTone = 0;
var ToneType = 0;

var IsPressedPageUp = false;
var IsPressedPageDown = false;

function Play(Code, Level, Type) {
	Sounds[Code] = Ctx.createOscillator();
		Sounds[Code].type = Type == 0 ? "sine" : Type == 1 ? "square" : Type == 2 ? "sawtooth" : Type == 3 ? "triangle" : "sine";
		Sounds[Code].frequency.value = 27.500 * Math.pow(Math.pow(Math.sqrt(2), 1 / (12 / 2)), Level - 1);
		
		Sounds[Code].connect(Ctx.destination);
		Sounds[Code].start(0);
}

function Init() {
	document.addEventListener("keydown", function (Event) {
		switch (Event.keyCode) {
			case 38: //上キー
				BaseTone++;
				document.getElementById("Tone").textContent = BaseTone;
				
				break;
				
			case 40: //下キー
				BaseTone--;
				document.getElementById("Tone").textContent = BaseTone;
				
				break;
				
			case 37: //左キー
				ToneType > 0 ? ToneType-- : ToneType = 3;
				document.getElementById("Type").textContent = ToneType;
				
				break;
				
			case 39: //右キー
				ToneType < 3 ? ToneType++ : ToneType = 0;
				document.getElementById("Type").textContent = ToneType;
				
				break;
				
			case 76: //Lキー
				IsPressedPageUp = true;
				break;
				
			case 190: //.キー
				IsPressedPageDown = true;
				break;
				
			case 90:
				if (!Keys[90]) {
					Keys[90] = true;
					Play(90, 52 + 12 * (BaseTone + (IsPressedPageUp == true ? 1 : 0) - (IsPressedPageDown == true ? 1 : 0)), ToneType);
				}
				
				break;
				
			case 83:
				if (!Keys[83]) {
					Keys[83] = true;
					Play(83, 53 + 12 * (BaseTone + (IsPressedPageUp == true ? 1 : 0) - (IsPressedPageDown == true ? 1 : 0)), ToneType);
				}
				
				break;
				
			case 88:
				if (!Keys[88]) {
					Keys[88] = true;
					Play(88, 54 + 12 * (BaseTone + (IsPressedPageUp == true ? 1 : 0) - (IsPressedPageDown == true ? 1 : 0)), ToneType);
				}
				
				break;
				
			case 68:
				if (!Keys[68]) {
					Keys[68] = true;
					Play(68, 55 + 12 * (BaseTone + (IsPressedPageUp == true ? 1 : 0) - (IsPressedPageDown == true ? 1 : 0)), ToneType);
				}
				
				break;
				
			case 67:
				if (!Keys[67]) {
					Keys[67] = true;
					Play(67, 56 + 12 * (BaseTone + (IsPressedPageUp == true ? 1 : 0) - (IsPressedPageDown == true ? 1 : 0)), ToneType);
				}
				
				break;
				
			case 86:
				if (!Keys[86]) {
					Keys[86] = true;
					Play(86, 57 + 12 * (BaseTone + (IsPressedPageUp == true ? 1 : 0) - (IsPressedPageDown == true ? 1 : 0)), ToneType);
				}
				
				break;
				
			case 71:
				if (!Keys[71]) {
					Keys[71] = true;
					Play(71, 58 + 12 * (BaseTone + (IsPressedPageUp == true ? 1 : 0) - (IsPressedPageDown == true ? 1 : 0)), ToneType);
				}
				
				break;
				
			case 66:
				if (!Keys[66]) {
					Keys[66] = true;
					Play(66, 59 + 12 * (BaseTone + (IsPressedPageUp == true ? 1 : 0) - (IsPressedPageDown == true ? 1 : 0)), ToneType);
				}
				
				break;
				
			case 72:
				if (!Keys[72]) {
					Keys[72] = true;
					Play(72, 60 + 12 * (BaseTone + (IsPressedPageUp == true ? 1 : 0) - (IsPressedPageDown == true ? 1 : 0)), ToneType);
				}
				
				break;
				
			case 78:
				if (!Keys[78]) {
					Keys[78] = true;
					Play(78, 61 + 12 * (BaseTone + (IsPressedPageUp == true ? 1 : 0) - (IsPressedPageDown == true ? 1 : 0)), ToneType);
				}
				
				break;
				
			case 74:
				if (!Keys[74]) {
					Keys[74] = true;
					Play(74, 62 + 12 * (BaseTone + (IsPressedPageUp == true ? 1 : 0) - (IsPressedPageDown == true ? 1 : 0)), ToneType);
				}
				
				break;
				
			case 77:
				if (!Keys[77]) {
					Keys[77] = true;
					Play(77, 63 + 12 * (BaseTone + (IsPressedPageUp == true ? 1 : 0) - (IsPressedPageDown == true ? 1 : 0)), ToneType);
				}
				
				break;
				
			case 188:
				if (!Keys[188]) {
					Keys[188] = true;
					Play(188, 64 + 12 * (BaseTone + (IsPressedPageUp == true ? 1 : 0) - (IsPressedPageDown == true ? 1 : 0)), ToneType);
				}
				
				break;
		}
	});
	
	document.addEventListener("keyup", function (Event) {
		switch (Event.keyCode) {
			case 76: //Lキー
				IsPressedPageUp = false;
				break;
				
			case 190: //.キー
				IsPressedPageDown = false;
				break;
				
			case 90:
				Keys[90] = false;
				Sounds[90].stop();
				
				break;
				
			case 83:
				Keys[83] = false;
				Sounds[83].stop();
				
				break;
				
			case 88:
				Keys[88] = false;
				Sounds[88].stop();
				
				break;
				
			case 68:
				Keys[68] = false;
				Sounds[68].stop();
				
				break;
				
			case 67:
				Keys[67] = false;
				Sounds[67].stop();
				
				break;
				
			case 86:
				Keys[86] = false;
				Sounds[86].stop();
				
				break;
				
			case 71:
				Keys[71] = false;
				Sounds[71].stop();
				
				break;
				
			case 66:
				Keys[66] = false;
				Sounds[66].stop();
				
				break;
				
			case 72:
				Keys[72] = false;
				Sounds[72].stop();
				
				break;
				
			case 78:
				Keys[78] = false;
				Sounds[78].stop();
				
				break;
				
			case 74:
				Keys[74] = false;
				Sounds[74].stop();
				
				break;
				
			case 77:
				Keys[77] = false;
				Sounds[77].stop();
				
				break;
				
			case 188:
				Keys[188] = false;
				Sounds[188].stop();
				
				break;
		}
	});
}