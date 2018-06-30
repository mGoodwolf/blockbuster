package mchorse.blockbuster.client.gui.dashboard.panels.model_editor.modals;

import java.util.function.Consumer;

import mchorse.blockbuster.client.gui.framework.elements.GuiButtonElement;
import mchorse.blockbuster.client.gui.framework.elements.GuiDelegateElement;
import mchorse.blockbuster.client.gui.framework.elements.GuiTextElement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class GuiPromptModal extends GuiModal
{
    public String label;
    public Consumer<String> callback;

    public GuiTextElement text;
    public GuiButtonElement<GuiButton> confirm;
    public GuiButtonElement<GuiButton> cancel;

    public GuiPromptModal(Minecraft mc, GuiDelegateElement parent, String label, Consumer<String> callback)
    {
        super(mc, parent);

        this.label = label;
        this.callback = callback;

        this.text = new GuiTextElement(mc, null);
        this.text.resizer().parent(this.area).set(0, 0, 90, 20).x(0.5F, -45).y(0.5F, 10);
        this.text.field.setFocused(true);

        this.confirm = GuiButtonElement.button(mc, "Ok", (b) -> this.send());
        this.confirm.resizer().set(60, 25, 30, 20).relative(this.text.resizer());

        this.cancel = GuiButtonElement.button(mc, "Cancel", (b) -> this.parent.setDelegate(null));
        this.cancel.resizer().set(0, 25, 55, 20).relative(this.text.resizer());

        this.children.add(this.text, this.confirm, this.cancel);
    }

    public GuiPromptModal setValue(String value)
    {
        this.text.setText(value);

        return this;
    }

    private void send()
    {
        String text = this.text.field.getText();

        if (!text.isEmpty())
        {
            this.parent.setDelegate(null);
            this.callback.accept(text);
        }
    }

    @Override
    public void draw(int mouseX, int mouseY, float partialTicks)
    {
        super.draw(mouseX, mouseY, partialTicks);

        this.font.drawSplitString(this.label, this.area.getX(0.2F), this.area.getY(0.25F), (int) (this.area.w * 0.6), 0xffffff);
    }
}