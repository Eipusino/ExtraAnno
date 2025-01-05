package lonetrail.entities.comp;

import arc.math.*;
import arc.util.*;
import mindustry.gen.*;
import mindustry.type.*;
import lonetrail.annotations.Annotations.*;
import lonetrail.entities.*;
import lonetrail.entities.Rotor.*;
import lonetrail.type.unit.*;

import static mindustry.Vars.*;

@EntityComponent
abstract class CopterComp implements Unitc {
    transient RotorMount[] rotors;
    transient float rotorSpeedScl = 1f;

    @Import UnitType type;
    @Import boolean dead;
    @Import float health, rotation;
    @Import int id;

    @Override
    public void add() {
        if (type instanceof TUnitType tType) {
            rotors = new RotorMount[tType.rotors.size];
            for (int i = 0; i < rotors.length; i++) {
                Rotor rotor = tType.rotors.get(i);
                rotors[i] = new RotorMount(rotor);
                rotors[i].rotorRot = rotor.rotOffset;
                rotors[i].rotorShadeRot = rotor.rotOffset;
            }
        }
    }

    @Override
    public void update() {
        if (type instanceof TUnitType tType) {
            if (dead || health < 0f) {
                if (!net.client() || isLocal()) rotation += tType.fallRotateSpeed * Mathf.signs[id % 2] * Time.delta;

                rotorSpeedScl = Mathf.lerpDelta(rotorSpeedScl, 0f, tType.rotorDeathSlowdown);
            } else {
                rotorSpeedScl = Mathf.lerpDelta(rotorSpeedScl, 1f, tType.rotorDeathSlowdown);
            }

            for (RotorMount rotor : rotors) {
                rotor.rotorRot += rotor.rotor.speed * rotorSpeedScl * Time.delta;
                rotor.rotorRot %= 360f;

                rotor.rotorShadeRot += rotor.rotor.shadeSpeed * Time.delta;
                rotor.rotorShadeRot %= 360f;
            }
        }
    }
}
