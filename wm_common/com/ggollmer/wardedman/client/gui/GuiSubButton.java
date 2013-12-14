package com.ggollmer.wardedman.client.gui;

import net.minecraft.client.gui.GuiScreen;

public class GuiSubButton
{
	private enum State { UP, HOVER, DOWN };
	
	GuiScreen screen;
	
	private State state;
	
	int width;
	int height;
	int x;
	int y;
	int xh;
	int yh;
	int xa;
	int ya;
	
	/**
	 * Used to create a button from an existing gui. Allowing for a callback to be passed mayhaps?
	 * @param screen The screen the button is on.
	 * @param tl The top left corner of the button in pixels;
	 * @param br The bottom right corner of the button in pixels;
	 * @param tlh The top left corner of the hover version of the button in pixels;
	 * @param brh The bottom right corner of the hover version of the button in pixels;
	 * @param tla The top left corner of the active version of the button in pixels;
	 * @param bla The bottom right corner of the active version of the button in pixels;
	 */
	public GuiSubButton(GuiScreen screen, int width, int height, int x, int y, int xh, int yh, int xa, int ya) {
		this.state = State.UP;
		this.screen = screen;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.xh = xh;
		this.yh = yh;
		this.xa = xa;
		this.ya = ya;
	}
	
	public boolean handleMouseEvent(int mx, int my, int button, int xOffset, int yOffset) {
		
		if(mx >= x && mx <= x + width &&
				my >= y && my <= y + height) {
			this.state = State.HOVER;
			if(button != -1) {
				this.state = State.DOWN;
			}
			else if(button == -1) {
				this.state = State.HOVER;
				return false;
			}
		}
		else {
			this.state = State.UP;
		}
		return false;
	}
	
	public void drawButton(int xOffset, int yOffset) {
		switch(this.state) {
			case UP:
				break;
			case HOVER:
				screen.drawTexturedModalRect(xOffset + this.x, yOffset + this.y, this.xh, this.yh, this.width, this.height);
				break;
			case DOWN:
				screen.drawTexturedModalRect(xOffset + this.x, yOffset + this.y, this.xa, this.ya, this.width, this.height);
				break;
			default:
				break;
		}
	}
}
