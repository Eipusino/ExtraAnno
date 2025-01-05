package lonetrail.content;

import mindustry.gen.*;
import mindustry.type.*;
import lonetrail.annotations.Annotations.*;
import lonetrail.gen.*;

public class TUnitTypes {
    public static @EntityDef({Unitc.class, Copterc.class}) UnitType caelifera;
    public static @EntityDef({Unitc.class, Payloadc.class, Legsc.class, BuildingTetherc.class}) UnitType legsMech;

    public static void load() {
        caelifera = new UnitType("caelifera");
        legsMech = new UnitType("legsMech");
    }
}
