package my.samples;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;

public class FileOperations {

	public static void main(String[] args) {
		System.out.println(Files.exists(Paths.get("/Users/gurunatha/Documents/Ram/workspace/Test/src/my/samples/process.json"),LinkOption.NOFOLLOW_LINKS));
		System.out.println(getJSON("/Users/gurunatha/Documents/Ram/workspace/Test/src/my/samples/process.json"));

	}
	
	static String getJSON(String path) {
		String processJSON = null;
		try {
			byte[] encoded = Files.readAllBytes(Paths.get(path));
            processJSON = new String(encoded, "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return processJSON;
	}

}
