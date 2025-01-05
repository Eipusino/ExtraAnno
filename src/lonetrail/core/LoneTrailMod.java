package lonetrail.core;

import arc.*;
import arc.struct.*;
import arc.util.*;
import lonetrail.content.*;
import lonetrail.gen.*;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import lonetrail.annotations.Annotations.*;

import static mindustry.Vars.*;

@Load("error")
public class LoneTrailMod extends Mod {
    /** Lists all the mod's classes by their canonical names. Generated at compile-time. */
    public static @ListClasses Seq<String> classes = Seq.with();

    /** Lists all the mod's packages by their canonical names. Generated at compile-time. */
    public static @ListPackages Seq<String> packages = Seq.with();

    public static String[] sun = {
            "sun.misc.Unsafe"
    };

    public static String[] jdkInternal = {
            "jdk.internal.misc.Unsafe",
            "jdk.internal.misc.VM",
            "jdk.internal.reflect.Reflection"
    };

    static {
        for (String s : jdkInternal) {
            try {
                Class<?> a = Class.forName(s);
                Log.info(a);
            } catch (Throwable e) {
                Log.err(e);
            }
        }

        for (String s : sun) {
            try {
                Class<?> a = Class.forName(s);
                Log.info(a);
            } catch (Throwable e) {
                Log.err(e);
            }
        }
    }

    public LoneTrailMod() {
        Events.on(ContentInitEvent.class, e -> {
            if (!headless) {
                Regions.load();
            }
        });
    }

    @Override
    public void loadContent() {
        //LUnitTypes.load();

        //EntityRegister.init();
    }

    @Override
    public void init() {
    }
}
