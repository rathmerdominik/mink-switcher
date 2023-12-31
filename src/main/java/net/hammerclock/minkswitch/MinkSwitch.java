package net.hammerclock.minkswitch;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.FMLNetworkConstants;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.hammerclock.minkswitch.commands.MinkSwitchCommand;

@Mod(MinkSwitch.PROJECT_ID)
public class MinkSwitch {
    public static final Logger LOGGER = LogManager.getLogger(MinkSwitch.PROJECT_ID);
    public static final String PROJECT_ID = "minkswitch";

    public MinkSwitch() {
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST,
                () -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (a, b) -> true));
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(FMLCommonSetupEvent event) {
        LOGGER.info("Mink Switcher started!");
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        new MinkSwitchCommand(event.getServer().getCommands().getDispatcher());
    }
}
