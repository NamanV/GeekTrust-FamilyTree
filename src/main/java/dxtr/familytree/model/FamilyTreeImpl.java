package dxtr.familytree.model;

import dxtr.familytree.interfaces.FamilyTree;
import dxtr.familytree.interfaces.Member;
import dxtr.familytree.utility.FamiltyTreeError;
import dxtr.familytree.utility.RelationsException;

import java.util.List;

public class FamilyTreeImpl implements FamilyTree {

    // Both are at the same hierarchy, can be done with just one of the two....
    private Member king, queen;

    @Override
    public Member findMember(String name) throws FamiltyTreeError {
        // throw new FamiltyTreeError(Error.PERSON_NOT_FOUND);
        return null;
    }

    @Override
    public List<Member> getRelatives(String name, String relation) throws FamiltyTreeError,RelationsException {
            return Relations.valueOf(relation).getRelatives(findMember(name));
    }
}

