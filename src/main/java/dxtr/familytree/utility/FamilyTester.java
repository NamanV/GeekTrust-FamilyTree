package dxtr.familytree.utility;

import dxtr.familytree.interfaces.Family;
import dxtr.familytree.interfaces.Member;
import dxtr.familytree.model.FamilyImpl;
import dxtr.familytree.model.MemberImpl;

public class FamilyTester {

    public static void main(String [] args){
        Member king = new MemberImpl("King Shan", EnumUtility.GENDER.MALE,null,null);
        Member queen = new MemberImpl("King Anga", EnumUtility.GENDER.FEMALE,null,null);

        king.addSpouse(queen);
        queen.addSpouse(king);

        Family family = new FamilyImpl(king,queen);
    }
}
