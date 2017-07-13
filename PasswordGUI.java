import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;

public class PasswordGUI extends JFrame{
    
    private JFrame frame;
 
    private JLabel lblEnterMaximumCharacters;
    private openWriter FW;
    private JCheckBox chckbxCapitals;
    private JCheckBox chckbxAddNumber;
    private JCheckBox chckbxAddSpecial;
    private JLabel lblOr;
    private JLabel lblNumberOfCapital;
    private JLabel lblNumberOfSpecial;
    private JSpinner SpecialsSpinner;
    private JLabel lblOfNumbers;
    private JSpinner CapitalsSpinner;
    private GeneratorLogic GL;
    private JSpinner NumbersSpinner;
    private JLabel lblEnterMinimumCharacters;
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
        setSize(450, 325);
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
        getContentPane().removeAll();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(450, 325);
        setLayout(null);
        
        lblEnterMinimumCharacters = new JLabel("Enter Minimum Characters");
        lblEnterMinimumCharacters.setBounds(35, 11, 207, 14);
        add(lblEnterMinimumCharacters);
        
        JFormattedTextField MinField =new JFormattedTextField(numFormatter());
        MinField.setBounds(0, 31, 217, 20);
        add(MinField);
        MinField.setColumns(10);
        
        lblEnterMaximumCharacters = new JLabel("Enter Maximum Characters");
        lblEnterMaximumCharacters.setBounds(252, 11, 165, 14);
        add(lblEnterMaximumCharacters);
        
        JFormattedTextField MaxField =new JFormattedTextField(numFormatter());
        MaxField.setBounds(217, 31, 227, 20);
        add(MaxField);        
        MaxField.setColumns(10);
        
        chckbxCapitals = new JCheckBox("Add 1 Capital Letter");
        chckbxCapitals.setBounds(0, 88, 150, 23);
        chckbxCapitals.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		if(chckbxCapitals.isSelected()){
        			
        		}
        	}
        });
        add(chckbxCapitals);
        
        chckbxAddNumber = new JCheckBox("Add 1 Number");
        chckbxAddNumber.setBounds(323, 88, 111, 23);
        add(chckbxAddNumber);
        
        chckbxAddSpecial = new JCheckBox("Add 1 Special Character");
        chckbxAddSpecial.setBounds(150, 88, 165, 23);
        add(chckbxAddSpecial);
        
        lblOr = new JLabel("OR");
        lblOr.setBounds(210, 129, 46, 14);
        add(lblOr);
        
        lblNumberOfCapital = new JLabel("# of Capital Letters");
        lblNumberOfCapital.setBounds(10, 178, 123, 14);
        add(lblNumberOfCapital);
        
        CapitalsSpinner = new JSpinner();
        CapitalsSpinner.setBounds(51, 194, 29, 20);
        add(CapitalsSpinner);
        
        lblNumberOfSpecial = new JLabel("# of Special Characters");
        lblNumberOfSpecial.setBounds(154, 178, 150, 14);
        add(lblNumberOfSpecial);
        
        SpecialsSpinner = new JSpinner();
        SpecialsSpinner.setBounds(210, 194, 29, 20);
        add(SpecialsSpinner);
        
        lblOfNumbers = new JLabel("# of Numbers");
        lblOfNumbers.setBounds(340, 178, 85, 14);
        add(lblOfNumbers);
        
        NumbersSpinner = new JSpinner();
        NumbersSpinner.setBounds(359, 194, 29, 20);
        add(NumbersSpinner);
        
        btnGenerate = new JButton("Generate");
        btnGenerate.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(MinField.getText().trim().isEmpty()||MaxField.getText().trim().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Min Characters or Max Characters field was left empty.");
                    GL.Reset();
                    NewScreen();
                }
                else{
                    GL.setMinChars(Integer.valueOf(MinField.getText()));
                    GL.setMaxChars(Integer.valueOf(MaxField.getText()));
                }
                if((chckbxCapitals.isSelected()||chckbxAddNumber.isSelected()||chckbxAddSpecial.isSelected())&&
                		((Integer)SpecialsSpinner.getValue()!=0||(Integer)NumbersSpinner.getValue()!=0||(Integer)CapitalsSpinner.getValue()!=0)){
                	JOptionPane.showMessageDialog(null, "Invalid selection, Check box and Counter were both used.");
                	GL.Reset();
                	NewScreen();
                }
                else if((chckbxCapitals.isSelected()||chckbxAddNumber.isSelected()||chckbxAddSpecial.isSelected())){
                	if(chckbxCapitals.isSelected()){ GL.setCapitalChars(1);}
                	if(chckbxAddNumber.isSelected()){GL.setNumbers(1);}
                	if(chckbxAddSpecial.isSelected()){GL.setSpecialChars(1);}
                }
                else if((Integer)SpecialsSpinner.getValue()!=0||(Integer)NumbersSpinner.getValue()!=0||(Integer)CapitalsSpinner.getValue()!=0){
        	GL.setCapitalChars((Integer)SpecialsSpinner.getValue());
        	GL.setNumbers((Integer)NumbersSpinner.getValue());
        	GL.setSpecialChars((Integer)SpecialsSpinner.getValue());
        }
                
                generatedPasswordScreen();
            }

			});
            btnGenerate.setBounds(0, 238, 450, 23);
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
		
		PWField = new JTextField(GL.Generate());
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
            }});
		add(ClipBoardButton);

		saveButton = new JButton("Save to File");
		saveButton.setBounds(150, 225, 150, 23);
		saveButton.addActionListener(e->FW.writeToFile(GL.Password));
		add(saveButton);


	}
	public void enableSaveItem(){
		saveItem.setEnabled(true);
	}

	public void openedFile() {
		// TODO Auto-generated method stub
		
	}
}