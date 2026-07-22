package service;

import exception.MemberNotFoundException;
import exception.MemberAlreadyExistException;

import java.util.HashMap;
import model.Member;

public class MemberService {
    private HashMap<Long, Member> members;

    public MemberService(){
        members = new HashMap<>();
    }

    public boolean memberExists(long memberId){
        return members.containsKey(memberId);
    }

    public void addMember(Member member) throws MemberAlreadyExistException {
        if(member == null){
            throw new IllegalArgumentException("Member cannot be null");
        }
        if(memberExists(member.getMemberId())){
            throw new MemberAlreadyExistException("Member of ID: " + member.getMemberId() + " already exist." );
        }
        members.put(member.getMemberId(), member);
    }

    public void removeMember(long memberId) throws MemberNotFoundException{
        Member member = findMemberById(memberId);
        members.remove(member.getMemberId());
    }

    public void displayMembers(){
        if(members.isEmpty()){
            System.out.println("No members found in the Library");
        } else {
            for(Member member : members.values()){
                System.out.print(member);
            }
        }
    }

    public Member findMemberById(long memberid) throws MemberNotFoundException {

        Member member = members.get(memberid);
        if (member == null) {
            throw new MemberNotFoundException("Member with Id: "+memberid + " not found.");
        }
        return member;
            
    }

}

