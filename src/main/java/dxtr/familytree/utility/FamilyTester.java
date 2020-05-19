package dxtr.familytree.utility;

import dxtr.familytree.errors.FamiltyTreeException;
import dxtr.familytree.interfaces.Family;
import dxtr.familytree.interfaces.Member;
import dxtr.familytree.model.FamilyImpl;
import dxtr.familytree.model.FamilyTree;
import dxtr.familytree.model.MemberImpl;
import sun.misc.ClassLoaderUtil;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
