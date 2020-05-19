package dxtr.familytree.interfaces;

import dxtr.familytree.errors.FamilyTreeException;

import java.util.List;

public interface Family {

    public Member findMember(String name) throws FamilyTreeException;
    public List<Member> getRelatives(String name, String relation) throws FamilyTreeException;
    public void addKing(Member member);
    public void addQueen(Member member);

    public void printFamilyTree();

}
