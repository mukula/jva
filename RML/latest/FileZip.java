import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
 
public class FileZip
{
    List<String> fileList;
    private  String OUTPUT_ZIP_FILE; 
    private  String SOURCE_FOLDER ;
 
    FileZip(){
	fileList = new ArrayList<String>();
    }
 
    public boolean zip(String s,String f)
	{
	OUTPUT_ZIP_FILE=f;
	SOURCE_FOLDER=s;
	generateFileList(new File(s));
	 return zipIt(f);
	}
	
    private boolean zipIt(String zipFile){
 
     byte[] buffer = new byte[1024];
 
     try{
 
    	FileOutputStream fos = new FileOutputStream(zipFile);
    	ZipOutputStream zos = new ZipOutputStream(fos);
 
    	//System.out.println("Output to Zip : " + zipFile);
         int y=0;
    	for(String file : this.fileList){
            y++;
    		System.out.println("File Sent : " + file);
    		ZipEntry ze= new ZipEntry(file);
        	zos.putNextEntry(ze);
 
        	FileInputStream in = 
                       new FileInputStream(SOURCE_FOLDER + File.separator + file);
 
        	int len;
        	while ((len = in.read(buffer)) > 0) {
        		zos.write(buffer, 0, len);
        	}
 
        	in.close();
    	}
 
    	zos.closeEntry();
    	//remember close it
    	zos.close();
        
    	System.out.println("\n "+y+" Files Sent");
		return true;
    }catch(IOException ex){
       ex.printStackTrace();
        return false;   
    }
   }
 
    
    private void generateFileList(File node){
 
    	//add file only
	if(node.isFile()){
		fileList.add(generateZipEntry(node.getAbsoluteFile().toString()));
	}
 
	if(node.isDirectory()){
		String[] subNote = node.list();
		for(String filename : subNote){
			generateFileList(new File(node, filename));
		}
	}
 
    }
 
    
    private String generateZipEntry(String file){
    	return file.substring(SOURCE_FOLDER.length()+1, file.length());
    }
}