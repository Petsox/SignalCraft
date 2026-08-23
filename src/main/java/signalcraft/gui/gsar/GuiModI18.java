package signalcraft.gui.gsar;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.resources.I18n;

@SideOnly(Side.CLIENT)
public class GuiModI18
{
    public static String gui(final String guiName, final String componentName, final Object... arg) {
        final String key = "gui." + guiName + "." + componentName;
        return I18n.format(key, arg);
    }
}
