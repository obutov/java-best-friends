/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bestfriends;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author onb
 */
public class TreeNode {
    
    private TreeNode _parent;
    private int _id;
    private List<TreeNode> _children;    
    
    public TreeNode(int id, TreeNode parent){
        _parent = parent;
        _id = id;
    }     
    
    public int GetScore(){
        int score = 0;
        TreeNode leaf = this;
        List<TreeNode> visited = new ArrayList();
        do{
            if (leaf._children != null){
                leaf._children.removeAll(visited);
                score += leaf._children.size();                
            }
            visited.add(leaf);
            leaf = leaf._parent;
        }while (leaf != null);
        return score;
    }
    
    public void MakeChildren(List<Integer> childrenIds){        
        this._children = new ArrayList();  
        
        if (childrenIds != null && childrenIds.size() > 0){                
            for (int childId : childrenIds){
                if (this._parent == null || childId != this._parent._id){
                    TreeNode child = new TreeNode(childId, this);
                    this._children.add(child);
                }
            }
        }       
    } 
    
    public List<TreeNode> GetChildren(){
        return this._children;
    }
    
    public int GetId(){
        return _id;
    }
}
