package lonetrail.fetched;

import lonetrail.annotations.Annotations.*;
import mindustry.gen.*;

@EntityComponent(write = false)
abstract class RotComp implements Entityc{
    @SyncField(false) @SyncLocal float rotation;
}
