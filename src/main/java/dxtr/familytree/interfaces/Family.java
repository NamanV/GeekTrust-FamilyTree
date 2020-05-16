package dxtr.familytree.interfaces;

import dxtr.familytree.errors.FamiltyTreeException;

import java.util.List;

public interface Family {
    public Member findMember(String name) throws FamiltyTreeException;
    public List<Member> getRelatives(String name, String relation) throws FamiltyTreeException;
}
