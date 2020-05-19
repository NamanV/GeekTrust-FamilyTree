package dxtr.familytree.handler;

import dxtr.familytree.interfaces.Member;
import dxtr.familytree.utility.EnumUtility.GENDER;
import dxtr.familytree.errors.Error;
import dxtr.familytree.errors.RelationsException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RelationsHandler {

    public enum RelationType {
        MATERNAL, PATERNAL, SIBLING_IN_LAWS;
    }

    public static List<Member> getUnclesOrAunt(final Member ofMember, RelationType relationType, final GENDER gender) throws RelationsException {
        Member parent;
        switch (relationType) {
            case MATERNAL:
                parent = ofMember.getParent(GENDER.FEMALE);
                break;
            case PATERNAL:
                parent = ofMember.getParent(GENDER.MALE);
                break;
            default:
                throw new RelationsException(Error.RELATION_NOT_FOUND);
        }

        Member grandMother = parent.getParent(GENDER.FEMALE);
        List<Member> unclesOrAunt =
                grandMother.getChildren().stream().filter(sibling -> sibling.getGender().equals(gender) && !sibling.getName().equalsIgnoreCase(parent.getName())).collect(Collectors.toList());
        return unclesOrAunt;
    }

    // Sister-in-Laws {Spouse’s sisters, Wives of siblings}
    public static List<Member> getInLawsSiblings(Member ofMember, RelationType relationType, GENDER gender) {
        List<Member> siblingsInLaws = null;

        // Spouse's Sisters
        if (Objects.nonNull(ofMember) && Objects.nonNull(ofMember.getSpouse()) && Objects.nonNull(ofMember.getSpouse().getParent(GENDER.MALE))) {
            siblingsInLaws.addAll(ofMember.getSpouse().getParent(GENDER.MALE).getChildren().stream().filter((member ->
                    member.getGender().equals(GENDER.FEMALE))).collect(Collectors.toList()));
        }

        if (Objects.nonNull(ofMember) && Objects.nonNull(ofMember.getParent(GENDER.MALE))) {
            siblingsInLaws.addAll(ofMember.getParent(GENDER.MALE).getChildren().stream().filter(member -> member.getSpouse()!=null && member.getSpouse().getGender().equals(gender)).collect(Collectors.toList()));
        }

        return siblingsInLaws;
    }

    public List<Member> getChildren(Member ofMember, RelationType relationType, GENDER gender) {
        return ofMember.getChildren().stream().filter(child -> child.getGender().equals(gender)).collect(Collectors.toList());
    }
}
