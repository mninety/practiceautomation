package Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * This processing file read our TestData properties file
 * Use on test cased where we need to pass our TestData properties file  datas
 * @since 2022-03-23
 * @author Mizan
 */
public class FileProcessing {
	Randomgenerator rand = new Randomgenerator();
	
	   public Properties readPropertiesFile(String fileName) throws IOException {
		      FileInputStream fis = null;
		      Properties prop = null;
		      try {
		         fis = new FileInputStream(fileName);
		         prop = new Properties();
		         prop.load(fis);
		      } catch(FileNotFoundException fnfe) {
		         fnfe.printStackTrace();
		      } catch(IOException ioe) {
		         ioe.printStackTrace();
		      } finally {
		         fis.close();
		      }
		      return prop;
		   }

		public void writePropertiesFile(String iOSDeviceName, String iOSDriverDeviceName, String driverDeviceName, String deviceName)
		{
			Properties props = new Properties();
			String fileName="TestData.properties";
			try (OutputStream output = new FileOutputStream(fileName)) {

				// Set or update property values
				props.setProperty("iOSDeviceName", iOSDeviceName);
				props.setProperty("iOSDriverDeviceName", iOSDriverDeviceName);
				props.setProperty("driverDeviceName", driverDeviceName);
				props.setProperty("deviceName", deviceName);


				// Save properties to file
				props.store(output, "Updated configuration on " + new java.util.Date());

				System.out.println("✅ Properties file updated successfully.");

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		   public String getPhonefromCSV(String fileName)
		   {
			   String randomPhone="";
			   List<String> phones = new ArrayList<>();

			   try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
				   br.readLine(); // Skip header
				   String line;
				   while ((line = br.readLine()) != null) {
					   String[] data = line.split("\n");
					   phones.add(data[0]);
				   }
			   } catch (IOException e) {
				   e.printStackTrace();
			   }

			   // Pick a random phone number
			   if (!phones.isEmpty()) {
				   randomPhone = phones.get(rand.getRandomNumberInRange(1,350));
				   System.out.println("Random Phone: " + randomPhone);
			   }
				return randomPhone;
		   }
	   
}
