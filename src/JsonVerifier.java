import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonVerifier {

    public static boolean isAwsIamRolePolicy(JSONObject jsonData) {
        try {
            if (!jsonData.has("PolicyName") || !jsonData.has("PolicyDocument")) {
                return false;
            }

            JSONObject policyDocument = jsonData.getJSONObject("PolicyDocument");
            if (!policyDocument.has("Version") || !policyDocument.has("Statement")) {
                return false;
            }

            if (!(policyDocument.get("Statement") instanceof JSONArray)) {
                return false;
            }

            JSONArray statements = policyDocument.getJSONArray("Statement");
            for (int i = 0; i < statements.length(); i++) {
                if (!(statements.get(i) instanceof JSONObject)) {
                    return false;
                }
            }

            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    public static boolean verifyJson(String jsonData) {
        try {
            JSONObject data = new JSONObject(jsonData);
            if(isAwsIamRolePolicy(data)) {
                JSONObject policyDocument = data.getJSONObject("PolicyDocument");
                JSONArray statements = policyDocument.getJSONArray("Statement");
                for (int i = 0; i < statements.length(); i++) {
                    JSONObject statement = statements.getJSONObject(i);
                    String resource = statement.getString("Resource");
                    if ("*".equals(resource.trim())) {
                        return false;
                    }
                }
            }
            return true;
        } catch (JSONException e) {
            return true;
        }
    }

    public static String readJsonFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }
}