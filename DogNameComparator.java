
import java.util.Comparator;

public class DogNameComparator implements Comparator<Dog> {
    
    private String firstChar; 
    private String secondChar; 

    private int min(int a, int b){
        if(a<b){
            return a;
        }else{
            return b;
        }
    }
    
    public int compare(Dog first, Dog second){
        firstChar = first.getName().toLowerCase();
        secondChar = second.getName().toLowerCase();

        int minLength =  min(firstChar.length(), secondChar.length());
        

        for(int i=0; i<minLength;i++){ 
            char firstCharIndexOfI = firstChar.charAt(i);
            char secondCharIndexOfI = secondChar.charAt(i);

            if(firstCharIndexOfI > secondCharIndexOfI){
                return 1;
            }
            if(firstCharIndexOfI < secondCharIndexOfI){
                return -1;
            }
        }
        
        if(firstChar.length()<secondChar.length()){
        return -1;
        }
        if(firstChar.length()>secondChar.length()){
        return 1;
        }
        return 0;

    }
}