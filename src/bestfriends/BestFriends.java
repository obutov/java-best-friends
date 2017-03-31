/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bestfriends;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;


/**
 *
 * @author onb
 */
public class BestFriends {
    
    static Dictionary<Integer, List> friendsDictionary;
    static int numberOfFriends = 5;
    static int maxValue = Integer.MAX_VALUE;
    static List visited;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        //int[] from = new int[] {1, 1, 2, 2, 2, 2, 3, 3, 4, 4, 5, 5};
        //int[] to = new int[] {2, 3, 1, 3, 4, 5, 1, 2, 2, 5, 2, 4};
                
        int[] from = new int[]  {1, 1, 1, 2, 2, 2, 3, 4, 4, 5, 5, 5};
        int[] to = new int[]    {1, 3, 5, 1, 4, 5, 1, 2, 5, 1, 2, 4};        
        
        friendsDictionary = new Hashtable();
        
        // build friends dictionary from inputs
        for (int i=0; i < from.length; i++){
            int friendKey = from[i];
            List bestFriends = friendsDictionary.get(friendKey);
            if (bestFriends == null){
                bestFriends = new ArrayList();                
            }
            bestFriends.add(to[i]);
            friendsDictionary.put(friendKey, bestFriends);
        }
        // initialize visited list
        visited = new ArrayList();
        int minScore = maxValue;                
        
        // build tree for each friend
        for (int friendId = 1; friendId <= numberOfFriends; friendId++){
            
            // we'll search for all possible tree paths
            List<TreeNode> paths = new ArrayList();
            
            TreeNode root = new TreeNode(friendId, null);

            if (DepthTraverseTree(root, 3, friendId, paths)){
                for (TreeNode path : paths){
                    int score = path.GetScore();
                    if (score < minScore)
                        minScore = score;
                }
            }  
            // keep track of visited friend ids, so these are excluded later
            visited.add(friendId);            
        }
        
        System.out.println("Min Score = "+minScore);
        
    }
    
    public static boolean DepthTraverseTree(TreeNode root, int level, int treeTopId, List<TreeNode> paths){  
        // terminate if reached depth limit
        if (level == 0){
            // if node's id matches root id - we found a path
            // add path leaf node to paths list
            if (root.GetId() == treeTopId){
                paths.add(root);
                return true;
            }else{
                return false;
            }            
        }       
      
        root.MakeChildren(friendsDictionary.get(root.GetId()));
        
        for (TreeNode child : root.GetChildren()){
            if (!visited.contains(child.GetId())){
                DepthTraverseTree(child, level-1, treeTopId, paths);
            }
        }
        return paths.size() > 0;
    }       
}
