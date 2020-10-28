/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pythonprogramtester;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 * @author Willhoft_Robert
 */
public class OperatingSystemWindows implements OperatingSystem
{
    public boolean copy( String srcFilePath, String destFilePath )
    {
        try
        {
            Path srcPath = FileSystems.getDefault().getPath( srcFilePath );
            Path destPath = FileSystems.getDefault().getPath( destFilePath );
            Files.copy( srcPath, destPath );
            return true;
        }
        catch( IOException ex )
        {
            System.out.println( ex.getMessage() );
            return false;
        }
    }
}
