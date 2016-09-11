package org.mozilla.javascript.v8dtoa;

public class CachedPowers
{
  static final CachedPower[] CACHED_POWERS;
  static final int CACHED_POWERS_SPACING = 8;
  static final int GRISU_CACHE_MAX_DISTANCE = 27;
  static final int GRISU_CACHE_OFFSET = 308;
  static final double kD_1_LOG2_10 = 0.30102999566398114D;
  
  static
  {
    if (!CachedPowers.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      CACHED_POWERS = new CachedPower[] { new CachedPower(-1865951482774665761L, 64449, 65228), new CachedPower(-6093090917745768758L, 64476, 65236), new CachedPower(-38366372719436721L, 64502, 65244), new CachedPower(-4731433901725329908L, 64529, 65252), new CachedPower(-8228041688891786180L, 64556, 65260), new CachedPower(-3219690930897053053L, 64582, 65268), new CachedPower(-7101705404292871755L, 64609, 65276), new CachedPower(-1541319077368263733L, 64635, 65284), new CachedPower(-5851220927660403859L, 64662, 65292), new CachedPower(-9062348037703676329L, 64689, 65300), new CachedPower(-4462904269766699465L, 64715, 65308), new CachedPower(-8027971522334779313L, 64742, 65316), new CachedPower(-2921563150702462265L, 64768, 65324), new CachedPower(-6879582898840692748L, 64795, 65332), new CachedPower(-1210330751515841307L, 64821, 65340), new CachedPower(-5604615407819967858L, 64848, 65348), new CachedPower(-8878612607581929669L, 64875, 65356), new CachedPower(-4189117143640191558L, 64901, 65364), new CachedPower(-7823984217374209642L, 64928, 65372), new CachedPower(-2617598379430861436L, 64954, 65380), new CachedPower(-6653111496142234890L, 64981, 65388), new CachedPower(-872862063775190746L, 65007, 65396), new CachedPower(-5353181642124984136L, 65034, 65404), new CachedPower(-8691279853972075893L, 65061, -124), new CachedPower(-3909969587797413805L, 65087, -116), new CachedPower(-7616003081050118571L, 65114, -108), new CachedPower(-2307682335666372931L, 65140, -100), new CachedPower(-6422206049907525489L, 65167, -92), new CachedPower(-528786136287117932L, 65193, -84), new CachedPower(-5096825099203863601L, 65220, -76), new CachedPower(-8500279345513818773L, 65247, -68), new CachedPower(-3625356651333078602L, 65273, -60), new CachedPower(-7403949918844649556L, 65300, -52), new CachedPower(-1991698500497491194L, 65326, -44), new CachedPower(-6186779746782440749L, 65353, -36), new CachedPower(-177973607073265138L, 65379, -28), new CachedPower(-4835449396872013077L, 65406, -20), new CachedPower(-8305539271883716404L, -103, -12), new CachedPower(-3335171328526686932L, -77, -4), new CachedPower(-7187745005283311616L, -50, 4), new CachedPower(-1669528073709551616L, -24, 12), new CachedPower(-5946744073709551616L, 3, 20), new CachedPower(-9133518327554766460L, 30, 28), new CachedPower(-4568956265895094861L, 56, 36), new CachedPower(-8106986416796705680L, 83, 44), new CachedPower(-3039304518611664792L, 109, 52), new CachedPower(-6967307053960650171L, 136, 60), new CachedPower(-1341049929119499481L, 162, 68), new CachedPower(-5702008784649933400L, 189, 76), new CachedPower(-8951176327949752869L, 216, 84), new CachedPower(-4297245513042813542L, 242, 92), new CachedPower(-7904546130479028392L, 269, 100), new CachedPower(-2737644984756826646L, 295, 108), new CachedPower(-6742553186979055798L, 322, 116), new CachedPower(-1006140569036166267L, 348, 124), new CachedPower(-5452481866653427593L, 375, 132), new CachedPower(-8765264286586255934L, 402, 140), new CachedPower(-4020214983419339459L, 428, 148), new CachedPower(-7698142301602209613L, 455, 156), new CachedPower(-2430079312244744221L, 481, 164), new CachedPower(-6513398903789220827L, 508, 172), new CachedPower(-664674077828931748L, 534, 180), new CachedPower(-5198069505264599346L, 561, 188), new CachedPower(-8575712306248138270L, 588, 196), new CachedPower(-3737760522056206171L, 614, 204), new CachedPower(-7487697328667536417L, 641, 212), new CachedPower(-2116491865831296966L, 667, 220), new CachedPower(-6279758049420528746L, 694, 228), new CachedPower(-316522074587315140L, 720, 236), new CachedPower(-4938676049251384304L, 747, 244), new CachedPower(-8382449121214030822L, 774, 252), new CachedPower(-3449775934753242068L, 800, 260), new CachedPower(-7273132090830278359L, 827, 268), new CachedPower(-1796764746270372707L, 853, 276), new CachedPower(-6041542782089432023L, 880, 284), new CachedPower(-9204148869281624187L, 907, 292), new CachedPower(-4674203974643163859L, 933, 300), new CachedPower(-8185402070463610993L, 960, 308), new CachedPower(-3156152948152813503L, 986, 316), new CachedPower(-7054365918152680535L, 1013, 324), new CachedPower(-1470777745987373095L, 1039, 332), new CachedPower(-5798663540173640085L, 1066, 340) };
      return;
    }
  }
  
  static int getCachedPower(int paramInt1, int paramInt2, int paramInt3, DiyFp paramDiyFp)
  {
    int i = ((int)Math.ceil((64 + (paramInt2 - paramInt1) - 1) * 0.30102999566398114D) + 308 - 1) / 8;
    CachedPower localCachedPower = CACHED_POWERS[(i + 1)];
    paramDiyFp.setF(localCachedPower.significand);
    paramDiyFp.setE(localCachedPower.binaryExponent);
    assert ((paramInt2 <= paramDiyFp.e() + paramInt1) && (paramDiyFp.e() + paramInt1 <= paramInt3));
    return localCachedPower.decimalExponent;
  }
  
  static class CachedPower
  {
    short binaryExponent;
    short decimalExponent;
    long significand;
    
    CachedPower(long paramLong, short paramShort1, short paramShort2)
    {
      this.significand = paramLong;
      this.binaryExponent = paramShort1;
      this.decimalExponent = paramShort2;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\v8dtoa\CachedPowers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */