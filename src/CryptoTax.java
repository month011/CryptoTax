import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CryptoTax {

	public static void main(String[] args)
			// TODO Auto-generated method stub
			throws IOException {
		try {
//		List<String> List  = new ArrayList<>();
			List<List<String>> outerArrayList = new ArrayList<>();

			String file = "crypto_tax.txt";

			// load content of file based on specific delimiter
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			String mesError = "";
			float sum = (float) 0.0;
			while ((line = br.readLine()) != null) {
				// process the line.
				List<String> List = new ArrayList<>();
				String[] strSplit = line.split(" ");

				if (outerArrayList.isEmpty()) {
					if (strSplit[0].equals("S")) {
						mesError = "Error";
						break;
					}
					for (String a : strSplit) {
						List.add(a);
					}
					outerArrayList.add(List);
				} else {

					for (String a : strSplit) {
						List.add(a);
					}
					outerArrayList.add(List);

					float coin = 0;

					if (("S").equals(strSplit[0])) {
						coin = Float.parseFloat(strSplit[3]);
						for (int j = 0; j < outerArrayList.size(); j++) {

							if ((strSplit[1]).equals(outerArrayList.get(j).get(1))
									&& ("B").equals(outerArrayList.get(j).get(0))) {

								if (Float.parseFloat(outerArrayList.get(j).get(3)) > 0) {
									coin = coin - Float.parseFloat(outerArrayList.get(j).get(3));
								}

								if (coin > 0) {
									sum = sum + ((Float.parseFloat(strSplit[2])
											- Float.parseFloat(outerArrayList.get(j).get(2)))
											* Float.parseFloat(outerArrayList.get(j).get(3)));
									outerArrayList.get(j).set(3, "0");

								} else {

									sum = sum + ((Float.parseFloat(strSplit[2])
											- Float.parseFloat(outerArrayList.get(j).get(2)))
											* (Float.parseFloat(outerArrayList.get(j).get(3)) + coin));
									outerArrayList.get(j).set(3, String.valueOf(coin * -1));
									break;
								}

							}

						}
						if (coin > 0) {
							mesError = "Error";
							break;
						}

					}

				}
			}

			br.close();
			if (!mesError.isEmpty()) {
				System.out.println(mesError);
			} else {
				System.out.println(sum);
			}
		} catch (IOException e) {
			System.out.println("The file could not be opened");
		}
	}

}
