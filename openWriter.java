import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class openWriter {
private Scanner scanner;

	public openWriter(String[] args,GeneratorLogic GL) {
		
		try {
			 scanner=new Scanner(new FileReader(args[0]));
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		}
		String line = scanner.nextLine();
		GL.Password=line;
		GL.copyToClipboard();
	}

	public openWriter() {
		//Constructor only here for when there is no premade file is dropped
		//onto the program and need access to writeToFile method
	}

	public void writeToFile(String password) {	
	    JFileChooser chooser = new JFileChooser(){
	        public void approveSelection(){
	            if (getSelectedFile().exists()){
	            	JOptionPane.showMessageDialog(null, "Must create new file");	              
	              
	                return;
	            }
	            else
	                super.approveSelection();
	        }
	    };
	    
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("PassGen", "pwg");
	    chooser.setFileFilter(filter);
	    chooser.setCurrentDirectory(new File("/home/me/Documents"));
	    int retrival = chooser.showSaveDialog(null);
	    
	    if (retrival == JFileChooser.APPROVE_OPTION) {
	        try {
	        	chooser.getSelectedFile().delete();	        	
	            FileWriter fw = new FileWriter(chooser.getSelectedFile()+".pwg",true);
	            fw.write(password);
	         fw.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }

	}

}
