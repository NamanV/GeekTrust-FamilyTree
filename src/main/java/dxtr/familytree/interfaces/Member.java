package dxtr.familytree.interfaces;

import dxtr.familytree.errors.MembersException;
import dxtr.familytree.utility.EnumUtility.GENDER;
import dxtr.familytree.utility.EnumUtility.RelationType;

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

    public List<Member> getUnclesOrAunt(RelationType relationType, final GENDER gender) throws MembersException;

    public List<Member> getInLawsSiblings(RelationType relationType, GENDER gender) throws MembersException;
}
