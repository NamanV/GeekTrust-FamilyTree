package dxtr.familytree.model;

import dxtr.familytree.handler.RelationsHandler;
import dxtr.familytree.interfaces.Member;
import dxtr.familytree.utility.EnumUtility.GENDER;
import dxtr.familytree.utility.RelationsException;

import java.util.List;
import java.util.stream.Collectors;

public enum Relations {

    MATERNAL_UNCLE {
        @Override
        public List<Member> getRelatives(Member ofMember) throws RelationsException {
            return RelationsHandler.getUnclesOrAunt(ofMember, RelationsHandler.RelationType.MATERNAL, GENDER.MALE);
            /*
            Member mother = ofMember.getParent(GENDER.FEMALE);
            Member grandMother = mother.getParent(GENDER.FEMALE);
            List<Member> maternalUncles =
                    grandMother.getChildren().stream().filter(sibling -> sibling.getGender().equals(GENDER.MALE)).collect(Collectors.toList());
            return maternalUncles;
             */
        }
    }, PATERNAL_UNCLE {
        @Override
        public List<Member> getRelatives(Member ofMember) throws RelationsException {
            return RelationsHandler.getUnclesOrAunt(ofMember, RelationsHandler.RelationType.PATERNAL, GENDER.MALE);
        }
    }, MATERNAL_AUNT {
        @Override
        public List<Member> getRelatives(Member ofMember) throws RelationsException {
            return RelationsHandler.getUnclesOrAunt(ofMember, RelationsHandler.RelationType.MATERNAL, GENDER.FEMALE);
        }

    }, PATERNAL_AUNT {
        @Override
        public List<Member> getRelatives(Member ofMember) throws RelationsException {
            return RelationsHandler.getUnclesOrAunt(ofMember, RelationsHandler.RelationType.PATERNAL, GENDER.FEMALE);
        }

    }, SISTER_IN_LAW {
        @Override
        public List<Member> getRelatives(Member ofMember) {
            return null;
        }

    }, BROTHER_IN_LAW {
        @Override
        public List<Member> getRelatives(Member ofMember) {
            return null;
        }

    }, SON {
        @Override
        public List<Member> getRelatives(Member ofMember) {
            return null;
        }

    }, DAUGHTER {
        @Override
        public List<Member> getRelatives(Member ofMember) {
            return null;
        }

    }, SIBLINGS {
        @Override
        public List<Member> getRelatives(Member ofMember) {
            return null;
        }

    };

    public abstract List<Member> getRelatives(Member ofMember) throws RelationsException;

}
