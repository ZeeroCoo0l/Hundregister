

import java.util.ArrayList;
import java.util.Comparator;

public class DogCollection {
    
    private ArrayList<Dog> listOfDogs = new ArrayList<>();

    // Metoder
    public boolean addDog(Dog dog){
        if(containsDog(dog)){
            //System.out.println("Hunden lades inte till i registret");
            return false;
        }else{
            listOfDogs.add(dog);
        return true;
        }
    }

    public boolean removeDog(String name){
        
        Dog dogWithSameName;

        for(int i = 0; i < listOfDogs.size(); i++){
            if(listOfDogs.get(i).getName().equals(name)){ 
                dogWithSameName = listOfDogs.get(i);
                removeDog(dogWithSameName);
                return true;
            }
        }
        
        return false;
    }

    public boolean removeDog(Dog dog){

        /*if(dog.getOwner() = null){ // != null
            System.out.println("Tog inte bort hunden från registret, hundens ägare == null");
            return false;
        }*/

        if(listOfDogs.contains(dog)){
            if(dog.getOwner() != null)
                dog.setOwner(null);
            int index = listOfDogs.indexOf(dog);
            listOfDogs.remove(index);
            return true;
        } else{
            System.out.println("Tog inte bort hunden från registret, hund inte i registret");
            return false;
        }
        }


    public boolean containsDog(String name){
        name = name.toLowerCase();
        for(Dog element:listOfDogs){
            if(element.getName().toLowerCase().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    public boolean containsDog(Dog dog){
        if(dog == null || dog.getName() == null){
            return false;
        }
        String name = dog.getName();
        return containsDog(name);
    }

    public Dog getDog(String name){
        if(containsDog(name)){
            for(int i = 0; i < listOfDogs.size(); i++){
                if(listOfDogs.get(i).getName().equalsIgnoreCase(name)){
                    return listOfDogs.get(i);
                }
            }
        }
        return null;
    }

    public ArrayList<Dog> getDogs(){
        ArrayList<Dog> sortedDogs = new ArrayList<>(listOfDogs);
        Comparator<Dog> sortWithTailName = new DogNameComparator();
        DogSorter.sortDogs(sortWithTailName, sortedDogs);
        return sortedDogs;
    }

    public ArrayList<Dog> getDogsAboveTailLength(double num){
        ArrayList<Dog> dogs = new ArrayList<>(); //this.getDogs();
        Comparator<Dog> sortWithTailName = new DogTailNameComparator();

        for(Dog element: listOfDogs){
            if(element.getTailLength() >= num){
            dogs.add(element);
            }
        }
        DogSorter.sortDogs(sortWithTailName, dogs);
        return dogs;
    }




    }




