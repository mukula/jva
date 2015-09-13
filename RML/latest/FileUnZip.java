import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileUnZip
{
    List<String> fileList;
	private boolean printExceptionStackTrace=false;
    
	public boolean unZip(File zip,File folder)
	{
	return unZip(zip.getAbsolutePath(),folder.getAbsolutePath());
	}
	
    public boolean unZip(String zipFile, String OUTPUT_FOLDER){
     
     byte[] buffer = new byte[1024];
 
     try{
 
    	//create output directory is not exists
    	File folder = new File(OUTPUT_FOLDER);
    	if(!folder.exists()){
    		folder.mkdir();
    	}
 
    	//get the zip file content
    	ZipInputStream zis = 
    		new ZipInputStream(new FileInputStream(zipFile));
    	//get the zipped file list entry
    	ZipEntry ze = zis.getNextEntry();
         int file_count=0;
    	while(ze!=null){
 
    	   String fileName = ze.getName();
           File newFile = new File(OUTPUT_FOLDER + File.separator + fileName);
 
           System.out.println("Recieving file : "+ newFile.getAbsoluteFile());
 
            //create all non exists folders
            //else you will hit FileNotFoundException for compressed folder
            new File(newFile.getParent()).mkdirs();
 
            FileOutputStream fos = new FileOutputStream(newFile);             
 
            int len;
            while ((len = zis.read(buffer)) > 0) {
       		fos.write(buffer, 0, len);
            }
 
            fos.close();   
            ze = zis.getNextEntry();
			file_count++;
    	}
 
        zis.closeEntry();
    	zis.close();
 
    	System.out.println("\n Recieved "+file_count+" files");
		return true;
 
    }catch(Exception ex){
       if(printExceptionStackTrace) ex.printStackTrace(); 
	   return false;
	   
    }
   }    
}