package sunsetsatellite.signalindustries.api.impl.btwaila;

import org.slf4j.Logger;
import sunsetsatellite.signalindustries.SignalIndustries;
import sunsetsatellite.signalindustries.api.impl.btwaila.tooltip.BoosterTooltip;
import sunsetsatellite.signalindustries.api.impl.btwaila.tooltip.FluidTooltip;
import sunsetsatellite.signalindustries.api.impl.btwaila.tooltip.MachineTooltip;
import sunsetsatellite.signalindustries.api.impl.btwaila.tooltip.StabilizerTooltip;
import toufoumaster.btwaila.entryplugins.waila.BTWailaCustomTooltipPlugin;
import toufoumaster.btwaila.tooltips.TooltipRegistry;

public class BTWailaSIPlugin implements BTWailaCustomTooltipPlugin {
    @Override
    public void initializePlugin(TooltipRegistry tooltipRegistry, Logger logger) {
        logger.info("Loading tooltips from "+SignalIndustries.MOD_ID+"..");
        tooltipRegistry.register(new FluidTooltip());
        tooltipRegistry.register(new MachineTooltip());
        tooltipRegistry.register(new BoosterTooltip());
        tooltipRegistry.register(new StabilizerTooltip());
    }
}
