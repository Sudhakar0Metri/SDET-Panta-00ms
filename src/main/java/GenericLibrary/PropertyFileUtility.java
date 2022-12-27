package GenericLibrary;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertyFileUtility {
	Properties prop;
     public PropertyFileUtility() {
    	 File file = new File("./src/test/resources/congig.properties");
    	 
         try{
        	 FileInputStream fs= new FileInputStream(file);
        	 prop = new Properties();
        	 prop.load(fs);
        	 
         }catch(Exception e) {
        	 System.out.println("Exception is"+e.getMessage());
         }
     }
     public String getChromePath() {
    	 String Chromepath = prop.getProperty("Chromepath");
        	 return Chromepath;
     }  
     public String getBaseURL() {
    	 String Baseurl = prop.getProperty("Baseurl");
    	 return Baseurl;
     }
     public String getBrowser() {
    	 String Browser = prop.getProperty("Browser");
    	 return Browser;
     }
     public String getFirefoxPath() {
    	 String Firefox = prop.getProperty("Firefox");
    	 return Firefox;
     }
}
