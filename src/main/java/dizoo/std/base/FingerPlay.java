package dizoo.std.base;

import java.util.Random;
import java.util.Scanner;

/**
 * ��ȭ��Ϸ 1.�����ȭ���� 2.�����û��Ĳ�ȭ���루0��ʯͷ��1������������2�� 3.���û������ֵ����ԱȽϣ��ۼƸ��Ե÷�  4.����ж���Ӯ
 * */
public class FingerPlay {
	public static void main(String[] args) {
		Games g1 = new Games();
		// ��ӡ��Ϸ����
		g1.info();
		// ��������
		g1.inputTimes();
		// ��ʼѭ������
		for (int i = 1; i <= g1.getTimes(); i++) {
			// �����û������ֵ
			int users = g1.userInput(i);
			int systems = g1.systemInput();
			// ��ʼ�Ƚϣ��ۼ���Ӯ����
			g1.compare(users, systems);

		}
		// ������Ľ��
		g1.output();
	}

}

class Games {
	public int getTimes() {
		return times;
	}

	private int times;
	private int userWin = 0;
	private int systemWin = 0;
	private int common = 0;
	Scanner in = new Scanner(System.in);
	Random r1 = new Random();

	public int getUserWin() {
		return userWin;
	}

	public int getSystemWin() {
		return systemWin;
	}

	public int getCommon() {
		return common;
	}

	/**
	 * ��Ϸ������Ϣ����
	 * **/

	public void info() {

		System.out.println("=============================");
		System.out.println("��Ϸ���򣺣�0��������1��ʯͷ������2��");

	}

	/**
	 * �������
	 * **/
	public void inputTimes() {
		System.out.print("�����ȭ����");
		this.times = in.nextInt();
	}

	/**
	 * �����û������벢���м���ķ���
	 * **/
	public int userInput(int mTimes) {
		System.out.print("��" + mTimes + "��PK�����ȭ��0��������1��ʯͷ������2����");
		int userFinger;
		while (true) {
			userFinger = in.nextInt();
			// �ж��û�����ĺϷ���
			if (userFinger != 0 && userFinger != 1 && userFinger != 2) {
				System.out.println("���벻�Ϸ������������루0��������1��ʯͷ������2����");
			} else {
				break;
			}

		}
		return userFinger;
	}

	/**
	 * ������������ķ���
	 * **/
	public int systemInput() {
		int systemFinger;
		systemFinger = r1.nextInt(3);
		return systemFinger;

	}

	/**
	 * �ж���Ӯ�����ҽ��л�ֵķ�����
	 * **/
	public void compare(int user, int system) {
		if (user == 0) {//�û��Ǽ���
			switch (system) { 
			case 0:
				this.common++;
				break;
			case 1:
				this.systemWin++;
				break;
			case 2:
				this.userWin++;
				break;
			default:
				break;
			}
		} else if (user == 1) { // �û���ʯͷ
			switch (system) {
			case 0:
				this.userWin++;
				break;
			case 1:
				this.common++;
				break;
			case 2:
				this.systemWin++;
				break;
			default:
				break;
			}

		} else if (user == 2) { // �û��ǲ�
			switch (system) {
			case 0:
				this.systemWin++;
				break;
			case 1:
				this.userWin++;
				break;
			case 2:
				this.common++;
				break;
			default:
				break;
			}
		}
	}

	/**
	 * ������
	 * **/
	public void output() {
		System.out.println("��Ӯ�Ĵ����ǣ�" + this.userWin);
		System.out.println("����Ӯ�Ĵ����ǣ�" + this.systemWin);
		System.out.println("ƽ����" + this.common);
		System.out.print("���Ľ���ǣ�");
		if (this.userWin > this.systemWin) {
			System.out.println("��Ӯ�ˣ�");

		} else if (this.userWin < this.systemWin) {
			System.out.println("�����ˣ�");
		} else {
			System.out.println("���Ǵ�ƽ�ˣ�");
		}

	}

}
