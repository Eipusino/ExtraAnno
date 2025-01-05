package lonetrail.content;

import lonetrail.type.unit.*;
import mindustry.gen.*;
import mindustry.type.*;
import lonetrail.annotations.Annotations.*;
import lonetrail.gen.*;

public class LUnitTypes {
    public static @EntityPoint(MechUnit.class) UnitType largeMech;

    public static @EntityDef({Unitc.class, Copterc.class}) UnitType caelifera;
    public static @EntityDef({Unitc.class, Payloadc.class, Legsc.class, BuildingTetherc.class}) UnitType legsMech;

    public static void load() {
        largeMech = new LUnitType("large-mech");

        caelifera = new LUnitType("caelifera");
        legsMech = new LUnitType("legs-mech");
    }
}
