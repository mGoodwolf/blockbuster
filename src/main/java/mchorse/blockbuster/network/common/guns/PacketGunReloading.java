package mchorse.blockbuster.network.common.guns;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

/**
 * \* User: Evanechecssss
 * \* https://evanechecssss.github.io
 * \
 */
public class PacketGunReloading implements IMessage
{
    public ItemStack itemStack;
    public int id;

    public PacketGunReloading()
    {}
    
    public PacketGunReloading(ItemStack itemStack, int id)
    {
        this.itemStack =itemStack;
        this.id = id;
    }

    @Override
    public void fromBytes(ByteBuf byteBuf)
    {
        this.itemStack = ByteBufUtils.readItemStack(byteBuf);
        this.id = byteBuf.readInt();
    }

    @Override
    public void toBytes(ByteBuf byteBuf)
    {
        ByteBufUtils.writeItemStack(byteBuf,itemStack);
        byteBuf.writeInt(id);
    }
}