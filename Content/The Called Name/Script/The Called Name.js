//普通のセリフ：R.COLOR.WHITE
//感情とか：R.COLOR.CYAN
//イベントとか：R.COLOR.ORANGE
function Main() {
	with (new RPGHelper()) {
		var Flag = {
			IsOpening: false,
		}
		
		var Panel = {
			SideMenu: null,
			StatusPane: null,
			ExplainPane: null,
			ItemPane: null,
			MagicPane: null
		}
		
		Resource.UserData.Flag = {
			Flag0001_Prologue: false,
			Flag0002_FindHero: false,
			Flag0003_OpenForest: false,
			Flag0004: false,
			Flag0005: false,
			Flag0006: false,
			Flag0007: false,
			Flag0008: false,
			Flag0009: false,
			Flag0010: false
		}
		
		function ToLibrary() {
			Map.Hide();
			Character.Hide();
			
			Map.Show(1, [
				function () {
					ToElumVillage();
				},
				
				function () {
					ToElumVillage();
				},
				
				function () {
					if (!Resource.UserData.Flag.Flag0001_Prologue) {
						MsgBox(R.POS.BOTTOM, R.SPEED.NORMAL, R.COLOR.WHITE, Resource.UserData.Character[0]["Name"] + "\n『空気が異様に重々しい...\n　この本は...？』", function () {
							MsgBox(R.POS.BOTTOM, R.SPEED.NORMAL, R.COLOR.WHITE, Resource.UserData.Character[0]["Name"] + "\n「一体何があったんですか...？」", function () {
								MsgBox(R.POS.BOTTOM, R.SPEED.NORMAL, R.COLOR.WHITE, "村人\n「エレンが...闇騎士団に誘拐されたようだ...」", function () {
									MsgBox(R.POS.BOTTOM, R.SPEED.NORMAL, R.COLOR.WHITE, Resource.UserData.Character[0]["Name"] + "\n『闇...騎士団...って...\n　まさかそんな事が...！』", function () {
										MsgBox(R.POS.BOTTOM, R.SPEED.NORMAL, R.COLOR.WHITE, "村人\n「この本を見てみろ...」", function () {
											Effect.BlackOut(1.5, 0, function () {
												Sound.StopBGM();
												
												Map.Hide();
												Character.Hide();
												
												MsgBox(R.POS.BOTTOM, R.SPEED.NORMAL, R.COLOR.CYAN, "白昼ノ空、紅ニ染ムル時、世界ガ闇騎士団ニ呪ワレル時ナリ。\n闇騎士団ヲ打チ砕キタル時、世界ハ救ワレタモウ。\n打チ砕ク者、村カラ一人現レリ。", function () {
													ToLibrary();
													Character.Warp(0, R.DIRECTION.N, [7, 12]);
													
													Effect.ColorOut(1.5, 0, "Black", function () {
														MsgBox(R.POS.BOTTOM, R.SPEED.NORMAL, R.COLOR.WHITE, Resource.UserData.Character[0]["Name"] + "\n「これは...！」", function () {
															MsgBox(R.POS.BOTTOM, R.SPEED.NORMAL, R.COLOR.WHITE, "村人\n「代々伝わる書物だ。\n　この中の誰かが救い主らしい......」", function () {
																MsgBox(R.POS.BOTTOM, R.SPEED.NORMAL, R.COLOR.WHITE, Resource.UserData.Character[0]["Name"] + "\n「ほう( ͡° ͜ʖ ͡°)」", function () {
																	MsgBox(R.POS.BOTTOM, R.SPEED.NORMAL, R.COLOR.WHITE, "村人\n「これから外で勇者探しが行われるらしい...\n　俺は先に行ってるぞ...」", function () {
																		MsgBox(R.POS.BOTTOM, R.SPEED.NORMAL, R.COLOR.CYAN, "勇者探し...か......\n僕も見に行くとするか...", function () {
																			Resource.UserData.Flag.Flag0001_Prologue = true;
																			
																			GamePad.KeyboardType(0);
																		});
																	});
																});
															});
														});
													});
												});
											});
										});
									});
								});
							});
						});
					} else {
						MsgBox(R.POS.BOTTOM, R.SPEED.NORMAL, R.COLOR.WHITE, Resource.UserData.Character[0]["Name"] + "\n『遥か昔から残されてきたとされる書物だ...』", function () {
							GamePad.KeyboardType(0);
						});
					}
				}
			]);
			
			Character.Warp(0, R.DIRECTION.N, [1, 13], 3);
			
			if (!Resource.UserData.Flag.Flag0001_Prologue) {
				Sound.StopBGM();
				Sound.PlayBGM(4);
			}
		}
		
		function ToElumVillage() {
			Map.Hide();
			Character.Hide();
			
			if ((Resource.UserData.Flag.Flag0001_Prologue && Resource.UserData.Flag.Flag0002_FindHero) || (!Resource.UserData.Flag.Flag0001_Prologue && !Resource.UserData.Flag.Flag0002_FindHero)) {
				Map.Show(0, [
					function () {
						ToElumForest();
					},
					
					function () {
						ToLibrary();
					},
					
					function () {
						ToLibrary();
					}
				]);
				
				Character.Warp(0, R.DIRECTION.S, [23, 5]);
			} else {
				Sound.StopBGM();
				
				MsgBox(R.POS.BOTTOM, R.SPEED.NORMAL, R.COLOR.CYAN, "僕は外に出て、ただただ勇者探しの闘いを観覧していた。\nこの中から現れる訳がないと思っていた......", function () {
					Map.Show(2, [null]);
					Character.Warp(0, R.DIRECTION.S, [23, 5]);
					
					Effect.ColorOut(1.5, 0, "Black", function () {
						MsgBox(R.POS.BOTTOM, R.SPEED.NORMAL, R.COLOR.WHITE, "村長\n「これで全員か......？\n　いや......まだ1人戦っておらん者が居るゾ^～」", function () {
							MsgBox(R.POS.BOTTOM, R.SPEED.NORMAL, R.COLOR.CYAN, "その時全員の視線が自分自身に向けられた事に気が付いた。", function () {
								MsgBox(R.POS.BOTTOM, R.SPEED.NORMAL, R.COLOR.WHITE, Resource.UserData.Character[0].Name + "\n『.........えっ...！？』", function () {
									MsgBox(R.POS.BOTTOM, R.SPEED.NORMAL, R.COLOR.WHITE, "エンリコ\n「おいおい村長さんよぉ！\n　まさか" + Resource.UserData.Character[0].Name + "が勇者とか言うんじゃないよな？」", function () {
										MsgBox(R.POS.BOTTOM, R.SPEED.NORMAL, R.COLOR.WHITE, "村長\n「それはそなたの腕を持って試してみるが良いじゃろう...」", function () {
											MsgBox(R.POS.BOTTOM, R.SPEED.NORMAL, R.COLOR.WHITE, "エンリコ\n「おう！\n　臨むところだ！」", function () {
												MsgBox(R.POS.BOTTOM, R.SPEED.NORMAL, R.COLOR.WHITE, Resource.UserData.Character[0].Name + "\n『くそっ......\n　やるしかないか...！』", function () {
													Effect.BlackOut(2.5, 0, function () {
														Map.Hide();
														Character.Hide();
														
														Fight.Init.call(R.THIS, 3, 103, "Elum Village.png", function () {
															Resource.UserData.Flag.Flag0002_FindHero = true;
															
															MsgBox(R.POS.BOTTOM, R.SPEED.NORMAL, R.COLOR.WHITE, "エンリコ\n「何......だと...!?」", function () {
																Sound.StopBGM();
																
																Effect.BlackOut(1.5, 0, function () {
																	Canvas.style.background = "Black";
																	
																	MsgBox(R.POS.BOTTOM, R.SPEED.NORMAL, R.COLOR.WHITE, Resource.UserData.Character[0].Name + "\n『......!?』", function () {
																		MsgBox(R.POS.BOTTOM, R.SPEED.SLOW, R.COLOR.CYAN, "気が付くとエンリコさんが地べたに座り込んでいた。\n僕は戦いに勝ったみたいだ...", function () {
																			MsgBox(R.POS.BOTTOM, R.SPEED.NORMAL, R.COLOR.WHITE, "エンリコ\n「お前...一体何者だ...？」", function () {
																				MsgBox(R.POS.BOTTOM, R.SPEED.NORMAL, R.COLOR.WHITE, Resource.UserData.Character[0].Name + "\n『......えっ...？』", function () {
																					MsgBox(R.POS.BOTTOM, R.SPEED.NORMAL, R.COLOR.WHITE, "エンリコ\n「......ただの奴ではなさそうだな...」", function () {
																						Effect.BlackOut(1.5, 0, function () {
																							MsgBox(R.POS.BOTTOM, R.SPEED.NORMAL, R.COLOR.WHITE, "エンリコ\n「エレンを...頼む...！」", function () {
																								Effect.WhiteOut(2, 0, function () {
																									Canvas.style.background = "White";
																									
																									Effect.BlackOut(1, 0, function () {
																										Canvas.style.background = "Black";
																										
																										MsgBox(R.POS.BOTTOM, R.SPEED.SLOW, R.COLOR.CYAN, "エンリコさんの心からの叫びが\n僕の心に鋭く突き刺さった。", function () {
																											MsgBox(R.POS.BOTTOM, R.SPEED.SLOW, R.COLOR.CYAN, "その叫びが僕の想いを変えた。\nエレンを必ず助けだすと決意した.........", function () {
																												ToElumVillage();
																												Character.Warp(0, R.DIRECTION.S, [12, 2]);
																												
																												Effect.ColorOut(2, 0, "Black", function () {
																													Sound.PlayBGM(11);
																													
																													MsgBox(R.POS.BOTTOM, R.SPEED.NORMAL, R.COLOR.WHITE, "エンリコ\n「頑張れよ！」", function () {
																														MsgBox(R.POS.BOTTOM, R.SPEED.NORMAL, R.COLOR.WHITE, Resource.UserData.Character[0].Name + "\n「分かったンゴ！」", function () {
																															Effect.BlackOut(1, 0, function () {
																																MsgBox(R.POS.BOTTOM, R.SPEED.FAST, R.COLOR.CYAN, "僕はエンリコさんの想いを胸に、\n故郷を後にした......", function () {
																																	ToElumVillage();
																																	Character.Warp(0, R.DIRECTION.S, [12, 2]);
																																	
																																	Effect.ColorOut(1, 0, "Black", function () {
																																		GamePad.KeyboardType(0);
																																	});
																																});
																															});
																														});
																													});
																												});
																											});
																										});
																									});
																								});
																							});
																						});
																					});
																				});
																			});
																		});
																	});
																});
															});
														}, function () {
															MsgBox(R.POS.BOTTOM, R.SPEED.NORMAL, R.COLOR.WHITE, "エンリコ\n『こいつ...出来るっ！』", function () {
														});
													});
												});
											});
										});
									});
								});
							});
						});
					});
				});
			}
		}
		
		function ToElumForest() {
			if (Resource.UserData.Flag.Flag0002_FindHero) {
				Map.Hide();
				Character.Hide();
				
				Map.Show(3, [
					function () {
						Map.Hide();
						Character.Hide();
						
						Map.Show(0, [
							function () {
								ToElumForest();
							},
							
							function () {
								ToLibrary();
							},
							
							function () {
								ToLibrary();
							}
						]);
						
						Character.Warp(0, R.DIRECTION.S, [12, 2]);
					},
					
					function () {
						Map.Hide();
						Character.Hide();
						
						Map.Show(0, [
							function () {
								ToElumForest();
							},
							
							function () {
								ToLibrary();
							},
							
							function () {
								ToLibrary();
							}
						]);
						
						Character.Warp(0, R.DIRECTION.S, [12, 2]);
					},
					
					function () {
						GamePad.Disable();
						
						Resource.UserData.Pos = [3, [6, 10, R.DIRECTION.N]];
						
						MsgBox(R.POS.CENTER, R.SPEED.NORMAL, R.COLOR.WHITE, "セーブします...", function () {
							GamePad.KeyboardType(0);
						});
						
						Save("The Called Name " + new Date().getToday("-") + ".sav");
						Resource.UserData.Pos = undefined;
					},
					
					function () {
						GamePad.Disable();
						
						Resource.UserData.Pos = [3, [7, 10, R.DIRECTION.N]];
						
						MsgBox(R.POS.CENTER, R.SPEED.NORMAL, R.COLOR.WHITE, "セーブします...", function () {
							GamePad.KeyboardType(0);
						});
						
						Save("The Called Name " + new Date().getToday("-") + ".sav");
						Resource.UserData.Pos = undefined;
					}
				]);
				
				Character.Warp(0, R.DIRECTION.N, [2, 14]);
			} else {
				GamePad.Disable();
				Character.Warp(0, R.DIRECTION.S, [12, 2]);
				
				MsgBox(R.POS.BOTTOM, R.SPEED.NORMAL, R.COLOR.WHITE, Resource.UserData.Character[0]["Name"] + "\n「先に蔵書庫に行かなきゃ...！」", function () {
					GamePad.KeyboardType(0);
				});
			}
		}
		
		Sound.Load(function () {
			Sound.PlayBGM(1);
			
			var StartMenu = Menu.MenuPanel(["36", "79"]);
				Menu.MenuItem(StartMenu, ["00", "41"], R.COLOR.WHITE, "NEW GAME", function () {
					Sound.StopBGM();
					
					StartMenu.parentElement.removeChild(StartMenu);
					
					Effect.BlackOut(1.5, 0, function () {
						Canvas.style.background = "Black";
						Sound.PlayBGM(2);
						
						var PropPane = Menu.MenuPanel(["03", "^7"]);
							Menu.MenuMsgBox(PropPane, ["00", "^1"], R.COLOR.WHITE, "主人公の名前を入力して下さい");
							Menu.MenuTextArea(PropPane, ["01", "^2"], R.COLOR.ORANGE, "", true);
							Menu.MenuItem(PropPane, ["02", "^4"], R.COLOR.WHITE, "決定", function () {
								Sound.StopBGM();
								
								PropPane.parentElement.removeChild(PropPane);
								Canvas.style.background = "Black";
								
								Resource.UserData.Character[0] = {
									Name: PropPane.children[1].value,
									Lv: 1,
									
									CharacterTip: "MainCharacter.png",
									CharacterFace: "MainCharacter.png",
									
									MaxHP: 50,
									HP: 50,
									MaxMP: 5,
									MP: 5,
									
									ATK: 5,
									DEF: 5,
									MAG: 5,
									SPD: 5,
									LUK: 5,
									
									EXP: 0
								};
								
								Resource.UserData.Common = {};
								Resource.UserData.Common.Money = 0;
								Resource.UserData.Common.PlayTime = "0sec";
								
								var PlayTimer = setInterval(function () {
									Resource.UserData.Common.PlayTime = parseInt(Resource.UserData.Common.PlayTime.split("ルナ")[0]) + 1 + "ルナ";
								}, 1000);
								
								Effect.BlackOut(2.5, 0, function () {
									Sound.PlayBGM(5);
									
									MsgBox(R.POS.CENTER, R.SPEED.NORMAL, R.COLOR.CYAN, "真っ青な空が広がるある日の朝。\n僕はただ空を眺めていた。", function () {
										MsgBox(R.POS.CENTER, R.SPEED.NORMAL, R.COLOR.CYAN, "僕の名前は" + Resource.UserData.Character[0]["Name"] + "。\nエルム村育ちの普通の子供だ。", function () {
											MsgBox(R.POS.CENTER, R.SPEED.NORMAL, R.COLOR.CYAN, "このエルム村は森に囲まれた場所にあり、\nあまり栄えているとはいえない。\n人口は僕を含めて13人。\nだから他から干渉を受けない場所のはずだった...", function () {
												Map.Show(0, [
													function () {
														ToElumForest();
													},
													
													function () {
														ToLibrary();
													},
													
													function () {
														ToLibrary();
													}
												]);
												
												Character.Warp(0, R.DIRECTION.S, [16, 12]);
												
												Effect.ColorOut(2, 0, "Black", function () {
													Sound.StopBGM();
													Sound.PlaySE(1);
													
													MsgBox(R.POS.BOTTOM, R.SPEED.NORMAL, R.COLOR.WHITE, "？？？\n「た、大変だー！！\n　エレンが居ないぞ！！」", function () {
														MsgBox(R.POS.BOTTOM, R.SPEED.NORMAL, R.COLOR.WHITE, Resource.UserData.Character[0]["Name"] + "\n『...なっ、何だって...!?\n　一体何が起こったんだ.........』", function () {
															Effect.BlackOut(2, 0, function () {
																Map.Hide();
																Character.Hide();
																
																MsgBox(R.POS.BOTTOM, R.SPEED.SLOW, R.COLOR.CYAN, "誰かの叫び声が聞こえてから暫くすると、\n蔵書庫へ集合する旨の放送が流れた...", function () {
																	Map.Show(0, [
																		function () {
																			ToElumForest();
																		},
																		
																		function () {
																			ToLibrary();
																		},
																		
																		function () {
																			ToLibrary();
																		}
																	]);
																	
																	Character.Warp(0, R.DIRECTION.S, [16, 12]);
																	
																	Effect.ColorOut(1.5, 0, "Black", function () {
																		Sound.PlayBGM(6);
																		
																		MsgBox(R.POS.BOTTOM, R.SPEED.SLOW, R.COLOR.WHITE, "エルム村の緊急放送\n「エルム村の村人達は蔵書庫へ集合せよ\n　繰り返す\n　エルム村の村人達は蔵書庫へ集合せよ」", function () {
																			MsgBox(R.POS.BOTTOM, R.SPEED.NORMAL, R.COLOR.CYAN, "周りの村人達が次々と蔵書庫に集まっている...\n僕も蔵書庫へ向かおう...！", function () {
																				MsgBox(R.POS.CENTER, R.SPEED.NORMAL, R.COLOR.ORANGE, "The Called Nameをプレイして頂き、誠にありがとうございます。", function () {
																					MsgBox(R.POS.CENTER, R.SPEED.NORMAL, R.COLOR.ORANGE, "このゲームは RPG Helper(C) を使って製作されています。", function () {
																						MsgBox(R.POS.CENTER, R.SPEED.NORMAL, R.COLOR.ORANGE, "方向キーでキャラクター移動\nZキーで各アクション\nXキーでメニュー\nとなっております。", function () {
																							MsgBox(R.POS.CENTER, R.SPEED.NORMAL, R.COLOR.ORANGE, "それではThe Called Nameの世界観をお楽しみ下さい。", function () {
																								GamePad.KeyboardType(0);
																								
																								document.addEventListener("keydown", function OnPressX(Event) {
																									var MagicPage = 1;
																									
																									switch (Event.keyCode) {
																										case Resource.SystemData.Key.Cancel:
																											if (!Flag.IsOpening) {
																												Flag.IsOpening = true;
																												
																												Panel.SideMenu = Menu.MenuPanel(["00", "36"]);
																													Menu.MenuItem(Panel.SideMenu, ["00", "31"], R.COLOR.WHITE, "アイテム", function () {
																														if (Panel.ItemPane != null) {
																															Panel.ItemPane.parentElement.removeChild(Panel.ItemPane);
																															Panel.ItemPane = null;
																														}
																														
																														Panel.ItemPane = Menu.MenuPanel(["30", "^6"]);
																															Menu.MenuItem(Panel.ItemPane, ["00", "11"], R.COLOR.WHITE, "←", function () {
																																Panel.ItemPane.parentElement.removeChild(Panel.ItemPane);
																																Panel.ItemPane = null;
																															});
																													});
																													
																													Menu.MenuItem(Panel.SideMenu, ["01", "32"], R.COLOR.WHITE, "魔法", function () {
																														if (Panel.MagicPane != null) {
																															Panel.MagicPane.parentElement.removeChild(Panel.MagicPane);
																															Panel.MagicPane = null;
																														}
																														
																														Panel.MagicPane = Menu.MenuPanel(["30", "^6"]);
																															Menu.MenuItem(Panel.MagicPane, ["00", "11"], R.COLOR.WHITE, "←", function () {
																																Panel.MagicPane.parentElement.removeChild(Panel.MagicPane);
																																Panel.MagicPane = null;
																															});
																													});
																													
																													Menu.MenuItem(Panel.SideMenu, ["02", "33"], R.COLOR.WHITE, "装備", function () {
																														
																													});
																													
																													Menu.MenuItem(Panel.SideMenu, ["03", "34"], R.COLOR.WHITE, "合成", function () {
																														
																													});
																													
																													Menu.MenuItem(Panel.SideMenu, ["04", "35"], R.COLOR.WHITE, "イベント", function () {
																														
																													});
																													
																													Menu.MenuItem(Panel.SideMenu, ["05", "36"], R.COLOR.WHITE, "オプション", function () {
																														//Panel.OptionPane = Menu.MenuPanel(["30", "^6"]);
																													});
																													
																												Panel.StatusPane = Menu.MenuPanel(["30", "^6"]);
																													Menu.MenuMsgBox(Panel.StatusPane, ["00", "22"], R.COLOR.WHITE, "").style.background = "URL('CharacterFace/MainCharacter - Normal.png') DarkSlateGray Left Top / 100% 100%";
																													
																													Menu.MenuMsgBox(Panel.StatusPane, ["20", "41"], R.COLOR.WHITE, "HP：");
																													Menu.MenuMsgBox(Panel.StatusPane, ["40", "71"], Resource.UserData.Character[0].HP <= (Resource.UserData.Character[0].MaxHP / 10) ? R.COLOR.RED : Resource.UserData.Character[0].HP <= (Resource.UserData.Character[0].MaxHP / 5) ? R.COLOR.ORANGE : R.COLOR.WHITE, Resource.UserData.Character[0].HP + " / " + Resource.UserData.Character[0].MaxHP);
																													
																													Menu.MenuMsgBox(Panel.StatusPane, ["21", "42"], R.COLOR.WHITE, "MP：");
																													Menu.MenuMsgBox(Panel.StatusPane, ["41", "72"], R.COLOR.WHITE, Resource.UserData.Character[0].MP + " / " + Resource.UserData.Character[0].MaxMP);
																													
																													Menu.MenuMsgBox(Panel.StatusPane, ["02", "23"], R.COLOR.WHITE, "Lv " + Resource.UserData.Character[0].Lv);
																													Menu.MenuMsgBox(Panel.StatusPane, ["22", "73"], R.COLOR.WHITE, "EXP：" + Resource.UserData.Character[0].EXP);
																													Menu.MenuMsgBox(Panel.StatusPane, ["23", "74"], R.COLOR.WHITE, "次のLvまで" + Resource.UserData.Character[0].EXP);
																													
																													Menu.MenuMsgBox(Panel.StatusPane, ["04", "75"], R.COLOR.WHITE, "所持金：" + Resource.UserData.Common.Money + "G(ゴキブリ)");
																													Menu.MenuMsgBox(Panel.StatusPane, ["05", "76"], R.COLOR.WHITE, "プレイ時間：" + Resource.UserData.Common.PlayTime);
																													
																												Panel.ExplainPane = Menu.MenuPanel(["06", "^^"]);
																											} else if (Flag.IsOpening) {
																												Flag.IsOpening = false;
																												
																												Panel.SideMenu.parentElement.removeChild(Panel.SideMenu);
																												Panel.StatusPane.parentElement.removeChild(Panel.StatusPane);
																												Panel.ExplainPane.parentElement.removeChild(Panel.ExplainPane);
																												
																												if (Panel.ItemPane != null) {
																													Panel.ItemPane.parentElement.removeChild(Panel.ItemPane);
																													Panel.ItemPane = null;
																												}
																												
																												if (Panel.MagicPane != null) {
																													Panel.MagicPane.parentElement.removeChild(Panel.MagicPane);
																													Panel.MagicPane = null;
																												}
																											}
																											
																											Sound.PlaySE("Click");
																											
																											break;
																									}
																								});
																							});
																						});
																					});
																				});
																			});
																		});
																	});
																});
															});
														});
													});
												});
											});
										});
									});
								});
							});
					});
				});
				
				Menu.MenuItem(StartMenu, ["01", "42"], R.COLOR.WHITE, "LOAD GAME", function () {
					Load(".sav", function () {
						Effect.BlackOut(2, 0, function () {
							Resource.UserData.Pos[0] == 3 ? ToElumForest() : null;
							Character.Warp(0, Resource.UserData.Pos[1][2], [Resource.UserData.Pos[1][0], Resource.UserData.Pos[1][1]])
							
							Resource.UserData.Pos = undefined;
							
							var PlayTimer = setInterval(function () {
								Resource.UserData.Common.PlayTime = parseInt(Resource.UserData.Common.PlayTime.split("ルナ")[0]) + 1 + "ルナ";
							}, 1000);
							
							GamePad.KeyboardType(0);
						});
					});
				});
				
				Menu.MenuItem(StartMenu, ["02", "43"], R.COLOR.WHITE, "About", function () {
					StartMenu.style.display = "None";
					
					var AboutPane = Menu.MenuPanel(["03", "^7"]);
						Menu.MenuItem(AboutPane, ["", ""], R.COLOR.WHITE, "←", function () {
							StartMenu.style.display = "Block";
							AboutPane.parentElement.removeChild(AboutPane);
						});
						
						Menu.MenuItem(AboutPane, ["01", "^2"], R.COLOR.WHITE, "製作者：プログラマーGenboo", function () {});
						Menu.MenuItem(AboutPane, ["02", "^3"], R.COLOR.WHITE, "最終更新日：2016年09月12日[月]", function () {});
						Menu.MenuItem(AboutPane, ["03", "^4"], R.COLOR.WHITE, "RPGHelperのバージョン：Release 1.3", function () {});
				});
		});
	}
}