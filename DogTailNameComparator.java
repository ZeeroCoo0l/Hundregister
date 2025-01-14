

import java.util.Comparator;

public class DogTailNameComparator implements Comparator<Dog>{

    public int compare(Dog first, Dog second){
        DogTailComparator comparingTail = new DogTailComparator();
        DogNameComparator comparingName = new DogNameComparator();
        int result = comparingTail.compare(first, second);
        if(result < 0){
            return -1;
        }
        if( result > 0){
            return 1;
        }
        else{
            result = comparingName.compare(first, second);
            if(result < 0){
                return -1;
            }
            if(result > 0){
                return 1;
            }
            else{
                return 0;
            }
        }

    } 
    
}
