

import java.util.ArrayList;
import java.util.Comparator;


public class DogSorter {

    // Metod för att byta plats på två hundar
    private static ArrayList<Dog> swapDogs(ArrayList<Dog> dogList, int indexOne, int indexTwo){
        ArrayList<Dog> localList = dogList;
        Dog saveSpaceOne;
        Dog saveSpaceTwo;

        saveSpaceOne = localList.get(indexOne);
        saveSpaceTwo = localList.get(indexTwo);

        localList.set(indexTwo, saveSpaceOne);
        localList.set(indexOne, saveSpaceTwo);
        return localList;
    }

    // Metod för att hitta nästa hund att flytta på
    private static int nextDog(Comparator<Dog> comparator, ArrayList<Dog> dogList, int index){
        int length = dogList.size();
        int returnMinIndex = index;

        Dog minValue = dogList.get(index);
        int indexOfMinValue = index;
            
            // Inre loopen
            for(int j = index + 1; j < length; j++){
                if(comparator.compare(dogList.get(j), minValue)<0){
                    minValue = dogList.get(j);
                    indexOfMinValue = j;
                }
            }
            returnMinIndex = indexOfMinValue;
        return returnMinIndex;
    }

    // Metod för att sortera hundarna
    public static int sortDogs(Comparator<Dog> comparator,ArrayList<Dog> dogList){
        int length = dogList.size();
        int swapsCount = 0;
        
        // Yttre loopen
        for( int index = 0; index < length - 1;index++ ){

            // Kalla på inre loopen
            int nextDogObject = nextDog(comparator, dogList, index);
            // Bytet
            if(nextDogObject != index){
                dogList = swapDogs(dogList, index, nextDogObject );
                swapsCount += 1;
            }
        }
        //return dogList;
        return swapsCount;

    }

}