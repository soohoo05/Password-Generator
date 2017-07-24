
public class Main {
	 public static void main(String[] args){
		 if(args.length==0){
	 GeneratorLogic GL=new GeneratorLogic();
		 }
	
		 else{ 
			 GeneratorLogic GL=new GeneratorLogic(args);
		 }
	 }
}
