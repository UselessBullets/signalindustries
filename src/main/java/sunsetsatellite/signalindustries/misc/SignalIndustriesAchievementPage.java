package sunsetsatellite.signalindustries.misc;

import net.minecraft.client.Minecraft;
import net.minecraft.client.render.block.model.BlockModel;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.core.Global;
import net.minecraft.core.achievement.Achievement;
import net.minecraft.core.achievement.AchievementList;
import net.minecraft.core.achievement.stat.Stat;
import net.minecraft.core.block.Block;
import net.minecraft.core.util.helper.Side;
import org.lwjgl.opengl.GL11;
import sunsetsatellite.signalindustries.SignalIndustries;
import turniplabs.halplibe.util.achievements.AchievementPage;
import turniplabs.halplibe.util.achievements.GuiAchievements;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Random;

import static sunsetsatellite.signalindustries.SignalIndustries.*;

public class SignalIndustriesAchievementPage extends AchievementPage {

    public static int nextAchievementID = 69;
    public static int offsetY = 5;

    public SignalIndustriesAchievementPage() {
        super("Signal Industries", "achievements.page.signalindustries");
        Field[] achievements = SignalIndustriesAchievementPage.class.getDeclaredFields();
        Arrays.stream(achievements).filter((F)->F.getType().equals(Achievement.class)).forEach((F)->{
            try {
                achievementList.add((Achievement) ((Stat) F.get(null)).registerStat());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

    }

    public static final Achievement INIT = new Achievement(nextAchievementID++, key("init"),0,0-offsetY, rawSignalumCrystal,null);
    public static final Achievement THE_PROTOTYPE = new Achievement(nextAchievementID++, key("thePrototype"),2,0-offsetY, prototypeMachineCore,INIT);
    public static final Achievement FROM_WITHIN = new Achievement(nextAchievementID++, key("fromWithin"),3,-1-offsetY, prototypeExtractor,THE_PROTOTYPE);
    public static final Achievement TRANSFER = new Achievement(nextAchievementID++, key("transfer"),4,1-offsetY, prototypeConduit,THE_PROTOTYPE);
    public static final Achievement BUFFER = new Achievement(nextAchievementID++, key("buffer"),5,-1-offsetY, prototypeEnergyCell,THE_PROTOTYPE);
    public static final Achievement CRUSHER = new Achievement(nextAchievementID++, key("crusher"),6,1-offsetY, prototypeCrusher,THE_PROTOTYPE);
    public static final Achievement ALLOY_SMELTER = new Achievement(nextAchievementID++, key("alloySmelter"),7,-1-offsetY, prototypeAlloySmelter,THE_PROTOTYPE);
    public static final Achievement PLATE_FORMER = new Achievement(nextAchievementID++, key("plateFormer"),8,1-offsetY, prototypePlateFormer,THE_PROTOTYPE);
    public static final Achievement SHINING = new Achievement(nextAchievementID++, key("shining"),9,0-offsetY, signalumCrystal,THE_PROTOTYPE);
    public static final Achievement BASIC = new Achievement(nextAchievementID++, key("basic"),2,2-offsetY, basicMachineCore,SHINING);
    public static final Achievement ROM_CHIP = new Achievement(nextAchievementID++, key("romChip"),-2,6-offsetY, romChipBoost,null);
    public static final Achievement COMBINED = new Achievement(nextAchievementID++, key("combined"),4,3-offsetY, basicCrystalChamber,BASIC);
    public static final Achievement MINER = new Achievement(nextAchievementID++, key("miner"),6,3-offsetY, basicAutomaticMiner,BASIC);
    public static final Achievement PUMP = new Achievement(nextAchievementID++, key("pump"),8,3-offsetY, basicPump,BASIC);
    public static final Achievement HARNESS = new Achievement(nextAchievementID++, key("harness"),3,5-offsetY, signalumPrototypeHarness,BASIC);
    public static final Achievement PROGRAMMER = new Achievement(nextAchievementID++, key("programmer"),5,5-offsetY, basicProgrammer,ROM_CHIP);
    public static final Achievement TRIGGER = new Achievement(nextAchievementID++, key("trigger"),7,5-offsetY, nullTrigger,PROGRAMMER);
    public static final Achievement CHALLENGE = new Achievement(nextAchievementID++, key("challenge"),11,5-offsetY, basicWrathBeacon,BASIC);
    public static final Achievement VICTORY = new Achievement(nextAchievementID++, key("victory"),13,5-offsetY, energyCatalyst,BASIC);
    public static final Achievement RELIC = new Achievement(nextAchievementID++, key("relic"),9,4-offsetY, glowingObsidian,BASIC);
    public static final Achievement KNIGHTS_ALLOY = new Achievement(nextAchievementID++, key("knightAlloy"),9,6-offsetY, reinforcedCrystalAlloyIngot,RELIC);
    public static final Achievement REINFORCED = new Achievement(nextAchievementID++, key("reinforced"),9,8-offsetY, reinforcedMachineCore,KNIGHTS_ALLOY);
    public static final Achievement VICTORY_REINFORCED = new Achievement(nextAchievementID++, key("victory.reinforced"),11,7-offsetY, reinforcedWrathBeacon,REINFORCED);
    public static final Achievement BLADE = new Achievement(nextAchievementID++, key("blade"),7,7-offsetY, signalumSaber,REINFORCED);
    public static final Achievement PULSE = new Achievement(nextAchievementID++, key("pulse"),5,7-offsetY, pulsar,REINFORCED);
    public static final Achievement POWER_SUIT = new Achievement(nextAchievementID++, key("powerSuit"),3,7-offsetY, signalumPowerSuitChestplate,REINFORCED);
    public static final Achievement DILITHIUM = new Achievement(nextAchievementID++, key("dilithium"),7,9-offsetY, dilithiumShard,REINFORCED);
    public static final Achievement DIMENSIONAl = new Achievement(nextAchievementID++, key("dimensional"),11,9-offsetY, dimensionalShard,REINFORCED);
    public static final Achievement WARP_ORB = new Achievement(nextAchievementID++, key("warpOrb"),13,9-offsetY, warpOrb,DIMENSIONAl);
    public static final Achievement ANCHOR = new Achievement(nextAchievementID++, key("anchor"),15,9-offsetY, dimensionalAnchor,WARP_ORB);
    public static final Achievement TELEPORT_SUCCESS = new Achievement(nextAchievementID++, key("teleport.success"),17,8-offsetY, Block.grassRetro,ANCHOR);
    public static final Achievement TELEPORT_FAIL = new Achievement(nextAchievementID++, key("teleport.fail"),17,10-offsetY, realityFabric,ANCHOR);
    public static final Achievement ETERNITY = new Achievement(nextAchievementID++, key("eternity"),19,10-offsetY, rootedFabric,TELEPORT_FAIL);
    public static final Achievement FALSE_ETERNITY = new Achievement(nextAchievementID++, key("falseEternity"),17,12-offsetY, dimensionalShardOre,TELEPORT_FAIL);
    public static final Achievement BOOST = new Achievement(nextAchievementID++, key("boost"),5,9-offsetY, dilithiumBooster,DILITHIUM);
    public static final Achievement WINGS = new Achievement(nextAchievementID++, key("wings"),1,7-offsetY, crystalWings,POWER_SUIT);
    public static final Achievement HORIZONS = new Achievement(nextAchievementID++, key("horizons"),9,10-offsetY, reinforcedEnergyConnector,REINFORCED);
    public static final Achievement REACTOR = new Achievement(nextAchievementID++, key("reactor"),9,12-offsetY, signalumReactorCore,HORIZONS);
    public static final Achievement RISING_ABOVE = new Achievement(nextAchievementID++, key("comingSoon"),9,14-offsetY, awakenedSignalumFragment,REACTOR);


    public static final Achievement BLOOD_MOON = new Achievement(nextAchievementID++, key("bloodMoon"),-2,2-offsetY, monsterShard,null);
    public static final Achievement ECLIPSE = new Achievement(nextAchievementID++, key("eclipse"),-2,4-offsetY, infernalFragment,null);
    public static final Achievement STARFALL = new Achievement(nextAchievementID++, key("starfall"),-2,8-offsetY, Block.lampActive,null);


    @Override
    public void getBackground(GuiAchievements guiAchievements, Random random, int iOffset, int jOffset, int blockX1, int blockY1, int blockX2, int blockY2) {
        for(int row = 0; row * 16 - blockY2 < 155; ++row) {
            float brightness = 0.6F - (float)(blockY1 + row) / 25.0F * 0.3F;
            GL11.glColor4f(brightness, brightness, brightness, 1.0F);

            for(int column = 0; column * 16 - blockX2 < 224; ++column) {
                random.setSeed(1234 + blockX1 + column);
                random.nextInt();
                int randomY = random.nextInt(1 + blockY1 + row) + (blockY1 + row) / 2;
                IconCoordinate texture = this.getTextureFromBlock(Block.stone);
                Block[] oreArray = stoneOres;
                if (randomY >= 28 || blockY1 + row > 24) {
                    oreArray = basaltOres;
                }

                if (randomY <= 37 && blockY1 + row != 35) {
                    if (randomY == 22) {
                        if (random.nextInt(2) == 0) {
                            texture = this.getTextureFromBlock(oreArray[3]);
                        } else {
                            texture = this.getTextureFromBlock(oreArray[4]);
                        }
                    } else if (randomY == 10) {
                        texture = this.getTextureFromBlock(oreArray[1]);
                    } else if (randomY == 8) {
                        texture = this.getTextureFromBlock(oreArray[0]);
                    } else if (randomY > 4) {
                        texture = this.getTextureFromBlock(Block.stone);
                        if (randomY >= 28 || blockY1 + row > 24) {
                            texture = this.getTextureFromBlock(Block.basalt);
                        }
                    } else if (randomY > 0) {
                        texture = this.getTextureFromBlock(Block.stone);
                    }
                } else {
                    texture = this.getTextureFromBlock(realityFabric);
                }


            }
        }

    }

    private static final Block[] stoneOres;
    private static final Block[] basaltOres;

    static {
        stoneOres = new Block[]{Block.oreIronStone};
        basaltOres = new Block[]{Block.oreIronBasalt, signalumOre, dilithiumOre};
    }

    protected IconCoordinate getTextureFromBlock(Block block) {
        return ((BlockModel<?>) BlockModelDispatcher.getInstance().getDispatch(block)).getBlockTextureFromSideAndMetadata(Side.BOTTOM, 0);
    }
}
