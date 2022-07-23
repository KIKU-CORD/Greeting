package GUI.INTERFACE;

import java.awt.Graphics;

public abstract class Interfaces {
	
	//インターフェースのレンダー
	public abstract void render(Graphics g);
	
	//マウスの動きを検知
	public abstract void mouseMove(int mouseX, int mouseY);
	
	//マウスのクリックを検知
	public abstract void mouseClick(int mouseX, int mouseY);
	
	//マウス位置がインターフェースの範囲内であるかチェック
	public abstract boolean isCover(int mouseX, int mouseY);
}
