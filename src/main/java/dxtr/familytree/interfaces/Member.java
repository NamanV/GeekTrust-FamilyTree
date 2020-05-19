package dxtr.familytree.interfaces;

import dxtr.familytree.errors.MembersException;
import dxtr.familytree.utility.EnumUtility.GENDER;

import java.util.List;

public interface Member {

    public void addSpouse(Member spouse);

    public GENDER getGender();

    public String getName();

    public Member getParent(GENDER gender);

    public void setParent(GENDER gender, Member parent);

    public Member getSpouse();

    public List<Member> getChildren();

    public void addChild(Member member) throws MembersException;
}
