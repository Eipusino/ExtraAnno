package lonetrail.fetched;

import lonetrail.annotations.Annotations.*;
import mindustry.gen.*;

@EntityComponent(write = false)
abstract class DrawComp implements Posc{

    float clipSize(){
        return Float.MAX_VALUE;
    }

    void draw(){

    }
}
