// from:  http://www.javaworld.com/article/2071275/core-java/when-runtime-exec---won-t.html?page=2


// This file is unused at this point //

package pythonprogramtester;

import java.util.*;
import java.io.*;

class StreamGobbler extends Thread
{
    InputStream is;
    String type;
    OutputStream os;
    
    StreamGobbler(InputStream is, String type)
    {
        this(is, type, null);
    }
    StreamGobbler(InputStream is, String type, OutputStream redirect)
    {
        this.is = is;
        this.type = type;
        this.os = redirect;
    }
    
    public void run()
    {
        try
        {
            PrintWriter pw = null;
            if (os != null)
                pw = new PrintWriter(os);
                
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line=null;
            while ( (line = br.readLine()) != null)
            {
                if (pw != null)
                    pw.println(line);
                System.out.println(type + ">" + line);    
            }
            if (pw != null)
                pw.flush();
        } catch (IOException ioe)
            {
            ioe.printStackTrace();  
            }
    }
}
public class WindowsPythonExec
{
    public int run( String command, String path ) {
        try {            
            FileOutputStream fos = new FileOutputStream( /*args[0]*/ "" );
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec( command );
            // any error message?
            StreamGobbler errorGobbler = new 
                StreamGobbler(proc.getErrorStream(), "ERROR");            
            
            // any output?
            StreamGobbler outputGobbler = new 
                StreamGobbler(proc.getInputStream(), "OUTPUT", fos);
                
            // kick them off
            errorGobbler.start();
            outputGobbler.start();
                                    
            int exitVal = proc.waitFor();

            fos.flush();
            fos.close();

            return exitVal;
            
        } catch (Throwable t)
          {
            t.printStackTrace();
            return -1;
          }
    }
}