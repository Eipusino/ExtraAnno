package lonetrail.type.unit;

import arc.graphics.g2d.*;
import arc.math.*;
import arc.struct.*;
import mindustry.gen.*;
import mindustry.type.*;
import lonetrail.entities.*;
import lonetrail.entities.Rotor.*;
import lonetrail.gen.*;

public class TUnitType extends UnitType {
    public final Seq<Rotor> rotors = new Seq<>(2);
    public float rotorDeathSlowdown = 0.01f;
    public float fallRotateSpeed = 2.5f;

    public TUnitType(String name) {
        super(name);
    }

    @Override
    public void init() {
        super.init();

        Seq<Rotor> mapped = new Seq<>();
        for (Rotor rotor : rotors) {
            mapped.add(rotor);
            if (rotor.mirror) {
                Rotor copy = rotor.copy();
                copy.x *= -1f;
                copy.speed *= -1f;
                copy.shadeSpeed *= -1f;
                copy.rotOffset += 360f / (copy.bladeCount * 2);

                mapped.add(copy);
            }
        }
        rotors.set(mapped);
    }

    @Override
    public void draw(Unit unit) {
        super.draw(unit);

        if (unit instanceof Copterc) drawRotors((Unit & Copterc) unit);
    }

    public <T extends Unit & Copterc> void drawRotors(T unit) {
        applyColor(unit);

        RotorMount[] rotors = unit.rotors();
        for (RotorMount mount : rotors) {
            Rotor rotor = mount.rotor;
            float x = unit.x + Angles.trnsx(unit.rotation - 90f, rotor.x, rotor.y);
            float y = unit.y + Angles.trnsy(unit.rotation - 90f, rotor.x, rotor.y);

            float alpha = Mathf.curve(unit.rotorSpeedScl(), 0.2f, 1f);
            Draw.color(0f, 0f, 0f, rotor.shadowAlpha);
            float rad = 1.2f;
            float size = Math.max(rotor.bladeRegion.width, rotor.bladeRegion.height) * Draw.scl;

            Draw.rect(softShadowRegion, x, y, size * rad * Draw.xscl, size * rad * Draw.yscl);

            Draw.color();
            Draw.alpha(alpha * rotor.ghostAlpha);

            Draw.rect(rotor.bladeGhostRegion, x, y, mount.rotorRot);
            Draw.rect(rotor.bladeShadeRegion, x, y, mount.rotorShadeRot);

            Draw.alpha(1f - alpha * rotor.bladeFade);
            for (int j = 0; j < rotor.bladeCount; j++) {
                Draw.rect(rotor.bladeOutlineRegion, x, y,
                        unit.rotation - 90f
                                + 360f / rotor.bladeCount * j
                                + mount.rotorRot
                );
            }
        }

        for (RotorMount mount : rotors) {
            Rotor rotor = mount.rotor;
            float x = unit.x + Angles.trnsx(unit.rotation - 90f, rotor.x, rotor.y);
            float y = unit.y + Angles.trnsy(unit.rotation - 90f, rotor.x, rotor.y);

            Draw.alpha(1f - Mathf.curve(unit.rotorSpeedScl(), 0.2f, 1f) * rotor.bladeFade);
            for (int j = 0; j < rotor.bladeCount; j++) {
                Draw.rect(rotor.bladeRegion, x, y,
                        unit.rotation - 90f
                                + 360f / rotor.bladeCount * j
                                + mount.rotorRot
                );
            }

            Draw.color();
            Draw.rect(rotor.topRegion, x, y, unit.rotation - 90f);
        }

        Draw.reset();
    }
}
