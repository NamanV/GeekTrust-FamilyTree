package dxtr.familytree.interfaces;

import dxtr.familytree.utility.FamiltyTreeError;
import dxtr.familytree.utility.RelationsException;

import java.util.List;

public interface FamilyTree {
    public Member findMember(String name) throws FamiltyTreeError;
    public List<Member> getRelatives(String name, String relation) throws FamiltyTreeError, RelationsException;
}
