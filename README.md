# Instructions for Running the AWS IAM Role Policy JSON Verifier
### Prerequisites
- Java Development Kit (JDK) installed on your machine.

## How to Run
download release jar from github repository or follow beneath instructions

### Step 1: Clone the Repository
- Open your terminal or command prompt.
- Navigate to the directory where you want to clone the repository.
- Run the following command to clone the repository: `git clone https://github.com/your_username/aws-iam-role-policy-verifier.git`

### Step 2: Build project
Open Build tab and choose 'Build Project'

### Step 3: Navigate to the build Directory
Change into the directory of the jar file:
`cd project-name\out\artifacts\JsonVerifier_jar`

## Run the Program
Use the following command to run the program with a path to JSON string:
```
java JsonVerifier.jar /path/to/your/json/file.json
```
