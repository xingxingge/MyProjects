package dizoo.std.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 事件实现方式：
 * 1.外部类实现
 * 2.匿名内部类
 * 3.当前类中实现监听器接口
 * 4.利用window适配器实现
 * @author HuangXing
 *
 */
public class Layout {

	private JFrame frmLayoutdemo;
	private JTextField txtUsername;
	private JTextField textPasswd;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					System.exit(0);
					Layout window = new Layout();
					window.frmLayoutdemo.setVisible(true);
					window.frmLayoutdemo.setSize(300, 150);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Layout() {
		initialize();
		setLocation();
		
	}
	public void setLocation(){
		Toolkit tool = Toolkit.getDefaultToolkit();
		// 获取当前屏幕的尺寸
		Dimension d = tool.getScreenSize();
		// 获取屏幕的宽高
		double h = d.getHeight();
		double w = d.getWidth();
		// 窗体x轴
		int x = (int) (w - 300) / 2;
		// 窗体y轴
		int y = (int) (h - 150) / 2;
		frmLayoutdemo.setLocation(x, y);// 设置窗体的位置
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		
		frmLayoutdemo = new JFrame();
		frmLayoutdemo.setResizable(false);
		//class，获取字节序
		frmLayoutdemo.setIconImage(Toolkit.getDefaultToolkit().getImage(Layout.class.getResource("/sun/print/resources/tumble.png")));
		frmLayoutdemo.setTitle("LayoutDemo");
		
		frmLayoutdemo.setBounds(100, 100, 342, 141);
		frmLayoutdemo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmLayoutdemo.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(3, 1, 5, 5));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel label = new JLabel("用户名：");
		panel_1.add(label);
		
		txtUsername = new JTextField();
		panel_1.add(txtUsername);
		txtUsername.setColumns(20);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		JLabel lblNewLabel = new JLabel("  密码：");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_2.add(lblNewLabel);
		
		textPasswd = new JTextField();
		panel_2.add(textPasswd);
		textPasswd.setColumns(20);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		
		JButton btnNewButton = new JButton("登录");
		btnNewButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				check();
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				check();
			}
			
		});
		panel_3.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("注册");
		panel_3.add(btnNewButton_1);
	}
	
	
	public void check(){
		String username= txtUsername.getText();
		String passwd = textPasswd.getText();
		if ("admin".equals(username) && "123".equals(passwd)) {
			System.out.println("登录成功!");
		}else {
			System.out.println("登录失败!");
		}
	}
}
