package net.zhuoweizhang.mcpelauncher.api.modpe;

import java.util.HashMap;
import java.util.Map;

public class MobEffect
{
  public static int absorption;
  public static int blindness;
  public static int confusion;
  public static int damageBoost;
  public static int damageResistance;
  public static int digSlowdown;
  public static int digSpeed;
  public static Map<Integer, String> effectIds = new HashMap();
  public static int fireResistance;
  public static int harm;
  public static int heal;
  public static int healthBoost;
  public static int hunger;
  public static int invisibility;
  public static int jump;
  public static int movementSlowdown;
  public static int movementSpeed;
  public static int nightVision;
  public static int poison;
  public static int regeneration;
  public static int saturation;
  public static int waterBreathing;
  public static int weakness;
  public static int wither;
  
  public static void initIds()
  {
    saturation = populate("saturation", "SATURATION");
    absorption = populate("absorption", "ABSORPTION");
    healthBoost = populate("healthBoost", "HEALTH_BOOST");
    wither = populate("wither", "WITHER");
    poison = populate("poison", "POISON");
    weakness = populate("weakness", "WEAKNESS");
    hunger = populate("hunger", "HUNGER");
    nightVision = populate("nightVision", "NIGHT_VISION");
    blindness = populate("blindness", "BLINDNESS");
    invisibility = populate("invisibility", "INVISIBILITY");
    waterBreathing = populate("waterBreathing", "WATER_BREATHING");
    fireResistance = populate("fireResistance", "FIRE_RESISTANCE");
    damageResistance = populate("damageResistance", "DAMAGE_RESISTANCE");
    regeneration = populate("regeneration", "REGENERATION");
    confusion = populate("confusion", "CONFUSION");
    jump = populate("jump", "JUMP");
    harm = populate("harm", "HARM");
    heal = populate("heal", "HEAL");
    damageBoost = populate("damageBoost", "DAMAGE_BOOST");
    digSlowdown = populate("digSlowdown", "DIG_SLOWDOWN");
    digSpeed = populate("digSpeed", "DIG_SPEED");
    movementSlowdown = populate("movementSlowdown", "MOVEMENT_SLOWDOWN");
    movementSpeed = populate("movementSpeed", "MOVEMENT_SPEED");
  }
  
  public static native int nativePopulate(String paramString);
  
  public static int populate(String paramString1, String paramString2)
  {
    int i = nativePopulate("_ZN9MobEffect" + paramString2.length() + paramString2 + "E");
    effectIds.put(Integer.valueOf(i), paramString1);
    return i;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\net\zhuoweizhang\mcpelauncher\api\modpe\MobEffect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */