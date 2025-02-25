package lonetrail.fetched;

import arc.util.io.*;
import lonetrail.annotations.Annotations.*;
import mindustry.entities.*;
import mindustry.gen.*;

import static mindustry.Vars.*;

@EntityComponent(write = false)
@EntityBaseComponent
abstract class EntityComp{
    private transient boolean added;
    transient int id = EntityGroup.nextId();

    boolean isAdded(){
        return added;
    }

    void update(){}

    void remove(){
        added = false;
    }

    void add(){
        added = true;
    }

    boolean isLocal(){
        return ((Object)this) == player || ((Object)this) instanceof Unitc u && u.controller() == player;
    }

    boolean isRemote(){
        return ((Object)this) instanceof Unitc u && u.isPlayer() && !isLocal();
    }

    /** Replaced with `this` after code generation. */
    <T extends Entityc> T self(){
        return (T)this;
    }

    <T> T as(){
        return (T)this;
    }

    @InternalImpl
    abstract int classId();

    @InternalImpl
    abstract boolean serialize();

    @MethodPriority(1)
    void read(Reads read){
        afterRead();
    }

    void write(Writes write){

    }

    void afterRead(){

    }

    /** Called after *all* entities are read. */
    void afterAllRead(){

    }
}
