import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;

public class PasswordGUI extends JFrame{
    
    private JFrame frame;
 
    private openWriter FW;
    private JLabel lblNumberOfCapital;
    private JLabel lblNumberOfSpecial;
    private JSpinner SpecialsSpinner;
    private JLabel lblOfNumbers;
    private JSpinner CapitalsSpinner;
    private GeneratorLogic GL;
    private JSpinner NumbersSpinner;
    private JButton btnGenerate;
    private JMenuItem newItem;
    private JMenuItem openItem;
    private JMenuItem saveItem;
    private JMenuItem aboutItem;
    private JLabel lblYourGeneratedPassword;
    private JTextField PWField; 
    private JButton ClipBoardButton;
    private JButton saveButton;
    
    public PasswordGUI(GeneratorLogic generatorLogic) {
        super("Password Generator");
        GL=generatorLogic;
        FW=new openWriter();
        setSize(450, 290);
        setResizable(false);
        this.setLocationRelativeTo(null);
        frame=new JFrame();
        createFileMenu();
        NewScreen();
        
        
    }
    
    private void createFileMenu(){
        
        JMenuBar menuBar= new JMenuBar();
        JMenu fileMenu= new JMenu("File");
        FileMenuHandler fmh=new FileMenuHandler(frame,this,GL);
        
        newItem= new JMenuItem("New");
        newItem.addActionListener(fmh);
        fileMenu.add(newItem,0);
        
        openItem = new JMenuItem("Open");
        openItem.addActionListener( fmh );
        fileMenu.add(openItem,1);
        
        saveItem= new JMenuItem("Save");
        saveItem.addActionListener(fmh);
        fileMenu.add(saveItem,2);
        saveItem.setEnabled(false);
        
        menuBar.add(fileMenu);
        
        JMenu HelpMenu=new JMenu("Help");
        aboutItem=new JMenuItem("About");
        aboutItem.addActionListener(fmh);
        HelpMenu.add(aboutItem,0);
        menuBar.add(HelpMenu);
        
        setJMenuBar(menuBar);
        
    }
    
