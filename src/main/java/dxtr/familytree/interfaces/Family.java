package dxtr.familytree.interfaces;

import dxtr.familytree.errors.FamiltyTreeException;
import dxtr.familytree.model.Relations;

import java.util.List;

public interface Family {

    public Member findMember(String name) throws FamiltyTreeException;
    public List<Member> getRelatives(String name, String relation) throws FamiltyTreeException;
    public void addKing(Member member);
    public void addQueen(Member member);

    public void printFamilyTree();

}
