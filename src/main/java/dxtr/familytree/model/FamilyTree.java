package dxtr.familytree.model;

import dxtr.familytree.errors.Error;
import dxtr.familytree.errors.FamilyTreeException;
import dxtr.familytree.interfaces.Family;
import dxtr.familytree.interfaces.Member;
import dxtr.familytree.utility.EnumUtility;
import dxtr.familytree.utility.EnumUtility.COMMANDS;
import dxtr.familytree.utility.EnumUtility.GENDER;
import dxtr.familytree.utility.Logger;
import sun.rmi.runtime.Log;

import java.util.List;

public class FamilyTree {

    private Family family;

    public FamilyTree() {
        family = new FamilyImpl();
    }

    public void initFamilyTree(List<String> instructions) {
        for (String instruction : instructions) {
            processInput(instruction,true);
        }
    }

    public void processInput(String instruction, boolean initFile) {
        String[] subInstruction = instruction.split(" ");

        COMMANDS command = EnumUtility.load(subInstruction[0], COMMANDS.class, COMMANDS.NOT_FOUND);
        switch (command) {
            case ADD_CHILD:
                try {
                    GENDER gender = EnumUtility.loadUpperCase(subInstruction[3],GENDER.class,GENDER.NONE);
                    if(gender.equals(GENDER.NONE)){
                        throw new FamilyTreeException(Error.INVALID_GENDER);
                    }
                    Member parent = family.findMember(subInstruction[1]);
                    Member member = new MemberImpl(subInstruction[2], EnumUtility.GENDER.valueOf(subInstruction[3].toUpperCase()), null, null);
                    parent.addChild(member);
                    if(!initFile){
                        Logger.printLog(Error.CHILD_ADDITION_SUCCEEDED.getErrorMessage());
                    }
                } catch (FamilyTreeException e) {
                    Logger.printLog(e.getMessage());
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
                } catch (FamilyTreeException e) {
                    Logger.printLog(e.getMessage());
                }
                break;
            case GET_RELATIONSHIP:
                try {
                    if(subInstruction.length < 3){
                        throw new FamilyTreeException(Error.INVALID_ARGUMENTS);
                    }
                    String relations = subInstruction[2].replace("-","_");
                    family.getRelatives(subInstruction[1], relations).forEach(member -> Logger.printLogSameLine(member + " "));
                    Logger.printLog("");
                } catch (FamilyTreeException e) {
                    Logger.printLog(e.getMessage());
                }
                break;
            case NOT_FOUND:
            default:
                Logger.printLog("Invalid instruction" + subInstruction[0] +", please use valid instructions.\n");
        }
    }
}

