package dxtr.familytree.model;

import dxtr.familytree.interfaces.Member;
import dxtr.familytree.utility.EnumUtility.GENDER;

import java.util.List;

public class MemberImpl implements Member {

    private String name;
    private GENDER gender;
    private Member spouse;
    private Member father,mother;
    private List<Member> children;

    @Override
    public List<Member> getChildren() {
        return children;
    }

    @Override
    public Member getParent(GENDER gender) {
        switch (gender){
            case MALE:
                return father;
            case FEMALE:
                return mother;
            default:
                return null;
        }
    }

    @Override
    public Member getSpouse() {
        return spouse;
    }

    @Override
    public GENDER getGender() {
        return gender;
    }

    @Override
    public String getName() {
        return name;
    }
}
