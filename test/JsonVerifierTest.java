import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class JsonVerifierTest {

    @Test
    public void testVerifyJsonWithAsterisk() {
        String jsonData = """
                {
                    "PolicyName": "root",
                    "PolicyDocument": {
                        "Version": "2012-10-17",
                        "Statement": [
                            {
                                "Sid": "IamListAccess",
                                "Effect": "Allow",
                                "Action": [
                                    "iam:ListRoles",
                                    "iam:ListUsers"
                                ],
                                "Resource": "*"
                            }
                        ]
                    }
                }
                """;

        assertFalse(JsonVerifier.verifyJson(jsonData));
    }

    @Test
    public void testVerifyJsonWithoutAsterisk() {
        String jsonData = """
                {
                    "PolicyName": "root",
                    "PolicyDocument": {
                        "Version": "2012-10-17",
                        "Statement": [
                            {
                                "Sid": "IamListAccess",
                                "Effect": "Allow",
                                "Action": [
                                    "iam:ListRoles",
                                    "iam:ListUsers"
                                ],
                                "Resource": "arn:aws:iam::123456789012:user/*"
                            }
                        ]
                    }
                }
                """;

        assertTrue(JsonVerifier.verifyJson(jsonData));
    }

    @Test
    public void testVerifyJsonWithInvalidJsonFormat() {
        String invalidJsonData = "This is not a valid JSON";

        assertTrue(JsonVerifier.verifyJson(invalidJsonData));
    }

    @Test
    public void testVerifyJsonWithEmptyResource() {
        String jsonDataWithEmptyResource = """
                {
                    "PolicyName": "root",
                    "PolicyDocument": {
                        "Version": "2012-10-17",
                        "Statement": [
                            {
                                "Sid": "IamListAccess",
                                "Effect": "Allow",
                                "Action": [
                                    "iam:ListRoles",
                                    "iam:ListUsers"
                                ],
                                "Resource": ""
                            }
                        ]
                    }
                }
                """;

        assertTrue(JsonVerifier.verifyJson(jsonDataWithEmptyResource));
    }
}