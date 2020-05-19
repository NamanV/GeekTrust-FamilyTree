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

    @Override
    public void addChild(Member member) {
        children.add(member);
        getSpouse().getChildren().add(member);
        if(getGender().equals(GENDER.MALE)){
            member.setParent(GENDER.MALE,this);
            member.setParent(GENDER.FEMALE,this.getSpouse());
        }else{
            member.setParent(GENDER.FEMALE,this);
            member.setParent(GENDER.MALE,this.getSpouse());
        }
    }

    @Override
    public void setParent(GENDER gender, Member parent) {
       switch (gender){
           case MALE:
               this.father = parent;
               break;
           case FEMALE:
               this.mother = parent;
               break;
           default:
       }
    }

    @Override
    public String toString() {
        return this.name;
    }
}
