package lonetrail.fetched;

import arc.util.*;
import lonetrail.annotations.Annotations.*;

@EntityComponent(write = false)
abstract class TimerComp{
    transient Interval timer = new Interval(6);

    public boolean timer(int index, float time){
        if(Float.isInfinite(time)) return false;
        return timer.get(index, time);
    }
}
