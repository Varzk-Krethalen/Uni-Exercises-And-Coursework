/**
 * Stores a line of a Java program for later analysis
 */
class JavaLine
{
  private String java    = ""; // Java code on line
  private String comment = ""; // The single line comment
  private int    lenJava = 0;  // The line length of just the java code
  static int brackets = 0;

  /**
   * Constructor
   * @param line of a java program
   */
  public JavaLine( String line )
  {
    // Split line into the Java code part 'variable java' and the // comment
    // into 'variable comment'.
    // Trailing spaces are removed from the variable 'java'
    // Record the length of the java code part of the line
    // into the variable 'lenJava'
    int slashIndex = line.lastIndexOf("//");
    int quoteIndex = line.lastIndexOf("\"\"");
    if (line.contains("//") && ( slashIndex > quoteIndex))
    {
        java = line.substring(0, slashIndex);
        comment = line.substring(slashIndex);
        java = java.trim();
    }
    else
    {
        comment = "";
        java = line.trim();
    }
  }


  /**
   * Return the length of the Java part of the stored line.<PRE>
   * JavaLine j = new JavaLine("int a; // Declaration");
   * int jp = j.getJavaLineLength();
   * Would set jp the be 6</PRE>
   * @return The length of the Java code in the line
   */
  
  public int getJavaLineLength()
  {
      lenJava = java.length();
      return lenJava;
  }

  /**
   * Return as an 'indented' line with the // comment
   * starting at column pos<PRE>
   * JavaLine j = new JavaLine("int a; // Declaration");
   * String res = j.returnLineWithCommentAt(10);
   * Would set res to be the following string:
   * int a;   // Declaration</PRE>
   * @param pos Start // comment at pos
   * @return A new version of the line with any // comment
   * starting at column pos.
   */
  public String returnLineWithCommentAt(int pos )
  {
      String finalLine = java;
      int number = pos - java.length();
      finalLine += spaces(number);
      finalLine += comment;
      return finalLine;
  }

  /**
   * Return a string of 'number' spaces.
   * @param number of spaces required
   * @return A string of 'number' spaces
   */
  public static String spaces( int number )
  {
      String spaces = "";
      for ( ; number > 0; number--)
      {
          spaces += " ";
      }
      return spaces;
  }
  
  public void indentLine(int indent)
  {
      int openCount = 0;
      int closeCount = 0;
      if (java.contains("}") && !java.contains("{"))
      {
          if (!(java.lastIndexOf("}") > java.lastIndexOf("\"")))
          {
              indent++;
          }
          for (int i = indent; i > 1; i--)
          {
              java = "  " + java;
          }
      }
      else if (java.contains("}") && java.contains("{"))
      {
          for (int i = 0; i < java.length(); i++)
          {
              if (java.charAt(i) == '{')
              {
                  openCount++;
              }
              else if (java.charAt(i) == '}')
              {
                  closeCount++;
              }
          }
          if (openCount < closeCount)
          {
              brackets -= (closeCount - openCount);
          }
          else if (openCount > closeCount)
          {
              brackets += (openCount - closeCount);
          } 
          for (int i = indent; i > 0; i--)
          {
              java = "  " + java;
          }
      }
      else
      {
          if ( (java.contains("\"")) && java.contains("{") && (!(java.lastIndexOf("{") > java.lastIndexOf("\""))))
          {
              brackets--;
          }
          for (int i = indent; i > 0; i--)
          {
              java = "  " + java;
          }
      }
  }
  
  public int checkBrackets()
  {
      int openIndex = java.lastIndexOf("{");
      int closeIndex = java.lastIndexOf("}");
      int quoteIndex = java.lastIndexOf("\"\"");
      if (java.contains("{") && !java.contains("}") && ((openIndex > quoteIndex) || !java.contains("\"")))
      {
          brackets++;
      }
      else if (java.contains("}") && (!java.contains("{")) && ((closeIndex < quoteIndex) || !java.contains("\"")))
      {
          brackets--;
      }
      return brackets;
  }
}

