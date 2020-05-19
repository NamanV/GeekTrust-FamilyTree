package dxtr.familytree.model;

import dxtr.familytree.errors.Error;
import dxtr.familytree.errors.FamiltyTreeException;
import dxtr.familytree.interfaces.Family;
import dxtr.familytree.interfaces.Member;
import dxtr.familytree.utility.EnumUtility;
import dxtr.familytree.utility.EnumUtility.COMMANDS;
import dxtr.familytree.utility.EnumUtility.GENDER;

import java.util.List;

public class FamilyTree {

    private Family family;

    public FamilyTree() {
        family = new FamilyImpl();
    }

    public void initFamilyTree(List<String> instructions) {
        for (String instruction : instructions) {
            processInput(instruction);
        }
    }


    public void processInput(String instruction) {
        System.out.println("Instruction : " + instruction);
        String[] subInstruction = instruction.split(" ");

        COMMANDS command = EnumUtility.load(subInstruction[0], COMMANDS.class, COMMANDS.NOT_FOUND);
        switch (command) {
            case ADD_CHILD:
                try {
                    Member parent = family.findMember(subInstruction[1]);
                    Member member = new MemberImpl(subInstruction[2], EnumUtility.GENDER.valueOf(subInstruction[3].toUpperCase()), null, null);
                    parent.addChild(member);
                    System.out.println(Error.CHILD_ADDITION_SUCCEEDED.getErrorMessage());
                } catch (FamiltyTreeException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case ADD_KING:
                family.addKing(new MemberImpl(subInstruction[1], EnumUtility.GENDER.valueOf(subInstruction[2].toUpperCase()), null, null));
                break;
            case ADD_QUEEN:
                family.addQueen(new MemberImpl(subInstruction[1], EnumUtility.GENDER.valueOf(subInstruction[2].toUpperCase()), null, null));
                break;
            case ADD_SPOUSE:
                try {
                    Member member = family.findMember(subInstruction[1]);
                    Member newMember = new MemberImpl(subInstruction[2], EnumUtility.GENDER.valueOf(subInstruction[3].toUpperCase()), null, null);
                    member.addSpouse(newMember);
                    newMember.addSpouse(member);
                } catch (FamiltyTreeException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case GET_RELATIONSHIP:
                try {
                    family.getRelatives(subInstruction[1], subInstruction[2]).forEach(member -> System.out.print(member + " "));
                    System.out.println("");
                } catch (FamiltyTreeException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case NOT_FOUND:
            default:
                System.out.printf("Invalid instruction %s, please use valid instructions.",subInstruction[0]);
        }
    }
}
