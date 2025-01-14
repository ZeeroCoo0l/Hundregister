

import java.util.ArrayList;
//import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;


public class DogRegister {

    private static final String EXIT_COMMAND = "exit";
    OwnerCollection owners = new OwnerCollection();
    DogCollection dogs = new DogCollection();
    private InputReader input = new InputReader();
    boolean stopLoop;
    

    private static void greetUser(){
        System.out.println("Welcome to the dog register!");
    }

    private static void menu(){
        System.out.println("The following commands are available:");
        System.out.println("* Register new dog");
        System.out.println("* Remove dog");
        System.out.println("* Register new owner");
        System.out.println("* Remove dog");
        System.out.println("* List dogs");
        System.out.println("* List owners");
        System.out.println("* Increase age");
        System.out.println("* Give dog to owner");
        System.out.println("* Remove dog from owner");
        System.out.println("* Exit");
        System.out.println();
        
    }

    private String readCommand(){
        System.out.println();
        String prompt = "Enter command";
        //System.out.print(prompt);
        return input.readString(prompt).toLowerCase();
    }

    private void runCommandLoop(){
        String command;
        menu();
        do{
            command = readCommand().trim();
            executeCommand(command);
            stopLoop = command.toLowerCase().equals(EXIT_COMMAND.trim());
        } while(!stopLoop);
    }
    // Load
    private void loadRegister(){
        Dog d1 = new Dog("Dog1", "Tax", 12, 9);
        Dog d2 = new Dog("Dog2", "Pekingese", 2, 5);
        Dog d3 = new Dog("Dog3", "Scäfer", 25, 100);

        Owner o1 = new Owner("Owner1");
        Owner o2 = new Owner("Owner2");
        Owner o3 = new Owner("Owner3");

        dogs.addDog(d1);
        dogs.addDog(d2);
        dogs.addDog(d3);
        owners.addOwner(o1);
        owners.addOwner(o2);
        owners.addOwner(o3);


        d1.setOwner(o1);
        d2.setOwner(o1);
        //d3.setOwner(o3);

        

        System.out.println("Register loaded!");

    }

    private void executeCommand(String command){
        command = command.toLowerCase();
        switch(command){
            case "register new dog":
                registerNewDog();
                break;
            case "remove dog":
                removeDog();
                break;
            case "register new owner":
                registerNewOwner();
                break;
            case "remove owner":
                removeOwner();
                break;
            case "list dogs":
                listDogs();
                break;
            case "list owners":
                listOwners();
                break;
            case "increase age":
                increaseAge();
                break;
            case "give dog to owner":
                giveDogToOwner();
                break;
            case "remove dog from owner":
                removeDogFromOwner();
                break;
            case "load":
                loadRegister();
                break;
            case "exit":
                // Hoppar ut ur switch-metoden och avslutar i main-metoden
                break;
            default:
                System.out.println("Error: '" + command + "' is not a recognised command");

        }
    }

    private void registerNewOwner() {
        String prompt = "Enter owner name";
        String name = input.readString(prompt);

        while(name.isBlank()){
            errorMessageForInput(prompt, name);
            name = input.readString(prompt);
        }

        if(owners.containsOwner(name)){
            System.out.println("Error: The owner %s already exists.".formatted(name));
        } else{
            Owner owner = new Owner(name);
            owners.addOwner(owner);
            System.out.println("The owner %s has been added to the register".formatted(owner.getName()));
        }
    }

    private int getLengthOfLongestOwnerName(){
        ArrayList<Owner> ownerList = owners.getOwners();
        if(ownerList.size()<1){
            return 0;
        }

        int maxValue = 0;
        for(Owner o: ownerList){
            if(maxValue < o.getName().length()){
                maxValue = o.getName().length();
            }
        }
        return maxValue;
    }

    private int getLengthOfLongestDogName(){
        ArrayList<Dog> dogList = dogs.getDogs();
        if(dogList.size()<1){
            return 0;
        }

        int maxValue = 0;
        for(Dog d: dogList){
            if(maxValue < d.getName().length()){
                maxValue = d.getName().length();
            }
        }
        return maxValue;
    }

    private int getSpaceBetweenColums(int length){
        int nameLength = length;
        if(nameLength < 10){
            int s = 10 - nameLength; 
            return s;
        } else{
            return 1;
        }
    }
    
    private int getOwnerlistWidth(){
        int sizeOfOwnerColumn = getLengthOfLongestOwnerName();
        int sizeOfDogColumn = getLengthOfLongestDogName();
        int space = getSpaceBetweenColums(sizeOfOwnerColumn);
        int width = sizeOfOwnerColumn + space + sizeOfDogColumn;
        return width;    
    }

    private void listOwners() {        
        ArrayList<Owner> ownerList = owners.getOwners();
        int width = getOwnerlistWidth();
        int indexOfDogs = getLengthOfLongestOwnerName() + 2;
        if(ownerList == null || ownerList.size() < 1){
            System.out.println("Error: No owners in register");
            return;
        } else{ 
            System.out.print("Name");
            fillSpaceLeftInColumTo(indexOfDogs -4);
            
            System.out.print("Dogs");

            System.out.println();
            for(int i = 0; i<=width; i++){
                System.out.print("=");
            }
            System.out.println();

            for(Owner owner: ownerList){
                ArrayList<Dog> dogsOfOwner = owner.getDogs();
                System.out.print(owner.getName());
                fillSpaceLeftInColumTo(owner.getName().length(), indexOfDogs);
                for(Dog d: dogsOfOwner){
                        if(dogsOfOwner.getLast() == d){
                            System.out.print(d.getName());    
                        } else{
                            System.out.print(d.getName() + ", ");
                        }
                    }
                System.out.println();
                }
            }
                

            }
    
    private void removeOwner() {
        if(isOwnerRegisterEmpty()){
            System.out.println("Error: No owners in register");
            return;
        }

        String name = input.readString("Enter owners name");
        if(!isOwnerInRegister(name)){
            System.out.println("The owner doesn't exist".formatted(name));
            return;
        }
        System.out.println("Innan bortagning: " + owners.getOwners());
        for(Owner o: owners.getOwners()){
            if(o.getName().equalsIgnoreCase(name)){
                for(Dog d:o.getDogs()){
                    //d.setOwner(null);
                    dogs.removeDog(d);
                }
                owners.removeOwner(o);
                System.out.println("Efter bortagning: " + owners.getOwners());
                break;
            }
        }
        /*
        String name = input.readString("Enter owners name");
        if(owners.getOwner(name) == null || owners.containsOwner(name) == false ){
            System.out.println("Error: The owner %s doesn't exist".formatted(name));
            return;
        }
        Owner owner = owners.getOwner(name);
        ArrayList<Dog> ownersDogs = owner.getDogs();
        if(ownersDogs == null){
            return;
        }
        if(ownersDogs.size()>0){
            for(int i = ownersDogs.size()-1; i >= 0;i--){
                String nameInLoop = ownersDogs.get(i).getName();
                //dogs.getDog(nameInLoop).setOwner(null);
                dogs.removeDog(ownersDogs.get(i));
            }
        }
        owners.removeOwner(owner);
        System.out.println("The owner %s is removed from register".formatted(owner.getName()));
        */
    }
    
    private void errorMessageForInput(String prompt, String name){
        System.out.println("Error: A blank string is not allowed, try again");
    }

    private void registerNewDog() {
        String prompt = "Enter dog name";
        String name = input.readString(prompt);
        while(name.isBlank()){
            errorMessageForInput(prompt, name);
            name = input.readString(prompt);
        }

        if(dogs.containsDog(name)){
            System.out.println("Error: The dog %s already exists.".formatted(name));
            return;
        }

        String breedPrompt = "Enter dog breed";
        String breed = input.readString(breedPrompt);
        while(breed.isBlank()){
            errorMessageForInput(breedPrompt, breed);
            breed = input.readString(breedPrompt);
        }
        
        int age = input.readInt("Enter dog age");
        int weight = input.readInt("Enter dog weight");
        Dog dog = new Dog(name, breed, age, weight);
        dogs.addDog(dog);
        System.out.println("Th dog %s has been added to the register".formatted(dog.getName()));
    }

    private void fillSpaceLeftInColumTo(int end){
        fillSpaceLeftInColumTo(0, end);
    }
    
    private void fillSpaceLeftInColumTo(int start, int end){
        for(int i=start; i<end;i++){
                System.out.print(" ");
            }
    }

    private int getLongestBreedName(ArrayList<Dog> dogList){
        ArrayList<String> breedList = new ArrayList<>();

        for(Dog dWithBreed: dogList ){
            if(!breedList.contains(dWithBreed.getBreed())){
                breedList.add(dWithBreed.getBreed());
            }
        }
        int maxValue = 0;
        for(String breed: breedList){
            if(maxValue < breed.length()){
                maxValue = breed.length();
            }
        }
        return maxValue;
    }

    private int getSpaceToWeigth(int age){
        if(age<10){
            return 1;
        }
        if(age>10 && age<100){
            return 2;
        } else{
            return 3;
        }
    }

    private int getSpaceToTail(int weight){
        if(weight<10){
            return 1;
        }
        if(weight>10 && weight<100){
            return 2;
        } else{
            return 3;
        }
    }

    private int getSpaceToOwner(double tailLength){
        if( tailLength<10.0){
            return 2;
        }
        if( tailLength>10 && tailLength<100){
            return 3;
        } else{
            return 4;
        }
    }

    private void menuInListDogs(int indexOfBreed, int indexOfAge, int indexOfWeight, int indexOfTail, int indexOfOwner){
        System.out.print("Name");
        fillSpaceLeftInColumTo(4,indexOfBreed);
        System.out.print("Breed");
        fillSpaceLeftInColumTo(indexOfBreed+6, indexOfAge);
        System.out.print("Age");
        fillSpaceLeftInColumTo(indexOfAge+4, indexOfWeight);
        System.out.print("Weight");
        fillSpaceLeftInColumTo(indexOfWeight+7, indexOfTail);
        System.out.print("Tail");
        fillSpaceLeftInColumTo(indexOfTail+5, indexOfOwner);
        System.out.print("Owner");
        System.out.println();
            for(int i = 0; i<=indexOfOwner+getLengthOfLongestOwnerName()-2; i++){
                System.out.print("=");
            }
        System.out.println();
    }

    private void listDogs(){
        //ArrayList<Dog> dogList = dogs.getDogs();
        //DogSorter sortDogs = new DogSorter();
        int indexOfBreed = getLengthOfLongestDogName() + 2;
        int indexOfAge = indexOfBreed + getLongestBreedName(dogs.getDogs())+3;
        int indexOfWeight = indexOfAge + 6 /*+ getSpaceToWeigth(getHighestAge())*/+2;
        int indexOfTail = indexOfWeight + 6 + 2;
        int indexOfOwner = indexOfTail + 5 + 2;

        if(dogs == null || dogs.getDogs().size()<1){
            System.out.println("Error: No dogs in register");
            return;
        }

        double minTailLength = input.readDouble("Enter minimum tail length");
        ArrayList<Dog> dogList = dogs.getDogsAboveTailLength(minTailLength);
        
        /*
        ArrayList<Integer> indexOfDogList = new ArrayList<>();
        for(int j = 0; j<dogList.size();j++){
            if((double)dogList.get(j).getTailLength()<(double)minTailLength){
                indexOfDogList.add(j);
            }
        for ( int i: indexOfDogList.reversed() ){
            dogList.remove(i);
        }*/
        if(dogList.size()<1){
            System.out.println("Error: No dogs with tail length");
        }
        menuInListDogs(indexOfBreed, indexOfAge, indexOfWeight, indexOfTail, indexOfOwner);
        // Meny
        /*System.out.print("Name");
        fillSpaceLeftInColumTo(4,indexOfBreed);
        System.out.print("Breed");
        fillSpaceLeftInColumTo(indexOfBreed+6, indexOfAge);
        System.out.print("Age");
        fillSpaceLeftInColumTo(indexOfAge+4, indexOfWeight);
        System.out.print("Weight");
        fillSpaceLeftInColumTo(indexOfWeight+7, indexOfTail);
        System.out.print("Tail");
        fillSpaceLeftInColumTo(indexOfTail+5, indexOfOwner);
        System.out.print("Owner");
        System.out.println();
            for(int i = 0; i<=indexOfOwner+getLengthOfLongestOwnerName()-2; i++){
                System.out.print("=");
            }
        System.out.println();
        */

        // Hundlistan
        for(Dog dog: dogList){
            System.out.print(dog.getName());
            fillSpaceLeftInColumTo(dog.getName().length(), indexOfBreed);
            System.out.print(dog.getBreed());
            fillSpaceLeftInColumTo(indexOfBreed + dog.getBreed().length() ,indexOfAge-1);
            System.out.print(dog.getAge());
            fillSpaceLeftInColumTo(indexOfAge + getSpaceToWeigth(dog.getAge()), indexOfWeight-1);
            System.out.print((dog.getWeight()));
            fillSpaceLeftInColumTo(indexOfWeight + getSpaceToTail(dog.getWeight()), indexOfTail-1);
            System.out.print((float)dog.getTailLength());
            fillSpaceLeftInColumTo(indexOfTail + getSpaceToOwner(dog.getTailLength()), indexOfOwner-1);
            if(dog.getOwner() != null)
                System.out.print(dog.getOwner().getName());
            System.out.println();
        }
    }

    private boolean isDogNameInRegister(String name){
        // Kontrollera register
        if(dogs == null || dogs.getDogs() == null || dogs.getDogs().size()<1){
            System.out.println("Error: No dogs in register");
            return false;
        }
        // Kontrollera String-objektet
        if(dogs.getDog(name) == null || dogs.containsDog(name) == false){
            System.out.println("Error: The dog %s doesn't exist".formatted(name));
            return false;
        }
        return true;
    }

    private boolean isOwnerInRegister(String name){
        if(owners == null || owners.getOwners().size()<1){
            System.out.println("Error: No owners in register");
            return false;
        }
        Owner owner = owners.getOwner(name);

        if(owner == null || !owners.containsOwner(name)){
            System.out.println("Error: The owner %s doesn't exist".formatted(name));
            return false;
        }
        return true;
    }