    public void NewScreen() {
    	saveItem.setEnabled(false);
        getContentPane().removeAll();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(450, 290);
        setLayout(null);
        
        JLabel lblEnterNumberOf = new JLabel("Enter Number of Characters");
        lblEnterNumberOf.setBounds(135, 18, 163, 57);
		add(lblEnterNumberOf);

        
        JFormattedTextField NumField =new JFormattedTextField(numFormatter());
       NumField.setBounds(145, 55, 144, 20);
        add(NumField);
        NumField.setColumns(10);
      
        
     
        
        lblNumberOfCapital = new JLabel("# of Capital Letters",SwingConstants.CENTER);
        lblNumberOfCapital.setBounds(1, 97, 144, 46);
        add(lblNumberOfCapital);
        
        SpinnerModel smCaps=new SpinnerNumberModel(0,0,100,1);
        CapitalsSpinner = new JSpinner(smCaps);      
        CapitalsSpinner.setBounds(58, 138, 40, 20);
        add(CapitalsSpinner);
        
        lblNumberOfSpecial = new JLabel("# of Special Characters");
        lblNumberOfSpecial.setBounds(154, 97, 144, 46);
        add(lblNumberOfSpecial);
        
        SpinnerModel smSpec=new SpinnerNumberModel(0,0,100,1);
        SpecialsSpinner = new JSpinner(smSpec);
        SpecialsSpinner.setBounds(195, 138, 40, 20);
        add(SpecialsSpinner);
        
        lblOfNumbers = new JLabel("# of Numbers");
        lblOfNumbers.setBounds(320, 97, 144, 46);
        add(lblOfNumbers);
        
        SpinnerModel smNum=new SpinnerNumberModel(0,0,100,1);
        NumbersSpinner = new JSpinner(smNum);
        NumbersSpinner.setBounds(340, 138, 40, 20);
        add(NumbersSpinner);
        
        btnGenerate = new JButton("Generate");
        btnGenerate.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(NumField.getText().trim().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Number of characters for Password field was left empty.");
                   
                    NewScreen();
                }
                else if(Integer.valueOf(NumField.getText())<0||Integer.valueOf(NumField.getText())>100){
                		JOptionPane.showMessageDialog(null,"Error, password length must be between 1 and 100");
                		NewScreen();
                	
                }
                else if(Integer.valueOf(NumField.getText()) < ( (Integer)CapitalsSpinner.getValue() + (Integer)NumbersSpinner.getValue() + (Integer)SpecialsSpinner.getValue() )){
                	JOptionPane.showMessageDialog(null, "Error, Number of characters for password less than amount of Numbers, Specials and Capitals requested in password");
                	NewScreen();
                }
                else{
                	GL.setNumChars(Integer.valueOf(NumField.getText()));
        	GL.setCapitalChars((Integer)CapitalsSpinner.getValue());
        	GL.setNumbers((Integer)NumbersSpinner.getValue());
        	GL.setSpecialChars((Integer)SpecialsSpinner.getValue());
        
        GL.Generate();
                generatedPasswordScreen();
                }
            }

			});
            btnGenerate.setBounds(1, 191, 443, 34);
            add(btnGenerate);
            
            setVisible(true);
        }

	public void HelpScreen(){
    	getContentPane().removeAll();        
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(450, 325);       
        setLayout(new GridLayout(0, 1, 0, 0));
				
		JLabel lblPasswordGeneratorFeatures = new JLabel("Password Generator Features",SwingConstants.CENTER);
		add(lblPasswordGeneratorFeatures);
		
		JLabel label_1 = new JLabel("");
		add(label_1);
		
		JLabel lblPasswordGeneratorWill = new JLabel("Password Generator will take user inputs",SwingConstants.CENTER);
		add(lblPasswordGeneratorWill);
		
		JLabel lblAndGenerateA = new JLabel("and generate a random password based on user preference.",SwingConstants.CENTER);
		add(lblAndGenerateA);
		
		JLabel label_2 = new JLabel("");
		add(label_2);
		
		JLabel lblFilenewBringsUp = new JLabel("File>New- Brings up new password generator window.",SwingConstants.CENTER);
		add(lblFilenewBringsUp);
		
		JLabel label_3 = new JLabel("");
		add(label_3);
		
		JLabel lblFileopenOpensUp = new JLabel("File>Open- Opens up a previously saved password",SwingConstants.CENTER);
		add(lblFileopenOpensUp);
		
		JLabel lblAndCopiesThe = new JLabel(" and copies the password to the clipboard.",SwingConstants.CENTER);
		add(lblAndCopiesThe);
		
		JLabel label_5 = new JLabel("");
		add(label_5);
		
		JLabel lblFilesaveSavesYour = new JLabel("File>Save- Saves your password to a textfile",SwingConstants.CENTER);
		add(lblFilesaveSavesYour);
		
		JLabel lblToBeAccessed = new JLabel(" to be accessed for later.",SwingConstants.CENTER);
		add(lblToBeAccessed);
		
		JLabel label_6=new JLabel("");
		add(label_6);
		
		JLabel lblDAD=new JLabel("Can also drag and drop a .pwg file to automatically copy the generated password",SwingConstants.CENTER);
		add (lblDAD);
		
		JLabel lblDADC=new JLabel("to the clipboard",SwingConstants.CENTER);
		add (lblDADC);
		
		JButton btnBackToPassword = new JButton("Back to Password Generator");
		btnBackToPassword.addActionListener(e->{NewScreen();});
		add(btnBackToPassword);
		
		setVisible(true);
    }
	
	private NumberFormatter numFormatter() {
    	NumberFormat Format=NumberFormat.getIntegerInstance();
    	NumberFormatter numFormatter = new NumberFormatter(Format) {           
            public Object stringToValue(String string)
                throws ParseException {
                if (string == null || string.length() == 0||string.equals("0")) {
                    return null;
                }
                return super.stringToValue(string);
            }
        };
        numFormatter.setValueClass(Integer.class);
        numFormatter.setAllowsInvalid(false);
        numFormatter.setMinimum(0);
        
        return numFormatter;
	}
	private void generatedPasswordScreen() {
		
		getContentPane().removeAll();
		setResizable(false);
        setSize(450, 325);     
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		lblYourGeneratedPassword = new JLabel("Your generated password:",SwingConstants.CENTER);
		lblYourGeneratedPassword.setBounds(0, 0, 434, 95);
		add(lblYourGeneratedPassword);
		
		PWField = new JTextField(GL.getPassword());
		PWField.setHorizontalAlignment(JTextField.CENTER);
		PWField.setBounds(0, 95, 434, 39);
		PWField.setEditable(false);
		add(PWField);
		PWField .setColumns(10);
		
		ClipBoardButton = new JButton("Copy to Clipboard");
		ClipBoardButton.setBounds(150, 166, 150, 23);
		ClipBoardButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	StringSelection stringSelection = new StringSelection(GL.Password);
            	Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
            	clpbrd.setContents(stringSelection, null);
            	JOptionPane.showMessageDialog(null, "Copied to Clipboard");
            }});
		add(ClipBoardButton);

		saveButton = new JButton("Save to File");
		saveButton.setBounds(150, 225, 150, 23);
		saveButton.addActionListener(e->FW.writeToFile(GL.Password));
		add(saveButton);


	}
	public void changeSaveItem(boolean setting){
		saveItem.setEnabled(setting);
	}

	public void openedFile() {
		getContentPane().removeAll();
		setResizable(false);
        setSize(450, 325);     
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		lblYourGeneratedPassword = new JLabel("Your generated password:",SwingConstants.CENTER);
		lblYourGeneratedPassword.setBounds(0, 0, 434, 95);
		add(lblYourGeneratedPassword);
		
		PWField = new JTextField(GL.Password);
		PWField.setHorizontalAlignment(JTextField.CENTER);
		PWField.setBounds(0, 95, 434, 39);
		PWField.setEditable(false);
		add(PWField);
		PWField .setColumns(10);
		
		ClipBoardButton = new JButton("Copy to Clipboard");
		ClipBoardButton.setBounds(150, 166, 150, 23);
		ClipBoardButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	StringSelection stringSelection = new StringSelection(GL.Password);
            	Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
            	clpbrd.setContents(stringSelection, null);
            	JOptionPane.showMessageDialog(null, "Copied to Clipboard");
            }});
		add(ClipBoardButton);
		changeSaveItem(false);
		
	}
}