import java.io.File;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class openWriter {

	public void writeToFile(String password) {	
	    JFileChooser chooser = new JFileChooser(){
	        public void approveSelection()
	        {
	            if (getSelectedFile().exists())
	            {
	                System.out.println("duplicate");	//change to joptionpane "cant select
	                //preexisting file"
	                //create opened file, work on drag and drop
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
