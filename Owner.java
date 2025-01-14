
import java.util.ArrayList;

public class Owner implements Comparable<Owner>{

    // Instansvariabler
    private String name;
    private ArrayList<Dog> ownedDogs = new ArrayList<>();
    // Konstruktor
    public Owner(String name){
        this.name = this.normalizeName(name);

    }
    // Metoder
    public String getName(){
        return name;
    }
    // En instans av klassen ska också kunna skrivas ut på Javas standardsätt.
    public String toString(){
        return "name= %s, Owned Dogs= %s".formatted(name,getDogs() );   
    }
    
    private String normalizeName(String nameInputed){
        String name = nameInputed.toLowerCase();
        int lengthName = name.length(); // Kollar hur många namn det är i strängen
        String showName = "";

        String[] nameArray = new String[lengthName];
        nameArray = name.split(" ");
        
        for(String element: nameArray){
            String firstCapital = element.substring(0, 1);
            showName = showName + firstCapital.toUpperCase() + element.substring(1) + " "; // Den hanterar ej flera namn än, enbart förnamnet.
            }

        if(showName.contains("-")){
            for(int index=0; index<lengthName; index++){
                if(showName.charAt(index) == '-'){
                    String uppercasedLetter = showName.substring(index + 1, index + 2).toUpperCase();
                    showName = showName.substring(0,index+1) + uppercasedLetter + showName.substring(index+2);
                }
            }

                //int index = showName.indexOf('-');
                
            }
        return showName.trim();
    }

    private int min(int a, int b){
        if(a<b){
            return a;
        }else{
            return b;
        }
    }
    
    public int compareTo(Owner other){
        String firstChar = this.getName().toLowerCase();
        String secondChar = other.getName().toLowerCase();

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
    
    public boolean addDog(Dog dog){
        ownedDogs.add(dog); // lägger till hund som "ägd hund"
        return true;
    }

    public boolean removeDog(Dog dog){
        ownedDogs.remove(dog);
        return true;
    }

    public ArrayList<Dog> getDogs(){
        ArrayList<Dog> copy = new ArrayList<>(ownedDogs);
        return copy;
    }
    
    }

    


