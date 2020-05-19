package dxtr.familytree.utility;

import dxtr.familytree.errors.Error;
import dxtr.familytree.errors.FamilyTreeException;
import dxtr.familytree.model.FamilyTree;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FamilyTester {

    public static void main(String[] args) {

        try {
            List<String> instructions = processInputFileToInstructions(Constants.INIT_FILE_NAME);
            FamilyTree familyTree = new FamilyTree();
            familyTree.initFamilyTree(instructions);
            String inputFilePath;
            if(args.length <= 0){
                throw new FamilyTreeException(Error.INVALID_ARGUMENTS);
            }

            inputFilePath = args[0];

            instructions = processInputFile(inputFilePath);
            instructions.forEach((instruction) -> familyTree.processInput(instruction,false));
        } catch (IOException | FamilyTreeException |URISyntaxException e) {
            e.printStackTrace();
        }

    }

    private static void runThroughIDE(){
        try {
            List<String> instructions = processInputFileToInstructions(Constants.INIT_FILE_NAME);
            FamilyTree familyTree = new FamilyTree();
            familyTree.initFamilyTree(instructions);
            instructions = processInputFileToInstructions("/testcase-1.txt");
            instructions.forEach((instruction) -> familyTree.processInput(instruction,false));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> processInputFileToInstructions(String fileName) throws IOException {
        List<String> instructions = null;
        try (InputStream inputStream = FamilyTester.class.getClass().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            instructions = reader.lines()
                    .collect(Collectors.toList());
        }
        return instructions;
    }

    private static List<String> processInputFile(String fileName) throws IOException, URISyntaxException {
        File file = new File(fileName);
        Path path = Paths.get(file.toURI());
        return Files.readAllLines(path, StandardCharsets.UTF_8);
    }
}
