
/**
 * Write a description of class Filter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Filter
{
    private int field, relation;
    String target; 

    public Filter(int field, int relation, String target)
    {
        this.field = field; 
        this.relation = relation; 
        this.target=target; 
    }

    public int getField()
    {
        return field; 
    }

    public String getTarget()
    {
        return target;
    }

    public int getRelation()
    {
        return relation;
    }
    
    public String toString()
    {
        return "\nField: "+field+"; Relation: "+relation+"; Target: "+target+"\n";
    }
    
}
