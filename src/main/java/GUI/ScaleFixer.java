package GUI;

public class ScaleFixer {
	
	/*
	 * �𑜓x�ύX���̃X�N���[�����C�A�E�g�C��
	 */
	
	private static double whelper, hhelper;
	
	public static void setup(double whelper, double hhelper) {
		ScaleFixer.whelper = whelper;
		ScaleFixer.hhelper = hhelper;
	}
	
	public static int getWidth(int width) {
		return (int)((double)width * whelper);
	}
	
	public static int getHeight(int height) {
		return (int)((double)height * hhelper);
	}
}
