package signalcraft.gui.gsar;

import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.resources.I18n;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import signalcraft.SignalCraft;
import signalcraft.entities.gsar.signalsBU.TileGSARCrossing;
import signalcraft.entities.levelCrossings.azd.TileAZD99;
import signalcraft.packet.levelCrossings.CPacketUpdateCrossings;
import signalcraft.packet.levelCrossings.SPacketUpdateCrossings;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class GuiModernBarriersGSAR extends GuiScreen {
    private GuiButton doneButton;
    private GuiButton LengthButton;
    private String LenghtText;
    private final TileGSARCrossing thisTileE;
    private GuiTextField textFieldArmDownDelay;


    public GuiModernBarriersGSAR(final TileGSARCrossing thisTileE) {
        Keyboard.enableRepeatEvents(true);
        this.allowUserInput = true;
        this.thisTileE = thisTileE;
    }

    public void initGui() {
        this.loadValuesFromTile();
        this.buttonList.add(this.doneButton = new GuiButton(0, this.width / 2 - 100, this.height / 4 + 140, I18n.format("gui.done")));
        this.buttonList.add(this.LengthButton = new GuiButton(1, this.width / 2 - 120, this.height / 4 - 20, 30, 20, this.LenghtText));
        this.textFieldArmDownDelay = new GuiTextField(this.fontRendererObj, this.width / 2 - 75, this.height / 4 + 10, 50, 10);
        this.textFieldArmDownDelay.setText(Integer.toString(this.thisTileE.getArmDownDelay()));
    }

    public void drawScreen(final int mouseX, final int mouseY, final float par3) {
        if (!this.mc.gameSettings.forceUnicodeFont) {
            this.fontRendererObj.setUnicodeFlag(true);
            this.fontRendererObj.setBidiFlag(true);
        }
        this.drawDefaultBackground();
        this.drawHorizontalLine(0, this.width, this.height / 32 * 4 + 4, new Color(255, 255, 255, 128).getRGB());
        this.drawModel();
        this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.arm.lenght.text"), this.width / 2 - 150, this.height / 4 - 15, 16777200);
        this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.arm.down.delay.text"), this.width / 2 - 150, this.height / 4 + 10, 16777200);
        this.textFieldArmDownDelay.drawTextBox();
        super.drawScreen(mouseX, mouseY, par3);
    }

    protected void actionPerformed(final GuiButton button) {
        switch (button.id) {
            case 0: {
                if (!this.textFieldArmDownDelay.getText().isEmpty())
                    thisTileE.setArmDownDelay(Integer.parseInt(textFieldArmDownDelay.getText()));
                this.thisTileE.markDirty();
                this.mc.displayGuiScreen(null);
                break;
            }
            case 1: {
                int currentLength = this.thisTileE.getBarrierLength();
                if (currentLength < 10) {
                    currentLength++;
                } else {
                    currentLength = 1;
                }
                this.LengthButton.displayString = String.valueOf(currentLength);
                this.thisTileE.setBarrierLength(currentLength);
            }
        }
    }

    protected void mouseClicked(final int x, final int y, final int buttonClicked) {
        this.textFieldArmDownDelay.mouseClicked(x, y, buttonClicked);
        super.mouseClicked(x, y, buttonClicked);
    }

    protected void keyTyped(final char character, final int code) {
        if (Character.isDigit(character) || code == 14) {
            this.textFieldArmDownDelay.textboxKeyTyped(character, code);
        }
    }

    public void updateScreen() {
        this.textFieldArmDownDelay.updateCursorCounter();
    }

    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
        if (!this.mc.gameSettings.forceUnicodeFont) {
            this.fontRendererObj.setUnicodeFlag(false);
            this.fontRendererObj.setBidiFlag(false);
        }
        if (!this.textFieldArmDownDelay.getText().isEmpty()) {
            this.thisTileE.setArmDownDelay(Integer.parseInt(this.textFieldArmDownDelay.getText()));
        }
        final CPacketUpdateCrossings thePacketC = new CPacketUpdateCrossings(this.thisTileE);
        final SPacketUpdateCrossings thePacketS = new SPacketUpdateCrossings(this.thisTileE);
        this.thisTileE.getWorldObj().markBlockForUpdate(this.thisTileE.xCoord, this.thisTileE.yCoord, this.thisTileE.zCoord);
        try {
            final List<Object> list = new LinkedList<>();
            SignalCraft.proxy.packetPipeline.encode(thePacketC, list);
            SignalCraft.proxy.packetPipeline.encode(thePacketS, list);
            final FMLProxyPacket pktC = (FMLProxyPacket) list.get(0);
            final FMLProxyPacket pktS = (FMLProxyPacket) list.get(1);
            SignalCraft.proxy.packetPipeline.sendToServer(pktC);
            SignalCraft.proxy.packetPipeline.sendToAll(pktS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawModel() {
        final float SizePercent = 50.0f;
        GL11.glPushMatrix();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glTranslatef((this.width / 5.0f) * 4, (this.height / 5.0f) * 4, 50.0f);
        GL11.glScalef(-SizePercent, -SizePercent, -SizePercent);
        final float angle = this.thisTileE.getBlockMetadata() * 360 / 16.0f;
        GL11.glRotatef(angle, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(180f, 0.0f, 1.0f, 0.0f);
        TileEntityRendererDispatcher.instance.renderTileEntityAt(this.thisTileE, -0.5, -0.5, -0.5, 0.0f);
        GL11.glPopMatrix();
    }

    private void loadValuesFromTile() {
        this.LenghtText = String.valueOf(this.thisTileE.getBarrierLength());
    }

    public boolean doesGuiPauseGame() {
        return false;
    }
}
