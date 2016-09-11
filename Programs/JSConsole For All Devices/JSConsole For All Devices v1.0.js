/*/
 *##################################################################
 *JSConsole For All Devices v1.0
 *Copyright (C) 2016 Genbu Project & Genbu Hase All Rights Reversed.
 *##################################################################
/*/

Function.prototype.Debug = function () {
	try {
		this.apply(this, this.arguments);
	} catch (Error) {
		document.getElementById(window.ConsoleID).innerHTML += "<Span Style = 'Color: Red;'>" + Error + "</Span> [エラー行：" + Error.toSource().split(",")[Error.toSource().split(",").length - 1].replace(/[)]/g, "") + "]<Br>";
	}
}

Debug = {
	log: function (Obj) {
		if (typeof Obj != "string" && typeof Obj != "number") {
			var Result = "";
			document.getElementById(window.ConsoleID).innerHTML += Obj + "<Br>";
			
			for (var Key in Obj) {
				Result += "=><Span Style = 'Color: Orange;'>" + Key + "</Span><Br>";
			}
			
			document.getElementById(window.ConsoleID).innerHTML += Result;
		} else {
			document.getElementById(window.ConsoleID).innerHTML += Obj + "<Br>";
		}
	}
}