package uy.edu.um.entities;

import lombok.Getter;
import lombok.Setter;
import uy.edu.um.tad.linkedlist.MyLinkedListImpl;

@Getter
@Setter
public class Credits {
    private int id;
    private MyLinkedListImpl<CastMember> castMembers;
    private MyLinkedListImpl<CrewMember> crewMembers;

    public Credits(int id, MyLinkedListImpl<CastMember> castMembers, MyLinkedListImpl<CrewMember> crewMembers) {
        this.id = id;
        this.castMembers = castMembers;
        this.crewMembers = crewMembers;
    }
}
