package signalcraft.gui.gsar.buttons;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiSliderButton extends GuiButton
{
    private float percent;
    private float value;
    private final String name;
    public boolean isPressed;
    private final float min;
    private final float max;
    private final float step;
    
    public GuiSliderButton(final int id, final int x, final int y, final int width, final String name, final float step, final float min, final float max, final float value) {
        super(id, x, y, width, 20, "");
        this.step = step;
        this.min = min;
        this.max = max;
        this.name = name;
        this.setValue(value);
    }
    
    public int getHoverState(final boolean isHover) {
        return 0;
    }
    
    protected void mouseDragged(final Minecraft MC, final int mouseX, final int mouseY) {
        if (this.visible) {
            if (this.isPressed) {
                this.percent = (mouseX - (this.xPosition + 4)) / (float)(this.width - 8);
                if (this.percent < 0.0f) {
                    this.percent = 0.0f;
                }
                if (this.percent > 1.0f) {
                    this.percent = 1.0f;
                }
                this.setValue(this.denormalize(this.percent));
            }
            this.drawSlider();
        }
    }
    
    public boolean mousePressed(final Minecraft MC, final int mouseX, final int mouseY) {
        if (super.mousePressed(MC, mouseX, mouseY)) {
            this.percent = (mouseX - (this.xPosition + 4)) / (float)(this.width - 8);
            if (this.percent < 0.0f) {
                this.percent = 0.0f;
            }
            if (this.percent > 1.0f) {
                this.percent = 1.0f;
            }
            this.setValue(this.denormalize(this.percent));
            return this.isPressed = true;
        }
        return false;
    }
    
    public void mouseReleased(final int mouseX, final int mouseY) {
        this.isPressed = false;
    }
    
    private void drawSlider() {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.drawTexturedModalRect(this.xPosition + (int)(this.percent * (this.width - 8)), this.yPosition, 0, 66, 4, 20);
        this.drawTexturedModalRect(this.xPosition + (int)(this.percent * (this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
    }
    
    public float getValue() {
        return this.value;
    }
    
    public void setValue(final float value) {
        this.value = this.snapToStepClamp(value);
        this.percent = this.normalize(this.value);
        this.displayString = this.name + ":" + this.value;
    }
    
    private float snapToStep(float value) {
        if (this.step > 0.0f) {
            value = this.step * Math.round(value / this.step);
        }
        return value;
    }
    
    private float snapToStepClamp(float value) {
        value = this.snapToStep(value);
        return MathHelper.clamp_float(value, this.min, this.max);
    }
    
    private float normalize(final float value) {
        return MathHelper.clamp_float((this.snapToStepClamp(value) - this.min) / (this.max - this.min), 0.0f, 1.0f);
    }
    
    private float denormalize(final float value) {
        return this.snapToStepClamp(this.min + (this.max - this.min) * MathHelper.clamp_float(value, 0.0f, 1.0f));
    }
}
