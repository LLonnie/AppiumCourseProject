package TestData;

import org.testng.annotations.DataProvider;

public class ApiAppData {

	@DataProvider(name = "EmailNames")
	public static Object[][] getDataForEmailNames() {
		return new Object[][] {
				{"This is my WiFi homie."},
				{"D0e$ th!s W!F! W0rk?"}
		};
	}
}
