package bbb;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

public class p1 extends JFrame implements ActionListener {
	JMenuBar jMenuBar = null;
	JMenuItem jMenuItem1 = null;
	JMenuItem jMenuItem2 = null;
	JButton jButton = null;
	JLabel jLabel1 = null;
	JLabel jLabel2 = null;
	JLabel jLabel3 = null;
	JTextArea jTextArea = null;
	JFileChooser jFileChooser1 = null;
	JFileChooser jFileChooser2 = null;
	FileReader fileReader = null;
	FileWriter fileWriter = null;
	BufferedReader bufferedReader = null;
	BufferedWriter bufferedWriter = null;

	public p1() {
		jMenuBar = new JMenuBar();
		jMenuItem1 = new JMenuItem("打开");
		jMenuItem2 = new JMenuItem("保存");
		jButton = new JButton("统计");
		jLabel1 = new JLabel("  字符数：0  ");
		jLabel2 = new JLabel("  单词数：0  ");
		jLabel3 = new JLabel("  总行数：0  ");
		jTextArea = new JTextArea();
		jMenuItem1.addActionListener(this);
		jMenuItem1.setActionCommand("打开");
		jMenuItem2.addActionListener(this);
		jMenuItem2.setActionCommand("保存");
		jButton.addActionListener(this);
		jButton.setActionCommand("统计");
		this.setJMenuBar(jMenuBar);
		jMenuBar.add(jMenuItem1);
		jMenuBar.add(jMenuItem2);
		jMenuBar.add(jButton);
		jMenuBar.add(jLabel1);
		jMenuBar.add(jLabel2);
		jMenuBar.add(jLabel3);
		this.add(jTextArea);
		this.setTitle("能进行统计的记事本");
		this.setSize(800, 600);
		this.setLocation(250, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

	}

	public static void main(String[] args) {
		p1 ss1 = new p1();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("打开")) {
			jFileChooser1 = new JFileChooser();
			jFileChooser1.setDialogTitle("请选择自己的文件... ...");
			jFileChooser1.showOpenDialog(null);
			jFileChooser1.setVisible(true);
			String address1 = jFileChooser1.getSelectedFile().getAbsolutePath();
			try {
				fileReader = new FileReader(address1);
				bufferedReader = new BufferedReader(fileReader);
				String str = "";
				String strAll = "";
				while ((str = bufferedReader.readLine()) != null) {
					strAll += str + "\r\n";
				}
				jTextArea.setText(strAll);
			} catch (Exception e2) {
				e2.printStackTrace();
			} finally {
				try {
					bufferedReader.close();
					fileReader.close();
				} catch (Exception e3) {
					e3.printStackTrace();
				}
			}
		}
		if (e.getActionCommand().equals("保存")) {
			jFileChooser2 = new JFileChooser("另存为...");
			jFileChooser2.showSaveDialog(null);
			jFileChooser2.setVisible(true);
			String address2 = jFileChooser2.getSelectedFile().getAbsolutePath();
			try {
				fileWriter = new FileWriter(address2);
				bufferedWriter = new BufferedWriter(fileWriter);
				bufferedWriter.write(this.jTextArea.getText());
			} catch (Exception e2) {
				e2.printStackTrace();
			} finally {
				try {
					bufferedWriter.close();
					fileWriter.close();
				} catch (Exception e3) {
					e3.printStackTrace();
				}
			}
		}
		if (e.getActionCommand().equals("统计")) {
			int c1 = 0, c2 = 0, c3 = 0;
			String str = this.jTextArea.getText(); // 获取当前文本框内容
			if (str != null)
				c3++;
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) != ' ' && str.charAt(i) != '\n')
					c1++;
				if (str.charAt(i) == '\n')
					c3++;
			}
			for (int i = 1; i < str.length(); i++) {
				if (str.charAt(i) == ' ' && str.charAt(i - 1) != ' ')
					c2++;
				if (str.charAt(i) == '\n' && str.charAt(i - 1) != ' ' && str.charAt(i - 1) != '\n')
					c2++;
			}
			if (str.charAt(str.length() - 1) != ' ')
				c2++;
			System.out.println(str);
			jLabel1.setText("  字符数：  " + c1);
			jLabel2.setText("  单词数：  " + c2);
			jLabel3.setText("  总行数：  " + c3);
		}
		// TODO 自动生成的方法存根

	}

}
