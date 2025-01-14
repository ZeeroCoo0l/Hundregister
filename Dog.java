

import java.util.ArrayList;

public class Dog {

    // Instansvariabler
    private String name;
    private int age;
    private int weight;
    private String breed;
    //private ArrayList<Owner> ownerOfDog = new ArrayList<>();
    private Owner ownerOfDog;

    

    // Konstruktor
    public Dog(String name, String breed, int age, int weight){
        this.name = this.normalizeName(name);
        this.breed = this.normalizeName(breed);
        this.age = age;
        this.weight = weight;
    }


    // Metoder
    public String getName(){
            return name;
    }

    public String getBreed(){
        return breed;
    }

    public int getAge(){
        //return LocalDate.now().getYear() - yearOfBirth;
        return age;
    }
  
    public void addOneYearToAge(){
        final int maxValueInt = 2147483647;
        
        if(age >= maxValueInt){
            age = maxValueInt;
        }else{
            age += 1;
        }
    }

    public int getWeight(){
        return weight;
    }

    public double getTailLength(){
        String[] taxLang = {"Tax", "Dachshund", "Mäyräkoira", "Teckel"};
        final double daschhundTailLength = 3.7;
        for(String element: taxLang){
            if(breed.equals(element)){
                return daschhundTailLength;
            }
        }
        double weightDog = age * ((double)weight/10);
        //age * (weight/10);
        return weightDog;
    }

    public String toString(){
        if(this.getOwner() == null){
            return "name= %s breed= %s age= %d weight= %d taillength= %.2f".formatted(name, breed, age, weight, getTailLength());    
        }
        return "name= %s breed= %s age= %d weight= %d taillength= %.2f owner= %s".formatted(name, breed, age, weight, getTailLength(), getOwner().getName());
            
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

    public boolean setOwner(Owner owner){ 
        // ta bort ägare av hund
        if(owner == null){
            if(this.ownerOfDog != null){
                this.ownerOfDog = null;
            }
            return true;
        }
        // Lägga till ägare av hund

        if(this.getOwner()!= null){
            System.out.println("Error: Dog Already has an owner");
            return false;
        }

        owner.addDog(this);

        this.ownerOfDog = owner;
        System.out.println("Owner set successfully");
        return true;

       
    }

    public Owner getOwner(){
        
        /*if(ownerOfDog.size()<1){
            ownerOfDog.add(null);
        }
        Owner copy = ownerOfDog.get(0);
        */
        Owner copy = ownerOfDog;
        return copy;
    }





}
