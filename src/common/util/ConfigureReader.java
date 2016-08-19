package common.util;

import java.io.InputStream;
import java.util.Properties;

public class ConfigureReader {

	private final String SDAAC_CONF = "/conf/sdaac.properties";

	public String getSDAACConf(String propertyName) {
		Properties prop = new Properties();
		try {
			InputStream is = getClass().getResourceAsStream(SDAAC_CONF);
			prop.load(is);
			if (is != null)
				is.close();
		} catch (Exception e) {
			System.out.println(e + "file " + SDAAC_CONF + " not found");
		}
		return prop.getProperty(propertyName);
	}

}
