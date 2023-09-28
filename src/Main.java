import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
      int[][] test = {{1,2},{3,5},{6,7},{8,10},{12,16}};
    }
}

// time complexity O(n), only one iteration
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>(); // no reindexing, just add intervals to ArrayList then convert and return later
        for(int[] in: intervals){ //loop each interval
            if(in[1] < newInterval[0]){ // if end index of current interval before start index of newInterval,
                result.add(in); // add current interval to ArrayList
            } else if (newInterval[1] < in[0]){ //  if end value of newInterval is < start value of currentInterval, then
                result.add(newInterval); // add newInterval to ArrayList
                newInterval = in; // this portion is to handle adding the remainder of intervals left in intervals[][]
                // after the original interval added, such that this statement will loop and add all remaining intervals
                // since they are sorted in ascending order and non-overlapping
            } else{ //otherwise, add no interval to the ArrayList, but merge the currentInterval with newInterval and set
                // new values to be used in subsequent while loops
                newInterval[0] = Math.min(newInterval[0], in[0]);
                newInterval[1] = Math.max(newInterval[1], in[1]);
            }
        }
        result.add(newInterval); // will get to this stament using example above after newInterval 3,10 added while 'in' is
        // 12,16. then newInterval reassigned to equal 'in'. Loop complete, but 12,16 not added, so added here.
        // assigned as 12,16 which is then
        return  result.toArray(new int[result.size()][]); // not sure why but JVM doesn't just naturally convert List of
        // nested arrays into a multidimensional array. gotta give it some help by telling it we are creating a new
        // multidimensional array of new int[3][]
    }
}





//attempt
//
//class Solution {
//    public int[][] insert(int[][] intervals, int[] newInterval) {
//        int L = 0;
//        int R = 0;
//        for(int i = 0; intervals.length(); i++){
//            if(newInterval[0] > intervals[i][1] && newInterval[1] < intervals[i+1][0]){ // no overlaps
//                //insert and reindex here
//            }
//            if(newInterval[0] > intervals[i][0] and newInterval[0] <= intervals[i][1]){ //store L overlap
//                L = i;
//            }
//            if(newInterval[1] < intervals[i][1]){ // engulfing interval
//                R = i;
//            }
//            if(newInterval[1] > intervals[i][1] && newInterval[1] < intervals[i+1][0]{
//                R = i+1;
//            })
//        }
//    }
//}
// 4 scenarios: no overlaps, overlapping to L, overlapping to R, overlapping L and R.

// must check newInterval [0] compared to interval[i][0] and interval[i][1]. if between then mark that i-th index. then search for if
// newInterval [1] is less than nextIndex[i][0], if yes then break and merge with first stored interval. if not then compare to newIndex[i][1]
// if less then store the new index in intervals[][].

// example [1,3],[6,9]. insert [2,7]. 2 is not less than start val of index 0 but is less than end val so store index 0. 7 is not less than 6
// but is less than 9 so store index 1. then need to reindex which sucks for time but outputs [1,9]

// must also account for situation for 1 stored index to L or to R such as inserting [2,4] or [5,7]

// third scenario of no overlaps [4,5]