package dxtr.familytree.model;

import dxtr.familytree.interfaces.Family;
import dxtr.familytree.interfaces.Member;
import dxtr.familytree.errors.FamiltyTreeException;

import java.util.List;

public class FamilyImpl implements Family {

    // Both are at the same hierarchy, can be done with just one of the two....
    private Member king, queen;

    public FamilyImpl(Member king, Member queen){
        this.king = king;
        this.queen = queen;
    }

    @Override
    public Member findMember(String name) throws FamiltyTreeException {
        if(king.getName().equalsIgnoreCase(name)){
            return king;
        }

        if(queen.getName().equalsIgnoreCase(name)){
            return queen;
        }

        // throw new FamiltyTreeError(Error.PERSON_NOT_FOUND);
        return null;
    }

    @Override
    public List<Member> getRelatives(String name, String relation) throws FamiltyTreeException {
            return Relations.valueOf(relation).getRelatives(findMember(name));
    }
}

