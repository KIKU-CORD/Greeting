package GUI.INTERFACE;

import java.awt.Graphics;

public abstract class Interfaces {
	
	//�C���^�[�t�F�[�X�̃����_�[
	public abstract void render(Graphics g);
	
	//�}�E�X�̓��������m
	public abstract void mouseMove(int mouseX, int mouseY);
	
	//�}�E�X�̃N���b�N�����m
	public abstract void mouseClick(int mouseX, int mouseY);
	
	//�}�E�X�ʒu���C���^�[�t�F�[�X�͈͓̔��ł��邩�`�F�b�N
	public abstract boolean isCover(int mouseX, int mouseY);
}
