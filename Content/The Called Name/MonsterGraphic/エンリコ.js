(function () {
	return function () {
		var ID = 2;
		var Percent = Math.floor(Math.random() * 100 + 1); //1～100
		
		if (1 <= Percent && Percent >= 85) {
			var Damage = Calc.GetFinalATKDamage([Calc.TypeID.Monster, ID], [Calc.TypeID.Character, 0]);
			Resource.UserData.Character[0].HP -= Damage;
			
			MsgBox.call(R.THIS, R.POS.BOTTOM, R.SPEED.NORMAL, R.COLOR.WHITE, Resource.SystemData.Monster[ID].Name + "の攻撃！\n" + Resource.SystemData.Monster[ID].Name + "は" + Resource.UserData.Character[0].Name + "に" + Damage + "のダメージを与えた！", function () {
				return;
			});
		}
	}
})();