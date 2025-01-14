

import java.util.ArrayList;
import java.util.Arrays;


public class OwnerCollection {

    /*static*/ private int length;
    private Owner[] arrayOfOwners = new Owner[length];

    // Lägg till ägare
    public boolean addOwner(Owner owner){
        if(containsOwner(owner)){
            return false;
        } else{

        Owner[] copy = new Owner[length + 1];

        for(int i = 0; i < length; i++){
            copy[i] = arrayOfOwners[i];
        }
        copy[length] = owner;

        arrayOfOwners = copy;
        length += 1;
        return true;
    }

    }

    // Innehåller ägare?
    public boolean containsOwner(String name){
        if(length < 1){
            return false;
        }
        
        name = this.normalizeName(name);
        
        for( int i = 0; i<length; i++){
            if(arrayOfOwners[i] != null && arrayOfOwners[i].getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public boolean containsOwner(Owner owner){
        return containsOwner(owner.getName());

    }

    // Hämta ägare
    public Owner getOwner(String name){
        if( containsOwner(name) ){
            for(Owner element:arrayOfOwners){
                if( element.getName().toLowerCase().equalsIgnoreCase(name)){
                    return element;
                }
            }
        }
        return null;
    }

    public ArrayList<Owner> getOwners(){
        ArrayList<Owner> ownerList = new ArrayList<>();
        
        if(arrayOfOwners == null || arrayOfOwners.length<1){
            return ownerList;
        }else{
        
        Owner[] sortedOwners = Arrays.copyOf(arrayOfOwners, arrayOfOwners.length);
        Arrays.sort(sortedOwners);
        
        for(Owner element: sortedOwners){
            if( element != null && element.getName() != null ){
            ownerList.add(element);
            }
        }
        

        return ownerList;
        } 
    }










    // Ta bort ägare
    public boolean removeOwner(String name){
        if(containsOwner(name)){
            for(Owner o: arrayOfOwners){
                if(o.getName().equalsIgnoreCase(name)){
                    return removeOwner(o);
                }
            }
            /*for(Owner element: arrayOfOwners){
                if(/*element != null && element.getName() != null && element.getName().equalsIgnoreCase(name)){
                    return removeOwner(element);
                }
            }*/
       }
       return false;
       
    }

    public boolean removeOwner(Owner owner){
        // Ifall arrayen är tom
        if( length < 1 /*|| owner.getDogs().size()>=1*/){ /*owner.getDogs().isEmpty() == false)*/ // Booleskt uttryck kan förenklas!
            System.out.println("OWNERCOLLECTION: No owners found");
            return false;
        }

        Owner[] copy = new Owner[length - 1 ];

        if( containsOwner(owner) ){
            // Hitta index på owner
            int indexUnwanted = 0;
            for( int i = 0; i < length; i++){
                if(arrayOfOwners[i].equals(owner)/*== owner */){
                    indexUnwanted = i;
                }
            }
            for( int j = 0; j< indexUnwanted; j++){
                copy[j] = arrayOfOwners[j];
            }
            for( int k = indexUnwanted + 1; k < length; k++ ){
                copy[k-1] = arrayOfOwners[k];
            }
        arrayOfOwners = copy;
        length -= 1;
        return true;
        } else{
        }        
        
        return false;
    }






    // Normalisera namn
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
    

}