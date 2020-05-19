package dxtr.familytree.utility;

import dxtr.familytree.model.FamilyTree;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FamilyTester {

    public static void main(String [] args){
        try {
            List<String> instructions = processInputFile("initial-data.txt");
            FamilyTree familyTree = new FamilyTree();
            familyTree.initFamilyTree(instructions);

            instructions = processInputFile("input-test-case-1.txt");
            instructions.forEach((instruction) -> familyTree.processInput(instruction));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }



    }

    private static List<String> processInputFile(String fileName) throws IOException, URISyntaxException {
        URL url = Thread.currentThread().getContextClassLoader().getResource(fileName);
        Path path = Paths.get(url.toURI());
        return Files.readAllLines(path, StandardCharsets.UTF_8);
    }
}
