package dxtr.familytree.model;

import dxtr.familytree.interfaces.Member;
import dxtr.familytree.utility.EnumUtility.GENDER;

import java.util.ArrayList;
import java.util.List;

public class MemberImpl implements Member {

    private String name;
    private GENDER gender;
    private Member spouse;
    private Member father,mother;
    private List<Member> children;

    public MemberImpl(String name, GENDER gender, Member father, Member mother){
       this.name = name;
       this.gender = gender;
       this.father = father;
       this.mother = mother;
       this.children = new ArrayList<>();
    }

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
    public void addSpouse(Member spouse) {
        this.spouse = spouse;
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
