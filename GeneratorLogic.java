import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JOptionPane;

public class GeneratorLogic {

	private PasswordGUI Display;
	private static int MinChars=0;
	private static int MaxChars=0;
	private static int SpecialChars=0;
	private static int CapitalChars=0;
	private static int Numbers=0;
	private final String Alphabet="abcdefghijklmnopqrstuvwxyz";
	private final String Digits= "0123456789";
	private final String Specials= "!@#$%&*()_+-=[]|,./?><";
	public String Password;
	private Random random=new Random();
	public GeneratorLogic(){
		Display=new PasswordGUI(this);
		Display.setVisible(true);
	}
	public void setMinChars(int number){
		MinChars=number;		
	}
	public void setMaxChars(int number){
		MaxChars=number;				
	}
	public void setSpecialChars(int number){
		SpecialChars=number;		
	}
	public void setCapitalChars(int number){
		CapitalChars=number;		
	}
	public void setNumbers(int number){
		Numbers=number;
	}
	public String Generate(){
		Password="";
		
		inputChecks();
		
		int randomPasswordLength=random.nextInt(MaxChars+1-MinChars)+MinChars;
		
		for (int i=0;i<SpecialChars;i++){
			Password=Password+Specials.charAt(random.nextInt(Specials.length()));
			randomPasswordLength--;
		}
		
		for(int j=0;j<CapitalChars;j++){
		char temp;
		temp=Alphabet.charAt(random.nextInt(Alphabet.length()));
		char upper=Character.toUpperCase(temp);
		Password=Password+upper;
		randomPasswordLength--;
		}
		
		for (int k=0;k<Numbers;k++){
		char temp;
		temp=Digits.charAt(random.nextInt(Digits.length()));
		Password=Password+temp;
		randomPasswordLength--;
		}
		
		for(int l=randomPasswordLength;l>0;l--){
			Password=Password+Alphabet.charAt(random.nextInt(Alphabet.length()));
		}
		Password=Shuffle(Password);
		System.out.println(Password);
		Display.enableSaveItem();
		return Password;
	}
	private void inputChecks() {
		if(MaxChars<SpecialChars+CapitalChars+Numbers){
			JOptionPane.showMessageDialog(null, "Error, number of Special characters, Capitals and Numbers exceed Max characters inputted");
			Reset();
			Display.NewScreen();
		}
		else if(MaxChars<MinChars){
			JOptionPane.showMessageDialog(null,"Error, Max Characters inputted is less than Min Characters" );
			Reset();
			Display.NewScreen();
		}
		
	}
	
	private String Shuffle(String password) {
		ArrayList<Character> shuffler=new ArrayList<>();
		for(char c:password.toCharArray()){
			shuffler.add(c);
		}
		Collections.shuffle(shuffler);
		
		StringBuilder shuffledPassword=new StringBuilder();
		for(char c:shuffler){
			shuffledPassword.append(c);
		}
		password=shuffledPassword.toString();
		return password;
		
	}
	public void Reset(){
		MinChars=0;
		MaxChars=0;
		SpecialChars=0;
		CapitalChars=0;
		Numbers=0;
	}
}
