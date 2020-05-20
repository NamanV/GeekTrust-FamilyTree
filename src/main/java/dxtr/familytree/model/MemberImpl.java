package dxtr.familytree.model;

import dxtr.familytree.errors.Error;
import dxtr.familytree.errors.MembersException;
import dxtr.familytree.interfaces.Member;
import dxtr.familytree.utility.EnumUtility.GENDER;
import dxtr.familytree.utility.EnumUtility.RelationType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    public void addChild(Member member) throws MembersException {
        if(!getGender().equals(GENDER.FEMALE)){
            throw new MembersException(Error.CHILD_ADDITION_FAILED);
        }

        children.add(member);
        getSpouse().getChildren().add(member);
        member.setParent(GENDER.FEMALE,this);
        member.setParent(GENDER.MALE,this.getSpouse());
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

    @Override
    public List<Member> getUnclesOrAunt(RelationType relationType, final GENDER gender) throws MembersException{
        Member parent;
        switch (relationType) {
            case MATERNAL:
                parent = getParent(GENDER.FEMALE);
                break;
            case PATERNAL:
                parent = getParent(GENDER.MALE);
                break;
            default:
                throw new MembersException(Error.RELATION_NOT_FOUND);
        }

        Member grandMother = parent.getParent(GENDER.FEMALE);
        List<Member> unclesOrAunt =
                grandMother.getChildren().stream().filter(sibling -> sibling.getGender().equals(gender) && !sibling.getName().equalsIgnoreCase(parent.getName())).collect(Collectors.toList());
        return unclesOrAunt;
    }

    // Sister-in-Laws {Spouse’s sisters, Wives of siblings}
    // Brother-in-Laws {Spouse’s brothers, Husbands of siblings}
    @Override
    public List<Member> getInLawsSiblings(RelationType relationType, GENDER gender) throws MembersException{
        List<Member> siblingsInLaws = new ArrayList<>();

        // Spouse's Sisters or Brothers depending on gender
        if (Objects.nonNull(this) && Objects.nonNull(this.getSpouse()) && Objects.nonNull(this.getSpouse().getParent(GENDER.MALE))) {
            siblingsInLaws.addAll(this.getSpouse().getParent(GENDER.MALE).getChildren().stream().filter((member ->
                    member.getGender().equals(gender) && (Objects.isNull(member.getSpouse()) || !member.getSpouse().getName().equalsIgnoreCase(this.getName())))).collect(Collectors.toList()));
        }

        // Wives/Husbands of siblings depending on gender
        if (Objects.nonNull(this) && Objects.nonNull(this.getParent(GENDER.MALE))) {
            siblingsInLaws.addAll(this.getParent(GENDER.MALE).getChildren().stream()
                    .filter(member -> !member.getName().equalsIgnoreCase(this.getName()) &&member.getSpouse()!=null && member.getSpouse().getGender().equals(gender))
                    .map(member -> member.getSpouse()).filter(member -> member.getSpouse()!=null)
                    .collect(Collectors.toList()));
        }

        return siblingsInLaws;
    }

}