    private boolean dogAlreadyHaveOwner(String name){
        Dog dog = dogs.getDog(name);
        if(dog != null && dog.getOwner() != null){
            System.out.println("Error: Dog already have owner, can't give dog to owner");
            return true;
        }
        return false;

    }

    private void giveDogToOwner() {
        if(dogs.getDogs().isEmpty()){
            System.out.println("Error: No dogs in register");
            return;
        }
        if(owners.getOwners().isEmpty()){
            System.out.println("Error: No owners in register");
            return;
        }

        String dogName = input.readString("Enter dog name");
        
        if(dogAlreadyHaveOwner(dogName)){
            return;
        }
        if(!isDogNameInRegister(dogName)){ // dogs.getDogs()contains(dogName)
            //System.out.println("Error: Dog not in register");
            return;
        }
        
        String ownerName = input.readString(("Enter owner name"));
        if(!isOwnerInRegister(ownerName)){
            //System.out.println("Error: Owner not in register");
            return;
        }
        if(isDogNameInRegister(dogName) && isOwnerInRegister(ownerName)){
            Owner owner = owners.getOwner(ownerName);
            Dog dog = dogs.getDog(dogName);

            dog.setOwner(owner); // Sätt ägare till hund
            System.out.println("Before removal: " + dogs.getDog(dogName));
            //dogs.removeDog(dog); // Ta bort ägare och hund från samlingsklassen
            //owners.removeOwner(ownerName); //getOwner(ownerName)

            //System.out.println("After removal: " + dogs.getDog(dog));
            //owner.addDog(dog);

            //dogs.addDog(dog);
            System.out.println("After removal: " + dogs.getDog(dogName));
            //owners.addOwner(owner);
            System.out.println("The dog now have been given to owner");
        }
    }
    private boolean isDogRegisterEmpty(){
        if(dogs.getDogs().isEmpty()){
            return true;
        } 
        return false;
    }

    private boolean isOwnerRegisterEmpty(){
        if(owners.getOwners().isEmpty()){
            return true;
        }
        return false;
    }

    private void removeDogFromOwner() {
        if(isDogRegisterEmpty()){
            System.out.println("Error: No dogs in register");
            return;
        }
        if(isOwnerRegisterEmpty()){
            System.out.println("Error: No owners in register");
            return;
        }
        
        String name = input.readString("Enter dog name");
        
        if(isDogNameInRegister(name)){
            Dog dog = dogs.getDog(name);
            dog.getOwner().removeDog(dog);
            dog.setOwner(null);
            

            /*if(dog.getName().equals(null) || dog.getOwner() == null){
            } else{
                owners.removeOwner(dog.getOwner());
                dog.setOwner(null);
            }
            System.out.println("The dog %s now have no owner".formatted(dog.getName()));*/
        }
    }

    private void increaseAge() {
        if(dogs.getDogs().size()<1){
            System.out.println("Error: No dogs in register");
            return;
        }
        
        String name = input.readString("Enter dog name");
        if(isDogNameInRegister(name)){
            Dog dog = dogs.getDog(name);
            dog.addOneYearToAge();
            System.out.println("The dog %s is now one year older".formatted(dog.getName()));
        }
        

    }
    
    private void removeDog() {
        if(isDogRegisterEmpty()){
            System.out.println("Error: No dogs in register");
            return;
        }
        if(isOwnerRegisterEmpty()){
            System.out.println("Error: No owners in register");
            return;
        }
        /*        
        if(dogs == null || dogs.getDogs() == null || dogs.getDogs().size() < 1 || dogs.getDogs() == null){
            System.out.println("Error: No dogs in register");
            return;
        }*/
        String name = input.readString("Enter dog name");
        if(isDogNameInRegister(name)){
            Dog theDog = dogs.getDog(name);

            for(Owner o: owners.getOwners()){
                if(o.getDogs().contains(theDog)){
                    //o.removeDog(theDog);
                    theDog.setOwner(null);
                    break;
                }
            }
            
            /*if(theDog.getOwner() != null){
                dogs.getDog(name).setOwner(null);
            }*/
            dogs.removeDog(theDog); // Ta bort hund från register
            System.out.println("The dog %s has been removed from the register".formatted(theDog.getName()));

        } /*else{
            System.out.println("Error: the Dog %s doesn't exist".formatted(name));
        }*/
    }




    private void shutDown(){
        input.close();
        System.out.println("Dog register shut down");
    }

    private void execute(){
        greetUser();
        runCommandLoop();
        shutDown();
    }
    
    public static void main(String[] args){
        new DogRegister().execute();
    }



}

