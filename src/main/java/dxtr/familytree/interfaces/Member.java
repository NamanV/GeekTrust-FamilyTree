package dxtr.familytree.interfaces;

import dxtr.familytree.utility.EnumUtility.GENDER;

import java.util.List;

public interface Member {

    public GENDER getGender();

    public String getName();

    public Member getParent(GENDER gender);

    public Member getSpouse();

    public List<Member> getChildren();
}
