package config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {
	public String baseUrl="";
	public void property() throws IOException {
		FileReader reader=new FileReader("src/test/resources/values.properties");  
	      
	    Properties p=new Properties();  
	    p.load(reader);  
	      
	    baseUrl=p.getProperty("baseurl");
	}
}
