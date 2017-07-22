package engToBoolParser;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Bool_Parser 
{
	public static void main (String[] args)
    {	    
		JFrame frame = new JFrame("Boolean text input.");
	    String stmt = JOptionPane.showInputDialog(frame, "Please input your statement using FALSE, TRUE, NOT, AND, OR, (). operators only.  These are CASE SENSITIVE!");
	    //I'm a UI guy, user interaction is my thing.  It's more fun than Scanner System.in.
	
	    if(stmt != null && stmt.trim().length() > 0)
	    {
	    	String[] tempStmt = stmt.split("\\.");//Period terminates the expression, so why not disregard everything after?
	    	stmt = tempStmt[0].replaceAll("AND", "&&").replaceAll("NOT", "!").replaceAll("OR", "||");//Pretty self-exp: replace English with code
	    	//System.out.println(stmt);
	    	
			ScriptEngineManager sem = new ScriptEngineManager();
		    ScriptEngine eng = sem.getEngineByName("JavaScript");//What can I say, I love JS
		    //This bit of code basically lets you run JS in Java
		    //It's kind of like Hibernate, but for JS instead of SQL
		    
		    try 
		    {
		    	//Safety first: wrap it in a try-catch.  If the user enters bad stuff, it needs to catch it
			    boolean result = Boolean.valueOf((boolean) eng.eval(stmt.toLowerCase()));
			    JOptionPane.showMessageDialog(frame, "Your statement has evaluated to: " + String.valueOf(result).toUpperCase());
			    //System.out.println(result);
		    }
		    
		    catch (ScriptException e) 
		    {
		    	//A nice syserr for when the user messes up
			    JOptionPane.showMessageDialog(frame, "You have a sytax or grammatical error!\r\nPlease remember all operands are case sensitive!\r\n" + e);
		    	System.err.println(e);
		    }
		    
		    finally
		    {
		    	//And this is because I always forget to manually terminate during testing
		    	System.exit(0);
		    }
	    }
    }
}