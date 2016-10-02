(function () {
	return function (TurnChangeFuc) {
		var ID = 4;
		var Percent = Math.floor(Math.random() * 100 + 1); //1～100
		
		if (1 <= Percent && Percent <= 60) {
			R.THIS.Sound.PlaySE(102);
			
			var Damage = Calc.GetFinalATKDamage([Calc.TypeID.Monster, ID], [Calc.TypeID.Character, 0]);
			Resource.UserData.Character[0].HP > Damage ? Resource.UserData.Character[0].HP -= Damage : Resource.UserData.Character[0].HP = 0;
			
			R.THIS.MsgBox.call(R.THIS, R.POS.BOTTOM, R.SPEED.NORMAL, R.COLOR.WHITE, Resource.SystemData.Monster[ID].Name + "の攻撃！\n" + Resource.SystemData.Monster[ID].Name + "は" + Resource.UserData.Character[0].Name + "に" + Damage + "のダメージを与えた！", "", function () {
				TurnChangeFuc();
			});
		} else {
			R.THIS.Sound.PlaySE(108);
			
			var Damage = Calc.GetFinalMAGDamage([Calc.TypeID.Monster, ID], [Calc.TypeID.Character, 0], 101);
			Resource.UserData.Character[0].HP > Damage ? Resource.UserData.Character[0].HP -= Damage : Resource.UserData.Character[0].HP = 0;
			
			R.THIS.MsgBox.call(R.THIS, R.POS.BOTTOM, R.SPEED.NORMAL, R.COLOR.WHITE, Resource.SystemData.Monster[ID].Name + "はIP開示を実行！\n" + Resource.SystemData.Monster[ID].Name + "は" + Resource.UserData.Character[0].Name + "に" + Damage + "のダメージを与えた！", "", function () {
				TurnChangeFuc();
			});
		}
	}
})();