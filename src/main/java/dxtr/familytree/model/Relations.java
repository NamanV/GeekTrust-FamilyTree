package dxtr.familytree.model;

import dxtr.familytree.errors.Error;
import dxtr.familytree.handler.RelationsHandler;
import dxtr.familytree.interfaces.Member;
import dxtr.familytree.utility.EnumUtility.GENDER;
import dxtr.familytree.errors.RelationsException;

import java.util.List;
import java.util.Objects;

public enum Relations {

    MATERNAL_UNCLE {
        @Override
        public List<Member> getRelatives(Member ofMember) throws RelationsException {
            return RelationsHandler.getUnclesOrAunt(ofMember, RelationsHandler.RelationType.MATERNAL, GENDER.MALE);
        }
        @Override
        public Member addMember(Member newMember, Member toMember) throws RelationsException {
            return null;
        }
    }, PATERNAL_UNCLE {
        @Override
        public List<Member> getRelatives(Member ofMember) throws RelationsException {
            return RelationsHandler.getUnclesOrAunt(ofMember, RelationsHandler.RelationType.PATERNAL, GENDER.MALE);
        }
        @Override
        public Member addMember(Member newMember, Member toMember) throws RelationsException {
            return null;
        }
    }, MATERNAL_AUNT {
        @Override
        public List<Member> getRelatives(Member ofMember) throws RelationsException {
            return RelationsHandler.getUnclesOrAunt(ofMember, RelationsHandler.RelationType.MATERNAL, GENDER.FEMALE);
        }

        @Override
        public Member addMember(Member newMember, Member toMember) throws RelationsException {
            return null;
        }
    }, PATERNAL_AUNT {
        @Override
        public List<Member> getRelatives(Member ofMember) throws RelationsException {
            return RelationsHandler.getUnclesOrAunt(ofMember, RelationsHandler.RelationType.PATERNAL, GENDER.FEMALE);
        }

        @Override
        public Member addMember(Member newMember, Member toMember) throws RelationsException {
            return null;
        }
    }, SISTER_IN_LAW {
        @Override
        public List<Member> getRelatives(Member ofMember) {
            return RelationsHandler.getInLawsSiblings(ofMember, RelationsHandler.RelationType.SIBLING_IN_LAWS,GENDER.FEMALE);
        }

        @Override
        public Member addMember(Member newMember, Member toMember) throws RelationsException {
            return null;
        }
    }, BROTHER_IN_LAW {
        @Override
        public List<Member> getRelatives(Member ofMember) {
            return null;
        }

        @Override
        public Member addMember(Member newMember, Member toMember) throws RelationsException {
            return null;
        }
    }, SON {
        @Override
        public List<Member> getRelatives(Member ofMember) {
            return null;
        }

        @Override
        public Member addMember(Member newMember, Member toMember) throws RelationsException {
            if(Objects.nonNull(newMember) && Objects.nonNull(toMember)) {
                //TODO: set Mother and father of new member here
                toMember.getChildren().add(newMember);
                return newMember;
            }
            throw new RelationsException(Error.CHILD_ADDITION_FAILED);
        }
    }, DAUGHTER {
        @Override
        public List<Member> getRelatives(Member ofMember) {
            return null;
        }

        @Override
        public Member addMember(Member newMember, Member toMember) throws RelationsException {
            if(Objects.nonNull(newMember) && Objects.nonNull(toMember)) {
                //TODO: set Mother and father of new member here
                toMember.getChildren().add(newMember);
                return newMember;
            }
            throw new RelationsException(Error.CHILD_ADDITION_FAILED);
        }
    }, SIBLINGS {
        @Override
        public List<Member> getRelatives(Member ofMember) {
            return null;
        }

        @Override
        public Member addMember(Member newMember, Member toMember) throws RelationsException {
            return null;
        }
    };

    public abstract List<Member> getRelatives(Member ofMember) throws RelationsException;
    public abstract Member addMember(Member newMember, Member toMember) throws RelationsException;

}
