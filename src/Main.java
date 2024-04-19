import java.io.IOException;

public class Main {
    public static void main(String[] arg) throws IOException {
        if (arg.length != 1) {
            System.err.println("Usage: java JsonVerifier <json_file>");
            System.exit(1);
        }

        String jsonData = JsonVerifier.readJsonFile(arg[0]);
        System.out.println(JsonVerifier.verifyJson(jsonData));
    }
}
