function ElumVillageToElumForest() {
	Map.Show(1, [
		function () {
			Map.Hide();
			Character.Hide();
			
			Map.Show(0, [
				function () {
					ElumVillageToElumForest();
				}
			]);
			
			Character.Warp(0, R.DIRECTION.S, [12, 1]);
		},
		
		function () {
			
		}
	]);
}