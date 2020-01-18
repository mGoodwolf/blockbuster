package mchorse.blockbuster.network.server.recording.actions;

import mchorse.blockbuster.network.Dispatcher;
import mchorse.blockbuster.network.common.recording.actions.PacketActionList;
import mchorse.blockbuster.network.common.recording.actions.PacketRequestActions;
import mchorse.blockbuster.recording.RecordUtils;
import mchorse.mclib.network.ServerMessageHandler;
import net.minecraft.entity.player.EntityPlayerMP;

public class ServerHandlerRequestActions extends ServerMessageHandler<PacketRequestActions>
{
    @Override
    public void run(EntityPlayerMP player, PacketRequestActions message)
    {
        Dispatcher.sendTo(new PacketActionList(RecordUtils.getReplays()), player);
    }
}