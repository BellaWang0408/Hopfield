import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;

import java.awt.BorderLayout;


public class windowbuilder {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					windowbuilder window = new windowbuilder();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public windowbuilder() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1021, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		final AlgoNN nn = new AlgoNN();
		
		
		final JFileChooser file = new JFileChooser();
		final JButton button1 = new JButton("開啟訓練資料");
		button1.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				file.setCurrentDirectory(new java.io.File("C:/Users/Lynn/workspace/NNHW3"));
				file.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                if(file.showOpenDialog(button1) == JFileChooser.APPROVE_OPTION) 
                {
                }
			}
		});
		button1.setBounds(34, 503, 139, 34);
		frame.getContentPane().add(button1, BorderLayout.EAST);
		
		final JFileChooser choose = new JFileChooser();
		final JButton button2 = new JButton("開啟測試資料");
		button2.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				choose.setCurrentDirectory(new java.io.File("C:/Users/Lynn/workspace/NNHW3"));
                choose.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                if(choose.showOpenDialog(button2) == JFileChooser.APPROVE_OPTION)
                {
                }
			}
		});
		button2.setBounds(201, 503, 139, 34);
		frame.getContentPane().add(button2, BorderLayout.WEST);
		
		//雜訊
		textField = new JTextField();
		textField.setBounds(404, 506, 67, 31);
		textField.setText("1");
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 51, 320, 434);
		frame.getContentPane().add(scrollPane_1);
		
		
		final JTextArea textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		textArea.setFont(new Font("新細明體", Font.PLAIN, 13));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(343, 51, 320, 434);
		frame.getContentPane().add(scrollPane);
		
		final JTextArea textArea_1 = new JTextArea();
		scrollPane.setViewportView(textArea_1);
		textArea_1.setFont(new Font("新細明體", Font.PLAIN, 13));
		
		JLabel label = new JLabel("\u8A13\u7DF4\u8CC7\u6599");
		label.setFont(new Font("微軟正黑體", Font.PLAIN, 22));
		label.setBounds(110, 10, 105, 31);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u6E2C\u8A66\u8CC7\u6599");
		label_1.setFont(new Font("微軟正黑體", Font.PLAIN, 22));
		label_1.setBounds(448, 10, 105, 31);
		frame.getContentPane().add(label_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(673, 51, 320, 434);
		frame.getContentPane().add(scrollPane_2);
		
		final JTextArea textArea_2 = new JTextArea();
		scrollPane_2.setViewportView(textArea_2);
		textArea_2.setFont(new Font("新細明體", Font.PLAIN, 13));
		
		JLabel label_2 = new JLabel("\u56DE\u60F3\u7D50\u679C");
		label_2.setFont(new Font("微軟正黑體", Font.PLAIN, 22));
		label_2.setBounds(782, 10, 105, 31);
		frame.getContentPane().add(label_2);
		
		JButton button = new JButton("\u57F7\u884C");
		button.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			nn.random = Integer.parseInt(textField.getText());
				try{
					//nn.run("C:\\Users\\Lynn\\workspace\\NNHW3\\Dataset\\Hopfield_dataset\\Basic_Training.txt", "C:\\Users\\Lynn\\workspace\\NNHW3\\Dataset\\Hopfield_dataset\\Basic_Testing.txt");
					nn.run(file.getSelectedFile().getAbsolutePath(),choose.getSelectedFile().getAbsolutePath());
				}
				catch(IOException ee)
				{
					ee.printStackTrace();					
				}
				for(int i=0;i<nn.h;i++)
				{
					System.out.println("++++"+nn.train.get(i).size());
					for(int j = 0;j < nn.train.get(i).size(); j++)
					{
						System.out.print(nn.train.get(i).get(j));
						if(j % nn.l == nn.l-1) System.out.println();
					}
					for(int j=0;j<nn.weight;j++)
					{
						if(nn.train.get(i).get(j)==1)
						{
							textArea.setText(textArea.getText()+"1 ");
						}
						if(nn.train.get(i).get(j)==-1)
							textArea.setText(textArea.getText()+"   ");
						if(j%(nn.l)==(nn.l-1))
							textArea.setText(textArea.getText()+"\n");
						
					}
						textArea.setText(textArea.getText() + "\n");
				}
				for(int i=0;i<nn.h;i++)
				{
					for(int j=0;j<nn.weight;j++)
					{
						if(nn.rantest[i][j]==1)
						{
							textArea_1.setText(textArea_1.getText() + "1 ");
						}
						else if(nn.rantest[i][j]==-1)
							textArea_1.setText(textArea_1.getText() + "   ");
						if(j%(nn.l)==(nn.l-1))
							textArea_1.setText(textArea_1.getText() + "\n");
					}
					textArea_1.setText(textArea_1.getText() + "\n");
				}
				for(int i=0;i<nn.h;i++)
				{
					for(int j=0;j<nn.weight;j++)
					{
						if(nn.testa[i][j]==1)
						{
							textArea_2.setText(textArea_2.getText() + "1 ");
						}
						else if(nn.testa[i][j]==-1)
							textArea_2.setText(textArea_2.getText() + "   ");
						if(j%(nn.l)==(nn.l-1))
							textArea_2.setText(textArea_2.getText() + "\n");
					}
					textArea_2.setText(textArea_2.getText() + "\n");
				}	
			}
		});
		button.setForeground(Color.BLACK);
		button.setBounds(503, 503, 139, 34);
		frame.getContentPane().add(button);
		
		JLabel lbln = new JLabel("\u6A5F\u7387");
		lbln.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
		lbln.setBounds(363, 503, 55, 34);
		frame.getContentPane().add(lbln);
	}				
}


