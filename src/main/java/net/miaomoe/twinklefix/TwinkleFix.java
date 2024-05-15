package net.miaomoe.twinklefix;

import net.fabricmc.api.ModInitializer;
import net.miaomoe.twinklefix.trinkets.OverwrittenDefaultTrinkets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TwinkleFix implements ModInitializer {

    public static final Logger logger = LoggerFactory.getLogger(TwinkleFix.class);

    @Override
    public void onInitialize() {
        try {
            OverwrittenDefaultTrinkets.inject();
            logger.info("[TwinkleFix] Success to apply trinkets fix");
        } catch (final Throwable t) {
            logger.warn("[TwinkleFix] Failed to inject trinkets fix", t);
        }
    }
}
