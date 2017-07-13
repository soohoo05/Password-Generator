import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileMenuHandler implements ActionListener{
	static JFrame jframe;
	static PasswordGUI PWGUI;
	static GeneratorLogic GenL;
	public FileMenuHandler (JFrame jf,PasswordGUI GUI,GeneratorLogic GL) {
	    jframe = jf;
	    PWGUI=GUI;
	    GenL=GL;
	   }

	@Override
	public void actionPerformed(ActionEvent event) {
		String  menuName;
	      menuName = event.getActionCommand();
	      if(menuName.equals("New")){
	    	 PWGUI.NewScreen(); 
	      }
	      else if (menuName.equals("Open")){
	        try {
	            openFile( );
	        } catch (FileNotFoundException e1) {
	            // TODO Auto-generated catch block
	            e1.printStackTrace();
	        }
	      }
	      else if(menuName.equals("About")){
	    	  PWGUI.HelpScreen();
	      }
	}
	

	private void openFile( ) throws FileNotFoundException {
	      JFileChooser chooser;
	      int status;
	      chooser = new JFileChooser( );
	      FileNameExtensionFilter filter = new FileNameExtensionFilter("Password Gen","pwg");
	      chooser.setFileFilter(filter);
	      status = chooser.showOpenDialog(null);
	      
	      if (status == JFileChooser.APPROVE_OPTION)
	         readSource(chooser.getSelectedFile());
	   } //openFile
	 private void readSource(File chosenFile)  {
		 try{
			 Scanner scanner=new Scanner(chosenFile);		
			 GenL.Password=scanner.nextLine();
			 System.out.println(GenL.Password);
			 scanner.close();
			 PWGUI.openedFile();
		 }
		 catch (FileNotFoundException e) {
             e.printStackTrace();
          }//catch

	 }
}
