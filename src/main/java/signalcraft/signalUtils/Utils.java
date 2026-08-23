package signalcraft.signalUtils;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class Utils {

    //Thx CovertJaguar
    public static void renderString(String name, double xOffset, double yOffset, double zOffset) {

        if (Minecraft.getMinecraft().currentScreen != null) return;

        RenderManager rm = RenderManager.instance;
        FontRenderer fontrenderer = rm.getFontRenderer();
        float f = 1.6F;
        float f1 = 0.016666668F * f;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)xOffset, (float)yOffset, (float)zOffset);
        GL11.glNormal3f(0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-rm.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(rm.playerViewX, 1.0F, 0.0F, 0.0F);
        GL11.glScalef(-f1, -f1, f1);
        GL11.glDisable(2896);
        GL11.glDepthMask(false);
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        Tessellator tessellator = Tessellator.instance;
        GL11.glDisable(3553);
        tessellator.startDrawingQuads();
        int j = fontrenderer.getStringWidth(name) / 2;
        tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
        tessellator.addVertex((double)(-j - 1), -1.0, 0.0);
        tessellator.addVertex((double)(-j - 1), 8.0, 0.0);
        tessellator.addVertex((double)(j + 1), 8.0, 0.0);
        tessellator.addVertex((double)(j + 1), -1.0, 0.0);
        tessellator.draw();
        GL11.glEnable(3553);
        fontrenderer.drawString(name, -fontrenderer.getStringWidth(name) / 2, 0, 553648127);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        fontrenderer.drawString(name, -fontrenderer.getStringWidth(name) / 2, 0, -1);
        GL11.glEnable(2896);
        GL11.glDisable(3042);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPopMatrix();
    }

    public static boolean isPlayerLookingAtAir(EntityPlayer player, World world) {
        Vec3 playerPosition = Vec3.createVectorHelper(player.posX, player.posY + player.getEyeHeight(), player.posZ);
        Vec3 lookVector = player.getLookVec();
        Vec3 lookPosition = playerPosition.addVector(lookVector.xCoord * 5, lookVector.yCoord * 5, lookVector.zCoord * 5);
        MovingObjectPosition movingObjectPosition = world.rayTraceBlocks(playerPosition, lookPosition, false);

        return movingObjectPosition == null || movingObjectPosition.typeOfHit == MovingObjectPosition.MovingObjectType.MISS;
    }

    public static void addChatMessage(EntityPlayer player, String message) {
        player.addChatMessage(new ChatComponentText(message));
    }

    public static void addChatMessage(EntityPlayer player, IChatComponent component) {
        player.addChatMessage(component);
    }

    // NB: must not use client-only I18n here - this runs on the server (dedicated servers
    // don't have net.minecraft.client classes at all, causing NoClassDefFoundError).
    // ChatComponentTranslation instead sends the key to the client, which localizes it there.
    public static void addLocalizedChatMessage(EntityPlayer player, String key, Object... args) {
        player.addChatMessage(new ChatComponentTranslation(key, args));
    }

}
