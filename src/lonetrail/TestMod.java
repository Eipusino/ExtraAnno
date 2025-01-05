package lonetrail;

import arc.struct.*;
import mindustry.mod.*;
import lonetrail.annotations.Annotations.*;

@Load("error")
public class TestMod extends Mod {
    /** Lists all the mod's classes by their canonical names. Generated at compile-time. */
    public static @ListClasses Seq<String> classes = Seq.with();

    /** Lists all the mod's packages by their canonical names. Generated at compile-time. */
    public static @ListPackages Seq<String> packages = Seq.with();
}
